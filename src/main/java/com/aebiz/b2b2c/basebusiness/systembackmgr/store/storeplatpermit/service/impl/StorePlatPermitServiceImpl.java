package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.dao.StorePlatPermitDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.service.StorePlatPermitService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class StorePlatPermitServiceImpl extends
		BaseServiceImpl<StorePlatPermitModel, StorePlatPermitQueryModel>
		implements StorePlatPermitService {
	private StorePlatPermitDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StorePlatPermitDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StorePlatPermitModel m) {
		m.setUuid(us.getNextUuid("StorePlatPermit", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(setParentUuid(m));
		return ret;
	}

	@Override
	public void update(StorePlatPermitModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(setParentUuid(m));
	}

	@Override
	public void delete(StorePlatPermitModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过页面选择的父菜单，设置父菜单
	 * 
	 * 父菜单拼凑规则：一级菜单|二级菜单|三级菜单
	 * 
	 * @param smm
	 * @return
	 */
	private StorePlatPermitModel setParentUuid(StorePlatPermitModel m) {

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