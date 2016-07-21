package com.aebiz.b2b2c.customermgr.consultrecordreply.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
/**
 * 咨询回复信息表
 * @author xueli
 *
 */
@Entity
@Table(name="consult_record_reply")
@Component
public class ConsultRecordReplyModel extends BaseModel{
	/* 注解文件的service */
	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	//在线咨询编号
	private String consultRecordUuid;
	
	//医生编号
	private String doctorUuid;
	
	//患者编号
	private String customerUuid;
	
	//内容
	private String content;
	
	//回复图片
	private String picture;
	
	//创建时间
	private String createTime;
	
	//次数
	private int num =0;
	
	//图片地址
	@Transient
	private String picUrl="";
	
	public String getPicUrl() {
		if (StringUtil.isEmpty(this.picture)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.picture);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPicture();
		}

	}
	
	public String getConsultRecordUuid() {
		return consultRecordUuid;
	}

	public void setConsultRecordUuid(String consultRecordUuid) {
		this.consultRecordUuid = consultRecordUuid;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public void setDoctorUuid(String obj){
		this.doctorUuid = obj;
	}
	public String getDoctorUuid(){
		return this.doctorUuid;
	}
	
	public void setCustomerUuid(String obj){
		this.customerUuid = obj;
	}
	public String getCustomerUuid(){
		return this.customerUuid;
	}
	
	public void setContent(String obj){
		this.content = obj;
	}
	public String getContent(){
		return this.content;
	}
	
	public void setPicture(String obj){
		this.picture = obj;
	}
	public String getPicture(){
		return this.picture;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setNum(int obj){
		this.num = obj;
	}
	public int getNum(){
		return this.num;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[doctorUuid=" + this.getDoctorUuid() + ",customerUuid=" + this.getCustomerUuid() + ",content=" + this.getContent() + ",picture=" + this.getPicture() + ",createTime=" + this.getCreateTime() + ",num=" + this.getNum() + ",]";
	}	
}
