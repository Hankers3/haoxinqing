package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.dao.StorePlatRoleDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.service.StorePlatRoleService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class StorePlatRoleServiceImpl extends
		BaseServiceImpl<StorePlatRoleModel, StorePlatRoleQueryModel> implements
		StorePlatRoleService {
	private StorePlatRoleDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StorePlatRoleDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StorePlatRoleModel m) {
		m.setUuid(us.getNextUuid("StorePlatRole", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(StorePlatRoleModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(StorePlatRoleModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
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
		StorePlatRoleModel srm = myDao.getRoleByRoleIdAndUuid(uuid, roleId);
		if (srm == null) {
			return true;
		} else {
			return false;
		}
	}
}