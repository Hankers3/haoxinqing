package com.aebiz.b2b2c.cms.consumerprotection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.cms.consumerprotection.dao.ConsumerProtectionDAO;
import com.aebiz.b2b2c.cms.consumerprotection.service.ProtectionInteractive;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;

@Service
@Transactional
public class ProtectionInteractiveImpl implements ProtectionInteractive {
	private ConsumerProtectionDAO myDao = null;
	@Autowired
	public void setMyDao(ConsumerProtectionDAO dao) {
		this.myDao = dao;
	}
	
	/**
	 * 根据商品id获取此商品参与的商家服务
	 * 
	 * @param productUuid
	 * @param storeUuid
	 * @return
	 */
	public List<ConsumerProtectionModel> getByProductUuid(String productUuid,
			String storeUuid){
		return this.myDao.getByProductUuid(productUuid, storeUuid);
	}
}
