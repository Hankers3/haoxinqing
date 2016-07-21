package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.dao;

import java.util.List;
import java.util.Set;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;

public interface SysRoleDAO extends BaseDAO<SysRoleModel, SysRoleQueryModel> {
	public void setDataDeleteFlag(String uuid, int delFlag);

	/**
	 * 查询某个用户所拥有的角色
	 * 
	 * 一个用户可以有多个角色，根据用户ID查询此用户关联的角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public Set<String> getRoleIdsByUserId(String userId);

	/**
	 * 检查角色编号是否存在
	 * 
	 * 1.当添加时，传入的uuid为空，此时只需要检查数据库中是否存在 <br />
	 * 2.当编辑时，传入的uuid不为空，此事在检查数据库中时，需要排除当前本身
	 * 
	 * @param uuid
	 * @param roleId
	 * @return
	 */
	public SysRoleModel getRoleByRoleIdAndUuid(String uuid, String roleId);
	
	public String getRoleUuidByRoleIdAndUuid(String uuid, String roleId);
	
	/**
	 * 查询某部门下所有的角色
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelsByDeptUuid(String deptUuid)  ;
	
	public List<String> getSysRoleModelUuidsByDeptUuid(String deptUuid)  ;

	/**
	 * 获得某个管理员已经关联的角色
	 * 
	 * @param managerUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelByManagerUuid(String managerUuid)  ;
	
	public List<String> getSysRoleModelUuidByManagerUuid(String managerUuid)  ;
}