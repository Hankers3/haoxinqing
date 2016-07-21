package com.aebiz.b2b2c.cms.usercenter.web.productappraise.vo;

import java.util.ArrayList;
import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;

/**
 * 
 * 订单评价页面、每个订单明细下面需要有对应的标签供会员勾选
 * 
 * @author cj
 * 
 */

public class ProductAppraiseWebModel extends BaseWebModel {
	/* 是否已评价 0否 1是 */
	public static final String APPRAISE_NO = "0";
	public static final String APPRAISE_YES = "1";

	/* 订单明细 */
	private OrderDetailModel odm = new OrderDetailModel();

	/* 商品图片 */
	private String productPicUrl = "";

	/* 是否已评价 0否 1是 */
	private String appraiseOrNo = "";

	/* 标签集合 */
	private List<TagsModel> tags = new ArrayList<TagsModel>();

	/* 商品评价信息 */
	private ProductAppraiseModel pam = new ProductAppraiseModel();
	
	/* 会员头像 */
	private String customerImage = "";

	public OrderDetailModel getOdm() {
		return odm;
	}

	public void setOdm(OrderDetailModel odm) {
		this.odm = odm;
	}

	public List<TagsModel> getTags() {
		return tags;
	}

	public void setTags(List<TagsModel> tags) {
		this.tags = tags;
	}

	public String getProductPicUrl() {
		return productPicUrl;
	}

	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}

	public String getAppraiseOrNo() {
		return appraiseOrNo;
	}

	public void setAppraiseOrNo(String appraiseOrNo) {
		this.appraiseOrNo = appraiseOrNo;
	}

	public ProductAppraiseModel getPam() {
		return pam;
	}

	public void setPam(ProductAppraiseModel pam) {
		this.pam = pam;
	}

	public String getCustomerImage() {
		return customerImage;
	}

	public void setCustomerImage(String customerImage) {
		this.customerImage = customerImage;
	}
	
}
