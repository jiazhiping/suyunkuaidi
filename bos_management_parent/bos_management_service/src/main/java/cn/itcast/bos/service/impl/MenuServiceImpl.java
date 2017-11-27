package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.MenuRepository;
import cn.itcast.bos.domain.base.Menu;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IMenuService;

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Resource
	private MenuRepository menuRepository;

	@Override
	public Page<Menu> pageQuery(Specification<Menu> spec, Pageable pageable) {
		return menuRepository.findAll(spec, pageable);
	}

	@Override
	public List<Menu> findParentMenuIsNull() {
		return menuRepository.findByParentMenuIsNull();
	}

	@Override
	public void add(Menu model) {
		menuRepository.save(model);
	}

	@Override
	public List<Menu> findAll() {
		return menuRepository.findAll();
	}

	@Override
	public List<Menu> findMenus() {
		//1.获取当前登录用户对象
		User user = (User) ServletActionContext
				.getRequest().getSession().getAttribute("loginUser");
		//2.判断用户是否是admin
		if("admin".equals(user.getUsername())){
			//3.如果是admin，查询所有的菜单
			return menuRepository.findAll();
		} else {
			//4.如果不是admin，根据用户id查询该用户的菜单
			return menuRepository.findMenusByUser(user.getId());
		}
	}

	/* 
	 * 根据 角色id 查询角色对应的所有菜单，转换成json数组，返回到界面
	 * 
	 * (non-Javadoc)
	 * @see cn.itcast.bos.service.IMenuService#findMenuByRoleId(java.lang.Integer)
	 */
	@Override
	public List<Menu> findMenuByRoleId(Integer id) {
		
		return menuRepository.findMenuByRoleId(id);
	}

}
