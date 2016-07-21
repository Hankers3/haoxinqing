package com.hxq.mobile.entity.weixin;

import com.wxcommon.repository.AbstractEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * 专业测试结果
 *
 */
@SuppressWarnings("serial")
@Entity(name="cs_zy_result")
public class ProfessionResult extends AbstractEntity<String> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String id;
	@Column(name="subject_id")
	public String subjectId;
	public String openid;
	@Column(columnDefinition="default 0")
	public Integer score;
	@Column(name="complete_date")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="now")
	public Date completeDate;
	@Column(columnDefinition="default 0")
	public Integer integral;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
}
