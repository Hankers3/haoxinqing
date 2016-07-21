package com.aebiz.b2b2c.finance.invoice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.finance.common.FinanceCacheConstant;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class InvoiceCacheImpl extends
		BaseMemcachedCache<InvoiceModel, InvoiceQueryModel> implements
		InvoiceDAO {
	@Resource(name = FinanceCacheConstant.FINANCE_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private InvoiceDAO myDao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(myDao);
	}

	public InvoiceCacheImpl() {
		super(FinanceCacheConstant.FINANCE_INVOICE);
	}

	/**
	 * 通过订单号，获得订单关联的发票列表
	 * 
	 */
	@Override
	public List<InvoiceModel> getInvoiceListByOrderId(String orderUuid) {
		List<String> uuids = this.myDao.getInvoiceUuidsByOrderId(orderUuid);
		List<InvoiceModel> listM = new ArrayList<InvoiceModel>();
		List<String> noList = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(FinanceCacheConstant.FINANCE_INVOICE
						+ uuid);
				if (obj != null) {
					InvoiceModel m = (InvoiceModel) obj;
					listM.add(m);
				} else {
					noList.add(uuid);
				}
			}
			if (noList.size() > 0) {
				for (String uuid : noList) {
					InvoiceModel m = this.myDao.getByUuid(uuid);
					if (m != null) {
						listM.add(m);
						this.mcc.set(FinanceCacheConstant.FINANCE_INVOICE
								+ uuid, m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public List<String> getInvoiceUuidsByOrderId(String orderMainUuid) {
		return this.myDao.getInvoiceUuidsByOrderId(orderMainUuid);
	}
}
