package cn.itcast.bos.web.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.crm.service.ICustomerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
	@Resource
	protected ICustomerService customerService;
	
	protected int page;//当前页
	
	protected int rows;//每页条数
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	protected T model;// new User();
	
	@Override
	public T getModel() {
		return model;
	}

	public BaseAction(){
		//UserAction extends BaseAction<User>
		//UserAction userAction = new UserAction();
		//this == userAction
		//this.getClass() == UserAction.class
		//this.getClass().getGenericSuperclass() == BaseAction<User>.class
		ParameterizedType parameterizedType = (ParameterizedType) 
				this.getClass().getGenericSuperclass();
		//parameterizedType.getActualTypeArguments() == [User.class]
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		//actualTypeArguments[0] == User.class
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		try {
			//entityClass.newInstance() == new User()
			model = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将java数据转换成json对象方法
	 * @param page
	 * @param excludes
	 * @throws IOException
	 */
	public void write2JsonObject(Page<T> page, String[] excludes) throws IOException {
		// 3.将分页查询数据转换成json对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalElements());// 总记录数
		map.put("rows", page.getContent());// 当前页要显示的数据集合
		// json-lib将java数据转换成json数据
		// JSONObject：将java数据转换成json对象
		// JSONArray：将java数据转换成json数组
		// JsonConfig：配置转换的json数据中不需要的属性
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
		String json = jsonObject.toString();
		// 4.使用response将json对象返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	/**
	 * 将java数据转换成json数组的方法
	 * @param list
	 * @param excludes
	 * @throws IOException
	 */
	public void write2JsonArray(List<?> list, String[] excludes) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		String json = jsonArray.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
