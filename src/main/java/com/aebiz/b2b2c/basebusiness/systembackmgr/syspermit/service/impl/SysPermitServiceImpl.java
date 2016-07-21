package com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.dao.SysPermitDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.service.SysPermitService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class SysPermitServiceImpl extends
		BaseServiceImpl<SysPermitModel, SysPermitQueryModel> implements
		SysPermitService {
	private SysPermitDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SysPermitDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysPermitModel m) {
		m.setUuid(us.getNextUuid("SysPermit", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(setParentUuid(m));
		return ret;
	}

	@Override
	public void update(SysPermitModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		super.update(setParentUuid(m));
	}

	@Override
	public void delete(SysPermitModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	public Set<String> getPermitExprsByRoleIds(Set<String> roleIds) {
		return myDao.getPermitExprsByRoleIds(roleIds);
	}

	public List<SysPermitModel> getRolesSysPermit(String roleUuid) {
		return myDao.getRolesSysPermit(roleUuid);
	}

	public List<SysPermitModel> getAllOperteSysPermit(String bussinessType) {
		return myDao.getAllOperteSysPermit(bussinessType);
	}

	/**
	 * 查询某个部门所拥有的权限
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysPermitModel> getDeptSysPermits(String deptUuid) {
		return myDao.getDeptSysPermits(deptUuid);
	}

	/**
	 * *************************************************************************
	 * *************************************************************************
	 */

	/**
	 * 通过页面选择的父菜单，设置父菜单
	 * 
	 * 父菜单拼凑规则：一级菜单|二级菜单|三级菜单
	 * 
	 * @param smm
	 * @return
	 */
	private SysPermitModel setParentUuid(SysPermitModel m) {

		if (StringUtil.isEmpty(m.getMenuUuidLevel1())
				&& StringUtil.isEmpty(m.getMenuUuidLevel2())
				&& StringUtil.isEmpty(m.getMenuUuidLevel3())
				&& StringUtil.isEmpty(m.getMenuUuidLevel4())) {
			m.setBelongToMenuUuid("");
		} else {
			if (!StringUtil.isEmpty(m.getMenuUuidLevel4())) {
				m.setBelongToMenuUuid(m.getMenuUuidLevel4());
			} else if (!StringUtil.isEmpty(m.getMenuUuidLevel3())) {
				m.setBelongToMenuUuid(m.getMenuUuidLevel3());
			} else if (!StringUtil.isEmpty(m.getMenuUuidLevel2())) {
				m.setBelongToMenuUuid(m.getMenuUuidLevel2());
			} else if (!StringUtil.isEmpty(m.getMenuUuidLevel1())) {
				m.setBelongToMenuUuid(m.getMenuUuidLevel1());
			}
		}
		return m;
	}
}