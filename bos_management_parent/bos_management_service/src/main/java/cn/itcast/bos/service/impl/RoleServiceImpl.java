package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.MenuRepository;
import cn.itcast.bos.dao.PermissionRepository;
import cn.itcast.bos.dao.RoleRepository;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Resource
	private RoleRepository roleRepository;
	@Resource
	private MenuRepository menuRepository;
	@Resource
	private PermissionRepository permissionRepository;

	@Override
	public Page<Role> pageQuery(Specification<Role> spec, Pageable pageable) {
		return roleRepository.findAll(spec, pageable);
	}

	@Override
	public void add(Role model, String menuIds, Integer[] permissionIds) {
		
		// 2.关联角色和菜单
		if (StringUtils.isNotBlank(menuIds)) {
			// 关联了菜单
			String[] menuIdArr = menuIds.split(",");
			for (String id : menuIdArr) {
				Menu menu = menuRepository.findOne(Integer.valueOf(id));
				model.getMenus().add(menu);
			}
		}
		// 3.关联角色和权限
		if (null != permissionIds && permissionIds.length > 0) {
			// 关联了权限
			for (Integer id : permissionIds) {
				Permission permission = permissionRepository.findOne(id);
				model.getPermissions().add(permission);
			}
		}
		
		// 1.保存角色数据
		roleRepository.save(model);
		
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public List<Role> findRolesByUser(User user) {
		// 1.判断当前用户是否是admin超级管理员
		String username = user.getUsername();
		if ("admin".equals(username)) {
			// 2.如果是admin，查询所有的权限
			return roleRepository.findAll();
		} else {
			// 3.如果不是admin，根据用户id查询该用户的权限
			return roleRepository.findRolesByUser(user.getId());
		}
	}

}
