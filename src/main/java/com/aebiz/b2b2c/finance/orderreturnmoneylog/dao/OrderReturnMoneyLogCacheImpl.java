package com.aebiz.b2b2c.finance.orderreturnmoneylog.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogModel;
import com.aebiz.b2b2c.finance.orderreturnmoneylog.vo.OrderReturnMoneyLogQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class OrderReturnMoneyLogCacheImpl
		extends
		BaseMemcachedCache<OrderReturnMoneyLogModel, OrderReturnMoneyLogQueryModel>
		implements OrderReturnMoneyLogDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private OrderReturnMoneyLogDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public OrderReturnMoneyLogCacheImpl() {
		super(FinanceCacheConstant.FINANCE_ORDER_RETURN_MONEY_LOG);
	}

}
