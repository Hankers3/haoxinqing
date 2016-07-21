package com.hxq.mobile.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;

/**
 * 记录订单主信息
 */
@SuppressWarnings("serial")
@Entity(name="order_main")
public class OrderMain extends AbstractEntity<String> {
	/**
	 * 会员中心订单放入回收站的状态
	 */
	@Transient
	public static final String DEL_FLAG_RECYCLER = "0";
	/**
	 * 分账状态 0：未分账 1：已分账 
	 */
	@Transient
	public static final String  ACCOUNTSTATE_UNFZ = "0";
	@Transient
	public static final String  ACCOUNTSTATE_HSFZ = "1";
	/**
	 * 订单支付成功
	 */
	@Transient
	public static final String ORDER_PAY_SUCCESS = "ca72f4c70ca";
	/**
	 * 订单支付失败
	 */
	@Transient
	public static final String ORDER_PAY_FAIL = "87cd314ew1";
	/**
	 * 支付回调验证失败
	 */
	@Transient
	public static final String ORDER_PAY_VALIDATA_FAIL = "3ebd59a27a";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	/* 分账状态 */
	public String accountState;
	/* 运费 */
	@Column(columnDefinition="default 0")
	public Double affixation;
	/* 评价状态 已评价 1 */
	public String commentState;
	/* 会员名称 */
	public String customerName;
	/* 客户编号 */
	public String customerUuid;
	/* 优惠金额 */
	@Column(columnDefinition="default 0")
	public Double freeMoney;
	/* 尾款截至支付时间，当下订单后，尾款的支付时间就确定了，计算未付款自动关闭就按这个时间计算 */
	public String lastPayTime;
	/* 备注,拒绝理由 */
	public String note;
	/* 订单组编号 */
	public String orderGroupUuid;
	/* 订单号 */
	public String orderId;
	/*订单时间 */
	public String orderTime;
	/* 订单类型 1:电话咨询订单 2：私人套餐*/
	public String orderType;
	/* 电话咨询创建的会议ID confId */
	public String orgId;
	/* 应付总金额 */
	public Double payMoney;
	/* 支付方式 0:线上支付 1：线下支付 */
	public String payType;
	/* 可获得积分数 */
	@Column(columnDefinition="default 0")
	public Integer presented;
	/*开始时间  电话咨询预约开始时间 如：18:00*/
	public String receiveTime;
	/* 退款状态    0:应退款 1：已退款  2:退款失败*/
	public String refoundState;
	/*套餐结束时间 -医生的私人套餐 */
	public String sendTime;
	/* 订单状态 */
	public String state;
	/* 余额支付*/
	@Column(columnDefinition="default 0")
	public Double balanceMoney;
	/* 优惠券支付 */
	@Column(columnDefinition="default 0")
	public Double couponMoney;
	/* 优惠券券号 */
	public String couponNo;
	/* 优惠说明 */
	public String freeDescription;
	/* 礼品卡支付 */
	@Column(columnDefinition="default 0")
	public Double giftCardMoney;
	/* 礼品卡卡号 */
	public String giftCardNo;
	/* 积分支付 */
	@Column(columnDefinition="default 0")
	public Double integralMoney;
	/* 订单的退款操作时间 */
	public String operateTime;
	/* 订单的投诉状态 :"":订单未接到投诉 0：订单接到投诉未处理 1:订单投诉已处理 */
	public String orderComplain;
	/* 订单来源类型 0:手机订单 */
	public String orderFromType;
	/* 订单的退款原因 */
	public String refundReson;
	/* 总金额 */
	public Double totalMoney;//咨询金额
	/* 订单动态状态  0：不需要 1：需要 */
	public String urgencyStatus;
	/* 已付金额 */
	@Column(columnDefinition="default 0")
	public Double paid;
	/* 订单的退款金额 */
	@Column(columnDefinition="default 0")
	public Double refundMoney;
	/* 订单取消时间 */
	public String cancelOrderTime;
	/* 在线支付状态 */
	public String payStatus;
	/* 退款申请的时间 */
	public String refundApplyTime;
	/* 退款审核意见 */
	public String refundAuditOpinion;
	/* 回访状态（订单回访状态）空：未回访      0：已回访 */
	public String revisitState;
	/* 医生uuid */
	public String doctorUuid;
	/* 预约时间 电话咨询的日期 如：2015-12-22 */
	public String bookTime;
	/* 咨询时长 通话时长 15分钟 */
	public String consultDuration;
	/* 咨询内容 */
	public String consultContent;
	/* 订单审核状态 */
	public String checkState;
	/* 审核类型 1:平台审核 2：医生审核 */
	public String checkType;
	/* 套餐编号 */
	public String packageUuid;
	public String plusNote;
	/* 结束时间  电话咨询预约结束时间 如：18:15 */
	public String endTime;
	/* 订单描述 */
	public String orderDescription;

	public OrderMain() {super();}
	public OrderMain(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {return uuid;}
	@Override
	public void setId(String id) {this.uuid = id;}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAccountState() {
		return accountState;
	}
	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}
	public Double getAffixation() {
		return affixation;
	}
	public void setAffixation(Double affixation) {
		this.affixation = affixation;
	}
	public String getCommentState() {
		return commentState;
	}
	public void setCommentState(String commentState) {
		this.commentState = commentState;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public Double getFreeMoney() {
		return freeMoney;
	}
	public void setFreeMoney(Double freeMoney) {
		this.freeMoney = freeMoney;
	}
	public String getLastPayTime() {
		return lastPayTime;
	}
	public void setLastPayTime(String lastPayTime) {
		this.lastPayTime = lastPayTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrderGroupUuid() {
		return orderGroupUuid;
	}
	public void setOrderGroupUuid(String orderGroupUuid) {
		this.orderGroupUuid = orderGroupUuid;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Integer getPresented() {
		return presented;
	}
	public void setPresented(Integer presented) {
		this.presented = presented;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getRefoundState() {
		return refoundState;
	}
	public void setRefoundState(String refoundState) {
		this.refoundState = refoundState;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getBalanceMoney() {
		return balanceMoney;
	}
	public void setBalanceMoney(Double balanceMoney) {
		this.balanceMoney = balanceMoney;
	}
	public Double getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public String getFreeDescription() {
		return freeDescription;
	}
	public void setFreeDescription(String freeDescription) {
		this.freeDescription = freeDescription;
	}
	public Double getGiftCardMoney() {
		return giftCardMoney;
	}
	public void setGiftCardMoney(Double giftCardMoney) {
		this.giftCardMoney = giftCardMoney;
	}
	public String getGiftCardNo() {
		return giftCardNo;
	}
	public void setGiftCardNo(String giftCardNo) {
		this.giftCardNo = giftCardNo;
	}
	public Double getIntegralMoney() {
		return integralMoney;
	}
	public void setIntegralMoney(Double integralMoney) {
		this.integralMoney = integralMoney;
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
	public String getOrderFromType() {
		return orderFromType;
	}
	public void setOrderFromType(String orderFromType) {
		this.orderFromType = orderFromType;
	}
	public String getRefundReson() {
		return refundReson;
	}
	public void setRefundReson(String refundReson) {
		this.refundReson = refundReson;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getUrgencyStatus() {
		return urgencyStatus;
	}
	public void setUrgencyStatus(String urgencyStatus) {
		this.urgencyStatus = urgencyStatus;
	}
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}
	public Double getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}
	public String getCancelOrderTime() {
		return cancelOrderTime;
	}
	public void setCancelOrderTime(String cancelOrderTime) {
		this.cancelOrderTime = cancelOrderTime;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getRefundApplyTime() {
		return refundApplyTime;
	}
	public void setRefundApplyTime(String refundApplyTime) {
		this.refundApplyTime = refundApplyTime;
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
	public String getDoctorUuid() {
		return doctorUuid;
	}
	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
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
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getPackageUuid() {
		return packageUuid;
	}
	public void setPackageUuid(String packageUuid) {
		this.packageUuid = packageUuid;
	}
	public String getPlusNote() {
		return plusNote;
	}
	public void setPlusNote(String plusNote) {
		this.plusNote = plusNote;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
}
