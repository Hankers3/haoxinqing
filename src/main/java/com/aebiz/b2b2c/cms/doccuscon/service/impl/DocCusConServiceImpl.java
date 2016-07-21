package com.aebiz.b2b2c.cms.doccuscon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.doccuscon.dao.DocCusConDAO;
import com.aebiz.b2b2c.cms.doccuscon.service.DocCusConService;
import com.aebiz.b2b2c.cms.doccuscon.vo.DocCusConModel;
import com.aebiz.b2b2c.cms.doccuscon.vo.DocCusConQueryModel;

@Service
@Transactional
public class DocCusConServiceImpl extends BaseServiceImpl<DocCusConModel,DocCusConQueryModel> implements DocCusConService {
	private DocCusConDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DocCusConDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DocCusConModel m) {
		m.setUuid(us.getNextUuid("DocCusCon",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DocCusConModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DocCusConModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}