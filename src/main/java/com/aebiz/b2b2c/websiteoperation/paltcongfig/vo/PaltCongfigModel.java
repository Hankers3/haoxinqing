package com.aebiz.b2b2c.websiteoperation.paltcongfig.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Entity
@Component
@Table(name="palt_congfig")
public class PaltCongfigModel extends BaseModel{
	
	@Transient
	private static FileService fileService;

	// 注入文件service，为了获取文件的路径
	@Autowired
	public void setFileService(FileService bs) {
		fileService = bs;
	}
	
	/*商城名称*/
	private String platName = "";
	
	/*商城logo*/
	private String platLogo = "";
	
	/*商城logopath*/
	@Transient
	private String platLogoPath = "";
	
	/*客服电话*/
	private String servicePhone = "";
	
	/*微信图片*/
	private String weixinImage = "";
	
	/*微信图片path*/
	@Transient
	private String weixinImagePath = "";
	
	/*底部版权信息*/
	private String bottomInfo = "";
	
	/*系统域名*/
	private String platDomain = "";
	
	/*会员注册协议*/
	private String customerReg = "";
	
	/*商户注册协议*/
	private String storeReg = "";
	
	/*底部广告图*/
	private String bottomAd = "";
	
	/*底部广告路径*/
	@Transient
	private String bottomAdPath = "";
	
	/*底部广告链接*/
	private String bottomAdUrl = "";
	 
	/*头部广告*/
	private String topAd = "";
	
	/*头部广告路径*/
	@Transient
	private String topAdPath = "";
	
	/*头部广告链接*/
	private String topAdUrl = "";
	
	
	public String getBottomAdUrl() {
		return bottomAdUrl;
	}
	public void setBottomAdUrl(String bottomAdUrl) {
		this.bottomAdUrl = bottomAdUrl;
	}
	public String getTopAdUrl() {
		return topAdUrl;
	}
	public void setTopAdUrl(String topAdUrl) {
		this.topAdUrl = topAdUrl;
	}
	public void setBottomAdPath(String bottomAdPath) {
		this.bottomAdPath = bottomAdPath;
	}
	public String getBottomAd() {
		return bottomAd;
	}
	public void setBottomAd(String bottomAd) {
		this.bottomAd = bottomAd;
	}
	public String getTopAd() {
		return topAd;
	}
	public void setTopAd(String topAd) {
		this.topAd = topAd;
	}
	public String getTopAdPath() {
		FileModel fileModel = fileService.getOneFileModel(this.topAd);

		if (fileModel != null) {
			return fileModel.getRemotePaths();
		}
		return "";
	}
	public void setTopAdPath(String topAdPath) {
		this.topAdPath = topAdPath;
	}
	public String getBottomAdPath() {
		FileModel fileModel = fileService.getOneFileModel(this.bottomAd);

		if (fileModel != null) {
			return fileModel.getRemotePaths();
		}
		return "";
	}
	public void setPlatName(String obj){
		this.platName = obj;
	}
	public String getPlatName(){
		return this.platName;
	}
	
	public void setPlatLogo(String obj){
		this.platLogo = obj;
	}
	public String getPlatLogo(){
		return this.platLogo;
	}
	
	public void setServicePhone(String obj){
		this.servicePhone = obj;
	}
	public String getServicePhone(){
		return this.servicePhone;
	}
	
	public void setWeixinImage(String obj){
		this.weixinImage = obj;
	}
	public String getWeixinImage(){
		return this.weixinImage;
	}
	
	public void setBottomInfo(String obj){
		this.bottomInfo = obj;
	}
	public String getBottomInfo(){
		return this.bottomInfo;
	}
	
	public void setPlatDomain(String obj){
		this.platDomain = obj;
	}
	public String getPlatDomain(){
		return this.platDomain;
	}
	
	public void setCustomerReg(String obj){
		this.customerReg = obj;
	}
	public String getCustomerReg(){
		return this.customerReg;
	}
	
	public void setStoreReg(String obj){
		this.storeReg = obj;
	}
	public String getStoreReg(){
		return this.storeReg;
	}
	
	
	public String getPlatLogoPath() {
		// 获取文件对象
		FileModel fileModel = fileService.getOneFileModel(this.platLogo);

		if (fileModel != null) {
			return fileModel.getRemotePaths();
		}
		return "";
	}
	public void setPlatLogoPath(String platLogoPath) {
		this.platLogoPath = platLogoPath;
	}
	public String getWeixinImagePath() {
		// 获取文件对象
		FileModel fileModel = fileService.getOneFileModel(this.weixinImage);

		if (fileModel != null) {
			return fileModel.getRemotePaths();
		}
		return "";
	}
	public void setWeixinImagePath(String weixinImagePath) {
		this.weixinImagePath = weixinImagePath;
	}
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[platName=" + this.getPlatName() + ",platLogo=" + this.getPlatLogo() + ",servicePhone=" + this.getServicePhone() + ",weixinImage=" + this.getWeixinImage() + ",bottomInfo=" + this.getBottomInfo() + ",platDomain=" + this.getPlatDomain() + ",customerReg=" + this.getCustomerReg() + ",storeReg=" + this.getStoreReg() + ",]";
	}	
}
