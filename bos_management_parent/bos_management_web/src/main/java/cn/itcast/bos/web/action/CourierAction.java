package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.ICourierService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller//相当于<bean id="" class="" scope="pototype"/>
@Scope("prototype")
public class CourierAction extends BaseAction<Courier> {

	@Autowired
	private ICourierService courierService;
	
	/**
	 * 快递员保存方法
	 * @return
	 */
	@Action(value="courierAction_add",
			results={
					@Result(name="success",location="/pages/base/courier.jsp"),
					@Result(name="failure",location="/unauthorizedUrl.jsp")
			})
	public String add(){
		try {
			courierService.add(model);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	private int page;//当前页
	
	private int rows;//每页条数
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException 
	 */
	@Action(value="courierAction_pageQuery")
	public String pageQuery() throws IOException{
		//1.封装查询参数
		//参数1：当前页-1  firstindex = （当前页-1）*每页条数
		//参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<Courier> spec = new Specification<Courier>() {
			/**
			 * root：保存当前实体类和表的映射关系
			 * query：将多个查询条件组装成完整的查询条件 
			 * cb：组装单个条件，比如id = ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<Courier> root, 
						CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				//1.获取查询条件数据:员工号courierNum
				String courierNum = model.getCourierNum();
				if(StringUtils.isNotBlank(courierNum)){
					//录入了条件
					list.add(cb.equal(root.get("courierNum").as(String.class), courierNum));
				}
				//2.获取查询条件数据:company
				String company = model.getCompany();
				if(StringUtils.isNotBlank(company)){
					//录入了条件
					list.add(cb.like(root.get("company").as(String.class), "%"+company+"%"));
				}
				//3.获取查询条件数据:type
				String type = model.getType();
				if(StringUtils.isNotBlank(type)){
					//录入了条件
					list.add(cb.equal(root.get("type").as(String.class), type));
				}
				//4.获取查询条件：standard.name
				Standard standard = model.getStandard();
				if(null != standard &&
						StringUtils.isNotBlank(standard.getName())){
					//录入了收派标准名
					Join<Courier,Standard> join = root.join(root.getModel().getSingularAttribute("standard",Standard.class));
					list.add(cb.like(join.get("name").as(String.class), "%"+standard.getName()+"%"));
				}
				Predicate[] pre = new Predicate[list.size()];
	            return query.where(list.toArray(pre)).getRestriction();
			}
		};
		//2.执行分页查询：总记录数和每页要显示的数据集合
		Page<Courier> page = courierService.pageQuery(spec, pageable);
		//3.将分页查询数据转换成json对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalElements());//总记录数
		map.put("rows", page.getContent());//当前页要显示的数据集合
		//json-lib将java数据转换成json数据
		//JSONObject：将java数据转换成json对象
		//JSONArray：将java数据转换成json数组
		//JsonConfig：配置转换的json数据中不需要的属性
		JsonConfig jsonConfig = new JsonConfig();
		String[] excludes = {"fixedAreas"};
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
		String json = jsonObject.toString();
		//4.使用response将json对象返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}

	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	@Action(value="courierAction_delete",
			results={
					@Result(name="success",location="/pages/base/courier.jsp"),
					@Result(name="failure",location="/unauthorizedUrl.jsp")
			})
	public String delete(){
		try {
			courierService.batchDelete(ids);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	/**
	 * 查询所有未作废（deltag=0）的快递员，转换成json数组，返回到前台
	 * @return
	 * @throws IOException 
	 */
	@Action(value="courierAction_listajax")
	public String listajax() throws IOException{
		//1.查询所有未作废（deltag=0）的快递员，
		List<Courier> list = courierService.findNoDel();
		String[] excludes = {"standard", "takeTime", "fixedAreas"};
		//2.转换成json数组，返回到前台
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	@Action(value="courierAction_resove",results={
			@Result(name="success",location="/pages/base/courier.jsp"),
			@Result(name="failure",location="/unauthorizedUrl.jsp")
	})
	public String resove(){
		try {
			courierService.resove(ids);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	

}
