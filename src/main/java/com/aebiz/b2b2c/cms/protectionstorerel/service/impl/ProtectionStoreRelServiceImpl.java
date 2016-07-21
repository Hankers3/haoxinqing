package com.aebiz.b2b2c.cms.protectionstorerel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.cms.protectionstorerel.service.ProtectionStoreRelService;
import com.aebiz.b2b2c.cms.protectionstorerel.dao.ProtectionStoreRelDAO;
import com.aebiz.b2b2c.cms.protectionstorerel.vo.ProtectionStoreRelModel;
import com.aebiz.b2b2c.cms.protectionstorerel.vo.ProtectionStoreRelQueryModel;

@Service
@Transactional
public class ProtectionStoreRelServiceImpl extends BaseServiceImpl<ProtectionStoreRelModel,ProtectionStoreRelQueryModel> implements ProtectionStoreRelService {
	private ProtectionStoreRelDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProtectionStoreRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProtectionStoreRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProtectionStoreRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProtectionStoreRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 保存消费者权益保障服务和商户关联关系
	 * @param checkIds	选择权益的id集合
	 * @param storeId	商户id
	 */
	public void saveProtectionStores(List<String> checkIds, String storeId) {
		for(String protectionUuid : checkIds){
			ProtectionStoreRelModel pm = new ProtectionStoreRelModel();
			pm.setProtectionUuid(protectionUuid);
			pm.setStoreUuid(storeId); 
			this.create(pm);
		}
	}
}