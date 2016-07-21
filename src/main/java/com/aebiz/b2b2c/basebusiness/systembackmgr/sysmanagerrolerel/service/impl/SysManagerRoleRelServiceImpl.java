package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.service.SysManagerRoleRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.dao.SysManagerRoleRelDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelQueryModel;

@Service
@Transactional
public class SysManagerRoleRelServiceImpl extends
		BaseServiceImpl<SysManagerRoleRelModel, SysManagerRoleRelQueryModel>
		implements SysManagerRoleRelService {
	private SysManagerRoleRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SysManagerRoleRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysManagerRoleRelModel m) {
		m.setUuid(us.getNextUuid("SYSROLEREL", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SysManagerRoleRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(SysManagerRoleRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 保存管理员和角色的关联关系
	 * 
	 * 
	 * @param smm
	 * @param selectedRoleUuids
	 */
	public void saveRelatedRole(String managerUuid, String[] selectedRoleUuids) {
		// 1.删除之前所有的关联关系
		myDao.removeReledRelsByManagerUuid(managerUuid);

		if (selectedRoleUuids == null || selectedRoleUuids.length <= 0) {
			return;
		}
		// 2.重新建立关联关系
		for (String roleUuid : selectedRoleUuids) {
			SysManagerRoleRelModel smrrm = new SysManagerRoleRelModel();
			smrrm.setRoleUuid(roleUuid);
			smrrm.setManagerUuid(managerUuid);

			this.create(smrrm);
		}

	}

	@Override
	public List<String> getSysManagerRoleRelByRoleUuid(String roleUuid) {
		return myDao.getSysManagerRoleRelByRoleUuid(roleUuid);
	}
}