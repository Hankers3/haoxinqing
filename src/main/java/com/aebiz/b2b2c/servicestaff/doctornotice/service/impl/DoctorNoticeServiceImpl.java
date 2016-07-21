package com.aebiz.b2b2c.servicestaff.doctornotice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.servicestaff.doctornotice.service.DoctorNoticeService;
import com.aebiz.b2b2c.servicestaff.doctornotice.dao.DoctorNoticeDAO;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeModel;
import com.aebiz.b2b2c.servicestaff.doctornotice.vo.DoctorNoticeQueryModel;

@Service
@Transactional
public class DoctorNoticeServiceImpl extends BaseServiceImpl<DoctorNoticeModel,DoctorNoticeQueryModel> implements DoctorNoticeService {
	private DoctorNoticeDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DoctorNoticeDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 添加  用不到
	 */
	@Override
	public String create(DoctorNoticeModel m) {
		m.setUuid(us.getNextUuid("DoctorNotice",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	/**
	 * 修改  用不到
	 */
	@Override
	public void update(DoctorNoticeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 删除  用不到
	 */
	@Override
	public void delete(DoctorNoticeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	
	/**
	 * 通过uuid得到查看医生公告信息
	 * @param uuid
	 * @return DoctorNoticeModel
	 */
	@Override
	public DoctorNoticeModel getDoctorNoticeModel(String uuid) {
		
		DoctorNoticeModel doctorNoticeModel = myDao.getByUuid(uuid);
		
		return doctorNoticeModel;
	}
	
	//根据医生公告的uuid来删除数据中用户公告的信息
	@Override
	public void deleteById(String uuid) {
		
		myDao.deleteById(uuid);
		
	}
	
	
	//根据医生uuid来获取用户公告的信息
        @Override
        public DoctorNoticeModel getDoctorNoticeModelByDoctorUuid(String doctorId) {
            return myDao.getDoctorNoticeModelByDoctorUuid(doctorId);
        }
        
        /**
         * 获取医生所有的公告
         * @param doctorUuid
         * @return
         */
        @Override
        public List<DoctorNoticeModel> getAllDoctorNoticeList(String doctorUuid) {
            return myDao.getAllDoctorNoticeList(doctorUuid);
        }
}