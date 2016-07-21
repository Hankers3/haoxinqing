package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 醫院信息表 用來管理醫院信息數據
 */
@SuppressWarnings("serial")
@Entity(name = "hospital_info")
public class HospitalInfo extends AbstractEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;
    @Column(columnDefinition="default 1")
    public String delFlag;
    public String opeTime;
    public String oper;
    /* 医院名 */
    public String hospitalName;
    /* 医院类型 1：综合 2：精神专科 */
    public String hospitalClassify;
    /* 省 */
    public String province;
    /* 市 */
    public String city;
    /* 区县 */
    public String region;
    /* 创建时间 */
    public String createTime;
    /* 备注 */
    public String note;
    /* 详细地址 */
    public String address;
    /* 医院级别 详见枚举 */
    public String hospitalLevel;

    public HospitalInfo() {super();}
    public HospitalInfo(String uuid) {
        super();
        this.uuid = uuid;
    }
    @Override
    public String getId() {
        return uuid;
    }
    @Override
    public void setId(String id) {
        this.uuid = id;
    }
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalClassify() {
		return hospitalClassify;
	}
	public void setHospitalClassify(String hospitalClassify) {
		this.hospitalClassify = hospitalClassify;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHospitalLevel() {
		return hospitalLevel;
	}
	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
}
