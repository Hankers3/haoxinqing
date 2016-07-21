package com.aebiz.b2b2c.order.ordermaincomment.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentModel;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentQueryModel;

@Repository
public class OrderMainCommentH4Impl extends BaseH4Impl<OrderMainCommentModel,OrderMainCommentQueryModel> implements OrderMainCommentDAO  {

	/**
	 * 根据id修改处理意见
	 * hedongfei
	 * @param 
	 */
	@Override
	public void updateconductIdea(String uuid, String conductIdea) {
		StringBuffer hql = new StringBuffer(
				" update OrderMainCommentModel set conductIdea=:conductIdea , conductState=:conductState where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("conductIdea", conductIdea);
		query.setString("uuid", uuid);
		query.setString("conductState", "1");
		

		query.executeUpdate();
		
	}

	
	/**
	 * 通过主订单编号获取订单评价信息表的服务态度评分
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainCommentServiceScoreByorderMainUuid(
			String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainCommentModel where orderMainUuid=:orderMainUuid");


		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		if (query.list() != null && query.list().size() > 0) {
			OrderMainCommentModel model = (OrderMainCommentModel) query.list().get(0);
			return model.getServiceScore();
		}
		
		return "";
	}
	/**
	 * 通过主订单号来获取订单评价
	 * @param orderMainUuid
	 * @return
	 * @2015-3-27:2015-3-27
	 * @author :SZH
	 */
	@Override
	public OrderMainCommentModel getOrderMainCommentByOrderMainUuid(
			String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainCommentModel where orderMainUuid=:orderMainUuid");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		OrderMainCommentModel orderMainCommentModel= (OrderMainCommentModel) query.list().get(0);
		return null;
	}
	
	
	/**
	 * 根据订单id获取评价列表
	 * @author zdx
	 */
	@Override
	public List<OrderMainCommentModel> getCommentByOrderId(String orderMainUuid) {
		StringBuffer hql = new StringBuffer("from OrderMainCommentModel where orderMainUuid=:orderMainUuid");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		return query.list();
	}
	

}
