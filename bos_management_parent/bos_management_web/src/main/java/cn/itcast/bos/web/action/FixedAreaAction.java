package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.IFixedAreaService;
import cn.itcast.bos.service.ISubAreaService;
import cn.itcast.crm.service.Customer;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class FixedAreaAction extends BaseAction<FixedArea> {
	
	@Resource
	private IFixedAreaService fixedAreaService;
	
	@Action(value="fixedAreaAction_save",
			results={
					@Result(name="success",location="/pages/base/fixed_area.jsp")
			})
	public String save(){
		fixedAreaService.save(model);
		return "success";
	}
	
	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException 
	 */
	@Action(value="fixedAreaAction_pageQuery")
	public String pageQuery() throws IOException{
		//1.封装查询参数
		//参数1：当前页-1  firstindex = （当前页-1）*每页条数
		//参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<FixedArea> spec = new Specification<FixedArea>() {
			/**
			 * root：保存当前实体类和表的映射关系
			 * query：将多个查询条件组装成完整的查询条件 
			 * cb：组装单个条件，比如id = ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<FixedArea> root, 
						CriteriaQuery<?> query, CriteriaBuilder cb) {
	            return null;
			}
		};
		//2.执行分页查询：总记录数和每页要显示的数据集合
		Page<FixedArea> page = fixedAreaService.pageQuery(spec, pageable);
		String[] excludes = { "subareas", "couriers"};
		this.write2JsonObject(page, excludes);
		return NONE;
	}
	
	/**
	 * 查询所有未关联定区的客户信息，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="fixedAreaAction_findNoGuanLianCustomers")
	public String findNoGuanLianCustomers() throws IOException{
		List<Customer> list = customerService.findNoGuanLianCustomers();
		String[] excludes = {};
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	
	/**
	 * 查询所有关联当前选中定区的客户信息，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="fixedAreaAction_findGuanLianCustomers")
	public String findGuanLianCustomers() throws IOException{
		List<Customer> list = customerService.findGuanLianCustomers(model.getId());
		String[] excludes = {};
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	
	private List<Integer> customerIds;

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	
	@Action(value="fixedAreaAction_assignCustomers2FixedArea",
			results={
					@Result(name="success",location="/pages/base/fixed_area.jsp")
			})
	public String assignCustomers2FixedArea(){
		customerService.assignCustomers2FixedArea(customerIds, model.getId());
		return "success";
	}
	
	
	private Integer courierId;
	
	private Integer takeTimeId;

	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}

	public void setTakeTimeId(Integer takeTimeId) {
		this.takeTimeId = takeTimeId;
	}
	/**
	 * 将定区、快递员、收派时间关联到一起，返回成功结果集
	 * @return
	 */
	@Action(value="fixedAreaAction_associationCourierToFixedArea",
			results={
					@Result(name="success",location="/pages/base/fixed_area.jsp")
			})
	public String associationCourierToFixedArea(){
		fixedAreaService.associationCourierToFixedArea(model.getId(), courierId, takeTimeId);
		return "success";
	}

	
	/**
	 * 查询所有关联当前选中定区的客户信息，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="fixedAreaAction_findxianshiGuanLianCustomers")
	public String findxianshiGuanLianCustomers() throws IOException{
		List<Customer> list = customerService.findGuanLianCustomers(model.getId());
		String[] excludes = {};
		this.write2JsonArray(list, excludes);
		return NONE;
	}




	@Resource
	private ISubAreaService subAreaService;
	
	private List<String> SubAreaIds;
	public void setSubAreaIds(List<String> subAreaIds) {
		this.SubAreaIds = subAreaIds;
	}
	/**
	 * 定区关联分区
	 * @return
	 */
	@Action(value="fixedAreaAction_assignSubArea",results={@Result(name="success",location="/pages/base/fixed_area.jsp")})
	public String assignSubArea(){
		subAreaService.assignSubArea(SubAreaIds,model.getId());
		return "success";
	}
	
	@Action(value="fixedAreaAction_findNoGuanLianFixedArea")
	public String findNoGuanLianFixedArea() throws IOException{
		List<SubArea> list=subAreaService.findNoGuanLianFixedArea();
		String[]excludes={"area"};
		this.write2JsonArray(list, excludes);
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value="fixedAreaAction_findGuanLianFixedArea")
	public String findGuanLianFixedArea() throws IOException{
		List<SubArea> list=subAreaService.findGuanLianFixedArea(model.getId());
		String[]excludes={"area","fixedArea"};
		this.write2JsonArray(list, excludes);
		return null;
	}
	
	@Action(value="fixedAreaAction_watchSubArea")
	public String watchSubArea() throws IOException{
		List<SubArea> list=subAreaService.watchSubArea(model.getId());
		String[]excludes={"subareas","fixedArea"};
		this.write2JsonArray(list, excludes);
		return null;
	}

	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	/**
	 * 根据选中id 删除数据
	 * @return
	 */
	@Action(value="fixedAreaAction_delete",
			results={
					@Result(name="success",location="/pages/base/fixed_area.jsp")
			})
	public String delete(){
		
			fixedAreaService.batchDelete(ids);
			return "success";
		
	}
	
}
