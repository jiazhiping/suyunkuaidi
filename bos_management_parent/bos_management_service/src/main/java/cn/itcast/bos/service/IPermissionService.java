package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.User;

public interface IPermissionService{

	Page<Permission> pageQuery(Specification<Permission> spec, Pageable pageable);

	void add(Permission model);

	List<Permission> findAll();

	List<Permission> findPermsByUser(User user);


	/**
	 * 根据 角色id 查询权限数据，转换成json数组，返回到界面
	 * @return
	 */
	List<Permission> findPermissionByRoleId(Integer id);

}
