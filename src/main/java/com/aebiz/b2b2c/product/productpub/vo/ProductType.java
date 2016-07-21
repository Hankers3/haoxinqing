package com.aebiz.b2b2c.product.productpub.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

public enum ProductType {
	//ORDINARY("01",MessageHelper.getMessage("productmain.productType.ordinary")),
	//SUIT("02",MessageHelper.getMessage("productmain.productType.suit")),
	//WHOLESALE("03",MessageHelper.getMessage("productmain.productType.wholesale")),
	//LIFE("04",MessageHelper.getMessage("productmain.productType.life")),
	//WILL("05",MessageHelper.getMessage("productmain.productType.will"));
	
	HOUSEKEEP("01",MessageHelper.getMessage("productmain.productType.housekeep")),
	MATRON("02",MessageHelper.getMessage("productmain.productType.matron"));
	
	private String value = "";
	private String name="";
	
	private ProductType(String value,String name) {
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
