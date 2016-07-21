package com.aebiz.b2b2c.order.ordermain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;

public interface OrderMainService extends
		BaseService<OrderMainModel, OrderMainQueryModel> {
	@Override
	public void update(OrderMainModel paramM);

	/**
	 * 根据用户id来获取其订单列表
	 * 
	 * @param customerUuid
	 */
	public List<String> getOrderListByCustomerUuid(String customerUuid,
			String type, int pageCount, int pageNo);

	/**
	 * 根据订单id获取订单
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderMainModel getOrderMainModelByOrderId(String orderId);


	/**
	 * 通过订单编号获得这个主订单对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainModel getOrderMainByUuid(String orderUuid);

	/**
	 * 订单查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm);


	/**
	 * 创建生成订单号
	 * 
	 * @return
	 */
	public String createOrderNo();

	/**
	 * 按照分账日期查询对应家政员所有的订单
	 * 
	 * @param serviceStaffUuid
	 * @param routTime
	 * @return
	 */
	public List<OrderMainModel> getOrderListByServiceStaffUuid(
			String serviceStaffUuid, String routTime);

	/**
	 * 根据家政员id和分账时间获取相关分账数据总额
	 * 
	 * @param strName
	 *            要查询的字段
	 * @param serviceStaffUuid
	 * @param routTime
	 * @return
	 */
	public double getTotalMoneyByStaffId(String strName,
			String serviceStaffUuid, String routTime);

	/**
	 * 统计订单某个月份收入管理
	 * 
	 * @param dateTime
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	public List<Object> getOrderListByReceiveTime(String ReceiveTime,
			int pageCount, int pageNo);

	/**
	 * 查询某一个月所有已完成的订单
	 * 
	 * @param receiveTime
	 * @return
	 */
	public int getCountOrderListByReceiveTime(String receiveTime);

	/**
	 * 根据确认时间查询每天的支付宝支付或者微信支付的金额总和
	 * 
	 * @param receiveTime
	 * @param payType
	 * @return
	 */
	public double getPayMoneyByReceiveTime(String receiveTime, String payType);

	/**
	 * 获取某一段时间的已完成订单收入金额总和
	 * 
	 * @param receiveTime
	 * @return
	 */
	public double getPaidMoneyByReceiveTime(String receiveTime);

	/**
	 * 根据订单uuid获取id
	 * 
	 * @param uuid
	 * @return
	 */
	public String getOrderIdByUuid(String uuid);

	/**
	 * 根据会员id和订单类型获取所有订单
	 * 
	 * @param OrderMainQueryModel
	 * @param state
	 *            订单状态
	 * @return
	 */
	public List<OrderMainModel> getOrderListByCustomerId(
			OrderMainQueryModel qm, String customerId, String state,
			int pageCount, int pageNo);

	/**
	 * 根据会员id和订单类型查询总数量
	 * 
	 * @param OrderMainQueryModel
	 * @param state
	 *            订单状态
	 * @return
	 */
	public int getOrderCountByCustomerId(OrderMainQueryModel qm,
			String customerId, String state);

	/**
	 * 查詢訂單的對應數量
	 * 
	 * @param serachType
	 * @return
	 */

	public int getOrderCount(String serachType);

	/**
	 * 根据会员ID查询评价列表
	 * 
	 * @param customerUuid
	 *            会员ID
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	public List<OrderMainModel> getOrdermainCommentList(String customerUuid,
			int pageCount, int pageNo);


	/**
	 * 查询当前年的接单总额
	 * 
	 * @param serviceStaffUuid
	 * @return
	 * @2015-3-31
	 * @author :JoeyShee
	 */
	public int getReceiveOrderYearSum(String serviceStaffUuid);

	/**
	 * 查询当前月的接单总额
	 * 
	 * @param serviceStaffUuid
	 * @return
	 * @2015-3-31
	 * @author :JoeyShee
	 */
	public int getReceiveOrderMonthSum(String serviceStaffUuid);

	/**
	 * 获得订单的数量根据订单对应的状态
	 * 
	 * @param staffUuid
	 * @param state
	 * @param commentState
	 * @return
	 */
	public int getCountOrderNum(String staffUuid, String state,
			String commentState);


	/**
	 * 根据订单组编号获取子订单集合
	 * 
	 * @param orderGroupUuid
	 * @return
	 * @author zdx
	 */
	public List<OrderMainModel> getByOrderGroupUuid(String orderGroupUuid);

	/**
	 * 根据订单金额计算需要多少家政员
	 * 
	 * @param orderAmount
	 * @return
	 */
	public int getOrderNeedNumbersByOrderAmount(double orderAmount);
	/**
	 * 返回错误信息调用的组装jsonMap
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object, Object> getFailJsonMap(String code, String message);

	/**
	 * 根据会员ID获取已完成订单信息
	 * 
	 * @param customerUuid
	 *            会员ID
	 * @param pageCount
	 *            分页
	 * @param pageNo
	 *            页码
	 * @return
	 */
	public List<OrderMainModel> getOrderList(String orderType,
			String customerUuid, int pageCount, int pageNo);

	public List<OrderMainModel> getOrderList(String customerUuid,
			int pageCount, int pageNo);

	/**
	 * 获取电话咨询的数量
	 * 
	 * @param doctorId
	 * @return
	 */
	public int getTotalTelNumByDoctorId(String doctorId);
	/**
	 * 根据患者的id和订单的状态查询患者所有的订单
	 * @param state
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
     public List<OrderMainModel> getCustomerOrderList(String state, String customerUuid, int pageCount, int pageNo,String orderType);
     
 	/**
 	 * 根据患者的id和订单的状态查询患者所有的订单
 	 * @param state
 	 * @param customerUuid
 	 * @param pageCount
 	 * @param pageNo
 	 * @return
 	 */
      public List<OrderMainModel> getOrderList(String state, String doctorUuid, int pageCount, int pageNo,String orderType);
      
  	/**
   	 * 根据患者的id和订单的状态查询患者所有的编号
   	 * add by  xl 20160311
   	 * @param state
   	 * @param customerUuid
   	 * @param pageCount
   	 * @param pageNo
   	 * @return
   	 */
     public List<String> getCustomerUuids(String state, String doctorUuid, int pageCount, int pageNo,String orderType);

	/**
	 * 获取医生id
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	public String getDoctorUuidByUuid(String orderMainUuid);

	/**
	 * 获取患者id
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	public String getCustomerUuidByUuid(String orderMainUuid);
	
	/**
	 * 通过会议Id获取订单对象
	 * by add xl 20151225
	 * @param confId
	 * @return
	 */
	public OrderMainModel getOrderMainByConfId(String confId);

	/**
	 * 根据医生编号 通话时长、预约日期 
	 * by add xl 20160115
	 * @param doctorUuid
	 * @param consultDuration
	 * @param bookTime
	 * @return
	 */
	public List<OrderMainModel> getOrderMains(String doctorUuid,String consultDuration,String bookTime);
	
	/**
	 * 获取可以分账的订单信息
	 * by add xl 20160128
	 * @return
	 */
	public List<OrderMainModel> getCanFzOrders();
}
