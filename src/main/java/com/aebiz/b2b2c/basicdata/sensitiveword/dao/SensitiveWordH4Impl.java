package com.aebiz.b2b2c.basicdata.sensitiveword.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordModel;
import com.aebiz.b2b2c.basicdata.sensitiveword.vo.SensitiveWordQueryModel;

@Repository
public class SensitiveWordH4Impl extends
		BaseH4Impl<SensitiveWordModel, SensitiveWordQueryModel> implements
		SensitiveWordDAO {
}
