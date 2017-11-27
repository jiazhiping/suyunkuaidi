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

import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.service.IPermissionService;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class PermissionAction extends BaseAction<Permission> {

	@Resource
	private IPermissionService permissionService;

	/**
	 * 分页查询方法
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "permissionAction_pageQuery")
	public String pageQuery() throws IOException {
		// 1.封装查询参数
		// 参数1：当前页-1 firstindex = （当前页-1）*每页条数
		// 参数2：每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		Specification<Permission> spec = new Specification<Permission>() {
			/**
			 * root：保存当前实体类和表的映射关系 query：将多个查询条件组装成完整的查询条件 cb：组装单个条件，比如id =
			 * ?或name like ?
			 */
			@Override
			public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		};
		// 2.执行分页查询：总记录数和每页要显示的数据集合
		Page<Permission> page = permissionService.pageQuery(spec, pageable);
		String[] excludes = { "roles" };
		this.write2JsonObject(page, excludes);
		return NONE;
	}

	@Action(value = "permissionAction_add", results = {
			@Result(name = "success", location = "/pages/system/permission.jsp") })
	public String add() {
		permissionService.add(model);
		return "success";
	}

	/**
	 * 查询所有的权限数据，转换成json数组，返回到界面
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "permissionAction_findAll")
	public String findAll() throws IOException {
		List<Permission> list = permissionService.findAll();
		String[] excludes = { "roles" };
		this.write2JsonArray(list, excludes);
		return NONE;
	}

	/**
	 * 根据 角色id 查询权限数据，转换成json数组，返回到界面
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "permissionAction_findPermissionByRoleId")
	public String findPermissionByRoleId() throws IOException {

		List<Permission> list = permissionService.findPermissionByRoleId(model.getId());
		String[] excludes = { "roles" };
		this.write2JsonArray(list, excludes);
		return NONE;
	}

}
