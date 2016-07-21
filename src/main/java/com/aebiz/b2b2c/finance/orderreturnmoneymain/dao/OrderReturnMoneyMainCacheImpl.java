package com.aebiz.b2b2c.finance.orderreturnmoneymain.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainModel;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class OrderReturnMoneyMainCacheImpl
		extends
		BaseMemcachedCache<OrderReturnMoneyMainModel, OrderReturnMoneyMainQueryModel>
		implements OrderReturnMoneyMainDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private OrderReturnMoneyMainDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public OrderReturnMoneyMainCacheImpl() {
		super(FinanceCacheConstant.FINANCE_ORDER_RETURN_MONEY_MAIN);
	}

}
