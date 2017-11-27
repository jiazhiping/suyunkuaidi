package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
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

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.service.IMenuService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction<Menu> {
	
	@Resource
	private IMenuService menuService;
	
	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException 
	 */
	@Action(value="menuAction_pageQuery")
	public String pageQuery() throws IOException{
		//1.封装查询参数
		//参数1：当前页-1  firstindex = （当前页-1）*每页条数
		//参数2：每页条数
		Pageable pageable = new PageRequest(Integer.parseInt(model.getPage()) - 1, rows);
		Specification<Menu> spec = new Specification<Menu>() {
			/**
			 * root：保存当前实体类和表的映射关系
			 * query：将多个查询条件组装成完整的查询条件 
			 * cb：组装单个条件，比如id = ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<Menu> root, 
						CriteriaQuery<?> query, CriteriaBuilder cb) {
				//只查询parentMenu is null的根节点数据，防止重复数据
	            return cb.isNull(root.get("parentMenu").as(Menu.class));
			}
		};
		//2.执行分页查询：总记录数和每页要显示的数据集合
		Page<Menu> page = menuService.pageQuery(spec, pageable);
		String[] excludes = { "roles", "childrenMenus", "parentMenu"};
		this.write2JsonObject(page, excludes);
		return NONE;
	}
	/**
	 * 查询所有的根节点数据，转换成json数组，返回到前台
	 * @return
	 * @throws IOException 
	 */
	@Action(value="menuAction_findParentMenuIsNull")
	public String findParentMenuIsNull() throws IOException{
		//1.查询所有的根节点数据（parentMenu is null）
		List<Menu> list = menuService.findParentMenuIsNull();
		String[] excludes = {"roles", "childrenMenus", "parentMenu"};
		//2.转换成json数组，返回到前台
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	
	/**
	 * 保存方法
	 * @return
	 */
	@Action(value="menuAction_add",
			results={
					@Result(name="success",location="/pages/system/menu.jsp")
			})
	public String add(){
		//1.当未选择父菜单项时，父菜单属性关联了一个瞬时态对象，需要将其设置为null
		Menu parentMenu = model.getParentMenu();
		if(null != parentMenu
				&& null == parentMenu.getId()){
			model.setParentMenu(null);
		}
		menuService.add(model);
		return "success";
	}
	
	/**
	 * 查询所有的菜单数据，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="menuAction_findAll")
	public String findAll() throws IOException{
		List<Menu> list = menuService.findAll();
		String[] excludes = {"roles", "childrenMenus", "parentMenu", "children"};
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	
	/**
	 * 根据用户信息，查询该用的所有的菜单，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="menuAction_findMenus")
	public String findMenus() throws IOException{
		List<Menu> list = menuService.findMenus();
		String[] excludes = {"roles", "childrenMenus", "parentMenu", "children"};
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	
	/**
	 * 根据 角色id 查询角色对应的所有菜单，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="menuAction_findMenuByRoleId")
	public String findMenuByRoleId() throws IOException{
		List<Menu> list = menuService.findMenuByRoleId(model.getId());
		String[] excludes = {"roles", "childrenMenus", "parentMenu", "children"};
		this.write2JsonArray(list, excludes);
		return NONE;
	}
	
}
