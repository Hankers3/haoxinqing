package com.aebiz.b2b2c.visitprecept.medicalrecord.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.servicestaff.departmentinfo.service.DepartmentInfoService;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.visitprecept.casecategory.service.CaseCategoryService;

/**
 * 患者病历信息表
 * 
 * @author xueli
 * 
 */
@Entity
@Table(name = "medical_record")
@Component
public class MedicalRecordModel extends BaseModel {
	/* 注入会员service */
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/* 注入会员service */
	@Transient
	private static CustomerInfoService customerInfoService;

	@Autowired
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	/* 注入医院service */
	@Transient
	private static HospitalInfoService hospitalService;

	@Autowired
	public void setHospitalService(HospitalInfoService hospitalService) {
		this.hospitalService = hospitalService;
	}

	/* 注入科室service */
	@Transient
	private static DepartmentInfoService departmentService;

	@Autowired
	public void setDepartmentService(DepartmentInfoService departmentService) {
		this.departmentService = departmentService;
	}

	/* 注入医生service */
	@Transient
	private static ServicestaffService staffService;

	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}

	/* 注入病例分类service */
	@Transient
	private static CaseCategoryService caseCategoryService;

	@Autowired
	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

	/* 注解文件的service */
	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/* 病例类型 0住院号 1门诊号 2床位号 3病案号 4其他 */
	private String caseCategoryType;

	/* 编号 ?意义不明? */
	private String medicalNum;

	/* 患者 */
	private String customerUuid;

	/* 患者名 */
	@Transient
	private String customerName;

	/* 病例分类 */
	private String caseCategoryUuid;
	/* 病例分类名 */
	@Transient
	private String caseCategoryName;

	/* 医院 */
	private String hospitalUuid;

	/* 医院名 */
	@Transient
	private String hospitalName;

	/* 科室 */
	private String divisionUuid;

	/* 科室名 */
	@Transient
	private String divisionName;

	/* 医生 */
	private String doctorUuid;

	/* 创建时间 */
	private String createTime;

	/* 医生名 */
	@Transient
	private String doctorName;

	/* 随访编号 */
	private String visitRecordUuid;

	/* 主诉 */
	private String mainsuit;

	/* 现病史 */
	private String illness;

	/* 既往史 */
	private String previous;

	/* 个人史 */
	private String personal;

	/* 家族史 */
	private String family;

	/* 躯体状况 */
	private String somastate;

	/* 精神检查 */
	private String spiritCheck;

	/* 辅助检查 */
	private String assistCheck;

	/* 辅助检查 图片 */
	private String assistCheckImage;
	/* 异常结果 */
	private String abnormal;

	/* 诊断 */
	private String diagnosis;
	/* 诊断图片 */
	private String diagnosisImage;
	/* 日期 */
	private String date;

	/* 病程 */
	private String process;

	/* 发作次数 */
	private int attackNum;

	/* 共病 */
	private String comorbidity;

	/* 合并症 */
	private String complication;

	/* 量表测评 */
	private String scaleAppraisal;

	/* 治疗方案 */
	private String curePreceptUuid;

	/* 治疗经过 */
	private String cureCourse;

	/* 治疗经过 */
	private String cureCourseImage;

	/* 住院时间 */
	private String startTime;

	/* 出院时间 */
	private String endTime;

	/* 就诊时间 */
	private String seeDoctorTime;

	/* 辅助检查 */
	private String preAssistCheck;

	private String preAssistCheckImage;
	/* 图片1 */
	private String image1;

	/* 图片2 */
	private String image2;

	/* 图片3 */
	private String image3;

	/* 图片4 */
	private String image4;

	/* 图片5 */
	private String image5;

	/* 随访方案编号 */
	private String visitPreceptUuid;

	/* 图片上传路径 */
	@Transient
	private String img1Url = "";

	@Transient
	private String img2Url = "";

	@Transient
	private String img3Url = "";

	@Transient
	private String img4Url = "";

	@Transient
	private String img5Url = "";

	// 医生Id
	@Transient
	private String doctorNo;

	// 患者Id
	@Transient
	private String cutomreId;

	@Transient
	private String assistCheckImgUrl = "";

	@Transient
	private String diagnosisImgUrl = "";

	@Transient
	private String preAssistCheckImgUrl = "";

	@Transient
	private String cureCourseImgUrl = "";

	public String getPreAssistCheckImgUrl() {
		if (StringUtil.isEmpty(this.preAssistCheckImage)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.preAssistCheckImage);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPreAssistCheckImage();
		}
	}

	public void setPreAssistCheckImgUrl(String preAssistCheckImgUrl) {
		this.preAssistCheckImgUrl = preAssistCheckImgUrl;
	}

	public String getCureCourseImgUrl() {
		if (StringUtil.isEmpty(this.cureCourseImage)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.cureCourseImage);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getCureCourseImage();
		}
	}

	public void setCureCourseImgUrl(String cureCourseImgUrl) {
		this.cureCourseImgUrl = cureCourseImgUrl;
	}

	public String getCureCourseImage() {
		return cureCourseImage;
	}

	public void setCureCourseImage(String cureCourseImage) {
		this.cureCourseImage = cureCourseImage;
	}

	public String getPreAssistCheckImage() {
		return preAssistCheckImage;
	}

	public void setPreAssistCheckImage(String preAssistCheckImage) {
		this.preAssistCheckImage = preAssistCheckImage;
	}

	public String getVisitPreceptUuid() {
		return visitPreceptUuid;
	}

	public void setVisitPreceptUuid(String visitPreceptUuid) {
		this.visitPreceptUuid = visitPreceptUuid;
	}

	public String getAssistCheckImgUrl() {
		if (StringUtil.isEmpty(this.assistCheckImage)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.assistCheckImage);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getAssistCheckImage();
		}
	}

	public void setAssistCheckImgUrl(String assistCheckImgUrl) {
		this.assistCheckImgUrl = assistCheckImgUrl;
	}

	public String getDiagnosisImgUrl() {
		if (StringUtil.isEmpty(this.diagnosisImage)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.diagnosisImage);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getDiagnosisImage();
		}
	}

	public void setDiagnosisImgUrl(String diagnosisImgUrl) {

		this.diagnosisImgUrl = diagnosisImgUrl;
	}

	public String getAssistCheckImage() {
		return assistCheckImage;
	}

	public void setAssistCheckImage(String assistCheckImage) {
		this.assistCheckImage = assistCheckImage;
	}

	public String getDiagnosisImage() {
		return diagnosisImage;
	}

	public void setDiagnosisImage(String diagnosisImage) {
		this.diagnosisImage = diagnosisImage;
	}

	public String getDoctorNo() {
		if (!StringUtil.isEmpty(this.doctorUuid)) {
			return staffService.getDoctorNoByUuid(doctorUuid);
		}
		return doctorNo;
	}

	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}

	public String getCutomreId() {
		if (StringUtil.isEmpty(customerUuid)) {
			return customerService.getCustomerId(this.customerUuid);
		}
		return cutomreId;
	}

	public void setCutomreId(String cutomreId) {
		this.cutomreId = cutomreId;
	}

	public String getSeeDoctorTime() {
		return seeDoctorTime;
	}

	public void setSeeDoctorTime(String seeDoctorTime) {
		this.seeDoctorTime = seeDoctorTime;
	}

	public String getMedicalNum() {
		return medicalNum;
	}

	public void setMedicalNum(String medicalNum) {
		this.medicalNum = medicalNum;
	}

	public String getImg1Url() {
		if (StringUtil.isEmpty(this.image1)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image1);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getImage1();
		}
	}

	public String getImg2Url() {

		if (StringUtil.isEmpty(this.image2)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image2);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getImage2();
		}
	}

	public void setImg2Url(String img2Url) {
		this.img2Url = img2Url;
	}

	public String getImg3Url() {
		if (StringUtil.isEmpty(this.image3)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image3);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getImage3();
		}
	}

	public void setImg3Url(String img3Url) {
		this.img3Url = img3Url;
	}

	public String getImg4Url() {
		if (StringUtil.isEmpty(this.image4)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image4);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getImage4();
		}
	}

	public void setImg4Url(String img4Url) {
		this.img4Url = img4Url;
	}

	public String getImg5Url() {
		if (StringUtil.isEmpty(this.image5)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image5);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getImage5();
		}
	}

	public void setImg5Url(String img5Url) {
		this.img5Url = img5Url;
	}

	public void setImg1Url(String img1Url) {
		this.img1Url = img1Url;
	}

	public String getCustomerName() {
		if (!StringUtil.isEmpty(this.customerUuid)) {
			return customerInfoService.getRealNameByUuid(customerUuid);
		}
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getHospitalName() {
		if (!StringUtil.isEmpty(this.hospitalUuid)) {
			String hName=hospitalService.getHospitalNameByUuid(hospitalUuid);
			if(!StringUtil.isEmpty(hName)){
				return hName;
			}else{
				return hospitalUuid;
			}
		}
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDivisionName() {
		if (!StringUtil.isEmpty(this.divisionUuid)) {
			return departmentService.getDepartmentNameByUuid(divisionUuid);
		}
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDoctorName() {
		if (!StringUtil.isEmpty(this.doctorUuid)) {
			return staffService.getServiceStaffNameByUuid(this.doctorUuid);
		}
		return doctorName;
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

	public void setCaseCategoryUuid(String obj) {
		this.caseCategoryUuid = obj;
	}

	public String getCaseCategoryUuid() {
		return this.caseCategoryUuid;
	}

	public void setHospitalUuid(String obj) {
		this.hospitalUuid = obj;
	}

	public String getHospitalUuid() {
		return this.hospitalUuid;
	}

	public void setDivisionUuid(String obj) {
		this.divisionUuid = obj;
	}

	public String getDivisionUuid() {
		return this.divisionUuid;
	}

	public void setDoctorUuid(String obj) {
		this.doctorUuid = obj;
	}

	public String getDoctorUuid() {
		return this.doctorUuid;
	}

	public void setVisitRecordUuid(String obj) {
		this.visitRecordUuid = obj;
	}

	public String getVisitRecordUuid() {
		return this.visitRecordUuid;
	}

	public void setMainsuit(String obj) {
		this.mainsuit = obj;
	}

	public String getMainsuit() {
		return this.mainsuit;
	}

	public void setIllness(String obj) {
		this.illness = obj;
	}

	public String getIllness() {
		return this.illness;
	}

	public void setPrevious(String obj) {
		this.previous = obj;
	}

	public String getPrevious() {
		return this.previous;
	}

	public void setPersonal(String obj) {
		this.personal = obj;
	}

	public String getPersonal() {
		return this.personal;
	}

	public void setFamily(String obj) {
		this.family = obj;
	}

	public String getFamily() {
		return this.family;
	}

	public void setSomastate(String obj) {
		this.somastate = obj;
	}

	public String getSomastate() {
		return this.somastate;
	}

	public void setSpiritCheck(String obj) {
		this.spiritCheck = obj;
	}

	public String getSpiritCheck() {
		return this.spiritCheck;
	}

	public void setAssistCheck(String obj) {
		this.assistCheck = obj;
	}

	public String getAssistCheck() {
		return this.assistCheck;
	}

	public void setAbnormal(String obj) {
		this.abnormal = obj;
	}

	public String getAbnormal() {
		return this.abnormal;
	}

	public void setDiagnosis(String obj) {
		this.diagnosis = obj;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDate(String obj) {
		this.date = obj;
	}

	public String getDate() {
		return this.date;
	}

	public void setProcess(String obj) {
		this.process = obj;
	}

	public String getProcess() {
		return this.process;
	}

	public void setAttackNum(int obj) {
		this.attackNum = obj;
	}

	public int getAttackNum() {
		return this.attackNum;
	}

	public void setComorbidity(String obj) {
		this.comorbidity = obj;
	}

	public String getComorbidity() {
		return this.comorbidity;
	}

	public void setComplication(String obj) {
		this.complication = obj;
	}

	public String getComplication() {
		return this.complication;
	}

	public void setScaleAppraisal(String obj) {
		this.scaleAppraisal = obj;
	}

	public String getScaleAppraisal() {
		return this.scaleAppraisal;
	}

	public void setCurePreceptUuid(String obj) {
		this.curePreceptUuid = obj;
	}

	public String getCurePreceptUuid() {
		return this.curePreceptUuid;
	}

	public void setCureCourse(String obj) {
		this.cureCourse = obj;
	}

	public String getCureCourse() {
		return this.cureCourse;
	}

	public void setStartTime(String obj) {
		this.startTime = obj;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setEndTime(String obj) {
		this.endTime = obj;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setPreAssistCheck(String obj) {
		this.preAssistCheck = obj;
	}

	public String getPreAssistCheck() {
		return this.preAssistCheck;
	}

	public void setImage1(String obj) {
		this.image1 = obj;
	}

	public String getImage1() {
		return this.image1;
	}

	public void setImage2(String obj) {
		this.image2 = obj;
	}

	public String getImage2() {
		return this.image2;
	}

	public void setImage3(String obj) {
		this.image3 = obj;
	}

	public String getImage3() {
		return this.image3;
	}

	public void setImage4(String obj) {
		this.image4 = obj;
	}

	public String getImage4() {
		return this.image4;
	}

	public void setImage5(String obj) {
		this.image5 = obj;
	}

	public String getImage5() {
		return this.image5;
	}

	public String getCaseCategoryType() {
		return caseCategoryType;
	}

	public void setCaseCategoryType(String caseCategoryType) {
		this.caseCategoryType = caseCategoryType;
	}

	public String getCaseCategoryName() {
		if (!StringUtil.isEmpty(this.caseCategoryUuid)) {
			return caseCategoryService.getNameByUuid(this.caseCategoryUuid);
		}
		return caseCategoryName;
	}

	public void setCaseCategoryName(String caseCategoryName) {
		this.caseCategoryName = caseCategoryName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[customerUuid=" + this.getCustomerUuid()
				+ ",caseCategoryUuid=" + this.getCaseCategoryUuid() + ",hospitalUuid=" + this.getHospitalUuid()
				+ ",divisionUuid=" + this.getDivisionUuid() + ",doctorUuid=" + this.getDoctorUuid()
				+ ",visitRecordUuid=" + this.getVisitRecordUuid() + ",mainsuit=" + this.getMainsuit() + ",illness="
				+ this.getIllness() + ",previous=" + this.getPrevious() + ",personal=" + this.getPersonal() + ",family="
				+ this.getFamily() + ",somastate=" + this.getSomastate() + ",spiritCheck=" + this.getSpiritCheck()
				+ ",assistCheck=" + this.getAssistCheck() + ",abnormal=" + this.getAbnormal() + ",diagnosis="
				+ this.getDiagnosis() + ",date=" + this.getDate() + ",process=" + this.getProcess() + ",attackNum="
				+ this.getAttackNum() + ",comorbidity=" + this.getComorbidity() + ",complication="
				+ this.getComplication() + ",scaleAppraisal=" + this.getScaleAppraisal() + ",curePreceptUuid="
				+ this.getCurePreceptUuid() + ",cureCourse=" + this.getCureCourse() + ",startTime="
				+ this.getStartTime() + ",endTime=" + this.getEndTime() + ",preAssistCheck=" + this.getPreAssistCheck()
				+ ",image1=" + this.getImage1() + ",image2=" + this.getImage2() + ",image3=" + this.getImage3()
				+ ",image4=" + this.getImage4() + ",image5=" + this.getImage5() + ",]";
	}
}
