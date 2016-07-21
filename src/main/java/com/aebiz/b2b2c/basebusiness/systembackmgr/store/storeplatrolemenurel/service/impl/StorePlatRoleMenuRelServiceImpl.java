package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.dao.StorePlatRoleMenuRelDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.service.StorePlatRoleMenuRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.service.StorePlatRolePermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class StorePlatRoleMenuRelServiceImpl
		extends
		BaseServiceImpl<StorePlatRoleMenuRelModel, StorePlatRoleMenuRelQueryModel>
		implements StorePlatRoleMenuRelService {
	private StorePlatRoleMenuRelDAO myDao = null;

	@Autowired
	private UuidService us;

	@Autowired
	private StorePlatRolePermitRelService permitRelService;

	@Autowired
	public void setMyDao(StorePlatRoleMenuRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StorePlatRoleMenuRelModel m) {
		m.setUuid(us.getNextUuid("StorePlatRoleMenuRel", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(StorePlatRoleMenuRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(StorePlatRoleMenuRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<StorePlatRoleMenuRelModel> getAllRelsByRoleUuid(String roleUuid) {
		return myDao.getAllRelsByRoleUuid(roleUuid);
	}

	/**
	 * 保存角色和菜单以及权限的关联关系
	 * 
	 * 1.删除角色关联的所有菜单 <br />
	 * 2.重新创建角色和菜单的关联关系<br />
	 * 3.删除角色关联的所有权限 <br />
	 * 4.重新创建角色和权限的关联关系<br />
	 * 
	 * @param deptUuid
	 * @param menuUuids
	 * @param permitUuids
	 */
	public void saveRoleAndMenuPermitRel(String roleUuid, String[] menuUuids,
			String[] permitUuids) {

		// 1.删除部门关联的所有菜单
		myDao.removeAllRelMenusByRoleUuid(roleUuid);

		if (menuUuids != null) {
			// 2.建立所有的关联关系
			for (String menuUuid : menuUuids) {
				StorePlatRoleMenuRelModel sdmrm = new StorePlatRoleMenuRelModel();
				sdmrm.setMenuUuid(menuUuid);
				sdmrm.setRoleUuid(roleUuid);
				this.create(sdmrm);
			}

		}

		// 3.删除部门关联的所有权限
		permitRelService.removeAllRelPermitsByRoleUuid(roleUuid);

		if (permitUuids != null) {
			// 4.重新创建部门和权限的关联关系
			for (String permitUuid : permitUuids) {
				StorePlatRolePermitRelModel sdmrm = new StorePlatRolePermitRelModel();
				sdmrm.setPermitUuid(permitUuid);
				sdmrm.setRoleUuid(roleUuid);
				permitRelService.create(sdmrm);
			}
		}
	}
}