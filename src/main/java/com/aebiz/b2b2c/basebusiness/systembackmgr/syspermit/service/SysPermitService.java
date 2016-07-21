package com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.service;

import java.util.List;
import java.util.Set;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface SysPermitService extends
		BaseService<SysPermitModel, SysPermitQueryModel> {

	public Set<String> getPermitExprsByRoleIds(Set<String> roleIds);

	/**
	 * 获得某个角色已经关联的菜单
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<SysPermitModel> getRolesSysPermit(String roleUuid);

	/**
	 * 获取系统某一类型的权限
	 * 
	 * 1.菜单权限 2.操作权限
	 * 
	 * @return
	 */
	public List<SysPermitModel> getAllOperteSysPermit(String bussinessType);

	/**
	 * 查询某个部门所拥有的权限
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysPermitModel> getDeptSysPermits(String deptUuid);
}
