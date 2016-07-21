package com.aebiz.b2b2c.product.sysback.web.product.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.product.productmainaudit.service.ProductMainAuditService;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditModel;

/**
 * 
 * 该类存放审核字段错误的原因 一个属性存放对应的错误原因，用于审核页面展示
 * 
 * @author huangpinpin
 * 
 */

@Component
public class FieldReason {

	
	private static ProductMainAuditService auditService;
	@Autowired
	public void setAuditService(ProductMainAuditService auditService) {
		this.auditService = auditService;
	}

	private List<ProductMainAuditModel> reasons;

	private Map<String, String> reasonMap = new HashMap<String, String>();

	public FieldReason() {
		
	}

	public FieldReason(String productUuid) {
		reasons = auditService.getByProductUuid(productUuid);
		if (reasons != null) {
			for (ProductMainAuditModel pmam : reasons) {
				reasonMap.put(pmam.getAuditField(), pmam.getAuditReason());
			}
		}
	}

	public static final String PRODUCTNAME = "productName";
	public static final String ADVICENOTE = "adviceNote";
	public static final String CATEGORYNAME = "categoryName";
	public static final String INTEGRAL = "integral";
	public static final String LEASTINTEGRAL = "leastIntegral";
	public static final String PRODUCTATTR = "productAttr";
	public static final String MARKETPRICE = "marketPrice";
	public static final String SHOPPRICE = "shopPrice";
	public static final String AVAILABLETIME = "availableTime";
	public static final String LASTPAYTIME = "lastPayTime";
	public static final String FIRSTPAY = "firstPay";
	public static final String LASTPAY = "lastPay";
	public static final String PRODUCTIMAGE = "productImage";
	public static final String NOTE = "note";
	public static final String DESCRIPTION = "description";

	/* 商品名称 */
	private String productName = "";

	/* 推荐语 */
	private String adviceNote = "";

	/* 分类名称 */
	private String categoryName = "";

	/* 赠送积分数量 */
	private String integral = "";

	/* 最少所需积分 */
	private String leastIntegral = "";

	/* 规格属性 */
	private String productAttr = "";

	/* 商品市场价 */
	private String marketPrice = "";

	/* 商品商城价 */
	private String shopPrice = "";

	/* 商品有效时间 */
	private String availableTime = "";

	/* 尾款交付时间 */
	private String lastPayTime = "";

	/* 首款 */
	private String firstPay = "";
	
	/* 尾款 */
	private String lastPay = "";

	/* 商品图片 */
	private String productImage = "";

	/* 商品简介 */
	private String note = "";

	/* 商品描述 */
	private String description = "";

	public String getLastPay() {
		return reasonMap.get(this.LASTPAY);
	}
	
	public String getProductName() {
		return reasonMap.get(this.PRODUCTNAME);
	}

	public String getAdviceNote() {
		return reasonMap.get(this.ADVICENOTE);
	}

	public String getCategoryName() {
		return reasonMap.get(this.CATEGORYNAME);
	}

	public String getIntegral() {
		return reasonMap.get(this.INTEGRAL);
	}

	public String getLeastIntegral() {
		return reasonMap.get(this.LEASTINTEGRAL);
	}

	public String getProductAttr() {
		return reasonMap.get(this.PRODUCTATTR);
	}

	public String getMarketPrice() {
		return reasonMap.get(this.MARKETPRICE);
	}

	public String getShopPrice() {
		return reasonMap.get(this.SHOPPRICE);
	}

	public String getAvailableTime() {
		return reasonMap.get(this.AVAILABLETIME);
	}

	public String getLastPayTime() {
		return reasonMap.get(this.AVAILABLETIME);
	}

	public String getFirstPay() {
		return reasonMap.get(this.FIRSTPAY);
	}

	public String getProductImage() {
		return reasonMap.get(this.PRODUCTIMAGE);
	}
 
	public String getNote() {
		return reasonMap.get(this.NOTE);
	}

	public String getDescription() {
		return reasonMap.get(this.DESCRIPTION);
	}

}
