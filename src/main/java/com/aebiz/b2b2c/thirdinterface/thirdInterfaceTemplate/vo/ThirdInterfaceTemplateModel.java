package com.aebiz.b2b2c.thirdinterface.thirdInterfaceTemplate.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "thirdinterface_template")
public class ThirdInterfaceTemplateModel extends BaseModel {
	/* 模板类型，mobile为短信模板 */
	public static final String TEMPLATETYPE_MOBILE = "mobile";

	/* 模板类型，email为邮件模板 */
	public static final String TEMPLATETYPE_EMAIL = "email";

	/* 模板类型，innermsg为站内信(消息)模板 */
	public static final String TEMPLATETYPE_INNERMESSAGE = "innermsg";

	/* 模板状态：1表示启用 */
	public static final String START_USE = "1";

	/* 模板状态：0表示不启用 */
	public static final String END_USE = "0";

	/* 模板id */
	private String templateId;

	/* 模板名称 */
	private String templateName;

	/* 模板类型 */
	private String templateType;

	/* 模板状态 */
	private String templateState;

	/* 排序 */
	private int position;

	/* 模板标题 */
	private String templateTitle;

	/* 模板内容 */
	private String templateContent;
	
	/* 消息提醒UUID */
	private String messageRemindUuid;

	public void setTemplateId(String obj) {
		this.templateId = obj;
	}

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateName(String obj) {
		this.templateName = obj;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateType(String obj) {
		this.templateType = obj;
	}

	public String getTemplateType() {
		return this.templateType;
	}

	public void setTemplateState(String obj) {
		this.templateState = obj;
	}

	public String getTemplateState() {
		return this.templateState;
	}

	public void setPosition(int obj) {
		this.position = obj;
	}

	public int getPosition() {
		return this.position;
	}

	public void setTemplateTitle(String obj) {
		this.templateTitle = obj;
	}

	public String getTemplateTitle() {
		return this.templateTitle;
	}

	public void setTemplateContent(String obj) {
		this.templateContent = obj;
	}

	public String getTemplateContent() {
		return this.templateContent;
	}

	public String getMessageRemindUuid() {
		return messageRemindUuid;
	}

	public void setMessageRemindUuid(String messageRemindUuid) {
		this.messageRemindUuid = messageRemindUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[templateId=" + this.getTemplateId() + ",templateName="
				+ this.getTemplateName() + ",templateType="
				+ this.getTemplateType() + ",templateState="
				+ this.getTemplateState() + ",position=" + this.getPosition()
				+ ",templateTitle=" + this.getTemplateTitle()
				+ ",templateContent=" + this.getTemplateContent() + ",]";
	}
}
