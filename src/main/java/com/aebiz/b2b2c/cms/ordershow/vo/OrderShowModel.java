package com.aebiz.b2b2c.cms.ordershow.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;

@Entity
@Table(name = "order_show")
public class OrderShowModel extends BaseModel {
	/* 晒单状态 即审核状态 1未审核 2审核通过 3审核不通过 */
	public static final String STATE_REVIEW_WEI = "1";
	public static final String STATE_REVIEW_SUCCESS = "2";
	public static final String STATE_REVIEW_FAIL = "3";

	/* 订单明细编号 */
	private String orderDetailUuid = "";

	/* 商品编号 */
	private String productUuid = "";

	/* 晒单人ID */
	private String customerUuid = "";
	
	/* 晒单人 */
	private String customerName = "";

	/* 晒单标题 */
	private String showTitle = "";

	/* 晒单时间 */
	private String showTime = "";

	/* 晒单内容 */
	private String showContent = "";

	/* 晒单状态 即审核状态 1未审核 2审核通过 3审核不通过 */
	private String state = "";
	
	/*审核人*/
	private String reviewUser = "";
	
	/*审核时间*/
	private String reviewTime = "";
	
	/*审核原因*/
	private String reviewDesc = "";

	/* 晒单图片 */
	@Transient
	private List<OrderShowPicModel> ospList = new ArrayList<OrderShowPicModel>();
	
	/* 订单号 */
	@Transient
	private String orderUuid = "";
	
	/* 商品编号 */
	@Transient
	private String productNo = "";
	
	/* 商品名称 */
	@Transient
	private String productName = "";
	
	
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

	public void setShowTime(String obj) {
		this.showTime = obj;
	}

	public String getShowTime() {
		return this.showTime;
	}

	public void setShowContent(String obj) {
		this.showContent = obj;
	}

	public String getShowContent() {
		return this.showContent;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public List<OrderShowPicModel> getOspList() {
		return ospList;
	}

	public void setOspList(List<OrderShowPicModel> ospList) {
		this.ospList = ospList;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(String reviewUser) {
		this.reviewUser = reviewUser;
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
	
}
