package cn.itcast.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.UserRepository;
import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IUserService;
import cn.itcast.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Page<User> pageQuery(Specification<User> spec, Pageable pageable) {
		return userRepository.findAll(spec, pageable);
	}

	@Override
	public void add(User model, Integer[] roleIds) {
		//1.保存用户数据
		model.setPassword(MD5Utils.md5(model.getPassword()));
		userRepository.save(model);
		//2.关联用户和角色
		if(null != roleIds && roleIds.length > 0){
			//关联了角色
			for(Integer id : roleIds){
				Role role = new Role();
				role.setId(id);
				model.getRoles().add(role);
			}
		}
	}

}
