package com.aebiz.b2b2c.cms.productappraise.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "product_appraise")
public class ProductAppraiseModel extends BaseModel {
	/* 审核状态 1未审核 2审核通过 3审核不通过 */
	public static final String STATE_REVIEW_WEI = "1";
	public static final String STATE_REVIEW_SUCCESS = "2";
	public static final String STATE_REVIEW_FAIL = "3";

	/* 订单编号 */
	private String orderUuid = "";

	/* 订单明细编号 */
	private String orderDetailUuid = "";
	
	/*商品规格编号*/
	private String productSkuNo = "";

	/* 商品UUID */
	private String productUuid = "";

	/* 评论人 */
	private String customerUuid = "";

	/* 评论人名称 */
	private String customerName = "";

	/* 评分 */
	private int appScore = 0;

	/* 评论标签，以;隔开 */
	private String appTag = "";

	/* 评论内容 */
	private String appContent = "";

	/* 审核状态 */
	private String state = "";

	/* 评论时间 */
	private String appTime = "";

	/* 审核人 */
	private String reviewUserUuid = "";

	/* 审核时间 */
	private String reviewTime = "";

	/* 审核原因 */
	private String reviewDesc = "";

	/* 商品编号 */
	@Transient
	private String productNo = "";

	/* 标签集合 */
	@Transient
	private List<String> tags = new ArrayList<String>();

	public void setOrderUuid(String obj) {
		this.orderUuid = obj;
	}

	public String getOrderUuid() {
		return this.orderUuid;
	}

	public void setOrderDetailUuid(String obj) {
		this.orderDetailUuid = obj;
	}

	public String getOrderDetailUuid() {
		return this.orderDetailUuid;
	}

	public void setProductUuid(String obj) {
		this.productUuid = obj;
	}

	public String getProductUuid() {
		return this.productUuid;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setAppScore(int obj) {
		this.appScore = obj;
	}

	public int getAppScore() {
		return this.appScore;
	}

	public void setAppTag(String obj) {
		this.appTag = obj;
	}

	public String getAppTag() {
		return this.appTag;
	}

	public void setAppContent(String obj) {
		this.appContent = obj;
	}

	public String getAppContent() {
		return this.appContent;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setAppTime(String obj) {
		this.appTime = obj;
	}

	public String getAppTime() {
		return this.appTime;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getReviewUserUuid() {
		return reviewUserUuid;
	}

	public void setReviewUserUuid(String reviewUserUuid) {
		this.reviewUserUuid = reviewUserUuid;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductSkuNo() {
		return productSkuNo;
	}

	public void setProductSkuNo(String productSkuNo) {
		this.productSkuNo = productSkuNo;
	}

}
