package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomercharge.vo;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;

/**
 * 记录会员虚拟账户的充值信息
 * 
 * @author tangyunkai
 * 
 * @date 2014年12月29日 下午2:51:04
 * 
 */
@Entity
@Table(name = "virtual_account_customer_charge")
@Component
public class VirtualAccountCustomerChargeModel extends BaseModel {

	/**
	 * 查询会员相关信息
	 */
	@Transient
	private static CustomerAccountService customerAccountService;

	@Autowired
	public void setCustomerService(CustomerAccountService cas) {
		this.customerAccountService = cas;
	}

	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService cs) {
		this.customerService = cs;
	}

	@Transient
	private static CustomerInfoService customerInfoService;

	@Autowired
	public void setCustomerInfoService(CustomerInfoService cis) {
		this.customerInfoService = cis;
	}

	/**
	 * 充值状态 待支付
	 */
	public static final String UNDER_PAY = "0";

	/**
	 * 充值状态 充值成功
	 */
	public static final String PAY_SUCCESS = "1";

	/**
	 * 充值状态 充值失败
	 */
	public static final String PAY_FAIL = "2";

	/* 会员uuid */
	private String customerUuid = "";

	/* 充值单号 */
	private String chargeNo = "";

	/* 充值金额 */
	private float operAmount;

	/* 备注 */
	private String note = "";

	/* 充值状态 */
	private String chargeState = "";

	/* 充值时间 */
	private String createTime = "";

	/* 手机号 */
	private String mobile = "";

	/* 充值次数 */
	private int chargeTimes = 0;

	/* 订单号 */
	private String orderId = "";

	/* 充值方式    1微信 2支付宝  3其他 */
	private String payType = "";

	/* 用户名 */
	@Transient
	private String customerName = "";

	/* 账户余额 */
	@Transient
	private double accountBalance = 0;
	
	/* 患者真实姓名 */
	@Transient
	private String customerRealName = "";

	public String getCustomerRealName() {
		if (!StringUtil.isEmpty(customerUuid)) {
			return customerInfoService.getRealNameByUuid(customerUuid);
		}

		return customerRealName;
	}

	public void setCustomerRealName(String customerRealName) {
		this.customerRealName = customerRealName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCustomerName() {

		CustomerModel cm = customerService.getCustomerByUuid(this.customerUuid);
		if (cm != null) {
			return cm.getCustomerName();
		}
		return this.customerName;

	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getAccountBalance() {
		CustomerAccountModel cam = customerAccountService
				.getCustomerAccountModelByCustomerUuid(this.customerUuid);
		if (cam != null) {
			BigDecimal bg = new BigDecimal(cam.getAccountBalance());
			double aBalance = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
			return aBalance;
		}
		return this.accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getChargeTimes() {
		return chargeTimes;
	}

	public void setChargeTimes(int chargeTimes) {
		this.chargeTimes = chargeTimes;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setChargeNo(String obj) {
		this.chargeNo = obj;
	}

	public String getChargeNo() {
		return this.chargeNo;
	}

	public void setOperAmount(float obj) {
		this.operAmount = obj;
	}

	public float getOperAmount() {
		return this.operAmount;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setChargeState(String obj) {
		this.chargeState = obj;
	}

	public String getChargeState() {
		return this.chargeState;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerUuid=" + this.getCustomerUuid() + ",chargeNo="
				+ this.getChargeNo() + ",operAmount=" + this.getOperAmount()
				+ ",note=" + this.getNote() + ",chargeState="
				+ this.getChargeState() + ",createTime=" + this.getCreateTime()
				+ ",]";
	}
}
