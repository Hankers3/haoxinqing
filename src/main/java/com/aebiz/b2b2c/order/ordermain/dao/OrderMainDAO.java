package com.aebiz.b2b2c.order.ordermain.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.order.ordermain.vo.IncomeModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;

public interface OrderMainDAO extends
		BaseDAO<OrderMainModel, OrderMainQueryModel> {

	/**
	 * 根据用户id来获取其订单列表
	 * 
	 * @param customerUuid
	 * @return
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
	 * 通过主订单编号获取客户姓名 hedongfei
	 * 
	 * @param uuid
	 * @return
	 */
	public String getOrderMaincustomerName(String uuid);

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
	 * 根据id修改投诉状态(修改为1) 注：投诉状态默认为空 hedongfei
	 * 
	 * @param
	 */
	public void updateOrderComplain(String uuid, String updateStype);

	/**
	 * 获得是否存在有紧急需要处理的订单
	 * 
	 * @return
	 */
	public boolean isUrgencyOrder();

	/**
	 * 按照分账日期查询对应家政员所有的订单
	 * 
	 * @param serviceStaffUuid
	 *            家政员id
	 * @param routTime
	 *            分账日期
	 * @return
	 */
	public List<OrderMainModel> getOrderListByServiceStaffUuid(
			String serviceStaffUuid, String routTime);

	/**
	 * 根据家政员id和分账时间获取相关分账数据总额
	 * 
	 * @param strName
	 *            要查询的字段名
	 * @param serviceStaffUuid
	 * @param routTime
	 * @return
	 */
	public double getTotalMoneyByStaffId(String strName,
			String serviceStaffUuid, String routTime);

	/**
	 * 得到订单成功单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int getCountOrdersaboutSuccess(String receiveTime, String searchType);

	/**
	 * 得到今日上门订单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int getCountOrdersaboutdoorin(String doorinEndTime, String searchType);

	/**
	 * 得到今日取消订单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int getCountOrdersaboutclose(String cancelOrderTime,
			String searchType);

	/**
	 * 得到今日申请退款订单数 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int getCountOrdersaboutRefund(String refundApplyTime,
			String searchType);

	/**
	 * 得到今日上门人次 hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int getCountOrdersaboutdoorinpeople(String doorinEndTime,
			String searchType);

	/**
	 * 统计订单某个月份收入管理
	 * 
	 * @param dateTime
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	public List<Object> getOrderListByReceiveTime(String receiveTime,
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
	 * 获取订单总额,积分使用金额,优惠券金额,-折扣金额,实际收入,支付宝支付,微信支付,其他支付金额 hedongfei 注：time指完成时间
	 * 
	 * @param
	 * @return
	 */
	public Object[] getMangKindsOfMoney(String receiveTime, String searchType);

	/**
	 * 按时间得到规定时间内的成功订单的uuid hedongfei
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public List getuuidsOrdersaboutSuccess(String receiveTime, String searchType);

	/**
	 * 获得紧急需要处理的订单数 hedongfei
	 * 
	 * @return
	 */
	public int urgencyOrders();

	/**
	 * 获取某一段时间的上门订单的总额 hedongfei
	 * 
	 * @param doorinEndTime
	 * @return
	 */
	public double getPaidMoneyBydoorinEndTime(String doorinEndTime,
			String searchType);

	/**
	 * 获取某一时间内申请退款金额 hedongfei
	 * 
	 * @param refundApplyTime
	 * @return
	 */
	public double getPaidMoneyByapply(String refundApplyTime, String searchType);

	/**
	 * 获取某一时间内取消订单金额 hedongfei
	 * 
	 * @param refundApplyTime
	 * @return
	 */
	public double getPaidMoneyBycancel(String cancelOrderTime, String searchType);

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
	 * @param customerId
	 *            会员id
	 * @param state
	 *            订单状态
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	public List<OrderMainModel> getOrderListByCustomerId(
			OrderMainQueryModel qm, String customerId, String state,
			int pageCount, int pageNo);

	/**
	 * 根据会员id和订单类型查询总数量
	 * 
	 * @param customerUuid
	 *            会员id
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
	public List<OrderMainModel> getOrderListByCustomerUuid(String customerUuid,
			int pageCount, int pageNo);

	/**
	 * 根据家政员ID和时间查询数量
	 * 
	 * @param serviceStaffUuid
	 * @param receiveTime
	 *            接单时间
	 * @param receiveStatus
	 *            接单拒单状态 0为拒单1为接单2为未接单 3为接单失败
	 * @return
	 * @2015-3-31
	 * @author :SZH
	 */
	public int getReceiveOrderCount(String serviceStaffUuid, String curTime,
			String receiveTime, String receiveStatus);

	/**
	 * 根据家政员ID和时间查询接单总额
	 * 
	 * @param serviceStaffUuid
	 * @param receiveTime
	 *            接单时间
	 * @param receiveStatus
	 *            接单拒单状态 0为拒单1为接单2为未接单 3为接单失败
	 * @return
	 * @2015-3-31
	 * @author :SZH
	 */
	public int getReceiveOrderSum(String serviceStaffUuid, String curTime,
			String receiveTime, String receiveStatus);

	/**
	 * 根据家政员ID和时间接单状态查询数量
	 * 
	 * @param serviceStaffUuid
	 * @param receiveTime
	 * @return
	 * @2015-3-31
	 * @author :SZH
	 */
	public int getServiceStaffOrderCount(String serviceStaffUuid,
			String curTime, String receiveTime);

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
	 * 获取家政员订单
	 * 
	 * @param serviceStaffUuid
	 *            家政员编号
	 * @return
	 * @2015-4-9
	 * @author :SZH
	 */
	public List<OrderMainModel> getOrderMainModelByserviceStaffUuid(
			String serviceStaffUuid, int pageCount, int pageNo);

	/**
	 * 取消未支付订单在服务前两个小时 设为无效订单
	 * 
	 * @param state
	 * @author :xl
	 */
	public void CloseOrderWZF(String state, String payType);

	/**
	 * 关闭未处理订单
	 * 
	 * @param serviceTime
	 */
	public void CloseOrderWCL(String serviceTime);

	/**
	 * 确认订单
	 * 
	 * @author :xl
	 */
	public void orderAffirm();

	/**
	 * 根据订单组编号获取子订单集合
	 * 
	 * @param orderGroupUuid
	 * @return
	 * @author zdx
	 */
	public List<OrderMainModel> getByOrderGroupUuid(String orderGroupUuid);

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
	public List<OrderMainModel> getOrderList(String customerUuid,
			int pageCount, int pageNo);

	public List<OrderMainModel> getOrderList(String orderType,
			String customerUuid, int pageCount, int pageNo);

	/**
	 * 获取医生电话咨询的数量
	 */
	public int getTotalTelNumByDoctorId(String doctorId);

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
	 * 根据患者的id和订单的状态查询患者所有的订单
	 * 
	 * @param state
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	public List<OrderMainModel> getCustomerOrderList(String state,
			String customerUuid, int pageCount, int pageNo,String orderType);
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
 	 * @param state
 	 * @param customerUuid
 	 * @param pageCount
 	 * @param pageNo
 	 * @return
 	 */
     public List<String> getCustomerUuids(String state, String doctorUuid, int pageCount, int pageNo,String orderType);
      
      
    /**
	 * 通过会议Id获取订单对象
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