package com.aebiz.b2b2c.cms.interactive.innermessage.vo;


/**
 * @author linqiang
 * 
 */
public class InnerMessageInteractiveModel {

	/* 信息标题 */
	private String title = "";

	/* 信息内容 */
	private String content = "";

	/* 发件人 */
	private String sendUser = "";
	
	/* 发件人头像 */
	private String userImage = "";

	/* 发送时间 */
	private String sendTime = "";
	
	public void setTitle(String obj) {
		this.title = obj;
	}

	public String getTitle() {
		return this.title;
	}

	public void setContent(String obj) {
		this.content = obj;
	}

	public String getContent() {
		return this.content;
	}

	public void setSendUser(String obj) {
		this.sendUser = obj;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getSendUser() {
		return this.sendUser;
	}

	public void setSendTime(String obj) {
		this.sendTime = obj;
	}

	public String getSendTime() {
		return this.sendTime;
	}

}
