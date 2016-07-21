package com.aebiz.b2b2c.servicestaff.servicestaffentry.vo;

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
@Table(name="service_staff_entry")
@Component
public class ServicestaffentryModel extends BaseModel{
	@Transient
	private static FileService fileService;
	
	// 注入文件service，为了获取文件的路径
	@Autowired
	public void setFileService(FileService bs) {
		fileService = bs;
	}
	private String serviceStaffUuid;
	
	/*合同照片 */
	private String contract;
	
	/*公司标准照 */
	private String standardimage;
	
	private String servicetraning;
	
	/*标准服务培训 1：合格 2：不合格*/
	private String servicetraningState;
	
	private String appusetraning;
	
	/*app使用培训 1：合格 2：不合格*/
	private String appusetraningState;
	
	private String paytraning;

	/*支付系统培训 1：合格 2：不合格*/
	private String paytraningState;
	
	private String uniformget;
	private String toolget;
	private String whethercomplete;
	
	/* 得到图片路径 */
	@Transient
	private String imgUrl = "";
	
	/* 得到图片路径 */
	@Transient
	private String contractImgUrl;
	
	public String getContractImgUrl() {
		if (StringUtil.isEmpty(this.contract)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.contract);
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			return file.getRemotePaths();
		} else {
			return this.getContract();
		}
	}

	public void setContractImgUrl(String contractImgUrl) {
		this.contractImgUrl = contractImgUrl;
	}

	public String getImgUrl() {
		if (StringUtil.isEmpty(this.standardimage)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.standardimage);
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			return file.getRemotePaths();
		} else {
			return this.getStandardimage();
		}
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public void setServiceStaffUuid(String obj){
		this.serviceStaffUuid = obj;
	}
	public String getServiceStaffUuid(){
		return this.serviceStaffUuid;
	}
	
	public void setContract(String obj){
		this.contract = obj;
	}
	public String getContract(){
		return this.contract;
	}
	
	public void setStandardimage(String obj){
		this.standardimage = obj;
	}
	public String getStandardimage(){
		return this.standardimage;
	}
	
	public void setServicetraning(String obj){
		this.servicetraning = obj;
	}
	public String getServicetraning(){
		return this.servicetraning;
	}
	
	public void setAppusetraning(String obj){
		this.appusetraning = obj;
	}
	public String getAppusetraning(){
		return this.appusetraning;
	}
	
	public void setPaytraning(String obj){
		this.paytraning = obj;
	}
	public String getPaytraning(){
		return this.paytraning;
	}
	
	public void setUniformget(String obj){
		this.uniformget = obj;
	}
	public String getUniformget(){
		return this.uniformget;
	}
	
	public void setToolget(String obj){
		this.toolget = obj;
	}
	public String getToolget(){
		return this.toolget;
	}
	
	public void setWhethercomplete(String obj){
		this.whethercomplete = obj;
	}
	
	public String getWhethercomplete(){
		return this.whethercomplete;
	}
	
	public String getServicetraningState() {
		return servicetraningState;
	}
	
	public void setServicetraningState(String servicetraningState) {
		this.servicetraningState = servicetraningState;
	}
	
	public String getAppusetraningState() {
		return appusetraningState;
	}
	
	public void setAppusetraningState(String appusetraningState) {
		this.appusetraningState = appusetraningState;
	}
	
	public String getPaytraningState() {
		return paytraningState;
	}
	
	public void setPaytraningState(String paytraningState) {
		this.paytraningState = paytraningState;
	}
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[serviceStaffUuid=" + this.getServiceStaffUuid() + ",contract=" + this.getContract() + ",standardimage=" + this.getStandardimage() + ",servicetraning=" + this.getServicetraning() + ",appusetraning=" + this.getAppusetraning() + ",paytraning=" + this.getPaytraning() + ",uniformget=" + this.getUniformget() + ",toolget=" + this.getToolget() + ",whethercomplete=" + this.getWhethercomplete() + ",]";
	}	
}
