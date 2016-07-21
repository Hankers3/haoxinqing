package com.aebiz.b2b2c.order.orderrevisit.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.orderrevisit.vo.OrderRevisitModel;
import com.aebiz.b2b2c.order.orderrevisit.vo.OrderRevisitQueryModel;

@Repository
public class OrderRevisitH4Impl extends BaseH4Impl<OrderRevisitModel,OrderRevisitQueryModel> implements OrderRevisitDAO {

}
