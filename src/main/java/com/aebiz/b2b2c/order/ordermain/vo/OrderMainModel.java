package com.aebiz.b2b2c.order.ordermain.vo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermaincomment.service.OrderMainCommentService;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentModel;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.ordermainlog.vo.OrderMainLogModel;
import com.aebiz.b2b2c.order.ordermainpay.service.OrderMainPayService;
import com.aebiz.b2b2c.order.ordermainpay.vo.OrderMainPayModel;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
/**
 * 记录订单主信息 
 * @author xueli
 *
 */
@Entity
@Table(name = "order_main")
@Component
public class OrderMainModel extends BaseModel {
	
	/*订单支付信息表 service*/
	@Transient
	private static OrderMainPayService orderMainPayService;

	@Autowired
	public void setOrderMainPayService(OrderMainPayService bs) {
		this.orderMainPayService = bs;
	}
	
	/*订单分账service*/
	@Transient
	private static OrderRoutingService orderroutingService;
	
	@Autowired
	public void setOrderroutingService(OrderRoutingService os) {
		this.orderroutingService = os;
	}
	
	/*订单详情service*/
	@Transient
	private static OrderDetailService orderDetailService ;
	@Autowired
	public  void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}
	
	/*患者信息 service*/
	@Transient
	private static CustomerInteractive  customerInteractive;
	@Autowired
	public void setCustomerInteractive(CustomerInteractive  customerInteractive) {
		this.customerInteractive = customerInteractive;
	}
	
	/*订单日志service */
	@Transient
	private static OrderMainLogService logService;
	@Autowired
	public void setLogService(OrderMainLogService logService) {
		this.logService = logService;
	}
	
	/*套餐service*/
	@Transient
	private static PackageManagementService packageServie;
	
	@Autowired
	public void setPackageServie(PackageManagementService packageServie) {
		this.packageServie = packageServie;
	}
	
	/**
	 * 会员中心订单放入回收站的状态
	 */
	public static final String DEL_FLAG_RECYCLER = "0";
	
	/*分账状态 0：未分账 1：已分账 */
	public static final String  ACCOUNTSTATE_UNFZ = "0";
	
	public static final String  ACCOUNTSTATE_HSFZ = "1";
	
	/**
	 * 订单支付成功
	 */
	public static final String ORDER_PAY_SUCCESS = "ca72f4c70ca";
	/**
	 * 订单支付失败
	 */
	public static final String ORDER_PAY_FAIL = "87cd314ew1";
	/**
	 * 支付回调验证失败
	 */
	public static final String ORDER_PAY_VALIDATA_FAIL = "3ebd59a27a";

	/* 订单组编号 */
	private String orderGroupUuid;

	/* 订单类型 1:电话咨询订单 2：私人套餐*/
	private String orderType;

	/* 客户编号 */
	private String customerUuid;

	/* 会员名称 */
	private String customerName;
	
	//医生uuid
	private String doctorUuid;
    
	//套餐编号
	private String packageUuid;
	
	/*订单时间 */
	private String orderTime;
	
	/*预约时间 电话咨询的日期 如：2015-12-22*/
	private String bookTime;
	
	/*开始时间  电话咨询预约开始时间 如：18:00*/
	private String receiveTime;
	
	/*结束时间  电话咨询预约结束时间 如：18:15*/
	private String endTime;
	
	/*咨询时长 通话时长 15分钟 */
	private String consultDuration;
	
	/*咨询内容 */
	private String consultContent;
	
	/*总金额 */
	private double totalMoney;//咨询金额
	
	/*套餐结束时间 -医生的私人套餐 */
	private String sendTime = "";
	
	/*审核类型 1:平台审核 2：医生审核*/
	private String checkType;
	
	/* 运费 */
	private double affixation;

	/* 优惠金额 */
	private double freeMoney;

	/* 应付总金额 */
	private double payMoney;
	
	/*已付金额*/
	private double paid;

	/* 订单号 */
	private String orderId;

	/* 订单状态 */
	private String state;

	/* 退款状态    0:应退款 1：已退款  2:退款失败*/
	private String refoundState;
	
	/* 退款申请的时间*/
	private String refundApplyTime;

	/*订单取消时间*/
	private String cancelOrderTime;

	/* 分账状态 */
	private String accountState;

	
	/* 评价状态 已评价 1 */
	private String commentState;

	/* 备注,拒绝理由 */
	private String note;

	/* 优惠说明 */
	private String freeDescription;

	/* 支付方式 0:线上支付 1：线下支付 */
	private String payType;

	/* 电话咨询创建的会议ID confId */
	private String orgId;

	/* 可获得积分数 */
	private int presented = 0;

	/* 尾款截至支付时间，当下订单后，尾款的支付时间就确定了，计算未付款自动关闭就按这个时间计算 */
	private String lastPayTime = "";
	
	/* 积分支付 */
	private  double  integralMoney;
	
	/* 优惠券支付 */
	private  double  couponMoney;
	
	/* 优惠券券号 */
	private  String  couponNo;
	
	/* 礼品卡支付 */
	private  String  giftCardMoney="0";
	
	/*礼品卡支金额*/
	@Transient
	private double giftcm=0;
	
	/* 礼品卡卡号 */
	private  String  giftCardNo;
	
	/* 余额支付*/
	private  double  balanceMoney;
	
	/*订单来源类型 0:手机订单*/
	private String orderFromType;
	
	/*订单动态状态  0：不需要 1：需要*/
	private String urgencyStatus;
	
	/*会员联系电话*/
	@Transient
	private String customerMobile;

	@Transient
	private String orderStatusName;

	@Transient
	private String orderTypeName = "";

	@Transient
	private String payTypeName = "";

	@Transient
	private List<OrderDetailModel> detailList;

	/* 订单标记 */
	@Transient
	private OrderStampModel orderStamp;

	/* 订单过期时间，如果订单在规定时间内不支付，则自动关闭，overdueDay 从资源束中获取，先固定测试 */
	@Transient
	private int overdueDay = 2;
	
	/*订单的投诉状态 :"":订单未接到投诉 0：订单接到投诉未处理 1:订单投诉已处理 */
	private String orderComplain;
	
	/*订单的退款原因 : */
	private String refundReson;
	
	/*订单的退款操作时间 : */
	private String operateTime;
	
	/*订单的退款金额 : */
	private double refundMoney=0;
	
	/*在线支付状态*/
	private String payStatus;
	
	/*分账信息 */
	@Transient 
	List<OrderRoutingModel>  orderroutings = null;
	
	/*回访状态（订单回访状态）空：未回访      0：已回访*/
	private String revisitState;
	
	//退款审核意见
	private String refundAuditOpinion;
	
	/*日志表*/
	@Transient
	private OrderMainLogModel logmodel; 
	
	/*医生名*/
	@Transient
	private String doctorName="";
	
	/*医院名*/
	@Transient
	private String hospitalName="";
	
	/*页面咨询内容展示字段 不超过15个字*/
	@Transient
	private String content;
	
	/*套餐名称*/
	@Transient
	private String packgeName="";
	/*订单描述*/
	private String orderDescription="";
	
	public String getOrderDescription() {
        return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPackageUuid() {
		return packageUuid;
	}

	public void setPackageUuid(String packageUuid) {
		this.packageUuid = packageUuid;
	}
	
	public String getPackgeName() {
		if(!StringUtil.isEmpty(this.getPackageUuid())){
			return packageServie.getPackageName(packageUuid);
		}
		return packgeName;
	}

	public void setPackgeName(String packgeName) {
		this.packgeName = packgeName;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getContent() {
		if(!StringUtil.isEmpty(consultContent)){
			if(consultContent.length()>15){
				return consultContent.substring(0, 15);
			}else{
				return consultContent;
			}
		}
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getConsultDuration() {
		return consultDuration;
	}

	public void setConsultDuration(String consultDuration) {
		this.consultDuration = consultDuration;
	}

	public String getConsultContent() {
		return consultContent;
	}

	public void setConsultContent(String consultContent) {
		this.consultContent = consultContent;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getRefundApplyTime() {
		return refundApplyTime;
	}

	public void setRefundApplyTime(String refundApplyTime) {
		this.refundApplyTime = refundApplyTime;
	}
	
	public String getCancelOrderTime() {
		return cancelOrderTime;
	}

	public void setCancelOrderTime(String cancelOrderTime) {
		this.cancelOrderTime = cancelOrderTime;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}
	
	
	public OrderMainLogModel getLogmodel() {
		List list = logService.getOrderMainLogModelListByOrderId(this.getUuid(), "2");
		if(list !=null && list.size()>0){
			logmodel = (OrderMainLogModel) list.get(0);
		}
		return logmodel;
	}

	public void setLogmodel(OrderMainLogModel logmodel) {
		this.logmodel = logmodel;
	}

	public String getDoctorUuid() {
		return doctorUuid;
	}
	
	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}
	
	public double getGiftcm() {
		if(!StringUtil.isEmpty(this.giftCardMoney)){
			giftcm = Double.parseDouble(this.giftCardMoney);
		}
		return giftcm;
	}

	public void setGiftcm(double giftcm) {
		this.giftcm = giftcm;
	}

	public String getRefundAuditOpinion() {
		return refundAuditOpinion;
	}

	public void setRefundAuditOpinion(String refundAuditOpinion) {
		this.refundAuditOpinion = refundAuditOpinion;
	}

	public String getRevisitState() {
		return revisitState;
	}

	public void setRevisitState(String revisitState) {
		this.revisitState = revisitState;
	}

	public List<OrderRoutingModel> getOrderroutings() {
		List<OrderRoutingModel> routingList = orderroutingService.searchAllRoutings(this.getUuid());
		if(routingList != null && routingList.size()>0){
			return routingList ;
		}
		return orderroutings;
	}

	public void setOrderroutings(List<OrderRoutingModel> orderroutings) {
		this.orderroutings = orderroutings;
	}

	public double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getRefundReson() {
		return refundReson;
	}

	public void setRefundReson(String refundReson) {
		this.refundReson = refundReson;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOrderComplain() {
		return orderComplain;
	}

	public void setOrderComplain(String orderComplain) {
		this.orderComplain = orderComplain;
	}
	/*订单的评价*/
	@Transient
	private OrderMainCommentModel orderMainCommentModel;
	
	/**
	 * 订单的所有评价
	 */
	@Transient
	private List<OrderMainCommentModel> commentList;
	
	
	/*订单评价service*/
	@Transient
	private static OrderMainCommentService orderMainCommentService ;
	
	@Autowired
	public void setOrderMainCommentService(OrderMainCommentService omcs) {
		this.orderMainCommentService = omcs;
	}
	public OrderMainCommentModel getOrderMainCommentModel() {
		List<OrderMainCommentModel> list = orderMainCommentService.getCommentByOrderId(this.getUuid());
		if(list.size()>0&&list!=null)
		{
			return list.get(0);
		}
		return null;
	}

	public void setOrderMainCommentModel(OrderMainCommentModel orderMainCommentModel) {
		this.orderMainCommentModel = orderMainCommentModel;
	}
	
	public List<OrderMainCommentModel> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<OrderMainCommentModel> commentList) {
		this.commentList = commentList;
	}
	
	public String getCustomerMobile() {
		
		return customerInteractive.getCustomerMobileByUuid(this.customerUuid);
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public void setOrderGroupUuid(String obj) {
		this.orderGroupUuid = obj;
	}

	public String getOrderGroupUuid() {
		return this.orderGroupUuid;
	}

	public void setOrderType(String obj) {
		this.orderType = obj;
	}

	public String getOrderType() {
		return this.orderType;
	}


	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setOrderTime(String obj) {
		this.orderTime = obj;
	}

	public String getOrderTime() {
		return this.orderTime;
	}

	public void setTotalMoney(double obj) {
		this.totalMoney = obj;
	}

	public double getTotalMoney() {
		return this.totalMoney;
	}

	public void setAffixation(double obj) {
		this.affixation = obj;
	}

	public double getAffixation() {
		return this.affixation;
	}

	public void setFreeMoney(double obj) {
		this.freeMoney = obj;
	}

	public double getFreeMoney() {
		return this.freeMoney;
	}

	public void setPayMoney(double obj) {
		this.payMoney = obj;
	}

	public double getPayMoney() {
		return this.payMoney;
	}

	public void setOrderId(String obj) {
		this.orderId = obj;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}


	public void setRefoundState(String obj) {
		this.refoundState = obj;
	}

	public String getRefoundState() {
		return this.refoundState;
	}

	public void setAccountState(String obj) {
		this.accountState = obj;
	}

	public String getAccountState() {
		return this.accountState;
	}

	public void setCommentState(String obj) {
		this.commentState = obj;
	}

	public String getCommentState() {
		return this.commentState;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setFreeDescription(String obj) {
		this.freeDescription = obj;
	}

	public String getFreeDescription() {
		return this.freeDescription;
	}

	public void setPayType(String obj) {
		this.payType = obj;
	}

	public String getPayType() {
		return this.payType;
	}



	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderStatusName() {
		return OrderStatusEnum.getNameByKey(this.getState());
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getOrderTypeName() {
		return OrderTypeEnum.getNameByKey(this.getOrderType());
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getPayTypeName() {
		return OrderPayTypeEnum.getNameByKey(this.getPayType());
	}



	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public List<OrderDetailModel> getDetailList() {
		return orderDetailService.getOrderDetailModelByOrderId(this.getUuid());
	}

	public void setDetailList(List<OrderDetailModel> detailList) {
		this.detailList = detailList;
	}

	public double getAdvancePay() {
		// 如果是预售订单
		if (OrderTypeEnum.PRESELLTYPE.getValue().equals(this.getOrderType())) {
			OrderMainPayModel omp = orderMainPayService
					.getAdvanceOrderMainPayModel(this.getUuid());
			if (omp != null) {
				return omp.getPayMoney();
			}
		}
		return 0;
	}

	public int getOverdueDay() {
		return overdueDay;
	}

	public void setOverdueDay(int overdueDay) {
		this.overdueDay = overdueDay;
	}

	public int getPresented() {
		return presented;
	}

	public void setPresented(int presented) {
		this.presented = presented;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public OrderStampModel getOrderStamp() {
		return orderStamp;
	}

	public void setOrderStamp(OrderStampModel orderStamp) {
		this.orderStamp = orderStamp;
	}

	public String getLastPayTime() {
		return lastPayTime;
	}

	public void setLastPayTime(String lastPayTime) {
		this.lastPayTime = lastPayTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	

	
	public double getIntegralMoney() {
		return integralMoney;
	}

	public void setIntegralMoney(double integralMoney) {
		this.integralMoney = integralMoney;
	}

	public double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(double couponMoney) {
		this.couponMoney = couponMoney;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getGiftCardMoney() {
		return giftCardMoney;
	}

	public void setGiftCardMoney(String giftCardMoney) {
		this.giftCardMoney = giftCardMoney;
	}

	public String getGiftCardNo() {
		return giftCardNo;
	}

	public void setGiftCardNo(String giftCardNo) {
		this.giftCardNo = giftCardNo;
	}

	public double getBalanceMoney() {
		return balanceMoney;
	}

	public void setBalanceMoney(double balanceMoney) {
		this.balanceMoney = balanceMoney;
	}
	
	public String getOrderFromType() {
		return orderFromType;
	}

	public void setOrderFromType(String orderFromType) {
		this.orderFromType = orderFromType;
	}

	public String getUrgencyStatus() {
		return urgencyStatus;
	}

	public void setUrgencyStatus(String urgencyStatus) {
		this.urgencyStatus = urgencyStatus;
	}
	
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[orderGroupUuid=" + this.getOrderGroupUuid() + ",orderType="
				+ this.getOrderType() 
				+ ",customerUuid=" + this.getCustomerUuid() + ",orderTime="
				+ this.getOrderTime() + ",totalMoney=" + this.getTotalMoney()
				+ ",affixation=" + this.getAffixation() + ",freeMoney="
				+ this.getFreeMoney() + ",payMoney=" + this.getPayMoney()
				+ ",orderId=" + this.getOrderId() + ",state=" + this.getState()
				+ this.getRefoundState() + ",accountState="
				+ this.getAccountState() + ",commentState="
				+ this.getCommentState() + ",note=" + this.getNote()
				+ ",freeDescription=" + this.getFreeDescription() + ",payType="
				+ this.getPayType() 
				+ ",]";
	}

}
