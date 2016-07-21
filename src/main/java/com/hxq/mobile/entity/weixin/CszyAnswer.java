package com.hxq.mobile.entity.weixin;

import com.wxcommon.repository.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Digger
 * 趣味测试答题，保留同一用户对于同一主题的多次测试结果。系统只在用户测试结束时插入数据库，中断则放弃记录。
 */
@SuppressWarnings("serial")
public class CszyAnswer extends AbstractEntity<String>{
	/* 记录ID 自动生成UUID*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String id;
	/*结果ID，引用测试结果ID（cs_qw_result.id）*/
	@Column(name="result_id")
	public String resultId;
	/*题目ID，引用测试题目ID（cs_qw_item.id）*/
	@Column(name="item_id")
	public String itemId;
	/*答复分值或选项*/
	public String answer1;
	/*补充说明*/
	public String answer2;

    public CszyAnswer(){
    	super();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

}
