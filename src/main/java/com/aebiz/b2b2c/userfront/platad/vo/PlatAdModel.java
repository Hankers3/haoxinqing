package com.aebiz.b2b2c.userfront.platad.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 平台广告管理<br>
 * 
 * 广告关联图片,不用管图片的效果,只需关联上图片即可,图片的效果由广告组件来做
 *
 * @author tangyunkai
 *
 * @date 2015年1月6日 下午8:32:58
 *
 */
@Entity
@Table(name = "plat_ad")
public class PlatAdModel extends BaseModel {

	/* 广告名称 */
	private String adName = "";

	/* 位置 */
	private int position;

	/* 创建时间 */
	private String createTime = "";

	/* 广告介绍 */
	private String note = "";

	/**/

	public void setAdName(String obj) {
		this.adName = obj;
	}
	public String getAdName() {
		return this.adName;
	}

	public void setPosition(int obj) {
		this.position = obj;
	}
	public int getPosition() {
		return this.position;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}
	public String getCreateTime() {
		return this.createTime;
	}

	public void setNote(String obj) {
		this.note = obj;
	}
	public String getNote() {
		return this.note;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[adName=" + this.getAdName() + ",position="
				+ this.getPosition() + ",createTime=" + this.getCreateTime()
				+ ",note=" + this.getNote() + ",]";
	}
}
