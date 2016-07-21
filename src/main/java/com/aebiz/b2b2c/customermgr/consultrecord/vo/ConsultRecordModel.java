package com.aebiz.b2b2c.customermgr.consultrecord.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.DoctorTagModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;

/**
 * 咨询记录表 记录咨询记录的信息
 * 
 * 
 */
@Entity
@Table(name = "consult_record")
@Component
public class ConsultRecordModel extends BaseModel {
	/* 咨询类型 在线咨询即图文咨询 1电话咨询 2预约加号 */
	public static final String TYPE_ONLINE = "0";
	public static final String TYPE_TEL = "1";
	public static final String TYPE_ORDER = "2";
	/* 注入标签库service */
	@Transient
	private static TagsService tagsService;

	@Autowired
	public void setTagsService(TagsService tagsService) {
		this.tagsService = tagsService;
	}

	// 注入患者的service
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 注入医生的service
	@Transient
	private static ServicestaffService servicestaffService;

	@Autowired
	public void setServicestaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}

	// 注入医生的service
	@Transient
	private static ServicestaffinfoService servicestaffinfoService;

	@Autowired
	public void setServicestaffinfoService(ServicestaffinfoService servicestaffinfoService) {
		this.servicestaffinfoService = servicestaffinfoService;
	}

	// 注入医生的权限service
	@Transient
	private static DoctorRightService doctorRightService;

	@Autowired
	public void setDoctorRightService(DoctorRightService doctorRightService) {
		this.doctorRightService = doctorRightService;
	}

	/* 注解文件的service */
	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/* 在线咨询几次 */
	private int num = 0;
	/* 患者编号 */
	private String customerUuid;
	/* 医生编号 */
	private String doctorUuid;
	/* 标签的编号 */
	private String tagsUuid;
	/* 内容 */
	private String content;
	/* 图片 */
	private String picture;
	/* 图片 */
	private String picture1;
	/* 图片 */
	private String picture2;
	/* 图片 */
	private String picture3;
	/* 图片 */
	private String picture4;
	/* 咨询时间 */
	private String createTime;

	/* 咨询j */
	private String endTime;

	/* 类型 (现在改为1是医生发送的消息,2是患者发送的消息) */
	private String consultType;
	/* 拒绝理由 */
	private String refuseReason;
	/* 加号管理：医生审核状态 0: 未审核 1:审核 2:审核未通过 */
	private String dealState; // 其他： 0：未处理。1：已处理
	/* 回复 */
	private String reply; // 0: 未回复 1：回复
	/* 平台审核 审核状态 */
	private String state; // 0: 未审核 1：审核 2：审核未通过 加号管理时，作为平台审核

	/* 审核类型：0：平台审核 1：医生审核 */
	private String exam;

	/* 电话咨询编号 */
	private String teleCounUuid;
	/* 回复内容字段 */
	private String replyContent;
	/* 时间类型1：全天 ,2：上午,3：下午 */
	private String timeType;

	/* 患者的姓名字段 */
	@Transient
	private String customerName;

	/* 患者的手机字段 */
	@Transient
	private String customerMobile;

	/* 医生的姓名字段 */
	@Transient
	private String docoterName;

	/* 医生的手机字段 */
	@Transient
	private String docoterMobile;

	/* 医生ID */
	@Transient
	private String docoterNo;

	/* 加号：就诊时间 */
	private String seeDoctorTime;
	/* 加号：疾病 */
	private String orderIllness;
	/* 加号：预约目的 */
	private String orderReason;
	/* 加号：疾病描述 */
	private String illnessDescription;
	/* 加号：加号说明 */
	private String plusNote;
	
	//是否已读 0未读,1已读
	private String ifread;

	/* 加号：截取15字的预约目的 */
	@Transient
	private String subOrderReason;

	/* 医生所在医院的名称 */
	@Transient
	private String hospitalName;

	/* 图片上传路径 */
	@Transient
	private String pic1Url = "";

	@Transient
	private String pic2Url = "";

	@Transient
	private String pic3Url = "";

	@Transient
	private String pic4Url = "";

	@Transient
	private String picUrl = "";

	private Integer iquestion; //是否是问题
	
	
	/* 标签名和id */
	@Transient
	private List<DoctorTagModel> consultRecordTags;

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public List<DoctorTagModel> getConsultRecordTags() {
		List<DoctorTagModel> tagList = new ArrayList<DoctorTagModel>();
		if (!StringUtil.isEmpty(tagsUuid)) {
			String[] tagUuids = tagsUuid.split(";");
			if (tagUuids != null && tagUuids.length > 0) {
				for (int i = 0; i < tagUuids.length; i++) {
					DoctorTagModel dt = new DoctorTagModel();
					String uuid = tagUuids[i];
					if (!StringUtil.isEmpty(uuid)) {
						String tagName = tagsService.getTagNameByUuid(uuid);
						dt.setTagName(tagName);
						dt.setTagUuid(uuid);
						tagList.add(dt);
					}

				}
			}
			return tagList;
		}
		return consultRecordTags;
	}

	public void setConsultRecordTags(List<DoctorTagModel> consultRecordTags) {
		this.consultRecordTags = consultRecordTags;
	}

	public String getTagsUuid() {
		return tagsUuid;
	}

	public void setTagsUuid(String tagsUuid) {
		this.tagsUuid = tagsUuid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPic1Url() {
		if (StringUtil.isEmpty(this.picture1)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.picture1);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPicture1();
		}
	}

	public void setPic1Url(String pic1Url) {
		this.pic1Url = pic1Url;
	}

	public String getPic2Url() {
		if (StringUtil.isEmpty(this.picture2)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.picture2);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPicture2();
		}
	}

	public void setPic2Url(String pic2Url) {
		this.pic2Url = pic2Url;
	}

	public String getPic3Url() {
		if (StringUtil.isEmpty(this.picture3)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.picture3);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPicture3();
		}
	}

	public void setPic3Url(String pic3Url) {
		this.pic3Url = pic3Url;
	}

	public String getPic4Url() {
		if (StringUtil.isEmpty(this.picture4)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.picture4);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPicture4();
		}
	}

	public void setPic4Url(String pic4Url) {
		this.pic4Url = pic4Url;
	}

	public String getPicUrl() {
		if (StringUtil.isEmpty(this.picture)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.picture);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getPicture();
		}

	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPicture1() {
		return picture1;
	}

	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public String getPicture2() {
		return picture2;
	}

	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}

	public String getPicture3() {
		return picture3;
	}

	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}

	public String getPicture4() {
		return picture4;
	}

	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}

	public String getConsultType() {
		return consultType;
	}

	public void setConsultType(String consultType) {
		this.consultType = consultType;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getExam() {
		return this.exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getSubOrderReason() {

		if (!StringUtil.isEmpty(this.orderReason)) {
			if (this.orderReason.length() > 15) {
				return this.orderReason.substring(0, 14) + "...";
			}
		}
		return this.orderReason;
	}

	public void setSubOrderReason(String subOrderReason) {
		this.subOrderReason = subOrderReason;
	}

	public String getSeeDoctorTime() {
		return seeDoctorTime;
	}

	public void setSeeDoctorTime(String seeDoctorTime) {
		this.seeDoctorTime = seeDoctorTime;
	}

	public String getOrderIllness() {
		return orderIllness;
	}

	public void setOrderIllness(String orderIllness) {
		this.orderIllness = orderIllness;
	}

	public String getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}

	public String getIllnessDescription() {
		return illnessDescription;
	}

	public void setIllnessDescription(String illnessDescription) {
		this.illnessDescription = illnessDescription;
	}

	public String getPlusNote() {
		return plusNote;
	}

	public void setPlusNote(String plusNote) {
		this.plusNote = plusNote;
	}

	public String getDocoterNo() {

		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel s = servicestaffService.getByUuid(doctorUuid);
			if (s != null) {
				return s.getDoctorNo();
			}
		}
		return docoterNo;
	}

	public void setDocoterNo(String docoterNo) {
		this.docoterNo = docoterNo;
	}

	public String getHospitalName() {
		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel s = servicestaffService.getByUuid(doctorUuid);
			if (s != null) {
				return s.getHospitalName();
			}
		}

		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDocoterMobile() {
		if (!StringUtil.isEmpty(doctorUuid)) {
			return servicestaffService.getMobileByUuid(doctorUuid);
		}

		return docoterMobile;
	}

	public void setDocoterMobile(String docoterMobile) {
		this.docoterMobile = docoterMobile;
	}

	public String getCustomerMobile() {
		if (!StringUtil.isEmpty(customerUuid)) {
			return customerService.getCustomerMobileByUuid(customerUuid);
		}

		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getDocoterName() {
		if (!StringUtil.isEmpty(doctorUuid)) {
			return servicestaffService.getServiceStaffNameByUuid(doctorUuid);
		}
		return docoterName;
	}

	public void setDocoterName(String docoterName) {
		this.docoterName = docoterName;
	}

	public String getCustomerName() {
		if (!StringUtil.isEmpty(customerUuid)) {
			return customerService.getCustomerNameByCustomerUuid(customerUuid);
		}
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setCustomerUuid(String obj) {
		this.customerUuid = obj;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setDoctorUuid(String obj) {
		this.doctorUuid = obj;
	}

	public String getDoctorUuid() {
		return this.doctorUuid;
	}

	public void setContent(String obj) {
		this.content = obj;
	}

	public String getContent() {
		return this.content;
	}

	public void setPicture(String obj) {
		this.picture = obj;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setDealState(String obj) {
		this.dealState = obj;
	}

	public String getDealState() {
		return this.dealState;
	}

	public void setReply(String obj) {
		this.reply = obj;
	}

	public String getReply() {
		return this.reply;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setTeleCounUuid(String obj) {
		this.teleCounUuid = obj;
	}

	public String getTeleCounUuid() {
		return this.teleCounUuid;
	}
	

	public Integer getIquestion() {
		return iquestion;
	}

	public void setIquestion(Integer iquestion) {
		this.iquestion = iquestion;
	}

	
	

	public String getIfread() {
		return ifread;
	}

	public void setIfread(String ifread) {
		this.ifread = ifread;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[customerUuid=" + this.getCustomerUuid()
				+ ",doctorUuid=" + this.getDoctorUuid() + ",content=" + this.getContent() + ",picture="
				+ this.getPicture() + ",createTime=" + this.getCreateTime() + ",type=" + this.getConsultType()
				+ ",dealState=" + this.getDealState() + ",reply=" + this.getReply() + ",state=" + this.getState()
				+ ",teleCounUuid=" + this.getTeleCounUuid() + ",]";
	}

}
