package com.hxq.mobile.util;

/**
 * 给前端传递的图像地址
 *
 */
public class Image4App {
	private String large;
	private String small;
	public Image4App(String large, String small) {
		super();
		this.large = large;
		this.small = small;
	}
	public String getLarge() {
		return large;
	}
	public void setLarge(String large) {
		this.large = large;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
}
