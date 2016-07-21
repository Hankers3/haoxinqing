package com.aebiz.b2b2c.finance.storebondcharge.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeModel;
import com.aebiz.b2b2c.finance.storebondcharge.vo.StoreBondChargeQueryModel;

@Repository
public class StoreBondChargeH4Impl
		extends
			BaseH4Impl<StoreBondChargeModel, StoreBondChargeQueryModel>
		implements
			StoreBondChargeDAO {
//	//注入商户账户的service,为了根据商户的名称或者编号获取uuid
//	@Autowired
//	private StoreAccountService accountService;
//	protected String getAppendHql(StoreBondChargeQueryModel qm) {
//		StringBuffer hql = new StringBuffer();
//		if(!StringUtil.isEmpty(qm.getStoreNameOrNo())){
//			
//		}
//		
//		hql.append(super.getAppendHql(qm));
//		
//		return hql.toString();
//	}

}
