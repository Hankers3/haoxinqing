package com.aebiz.b2b2c.visitprecept.illnessrecord.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.visitprecept.illnessrecord.service.IllnessRecordService;
import com.aebiz.b2b2c.visitprecept.illnessrecord.dao.IllnessRecordDAO;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordModel;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordQueryModel;

@Service
@Transactional
public class IllnessRecordServiceImpl extends
		BaseServiceImpl<IllnessRecordModel, IllnessRecordQueryModel> implements
		IllnessRecordService {
	private IllnessRecordDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(IllnessRecordDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(IllnessRecordModel m) {
		m.setUuid(us.getNextUuid("IllnessRecord", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(IllnessRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(IllnessRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	// 根据用户的Id查询用户的所有的病情变化
	@Override
	public List<String> getAllNoteByCustomerid(String customerid) {
		return myDao.getAllNoteByCustomerid(customerid);
	}

	/**
	 * 根据随访编号得到病情变化
	 * 
	 * @param visitRecordUuid
	 * @return
	 */
	@Override
	public List<IllnessRecordModel> getByVisitRecordUuid(String visitRecordUuid) {
		return myDao.getByVisitRecordUuid(visitRecordUuid);
	}
}