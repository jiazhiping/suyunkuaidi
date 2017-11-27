package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.Request;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.Order;
import cn.itcast.bos.service.IOrderRemoteService;

@ParentPackage("struts-default")
@Namespace("/")
@Action
@Controller
@Scope("prototype")
public class OrderAction extends BaseAction<Order>{
	
	@Resource
	private IOrderRemoteService orderRemotoService;
	
	/**
	 * 查询需要人工调度的订单
	 * @return
	 * @throws IOException 
	 */
	@Action(value="orderAction_findOrderByHand")
	public String FindOrderByHand() throws IOException{
		//查询所有需要手工分单的订单
		List<Order> list = orderRemotoService.FindOrderByHand();
		String [] excludes = {"workBills","courier"};
		this.write2JsonArray(list, excludes);
		return NONE;
		
	}
	
	
	
	
	private Integer courierId;

	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}

	@Action(value="OrderAction_editType",results={
			@Result(name = "success",location = "/pages/take_delivery/dispatcher1.jsp"),
			@Result(name = "fail", location = "/unauthorizedUrl.jsp") 
	})
	public String EditType(){
		try {
			//获取选中数据id
			String id = ServletActionContext.getRequest().getParameter("id");
			
			orderRemotoService.EditType(id,courierId);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
	}

}
