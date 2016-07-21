package com.hxq.mobile.entity.common;
/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年5月28日 下午3:45:10 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
public class QueryUtil {
	private Integer start ; //分页开始
	private Integer length; //分页的条数
	private String sortName; //排序字段
	private String sortType; //排序type
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	
}
