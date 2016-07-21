package com.aebiz.b2b2c.order.orderrouting.service;

import java.util.Date;
import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingQueryModel;

public interface OrderRoutingService extends BaseService<OrderRoutingModel,OrderRoutingQueryModel>{
	
	/**
	 * 根据订单id获取所有的分账信息
	 * @param orderMainUuid
	 * @return
	 */
	public List<OrderRoutingModel> searchAllRoutings(String orderMainUuid);
	
	/**
	 * 查询所有分账记录的总数量
	 * @param qm
	 * @return
	 */
	public int getAllChargeCount(OrderRoutingQueryModel qm);
	
	/**
	 * 查询家政人员一天的收入
	 * @param day 传入距离查询日期的天数，例如：如果查询昨天的收入，则day=1，今天的day=0.以此类推
	 * @return 所查询日期的收入
	 */
	public Double getTodayIncome(int day,String id);
	/**
	 * 查询家政员本年收入
	 * @param id 家政员ID
	 * @return
	 */
	public Double getYearSumIncome(String id);
	/**
	 * 传入一个util.Date类型的时间和距离传入时间的天数day，返回一个String类型的”年月日“时间："yyyy-MM-dd"
	 * @param date util.Date对象
	 * @param day 查询日期距离当前日期的天数差，例如：如果查询昨天的收入，则day=1，今天的day=0.以此类推
	 * @return String类型的”年月日“时间："yyyy-MM-dd"
	 */
	public String getDay(Date date,int day);
	/**
	 * 获得月份
	 * @return
	 */
	public  String getMonth();
	/**
	 * 获得本年度内十二个月份的收入
	 * @return
	 */
	public Double[] getAllMonthIncomes(String id);
	/**
	 * 查询家政员在某月份内的总收入
	 * @param id 家政员ID
	 * @param month 查询月份   格式为：01,02,12
	 * @return 某月份内的总收入
	 */
	public Double getMonthSumIncome(String id,String month);
	/**
	 * 查询家政员在某月份的每一笔收入
	 * @param id 家政员ID
	 * @param month 查询月份
	 * @return 某月份的每一笔收入
	 */
	public List<Double> getMonthIncomes(String id,String month);
	/**
	 * 获取当前月份收入
	 * @param id 家政员ID
	 * @return 当月收入
	 */
	public Double getCurrentMonthIncomes(String id);
	/**
	 * 获取本年度所有订单
	 * @param id
	 * @return
	 */
	public List<Double> getYearIncomes(String id);
	
	/**
	 * 根据家政员编号和分账时间获取总的分账金额等
	 * @param strName 要查询的字段
	 * @param serviceStaffUuid
	 * @param routTime
	 * @return
	 */
	public double getTotalMoneyBystaffId(String strName, String serviceStaffUuid,String routTime);
	/**
	 * 查询家政员在本月内的订单数量
	 * @param id 家政员ID
	 * @return
	 */
	public int getStaffMonthOrderCount(String id);
	/**
	 * 查询家政员在一定日期内的订单数量
	 * @param date 查询日期
	 * @param id 家政员ID
	 * @return
	 */
	public int getStaffOrderCount(String date, String id);
	
	/**
	 * 创建分账信息
	 * 
	 * @param serviceStaffUuid 家政员编号
	 * @param orderMainUuid 订单编号
	 * @param routPrice 分账金额
	 * @param routType 分账类型 
	 */
	public void createOrderRout(String serviceStaffUuid,String orderMainUuid,double routPrice,String routType);
	
	/**
	 * 根据家政员id查询在一定时间内的分账记录
	 * @param serviceStaffUuid 家政员id
	 * @param timeType  0：最近一周  1：最近一个月  2：最近3个月  3：最近半年
	 * @param pageNo
	 * @param pageCount
	 * @return
	 */
	public List<Object> getOrderRoutingList(String serviceStaffUuid, String timeType,int pageNo,int pageCount);
	
	/**
	 * 获取家政员的总分账金额
	 * @param serviceStaffUuid
	 * @return
	 */
	public Double getStaffTotalRouting(String serviceStaffUuid);
	
	/**
	 * 根据家政员id和分账时间查询对应的记录
	 * @param serviceStaffUuid
	 * @param routTime
	 * @return
	 */
	public List<OrderRoutingModel> getWagesByserviceStaffUuid(String serviceStaffUuid,String routTime);
	
	/**
	 * 获取某一个月份所有家政员或平台分账总和
	 * @param routTime
	 * @return
	 */
	public double getTotalRoutingMoneyByTime( String routTime,String routType);
	/**
	 * 获取家政员分账列表
	 * @param serviceStaffUuid
	 * @return
	 * @2015-4-20
	 * @author :SZH
	 */
	public List<OrderRoutingModel> getOrderRoutingsByServiceStaffId(String serviceStaffUuid, int pageCount, int pageNo);

	public double format(double price);
	
	/**
	 * 获取平台分账金额
	 
	 * @param orderMainUuid
	 * @param routeType
	 * @return
	 */
	
	public Double getStaffTotalRouting(String orderMainUuid ,String routeType);
	
	/**
	 * 得到医生的不重复的UuidList
	 * @return
	 */
	public List<String> getDoctorUuids();
	
	/**
	 * 得到医生的不重复的UuidList
	 * @return
	 */
	public List<String> getUuids();
	/**
	 * 根据医生Uuid得到List
	 * @return
	 */
	public List<OrderRoutingModel> getByDoctorUuid(String doctorUuid);
	/**
     * 获取平台收益总钱数
     * @return
     */
    public Number getAllPlatformIncome();
    /**
     * 根据医生的id和类型获取医生的收入
     * @param doctorUuid
     * @param type
     * @return
     */
    public Number getDoctorAllIncomeByIdAndType(String doctorUuid,String type);
    
    /**
     * 订单定时任务分账
     * new add by  xl 20160127
     */
    public void orderFz();
    
}
