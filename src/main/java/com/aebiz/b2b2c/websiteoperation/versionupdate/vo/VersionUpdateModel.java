package com.aebiz.b2b2c.websiteoperation.versionupdate.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
/**
 * 安卓版本更新
 * @author xueli
 *
 */
@Entity
@Table(name="version_update")
@Component
public class VersionUpdateModel extends BaseModel{

	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/*设备号*/
	private String versionNo;
	
	/*创建时间*/
	private String createTime;
	
	/*链接*/
	private String url;
	
	/*类型：1、患者端  0、医生端*/
	private String versionType;
	
	@Transient
	private String versionUrl="";
	
	public String getVersionUrl() {
		if (StringUtil.isEmpty(this.url)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.url);
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			return file.getRemotePaths();
		} else {
			return this.versionUrl;
		}
		
	}
	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}
	public void setVersionNo(String obj){
		this.versionNo = obj;
	}
	public String getVersionNo(){
		return this.versionNo;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setUrl(String obj){
		this.url = obj;
	}
	public String getUrl(){
		return this.url;
	}
	
	public String getVersionType() {
		return versionType;
	}
	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[versionNo=" + this.getVersionNo() + ",createTime=" + this.getCreateTime() + ",url=" + this.getUrl() + ",]";
	}	
}
