package com.aebiz.b2b2c.product.sysback.web.productcategoryplatform.vo;

import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class ProductCategoryPlatformWebModel extends BaseWebModel{
	/* 模板名称,格式为:模板1;模板2;*/
	private String templateName;
	
	/* 关联模板按钮是否显示*/
	private String buttonRelTemplate;
	
	public void setButtonRelTemplate(String buttonRelTemplate) {
		this.buttonRelTemplate = buttonRelTemplate;
	}
	
	public String getButtonRelTemplate() {
		return buttonRelTemplate;
	}
	
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateName() {
		return templateName;
	}
}