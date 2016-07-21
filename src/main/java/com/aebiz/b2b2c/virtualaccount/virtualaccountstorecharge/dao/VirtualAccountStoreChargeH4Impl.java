package com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorecharge.vo.VirtualAccountStoreChargeQueryModel;

@Repository
public class VirtualAccountStoreChargeH4Impl
		extends
		BaseH4Impl<VirtualAccountStoreChargeModel, VirtualAccountStoreChargeQueryModel>
		implements VirtualAccountStoreChargeDAO {
	
		@Override
		public String getAppendHql(VirtualAccountStoreChargeQueryModel qm){
			return "order by o.opeTime desc ";
		}
}
