package com.aebiz.b2b2c.order.ordermainaddress.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressQueryModel;

public interface OrderMainAddressDAO extends
		BaseDAO<OrderMainAddressModel, OrderMainAddressQueryModel> {

	/**
	 * 通过订单编号获得这个订单对应的收货地址对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByUuid(String orderUuid);


	
	/**
	 * 通过主订单编号获取客户手机
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
	 * 通过家政员编号和日期获得获取对应的订单编号数组
	 * @param orderUuid
	 * @param date
	 * @return
	 */
	public List<String> getOrderMainAddressModelByServiceUuid(String serviceUuid,String date);
	/**
	 * 通过日期,订单类型，家政员编号获得该日期的订单号数组
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
	
	/**
	 * 通过手机号获得这个订单对应的收货地址对象
	 * 
	 * @param Mobile
	 * @param city
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressByMobileAndCity(String Mobile,String city);
}