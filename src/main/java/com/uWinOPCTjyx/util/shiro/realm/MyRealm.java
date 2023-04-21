package com.uWinOPCTjyx.util.shiro.realm;

import com.uWinOPCTjyx.dao.UserListMapper;
import com.uWinOPCTjyx.entity.UserList;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

//import com.cqZnxj.entity.*;
//import com.cqZnxj.dao.*;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserListMapper userListMapper;
//	@Autowired
//	private RoleService roleService;
	/**
	 * 为账号进行授权并进行验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//		AccountMsg msg=(AccountMsg) SecurityUtils.getSubject().getPrincipal();
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		if(msg.getId()==null) {
//			return info;
//		}
//		try {
//			Set<String> roleNames =roleService.getRoleListByUserId(msg.getId());
//			Set<String> permissions =roleService.getPermissionByUserId(msg.getRole());
//			//TODO添加对应的方法
//			info.setRoles(roleNames);
//			info.setStringPermissions(permissions);
//			return info;
//		}catch (Exception e) {
//			e.printStackTrace();
//			return info;
//		}
		return null;
	}

	/**
	 * 对账号登录进行验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserList msg=new UserList(token.getUsername(),String.valueOf(token.getPassword()));

		UserList resultMsg=userListMapper.getUser(msg);
		System.out.println(resultMsg+";;;");
		if(token.getUsername().equals(resultMsg.getUserName())
				&&
				String.valueOf(token.getPassword()).equals(resultMsg.getPsd())){
			return new SimpleAuthenticationInfo(resultMsg,resultMsg.getPsd(),resultMsg.getUserName());
		}else{
			throw new AuthenticationException();
		}
	}

}
