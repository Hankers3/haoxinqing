package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.service.SysRoleService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.dao.SysRoleDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleQueryModel;

@Service
@Transactional
public class SysRoleServiceImpl extends
		BaseServiceImpl<SysRoleModel, SysRoleQueryModel> implements
		SysRoleService {
	private SysRoleDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SysRoleDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysRoleModel m) {
		m.setUuid(us.getNextUuid("SysRole", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SysRoleModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		if (StringUtil.isEmpty(m.getCreateTime())) {
			m.setCreateTime(DateFormatHelper.getNowTimeStr());
		}

		super.update(m);
	}

	@Override
	public void delete(SysRoleModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 查询某个用户所拥有的角色
	 * 
	 * 一个用户可以有多个角色，根据用户ID查询此用户关联的角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public Set<String> getRoleIdsByUserId(String userId) {
		return myDao.getRoleIdsByUserId(userId);
	}

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
	public boolean check(String uuid, String roleId) {
		SysRoleModel srm = myDao.getRoleByRoleIdAndUuid(uuid, roleId);
		if (srm == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询某部门下所有的角色
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelsByDeptUuid(String deptUuid) {
		return myDao.getSysRoleModelsByDeptUuid(deptUuid);

	}

	/**
	 * 获得某个管理员已经关联的角色
	 * 
	 * @param managerUuid
	 * @return
	 */
	public List<SysRoleModel> getSysRoleModelByManagerUuid(String managerUuid) {
		return myDao.getSysRoleModelByManagerUuid(managerUuid);
	}
}