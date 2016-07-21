package com.aebiz.b2b2c.cms.innermessage.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.innermessage.dao.InnerMessageDAO;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.mobile.web.push.JPushService;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class InnerMessageServiceImpl extends BaseServiceImpl<InnerMessageModel, InnerMessageQueryModel>
		implements InnerMessageService {
	private InnerMessageDAO innerMessageDAO = null;
	@Autowired
	private UuidService us;

	@Autowired
	private CustomerInteractive customerInteractive;

	/*注入订单service*/
	@Autowired
	private OrderMainService orderService;
	
	@Autowired
	public void setMyDao(InnerMessageDAO dao) {
		this.innerMessageDAO = dao;
		super.setDao(dao);
	}
	
	
    //log4j记录日志
    private static Logger log = Logger.getLogger("pushMessage_logger");

	@Autowired
	private FileUploadHelper fileUpload = null;

	@Override
	public String create(InnerMessageModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 设置站内信发送时间
		m.setSendTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(InnerMessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 更新为已读状态
		m.setReadStatus(InnerMessageModel.READ_STATUS_READ);

		super.update(m);
	}

	@Override
	public void delete(InnerMessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.delete(m);
	}

	/**
	 * 发送站内信
	 */
	@Override
	public void saveInnerMessage(String receiveUser, String title, String content) {
		InnerMessageModel innerMessage = new InnerMessageModel();
		innerMessage.setReceiveUser(receiveUser);
		innerMessage.setTitle(title);
		innerMessage.setContent(content);

		String userUuid = LoginUserHelper.getCustomerLoginUserUuid();
		String userImage = this.customerInteractive.getCustomerInfoRemoteImageUrlByCustomerUuid(userUuid);
		if (!StringUtil.isEmpty(userImage)) {
			innerMessage.setUserImage(userImage);
		}
		// 设置发信人为当前用户
		innerMessage.setSendUser(userUuid);
		// 设置消息为未读状态
		this.create(innerMessage);
	}

	/**
	 * 删除站内信
	 */
	@Override
	public void deleteInnerMessage(List<String> needDeleteUuids, String type) {
		if (needDeleteUuids != null && needDeleteUuids.size() > 0) {
			for (String deleteUuid : needDeleteUuids) {
				if (!StringUtil.isEmpty(deleteUuid)) {
					InnerMessageModel deleteMessage = super.getByUuid(deleteUuid);

					if (type.equals("sx")) {
						deleteMessage.setSjDelStatus("1");
					} else if (type.endsWith("fx")) {
						deleteMessage.setFjDelStatus("1");
					}
					super.update(deleteMessage);
				}
			}
		}
	}

	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 * @return
	 */
	@Override
	public List<InnerMessageModel> getInnerMessageListByMessageType(String messageType, String doctorUuid) {

		return this.innerMessageDAO.getInnerMessageListByMessageType(messageType, doctorUuid);
	}

	/**
	 * 根据用户Id获取消息列表
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<InnerMessageModel> getInnerMessageListByUserId(String userId, String readStatus, int pageCount,
			int pageNo) {
		return this.innerMessageDAO.getInnerMessageListByUserId(userId, readStatus, pageCount, pageNo);
	}

	/**
	 * 文件上传
	 * 
	 * @param innerMessageModel
	 * @param files
	 * @return
	 */
	@Override
	public InnerMessageModel uploadImage(InnerMessageModel m, MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "innerImage" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

					// 如果文件大小不为0
					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUpload.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					m.setImage(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				m.setImage("");
			}
		} catch (Exception ex) {
			m.setImage("");
		}
		if (fileList != null && fileList.size() > 0) {
			MultipartFile[] newFiles = new MultipartFile[fileList.size()];
			String[] newFileNames = new String[fileList.size()];
			for (int i = 0; i < fileList.size(); i++) {
				newFileNames[i] = fileNameList.get(i);
				newFiles[i] = fileList.get(i);
			}
			fileUpload.uploadFiles(newFiles, newFileNames);
		}
		return m;
	}

	/**
	 * 得到文件后缀
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String getFileSuffix(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return "";
		}
		return fileName.substring(fileName.indexOf("."), fileName.length());
	}

	/**
	 * 保存订单相关站内信推送消息
	 * 
	 * @author zdx
	 */
	@Override
	public void saveServiceInnerMessage(String orderUuid, String userId, String title, String content, String msgType) {
		InnerMessageModel im = new InnerMessageModel();
		// 收件人类别
		im.setAccountType(InnerMessageModel.ACCOUNT_TYPE_STORE);
		im.setContent(content);
		im.setOrderUuid(orderUuid);
		im.setMessageType(InnerMessageModel.MESSAGE_TYPE_ORDER);
		im.setReceiveUser(userId);
		im.setSendUser("system");
		im.setTitle(title);
		im.setMessageOpenType(msgType);
		im.setReadStatus("0");
		// 已发送
		im.setSendStatus("1");
		im.setSendTime(DateFormatHelper.getNowTimeStr());
		this.create(im);

	}

	/**
	 * 通过uuid得到发送给该医生的各种消息
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<InnerMessageModel> getCollectMeListByReceiveUser(String userId, String messageType) {
		return innerMessageDAO.getMessageList(userId, messageType);// 消息类型
																	// 0最近收藏我的人
																	// 1随访消息
																	// 2在线咨询消息
	}

	/**
	 * 返回消息中心未读数量
	 */
	@Override
	public int getMessageCenterCount(String messageType, String userId) {
		return innerMessageDAO.getMessageCenterCount(messageType, userId);
	}

	/**
	 * 保存消息到数据库，并发送到手机终端
	 * 
	 * @param sendUuid
	 *            发送者ID
	 * @param receiveUuid
	 *            接受者ID
	 * @param content
	 *            消息体
	 * @param sendType
	 *            发送者类型
	 * @param accountType
	 *            接受者类型
	 * @param pushClient
	 *            推送客户端
	 */
	@Override
	public void saveInnerMessageAndPush(String sendUuid, String receiveUuid, String content, String sendType,
			String accountType, String pushClient, String messageKey,String universalUuid,String universalType) {
		
		log.info("==============saveInnerMessageAndPush============");
		// 创建新的InnerMessageModel
		InnerMessageModel innerMessageModel = new InnerMessageModel();
		// 赋值
		innerMessageModel.setTitle("您有一条新的系统消息");
		/* 发件人 */
		innerMessageModel.setSendUser(sendUuid);
		/* 信息内容 */
		innerMessageModel.setContent(content);
		/* 发信人类别 0:平台 1:医生 2：患者 */
		innerMessageModel.setSendType(sendType);
		/* 收信人类别 0:平台 1:医生 2：患者 */
		innerMessageModel.setAccountType(accountType);
		// 发送时间
		innerMessageModel.setSendTime(DateFormatHelper.getNowTimeStr());
		// 未读
		innerMessageModel.setReadStatus(InnerMessageModel.READ_STATUS_UNREAD);
		// 系统消息
		innerMessageModel.setMessageType(InnerMessageModel.MESSAGE_TYPE_SYSTEM);
		/* 收件人 */
		innerMessageModel.setReceiveUser(receiveUuid);
		/* 消息的通用的类型 */
		innerMessageModel.setUniversalType(universalType);
		/* 消息的通用的id*/
		innerMessageModel.setUniversalUuid(universalUuid);
		// 创建
		this.create(innerMessageModel);
		
		log.info("==============messageKey============"+messageKey);
		log.info("==============universalUuid============"+universalUuid);
		log.info("==============universalType============"+universalType);

		OrderMainModel order =null;
		String orderType="";
		//如果消息类型是订单
		if("orderUuid".equals(messageKey)){
			order=orderService.getByUuid(universalUuid);
			if(order !=null){
				orderType = order.getOrderType();
			}
		}
		// 消息发送
		Map<String, String> messageMap = new HashMap<String, String>();
		// 消息体
		messageMap.put("pushClient", pushClient);
		messageMap.put("message", content);
		
		StringBuffer urlBuff = new StringBuffer();
		
		//重要医嘱 医生向患者发送
		if(InnerMessageTypeEnum.DOCTORADVICE.getValue().equals(universalType)){
			urlBuff.append(PushConst.PUSH_URL).append("patient/home/total_details.shtml?doctorUuid=").append(sendUuid);
		//  messageMap.put("url", PUsh"http://admin.hxqydyl.com/patient/home/total_details.shtml?doctorUuid="+sendUuid);
		 //健康指导 医生向患者发送
		}else if(InnerMessageTypeEnum.DOCTORHEALTHGUIDE.getValue().equals(universalType)){
			urlBuff.append(PushConst.PUSH_URL).append("patient/my_doctor/health_guide.shtml?doctorUuid=").append(sendUuid);
		//  messageMap.put("url", "http://admin.hxqydyl.com/patient/my_doctor/health_guide.shtml?doctorUuid="+sendUuid);
		 //在线咨询
		}else if(InnerMessageTypeEnum.ONLINE.getValue().equals(universalType)){
			  //向患者发送
			  if("customerUuid".equals(messageKey)){
				  urlBuff.append(PushConst.PUSH_URL).append("patient/my_doctor/communicate_patient.shtml?doctorUuid=").append(sendUuid);
				  //messageMap.put("url", "http://admin.hxqydyl.com/patient/my_doctor/communicate_patient.shtml?doctorUuid="+sendUuid);
			  }else{
				  //医生段落地
				  urlBuff.append(PushConst.PUSH_URL).append("html/task/online_consultation.shtml?type=0");
//				  messageMap.put("url", "http://admin.hxqydyl.com/html/task/online_consultation.shtml?type=0");
			  }
		//预约加号
		}else if(InnerMessageTypeEnum.SUBSCRIBE.getValue().equals(universalType)){
			  urlBuff.append(PushConst.PUSH_URL).append("html/task/booking_plus.shtml?type=2");
//			  messageMap.put("url", "http://admin.hxqydyl.com/html/task/booking_plus.shtml?type=2");
		//随访申请
		}else if(InnerMessageTypeEnum.VISIT.getValue().equals(universalType)){
			 urlBuff.append(PushConst.PUSH_URL).append("html/follow_up/patient_information.shtml?_customerUuid=").append(messageKey);
//			  messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/patient_information.shtml?_customerUuid="+messageKey);
		//随访详情
		}else if(InnerMessageTypeEnum.VISITDETAIL.getValue().equals(universalType)){
			 urlBuff.append(PushConst.PUSH_URL).append("html/follow_up/followed_for_details.shtml?applyUuid=").append(messageKey);
//			  messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/followed_for_details.shtml?applyUuid="+messageKey);
		//随访医生
		}else if(InnerMessageTypeEnum.VISITDOCTOR.getValue().equals(universalType)){
			  urlBuff.append(PushConst.PUSH_URL).append("patient/my_doctor/index.shtml?tab=2");
//			  messageMap.put("url", "http://admin.hxqydyl.com/patient/my_doctor/index.shtml?tab=2");
		//服务记录
		}else if(InnerMessageTypeEnum.SERVICE.getValue().equals(universalType)){
			  urlBuff.append(PushConst.PUSH_URL).append("patient/my/my_system.shtml");
//			  messageMap.put("url", "http://admin.hxqydyl.com/patient/my/my_system.shtml");
		//病例
		}else if(InnerMessageTypeEnum.CASE.getValue().equals(universalType)){
			 urlBuff.append(PushConst.PUSH_URL).append("patient/home/case_notes.shtml?doctorUuid=").append(sendUuid);
//			  messageMap.put("url", "http://admin.hxqydyl.com/patient/home/case_notes.shtml?doctorUuid="+sendUuid);
		//内容详情
		}else if(InnerMessageTypeEnum.CONTENT.getValue().equals(universalType)){
			 urlBuff.append(PushConst.PUSH_URL).append("html/follow_up/toolbox/patient_content.shtml?contextUuid=").append(messageKey).append("&action=show");
//			  messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/toolbox/patient_content.shtml?contextUuid="+messageKey+"&action=show");
		//订单
		}else if(InnerMessageTypeEnum.ORDER.getValue().equals(universalType)){
			 // messageMap.put("universalUuid", messageKey+"|"+universalUuid);
			  if("2".equals(orderType)){
				  urlBuff.append(PushConst.PUSH_URL).append("patient/my/myorder.shtml");
//				  messageMap.put("url", "http://admin.hxqydyl.com/patient/my/myorder.shtml");
			  }else{
				  urlBuff.append(PushConst.PUSH_URL).append("patient/my/myorder.shtml");
//				  messageMap.put("url", "http://admin.hxqydyl.com/patient/my/myorder.shtml");
			  }
		}
		
		messageMap.put("url",urlBuff.toString());
		log.info("==============messageMap============"+JSON.toJSONString(messageMap));
		List<String> userId = new ArrayList<String>();
		userId.add(receiveUuid);
		System.out.println("============messageMap============="+JSON.toJSONString(messageMap));
		System.out.println("id========================="+receiveUuid+"===============================");
		System.out.println("url========================="+messageMap.get("url")+"===============================");
		
		try {
			JPushService.pushSystemMessage(userId, messageMap);
		} catch (Exception e) {
			//e.printStackTrace();
		    System.out.println("==================推送异常================");

		}

	}
	/**
     * 关注我的人专用消息推送接口
     * @Description: (关注我的人专用消息推送接口)    
     * @author XP  
     * @param customerUuid
     * @param doctorUuid
     * @param content
     * @param accountTypeCustomer
     * @param accountTypeStore
     * @param pushClientService
     * @param string
     * @param customerUuid2
     * @param string2
     * @date 2016-2-14 下午9:32:44
     */
    @Override
    public void saveInnerMessageAndPushForConcernDoctor(String sendUuid, String receiveUuid, String content, String sendType,
            String accountType, String pushClient, String messageKey,String universalUuid,String universalType) {
     // 创建新的InnerMessageModel
        InnerMessageModel innerMessageModel = new InnerMessageModel();
        // 赋值
        innerMessageModel.setTitle("您有一条新的关注信息");
        /* 发件人 */
        innerMessageModel.setSendUser(sendUuid);
        /* 信息内容 */
        innerMessageModel.setContent(content);
        /* 发信人类别 0:平台 1:医生 2：患者 */
        innerMessageModel.setSendType(sendType);
        /* 收信人类别 0:平台 1:医生 2：患者 */
        innerMessageModel.setAccountType(accountType);
        // 发送时间
        innerMessageModel.setSendTime(DateFormatHelper.getNowTimeStr());
        // 未读
        innerMessageModel.setReadStatus(InnerMessageModel.READ_STATUS_UNREAD);
        // 系统消息
        innerMessageModel.setMessageType(InnerMessageModel.MESSAGE_TYPE_PROMOTION);
        /* 收件人 */
        innerMessageModel.setReceiveUser(receiveUuid);
        /* 消息的通用的类型 */
        innerMessageModel.setUniversalType(universalType);
        /* 消息的通用的id*/
        innerMessageModel.setUniversalUuid(universalUuid);
        // 创建
        this.create(innerMessageModel);
        // 消息发送
        /*Map<String, String> messageMap = new HashMap<String, String>();
        // 消息体
        messageMap.put("pushClient", pushClient);
        messageMap.put("message", content);
       
        messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/patient_information.shtml?_customerUuid="+sendUuid);
        
        List<String> userId = new ArrayList<String>();
        userId.add(receiveUuid);
        try {
                JPushService.pushSystemMessage(userId, messageMap);
        } catch (Exception e) {
                //e.printStackTrace();
            System.out.println("==================推送异常================");

        }*/
        
    }
    /**
     * 
     * @Description: (申请随访的消息推送)    
     * @author XP  
     * @param customerUuid
     * @param doctorUuid
     * @param content
     * @param accountTypeCustomer
     * @param accountTypeStore
     * @param pushClientService
     * @param string
     * @param customerUuid2
     * @param string2
     * @date 2016-2-14 下午9:32:44
     */
    @Override
    public void saveInnerMessageAndPushForVisitRecord(String sendUuid, String receiveUuid, String content, String sendType,
            String accountType, String pushClient, String messageKey,String universalUuid,String universalType) {
     // 创建新的InnerMessageModel
        InnerMessageModel innerMessageModel = new InnerMessageModel();
        // 赋值
        innerMessageModel.setTitle("您有一条新的随访消息");
        /* 发件人 */
        innerMessageModel.setSendUser(sendUuid);
        /* 信息内容 */
        innerMessageModel.setContent(content);
        /* 发信人类别 0:平台 1:医生 2：患者 */
        innerMessageModel.setSendType(sendType);
        /* 收信人类别 0:平台 1:医生 2：患者 */
        innerMessageModel.setAccountType(accountType);
        // 发送时间
        innerMessageModel.setSendTime(DateFormatHelper.getNowTimeStr());
        // 未读
        innerMessageModel.setReadStatus(InnerMessageModel.READ_STATUS_UNREAD);
        // 系统消息
        innerMessageModel.setMessageType(InnerMessageModel.MESSAGE_TYPE_ORDER);
        /* 收件人 */
        innerMessageModel.setReceiveUser(receiveUuid);
        /* 消息的通用的类型 */
        innerMessageModel.setUniversalType(universalType);
        /* 消息的通用的id*/
        innerMessageModel.setUniversalUuid(universalUuid);
        // 创建
        this.create(innerMessageModel);
        
        // 消息发送
        Map<String, String> messageMap = new HashMap<String, String>();
        // 消息体
        messageMap.put("pushClient", pushClient);
        messageMap.put("message", content);

        //messageMap.put("universalUuid", messageKey+"|"+universalUuid);
        messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/patient_information.shtml?_customerUuid={"+messageKey+"}");
        List<String> userId = new ArrayList<String>();
        userId.add(receiveUuid);
        try {
                JPushService.pushSystemMessage(userId, messageMap);
        } catch (Exception e) {
            System.out.println("==================推送异常================");

        }

    }

}