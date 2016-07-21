package com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.dao.SysDeptMenuRelDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.service.SysDeptMenuRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.service.SysDeptPermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class SysDeptMenuRelServiceImpl extends
		BaseServiceImpl<SysDeptMenuRelModel, SysDeptMenuRelQueryModel>
		implements SysDeptMenuRelService {
	private SysDeptMenuRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	private SysDeptPermitRelService permitRelService;

	@Autowired
	public void setMyDao(SysDeptMenuRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysDeptMenuRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SysDeptMenuRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(SysDeptMenuRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 通过部门编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysDeptMenuRelModel> getAllRelsByDeptUuid(String deptUuid) {
		return myDao.getAllRelsByDeptUuid(deptUuid);
	}

	/**
	 * 保存部门和菜单以及权限的关联关系
	 * 
	 * 1.删除部门关联的所有菜单 <br />
	 * 2.重新创建部门和菜单的关联关系<br />
	 * 3.删除部门关联的所有权限 <br />
	 * 4.重新创建部门和权限的关联关系<br />
	 * 
	 * @param deptUuid
	 * @param menuUuids
	 * @param permitUuids
	 */
	public void saveDeptAndMenuPermitRel(String deptUuid, String[] menuUuids,
			String[] permitUuids) {

		// 1.删除部门关联的所有菜单
		myDao.removeAllRelMenusByDeptUuid(deptUuid);

		if (menuUuids != null) {
			// 2.建立所有的关联关系
			for (String menuUuid : menuUuids) {
				SysDeptMenuRelModel sdmrm = new SysDeptMenuRelModel();
				sdmrm.setMenuUuid(menuUuid);
				sdmrm.setDeptUuid(deptUuid);
				this.create(sdmrm);
			}
		}

		// 3.删除部门关联的所有权限
		permitRelService.removeAllRelPermitsByDeptUuid(deptUuid);

		if (permitUuids != null) {
			// 4.重新创建部门和权限的关联关系
			for (String permitUuid : permitUuids) {
				SysDeptPermitRelModel sdmrm = new SysDeptPermitRelModel();
				sdmrm.setPermitUuid(permitUuid);
				sdmrm.setDeptUuid(deptUuid);
				permitRelService.create(sdmrm);
			}
		}
	}
}