package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.dao.SysDeptDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.SysDeptService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class SysDeptServiceImpl extends
		BaseServiceImpl<SysDeptModel, SysDeptQueryModel> implements
		SysDeptService {
	private SysDeptDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SysDeptDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysDeptModel m) {
		m.setUuid(us.getNextUuid("SysDept", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SysDeptModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(SysDeptModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 检查部门名称是否重复
	 * 
	 * @param uuid
	 * @param deptName
	 * @return
	 */
	public boolean check(String uuid, String deptName) {
		SysDeptModel sdm = myDao.getDeptByDeptIdAndUuid(uuid, deptName);
		if (sdm == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过部门的编号查找部门的名称
	 * 
	 * @param deptUuid
	 * @return
	 */
	public String getDeptNameByDeptUuid(String deptUuid) {
		return myDao.getDeptNameByDeptUuid(deptUuid);
	}
}