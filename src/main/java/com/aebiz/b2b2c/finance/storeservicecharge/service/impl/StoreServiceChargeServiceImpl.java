package com.aebiz.b2b2c.finance.storeservicecharge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.storeservicecharge.dao.StoreServiceChargeDAO;
import com.aebiz.b2b2c.finance.storeservicecharge.service.StoreServiceChargeService;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeModel;
import com.aebiz.b2b2c.finance.storeservicecharge.vo.StoreServiceChargeQueryModel;

@Service
@Transactional
public class StoreServiceChargeServiceImpl extends BaseServiceImpl<StoreServiceChargeModel,StoreServiceChargeQueryModel> implements StoreServiceChargeService {
	private StoreServiceChargeDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(StoreServiceChargeDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(StoreServiceChargeModel m) {
		m.setUuid(us.getNextUuid());
		m.setBondUuid(us.getNextUuid("ServiceChg",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		//支付状态为未支付
		m.setPayStatus(StoreServiceChargeModel.UNDER_PAY);
		//支付类型默认为线上支付
		m.setPayType(StoreServiceChargeModel.ONLINE);
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(StoreServiceChargeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(StoreServiceChargeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 添加完合同后生成的商户服务单据,供商户去支付
	 * @param accountUuid 商户uuid
	 * @param contract 合同uuid
	 * @param amount 服务费金额
	 * void
	 */
	public void createServiceChargeByContract(String accountUuid,String contract,float amount){
		StoreServiceChargeModel model = new StoreServiceChargeModel();
		model.setAccountUuid(accountUuid);
		model.setContractUuid(contract);
		model.setPayAmount(amount);
		this.create(model);
	}
	
	/**
	 * 查询当前商户的未支付的服务单据
	 * @param accountUuid
	 * @return 
	 * StoreServiceChargeModel
	 */
	public StoreServiceChargeModel getUnderPayServiceCharge(String accountUuid){
		return myDao.getUnderPayServiceCharge(accountUuid);
	}
	
	/**
	 * 将服务费单据置成功
	 * @param uuid 
	 * void
	 */
	public void updateServiceChargeToSuccess(String uuid){
		StoreServiceChargeModel serviceCharge = this.getByUuid(uuid);
		
		//把操作状态改为成功
		serviceCharge.setPayStatus(StoreServiceChargeModel.PAY_SUCCESS);
		
		this.updateCell(serviceCharge, "payStatus");
	}
}