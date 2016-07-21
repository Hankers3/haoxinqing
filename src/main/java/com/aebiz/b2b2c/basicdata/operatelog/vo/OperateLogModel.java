package com.aebiz.b2b2c.basicdata.operatelog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 接口调用错误信息记录
 * @author xl
 *
 */
@Entity
@Table(name = "operate_log")
public class OperateLogModel extends BaseModel {
	/* 用户*/
	private String ua;
	/*连接地址 */
	private String url;
	/* 参数 */
	private String param;
	/* 状态*/
	private String state;
	/* 创建时间 */
	private String createTime;
	
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
