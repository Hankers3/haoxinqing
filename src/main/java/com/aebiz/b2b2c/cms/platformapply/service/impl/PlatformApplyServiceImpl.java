package com.aebiz.b2b2c.cms.platformapply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.platformapply.dao.PlatformApplyDAO;
import com.aebiz.b2b2c.cms.platformapply.service.PlatformApplyService;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyModel;
import com.aebiz.b2b2c.cms.platformapply.vo.PlatformApplyQueryModel;

@Service
@Transactional
public class PlatformApplyServiceImpl extends BaseServiceImpl<PlatformApplyModel,PlatformApplyQueryModel> implements PlatformApplyService {
	private PlatformApplyDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(PlatformApplyDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(PlatformApplyModel m) {
		m.setUuid(us.getNextUuid("PlatformApply",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(PlatformApplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(PlatformApplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过id得到List
	 * @param userId
	 * @param userType
	 * @return
	 */
	@Override
	public List<PlatformApplyModel> getByUserid(String userId, String userType) {
		return myDao.getByUserid(userId,userType);
	}
}