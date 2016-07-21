package com.aebiz.b2b2c.cms.storeback.service.protectionproductrel.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;
import com.aebiz.b2b2c.cms.storeback.dao.protectionproductrel.StoreProtectionProductRelDAO;
import com.aebiz.b2b2c.cms.storeback.service.protectionproductrel.StoreProtectionProductRelService;

@Service
@Transactional
public class StoreProtectionProductRelServiceImpl extends BaseServiceImpl<ProtectionProductRelModel,ProtectionProductRelQueryModel> implements StoreProtectionProductRelService {
	
	private StoreProtectionProductRelDAO myDao = null;
	
	@Autowired
	private UuidService us;
	
	@Autowired
	public void setMyDao(StoreProtectionProductRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	@Override
	public String create(ProtectionProductRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(ProtectionProductRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	@Override
	public void delete(ProtectionProductRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过商户的id和权益id获取所有该用户权益下的商品
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @param start	开始页
	 * @param page	页面大小 
	 * @return	List&ltProtectionProductRelModel&gt
	 */
	public List<ProtectionProductRelModel> getProducts(String storeUuid, String protectionUuid,int start, int page) {
		List<ProtectionProductRelModel> list = myDao.getProducts(storeUuid,protectionUuid,start,page);
		return list;
	}

	/**
	 * 通过商户的id和权益id获取所有该用户权益下的商品
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @return	List&ltProtectionProductRelModel&gt
	 */
	public List<ProtectionProductRelModel> getProducts(String storeUuid, String protectionUuid) {
		return myDao.getProducts(storeUuid, protectionUuid);
	}
	
	/**
	 * 通过用户id，商品id，权益id删除一个商品服务。
	 * @param storeId	商品id
	 * @param productUuid	商品id
	 * @param protectionUuid	权益id
	 */
	public void deleteProtectionPrRel(String storeId, String productUuid,String protectionUuid) {
		myDao.deleteProtectionPrRel(storeId,productUuid,protectionUuid);
	}
}