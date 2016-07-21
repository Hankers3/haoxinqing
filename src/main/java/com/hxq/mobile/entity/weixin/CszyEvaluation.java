package com.hxq.mobile.entity.weixin;

import com.wxcommon.repository.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Digger
 * 趣味测试结果，保留同一用户对于同一主题的多次测试结果。系统只在用户测试结束时插入数据库，中断则放弃记录。
 */
@SuppressWarnings("serial")
public class CszyEvaluation extends AbstractEntity<String>{
	/* 记录ID 自动生成UUID*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String id;
	/*所属主题，引用测试主体ID(cs_qw_subject.id)*/
	@Column(name="subject_id")
	public String subjectId;
	/*分值范围最小*/
	@Column(name="min_value")
	public String minValue;
	/*分值范围最大*/
	@Column(name="max_value")
	public String maxValue;
	/*普通文字描述*/
	public String result;
	/*普通文字描述*/
	public String analys;
	@Column(name="recommend_title")
	public String recommendTitle;
	@Column(name="recommend_url")
	public String recommendUrl;
	@Column(name="recommend_doctor")
	public String recommendDoctor;

    public CszyEvaluation(){
    	super();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAnalys() {
		return analys;
	}

	public void setAnalys(String analys) {
		this.analys = analys;
	}

	public String getRecommendUrl() {
		return recommendUrl;
	}

	public void setRecommendUrl(String recommendUrl) {
		this.recommendUrl = recommendUrl;
	}

	public String getRecommendTitle() {
		return recommendTitle;
	}

	public void setRecommendTitle(String recommendTitle) {
		this.recommendTitle = recommendTitle;
	}

	public String getRecommendDoctor() {
		return recommendDoctor;
	}

	public void setRecommendDoc(String recommendDoctor) {
		this.recommendDoctor = recommendDoctor;
	}
}
