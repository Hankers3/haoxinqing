package com.aebiz.b2b2c.customermgr.alipay.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.alipay.vo.AlipayModel;
import com.aebiz.b2b2c.customermgr.alipay.vo.AlipayQueryModel;

@Repository
public class AlipayH4Impl extends BaseH4Impl<AlipayModel,AlipayQueryModel> implements AlipayDAO {

}
