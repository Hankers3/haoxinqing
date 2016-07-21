package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 邀请码信息
 */
@SuppressWarnings("serial")
@Entity(name="active_code")
public class ActiveCode extends AbstractEntity<String> {

	/* 编号 */
	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="code")
	public String code;
	public String usr;
	
	public ActiveCode() {super();}
	public ActiveCode(String id) {
		super();
		this.code = id;
	}
	@Override
	public String getId() {
		return code;
	}
	@Override
	public void setId(String id) {
		this.code = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
}
