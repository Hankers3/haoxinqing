package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.service;

import java.util.List;
import java.util.Set;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

/**
 * 
 * <p>
 * 
 * 系统的角色管理<br />
 * 
 * 角色可以根据部门来划分，因此角色在添加和维护的时候必须有所属的部门 <br />
 * 
 * 部门可以关联菜单和权限，在关联角色的菜单和权限时，只能从本角色所属部门中去挑选菜单和权限进行关联 <br />
 * 
 * </p>
 * 
 * 
 * @author duandeyi
 * 
 */
public interface SysRoleService extends
		BaseService<SysRoleModel, SysRoleQueryModel> {

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
	public boolean check(String uuid, String roleId);

	/**
	 * 查询某部门下所有的角色
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelsByDeptUuid(String deptUuid);

	/**
	 * 获得某个管理员已经关联的角色
	 * 
	 * @param managerUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelByManagerUuid(String managerUuid);
}
