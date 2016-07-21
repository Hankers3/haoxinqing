package com.aebiz.b2b2c.cms.ordermainappraise.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 订单评价
 * 
 * @author cj
 * 
 */
@Entity
@Table(name = "order_main_appraise")
public class OrderMainAppraiseModel extends BaseModel {
	// 店铺评分类型 1 商家评分；2发货速度评分；3如实描述评分；4服务态度评分
	public static final String SCORE_STORE = "1";
	public static final String SCORE_SPEED = "2";
	public static final String SCORE_DESC = "3";
	public static final String SCORE_SERVICE = "4";

	/* 订单号 */
	private String orderMainUuid = "";

	/* 店铺UUID */
	private String storeUuid = "";

	/* 评价人id */
	private String customerUuid = "";

	/* 评价人名称 */
	private String customerName = "";

	/* 评价时间 */
	private String appraiseTime = "";

	/* 评价内容 */
	private String content = "";

	/* 店铺评分 */
	private int storeScore = 0;

	/* 发货速度评分 */
	private int speedScore = 0;

	/* 如实描述评分 */
	private int descScore = 0;

	/* 服务态度评分 */
	private int serviceScore = 0;

	/* 店家回复内容 */
	private String storeReply = "";

	/* 店家对客户的评分 */
	private int customerScore = 0;

	public void setOrderMainUuid(String obj) {
		this.orderMainUuid = obj;
	}

	public String getOrderMainUuid() {
		return this.orderMainUuid;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setCustomerName(String obj) {
		this.customerName = obj;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setAppraiseTime(String obj) {
		this.appraiseTime = obj;
	}

	public String getAppraiseTime() {
		return this.appraiseTime;
	}

	public void setContent(String obj) {
		this.content = obj;
	}

	public String getContent() {
		return this.content;
	}

	public void setStoreScore(int obj) {
		this.storeScore = obj;
	}

	public int getStoreScore() {
		return this.storeScore;
	}

	public void setSpeedScore(int obj) {
		this.speedScore = obj;
	}

	public int getSpeedScore() {
		return this.speedScore;
	}

	public void setDescScore(int obj) {
		this.descScore = obj;
	}

	public int getDescScore() {
		return this.descScore;
	}

	public void setServiceScore(int obj) {
		this.serviceScore = obj;
	}

	public int getServiceScore() {
		return this.serviceScore;
	}

	public void setStoreReply(String obj) {
		this.storeReply = obj;
	}

	public String getStoreReply() {
		return this.storeReply;
	}

	public void setCustomerScore(int obj) {
		this.customerScore = obj;
	}

	public int getCustomerScore() {
		return this.customerScore;
	}

	public String getStoreUuid() {
		return storeUuid;
	}

	public void setStoreUuid(String storeUuid) {
		this.storeUuid = storeUuid;
	}

}
