package com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.dao;

import java.util.List;
import java.util.Set;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface SysPermitDAO extends
		BaseDAO<SysPermitModel, SysPermitQueryModel> {

	public void setDataDeleteFlag(String uuid, int delFlag);

	public Set<String> getPermitExprsByRoleIds(Set<String> roleIds);

	/**
	 * 获得某个角色已经关联的菜单
	 * 
	 * @param userUuid
	 * @return
	 */
	public List<SysPermitModel> getRolesSysPermit(String roleUuid);
	
	public List<String> getRolesSysPermitUuids(String roleUuid);

	/**
	 * 通过业务类型和业务ID获得权限数据
	 * 
	 * @param bussinessType
	 * @param bussinessUuid
	 * @return
	 */
	public SysPermitModel getSysPermitByMenuId(String bussinessType,
			String bussinessUuid);
	
	public String getSysPermitUuidByMenuId(String bussinessType,
			String bussinessUuid);

	/**
	 * 获得某一业务类型的权限
	 * 
	 * 例如 1.菜单权限 2.操作权限
	 * 
	 * @param bussinessType
	 * @return
	 */
	public List<SysPermitModel> getAllOperteSysPermit(String bussinessType);
	
	public List<String> getAllOperteSysPermitUuids(String bussinessType);
	
	/**
	 * 查询某个部门所拥有的权限
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysPermitModel> getDeptSysPermits(String deptUuid);
	
	public List<String> getDeptSysPermitUuids(String deptUuid);
}