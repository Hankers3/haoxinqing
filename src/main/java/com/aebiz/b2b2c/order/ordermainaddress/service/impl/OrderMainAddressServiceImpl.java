package com.aebiz.b2b2c.order.ordermainaddress.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.ordermainaddress.dao.OrderMainAddressDAO;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressQueryModel;
import com.aebiz.b2b2c.order.orderrouting.dao.OrderRoutingDAO;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;

@Service
@Transactional
public class OrderMainAddressServiceImpl extends
		BaseServiceImpl<OrderMainAddressModel, OrderMainAddressQueryModel>
		implements OrderMainAddressService {
	private OrderMainAddressDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private OrderRoutingService orservice;
	@Autowired
	public void setMyDao(OrderMainAddressDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderMainAddressModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderMainAddressModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderMainAddressModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过订单编号获得这个订单对应的收货地址对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByUuid(String orderUuid) {
		return myDao.getOrderMainAddressModelByUuid(orderUuid);
	}

	
	/**
	 * 通过主订单编号获取客户电话
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddresscustomerMobile(String orderMainUuid) {
		
		return myDao.getOrderMainAddresscustomerMobile(orderMainUuid);
	}

	/**
	 * 通过主订单编号获取客户服务地址
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddressaddress(String orderMainUuid) {
		return myDao.getOrderMainAddressaddress(orderMainUuid);
	}

	/**
	 * 通过主订单编号获取客户服务时间
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddressserviceTime(String orderMainUuid) {
		return myDao.getOrderMainAddressserviceTime(orderMainUuid);
	}

	
	/**
	 * 通过主订单编号获取客户名称
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddressname(String orderMainUuid) {
		return myDao.getOrderMainAddressname(orderMainUuid);
	}


	/**
	 * 通过家政员编号和日期获得获取对应的订单编号
	 * @param orderUuid
	 * @param date
	 * @return
	 */
	@Override
	public List<String> getOrderMainAddressModelByServiceUuid(
			String serviceUuid, String date) {
		return myDao.getOrderMainAddressModelByServiceUuid(serviceUuid,date);
	}

	
	
	
	/**
	 * 通过手机号获得这个订单对应的收货地址对象
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	@Override
	public OrderMainAddressModel getOrderMainAddressModelByMobile(String Mobile) {
		
		return myDao.getOrderMainAddressModelByMobile(Mobile);
	}

	/**
	 * 通过日期,订单类型，家政员编号获得该日期的订单号
	 * @param date
	 * @return
	 */
	@Override
	public List<String> getOrderMainUuidByDate(String date,String type, String userId) {
		return myDao.getOrderMainUuidByDate(date,type,userId);
	}
	
	
	/**
	 * 查询家政员在一定日期内的订单数量
	 * @param id 家政员ID
	 * @return
	 */
	public int getStaffOrderCount(String id,int day) {
		//获取当前日期
		String  date = orservice.getDay(new Date(),day);
		//调用 OrderRoutingDAO中的getStaffOrderCount方法获取订单数
		int staffOrderCount = orservice.getStaffOrderCount(date, id);
		return staffOrderCount;
	}

	@Override
	public int getStaffOrderMonthCount(String id) {

		return orservice.getStaffMonthOrderCount(id);
	}

	
	
	/**
	 * 根据主订单编号得到上门间隔时长的方法
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	@Override
	public int getdoorinBetweenTimeByordId(List orderMainUuids) {
	
		return myDao.getdoorinBetweenTimeByordId(orderMainUuids);
	}

	
	/**
	 * 通过主订单编号获取对应的收货地址对象
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public OrderMainAddressModel getOrderMainAddressModelbyOrdId(
			String orderMainUuid) {
		
		return myDao.getOrderMainAddressModelbyOrdId(orderMainUuid);
	}

	
	/**
	 * 根据时间得到时间内的已丈量的总订单量
	 * hedongfei
	 * @param 
	 * @return
	 */
	@Override
	public int getMeasuredOrders(String time,String searchType) {
		
		return myDao.getMeasuredOrders(time,searchType);
	}

	/**
	 * 根据时间得到时间内的已丈量订单丈量时间及服务时间偏差的总和
	 * hedongfei
	 * @param 
	 * @return
	 */
	@Override
	public int getMeasuredOrdersTimeWarp(String time,String searchType) {
		
		return myDao.getMeasuredOrdersTimeWarp(time,searchType);
	}
	/**
	 * 根据时间得到时间内的已丈量订单丈量时间及服务时间偏差的总和
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	@Override
	public OrderMainAddressModel getOrderMainAddressByMobileAndCity(
			String Mobile, String city) {
		
		return myDao.getOrderMainAddressByMobileAndCity(Mobile, city);
	}

}