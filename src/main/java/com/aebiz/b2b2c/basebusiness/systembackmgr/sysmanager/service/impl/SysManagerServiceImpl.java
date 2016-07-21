package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.dao.SysManagerDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;

@Service
@Transactional
public class SysManagerServiceImpl extends
		BaseServiceImpl<SysManagerModel, SysManagerQueryModel> implements
		SysManagerService {
	private SysManagerDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(SysManagerDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SysManagerModel m) {
		m.setUuid(us.getNextUuid("SysManager", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(SysManagerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		if (StringUtil.isEmpty(m.getCreateTime())) {
			m.setCreateTime(DateFormatHelper.getNowTimeStr());
		}
		super.update(m);
	}

	@Override
	public void delete(SysManagerModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	public SysManagerModel getById(String id) {
		SysManagerQueryModel qm = new SysManagerQueryModel();
		qm.setId(id);
		qm.getMapCondition().put("id", ConditionOpTypeEnum.EQ.getCode());

		System.out.println(id + "," + ConditionOpTypeEnum.EQ.getCode());
		List<SysManagerModel> list = this.getByCondition(qm);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 验证管理员登录名是否已经存在
	 * 
	 * @param uuid
	 * @param managerId
	 * @return
	 */
	public boolean check(String uuid, String managerId) {
		SysManagerModel smm = myDao.getManagerByManagerIdAndUuid(uuid,
				managerId);
		if (smm == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 通过编号获取管理员名
	 * 
	 * @param id
	 * @return
	 */
	public String getSysManagerNameByUuid(String uuid) {
		
		return myDao.getSysManagerNameByUuid(uuid);
	}
	/**
         * 通过用户名和密码查询系统管理员
         * 
         * @param id
         * @return
         */
        public SysManagerModel getSysManagerModelByLoginNameAndPassword(String loginName, String pwd) {
            return myDao.getSysManagerModelByLoginNameAndPassword(loginName,pwd);
        }
}