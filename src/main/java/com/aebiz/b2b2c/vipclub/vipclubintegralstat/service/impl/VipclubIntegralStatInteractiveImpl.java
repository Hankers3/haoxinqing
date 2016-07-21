package com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao.VipclubIntegralStatDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatInteractive;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatQueryModel;

/**
 * 会员积分统计表对外接口实现类
 * 
 * @author huyingying
 * 
 */
@Service
@Transactional
public class VipclubIntegralStatInteractiveImpl
		extends
		BaseServiceImpl<VipclubIntegralStatModel, VipclubIntegralStatQueryModel>
		implements VipclubIntegralStatInteractive {

	/* 会员积分统计Dao */

	private VipclubIntegralStatDAO myDao = null;

	@Autowired
	public void setMyDao(VipclubIntegralStatDAO myDao) {
		this.myDao = myDao;
	}
	
	/**
	 * 通过会员uuid获取会员可用积分
	 */
	public int getUsableIntegralBycustomerUuid(String customerUuid) {
		 return myDao.getUsaleIntegralByCustomerUuid(customerUuid);
		
	}

}