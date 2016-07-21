package com.aebiz.b2b2c.basicdata.operatelog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.operatelog.dao.OperateLogDAO;
import com.aebiz.b2b2c.basicdata.operatelog.service.OperateLogService;
import com.aebiz.b2b2c.basicdata.operatelog.vo.OperateLogModel;
import com.aebiz.b2b2c.basicdata.operatelog.vo.OperateLogQueryModel;

@Service
public class OperateLogServiceImpl extends BaseServiceImpl<OperateLogModel,OperateLogQueryModel> implements OperateLogService{
	private OperateLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OperateLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OperateLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		// 设置操作时间
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		// 设置创建时间
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OperateLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OperateLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
}
