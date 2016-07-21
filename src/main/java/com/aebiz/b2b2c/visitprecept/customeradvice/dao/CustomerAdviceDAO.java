package com.aebiz.b2b2c.visitprecept.customeradvice.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceModel;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceQueryModel;

public interface CustomerAdviceDAO extends BaseDAO<CustomerAdviceModel,CustomerAdviceQueryModel>{
	/**
	 * 通过uuid将处理意见保存到数据库
	 * @param customerUuid  当前数据的uuid
	 * @param refundConten   处理意见
	 * @return
	 */
	public void toUpdate(String customerUuid, String refundConten) ;

	

}