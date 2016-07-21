package com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service.SysMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.dao.SysRoleMenuRelDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.service.SysRoleMenuRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.service.SysRolePermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class SysRoleMenuRelServiceImpl extends
		BaseServiceImpl<SysRoleMenuRelModel, SysRoleMenuRelQueryModel>
		implements SysRoleMenuRelService {
	private SysRoleMenuRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	private SysRolePermitRelService permitRelService;
	
	@Autowired
	private SysMenuService menuService;

	@Autowired
	public void setMyDao(SysRoleMenuRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysRoleMenuRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SysRoleMenuRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(SysRoleMenuRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param deptUuid
	 * @return
	 */
	public List<SysRoleMenuRelModel> getAllRelsByRoleUuid(String roleUuid) {
		return myDao.getAllRelsByRoleUuid(roleUuid);
	}
	
	/**
	 * 
	 * 通过角色编号查找已经关联的菜单的uuid
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<String> getAllRelMenuUuidsByRoleUuid(String roleUuid){
		return myDao.getAllRelMenuUuidsByRoleUuid(roleUuid);
	}
	
	/**
	 * 
	 * 通过角色编号查找已经关联的菜单
	 * 
	 * @param roleUuid
	 * @return
	 */
	public List<SysMenuModel> getAllRelMenuByRoleUuid(List<String> roleIds){
		List<SysMenuModel> menuModels = new ArrayList<SysMenuModel>();
		if(roleIds != null && roleIds.size() > 0){
			for (String roleUuid : roleIds) {
				List<String> list = this.getAllRelMenuUuidsByRoleUuid(roleUuid);
				menuModels = new ArrayList<SysMenuModel>();
				if(list != null && list.size() > 0){
					for (String uuid : list) {
						SysMenuModel model = menuService.getByUuid(uuid);
						if(model != null){
							menuModels.add(model);
						}
					}
				}
				
			}
		}
		return menuModels;
		
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
				SysRoleMenuRelModel sdmrm = new SysRoleMenuRelModel();
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
				SysRolePermitRelModel sdmrm = new SysRolePermitRelModel();
				sdmrm.setPermitUuid(permitUuid);
				sdmrm.setRoleUuid(roleUuid);
				permitRelService.create(sdmrm);
			}
		}
	}
}