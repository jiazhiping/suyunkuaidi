package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.PermissionRepository;
import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IPermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

	@Resource
	private PermissionRepository permissionRepository;

	@Override
	public Page<Permission> pageQuery(Specification<Permission> spec, Pageable pageable) {
		return permissionRepository.findAll(spec, pageable);
	}

	@Override
	public void add(Permission model) {
		permissionRepository.save(model);
	}

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	@Override
	public List<Permission> findPermsByUser(User user) {
		//1.判断当前用户是否是admin超级管理员
		String username = user.getUsername();
		if("admin".equals(username)){
			//2.如果是admin，查询所有的权限
			return permissionRepository.findAll();
		} else {
			//3.如果不是admin，根据用户id查询该用户的权限
			return permissionRepository.findPermsByUser(user.getId());
		}
	}

	/* 
	 * 根据 角色id 查询权限数据，转换成json数组，返回到界面
	 * (non-Javadoc)
	 * @see cn.itcast.bos.service.IPermissionService#findPermissionByRoleId(java.lang.Integer)
	 */
	@Override
	public List<Permission> findPermissionByRoleId(Integer id) {
		return permissionRepository.findPermissionByRoleId(id);
	}

	

}
