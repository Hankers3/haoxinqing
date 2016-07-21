package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wxcommon.repository.AbstractEntity;

/**
 * 科室表信息  用來添加管理醫生科室信息
 *
 * @author xueli
 */
@SuppressWarnings("serial")
@Entity(name = "department_info")
public class DepartmentInfo extends AbstractEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    public String uuid;
    @Column(columnDefinition="default 1")
    public String delFlag;
    public String opeTime;
    public String oper;
    /*科室名称*/
    public String departmentName;
    /*科室电话*/
    public String mobile;
    /*创建时间*/
    public String createTime;
    /*科室ID*/
    public String departmentId;
    /*备注*/
    public String note;

    public DepartmentInfo() {
        super();
    }
    public DepartmentInfo(String uuid) {
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
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}

