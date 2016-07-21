package com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
/**
 * 测试结果和医生关联关系表
 * @author xueli
 *
 */
@Entity
@Table(name="quiz_result_doc_rel")
@Component
public class QuizResultDocRelModel extends BaseModel{
	@Transient
	private static ServicestaffinfoService servicestaffinfoService;
	@Autowired
	private void setServicestaffinfoService(ServicestaffinfoService ss){
		this.servicestaffinfoService = ss;
	}
	
	@Transient
	private static ServicestaffService servicestaffService;
	@Autowired
	private void setServicestaffService(ServicestaffService servicestaffService){
		this.servicestaffService = servicestaffService;
	}
	
	private String quizResultUuid;
	private String serviceStaffInfoId;
	private String createTime;
	
	@Transient
	private ServicestaffinfoModel servicestaffinfo;
	
	@Transient
	private ServicestaffModel servicestaff;
	
	public ServicestaffModel getServicestaff() {
		servicestaff = servicestaffService.getByUuid(serviceStaffInfoId);
		return servicestaff;
	}
	public void setServicestaff(ServicestaffModel servicestaff) {
		this.servicestaff = servicestaff;
	}

	public ServicestaffinfoModel getServicestaffinfo() {
		servicestaffinfo =	servicestaffinfoService.getServicestaffinfoModelByServicestaffUuid(serviceStaffInfoId);
		if(servicestaffinfo==null){
			return new ServicestaffinfoModel();
		}
		return servicestaffinfo;
	}
	public void setServicestaffinfo(ServicestaffinfoModel servicestaffinfo) {
		this.servicestaffinfo = servicestaffinfo;
	}
	public void setQuizResultUuid(String obj){
		
		this.quizResultUuid = obj;
	}
	public String getQuizResultUuid(){
		
		return this.quizResultUuid;
	}
	
	public void setServiceStaffInfoId(String obj){
		this.serviceStaffInfoId = obj;
	}
	public String getServiceStaffInfoId(){
		return this.serviceStaffInfoId;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[quizResultUuid=" + this.getQuizResultUuid() + ",serviceStaffInfoId=" + this.getServiceStaffInfoId() + ",createTime=" + this.getCreateTime() + ",]";
	}	
}
