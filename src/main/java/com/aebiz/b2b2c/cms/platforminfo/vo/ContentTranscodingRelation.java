package com.aebiz.b2b2c.cms.platforminfo.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "platform_info_relation")
@Component
public class ContentTranscodingRelation extends BaseModel{
	/*转码任务jobId*/
	public String jobId;
	/*内容描述*/
	public String contentName;
	/*源文件名*/
	public String sourceFileName;
	/*源文件地址*/
	public String sourceFileUrl;
	/*目标文件名*/
	public String targetFileName;
	/*目标文件地址*/
	public String targetFileUrl;
	/*创建时间*/
	public String createTime;
	/*内容uuid*/
	public String contentSid;
	
	


	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getSourceFileUrl() {
		return sourceFileUrl;
	}

	public void setSourceFileUrl(String sourceFileUrl) {
		this.sourceFileUrl = sourceFileUrl;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public String getTargetFileUrl() {
		return targetFileUrl;
	}

	public void setTargetFileUrl(String targetFileUrl) {
		this.targetFileUrl = targetFileUrl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContentSid() {
		return contentSid;
	}

	public void setContentSid(String contentSid) {
		this.contentSid = contentSid;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
