package com.aebiz.b2b2c.basicdata.province.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

@Entity
@Table(name = "basicdata_area_province")
public class ProvinceModel extends BaseModel {
	
	/*启用状态——启用*/
	public static final String INITITATE_EBLE = "1";
	
	/*启用状态——不启用 */
	public static final String INITITATE_UNEBLE = "0";
	
	/* 省名称 */
	private String provinceName;
	
	/* 启用状态 */
	private String state;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setProvinceName(String obj) {
		this.provinceName = obj;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[provinceName=" + this.getProvinceName() + 
				"state=" + this.getState() +
				",]";
	}
}
