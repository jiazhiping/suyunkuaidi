package cn.itcast.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.MD5Utils;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Resource
	private IUserService userService;
	
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	/**
	 * 登录方法
	 * @return
	 */
	@Action(value="userAction_login",
			results={
					@Result(name="success",location="/index.jsp"),
					@Result(name="login",location="/login.jsp")
			})
	public String login(){
		//1.校验验证码是否正确
		String checkcodeS = (String) ServletActionContext
				.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode)
				&& checkcode.equals(checkcodeS)){
			//2.正确，使用shiro认证
			//2.1获取subject对象
			Subject subject = SecurityUtils.getSubject();//未认证状态
			AuthenticationToken authenticationtoken = new UsernamePasswordToken(
					model.getUsername(), MD5Utils.md5(model.getPassword()));
			//2.2使用subject进行认证
			try {
				subject.login(authenticationtoken);
				//2.3登录成功，将用户放到session中，跳转到首页
				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				//2.4.认证失败，跳转到登录界面
				return "login";
			}
		} else {
			//3.不正确，跳转登录界面
			return "login";
		}
	}
	
	/**
	 * 清理缓存，跳转登录界面
	 * @return
	 */
	@Action(value="userAction_logout",
			results={
					@Result(name="success", location="/login.jsp")
			})
	public String logout(){
		//1.获取subject对象
		Subject subject = SecurityUtils.getSubject();
		//2.调用subject注销方法清理缓存
		subject.logout();
		return "success";
	}
	
	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException 
	 */
	@Action(value="userAction_pageQuery")
	public String pageQuery() throws IOException{
		//1.封装查询参数
		//参数1：当前页-1  firstindex = （当前页-1）*每页条数
		//参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<User> spec = new Specification<User>() {
			/**
			 * root：保存当前实体类和表的映射关系
			 * query：将多个查询条件组装成完整的查询条件 
			 * cb：组装单个条件，比如id = ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<User> root, 
						CriteriaQuery<?> query, CriteriaBuilder cb) {
	            return null;
			}
		};
		//2.执行分页查询：总记录数和每页要显示的数据集合
		Page<User> page = userService.pageQuery(spec, pageable);
		String[] excludes = { "roles" };
		this.write2JsonObject(page, excludes);
		return NONE;
	}

	private Integer[] roleIds;
	
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * 保存用户信息，关联用户和角色，返回success结果集
	 * @return
	 */
	@Action(value="userAction_add",
			results={
					@Result(name="success", location="/pages/system/user.jsp")
			})
	public String add(){
		userService.add(model, roleIds);
		return "success";
	}
}
