package com.aebiz.b2b2c.customermgr.consultrecordreply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.consultrecordreply.dao.ConsultRecordReplyDAO;
import com.aebiz.b2b2c.customermgr.consultrecordreply.service.ConsultRecordReplyService;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyModel;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyQueryModel;

@Service
@Transactional
public class ConsultRecordReplyServiceImpl extends BaseServiceImpl<ConsultRecordReplyModel,ConsultRecordReplyQueryModel> implements ConsultRecordReplyService {
	private ConsultRecordReplyDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ConsultRecordReplyDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ConsultRecordReplyModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ConsultRecordReplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ConsultRecordReplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	    /**
         * 
         * @Description: (根据咨询记录的uuid获取回复消息的列表)    
         * @author XP  
         * @param uuid
         * @return
         * @date 2016-1-21 上午11:53:42
         */
        @Override
        public List<ConsultRecordReplyModel> getConsultRecordReplListByUuid(String uuid) {
            return myDao.getConsultRecordReplListByUuid(uuid);
        }
}