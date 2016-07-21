package com.aebiz.b2b2c.websiteoperation.customerquiz.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizModel;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizQueryModel;


@Repository
public class CustomerQuizH4Impl extends BaseH4Impl<CustomerQuizModel,CustomerQuizQueryModel> implements CustomerQuizDAO {

}
