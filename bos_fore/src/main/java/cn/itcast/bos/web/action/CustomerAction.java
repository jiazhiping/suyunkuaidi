package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.activemq.producer.queue.QueueSender;
import cn.itcast.bos.utils.MailUtils;

import cn.itcast.crm.service.Customer;
import cn.itcast.crm.service.ICustomerService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	@Resource
	private QueueSender queueSender;
	
	private Customer model = new Customer();
	
	@Override
	public Customer getModel() {
		return model;
	}

	/**
	 * 发送短信验证码方法
	 * @return
	 */
	@Action(value="customerAction_sendMsg")
	public String sendMsg(){
		//1.生成验证码
		String validcode = RandomStringUtils.randomNumeric(4);
		//2.将验证码放到session中
		ServletActionContext.getRequest()
			.getSession().setAttribute(model.getTelephone(), validcode);
		String msg = "尊敬的用户您好，本次获取的验证码为："+validcode+"，服务电话：4006184000【传智播客】";
		//3.生成一个发送短信的消息，给sms系统，
		Map<String, String> map = new HashMap<String,String>();
		map.put("telephone", model.getTelephone());
		map.put("msg", msg);
		queueSender.send("test.map", map);
		return NONE;
	}

	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Resource
	private ICustomerService customerService;
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 用户注册方法
	 * @return
	 */
	@Action(value="customerAction_regist",
			results={
					@Result(name="success", location="/signup-success.html",type="redirect"),
					@Result(name="failure", location="/signup-fail.html",type="redirect")
			})
	public String regist(){
		//1.校验验证码
		/*String checkcodeS = (String) ServletActionContext
				.getRequest().getSession().getAttribute(model.getTelephone());
		if(StringUtils.isNotBlank(checkcode)
				&& checkcode.equals(checkcodeS)){*/
			//2.如果验证码正确，保存客户信息
			try {
				customerService.regist(model);
				//2.1没有异常，注册成功，跳转到成功界面
				//2.2生成邮箱激活码
				String activecode = RandomStringUtils.randomNumeric(32);
				//2.3将激活码在redis中保存24小时
				redisTemplate.opsForValue().set(
						model.getTelephone(), activecode, 24, TimeUnit.HOURS);
				String subject = "速运快递激活邮件";//标题
				String content = "尊敬的客户您好，请于24小时之内,进行邮箱绑定，点击下面地址完成绑定：<br/>"
						+"<a href='"+MailUtils.activeUrl
						+"?telephone="+model.getTelephone()
						+"&activecode="+activecode
						+"'>速运快递激活链接</a>";//内容
				//2.4通过javamail给客户邮箱发送激活邮件（客户手机号和激活码）
				MailUtils.sendMail(subject, content, model.getEmail());
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				//2.2发生异常，注册失败，跳转到失败界面
				return "failure";
			}
		/*} else {
			//3.如果验证码错误，直接跳转失败界面
			return "failure";
		}*/
	}

	private String activecode;
	
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	
	/**
	 * 客户邮箱激活方法
	 * @return
	 * @throws IOException 
	 */
	@Action(value="customerAction_activeMail")
	public String activeMail() throws IOException{
		//1.判断激活码是否正确
		String activecodeR = redisTemplate
				.opsForValue().get(model.getTelephone());
		if(StringUtils.isNotBlank(activecode)
				&& activecode.equals(activecodeR)){
			//2.如果正确，根据电话查询客户激活状态
			Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
			Integer type = customer.getType();
			if(null == type || 1!=type){
				//2.2如果客户未激活，调用激活方法，根据客户id更新激活状态（type=1）
				try {
					customerService.updateCustomerType(customer.getId());
					//2.3如果激活成功，提示客户激活成功
					ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
					ServletActionContext.getResponse().getWriter().print("恭喜您，激活成功，可以下单啦！");
				} catch (Exception e) {
					e.printStackTrace();
					//2.4如果激活失败，提示客户激活失败
					ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
					ServletActionContext.getResponse().getWriter().print("对不起，激活失败，请联系管理员！");
				}
			} else {
				//2.1如果客户已激活（type == 1），提示客户已经激活，不能重复激活
				ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
				ServletActionContext.getResponse().getWriter().print("对不起，您已经激活，不能重复激活！");
			}
		} else {
			//3.如果不正确，提示客户激活失败，验证码错误或失效！
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print("对不起，激活失败，验证码错误或失效！");
		}
		return NONE;
	}
	
	/**
	 * 登录方法
	 * @return
	 */
	@Action(value="customerAction_login",
			results={
					@Result(name="success",location="/myhome.html",type="redirect"),
					@Result(name="login",location="/login.html",type="redirect")
			})
	public String login(){
		//1.校验验证码是否正确
		String checkcodeS = (String) ServletActionContext
				.getRequest().getSession().getAttribute("validateCode");
		if(StringUtils.isNotBlank(checkcode)
				&& checkcode.equals(checkcodeS)){
			//2.如果正确，根据客户手机号和密码查询客户对象
			Customer customer = customerService.findCustomerByTelephoneAndPassword(
					model.getTelephone(), model.getPassword());
			if(null != customer){
				//2.1登录成功，将customer对象放到session，跳转主界面
				ServletActionContext.getRequest().getSession().setAttribute("loginCust", customer);
				return "success";
			} else {
				//2.2登录失败，返回登陆界面
				return "login";
			}
		} else {
			//3.如果不正确，直接跳转登陆界面
			return "login";
		}
	}
}
