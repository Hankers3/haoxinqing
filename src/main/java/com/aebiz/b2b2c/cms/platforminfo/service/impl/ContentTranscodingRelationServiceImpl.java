package com.aebiz.b2b2c.cms.platforminfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.platforminfo.dao.ContentTranscodingRelationDAO;
import com.aebiz.b2b2c.cms.platforminfo.service.ContentTranscodingRelationService;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelation;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelationQueryModel;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;

@Service
@Transactional
public class ContentTranscodingRelationServiceImpl extends BaseServiceImpl<ContentTranscodingRelation, ContentTranscodingRelationQueryModel>
		implements ContentTranscodingRelationService {
	
	private ContentTranscodingRelationDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ContentTranscodingRelationDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}
	
	@Override
	public String create(ContentTranscodingRelation m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ContentTranscodingRelation m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ContentTranscodingRelation m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	@Override
	public ContentTranscodingRelation getBySid(String sid) {
		
		return myDao.getBySid(sid);
	}

	
}
