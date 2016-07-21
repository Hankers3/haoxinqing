package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.dao.SysDeptPermitRelDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.service.SysDeptPermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class SysDeptPermitRelServiceImpl extends
		BaseServiceImpl<SysDeptPermitRelModel, SysDeptPermitRelQueryModel>
		implements SysDeptPermitRelService {
	private SysDeptPermitRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SysDeptPermitRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysDeptPermitRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SysDeptPermitRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(SysDeptPermitRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 通过部门编号查找已经关联的权限
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptPermitRelModel> getAllPermitRelsByDeptUuid(
			String deptUuid) {
		return myDao.getAllPermitRelsByDeptUuid(deptUuid);
	}

	/**
	 * 删除所有的某个部门和权限的关联关系
	 * 
	 * 在建立部门和权限的关联关系时，先删除，再重新建立
	 * 
	 * @param deptUuid
	 */
	public void removeAllRelPermitsByDeptUuid(String deptUuid) {

		myDao.removeAllRelPermitsByDeptUuid(deptUuid);
	}
}