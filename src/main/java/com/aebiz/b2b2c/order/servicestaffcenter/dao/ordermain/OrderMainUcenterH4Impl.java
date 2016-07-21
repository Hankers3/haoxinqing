package com.aebiz.b2b2c.order.servicestaffcenter.dao.ordermain;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;

@Repository
public class OrderMainUcenterH4Impl extends
		BaseH4Impl<OrderMainModel, OrderMainQueryModel> implements
		OrderMainUcenterDAO {

	protected String getMultiModel() {
		return " , OrderStaffModel as os ";
	}

	/**
	 * 拼接更多的查询条件
	 * 
	 * 1.会员查询的框，输入的值可以同时查询会员编号和会员名称 <br />
	 * 2.商户查询的框，输入的值可以同时查询商户编号和商户名称 <br />
	 * 	 * @param qm
	 * @return
	 */
	protected String getAppendHql(OrderMainQueryModel qm) {

		StringBuffer sb = new StringBuffer();
		
		if(!StringUtil.isEmpty(qm.getState())){
			sb.append(" and o.state = :state ");
		}
		
		if(!StringUtil.isEmpty(qm.getOrderState())){
			sb.append(" and o.state = :state ");
		}
		
		//开始时间
		if(!StringUtil.isEmpty(qm.getCreateStartTime())){
			sb.append(" and o.orderTime >= :createTime ");
		}
		
		//结束时间
		if(!StringUtil.isEmpty(qm.getCreateEndTime())){
			sb.append(" and o.orderTime <= :endTime ");
		}
		
		if (qm != null) {
			return sb.append(
					"order by o." + qm.getSortName() + " " + qm.getSortType())
					.toString();
		}

		return sb.append(" order by o.uuid desc ").toString();
	}

	/**
	 * 拼接更多的查询条件
	 * 
	 * 1.会员查询条件，输入的值可以同时查询会员编号和会员名称 <br />
	 * 2.家政员查询条件，输入的值可以同时查询商户编号和商户名称 <br />
	 * 
	 * @param qm
	 * @return
	 */
	protected void setAppendHqlValue(OrderMainQueryModel qm, Query q) {
		
		//開始時間
		if(!StringUtil.isEmpty(qm.getCreateStartTime())){
			q.setString("createTime",  qm.getCreateStartTime().trim() );
		}
		
		//结束时间
		if(!StringUtil.isEmpty(qm.getCreateEndTime())){
			q.setString("endTime",qm.getCreateEndTime().trim());
		}
		
		//订单状态
		if(!StringUtil.isEmpty(qm.getState())){
			q.setString("state",  qm.getState());
		}
		
		if(!StringUtil.isEmpty(qm.getOrderState())){
			q.setString("state",  qm.getOrderState());
		}
	}

	/**
	 * 会员中心将订单添加到回收站
	 * 
	 * 正常状态的订单delflag=1，回收站delFalg=0,逻辑删除delFlag=2
	 * 
	 * @param orderId
	 */
	public void putOrderToRecycler(String orderId) {
		StringBuffer sb = new StringBuffer(
				"update OrderMainModel set delFlag = :delFlag where uuid=:orderId");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("delFlag", OrderMainModel.DEL_FLAG_RECYCLER);
		q.setString("orderId", orderId);

		q.executeUpdate();
	}

	/**
	 * 会员中心将订单添加到回收站
	 * 
	 * 正常状态的订单delflag=1，回收站delFalg=0,逻辑删除delFlag=2
	 * 
	 * @param orderId
	 */
	public void removeOrderForever(String orderId) {
		StringBuffer sb = new StringBuffer(
				"update OrderMainModel set delFlag = :delFlag where uuid=:orderId");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("delFlag", BaseModel.DEL_FLAG_INVALID);
		q.setString("orderId", orderId);

		q.executeUpdate();
	}

	/**
	 * 会员中心将订单添加到回收站
	 * 
	 * 正常状态的订单delflag=1，回收站delFalg=0,逻辑删除delFlag=2
	 * 
	 * @param orderId
	 */
	public void recovery(String orderId) {
		StringBuffer sb = new StringBuffer(
				"update OrderMainModel set delFlag = :delFlag where uuid=:orderId");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("delFlag", BaseModel.DEL_FLAG_VALID);
		q.setString("orderId", orderId);

		q.executeUpdate();
	}

	/**
	 * 会员中心取消订单，记录取消原因 并，需要记录日志
	 * 
	 * @param orderId
	 * @param cancelReason
	 * @param oper
	 */
	public void cancelOrder(String orderId, String cancelReason) {
		StringBuffer sb = new StringBuffer(
				"update OrderMainModel set state = :state where uuid=:orderId");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("state", OrderStatusEnum.ORDERCANCEL.getValue());
		q.setString("orderId", orderId);

		q.executeUpdate();
	}

	/**
	 * 会员中心订单查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm) {
		String hql = "select count(distinct o.uuid ) from OrderMainModel o "
				+ getMultiModel() + " where 1=1 and o.uuid=os.orderMainUuid";

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		return ((Number) q.uniqueResult()).intValue();
	}

	/**
	 * 会员中心查询订单列表
	 * 
	 * @param paramQM
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List getOrderListByCondition(OrderMainQueryModel qm, int start,
			int pageShow) {
		String hql = "select distinct o.uuid " + getMultiSelect()
				+ " from OrderMainModel o " + getMultiModel() + " where 1=1 and o.uuid=os.orderMainUuid";

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		q.setFirstResult(start);
		q.setMaxResults(pageShow);

		if ((getMultiSelect() != null)
				&& (getMultiSelect().trim().length() > 0)) {
			List tempList = q.list();
			return exeResultList(tempList);
		}

		List retList = q.list();
		return retList;
	}
	
	/**
	 * 根据会员id和订单类型查询总数量
	 * @param OrderMainQueryModel
	 * @param state 订单状态
	 * @return
	 */
	@Override
	public int getOrderCountByCustomerId(OrderMainQueryModel qm,
			String serviceStaffUuid, String state) {
		
		StringBuffer hql = new StringBuffer("select count(distinct o.uuid )from OrderMainModel o ") ;
		hql.append(getMultiModel()).append(" where 1=1 ");
		hql.append(" and o.uuid = os.orderMainUuid ");
		if(!StringUtil.isEmpty(serviceStaffUuid)){
			hql.append(" and os.serviceStaffUuid =:serviceStaffUuid ");
		}
		if(!StringUtil.isEmpty(state)){
			hql.append(" and o.state =:state ");
		}
		if(qm != null){
			hql.append(" and o.mutualState !=:mutualState ");
		}
		Query q = getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(serviceStaffUuid)){
			q.setString("serviceStaffUuid", serviceStaffUuid);
			
		}
		if(!StringUtil.isEmpty(state)){
			q.setString("state", state);
		}
		return ((Number) q.uniqueResult()).intValue();
	}
}
