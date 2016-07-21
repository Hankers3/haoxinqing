package com.hxq.mobile.entity.visit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;

/**
 * 患者病历信息表
 */
@SuppressWarnings("serial")
@Entity(name="medical_record")
public class MedicalRecord extends AbstractEntity<String> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	public String uuid;
	public String oper;
	public String opeTime;
	@Column(columnDefinition="default 1")
	public String delFlag;
	/* 病例类型 0住院号 1门诊号 2床位号 3病案号 4其他 */
	public String caseCategoryType;
	/* 编号 ?意义不明? */
	public String medicalNum;
	/* 患者 */
	public String customerUuid;
	/* 患者名 */
	@Transient
	public String customerName;
	/* 病例分类 */
	public String caseCategoryUuid;
	/* 医院 */
	public String hospitalUuid;
	/* 医院名 */
	@Transient
	public String hospitalName;
	/* 科室 */
	public String divisionUuid;
	/* 医生 */
	public String doctorUuid;
	/* 创建时间 */
	public String createTime;
	/* 医生名 */
	@Transient
	public String doctorName;
	/* 随访编号 */
	public String visitRecordUuid;
	/* 主诉 */
	public String mainsuit;
	/* 现病史 */
	public String illness;
	/* 既往史 */
	public String previous;
	/* 个人史 */
	public String personal;
	/* 家族史 */
	public String family;
	/* 躯体状况 */
	public String somastate;
	/* 精神检查 */
	public String spiritCheck;
	/* 辅助检查 */
	public String assistCheck;
	/* 辅助检查 图片 */
	public String assistCheckImage;
	/* 异常结果 */
	public String abnormal;
	/* 诊断 */
	public String diagnosis;
	/* 诊断图片 */
	public String diagnosisImage;
	/* 日期 */
	public String date;
	/* 病程 */
	public String process;
	/* 发作次数 */
	@Column(columnDefinition="default 0")
	public Integer attackNum;
	/* 共病 */
	public String comorbidity;
	/* 合并症 */
	public String complication;
	/* 量表测评 */
	public String scaleAppraisal;
	/* 治疗方案 */
	public String curePreceptUuid;
	/* 治疗经过 */
	public String cureCourse;
	/* 治疗经过 */
	public String cureCourseImage;
	/* 住院时间 */
	public String startTime;
	/* 出院时间 */
	public String endTime;
	/* 就诊时间 */
	public String seeDoctorTime;
	/* 辅助检查 */
	public String preAssistCheck;
	public String preAssistCheckImage;
	/* 图片1 */
	public String image1;
	/* 图片2 */
	public String image2;
	/* 图片3 */
	public String image3;
	/* 图片4 */
	public String image4;
	/* 图片5 */
	public String image5;
	/* 随访方案编号 */
	public String visitPreceptUuid;

	public MedicalRecord() {super();}
	public MedicalRecord(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return this.uuid;
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
	public String getCaseCategoryType() {
		return caseCategoryType;
	}
	public void setCaseCategoryType(String caseCategoryType) {
		this.caseCategoryType = caseCategoryType;
	}
	public String getMedicalNum() {
		return medicalNum;
	}
	public void setMedicalNum(String medicalNum) {
		this.medicalNum = medicalNum;
	}
	public String getCustomerUuid() {
		return customerUuid;
	}
	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCaseCategoryUuid() {
		return caseCategoryUuid;
	}
	public void setCaseCategoryUuid(String caseCategoryUuid) {
		this.caseCategoryUuid = caseCategoryUuid;
	}
	public String getHospitalUuid() {
		return hospitalUuid;
	}
	public void setHospitalUuid(String hospitalUuid) {
		this.hospitalUuid = hospitalUuid;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDivisionUuid() {
		return divisionUuid;
	}
	public void setDivisionUuid(String divisionUuid) {
		this.divisionUuid = divisionUuid;
	}
	public String getDoctorUuid() {
		return doctorUuid;
	}
	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}
	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}
	public String getMainsuit() {
		return mainsuit;
	}
	public void setMainsuit(String mainsuit) {
		this.mainsuit = mainsuit;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getSomastate() {
		return somastate;
	}
	public void setSomastate(String somastate) {
		this.somastate = somastate;
	}
	public String getSpiritCheck() {
		return spiritCheck;
	}
	public void setSpiritCheck(String spiritCheck) {
		this.spiritCheck = spiritCheck;
	}
	public String getAssistCheck() {
		return assistCheck;
	}
	public void setAssistCheck(String assistCheck) {
		this.assistCheck = assistCheck;
	}
	public String getAssistCheckImage() {
		return assistCheckImage;
	}
	public void setAssistCheckImage(String assistCheckImage) {
		this.assistCheckImage = assistCheckImage;
	}
	public String getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(String abnormal) {
		this.abnormal = abnormal;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDiagnosisImage() {
		return diagnosisImage;
	}
	public void setDiagnosisImage(String diagnosisImage) {
		this.diagnosisImage = diagnosisImage;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public Integer getAttackNum() {
		return attackNum;
	}
	public void setAttackNum(Integer attackNum) {
		this.attackNum = attackNum;
	}
	public String getComorbidity() {
		return comorbidity;
	}
	public void setComorbidity(String comorbidity) {
		this.comorbidity = comorbidity;
	}
	public String getComplication() {
		return complication;
	}
	public void setComplication(String complication) {
		this.complication = complication;
	}
	public String getScaleAppraisal() {
		return scaleAppraisal;
	}
	public void setScaleAppraisal(String scaleAppraisal) {
		this.scaleAppraisal = scaleAppraisal;
	}
	public String getCurePreceptUuid() {
		return curePreceptUuid;
	}
	public void setCurePreceptUuid(String curePreceptUuid) {
		this.curePreceptUuid = curePreceptUuid;
	}
	public String getCureCourse() {
		return cureCourse;
	}
	public void setCureCourse(String cureCourse) {
		this.cureCourse = cureCourse;
	}
	public String getCureCourseImage() {
		return cureCourseImage;
	}
	public void setCureCourseImage(String cureCourseImage) {
		this.cureCourseImage = cureCourseImage;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSeeDoctorTime() {
		return seeDoctorTime;
	}
	public void setSeeDoctorTime(String seeDoctorTime) {
		this.seeDoctorTime = seeDoctorTime;
	}
	public String getPreAssistCheck() {
		return preAssistCheck;
	}
	public void setPreAssistCheck(String preAssistCheck) {
		this.preAssistCheck = preAssistCheck;
	}
	public String getPreAssistCheckImage() {
		return preAssistCheckImage;
	}
	public void setPreAssistCheckImage(String preAssistCheckImage) {
		this.preAssistCheckImage = preAssistCheckImage;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	public String getVisitPreceptUuid() {
		return visitPreceptUuid;
	}
	public void setVisitPreceptUuid(String visitPreceptUuid) {
		this.visitPreceptUuid = visitPreceptUuid;
	}
}
