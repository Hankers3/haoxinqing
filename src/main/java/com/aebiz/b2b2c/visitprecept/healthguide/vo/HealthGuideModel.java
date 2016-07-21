package com.aebiz.b2b2c.visitprecept.healthguide.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;

/**
 * 健康指导
 * 
 * @author szr
 * 
 */
@Entity
@Table(name = "health_guide")
@Component
public class HealthGuideModel extends BaseModel {

	/* 注入医生service */
	@Transient
	private static ServicestaffService staffService;

	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}

	/* 注入患者service */
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/* 患者名 */
	@Transient
	private String customerName;
	/* 医生名 */
    @Transient
    private String doctorName;
	/* 用户编号 */
	private String customerUuid;
	/* 医生编号 */
	private String serviceStaffUuid;
	/* 饮食指导 */
	private String diet;
	/* 运动指导 */
	private String sports;
	/* 其他指导 */
	private String rest;

	/**
	 *推送周期
	 */
	private Integer period = 0 ;

	/**
	 * 睡眠指导
	 */
	private String sleep;
	/* 创建时间 */
	private String createTime;
	/* 备注 */
	private String note;
	/* 随访编号 */
	private String notevisitRecordUuid;
	
	/* 过期状态 0：未过期 1：已过期  */
	@Transient
	private String state;
	
	/* 运动 过期状态 0：未过期 1：已过期  */
	@Transient
	private String spState;

	/* 其他 过期状态 0：未过期 1：已过期  */
	@Transient
	private String reState;
	
	public String getSpState() {
		return spState;
	}

	public void setSpState(String spState) {
		this.spState = spState;
	}

	public String getReState() {
		return reState;
	}

	public void setReState(String reState) {
		this.reState = reState;
	}
	
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
	
	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}
	
	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setServiceStaffUuid(String obj) {
		this.serviceStaffUuid = obj;
	}

	public String getServiceStaffUuid() {
		return this.serviceStaffUuid;
	}

	public void setDiet(String obj) {
		this.diet = obj;
	}

	public String getDiet() {
		return this.diet;
	}

	public void setSports(String obj) {
		this.sports = obj;
	}

	public String getSports() {
		return this.sports;
	}

	public void setRest(String obj) {
		this.rest = obj;
	}

	public String getRest() {
		return this.rest;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setNotevisitRecordUuid(String obj) {
		this.notevisitRecordUuid = obj;
	}

	public String getNotevisitRecordUuid() {
		return this.notevisitRecordUuid;
	}
    
	public String getCustomerName() {
        if (!StringUtil.isEmpty(customerUuid)) {
                return customerService
                                .getCustomerNameByCustomerUuid(this.customerUuid);
        }
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDoctorName() {
	    if (!StringUtil.isEmpty(this.serviceStaffUuid)) {
	            return staffService
	                            .getServiceStaffNameByUuid(this.serviceStaffUuid);
	    }
	    return doctorName;
    }

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getSleep() {
		return sleep;
	}

	public void setSleep(String sleep) {
		this.sleep = sleep;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[customerUuid=" + this.getCustomerUuid()
				+ ",serviceStaffUuid=" + this.getServiceStaffUuid() + ",diet="
				+ this.getDiet() + ",sports=" + this.getSports() + ",rest="
				+ this.getRest() + ",createTime=" + this.getCreateTime()
				+ ",note=" + this.getNote() + ",notevisitRecordUuid="
				+ this.getNotevisitRecordUuid() + ",]";
	}
}
