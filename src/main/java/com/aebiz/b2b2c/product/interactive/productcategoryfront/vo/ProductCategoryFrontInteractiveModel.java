package com.aebiz.b2b2c.product.interactive.productcategoryfront.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productcategoryfront.service.ProductCategoryFrontService;

@Component
public class ProductCategoryFrontInteractiveModel extends BaseModel{
	
	/*注入文件service*/
	@Transient
	private  static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	/*注入查询商品分类service*/
	@Transient
	private static ProductCategoryFrontService productCategoryFrontService;
	
	/* 分类编号 */
	private String categoryNo;

	/* 分类名称 */
	private String categoryName;

	/* 英文名称 */
	private String enCategoryName;

	/* 位置 */
	private int position;

	/* 分类描述 */
	private String categoryNote;

	/* 是否启用 */
	private String state;

	/* 所属分类uuid */
	private String parentUuid;

	/* 分类图标 */
	private String icon;

	/* SEO关键字 */
	private String seoKeyWords;

	/* SEO描述 */
	private String seoDescription;

	/* 价格区间 */
	private String priceSpace;

	/* 分类链接 */
	private String categoryUrl;

	/* 分类图标路径 */
	@Transient
	private String iconPath;

	@Transient
	private List<String> categoryPrice = new ArrayList<String>();

	public String getIconPath() {
		if (!StringUtil.isEmpty(this.icon)) {
			return this.fileService.getOneFileModel(this.icon).getRemotePaths();
		}
		return iconPath;
	}

	public void setCategoryNo(String obj) {
		this.categoryNo = obj;
	}

	public String getCategoryNo() {
		return this.categoryNo;
	}

	public void setCategoryName(String obj) {
		this.categoryName = obj;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setEnCategoryName(String obj) {
		this.enCategoryName = obj;
	}

	public String getEnCategoryName() {
		return this.enCategoryName;
	}

	public void setPosition(int obj) {
		this.position = obj;
	}

	public int getPosition() {
		return this.position;
	}

	public void setCategoryNote(String obj) {
		this.categoryNote = obj;
	}

	public String getCategoryNote() {
		return this.categoryNote;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setParentUuid(String obj) {
		this.parentUuid = obj;
	}

	public String getParentUuid() {
		return this.parentUuid;
	}

	public void setIcon(String obj) {
		this.icon = obj;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setSeoKeyWords(String obj) {
		this.seoKeyWords = obj;
	}

	public String getSeoKeyWords() {
		return this.seoKeyWords;
	}

	public void setSeoDescription(String obj) {
		this.seoDescription = obj;
	}

	public String getSeoDescription() {
		return this.seoDescription;
	}

	public void setPriceSpace(String obj) {
		this.priceSpace = obj;
	}

	public String getPriceSpace() {
		return this.priceSpace;
	}

	public void setCategoryUrl(String obj) {
		this.categoryUrl = obj;
	}

	public String getCategoryUrl() {
		return this.categoryUrl;
	}

	public List<String> getCategoryPrice() {
		return categoryPrice;
	}

	public void setCategoryPrice(List<String> categoryPrice) {
		this.categoryPrice = categoryPrice;
	}
	
	
	
	@Autowired
	public  void setProductCategoryStoreService(
			ProductCategoryFrontService productCategoryStoreService) {
		this.productCategoryFrontService = productCategoryFrontService;
	}

	 
	
	/* 店铺子分类*/
	private List<ProductCategoryFrontInteractiveModel> subProductCategoryFront;
	
	public void setSubProductCategoryFront(
			List<ProductCategoryFrontInteractiveModel> subProductCategoryFront) {
		this.subProductCategoryFront = subProductCategoryFront;
	}
	public List<ProductCategoryFrontInteractiveModel> getSubProductCategoryFront() {
		return subProductCategoryFront;
	}
 
}
