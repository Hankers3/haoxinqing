package com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.dao.VipclubIntegralLogDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogInteractive;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao.VipclubIntegralStatDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;

/**
 * 会员积分日志service实现类
 * 
 * @author huyingying
 * 
 */
@Service
@Transactional
public class VipclubIntegralLogInteractiveImpl extends
		BaseServiceImpl<VipclubIntegralLogModel, VipclubIntegralLogQueryModel>
		implements VipclubIntegralLogInteractive {
	private VipclubIntegralLogDAO myDao = null;

	@Autowired
	/* 会员积分统计表Dao */
	private VipclubIntegralStatDAO statDAO = null;

	@Autowired
	public void setMyDao(VipclubIntegralLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 下订单增加积分，需要会员uuid，积分数量，订单uuid，商品uuid，描述
	 * 
	 * @param customerUuid
	 * @param integralCount
	 * @param orderMainUuid
	 * @param productUuid
	 * @param descrption
	 */
	public void addIntegralByOrder(String customerUuid,int integralCount,String orderMainUuid,String productUuid,String descrption){
		VipclubIntegralLogModel integralLogModel=new VipclubIntegralLogModel();
		integralLogModel.setIntergralCount(integralCount);
		integralLogModel.setIntergralType(IntegralType.GET_BY_ORDER.getValue());
		integralLogModel.setDescription(descrption);
		integralLogModel.setProductUuid(productUuid);
		integralLogModel.setOrderMainUuid(orderMainUuid);
		myDao.create(integralLogModel);
		VipclubIntegralStatModel usableIntegralStatModel = statDAO
				.getByCustomerUuidAndIntegralType(customerUuid,
						StatIntegralType.USABLE.getValue());
		usableIntegralStatModel.setIntergralCount(usableIntegralStatModel
				.getIntergralCount() + integralCount);
		statDAO.update(usableIntegralStatModel);
		VipclubIntegralStatModel totalIntegralStatModel = statDAO
				.getByCustomerUuidAndIntegralType(customerUuid,
						StatIntegralType.TOTAL.getValue());
		totalIntegralStatModel.setIntergralCount(totalIntegralStatModel
				.getIntergralCount() + integralCount);
		statDAO.update(totalIntegralStatModel);
	}

	/**
	 * 下订单减少积分,需要需要会员uuid，积分数量，订单uuid，商品uuid，描述
	 * 
	 * @param customerUuid
	 * @param integralCount
	 * @param orderMainUuid
	 * @param productUuid
	 * @param descrption
	 */

	public void reduceIntegralByOrder(String customerUuid,int integralCount,String orderMainUuid,String productUuid,String descrption){
		VipclubIntegralLogModel integralLogModel=new VipclubIntegralLogModel();

		integralLogModel.setIntergralCount(integralCount);
		integralLogModel
				.setIntergralType(IntegralType.REDUCE_BY_PAY.getValue());
		integralLogModel.setDescription(descrption);
		integralLogModel.setProductUuid(productUuid);
		integralLogModel.setOrderMainUuid(orderMainUuid);
		myDao.create(integralLogModel);
		VipclubIntegralStatModel usableIntegralStatModel = statDAO
				.getByCustomerUuidAndIntegralType(customerUuid,
						StatIntegralType.USABLE.getValue());
		usableIntegralStatModel.setIntergralCount(usableIntegralStatModel
				.getIntergralCount() - integralCount);
		statDAO.update(usableIntegralStatModel);
	}
}