package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.SysDeptService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Entity
@Table(name = "sys_manager")
@Component
public class SysManagerModel extends BaseModel {

	@Transient
	private static SysDeptService myService;

	@Autowired
	public void setMyService(SysDeptService bs) {
		this.myService = bs;
	}

	/* 登录帐号 */
	private String id = "";

	/* 管理员姓名 */
	private String name = "";

	/* 管理员密码 */
	private String pwd = "";

	/* 所属部门 */
	private String departmentUuid = "";

	/* 创建时间 */
	private String createTime = "";

	/* 上级领导 */
	private String leaderUuid = "";

	/* 手机号 */
	private String mobile = "";

	/* 管理员激活状态  1.有效  0.无效*/
	private String state = "1";
	
	private String idNo="";

	public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Transient
	private String deptName = "";

	@Transient
	private String confirmPwd = "";
	
	@Transient
	private String[] roleUuids  ;

	public void setId(String obj) {
		this.id = obj;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String obj) {
		this.name = obj;
	}

	public String getName() {
		return this.name;
	}

	public void setPwd(String obj) {
		this.pwd = obj;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setDepartmentUuid(String obj) {
		this.departmentUuid = obj;
	}

	public String getDepartmentUuid() {
		return this.departmentUuid;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setLeaderUuid(String obj) {
		this.leaderUuid = obj;
	}

	public String getLeaderUuid() {
		return this.leaderUuid;
	}

	public void setMobile(String obj) {
		this.mobile = obj;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[id=" + this.getId() + ",name=" + this.getName() + ",pwd="
				+ this.getPwd() + ",departmentUuid=" + this.getDepartmentUuid()
				+ ",createTime=" + this.getCreateTime() + ",leaderUuid="
				+ this.getLeaderUuid() + ",mobile=" + this.getMobile() + ",]";
	}

	public String getDeptName() {
		if (StringUtil.isEmpty(this.getDepartmentUuid())) {
			return "";
		} else {
			return myService.getDeptNameByDeptUuid(this.getDepartmentUuid());
		}
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getRoleUuids() {
		return roleUuids;
	}

	public void setRoleUuids(String[] roleUuids) {
		this.roleUuids = roleUuids;
	}
}
