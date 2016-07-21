package com.aebiz.b2b2c.thirdinterface.mobilemessage.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "mobile_message")
public class MobileMessageModel extends BaseModel {

	/* url */
	private String url;

	/* 用户名 */
	private String userId;

	/* 密码 */
	private String password;

	/* 企业代码(扩展号，不确定请赋值空) */
	private String scorpid;

	/* 产品编号 */
	private String sprdid;

	@Transient
	private String repeatPassWord = "";

	public String getRepeatPassWord() {
		return repeatPassWord;
	}

	public void setRepeatPassWord(String repeatPassWord) {
		this.repeatPassWord = repeatPassWord;
	}

	public void setUrl(String obj) {
		this.url = obj;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUserId(String obj) {
		this.userId = obj;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setPassword(String obj) {
		this.password = obj;
	}

	public String getPassword() {
		return this.password;
	}

	public String getScorpid() {
		return scorpid;
	}

	public void setScorpid(String scorpid) {
		this.scorpid = scorpid;
	}

	public String getSprdid() {
		return sprdid;
	}

	public void setSprdid(String sprdid) {
		this.sprdid = sprdid;
	}
}
