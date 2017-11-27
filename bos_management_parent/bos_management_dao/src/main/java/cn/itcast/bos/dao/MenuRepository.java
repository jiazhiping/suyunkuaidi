package cn.itcast.bos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu>{

	List<Menu> findByParentMenuIsNull();

	@Query("select distinct m from Menu m join m.roles r join r.users u where u.id=?1")
	List<Menu> findMenusByUser(Integer id);

	/**
	 * 根据 角色id 查询角色对应的所有菜单，转换成json数组，返回到界面
	 * @param id
	 * @return
	 */
	@Query("select distinct m from Menu m join m.roles r where r.id=?1")
	List<Menu> findMenuByRoleId(Integer id);

}
