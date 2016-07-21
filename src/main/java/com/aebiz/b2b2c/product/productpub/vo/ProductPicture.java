package com.aebiz.b2b2c.product.productpub.vo;

/* 图片对象*/
public class ProductPicture {
	/* 图片名称*/
	private String imgName;
	/* 图片名称*/
	private String imgPath;
	/* 位置*/
	private int position=0;
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
