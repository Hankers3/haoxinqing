package com.aebiz.b2b2c.order.ordermain.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.order.ordermain.vo.OrderConst;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.atomikos.util.DateHelper;

@Repository
public class OrderMainH4Impl extends BaseH4Impl<OrderMainModel, OrderMainQueryModel> implements OrderMainDAO {
	@Autowired
	private ServicestaffService staffService;

	/**
	 * 订单查询搜素 修改 xueLi 20150420
	 * 
	 * @param qm
	 * @return
	 */
	@Override
	protected String getMultiModel() {
		return ",ServicestaffModel as s, CustomerModel as c ";
	}

	/**
	 * 拼接返回对象
	 */
	@Override
	protected String getMultiSelect() {

		return " ,s ";
	}

	/**
	 * 拼接更多的查询条件
	 * 
	 * 1.查询的框，输入的值可以同时查询会员编号和会员名称 <br />
	 * 
	 * @param qm
	 * @return
	 */
	protected String getAppendHql(OrderMainQueryModel qm) {
		StringBuffer sb = new StringBuffer();
		sb.append("and o.doctorUuid = s.uuid and o.customerUuid=c.uuid ");

		// 订单类型 1:电话 2：套餐
		if (!StringUtil.isEmpty(qm.getSerachtype())) {
			sb.append(" and o.orderType =:orderType ");
		}

		// 手机号码
		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			sb.append(" and c.mobile like:customerMobile ");
		}

		/* 患者名 */
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			sb.append(" and o.customerName like:customerName ");
		}

		/* 医生电话 */
		if (!StringUtil.isEmpty(qm.getServiceStaffMobile())) {
			sb.append(" and s.mobile =:doctorMobile ");
		}

		// 医生名
		if (!StringUtil.isEmpty(qm.getServiceStaffName())) {
			sb.append(" and s.realName like:realName ");
		}

		// 订单状态
		if (!StringUtil.isEmpty(qm.getOrderState())) {
			sb.append(" and o.state =:state ");
		}

		// 审核类型
		if (!StringUtil.isEmpty(qm.getCheckType())) {
			sb.append(" and o.checkType =:checkType ");
		}

		// 电话订单的开始时间
		if (!StringUtil.isEmpty(qm.getBeginTime())) {
			sb.append(" and substr(o.bookTime,1,10) >=:beginTime ");
		}

		// 电话订单的结束时间
		if (!StringUtil.isEmpty(qm.getEndTime())) {
			sb.append(" and substr(o.bookTime,1,10) <:endTime ");
		}

		// 查询今天的订单信息
		if (!StringUtil.isEmpty(qm.getOrderTime())) {
			sb.append(" and substr(o.bookTime,1,10) =:orderTimeq ");
		}
		return sb.append(" order by o.orderTime desc ").toString();

	}

	/**
	 * 拼接更多的查询条件
	 * 
	 * 1.查询条件，输入的值可以同时查询会员编号和会员名称 <br />
	 * 
	 * @param qm
	 * @return
	 */
	protected void setAppendHqlValue(OrderMainQueryModel qm, Query q) {

		// 订单类型 1:电话 2：套餐
		if (!StringUtil.isEmpty(qm.getSerachtype())) {
			q.setString("orderType", qm.getSerachtype());
		}

		// 手机号码
		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			q.setString("customerMobile", "%" + (qm.getCustomerMobile()).trim() + "%");
		}

		/* 患者名 */
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + (qm.getCustomerName()).trim() + "%");
		}

		// 医生手机号
		if (!StringUtil.isEmpty(qm.getServiceStaffMobile())) {
			q.setString("doctorMobile", "%" + (qm.getServiceStaffMobile()).trim() + "%");
		}

		// 医生名
		if (!StringUtil.isEmpty(qm.getServiceStaffName())) {
			q.setString("realName", "%" + (qm.getServiceStaffName()).trim() + "%");
		}

		// 订单状态
		if (!StringUtil.isEmpty(qm.getOrderState())) {
			q.setString("state", qm.getOrderState().trim());
		}

		// 审核类型
		if (!StringUtil.isEmpty(qm.getCheckType())) {
			q.setString("checkType", qm.getCheckType().trim());
		}
		// 电话订单的开始时间
		if (!StringUtil.isEmpty(qm.getBeginTime())) {
			q.setString("beginTime", qm.getBeginTime().trim());
		}
		// 电话订单的结束时间
		if (!StringUtil.isEmpty(qm.getEndTime())) {
			q.setString("endTime", qm.getEndTime().trim());
		}

		// 查询今天的订单信息
		if (!StringUtil.isEmpty(qm.getOrderTime())) {
			q.setString("orderTimeq", qm.getOrderTime().trim());
		}
	}

	@Override
	public List<OrderMainModel> getByCondition(OrderMainQueryModel qm, int start, int pageShow) {

		return getByCondition(false, true, qm, start, pageShow);
	}

	/**
	 * 订单查询
	 * 
	 * @param onlyUuids
	 * @param needPage
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 */
	private List getByCondition(boolean onlyUuids, boolean needPage, OrderMainQueryModel qm, int start, int pageShow) {
		String hql = "select o" + getMultiSelect() + " from  OrderMainModel  o " + getMultiModel() + " where 1=1 ";
		if (onlyUuids) {
			hql = "select o.uuid from OrderMainModel  o " + getMultiModel() + " where 1=1 ";
		}

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		if (needPage) {
			q.setFirstResult(start);
			q.setMaxResults(pageShow);
		}

		if (onlyUuids) {
			return q.list();
		}

		if ((getMultiSelect() != null) && (getMultiSelect().trim().length() > 0)) {
			List<Object[]> tempList = q.list();
			return exeResultList(tempList);
		}
		List<OrderMainModel> retList = q.list();
		return retList;
	}

	/**
	 * 封装返回的数据
	 */
	@Override
	protected List<OrderMainModel> exeResultList(List<Object[]> tempList) {
		List<OrderMainModel> list = new ArrayList<OrderMainModel>();
		if (tempList != null && tempList.size() > 0) {
			for (int i = 0; i < tempList.size(); i++) {
				Object[] obj = tempList.get(i);
				OrderMainModel order = (OrderMainModel) obj[0];
				ServicestaffModel staff = (ServicestaffModel) obj[1];
				order.setDoctorName(staff.getRealName());
				order.setHospitalName(staff.getHospitalName());
				list.add(order);
			}
		}
		return list;
	}

	/**
	 * 订单查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm) {
		String hql = "select count(distinct o.uuid ) from OrderMainModel o " + getMultiModel() + " where 1=1 ";

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		return ((Number) q.uniqueResult()).intValue();
	}

	/**
	 * 根据用户id来获取其订单列表
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<String> getOrderListByCustomerUuid(String customerUuid, String type, int pageCount, int pageNo) {
		// 当前时间
		// String serviceTime = DateFormatHelper.getNowTimeStr();
		StringBuffer sb = new StringBuffer(
				"select omm.uuid from OrderMainModel as omm,OrderMainAddressModel as omam where 1=1 and omm.uuid=omam.orderMainUuid ");
		sb.append(" and omm.customerUuid=:customerUuid ");

		if ("1".equals(type)) {
			sb.append(" and (omm.state =:state1 or omm.state =:state2 or omm.state =:state3 ) ");
		} else if ("0".equals(type)) {
			sb.append(" and (omm.state !=:state1 and omm.state !=:state2 and omm.state !=:state3 ) ");
			// sb.append(" and omam.serviceTime > :serviceTime ");
		}
		sb.append(" order by omm.orderTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("customerUuid", customerUuid);
		q.setString("state1", "2");
		q.setString("state2", "11");
		q.setString("state3", "12");
		// if("0".equals(type)){
		// //当前时间
		// q.setString("serviceTime", serviceTime);
		// }
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		List<String> list = new ArrayList();
		list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return new ArrayList();
		}
	}

	/**
	 * 根据订单id获取订单
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderMainModel getOrderMainModelByOrderId(String orderId) {
		StringBuffer sb = new StringBuffer("from OrderMainModel omm where 1=1");
		sb.append(" and omm.orderId=:orderId ");
		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderId", orderId);
		List list = new ArrayList<OrderMainModel>();
		list = q.list();
		if (list != null && list.size() > 0) {
			return (OrderMainModel) list.get(0);
		} else {
			return new OrderMainModel();
		}
	}

	/**
	 * 通过主订单编号获取客户姓名 hedongfei
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMaincustomerName(String uuid) {
		StringBuffer hql = new StringBuffer("from OrderMainModel where uuid=:uuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		if (query.list() != null && query.list().size() > 0) {
			OrderMainModel model = (OrderMainModel) query.list().get(0);
			return model.getCustomerName();
		}

		return "";
	}

	/**
	 * 通过订单编号获得这个主订单对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	@Override
	public OrderMainModel getOrderMainByUuid(String orderUuid) {
		StringBuffer hql = new StringBuffer("from OrderMainModel where uuid=:uuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", orderUuid);

		List<OrderMainModel> omamList = query.list();
		if (omamList != null && omamList.size() > 0) {
			return omamList.get(0);
		}

		return null;
	}

	/**
	 * 根据id修改投诉状态(修改为1) 注：投诉状态默认为空 hedongfei
	 * 
	 * @param
	 */
	@Override
	public void updateOrderComplain(String uuid, String updateStype) {
		StringBuffer hql = new StringBuffer(
				" update OrderMainModel set  orderComplain=:orderComplain where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		if (updateStype.equals("0")) {
			query.setString("orderComplain", "0");
		} else if (updateStype.equals("1")) {
			query.setString("orderComplain", "1");
		} else {
			query.setString("orderComplain", "2");
		}

		query.executeUpdate();

	}

	@Override
	public boolean isUrgencyOrder() {
		StringBuffer hql = new StringBuffer(" select o.uuid from OrderMainModel as o where 1=1");
		hql.append(" and o.urgencyStatus =:urgencyStatus");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("urgencyStatus", "1");

		List list = q.list();
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 按照分账日期查询对应家政员所有的订单
	 */
	@Override
	public List<OrderMainModel> getOrderListByServiceStaffUuid(String serviceStaffUuid, String routTime) {
		// 当前时间
		String serviceTime = DateFormatHelper.getNowTimeStr(DateFormatHelper.FORMAT_DATE_STR);
		if (StringUtil.isEmpty(routTime)) {
			routTime = serviceTime;
		}
		List<OrderMainModel> orderList = new ArrayList<OrderMainModel>();
		StringBuffer sb = new StringBuffer("select o.orderMainUuid from OrderRoutingModel o where 1=1 ");
		sb.append(" and o.serviceStaffUuid =:serviceStaffUuid ");
		sb.append(" and o.routTime like:routTime ");
		sb.append(" order by o.routTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("serviceStaffUuid", serviceStaffUuid);
		q.setString("routTime", routTime + "%");
		List<String> list = q.list();
		if (list != null && list.size() > 0) {
			StringBuffer buff = new StringBuffer(" from OrderMainModel om where 1=1 ");
			buff.append(" and om.uuid =:uuid");
			Query query = getH4Session().createQuery(buff.toString());
			for (int i = 0; i < list.size(); i++) {
				String orderId = list.get(i);
				query.setString("uuid", orderId);
				List<OrderMainModel> orderMainList = query.list();
				if (orderMainList != null && orderMainList.size() > 0) {
					OrderMainModel om = (OrderMainModel) query.list().get(0);
					orderList.add(om);
				}
			}
		}
		return orderList;
	}

	/**
	 * 根据家政员id和分账时间获取相关分账数据总额
	 */
	@Override
	public double getTotalMoneyByStaffId(String strName, String serviceStaffUuid, String routTime) {
		// 当前时间
		String serviceTime = DateFormatHelper.getNowTimeStr(DateFormatHelper.FORMAT_DATE_STR);
		if (StringUtil.isEmpty(routTime)) {
			routTime = serviceTime;
		}
		List<OrderMainModel> orderList = new ArrayList<OrderMainModel>();
		StringBuffer sb = new StringBuffer("select o.orderMainUuid from OrderRoutingModel o where 1=1 ");
		sb.append(" and o.serviceStaffUuid =:serviceStaffUuid ");
		sb.append(" and o.routTime like:routTime ");
		sb.append(" order by o.routTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("serviceStaffUuid", serviceStaffUuid);
		q.setString("routTime", routTime + "%");
		List<String> list = q.list();
		double totalMoney = 0;
		if (list != null && list.size() > 0) {
			StringBuffer buff = new StringBuffer(" select sum(om." + strName + ") from OrderMainModel om where 1=1 ");
			buff.append(" and om.uuid in (:uuid)");
			Query query = getH4Session().createQuery(buff.toString());
			query.setParameterList("uuid", list.toArray());
			if (query.uniqueResult() != null) {
				totalMoney = ((Number) query.uniqueResult()).doubleValue();
			}
		}
		return totalMoney;
	}

	/**
	 * 得到订单成功单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@Override
	public int getCountOrdersaboutSuccess(String receiveTime, String searchType) {
		StringBuffer hql = new StringBuffer("SELECT count(*)  FROM order_main  where state='11' ");
		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(receiveTime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(receiveTime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(receiveTime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());
		// q.setString("ServiceStaffUuid",ServiceStaffUuid);

		// 判断所传时间是否为空
		if (receiveTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", receiveTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Number) q.uniqueResult()).intValue();
		} else {

			return 0;
		}
	}

	/**
	 * 统计订单每个月份收入管理
	 */
	@Override
	public List<Object> getOrderListByReceiveTime(String receiveTime, int pageCount, int pageNo) {

		StringBuffer sb = new StringBuffer(
				" select substr(o.receiveTime,1,10) ,sum(o.totalMoney),sum(o.integralMoney),sum(o.couponMoney),sum(o.freeMoney),sum(o.paid),sum(giftCardMoney),sum(balanceMoney) from order_main o where o.receiveTime like:receiveTime and o.state =:state");
		sb.append(" group by substr(o.receiveTime,1,10)");
		sb.append(" order by substr(o.receiveTime,1,10) desc");
		Query query = getH4Session().createSQLQuery(sb.toString());

		// 已经完成的订单
		query.setString("state", OrderConst.ORDERSTATUS_QRWC);
		// 完成时间
		query.setString("receiveTime", receiveTime + "%");
		List<Object> list = query.list();
		return list;
	}

	/**
	 * 查询某一个月所有已完成的订单
	 */
	@Override
	public int getCountOrderListByReceiveTime(String receiveTime) {
		StringBuffer sb = new StringBuffer(
				" select count(distinct o.uuid ) from OrderMainModel o where o.receiveTime like:receiveTime and o.state =:state");
		sb.append(" group by substr(o.receiveTime,1,10)");
		Query query = getH4Session().createQuery(sb.toString());
		// 已完成的订单状态
		query.setString("state", OrderConst.ORDERSTATUS_QRWC);
		// 完成时间
		query.setString("receiveTime", receiveTime + "%");
		List<Object> list = query.list();
		return list.size();
	}

	/**
	 * 根据确认时间查询每天的支付宝支付或者微信支付的金额总和
	 */
	@Override
	public double getPayMoneyByReceiveTime(String receiveTime, String payType) {

		StringBuffer sb = new StringBuffer(
				" select sum(om.payMoney) from OrderMainPayModel om,OrderMainModel o where o.uuid=om.orderMainUuid ");
		sb.append(" and o.receiveTime like:receiveTime and om.payType =:payType");
		Query query = getH4Session().createQuery(sb.toString());
		query.setString("receiveTime", receiveTime + "%");
		query.setString("payType", payType);
		List list = query.list();
		if (list != null && list.size() > 0) {
			if (list.get(0) != null) {
				Number count = (Number) list.get(0);
				return count.doubleValue();
			}
		}
		return 0;
	}

	/**
	 * 获取某一段时间的已完成订单收入金额总和
	 */
	@Override
	public double getPaidMoneyByReceiveTime(String receiveTime) {
		StringBuffer sb = new StringBuffer(
				" select sum(o.paid) from OrderMainModel o where o.receiveTime like:receiveTime and o.state =:state");
		Query query = getH4Session().createQuery(sb.toString());
		query.setString("receiveTime", receiveTime + "%");
		// 已完成的订单状态
		query.setString("state", OrderConst.ORDERSTATUS_QRWC);
		List list = query.list();
		if (list != null && list.size() > 0) {
			if (list.get(0) != null) {
				Number count = (Number) list.get(0);
				return count.doubleValue();
			}
		}
		return 0;
	}

	/**
	 * 得到今日上门订单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@Override
	public int getCountOrdersaboutdoorin(String doorinEndTime, String searchType) {
		StringBuffer hql = new StringBuffer(
				"SELECT count(*)  FROM order_main as o ,order_main_address as omd  where omd.orderMainUuid=o.Uuid ");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());

		// 判断所传时间是否为空
		if (doorinEndTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}

		} else {
			q.setString("time", doorinEndTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Number) q.uniqueResult()).intValue();
		} else {

			return 0;
		}
	}

	/**
	 * 得到今日取消订单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@Override
	public int getCountOrdersaboutclose(String cancelOrderTime, String searchType) {
		StringBuffer hql = new StringBuffer("SELECT count(*)  FROM order_main  where state='2' ");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(cancelOrderTime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(cancelOrderTime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(cancelOrderTime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());
		// q.setString("ServiceStaffUuid",ServiceStaffUuid);

		// 判断所传时间是否为空
		if (cancelOrderTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", cancelOrderTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Number) q.uniqueResult()).intValue();
		} else {

			return 0;
		}
	}

	/**
	 * 得到今日申请退款订单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@Override
	public int getCountOrdersaboutRefund(String refundApplyTime, String searchType) {
		StringBuffer hql = new StringBuffer("SELECT count(*)  FROM order_main  where refoundState IS NOT NULL ");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(refundApplyTime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(refundApplyTime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(refundApplyTime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());
		// q.setString("ServiceStaffUuid",ServiceStaffUuid);
		// 判断所传时间是否为空
		if (refundApplyTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", refundApplyTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Number) q.uniqueResult()).intValue();
		} else {

			return 0;
		}
	}

	/**
	 * 得到今日上门人次 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@Override
	public int getCountOrdersaboutdoorinpeople(String doorinEndTime, String searchType) {
		StringBuffer hql = new StringBuffer(
				"SELECT COUNT(*)  FROM order_main_address AS omd,order_staff AS os  WHERE  omd.orderMainUuid=os.orderMainUuid");

		// hql.append(" and SUBSTR(omd.doorinEndtime,1,10)=:time");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());
		// q.setString("ServiceStaffUuid",ServiceStaffUuid);
		// 判断所传时间是否为空
		if (doorinEndTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", doorinEndTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Number) q.uniqueResult()).intValue();
		} else {

			return 0;
		}
	}

	/**
	 * 获取订单总额,积分使用金额,优惠券金额,-折扣金额,实际收入,支付宝支付,微信支付,其他支付金额 hedongfei 注：time指完成时间
	 * 
	 * @param
	 * @return
	 */
	@Override
	public Object[] getMangKindsOfMoney(String receiveTime, String searchType) {
		StringBuffer sb = new StringBuffer(
				" select sum(o.totalMoney),sum(o.integralMoney),sum(o.couponMoney),sum(o.freeMoney),sum(o.paid) from order_main o where  o.state =:state");

		// sb.append(" and SUBSTR(o.receiveTime,1,10)=:time");
		if (searchType.equals("0")) {
			sb.append(" and SUBSTR(o.receiveTime,1,10)=:time");
		} else if (searchType.equals("1")) {
			sb.append(" and SUBSTR(o.receiveTime,1,7)=:time");
		} else {
			sb.append(" and SUBSTR(o.receiveTime,1,4)=:time");
		}

		Query query = getH4Session().createSQLQuery(sb.toString());

		// 已经完成的订单
		query.setString("state", "11");

		// 判断所传时间是否为空
		if (receiveTime.equals("")) {
			if (searchType.equals("0")) {
				query.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				query.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				query.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			query.setString("time", receiveTime);
		}

		List<Object> result = query.list();

		Object[] o = null;

		if (result.get(0) != null && result.size() > 0) {

			o = (Object[]) result.get(0);
			for (int i = 0; i < o.length; i++) {
				if (o[i] != null) {
					if (o[i] instanceof BigDecimal)
						o[i] = ((BigDecimal) o[i]).doubleValue();
					else if (o[i] instanceof Character)
						o[i] = ((Character) o[i]).toString();
					else if (o[i] instanceof BigInteger)
						o[i] = ((BigInteger) o[i]).longValue();
				} else {
					double a = 0;
					o[i] = a;
				}
			}
		}

		return o;
	}

	/**
	 * 按时间得到规定时间内的成功订单的uuid hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@Override
	public List getuuidsOrdersaboutSuccess(String receiveTime, String searchType) {
		StringBuffer hql = new StringBuffer("SELECT uuid FROM order_main  where state='11' ");

		// hql.append(" and SUBSTR(receiveTime,1,10)=:time");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(receiveTime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(receiveTime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(receiveTime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());

		// 判断所传时间是否为空
		if (receiveTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", receiveTime);
		}

		List<Object> result = q.list();

		if (result != null && result.size() > 0) {
			return result;
		} else {
			return null;
		}

	}

	/**
	 * 获得紧急需要处理的订单数 hedongfei
	 * 
	 * @return
	 */
	@Override
	public int urgencyOrders() {
		StringBuffer hql = new StringBuffer(" select COUNT(*) from order_main as o where 1=1");
		hql.append(" and o.urgencyStatus =:urgencyStatus");

		Query q = this.getH4Session().createSQLQuery(hql.toString());
		q.setString("urgencyStatus", "1");

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Number) q.uniqueResult()).intValue();
		} else {

			return 0;
		}
	}

	/**
	 * 获取某一段时间的上门订单的总额 hedongfei
	 * 
	 * @param doorinEndTime
	 * @return
	 */
	@Override
	public double getPaidMoneyBydoorinEndTime(String doorinEndTime, String searchType) {
		StringBuffer hql = new StringBuffer(
				"SELECT sum(o.totalMoney)  FROM order_main as o ,order_main_address as omd   where   o.uuid=omd.orderMainUuid  ");

		// hql.append(" and SUBSTR(omd.doorinEndtime,1,10)=:time");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(omd.doorinEndtime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());

		// 判断所传时间是否为空
		if (doorinEndTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", doorinEndTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Double) q.uniqueResult()).doubleValue();
		} else {

			return 0;
		}
	}

	/**
	 * 获取某一时间内申请退款金额 hedongfei
	 * 
	 * @param refundApplyTime
	 * @return
	 */
	@Override
	public double getPaidMoneyByapply(String refundApplyTime, String searchType) {
		StringBuffer hql = new StringBuffer("SELECT sum(o.refundMoney)  FROM order_main as o where 1=1  ");
		// hql.append(" and SUBSTR(o.refundApplyTime,1,10)=:time");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(o.refundApplyTime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(o.refundApplyTime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(o.refundApplyTime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());
		// q.setString("ServiceStaffUuid",ServiceStaffUuid);

		// 判断所传时间是否为空
		if (refundApplyTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", refundApplyTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Double) q.uniqueResult()).doubleValue();
		} else {

			return 0;
		}
	}

	/**
	 * 获取某一时间内取消订单金额 hedongfei
	 * 
	 * @param refundApplyTime
	 * @return
	 */
	@Override
	public double getPaidMoneyBycancel(String cancelOrderTime, String searchType) {
		StringBuffer hql = new StringBuffer("SELECT sum(o.totalMoney)  FROM order_main as o where  o.state='2' ");
		// hql.append(" and SUBSTR(o.cancelOrderTime,1,10)=:time");

		if (searchType.equals("0")) {
			hql.append(" and SUBSTR(o.cancelOrderTime,1,10)=:time");
		} else if (searchType.equals("1")) {
			hql.append(" and SUBSTR(o.cancelOrderTime,1,7)=:time");
		} else {
			hql.append(" and SUBSTR(o.cancelOrderTime,1,4)=:time");
		}

		Query q = this.getH4Session().createSQLQuery(hql.toString());
		// q.setString("ServiceStaffUuid",ServiceStaffUuid);

		// 判断所传时间是否为空
		if (cancelOrderTime.equals("")) {
			if (searchType.equals("0")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 10));
			} else if (searchType.equals("1")) {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 7));
			} else {
				q.setString("time", DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		} else {
			q.setString("time", cancelOrderTime);
		}

		List<Object> result = q.list();

		if (result.get(0) != null && result.size() > 0) {
			return ((Double) q.uniqueResult()).doubleValue();
		} else {

			return 0;
		}
	}

	/**
	 * 根据订单uuid获取id
	 */
	@Override
	public String getOrderIdByUuid(String uuid) {
		StringBuffer sb = new StringBuffer("select o.orderId from OrderMainModel  o where o.uuid=:uuid");
		Query query = this.getH4Session().createQuery(sb.toString());
		query.setString("uuid", uuid);
		List<Object> list = query.list();
		if (list != null && list.size() > 0) {
			String orderId = (String) list.get(0);
			return orderId;
		}
		return "";
	}

	/**
	 * 根据会员id和订单类型获取所有订单
	 */
	@Override
	public List<OrderMainModel> getOrderListByCustomerId(OrderMainQueryModel qm, String customerId, String state,
			int pageCount, int pageNo) {
		StringBuffer sb = new StringBuffer(" from OrderMainModel o where 1=1");
		if (qm != null) {
			// 订单开始时间
			if (!StringUtil.isEmpty(qm.getOrderTime())) {
				sb.append(" and o.orderTime >=:startTime");
			}
			// 评价
			if (!StringUtil.isEmpty(qm.getCommentState())) {
				sb.append(" and o.commentState =:commentState");
			}
			// 订单类型
			if (!StringUtil.isEmpty(qm.getSerachtype())) {
				sb.append(" and o.orderType =:orderType");

			}
		}
		// 会员id
		if (!StringUtil.isEmpty(customerId)) {
			sb.append(" and o.customerUuid =:customerUuid");
		}
		// 订单状态
		if (!StringUtil.isEmpty(state)) {
			sb.append(" and o.state =:state");
		}

		sb.append(" order by o.orderTime desc ");
		Query query = this.getH4Session().createQuery(sb.toString());
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			query.setFirstResult((pageNo - 1) * pageCount);
			query.setMaxResults(pageCount);
		}
		if (qm != null) {
			// 订单开始时间
			if (!StringUtil.isEmpty(qm.getOrderTime())) {
				query.setString("startTime", qm.getOrderTime().trim());
			}
			// 评价状态
			if (!StringUtil.isEmpty(qm.getCommentState())) {
				query.setString("commentState", qm.getCommentState().trim());
			}
			// 订单类型
			if (!StringUtil.isEmpty(qm.getSerachtype())) {
				query.setString("orderType", qm.getSerachtype().trim());
			}

		}
		// 会员id
		if (!StringUtil.isEmpty(customerId)) {
			query.setString("customerUuid", customerId.trim());
		}
		// 订单状态
		if (!StringUtil.isEmpty(state)) {
			query.setString("state", state.trim());
		}
		return query.list();
	}

	/**
	 * 根据会员id和订单类型查询总数量
	 */
	@Override
	public int getOrderCountByCustomerId(OrderMainQueryModel qm, String customerId, String state) {
		StringBuffer sb = new StringBuffer("select count(distinct o.uuid ) from OrderMainModel o where 1=1");

		// 会员id
		if (!StringUtil.isEmpty(customerId)) {
			sb.append(" and o.customerUuid =:customerUuid");
		}
		// 订单状态
		if (!StringUtil.isEmpty(state)) {
			sb.append(" and o.state =:state");
		}
		if (qm != null) {
			// 订单开始时间
			if (!StringUtil.isEmpty(qm.getOrderTime())) {
				sb.append(" and o.orderTime >=:startTime");
			}
			// 订单结束时间
			if (!StringUtil.isEmpty(qm.getOrderTime())) {
				sb.append(" and o.orderTime <=:endTime");
			}
			// 评价
			if (!StringUtil.isEmpty(qm.getCommentState())) {
				sb.append(" and o.commentState =:commentState");
			}
			// 退款状态
			if (!StringUtil.isEmpty(qm.getRefoundState())) {
				sb.append(" and o.refoundState =:refoundState");
			}
		}
		Query query = this.getH4Session().createQuery(sb.toString());
		// 会员id
		if (!StringUtil.isEmpty(customerId)) {
			query.setString("customerUuid", customerId.trim());
		}
		// 评价状态
		if (!StringUtil.isEmpty(state)) {
			query.setString("state", state);
		}
		if (qm != null) {
			// 订单开始时间
			if (!StringUtil.isEmpty(qm.getOrderTime())) {
				query.setString("startTime", qm.getOrderTime().trim());
			}
			// 评价状态
			if (!StringUtil.isEmpty(qm.getCommentState())) {
				query.setString("commentState", qm.getCommentState().trim());
			}
			// 退款状态
			if (!StringUtil.isEmpty(qm.getRefoundState())) {
				query.setString("refoundState", qm.getRefoundState().trim());
			}
		}

		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 查詢訂單的對應數量
	 * 
	 * @param serachType
	 * @return
	 */

	@Override
	public int getOrderCount(String serachType) {
		StringBuffer hql = new StringBuffer(
				" select count(o) from  OrderMainModel as o ,ServicestaffModel as s, CustomerModel as c  where 1=1 and o.doctorUuid = s.uuid and o.customerUuid=c.uuid  and o.orderType=:orderType");

		// 今日订单数量
		if ("2".equals(serachType)) {
			hql.append(" and substr(o.bookTime,1,10)=:bookTime ");
			hql.append(" and o.state=:state ");
			// 动态任务订单
		} else if ("3".equals(serachType)) {
			hql.append(" and o.state=:state ");
		}
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("orderType", "1");
		// 今日订单的数量
		if ("2".equals(serachType)) {
			q.setString("bookTime", DateFormatHelper.getNowTimeStr("yyyy-MM-dd"));
			q.setString("state", "4");
			// 动态任务订单数量
		} else if ("3".equals(serachType)) {
			q.setString("state", "7");
		}

		Long result = (Long) q.uniqueResult();

		return result.intValue();
	}

	/**
	 * 根据会员ID获取订单信息
	 * 
	 * @param customerUuid
	 *            会员ID
	 * @param pageCount
	 *            分页
	 * @param pageNo
	 *            页码
	 * @return
	 */
	@Override
	public List<OrderMainModel> getOrderListByCustomerUuid(String customerUuid, int pageCount, int pageNo) {
		StringBuffer sb = new StringBuffer("from OrderMainModel as omm where ");
		sb.append(" omm.customerUuid=:customerUuid ");
		sb.append(" order by omm.orderTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("customerUuid", customerUuid);
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		return q.list();
	}

	/**
	 * 根据家政员ID和时间接单状态查询数量
	 * 
	 * @param serviceStaffUuid
	 * @param receiveTime
	 * @param receiveStatus
	 * @return @2015-3-31
	 * @author :SZH
	 */
	@Override
	public int getReceiveOrderCount(String serviceStaffUuid, String curTime, String receiveTime, String receiveStatus) {
		StringBuffer hql = new StringBuffer(
				"select count(uuid) from OrderMainModel  o where o.uuid in(select os.orderMainUuid from OrderStaffModel as os where  os.serviceStaffUuid =:serviceStaffUuid and os.receiveStatus=:receiveStatus ");
		if ("year".equals(curTime)) {
			hql.append(" and substr(os.receiveTime,1,4)=:receiveTime )");
		} else if ("month".equals(curTime)) {
			hql.append(" and substr(os.receiveTime,1,7)=:receiveTime )");
		} else {
			return 0;
		}
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);
		query.setString("receiveTime", receiveTime);
		query.setString("receiveStatus", receiveStatus);

		Long result = (Long) query.uniqueResult();
		return result.intValue();

	}

	/**
	 * 根据家政员ID和时间接单状态查询数量
	 * 
	 * @param serviceStaffUuid
	 * @param receiveTime
	 * @return @2015-3-31
	 * @author :SZH
	 */
	@Override
	public int getServiceStaffOrderCount(String serviceStaffUuid, String curTime, String receiveTime) {
		StringBuffer hql = new StringBuffer(
				"select sum(o.payMoney) from OrderMainModel  o where o.uuid in(select os.orderMainUuid from OrderStaffModel as os where  os.serviceStaffUuid =:serviceStaffUuid ");
		if ("year".equals(curTime)) {
			hql.append(" and substr(os.receiveTime,1,4)=:receiveTime )");
		} else if ("month".equals(curTime)) {
			hql.append(" and substr(os.receiveTime,1,7)=:receiveTime )");
		}
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);

		if ("month".equals(curTime) || "year".equals(curTime)) {
			query.setString("receiveTime", receiveTime);
		}
		Double result = (Double) query.uniqueResult();

		if (result != null) {
			return result.intValue();
		}
		return 0;
	}

	/**
	 * 根据家政员ID和时间查询接单总额
	 * 
	 * @param serviceStaffUuid
	 * @param receiveTime
	 *            接单时间
	 * @param receiveStatus
	 *            接单拒单状态 0为拒单1为接单2为未接单 3为接单失败
	 * @return @2015-3-31
	 * @author :SZH
	 */
	@Override
	public int getReceiveOrderSum(String serviceStaffUuid, String curTime, String receiveTime, String receiveStatus) {
		StringBuffer hql = new StringBuffer(
				"select sum(o.totalMoney) from OrderMainModel  o where o.uuid in(select os.orderMainUuid from OrderStaffModel as os where  os.serviceStaffUuid =:serviceStaffUuid ");
		if ("year".equals(curTime)) {
			hql.append(" and substr(os.receiveTime,1,4)=:receiveTime )");
		} else if ("month".equals(curTime)) {
			hql.append(" and substr(os.receiveTime,1,7)=:receiveTime )");
		} else {
			return 0;
		}
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);
		query.setString("receiveTime", receiveTime);
		Number result = (Number) query.uniqueResult();
		if (result == null) {
			return 0;
		}
		return result.intValue();
	}

	/**
	 * 获得订单的数量根据订单对应的状态
	 * 
	 * @param staffUuid
	 * @param state
	 * @param commentState
	 * @return
	 */
	@Override
	public int getCountOrderNum(String staffUuid, String state, String commentState) {
		StringBuffer hql = new StringBuffer(
				"select count(o) from OrderMainModel as o ,OrderStaffModel as os where o.uuid=os.orderMainUuid ");
		hql.append(" and os.serviceStaffUuid=:serviceStaffUuid");
		if (!StringUtil.isEmpty(state)) {
			hql.append(" and o.state=:state");
		}
		if (!StringUtil.isEmpty(commentState)) {
			hql.append(" and o.commentState=:commentState");
		}

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffUuid", staffUuid);
		if (!StringUtil.isEmpty(state)) {
			q.setString("state", state);
		}
		if (!StringUtil.isEmpty(commentState)) {
			q.setString("commentState", commentState);
		}

		Number num = (Number) q.uniqueResult();
		return num.intValue();
	}

	/**
	 * 获取家政员订单
	 * 
	 * @param serviceStaffUuid
	 *            家政员编号
	 * @return
	 * @2015-4-9 @author :SZH
	 */
	@Override
	public List<OrderMainModel> getOrderMainModelByserviceStaffUuid(String serviceStaffUuid, int pageCount,
			int pageNo) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainModel omm where omm.uuid in (select osm.orderMainUuid from  OrderStaffModel osm where osm.serviceStaffUuid=:serviceStaffUuid) ORDER BY omm.orderTime DESC");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffUuid", serviceStaffUuid);
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		return q.list();
	}

	/**
	 * 取消未支付订单在服务前两个小时 设为无效订单
	 * 
	 * @param state
	 * @author :xl
	 */
	@Override
	public void CloseOrderWZF(String state, String payType) {
		StringBuffer sql = new StringBuffer(" UPDATE  order_main AS o ,order_main_address AS os SET o.state ='12'");
		sql.append(" WHERE o.uuid=os.orderMainUuid AND DATEDIFF(os.serviceTime,o.orderTime)<=2 ");
		sql.append(" AND o.state=:state ");
		sql.append(" AND o.payType=:payType ");

		Query q = this.getH4Session().createSQLQuery(sql.toString());
		q.setString("state", state);
		q.setString("payType", payType);
		q.executeUpdate();
	}

	/**
	 * 关闭未处理订单
	 * 
	 * @param serviceTime
	 */
	@Override
	public void CloseOrderWCL(String serviceTime) {
		StringBuffer sql = new StringBuffer(
				" UPDATE order_main AS o, order_main_address AS os SET o.state='12', o.urgencyStatus='0' ");
		sql.append(" WHERE o.uuid=os.orderMainUuid  AND o.urgencyStatus='1' ");
		sql.append(" AND os.serviceTime <=:serviceTime");

		Query q = this.getH4Session().createSQLQuery(sql.toString());
		q.setString("serviceTime", serviceTime);
		q.executeUpdate();

	}

	/**
	 * 根据订单组编号获取子订单集合
	 * 
	 * @param orderGroupUuid
	 * @return
	 */
	public List<OrderMainModel> getByOrderGroupUuid(String orderGroupUuid) {
		StringBuffer hql = new StringBuffer(" select o from OrderMainModel o where o.orderGroupUuid=:orderGroupUuid ");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("orderGroupUuid", orderGroupUuid);

		return q.list();
	}

	/**
	 * 确认订单
	 * 
	 * @author :xl
	 */
	@Override
	public void orderAffirm() {
		StringBuffer sql = new StringBuffer(" UPDATE order_main AS o SET o.state='11' WHERE 1=1  ");
		sql.append(" and (o.state=:state and o.adjustPayStatus !=:adjustPayStatus)");
		sql.append(" or o.state=:state1 ");
		Query q = this.getH4Session().createSQLQuery(sql.toString());
		q.setString("state", "9");
		q.setString("adjustPayStatus", "1");
		q.setString("state1", "10");

		q.executeUpdate();

	}

	@Override
	public List<OrderMainModel> getOrderList(String customerUuid, int pageCount, int pageNo) {
		StringBuffer sb = new StringBuffer("from OrderMainModel as omm where ");
		sb.append(" omm.customerUuid=:customerUuid ");
		// 查已完成的
		sb.append(" and omm.state='6' ");
		sb.append(" order by omm.orderTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("customerUuid", customerUuid);
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		return q.list();
	}

	@Override
	public List<OrderMainModel> getOrderList(String orderType, String customerUuid, int pageCount, int pageNo) {
		// StringBuffer sb = new StringBuffer("select distinct(o) from
		// OrderMainModel as omm where ");
		StringBuffer sb = new StringBuffer("select omm from OrderMainModel as omm , ServicestaffModel as s where ");
		sb.append(" omm.doctorUuid = s.uuid  ");
		sb.append(" and omm.customerUuid=:customerUuid ");
		// 查已完成的
		sb.append(" and omm.state='6' ");
		sb.append(" order by omm.orderTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("customerUuid", customerUuid);
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		return q.list();
	}

	/**
	 * 获取医生电话咨询的数量
	 */
	@Override
	public int getTotalTelNumByDoctorId(String doctorId) {
		StringBuffer hql = new StringBuffer(
				"select count(distinct o.uuid)from OrderMainModel o where o.orderType=:type1  "
						+ " and o.doctorUuid =:doctorId ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type1", "1");
		query.setString("doctorId", doctorId);
		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 获取医生id
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public String getDoctorUuidByUuid(String orderMainUuid) {
		StringBuffer hql = new StringBuffer("select o.doctorUuid from OrderMainModel o where o.uuid=:orderMainUuid  ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		List<Object> list = query.list();
		if (list != null && list.size() > 0) {
			String doctorUuid = (String) list.get(0);
			return doctorUuid;
		}
		return "";
	}

	/**
	 * 获取患者id
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public String getCustomerUuidByUuid(String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.customerUuid from OrderMainModel o where o.uuid=:orderMainUuid  ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		List<Object> list = query.list();
		if (list != null && list.size() > 0) {
			String doctorUuid = (String) list.get(0);
			return doctorUuid;
		}
		return "";
	}

	/**
	 * 根据患者的id和订单的状态查询患者所有的订单
	 * 
	 * @param state
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	@Override
	public List<OrderMainModel> getCustomerOrderList(String state, String customerUuid, int pageCount, int pageNo,
			String orderType) {
		StringBuffer sb = new StringBuffer(" select omm from OrderMainModel as omm , ServicestaffModel as s  where ");
		sb.append(" omm.doctorUuid = s.uuid  ");

		sb.append(" and omm.customerUuid=:customerUuid ");
		// 查所有的
		if (state.equals("1")) {
			// 查询未支付的订单
			sb.append(" and (omm.state='3' or omm.state='4' or omm.state='5') ");
		} else if (state.equals("2")) {
			// 查询已支付的订单
			sb.append(" and omm.state='6'");
		}
		if (!StringUtil.isEmpty(orderType)) {
			sb.append(" and omm.orderType=:orderType ");
		}
		sb.append(" order by omm.orderTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("customerUuid", customerUuid);
		if (!StringUtil.isEmpty(orderType)) {
			q.setString("orderType", orderType);
		}
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		return q.list();
	}

	/**
	 * 根据患者的id和订单的状态查询患者所有的订单
	 * 
	 * @param state
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	@Override
	public List<OrderMainModel> getOrderList(String state, String doctorUuid, int pageCount, int pageNo,
			String orderType) {
		StringBuffer sb = new StringBuffer("from OrderMainModel as omm where ");
		sb.append(" omm.doctorUuid=:doctorUuid ");
		// 查所有的支付待审核
		if (state.equals("0")) {
			sb.append(" and omm.state='3' ");
		} else if (state.equals("1")) {
			//已审核通过的
			sb.append(" and (omm.state='4' or omm.state='5') ");
		} else if (state.equals("2")) {
			// 查询 已完成
			sb.append(" and omm.state='6'");
		}
		if (!StringUtil.isEmpty(orderType)) {
			sb.append(" and omm.orderType=:orderType ");
		}
		sb.append(" order by omm.orderTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("doctorUuid", doctorUuid);
		if (!StringUtil.isEmpty(orderType)) {
			q.setString("orderType", orderType);
		}
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		return q.list();
	}
	
	
	/**
	 * 根据患者的id和订单的状态查询患者所有编号
	 * 
	 * @param state
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	@Override
	public  List<String> getCustomerUuids(String state, String doctorUuid, int pageCount, int pageNo,
			String orderType) {
		StringBuffer sb = new StringBuffer(" select  distinct(omm.customerUuid) from OrderMainModel as omm where ");
		sb.append(" omm.doctorUuid=:doctorUuid ");
		// 查所有的支付待审核
		if (state.equals("0")) {
			sb.append(" and omm.state='3' ");
		} else if (state.equals("1")) {
			//已审核通过的
			sb.append(" and (omm.state='4' or omm.state='5') ");
		} else if (state.equals("2")) {
			// 查询 已完成
			sb.append(" and omm.state='6'");
		}else{
			sb.append(" and (omm.state='4' or omm.state='5' or  omm.state='6' ) ");
		}
		
		if (!StringUtil.isEmpty(orderType)) {
			sb.append(" and omm.orderType=:orderType ");
		}
		sb.append(" order by omm.orderTime desc ");
		Query q = getH4Session().createQuery(sb.toString());
		q.setString("doctorUuid", doctorUuid);
		if (!StringUtil.isEmpty(orderType)) {
			q.setString("orderType", orderType);
		}
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			q.setMaxResults(pageCount);
		}
		return q.list();
	}

	/**
	 * 通过会议Id获取订单对象
	 * 
	 * @param confId
	 * @return
	 */
	public OrderMainModel getOrderMainByConfId(String confId) {
		if (StringUtil.isEmpty(confId)) {
			return null;
		}
		StringBuffer hql = new StringBuffer("from OrderMainModel as o where ");
		hql.append(" o.orgId =:confId");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("confId", confId);

		List<OrderMainModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据医生编号 通话时长、预约日期 by add xl 20160115
	 * 
	 * @param doctorUuid
	 * @param consultDuration
	 * @param bookTime
	 * @return
	 */
	@Override
	public List<OrderMainModel> getOrderMains(String doctorUuid, String consultDuration, String bookTime) {
		StringBuffer hql = new StringBuffer("from OrderMainModel as o where ");
		hql.append("  o.doctorUuid =:doctorUuid");
		hql.append(" and o.consultDuration =:consultDuration");
		hql.append(" and o.bookTime =:bookTime");
		hql.append(" and (o.state !=:state1 or o.state !=:state2 ) ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		q.setString("consultDuration", consultDuration);
		q.setString("bookTime", bookTime);
		q.setString("state1", OrderStatusEnum.REFUSE.getValue());
		q.setString("state2", OrderStatusEnum.ORDERCANCEL.getValue());

		List<OrderMainModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	/**
	 * 获取可以分账的订单信息
	 * by add xl 20160128
	 * @return
	 */
	@Override
	public List<OrderMainModel> getCanFzOrders() {
		StringBuffer hql = new StringBuffer("from OrderMainModel as o where  1=1 ");
		hql.append(" and o.accountState !=:accountState ");
		hql.append(" and ( o.state =:state1 or o.state =:state2 or o.state =:state3  or o.state =:state4 ) ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("accountState", OrderMainModel.ACCOUNTSTATE_HSFZ);
		q.setString("state1", OrderStatusEnum.CONFIRMED.getValue());
		q.setString("state2", OrderStatusEnum.DROPIN.getValue());
		q.setString("state3", OrderStatusEnum.DROPINCOMPLETE.getValue());
		q.setString("state4", OrderStatusEnum.DONGTAI.getValue());
		

		List<OrderMainModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
