package com.aebiz.b2b2c.order.orderdetail.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailQueryModel;

@Repository
public class OrderDetailH4Impl extends
		BaseH4Impl<OrderDetailModel, OrderDetailQueryModel> implements
		OrderDetailDAO {

	/**
	 * 通过订单编号查询订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailModelByOrderId(String orderUuid) {

		StringBuffer sb = new StringBuffer(
				"from OrderDetailModel odm where 1=1");
		sb.append(" and odm.orderMainUuid=:orderMainUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderUuid);

		return q.list();
	}

	/**
	 * 退换货的状态修改后，需要同步更新订单明细的退换货状态进行展示
	 * 
	 * @param detailUuid
	 * @param afterServiceState
	 */
	public void updateOrderDetailState(String detailUuid,
			String afterServiceState) {
		StringBuffer sb = new StringBuffer(
				"update OrderDetailModel odm set state=:state where uuid=:detailUuid");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("state", afterServiceState);
		q.setString("detailUuid", detailUuid);

		q.executeUpdate();
	}

	
	/**统计商品数量
	 * hedongfei
	 * @param 
	 * @param 
	 * @return
	 */
	@Override
	public int getSumProductNumber(String productName,String searchType,String receiveTime) {
		StringBuffer hql = new StringBuffer(
				"SELECT SUM(buyNum)  FROM order_detail AS od,order_main AS om WHERE od.productName=:productName AND od.orderMainUuid=om.uuid ");

		if(searchType.equals("0")){		
			hql.append(" and SUBSTR(om.receiveTime,1,10)=:time");
		}else if(searchType.equals("1")){
			hql.append(" and SUBSTR(om.receiveTime,1,7)=:time");
		}else{
			hql.append(" and SUBSTR(om.receiveTime,1,4)=:time");
		}
		
		Query q = this.getH4Session().createSQLQuery(hql.toString());
		q.setString("productName",productName);
		
		//判断所传时间是否为空
		if(receiveTime.equals("")){
			if(searchType.equals("0")){		
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 10));
			}else if(searchType.equals("1")){
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 7));
			}else{
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		}else{
			q.setString("time",receiveTime);
			}
		
		
		List<Object> result =q.list();
		
		if(result.get(0)!=null && result.size()>0){
			return ((Number) q.uniqueResult()).intValue();
		}else{
			
			return 0;
		}
	}
	
	
	/**
	 * 获取该会员订购该医生的套餐信息
	 * @param customerUuid
	 * @param doctorUuid
	 * @param duration
	 * @return
	 */
	@Override
	public OrderDetailModel getOrderDetailModel(String customerUuid,
			String doctorUuid, String duration) {
		StringBuffer hql = new StringBuffer(" from OrderDetailModel as od ");
		hql.append(" where od.customerUuid =:customerUuid");
		hql.append(" and od.doctorUuid =:doctorUuid");
		hql.append(" and od.duration =:duration");
		hql.append(" and od.phoneTimes > 0");
		hql.append(" order by  od.dueTime desc ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", customerUuid);
		q.setString("doctorUuid", doctorUuid);
		q.setString("duration", duration);
		List list = q.list();
		if(list !=null && list.size()>0){
			return (OrderDetailModel) list.get(0);
		}
		
		return null;
	}

}
