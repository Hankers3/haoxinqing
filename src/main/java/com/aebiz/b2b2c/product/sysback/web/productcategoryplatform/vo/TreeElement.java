package com.aebiz.b2b2c.product.sysback.web.productcategoryplatform.vo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class TreeElement {
	
	private String title="";
	
	private String key="";
	/* 叶子*/
	private List<TreeElement> children;
	/* 是否展开*/
	private boolean expand=false;
	/* 是否收起  与上功能相反*/
	private boolean isFolder=true;
	/* 是否延迟加载 */
	private boolean isLazy=false;
	/* 隐藏复选框 false为不隐藏*/
	private boolean hideCheckbox=false;
	/* 是否选中*/
	private boolean select=false;
	
	public void setSelect(boolean select) {
		this.select = select;
	}
	public boolean isSelect() {
		return select;
	}
	
	public void setHideCheckbox(boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}
	public boolean isHideCheckbox() {
		return hideCheckbox;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public List<TreeElement> getChildren() {
		return children;
	}
	public void setChildren(List<TreeElement> children) {
		this.children = children;
	}
	public boolean getIsFolder() {
		return isFolder;
	}
	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	
	public boolean isExpand() {
		return expand;
	}
	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public boolean getIsLazy() {
		return isLazy;
	}
	public void setIsLazy(boolean isLazy) {
		this.isLazy = isLazy;
	}
	
	public static void main(String[] args) {
		TreeElement t=new TreeElement();
		TreeElement t1=new TreeElement();
		t.setChildren(null);
		List<TreeElement> list=new ArrayList<TreeElement>();
		list.add(t);list.add(t);
		System.out.println(JSON.toJSONString(list));
	}
}
