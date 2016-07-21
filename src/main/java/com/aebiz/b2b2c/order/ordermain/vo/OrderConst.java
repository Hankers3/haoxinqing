package com.aebiz.b2b2c.order.ordermain.vo;

/**
 * 
 * 订单系统需要在运营系统中维护的参数的常量
 * 
 * 1.如果在运营系统中需要调去很多系统参数，需要在这里定义参数来统一管理<br />
 * 
 * @author duandeyi
 * 
 */
public class OrderConst {

	/**
	 * 订单超时未支付关闭天数
	 * 
	 * 当订单下单成功后，超过一定时间未支付，需要自动关闭
	 * 
	 */
	public static final String OVERTIME_NOPAY_CLOSE_DAY = "overtime.close.day";

	/**
	 * 订单超时未确认收货，自动确认收货
	 * 
	 * 当卖家发货后，买家超过一定时间未确认收货，则系统自动到货
	 * 
	 */
	public static final String AUTO_CONFIRM_RECEIVE_DAY = "auto.confirm.receive.day";

	/**
	 * 
	 * 订单确认成功后，超过一定时间未评价，系统自动默认好评
	 * 
	 */
	public static final String AUTO_APPRAISE_DAY = "auto.appraise.day";

	/**
	 * 会员取消订单原因
	 * 
	 * 会员取消订单时，需要选择取消原因
	 * 
	 * 取消原因由运营系统来维护
	 * 
	 */
	public static final String USER_CANCEL_ORDER_REASON = "user.cancel.order.reason";

	/**
	 * 商户取消订单原因
	 * 
	 * 商户取消订单时，需要选择取消原因
	 * 
	 * 取消原因由运营系统来维护
	 * 
	 */
	public static final String STORE_CANCEL_ORDER_REASON = "store.cancel.order.reason";
	
	

	// 1：待支付 （待处理订单）
	public static final String ORDERSTATUS_DFK = "1";

	// 2：取消状态
	public static final String ORDERSTATUS_QX = "2";

	// 3：已支付（待确认）
	public static final String ORDERSTATUS_YZF = "3";

	// 4：已确认（待上门）
	public static final String ORDERSTATUS_YQR = "4";

	// 5：上门中
	public static final String ORDERSTATUS_SMZ = "5";

	//6：上门完成
	public static final String ORDERSTATUS_SMWC = "6";

	//7：丈量
	public static final String ORDERSTATUS_ZL = "7";

	// 8：工作中
	public static final String ORDERSTATUS_GZZ = "8";

	// 9：工作完成
	public static final String ORDERSTATUS_WC = "9";
	
	//10：代付完成（客户待确认订单完成）
	public static final String ORDERSTATUS_DFWC = "10";
	
	//11：确认订单完成
	public static final String ORDERSTATUS_QRWC = "11";
	
	//12:订单无效
	public static final String ORDERSTATUS_INVALID = "12";
	

	/*日志订单展示使用   new add xl 20150512 */
	//13:申请退款
	public static final String ORDERSTATUS_SQTK = "13";
		
	//14:退款成功
	public static final String ORDERSTATUS_TKCG = "14";
	
	//15:退款失败
	public static final String ORDERSTATUS_TKSB = "15";
	
	
}
