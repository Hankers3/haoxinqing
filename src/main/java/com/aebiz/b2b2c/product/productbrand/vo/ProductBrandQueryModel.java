package com.aebiz.b2b2c.product.productbrand.vo;

import java.util.List;

public class ProductBrandQueryModel extends ProductBrandModel {
	
	 public static final String SEARCH_IN = "1";
	 public static final String SEARCH_NOT_IN = "2";
	
	/* 品牌uuid集合*/
	private List<String> brandUuids;
	
	/* not in  Or  in*/
	private String searchType;
	
	/* 字母*/
	private String letter;
	
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getLetter() {
		return letter;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public void setBrandUuids(List<String> brandUuids) {
		this.brandUuids = brandUuids;
	}
	public List<String> getBrandUuids() {
		return brandUuids;
	}
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
