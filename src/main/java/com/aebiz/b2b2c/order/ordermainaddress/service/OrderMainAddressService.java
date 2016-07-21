package com.aebiz.b2b2c.order.ordermainaddress.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressQueryModel;

public interface OrderMainAddressService extends
		BaseService<OrderMainAddressModel, OrderMainAddressQueryModel> {

	/**
	 * 通过订单编号获得这个订单对应的收货地址对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByUuid(String orderUuid);


	/**
	 * 通过主订单编号获取客户电话
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	public String getOrderMainAddresscustomerMobile(String orderMainUuid);
	
	/**
	 * 通过主订单编号获取客户服务地址
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	public String getOrderMainAddressaddress(String orderMainUuid);
	
	/**
	 * 通过主订单编号获取客户服务时间
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	public String getOrderMainAddressserviceTime(String orderMainUuid);
	
	/**
	 * 通过主订单编号获取客户名称
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	public String getOrderMainAddressname(String orderMainUuid);

	/**
	 * 通过家政员编号和日期获得获取对应的订单编号
	 * @param orderUuid
	 * @param date
	 * @return
	 */
	public List<String> getOrderMainAddressModelByServiceUuid(String serviceUuid, String date) ;
	
	/**
	 * 通过日期,订单类型，家政员编号获得该日期的订单号
	 * @param date
	 * @return
	 */
	public List<String> getOrderMainUuidByDate(String date, String type, String userId);
	
	/**
	 * 通过手机号获得这个订单对应的收货地址对象
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByMobile(String Mobile);
	
	/**
	 * 通过手机号获得这个订单对应的收货地址对象
	 * 
	 * @param Mobile
	 * @param city
	 * @return
	 */
	 
	public OrderMainAddressModel getOrderMainAddressByMobileAndCity(String Mobile,String city);
	/**
	 * 查询家政员在一定日期内的订单数量
	 * @param id 家政员ID
	 * @return
	 */
	public int getStaffOrderCount(String id,int day);
	/**
	 * 查询家政员在本月内的订单数量
	 * @param id 家政员ID
	 * @return 本月内的订单数量
	 */
	public int getStaffOrderMonthCount(String id);
	
	/**
	 * 根据主订单编号得到上门间隔时长的方法
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	public int getdoorinBetweenTimeByordId(List orderMainUuids);

	
	/**
	 * 通过主订单编号获取对应的收货地址对象
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelbyOrdId(String orderMainUuid);
	
	/**
	 * 根据时间得到时间内的已丈量的总订单量
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	public int getMeasuredOrders(String time,String searchType);
	
	/**
	 * 根据时间得到时间内的已丈量订单丈量时间及服务时间偏差的总和
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	public int getMeasuredOrdersTimeWarp(String time,String searchType);
	
}
