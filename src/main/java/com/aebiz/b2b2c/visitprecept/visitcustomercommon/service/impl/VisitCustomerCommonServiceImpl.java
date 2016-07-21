package com.aebiz.b2b2c.visitprecept.visitcustomercommon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.dao.VisitCustomerCommonDAO;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.service.VisitCustomerCommonService;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonModel;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonQueryModel;

@Service
@Transactional
public class VisitCustomerCommonServiceImpl extends BaseServiceImpl<VisitCustomerCommonModel,VisitCustomerCommonQueryModel> implements VisitCustomerCommonService {
	private VisitCustomerCommonDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(VisitCustomerCommonDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VisitCustomerCommonModel m) {
		m.setUuid(us.getNextUuid("VisitCustomerCommon",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(VisitCustomerCommonModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(VisitCustomerCommonModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 通过医生uuid获取医生的常用项列表
	 * @author xp
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<VisitCustomerCommonModel> getCustomerCommonListByDoctorid(
			String doctorid) {
		return myDao.getCustomerCommonListByDoctorid(doctorid);
	}
	
	/**
	 * 通过医生uuid删除医生的常用项列表
	 * @author xp
	 * @param customerCommonUuid
	 * @return
	 */
	@Override
	public void deleteCustomerCommonModelByUuid(String customerCommonUuid) {
		myDao.deleteCustomerCommonModelByUuid(customerCommonUuid);
		
	}
}