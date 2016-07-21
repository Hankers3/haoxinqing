package com.aebiz.b2b2c.cms.innermessage.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;

/**
 * 消息Model
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "inner_message")
@Component
public class InnerMessageModel extends BaseModel {

	/* 消息类型 0 关注收藏的类型  1随访消息  2系统的消息   */
	public static final String MESSAGE_TYPE_PROMOTION = "0";
	public static final String MESSAGE_TYPE_ORDER = "1";
	public static final String MESSAGE_TYPE_SYSTEM = "2";

	/* 收件人类别 1医生  2患者 */
	public static final String  ACCOUNT_TYPE_STORE= "1";
	public static final String ACCOUNT_TYPE_CUSTOMER = "2";

	/* 读取状态 0未读 1已读 */
	public static final String READ_STATUS_UNREAD = "0";
	public static final String READ_STATUS_READ = "1";

	// 注入患者的service
	@Transient
	private static CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 注入患者INFO的service
	@Transient
	private static CustomerInfoService customerInfoService;

	@Autowired
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	// 注入医生的service
	@Transient
	private static ServicestaffService staffService;

	@Autowired
	public void setStaffService(ServicestaffService staffService) {
		this.staffService = staffService;
	}

	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	/* 信息标题 */
	private String title = "";

	/* 信息内容 */
	private String content = "";

	/* 发件人 */
	private String sendUser = "";

	/* 收件人 */
	private String receiveUser = "";

	/* 发送时间 */
	private String sendTime = "";

	/* 发件人删除状态 */
	private String fjDelStatus = "0";

	/* 读取状态 */
	private String readStatus = "";

	/* 收件人删除状态 */
	private String sjDelStatus = "0";

	/* 消息类型 0系统消息 1随访消息 2在线咨询消息 3最近收藏我的人 */
	private String messageType = "";

	/* 发信人类别 0:平台 1:医生 2：患者 */
	private String sendType = "";

	/* 收信人类别 0:平台 1:医生 2：患者 */
	private String accountType = "";

	/* 发件人头像 */
	private String userImage = "";

	/* 关联的订单编号 */
	private String orderUuid = "";

	/* 随访id */
	private String visitRecordUuid = "";

	/* 站内发送状态 0：未发送 1：已发送 */
	private String sendStatus = "";

	/* 图片 */
	private String image = "";

	/* 消息打开类型 */
	private String messageOpenType = "";

	/* 消息读取状态名称 */
	@Transient
	private String readStatusName = "";

	/* 前台列表展示的内容 */
	@Transient
	private String showContent = "";

	// 发信人名字
	@Transient
	private String sendUserName = "";

	// 接收人员姓名
	@Transient
	private String receiveUserName = "";
	//通用的type 
	private String universalType = "";
	//通用的Uuid
	private String universalUuid = "";
	
	/* 得到图片路径 */
	@Transient
	private String imgUrl = "";
	
	
	public String getUniversalUuid() {
		return universalUuid;
    }

	public void setUniversalUuid(String universalUuid) {
		this.universalUuid = universalUuid;
    }

	public String getUniversalType() {
        return universalType;
    }

	public void setUniversalType(String universalType) {
        this.universalType = universalType;
	}

	public String getVisitRecordUuid() {
		return visitRecordUuid;
	}

	public void setVisitRecordUuid(String visitRecordUuid) {
		this.visitRecordUuid = visitRecordUuid;
	}

	public String getMessageOpenType() {
		return messageOpenType;
	}

	public void setMessageOpenType(String messageOpenType) {
		this.messageOpenType = messageOpenType;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImgUrl() {
		if (StringUtil.isEmpty(this.image)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image);
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			return file.getRemotePaths();
		} else {
			return this.getImage();
		}
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSendUserName() {
		if (!StringUtil.isEmpty(this.sendType)) {
			if ("1".equals(this.sendType)) {
				return staffService.getServiceStaffNameByUuid(this.sendUser);
			} else if ("2".equals(this.sendType)) {
				return customerInfoService.getRealNameByUuid(this.sendUser);
			}
		}
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getReceiveUserName() {
		if (!StringUtil.isEmpty(this.accountType)) {
			if ("1".equals(this.accountType)) {
				return staffService.getServiceStaffNameByUuid(this.receiveUser);
			} else if ("2".equals(this.accountType)) {
				return customerInfoService.getRealNameByUuid(this.receiveUser);
			}
		}
		return receiveUserName;

	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public void setTitle(String obj) {
		this.title = obj;
	}

	public String getTitle() {
		return this.title;
	}

	public void setContent(String obj) {
		this.content = obj;
	}

	public String getContent() {
		return this.content;
	}

	public void setSendUser(String obj) {
		this.sendUser = obj;
	}

	public String getSendUser() {
		return this.sendUser;
	}

	public void setReceiveUser(String obj) {
		this.receiveUser = obj;
	}

	public String getReceiveUser() {
		return this.receiveUser;
	}

	public void setSendTime(String obj) {
		this.sendTime = obj;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setFjDelStatus(String obj) {
		this.fjDelStatus = obj;
	}

	public String getFjDelStatus() {
		return this.fjDelStatus;
	}

	public void setReadStatus(String obj) {
		this.readStatus = obj;
	}

	public String getReadStatus() {
		return this.readStatus;
	}

	public void setSjDelStatus(String obj) {
		this.sjDelStatus = obj;
	}

	public String getSjDelStatus() {
		return this.sjDelStatus;
	}

	public void setMessageType(String obj) {
		this.messageType = obj;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setAccountType(String obj) {
		this.accountType = obj;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getReadStatusName() {
		if (this.readStatus.equals("0")) {
			return MessageHelper.getMessage("innermessage.readStatus.unread");
		} else {
			return MessageHelper.getMessage("innermessage.readStatus.read");
		}
	}

	public void setReadStatusName(String readStatusName) {
		this.readStatusName = readStatusName;
	}

	public String getShowContent() {
		if (this.content.length() > 60) {
			return content.substring(0, 60) + "...";
		}

		return content;
	}

	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}

	public String getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName() + "[title=" + this.getTitle() + ",content="
				+ this.getContent() + ",sendUser=" + this.getSendUser() + ",receiveUser=" + this.getReceiveUser()
				+ ",sendTime=" + this.getSendTime() + ",fjDelStatus=" + this.getFjDelStatus() + ",readStatus="
				+ this.getReadStatus() + ",sjDelStatus=" + this.getSjDelStatus() + ",messageType="
				+ this.getMessageType() + ",accountType=" + this.getAccountType() + ",]";
	}
}
