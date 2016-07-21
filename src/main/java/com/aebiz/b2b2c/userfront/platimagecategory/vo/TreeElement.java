package com.aebiz.b2b2c.userfront.platimagecategory.vo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class TreeElement {
	
	private String title="";
	
	private String key="";
	
	private List<TreeElement> children;
	
	private boolean expand=false;
	
	private boolean isFolder=true;
	
	private boolean isLazy=false;
	
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
