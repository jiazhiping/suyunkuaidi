package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.IStandardService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller//相当于<bean id="" class="" scope="pototype"/>
@Scope("prototype")
public class StandardAction extends ActionSupport implements ModelDriven<Standard> {

	private Standard model = new Standard();
	
	@Override
	public Standard getModel() {
		return model;
	}

	@Autowired
	private IStandardService standardService;
	
	/**
	 * 取派标准保存方法
	 * @return
	 */
	@Action(value="standardAction_add",
			results={
					@Result(name="success",location="/pages/base/standard.jsp")
			})
	public String add(){
		standardService.add(model);
		return "success";
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
	@Action(value="standardAction_pageQuery")
	public String pageQuery() throws IOException{
		//1.封装查询参数
		//参数1：当前页-1  firstindex = （当前页-1）*每页条数
		//参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		//2.执行分页查询：总记录数和每页要显示的数据集合
		Page<Standard> page = standardService.pageQuery(pageable);
		//3.将分页查询数据转换成json对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalElements());//总记录数
		map.put("rows", page.getContent());//当前页要显示的数据集合
		//json-lib将java数据转换成json数据
		//JSONObject：将java数据转换成json对象
		//JSONArray：将java数据转换成json数组
		JSONObject jsonObject = JSONObject.fromObject(map);
		String json = jsonObject.toString();
		//4.使用response将json对象返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
	
	/**
	 * 查询所有的收派标准数据，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="standardAction_findAll")
	public String findAll() throws IOException{
		//1.查询所有的收派标准数据
		List<Standard> list = standardService.findAll();
		//2.转换成json数组
		JSONArray jsonArray = JSONArray.fromObject(list);
		String json = jsonArray.toString();
		//3.返回到界面
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
	
	

	
}
