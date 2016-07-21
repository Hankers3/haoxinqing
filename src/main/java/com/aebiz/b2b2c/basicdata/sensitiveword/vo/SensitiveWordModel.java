package com.aebiz.b2b2c.basicdata.sensitiveword.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "basicdata_sensitive_word")
public class SensitiveWordModel extends BaseModel {
	/* 敏感词内容 */
	private String sensitiveName;

	/* 敏感级别 */
	private String sensitiveLevel;

	/* 替换内容 */
	private String replaceContent;

	/* 备注 */
	private String description;

	@Transient
	private String sensitiveShowName = "";

	public void setSensitiveName(String obj) {
		this.sensitiveName = obj;
	}

	public String getSensitiveName() {
		return this.sensitiveName;
	}

	public void setSensitiveLevel(String obj) {
		this.sensitiveLevel = obj;
	}

	public String getSensitiveLevel() {
		return this.sensitiveLevel;
	}

	public void setReplaceContent(String obj) {
		this.replaceContent = obj;
	}

	public String getReplaceContent() {
		return this.replaceContent;
	}

	public void setDescription(String obj) {
		this.description = obj;
	}

	public String getDescription() {
		return this.description;
	}

	public String getSensitiveShowName() {
		System.out.println("this.getSensitiveLevel()===="
				+ this.getSensitiveLevel());
		System.out.println(SensitiveWordEnum.getNameByKey(this
				.getSensitiveLevel()));
		System.out.println("====================================");
		return SensitiveWordEnum.getNameByKey(this.getSensitiveLevel());
	}

	public void setSensitiveShowName(String sensitiveShowName) {
		this.sensitiveShowName = sensitiveShowName;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[sensitiveName=" + this.getSensitiveName()
				+ ",sensitiveLevel=" + this.getSensitiveLevel()
				+ ",replaceContent=" + this.getReplaceContent()
				+ ",description=" + this.getDescription() + ",]";
	}
}
