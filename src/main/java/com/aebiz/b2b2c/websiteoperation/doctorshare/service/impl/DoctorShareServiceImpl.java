package com.aebiz.b2b2c.websiteoperation.doctorshare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.doctorshare.dao.DoctorShareDAO;
import com.aebiz.b2b2c.websiteoperation.doctorshare.service.DoctorShareService;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareModel;
import com.aebiz.b2b2c.websiteoperation.doctorshare.vo.DoctorShareQueryModel;

@Service
@Transactional
public class DoctorShareServiceImpl extends BaseServiceImpl<DoctorShareModel,DoctorShareQueryModel> implements DoctorShareService {
	private DoctorShareDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DoctorShareDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DoctorShareModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DoctorShareModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DoctorShareModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	  /**
	     * 根据医生的id和文章的id获取该文章是否被分享
	     * @param doctorUuid
	     * @param uuid
	     * @return
	     */
        @Override
        public int getShareTypeByDoctorUuidAndContenUuid(String doctorUuid, String contentUuid) {
            return myDao.getShareTypeByDoctorUuidAndContenUuid(doctorUuid,contentUuid);
        }
}