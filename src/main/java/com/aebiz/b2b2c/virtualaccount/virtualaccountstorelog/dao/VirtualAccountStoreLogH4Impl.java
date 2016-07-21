package com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogQueryModel;

@Repository
public class VirtualAccountStoreLogH4Impl
		extends
		BaseH4Impl<VirtualAccountStoreLogModel, VirtualAccountStoreLogQueryModel>
		implements VirtualAccountStoreLogDAO {

	@Override
	public String getAppendHql(VirtualAccountStoreLogQueryModel qm){
		return "order by o.opeTime desc ";
	}
}
