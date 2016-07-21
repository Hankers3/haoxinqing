package com.aebiz.b2b2c.product.productmain.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.product.productpub.vo.ProductType;

/**
 * 商品计费方式枚举 
 * 			SQUARE ：平米</br>
 * 			NUMBER ：件数</br>
 * 
 * 商品发布范围枚举
 * 			APP ：APP端</br>
 * 			WEB ：WEB端</br>
 * @author xueli
 *
 */
public enum ProductMainType {
	/* 商品计费方式 */
	SQUARE("1",MessageHelper.getMessage("productmain.chargetype.square")),
	NUMBER("2",MessageHelper.getMessage("productmain.chargetype.number")),
	
	/* 商品发布范围 */
	APP("01",MessageHelper.getMessage("productmain.scope.app")),
	WEB("02",MessageHelper.getMessage("productmain.scope.web"));
	
	private String value = "";
	private String name="";
	
	private ProductMainType(String value,String name) {
		this.value = value;
		this.name = name;
	}
	
	
	public String getValue() {
		return this.value;
	}
	public String getName() {
		return this.name;
	}
	
	public static String getNameByKey(String value) {  
        for (ProductType c : ProductType.values()) {  
            if (c.getValue().equals(value)) {  
                return c.getName();  
            }  
        }  
        return "";  
    }  
	
}
