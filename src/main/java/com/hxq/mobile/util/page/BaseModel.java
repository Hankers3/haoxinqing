package com.hxq.mobile.util.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseModel implements Serializable {
	public static final String DEL_FLAG_VALID = "1";
	public static final String DEL_FLAG_INVALID = "2";
	@Id
	private String uuid;
	@Transient
	private Map<String, Integer> mapCondition = new HashMap<String, Integer>();
	private String oper;
	private String opeTime;
	private String delFlag;
	@Transient
	private String sortName = "uuid";
	@Transient
	private String sortType = "desc";

	public String getSortName() {
		return this.sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortType() {
		return this.sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getOper() {
		return this.oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getOpeTime() {
		return this.opeTime;
	}

	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}

	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Map<String, Integer> getMapCondition() {
		return this.mapCondition;
	}

	public void setMapCondition(Map<String, Integer> mapCondition) {
		this.mapCondition = mapCondition;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.uuid == null ? 0 : this.uuid.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseModel other = (BaseModel) obj;
		if (this.uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!this.uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "BaseModel [uuid=" + this.uuid + ", mapCondition="
				+ this.mapCondition + ", oper=" + this.oper + ", opeTime="
				+ this.opeTime + ", delFlag=" + this.delFlag + ", sortName="
				+ this.sortName + ", sortType=" + this.sortType + "]";
	}
}
