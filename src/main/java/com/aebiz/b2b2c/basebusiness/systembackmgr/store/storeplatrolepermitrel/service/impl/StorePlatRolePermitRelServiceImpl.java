package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.dao.StorePlatRolePermitRelDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.service.StorePlatRolePermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class StorePlatRolePermitRelServiceImpl
		extends
		BaseServiceImpl<StorePlatRolePermitRelModel, StorePlatRolePermitRelQueryModel>
		implements StorePlatRolePermitRelService {
	private StorePlatRolePermitRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StorePlatRolePermitRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StorePlatRolePermitRelModel m) {
		m.setUuid(us.getNextUuid("StorePlatRolePermitRel", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(StorePlatRolePermitRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(StorePlatRolePermitRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 获得角色已经关联的权限列表
	 * 
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<StorePlatRolePermitRelModel> getAllPermitRelsByRoleUuid(
			String roleUuid) {

		return myDao.getAllPermitRelsByRoleUuid(roleUuid);

	}

	/**
	 * 删除所有的某个角色和权限的关联关系
	 * 
	 * 在建立角色和权限的关联关系时，先删除，再重新建立
	 * 
	 * @param roleUuid
	 */
	public void removeAllRelPermitsByRoleUuid(String roleUuid) {
		myDao.removeAllRelPermitsByRoleUuid(roleUuid);
	}
}