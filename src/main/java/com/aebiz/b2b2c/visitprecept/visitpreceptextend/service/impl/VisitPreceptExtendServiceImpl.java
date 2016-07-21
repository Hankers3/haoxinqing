package com.aebiz.b2b2c.visitprecept.visitpreceptextend.service.impl;

import java.util.List;

import com.wxcommon.util.IdentityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.dao.VisitPreceptExtendDAO;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.service.VisitPreceptExtendService;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendQueryModel;

@Service
@Transactional
public class VisitPreceptExtendServiceImpl extends BaseServiceImpl<VisitPreceptExtendModel,VisitPreceptExtendQueryModel> implements VisitPreceptExtendService {
	private VisitPreceptExtendDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(VisitPreceptExtendDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VisitPreceptExtendModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(VisitPreceptExtendModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(VisitPreceptExtendModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	  /**
	   * 获取其它方案的列表
	   * @param visitUuid
	   * @return
	   */
        @Override
        public List<VisitPreceptExtendModel> getAllVisitPreceptByPreceptUuid(String visitUuid) {
            return myDao.getAllVisitPreceptByPreceptUuid(visitUuid);
        }
      
	
}