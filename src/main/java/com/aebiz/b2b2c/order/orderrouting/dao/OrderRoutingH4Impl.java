package com.aebiz.b2b2c.order.orderrouting.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.withdrawapply.util.DateUtil;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingQueryModel;

@Repository
public class OrderRoutingH4Impl extends BaseH4Impl<OrderRoutingModel, OrderRoutingQueryModel>
		implements OrderRoutingDAO {

	private int Monthlength = 7;
	private int Daylength = 10;

	/**
	 * 根据订单id获取所有的分账数据
	 */
	@Override
	public List<OrderRoutingModel> getModelByOrderId(String orderUuid) {
		String hql = " from OrderRoutingModel o where 1=1 ";
		hql += (" and o.orderMainUuid =:orderMainUuid and o.routType !=:routType ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderUuid);
		query.setString("routType", "1");
		return (List<OrderRoutingModel>) query.list();
	}

	/**
	 * 查询所有分账信息
	 * 
	 * @Override public List
	 *           <OrderRoutingModel> getByCondition(OrderRoutingQueryModel qm,
	 *           int start, int pageShow) { StringBuffer hql = new StringBuffer(
	 *           "  from OrderRoutingModel o where 1=1 ");
	 *           hql.append(getAppendHql1(qm)); Query query =
	 *           this.getH4Session().createQuery(hql.toString());
	 *           query.setFirstResult(start); query.setMaxResults(pageShow);
	 *           setValue(qm, query); setAppendHqlValue(qm, query); List
	 *           <OrderRoutingModel> list = new ArrayList(); List
	 *           <OrderRoutingModel> modellist = (List<OrderRoutingModel>) query
	 *           .list(); // 分账查询时间 String searchTime = qm.getRoutTime(); String
	 *           dateTime = DateFormatHelper
	 *           .getNowTimeStr(DateFormatHelper.FORMAT_DATE_STR); // 按月份分账 if
	 *           (qm.MONTH_ROUTING.equals(qm.getFzType())) { if
	 *           (!StringUtil.isEmpty(qm.getRoutYear()) &&
	 *           !StringUtil.isEmpty(qm.getRoutMonth())) { searchTime =
	 *           qm.getRoutYear() + "-" + qm.getRoutMonth(); } else { searchTime
	 *           = dateTime.substring(0, 7); } // 按年份分账 } else if
	 *           (qm.YEAR_ROUTING.equals(qm.getFzType())) { if
	 *           (!StringUtil.isEmpty(qm.getRoutOnlyYear())) { searchTime =
	 *           qm.getRoutOnlyYear(); } else { searchTime =
	 *           dateTime.substring(0, 4); } } for (int i = 0; i <
	 *           modellist.size(); i++) { OrderRoutingModel mo =
	 *           (OrderRoutingModel) modellist.get(i);
	 *           mo.setSerachTime(searchTime); list.add(mo); } return list; }
	 */

	/**
	 * 为搜索查询赋值
	 */
	@Override
	protected void setAppendHqlValue(OrderRoutingQueryModel qm, Query q) {

		// 赋值
		if (!StringUtil.isEmpty(qm.getDoctorUuid())) {
			q.setString("doctorUuid", qm.getDoctorUuid());
		}
		if (!StringUtil.isEmpty(qm.getIncomeType())) {
			q.setString("incomeType", qm.getIncomeType());
		}

		// 赋值
		if (!StringUtil.isEmpty(qm.getDoctorRealName())) {
			q.setString("realName", "%" + qm.getDoctorRealName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			q.setString("mobile", "%" + qm.getDoctorMobile() + "%");
		}
	}

	/**
	 * 查询所有充值记录的总数量
	 */

	@Override
	public int getCount(OrderRoutingQueryModel qm) {
		String hql = "select count(distinct o.serviceStaffUuid ) from OrderRoutingModel o " + getMultiModel()
				+ " where 1=1 ";

		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		return ((Number) q.uniqueResult()).intValue();
	}

	@Override
	protected String getMultiModel() {
		return " , ServicestaffModel as s ";
	}

	/**
	 * 拼接搜索时需要的字段
	 */
	@Override
	protected String getAppendHql(OrderRoutingQueryModel qm) {
		StringBuffer buff = new StringBuffer(" and o.serviceStaffUuid = s.uuid ");
		if (!StringUtil.isEmpty(qm.getDoctorUuid())) {
			buff.append(" and o.serviceStaffUuid=:doctorUuid");
		}
		if (!StringUtil.isEmpty(qm.getIncomeType())) {
			buff.append(" and o.incomeType=:incomeType");
		}

		if (!StringUtil.isEmpty(qm.getDoctorRealName())) {
			buff.append(" and s.realName Like:realName");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			buff.append(" and s.mobile Like:mobile");
		}

		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			buff.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			buff.append(" order by o.routTime desc");
		}
		
		return buff.toString();
	}

	/**
	 * 根据日期和家政员ID获取所有符合条件的分账信息
	 * 
	 * @param day
	 *            传入距离查询日期的天数，例如：如果查询昨天的收入，则day=1，今天的day=0.以此类推
	 * @param id
	 *            家政员ID
	 * @return
	 */
	public List<OrderRoutingModel> getAppAll(String id, int start, int length, String date) {

		StringBuffer hql = new StringBuffer(
				"from OrderRoutingModel o where o.serviceStaffUuid =:id and substr(o.routTime, ");
		hql.append(start);
		hql.append(",");
		hql.append(length);
		hql.append(")='");
		hql.append(date);
		hql.append("'");
		Query q = getH4Session().createQuery(hql.toString());
		q.setString("id", id);
		return q.list();
	}

	@Override
	/**
	 * 查询家政员在一定日期内的订单数量
	 * 
	 * @param date
	 *            查询日期
	 * @param id
	 *            家政员ID
	 * @return
	 */
	public int getStaffOrderCount(String date, String id) {
		return getOrderCount(id, Daylength, date);
	}

	@Override
	/**
	 * 查询家政员在一定日期内的订单数量
	 * 
	 * @param date
	 *            查询日期
	 * @param id
	 *            家政员ID
	 * @return
	 */
	public int getOrderCount(String id, int length, String date) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM order_routing WHERE serviceStaffUuid =:id ");
		sql.append(" AND SUBSTRING(routTime,1,");
		sql.append(length);
		sql.append(") =:date");
		Query q = getH4Session().createSQLQuery(sql.toString());
		q.setString("id", id);
		q.setString("date", date);
		int count = ((Number) q.uniqueResult()).intValue();
		return count;

	}

	/**
	 * 根据家政员编号和分账时间获取总的分账金额等
	 */
	@Override
	public double getTotalMoneyBystaffId(String strName, String serviceStaffUuid, String routTime) {
		String dateTime = DateFormatHelper.getNowTimeStr(DateFormatHelper.FORMAT_DATE_STR);
		if (!StringUtil.isEmpty(routTime)) {
			dateTime = routTime;
		}
		String hql = " select sum(o." + strName + ")from OrderRoutingModel o where 1=1 ";
		hql += (" and o.serviceStaffUuid =:serviceStaffUuid and o.routTime like:routTime");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);
		query.setString("routTime", dateTime + "%");
		if (query.uniqueResult() != null) {
			return ((Number) query.uniqueResult()).doubleValue();
		}
		return 0;
	}

	/**
	 * 根据家政员id查询在一定时间内的分账记录
	 */
	@Override
	public List<Object> getOrderRoutingList(String serviceStaffUuid, String timeType, int pageNo, int pageCount) {
		Date dateTime = DateFormatHelper.toDate(DateFormatHelper.getNowTimeStr("yyyy-MM-dd 00:00:00"));
		StringBuffer sb = new StringBuffer(
				" select orm.routTime,orm.routPrice,orm.routType,orm.orderMainUuid   from OrderRoutingModel orm where orm.serviceStaffUuid =:serviceStaffUuid");
		if (WithdrawApplyModel.LAST_ONEWEEK.equals(timeType) || WithdrawApplyModel.LAST_ONEMONTH.equals(timeType)
				|| WithdrawApplyModel.LAST_THREEMONTH.equals(timeType)
				|| WithdrawApplyModel.LAST_HALFYEAR.equals(timeType)) {
			sb.append(" and orm.routTime >=:startSuccessTime and orm.routTime <:endSuccessTime");
		}
		sb.append(" order by orm.routTime desc ");
		Query query = this.getH4Session().createQuery(sb.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);
		if (WithdrawApplyModel.LAST_ONEWEEK.equals(timeType)) {
			query.setString("startSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateFormatHelper.getDateBefore(dateTime, 7)));
			query.setString("endSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateFormatHelper.getDateAfter(dateTime, 1)));
		} else if (WithdrawApplyModel.LAST_ONEMONTH.equals(timeType)) {
			query.setString("startSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateFormatHelper.getDateBefore(dateTime, 30)));
			query.setString("endSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateFormatHelper.getDateAfter(dateTime, 1)));
		} else if (WithdrawApplyModel.LAST_THREEMONTH.equals(timeType)) {
			query.setString("startSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateFormatHelper.getDateBefore(dateTime, 90)));
			query.setString("endSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateFormatHelper.getDateAfter(dateTime, 1)));
		} else if (WithdrawApplyModel.LAST_HALFYEAR.equals(timeType)) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			query.setString("startSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateUtil.addMonths(timestamp, -6)));
			query.setString("endSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00", DateFormatHelper.getDateAfter(dateTime, 1)));
		}
		if (pageCount != 0 && pageNo != 0) {
			query.setFirstResult((pageNo - 1) * pageCount);
			query.setMaxResults(pageCount);
		}
		return query.list();
	}

	/**
	 * 获取家政员的分账总金额
	 */
	@Override
	public double getTotalRoutingMoneyBystaffId(String serviceStaffUuid) {
		StringBuffer hql = new StringBuffer(" select sum(o.routPrice)from OrderRoutingModel o where 1=1 ");
		hql.append(" and o.serviceStaffUuid =:serviceStaffUuid");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);
		List list = query.list();
		if (list != null && list.size() > 0) {
			return (Double) list.get(0);
		}
		return 0;
	}

	/**
	 * 根据家政员id和分账时间查询对应的记录
	 */
	@Override
	public List<OrderRoutingModel> getWagesByserviceStaffUuid(String serviceStaffUuid, String routTime) {

		StringBuffer hql = new StringBuffer(" from OrderRoutingModel o where 1=1 ");
		hql.append(" and o.serviceStaffUuid =:serviceStaffUuid and o.routTime like:routTime");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);
		query.setString("routTime", routTime + "%");
		List list = query.list();
		return list;
	}

	/**
	 * 获取某一个月份所有家政员分账总和
	 */
	@Override
	public double getTotalRoutingMoneyByTime(String routTime, String routType) {
		StringBuffer hql = new StringBuffer(" select sum(o.routPrice)from OrderRoutingModel o where 1=1 ");
		if (!StringUtil.isEmpty(routTime)) {
			hql.append(" and o.routTime like:routTime");
		}
		if (!StringUtil.isEmpty(routType)) {
			hql.append(" and o.routType =:routType");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(routTime)) {
			query.setString("routTime", routTime + "%");
		}
		if (!StringUtil.isEmpty(routType)) {
			query.setString("routType", routType);
		}
		List list = query.list();
		if (list != null && list.size() > 0) {
			if (list.get(0) != null) {
				return (Double) list.get(0);
			}
		}
		return 0;
	}

	/**
	 * 获取家政员分账列表
	 * 
	 * @param serviceStaffUuid
	 * @return @2015-4-20
	 * @author :SZH
	 */
	@Override
	public List<OrderRoutingModel> getOrderRoutingsByServiceStaffId(String serviceStaffUuid, int pageCount,
			int pageNo) {
		StringBuffer hql = new StringBuffer(
				" from OrderRoutingModel o where o.serviceStaffUuid =:serviceStaffUuid order by o.routTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			query.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			query.setMaxResults(pageCount);
		}
		return query.list();
	}

	/**
	 * 获取平台分账金额
	 * 
	 * @param orderMainUuid
	 * @param routeType
	 * @return
	 */
	@Override
	public double getStaffTotalRouting(String orderMainUuid, String routType) {
		StringBuffer hql = new StringBuffer(" select o.routPrice  from OrderRoutingModel o where 1=1 ");
		hql.append(" and o.orderMainUuid =:orderMainUuid and o.routType =:routType");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		query.setString("routType", routType);
		Object obj = query.uniqueResult();

		if (obj != null) {
			return ((Number) query.uniqueResult()).doubleValue();
		} else {
			return 0;
		}
	}

	/**
	 * 得到医生的不重复的UuidList
	 * 
	 * @return
	 */
	@Override
	public List<String> getDoctorUuids() {
		StringBuffer hql = new StringBuffer(" select distinct(o.serviceStaffUuid) from OrderRoutingModel as o ");
		Query query = this.getH4Session().createQuery(hql.toString());

		List<String> obj = query.list();
		if (obj != null && obj.size() > 0) {
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * 根据医生Uuid得到List
	 * 
	 * @return
	 */
	@Override
	public List<OrderRoutingModel> getByDoctorUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderRoutingModel as o where o.serviceStaffUuid=:doctorUuid and o.routType=:routType");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("doctorUuid", doctorUuid);
		query.setString("routType", "0"); /* 分账类型 0：医生 1：平台 */
		List<OrderRoutingModel> obj = query.list();
		if (obj != null && obj.size() > 0) {
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * 获取平台收益的总钱数
	 * 
	 * @return
	 */
	@Override
	public Number getAllPlatformIncome() {
		StringBuffer hql = new StringBuffer(
				"select sum(o.routPrice) from OrderRoutingModel as o where o.routType=:routType ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("routType", "1");
		Number totalMonery = (Number) query.uniqueResult();
		return totalMonery;
	}

	/**
	 * 根据医生的id和类型获取医生的收入
	 * 
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	@Override
	public Number getDoctorAllIncomeByIdAndType(String doctorUuid, String type) {
		StringBuffer hql = new StringBuffer("select sum(o.routPrice) from OrderRoutingModel as o ");
		Number totalMonery = 0;
		if (type.equals("1")) {
			hql.append(" where SUBSTR(o.routTime,1,10)=:time and o.serviceStaffUuid=:doctorUuid ");
			Query query = this.getH4Session().createQuery(hql.toString());
			query.setString("doctorUuid", doctorUuid);
			query.setString("time", OrderRoutingH4Impl.getYesDay());
			totalMonery = (Number) query.uniqueResult();

		} else if (type.equals("0")) {
			hql.append(" where  o.serviceStaffUuid=:doctorUuid ");
			Query query = this.getH4Session().createQuery(hql.toString());
			query.setString("doctorUuid", doctorUuid);
			totalMonery = (Number) query.uniqueResult();
		}
		return totalMonery;
	}

	// 获取昨天的日期类型的方法
	private static String getYesDay() {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String defaultStartDate = sdf.format(dBefore); // 格式化前一天
		String defaultEndDate = sdf.format(dNow);

		return defaultStartDate.substring(0, 10);
	}

}
