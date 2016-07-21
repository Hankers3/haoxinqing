package com.aebiz.b2b2c.order.orderfront.web.ordermain.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

/**
 * 
 * 购物车存放在cookie中的转换对象
 * 
 * key=卖家的编号<br />
 * value=卖家店铺的商品集合<br />
 * 
 * @author zdx
 * 
 */
public class SimpleCookieOrDbCart implements Serializable {

	public Map<String, List<CartManagerModel>> simpleCookieOrCartManagerModel;

	public Map<String, List<CartManagerModel>> getSimpleCookieOrCartManagerModel() {
		return simpleCookieOrCartManagerModel;
	}

	public void setSimpleCookieOrCartManagerModel(
			Map<String, List<CartManagerModel>> simpleCookieOrCartManagerModel) {
		this.simpleCookieOrCartManagerModel = simpleCookieOrCartManagerModel;
	}
	
	
}
