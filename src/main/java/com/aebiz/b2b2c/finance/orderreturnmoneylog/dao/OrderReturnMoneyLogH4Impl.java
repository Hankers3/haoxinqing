package com.aebiz.b2b2c.finance.orderreturnmoneylog.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogModel;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogQueryModel;

@Repository
public class OrderReturnMoneyLogH4Impl extends
		BaseH4Impl<OrderReturnMoneyLogModel, OrderReturnMoneyLogQueryModel>
		implements OrderReturnMoneyLogDAO {

}
