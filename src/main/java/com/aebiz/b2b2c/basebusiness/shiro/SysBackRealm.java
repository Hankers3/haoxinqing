package com.aebiz.b2b2c.basebusiness.shiro;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.service.StorePlatPermitService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.service.StorePlatRolePermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.service.SysPermitService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.service.SysRoleService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

/**
 * 系统后台提供给shiro框架使用的自定义Realm
 * 
 * @author cc
 * 
 */
public class SysBackRealm extends AuthorizingRealm {
	/**
	 * SysManager模块的service，由spring注入
	 */
	@Autowired
	private SysManagerService sms = null;
	/**
	 * SysRole模块的service，由Spring注入
	 */
	@Autowired
	private SysRoleService srs = null;
	/**
	 * SysPermit模块的Service，由Spring注入
	 */
	@Autowired
	private SysPermitService sps = null;

	@Autowired
	private StorePlatRolePermitRelService storePlatRolePermitRelService;

	// 注入平台商户的权限service
	@Autowired
	private StorePlatPermitService storePlatPermitService;

	// 注入角色权限关系的service 为了获取权限角色关联关系,在去获取商户的平台权限

	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userString = (String) getAvailablePrincipal(principals);
		String[] user = userString.split(",");
		String userType = "";
		String userId = "";
		if (user.length > 0) {
			userId = user[1];
			userType = user[0];
		}
		userId = (String) getAvailablePrincipal(principals);
		// 通过用户名去获得用户的所有资源，并把资源存入info中
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 后台登陆登录
		if (!StringUtil.isEmpty(userType) && "system".equals(userType)) {
			Set<String> roleIds = srs.getRoleIdsByUserId(userId);

			System.out
					.println("=================run shiro doGetAuthorizationInfo start =======");

			info.setRoles(roleIds);

			Set<String> roleSet = sps.getPermitExprsByRoleIds(roleIds);

			info.setStringPermissions(roleSet);

			Iterator perIt = roleIds.iterator();
			while (perIt.hasNext()) {
				System.out.println("permit===========" + perIt.next());
			}

			Iterator roleIt = roleIds.iterator();
			while (roleIt.hasNext()) {
				System.out.println("roles===========" + roleIt.next());
			}

			System.out
					.println("=================run shiro doGetAuthorizationInfo end =======");
		}

		return info;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// token中储存着输入的用户名和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String userString = upToken.getUsername();
		String[] user = userString.split(",");
		String userType = "";
		String userId = "";
		if (user != null && user.length > 0) {
			userId = user[1];
			userType = user[0];
		}

		// 后台登陆登录
		if (!StringUtil.isEmpty(userType) && "system".equals(userType)) {
			// String newPwd = new Sha256Hash(upToken.getPassword()).toHex();
			SysManagerModel smm = sms.getById(userId);

			if (smm != null) {
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
						userId, smm.getPwd().toCharArray(), getName());
				return info;
			}

		}
		
		// 会员登录
	/*	if (!StringUtil.isEmpty(userType) && "customer".equals(userType)) {
			// 根据会员输入的用户名来查找会员
			CustomerModel customer = customerInteractive
					.getCustomerModelByLoginNameOrMobileOrEmail(userId);
			if (customer != null) {
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
						userId, customer.getPassword(), getName());
				return info;
			}
		}*/
		return null;

	}

	/**
	 * 自己添加的，用来清除shiro缓存的用户权限数据
	 * 
	 * @param userId
	 *            要清除Shiro缓存的userId
	 */
	public void removeUserCache(String userId) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(userId, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}

	/**
	 * ========================================私有方法============================
	 * =========================
	 */

	/**
	 * 获取主账户获取已经关联的角色的 权限
	 * 
	 * @param accountModel
	 * @return List<StorePlatMenuModel>
	 */
	private Set<String> getAdminPrivilegesByStoreRole(Set<String> roleIds) {
		// 定义list获取所有该商户角色的平台权限的uuid集合
		Set<String> priviligeSet = new HashSet<String>();

		if (roleIds != null && roleIds.size() > 0) {
			for (String roleId : roleIds) {
				// 获取平台角色关联的权限
				List<StorePlatRolePermitRelModel> storePlatRolePermitRelModels = storePlatRolePermitRelService
						.getAllPermitRelsByRoleUuid(roleId);
				if (storePlatRolePermitRelModels != null
						&& storePlatRolePermitRelModels.size() > 0) {
					for (StorePlatRolePermitRelModel storePlatRolePermitRelModel : storePlatRolePermitRelModels) {
						// 获取该商户的所有的平台权限
						StorePlatPermitModel platPermitModel = storePlatPermitService
								.getByUuid(storePlatRolePermitRelModel
										.getPermitUuid());
						priviligeSet.add(platPermitModel.getUuid());
					}
				}

			}
		}
		return priviligeSet;
	}
}
