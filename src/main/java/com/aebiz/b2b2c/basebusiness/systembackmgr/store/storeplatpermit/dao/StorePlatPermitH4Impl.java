package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

@Repository
public class StorePlatPermitH4Impl extends
		BaseH4Impl<StorePlatPermitModel, StorePlatPermitQueryModel> implements
		StorePlatPermitDAO {

}
