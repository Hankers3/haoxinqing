package com.aebiz.b2b2c.finance.invoice.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceQueryModel;
import com.aebiz.b2b2c.finance.invoice.vo.SaleType;
import com.aebiz.b2b2c.finance.invoice.vo.SaleTypeInvoice;

@Repository
public class InvoiceH4Impl extends BaseH4Impl<InvoiceModel, InvoiceQueryModel>
		implements InvoiceDAO {

	/**
	 * 根据订单编号获得订单的发票信息
	 * 
	 * 一个订单可能有多个发票信息，因此获得的是一个LIST集合
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<InvoiceModel> getInvoiceListByOrderId(String orderMainUuid) {
		StringBuffer sb = new StringBuffer("from InvoiceModel im where 1=1");
		sb.append(" and im.orderMainUuid=:orderMainUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderMainUuid);

		return q.list();
	}
	
	/**
	 * 拼接根据交易类型查询发票记录的hql语句
	 */
	@Override
	protected String getAppendHql(InvoiceQueryModel qm){
		StringBuffer hql=new StringBuffer("");

		//判断qm不为空
		if(qm!=null){

			//判断qm需要查询的发票不为空
			if(!StringUtil.isEmpty(qm.getNeedInvoice()) ){

				//拼接查询交易类型为账单结算，商户订单，续缴服务费的发票记录
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.BILL_ORDER_SERVICEFEE.getValue())){
					hql.append(" and o.saleType in ( :bill_settle, :store_order,:pay_servicefee)");
				}

				//拼接查询交易类型为账单结算，商户订单的发票记录
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.BILL_ORDER.getValue())){
					hql.append(" and o.saleType in ( :bill_settle, :store_order)");
				}

				//拼接查询交易类型为账单结算，礼品卡购买，续缴服务费的发票记录
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.BILL_GIFT_SERVICEFEE.getValue())){
					hql.append(" and o.saleType in ( :bill_settle, :gift_buy,:pay_servicefee)");
				}

				//拼接查询交易类型为续缴服务费的发票记录
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.SERVICEFEE.getValue())){
					hql.append(" and o.saleType in ( :pay_servicefee)");
				}
				if(!StringUtil.isEmpty(qm.getSortName()) && !StringUtil.isEmpty(qm.getSortName())){
					return hql.append(" order by o." + qm.getSortName() + " " + qm.getSortType()).toString();
				}
			}
		}	
		return hql.append(" order by o.opeTime desc ").toString(); 

	}
	
	/**
	 * 设置根据交易类型查询发票记录的值
	 */
	@Override
	protected void setAppendHqlValue(InvoiceQueryModel qm, Query q){
		
		//判断qm不为空
		if(qm!=null){
			
			//判断qm需要查询的发票不为空
			if(!StringUtil.isEmpty(qm.getNeedInvoice()) ){
				
				//设置交易类型为账单结算，商户订单，续缴服务费的交易类型值
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.BILL_ORDER_SERVICEFEE.getValue())){
					//System.out.println(qm.getNeedInvoice());
					q.setString("bill_settle", SaleType.SALE_TYPE_BILL_SETTLE.getName());
					q.setString("store_order", SaleType.SALE_TYPE_STORE_ORDER.getName());
					q.setString("pay_servicefee", SaleType.SALE_TYPE_PAY_SERVICEFEE.getName());
				}
				
				//设置交易类型为账单结算，商户订单的交易类型值
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.BILL_ORDER.getValue())){
					q.setString("bill_settle", SaleType.SALE_TYPE_BILL_SETTLE.getName());
					q.setString("store_order", SaleType.SALE_TYPE_STORE_ORDER.getName());
				}
				
				//设置交易类型为账单结算，礼品卡购买，续缴服务费的交易类型值
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.BILL_GIFT_SERVICEFEE.getValue())){
					q.setString("bill_settle", SaleType.SALE_TYPE_BILL_SETTLE.getName());
					q.setString("gift_buy", SaleType.SALE_TYPE_GIFTCARD_BUY.getName());
					q.setString("pay_servicefee", SaleType.SALE_TYPE_PAY_SERVICEFEE.getName());
				}
				
				//设置交易类型为续缴服务费的交易类型值
				if(qm.getNeedInvoice().equals(SaleTypeInvoice.SERVICEFEE.getValue())){
					q.setString("pay_servicefee", SaleType.SALE_TYPE_PAY_SERVICEFEE.getName());
				}
			}
		}
		
	}
	
	
	   public List<String> getInvoiceUuidsByOrderId(String orderMainUuid) {
                StringBuffer sb = new StringBuffer(
                                "select im.uuid from InvoiceModel im where 1=1");
                sb.append(" and im.orderMainUuid=:orderMainUuid");
    
                Query q = this.getH4Session().createQuery(sb.toString());
                q.setString("orderMainUuid", orderMainUuid);
                
                List<String> list = q.list();
                     if(list != null && list.size() > 0){
                             return list;
                     } 
                 
                return null;
        }
}
