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

import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.service.IRoleService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
	@Resource
	private IRoleService roleService;
	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException 
	 */
	@Action(value="roleAction_pageQuery")
	public String pageQuery() throws IOException{
		//1.封装查询参数
		//参数1：当前页-1  firstindex = （当前页-1）*每页条数
		//参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<Role> spec = new Specification<Role>() {
			/**
			 * root：保存当前实体类和表的映射关系
			 * query：将多个查询条件组装成完整的查询条件 
			 * cb：组装单个条件，比如id = ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<Role> root, 
						CriteriaQuery<?> query, CriteriaBuilder cb) {
	            return null;
			}
		};
		//2.执行分页查询：总记录数和每页要显示的数据集合
		Page<Role> page = roleService.pageQuery(spec, pageable);
		String[] excludes = { "users","permissions","menus" };
		this.write2JsonObject(page, excludes);
		return NONE;
	}

	private String menuIds;
	
	private Integer[] permissionIds;
	
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
	public void setPermissionIds(Integer[] permissionIds) {
		this.permissionIds = permissionIds;
	}
	
	/**
	 * 保存角色数据，关联角色和菜单，关联角色和权限，返回success结果集
	 * @return
	 */
	@Action(value="roleAction_add",
			results={
					@Result(name="success",location="/pages/system/role.jsp")
			})
	public String add(){
		roleService.add(model, menuIds, permissionIds);
		return "success";
	}
	
	/**
	 * 查询所有的角色数据，转换成json数组，返回到界面
	 * @return
	 * @throws IOException 
	 */
	@Action(value="roleAction_findAll")
	public String findAll() throws IOException{
		List<Role> list = roleService.findAll();
		String[] excludes = {"users","permissions","menus"};
		this.write2JsonArray(list, excludes);
		return NONE;
	}
}
