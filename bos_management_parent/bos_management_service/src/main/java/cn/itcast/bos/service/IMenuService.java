package cn.itcast.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.bos.domain.base.Menu;

public interface IMenuService{

	Page<Menu> pageQuery(Specification<Menu> spec, Pageable pageable);

	List<Menu> findParentMenuIsNull();

	void add(Menu model);

	List<Menu> findAll();

	List<Menu> findMenus();

	/**
	 * 
	 * 根据 角色id 查询角色对应的所有菜单，转换成json数组，返回到界面
	 * 
	 * @param id
	 * @return
	 */
	List<Menu> findMenuByRoleId(Integer id);

}
