package cn.itcast.bos.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.itcast.bos.domain.base.Permission;
import cn.itcast.bos.domain.base.Role;
import cn.itcast.bos.domain.base.User;
import cn.itcast.bos.service.IPermissionService;
import cn.itcast.bos.service.IRoleService;
import cn.itcast.bos.service.IUserService;

public class BosLoginRealm extends AuthorizingRealm{

	@Resource
	private IUserService userService;
	@Resource
	private IPermissionService permissionService;
	@Resource
	private IRoleService roleService;
	
	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalcollection) {
		//1.获取当前登录用户对象
		User user = (User) principalcollection.getPrimaryPrincipal();
		//2.根据用户信息（id）查询该用户的权限和角色
		//2.1查询用户权限
		List<Permission> permsList = permissionService.findPermsByUser(user);
		//2.2查询用户角色
		List<Role> rolesList = roleService.findRolesByUser(user);
		//3.将用户的权限和角色通过AuthorizationInfo，授予当前用户
		//3.1将权限授予用户
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		if(null != permsList && permsList.size() > 0){
			//用户有权限
			for(Permission perm : permsList){
				//使用AuthorizationInfo进行授权
				sai.addStringPermission(perm.getKeyword());
			}
		}
		//3.2将角色授予用户
		if(null != rolesList && rolesList.size() > 0){
			//用户有角色
			for(Role role : rolesList){
				//使用AuthorizationInfo进行授角色
				sai.addRole(role.getKeyword());
			}
		}
		return sai;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationtoken)
			throws AuthenticationException {
		//1.根据用户名查询用户对象
		UsernamePasswordToken upt = (UsernamePasswordToken)authenticationtoken;
		String username = upt.getUsername();
		User user = userService.findByUsername(username);
		if(null != user){
			//2.查询到用户，将用户对象和密码通过AuthenticationInfo返回给安全管理器，安全管理器会自动校验密码，
			//  如果校验通过，方法正常执行
			//  如果不通过，安全管理器直接抛异常
			//参数1：可以是任意对象，该参数上的值可以在系统中任意位置获取，一般方法user对象
			//参数2：放用户密码
			//参数3：当前realm的名称
			return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		}
		//3.查询不到用户，直接返回null给安全管理器，安全管理器会直接抛异常
		return null;
	}

}
