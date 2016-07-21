package com.aebiz.b2b2c.order.orderrouting.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingQueryModel;

public interface OrderRoutingDAO extends
		BaseDAO<OrderRoutingModel, OrderRoutingQueryModel> {

	/**
	 * 根据日期和家政员ID获取所有符合条件的分账信息
	 * 
	 * @param day
	 *            查询日期距离当前日期的天数差，例如：如果查询昨天的收入，则day=1，今天的day=0.以此类推
	 * @param id
	 *            家政员ID
	 * @return
	 */
	public List<OrderRoutingModel> getAppAll(String id, int start, int length,
			String date);

	/**
	 * 查询家政员在一定日期内的订单数量
	 * 
	 * @param date
	 *            查询日期
	 * @param id
	 *            家政员ID
	 * @return
	 */
	public int getStaffOrderCount(String date, String id);

	/**
	 * 根据订单id获取所有的分账数据
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderRoutingModel> getModelByOrderId(String orderUuid);

	/**
	 * 查询家政员在一定日期内的订单数量
	 * 
	 * @param date
	 *            查询日期
	 * @param id
	 *            家政员ID
	 * @return
	 */
	public int getOrderCount(String id, int length, String date);

	/**
	 * 根据家政员编号和分账时间获取总的分账金额等
	 * 
	 * @param serviceStaffUuid
	 *            家政员id
	 * @param routTime
	 *            分账时间
	 * @param strName
	 *            要查询的字段
	 * @return
	 */
	public double getTotalMoneyBystaffId(String strName,
			String serviceStaffUuid, String routTime);

	/**
	 * 根据家政员id查询在一定时间内的分账记录
	 * 
	 * @param serviceStaffUuid
	 *            家政员id
	 * @param timeType
	 *            0：最近一周 1：最近一个月 2：最近3个月 3：最近半年
	 * @param pageNo
	 * @param pageCount
	 * @return
	 */
	public List<Object> getOrderRoutingList(String serviceStaffUuid,
			String timeType, int pageNo, int pageCount);

	/**
	 * 获取家政员的分账总金额
	 * 
	 * @param serviceStaffUuid
	 * @return
	 */
	public double getTotalRoutingMoneyBystaffId(String serviceStaffUuid);

	/**
	 * 根据家政员id和分账时间查询对应的记录
	 * 
	 * @param serviceStaffUuid
	 * @param routTime
	 * @return
	 */
	public List<OrderRoutingModel> getWagesByserviceStaffUuid(
			String serviceStaffUuid, String routTime);

	/**
	 * 获取某一个月份所有家政员分账总和
	 * 
	 * @param routTime
	 * @return
	 */
	public double getTotalRoutingMoneyByTime(String routTime, String routType);

	/**
	 * 获取家政员分账列表
	 * 
	 * @param serviceStaffUuid
	 * @return
	 * @2015-4-20
	 * @author :SZH
	 */
	public List<OrderRoutingModel> getOrderRoutingsByServiceStaffId(
			String serviceStaffUuid, int pageCount, int pageNo);

	/**
	 * 获取平台分账金额
	 * 
	 * @param orderMainUuid
	 * @param routeType
	 * @return
	 */

	public double getStaffTotalRouting(String orderMainUuid, String routeType);

	/**
	 * 得到医生的不重复的UuidList
	 * 
	 * @return
	 */
	public List<String> getDoctorUuids();

	/**
	 * 根据医生Uuid得到List
	 * 
	 * @return
	 */
	public List<OrderRoutingModel> getByDoctorUuid(String doctorUuid);
	/**
	 * 获取平台收益的总钱数
   	 * @return
   	 */
	public Number getAllPlatformIncome();
    /**
   * 根据医生的id和类型获取医生的收入
   * @param doctorUuid
   * @param type
   * @return
   */
  public Number getDoctorAllIncomeByIdAndType(String doctorUuid, String type);


}