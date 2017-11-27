package cn.itcast.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.service.Area;
import cn.itcast.bos.service.IOrderRemoteService;
import cn.itcast.bos.service.Order;
import cn.itcast.crm.service.Customer;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order model = new Order();
	
	@Override
	public Order getModel() {
		return model;
	}

	private String sendAreaInfo;//发件人区域信息
	
	private String recAreaInfo;//收件人区域信息
	
	public void setSendAreaInfo(String sendAreaInfo) {
		this.sendAreaInfo = sendAreaInfo;
	}
	
	public void setRecAreaInfo(String recAreaInfo) {
		this.recAreaInfo = recAreaInfo;
	}
	
	@Resource
	private IOrderRemoteService orderRemoteService;
	
	/**
	 * 订单保存方法
	 * @return
	 * @throws IOException 
	 */
	@Action(value="orderAction_add")
	public String add() throws IOException{
		try {
			//1.预处理区域信息
			if(StringUtils.isNotBlank(sendAreaInfo)){
				//发件人的区域录入了
				String[] split = sendAreaInfo.split("/");//[北京市，北京市，东城区]
				Area sendArea = new Area();
				sendArea.setProvince(split[0]);
				sendArea.setCity(split[1]);
				sendArea.setDistrict(split[2]);
				model.setSendArea(sendArea);
			}
			if(StringUtils.isNotBlank(recAreaInfo)){
				//发件人的区域录入了
				String[] split = recAreaInfo.split("/");//[北京市，北京市，东城区]
				Area recArea = new Area();
				recArea.setProvince(split[0]);
				recArea.setCity(split[1]);
				recArea.setDistrict(split[2]);
				model.setRecArea(recArea);
			}
			//2.绑定订单和客户
			Customer customer = (Customer) ServletActionContext
					.getRequest().getSession().getAttribute("loginCust");
			if(null != customer){
				model.setCustomerId(customer.getId());
			}
			//3.调用订单服务接口，保存订单
			orderRemoteService.saveOrder(model);
			//4.没有异常，保存成功，提示客户下单成功
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print("恭喜您，下单成功！");
		} catch (Exception e) {
			e.printStackTrace();
			//5.有异常，保存失败，提示客户下单失败
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print("对不起，下单失败！");
		}
		return NONE;
	}
}
