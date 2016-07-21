package com.aebiz.b2b2c.finance.orderreturnmoneymain.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainModel;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainQueryModel;

@Repository
public class OrderReturnMoneyMainH4Impl extends
		BaseH4Impl<OrderReturnMoneyMainModel, OrderReturnMoneyMainQueryModel>
		implements OrderReturnMoneyMainDAO {

}
