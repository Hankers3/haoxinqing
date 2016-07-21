package com.aebiz.b2b2c.servicestaff.staffloginstatus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.dao.StaffLoginStatusDAO;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.service.StaffLoginStatusService;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.vo.StaffLoginStatusQueryModel;


@Service
@Transactional
public class StaffLoginStatusServiceImpl extends BaseServiceImpl<StaffLoginStatusModel,StaffLoginStatusQueryModel> implements StaffLoginStatusService {
	private StaffLoginStatusDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(StaffLoginStatusDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StaffLoginStatusModel m) {
		m.setUuid(us.getNextUuid("StaffLoginStatus",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(StaffLoginStatusModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(StaffLoginStatusModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 判断该家政员是否登录
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public boolean serviceStaffLogin(String serviceStaffUuid) {
		StaffLoginStatusModel staffLoginStatusModel = myDao.getByServiceStaffUuid(serviceStaffUuid);
		if(staffLoginStatusModel != null){
			if("1".equals(staffLoginStatusModel.getStatus())){
				return true;
			}else if("0".equals(staffLoginStatusModel.getStatus())){
				return false;
			}
		}
		return false;
	}

	/**
	 * 通过家政员编号获取该家政员是否登录信息
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public StaffLoginStatusModel getByServiceStaffUuid(String serviceStaffUuid) {
		return myDao.getByServiceStaffUuid(serviceStaffUuid);
	}
}