package com.hxq.mobile.message.InnerMessage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.mobile.web.push.JPushService;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.doctor.order.service.OrderMainService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.common.ImgUpload;
import com.hxq.mobile.entity.common.ImgUploadResponse;
import com.hxq.mobile.entity.common.ImgUploadResponse.FileMeta;
import com.hxq.mobile.entity.message.InnerMessage;
import com.hxq.mobile.entity.order.OrderMain;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
public class InnerMessageServiceImpl extends SpringJdbcSimpleEntityService implements InnerMessageService {
	Logger log = LoggerFactory.getLogger(InnerMessageServiceImpl.class);

	@Resource(name="com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;

	@Resource(name="com.hxq.mobile.doctor.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;

	/*注入订单service*/
	@Resource(name = "com.hxq.mobile.doctor.order.service.OrderMainService")
	private OrderMainService orderService;

	private String TABLE_COLUMNS="a.uuid, a.opeTime, a.delFlag, a.title, a.content, a.sendUser, a.receiveUser, a.sendTime,"
			+ " a.fjDelStatus, a.readStatus, a.sjDelStatus, a.messageType, a.accountType, a.userImage, a.image, a.orderUuid,"
			+ " a.sendStatus, a.messageOpenType, a.sendType, a.visitRecordUuid, a.universalType, a.universalUuid";

	@Override
	public int insert(AbstractEntity<?> bean) throws Exception {
		InnerMessage im = (InnerMessage) bean;
		im.setDelFlag("1");
		im.setOper(LoginUserHelper.getLoginUserUuid());
		im.setOpeTime(DateFormatHelper.getNowTimeStr());
		im.setSendTime(DateFormatHelper.getNowTimeStr());// 设置站内信发送时间
		return super.insert(im);
	}

	@Override
	public int update(AbstractEntity<?> bean) throws Exception {
		InnerMessage im = (InnerMessage) bean;
		im.setDelFlag("1");
		im.setOper(LoginUserHelper.getLoginUserUuid());
		im.setOpeTime(DateFormatHelper.getNowTimeStr());
		im.setReadStatus(InnerMessage.READ_STATUS_READ);// 更新为已读状态
		return super.update(im);
	}

	@Override
	public int delete(AbstractEntity<?> id) throws Exception {
		InnerMessage im = (InnerMessage) id;
//		im.setDelFlag("2");
//		im.setOper(LoginUserHelper.getLoginUserUuid());
//		im.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.delete(im);
	}

	@Override
	public int insertInnerMessage(String receiveUser, String title, String content) throws Exception {
		InnerMessage innerMessage = new InnerMessage();
		innerMessage.setReceiveUser(receiveUser);
		innerMessage.setTitle(title);
		innerMessage.setContent(content);

		String userUuid = LoginUserHelper.getCustomerLoginUserUuid();
		innerMessage.setSendUser(userUuid);// 设置发信人为当前用户

		CustomerInfo userInfo = customerInfoService.selectByCustomerUuid(userUuid);
		String imgId = ObjectUtils.isEmpty(userInfo) ? null : (String) userInfo.getImage();
		ImgUpload iu = ObjectUtils.isEmpty(imgId) ? null : (ImgUpload) imgService.select(new ImgUpload(imgId));
		if(iu != null) innerMessage.setUserImage(iu.getImgUrl());
		return super.insert(innerMessage);// 设置消息为未读状态
	}

	@Override
	public void deleteInnerMessage(List<String> needDeleteUuids, String type) throws Exception {
		if(ObjectUtils.isEmpty(needDeleteUuids)) return;
		InnerMessage im = null;
		for(String id : needDeleteUuids) {
			if(ObjectUtils.isEmpty(id)) continue;
			im = new InnerMessage(id);
			if(type.equals("sx")) {
				im.setSjDelStatus("1");
				super.update(im);
			} else if(type.endsWith("fx")) {
				im.setFjDelStatus("1");
				super.update(im);
			}
		}
	}

	@Override
	public List<InnerMessage> selectInnerMessageListByMessageType(String messageType, String doctorUuid) {
		if(ObjectUtils.isEmpty(messageType) || ObjectUtils.isEmpty(doctorUuid)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select ").append(TABLE_COLUMNS)
		.append(" from inner_message as a where a.messageType=? and a.receiveUser=? order by a.sendTime desc");

		List<Map<String, Object>> lst = dao.query(sbf.toString(),
				new Object[]{StringUtils.trimToEmpty(messageType), StringUtils.trimToEmpty(doctorUuid)},
				null, getQueryCache());
		return convertMap2Object(lst);
	}

	/**
	 * 根据用户Id获取消息列表
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<InnerMessage> selectInnerMessageListByUserId(String userId,String readStatus,int pageCount,int pageNo) {
		if(ObjectUtils.isEmpty(userId)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();

		sbf.append(" from inner_message as a where a.messageType='1' and a.receiveUser=?");
		args.add(StringUtils.trimToEmpty(userId));

		if(!ObjectUtils.isEmpty(readStatus)) {
			sbf.append(" and a.readStatus=?");
			args.add(StringUtils.trimToEmpty(readStatus));
		}
		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();

		sbf.delete(0, sbf.length());
		sbf.append("select ").append(TABLE_COLUMNS).append(strWhere).append(" order by a.sendTime desc");
		String strQuery = sbf.toString();

		Object[] params = args.isEmpty() ? null : args.toArray();
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), pageNo, pageCount);
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return convertMap2Object(lst);
	}

	@Override
	public int insertServiceInnerMessage(String orderUuid, String userId, String title, String content, String msgType) throws Exception {
		InnerMessage im = new InnerMessage();
		im.setAccountType(InnerMessage.ACCOUNT_TYPE_STORE);// 收件人类别
		im.setContent(content);
		im.setOrderUuid(orderUuid);
		im.setMessageType(InnerMessage.MESSAGE_TYPE_ORDER);
		im.setReceiveUser(userId);
		im.setSendUser("system");
		im.setTitle(title);
		im.setMessageOpenType(msgType);
		im.setReadStatus("0");
		im.setSendStatus("1");// 已发送
		im.setSendTime(DateFormatHelper.getNowTimeStr());
		return super.insert(im);
	}

	/**
	 * 通过uuid得到发送给该医生的各种消息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<InnerMessage> selectCollectMeListByReceiveUser(String userId, String messageType) {
		if(ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(messageType)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select ").append(TABLE_COLUMNS)
		.append(" from inner_message as a where a.messageType=? and a.receiveUser=? order by a.sendTime desc");

		List<Map<String, Object>> lst = dao.query(sbf.toString(), new Object[]{
			StringUtils.trimToEmpty(messageType), StringUtils.trimToEmpty(userId)}, null, getQueryCache());
		return convertMap2Object(lst);
	}

	/**
	 * 返回消息中心未读数量
	 */
	@Override
	public int selectMessageCenterCount(String messageType, String userId) {
		String sql = "select count(uuid) from inner_message where messageType=? and receiveUser=? and readStatus='0'";
		Integer intCount = dao.queryForObject(sql,
				new Object[]{StringUtils.trimToEmpty(messageType), StringUtils.trimToEmpty(userId)},
				null, Integer.class);
		return intCount != null ? intCount.intValue() : 0;
	}

	@Override
	public int insertInnerMessageAndPush(String sendUuid, String receiveUuid, String content, String sendType,
			String accountType, String pushClient, String messageKey,String universalUuid,String universalType) throws Exception {

		// 消息发送
		Map<String, String> messageMap = new HashMap<String, String>();
		// 消息体
		messageMap.put("pushClient", pushClient);
		messageMap.put("message", content);
		//重要医嘱 医生向患者发送
		if(InnerMessageTypeEnum.DOCTORADVICE.getValue().equals(universalType)){
		  messageMap.put("url", "http://admin.hxqydyl.com/patient/home/total_details.shtml?doctorUuid="+sendUuid);
		 //健康指导 医生向患者发送
		}else if(InnerMessageTypeEnum.DOCTORHEALTHGUIDE.getValue().equals(universalType)){
		  messageMap.put("url", "http://admin.hxqydyl.com/patient/my_doctor/health_guide.shtml?doctorUuid="+sendUuid);
		 //在线咨询
		}else if(InnerMessageTypeEnum.ONLINE.getValue().equals(universalType)){
			  //向患者发送
			  if("customerUuid".equals(messageKey)){
				  messageMap.put("url", "http://admin.hxqydyl.com/patient/my_doctor/communicate_patient.shtml?doctorUuid="+sendUuid);
			  }else{
				  //医生段落地
				  messageMap.put("url", "http://admin.hxqydyl.com/html/task/online_consultation.shtml?type=0");
			  }
		//预约加号
		}else if(InnerMessageTypeEnum.SUBSCRIBE.getValue().equals(universalType)){
			  messageMap.put("url", "http://admin.hxqydyl.com/html/task/booking_plus.shtml?type=2");
		//随访申请
		}else if(InnerMessageTypeEnum.VISIT.getValue().equals(universalType)){
			  messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/patient_information.shtml?_customerUuid="+messageKey);
		//随访详情
		}else if(InnerMessageTypeEnum.VISITDETAIL.getValue().equals(universalType)){
			  messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/followed_for_details.shtml?applyUuid="+messageKey);
		//随访医生
		}else if(InnerMessageTypeEnum.VISITDOCTOR.getValue().equals(universalType)){
			  messageMap.put("url", "http://admin.hxqydyl.com/patient/my_doctor/index.shtml?tab=2");
		//服务记录
		}else if(InnerMessageTypeEnum.SERVICE.getValue().equals(universalType)){
			  messageMap.put("url", "http://admin.hxqydyl.com/patient/my/my_system.shtml");
		//病例
		}else if(InnerMessageTypeEnum.CASE.getValue().equals(universalType)){
			  messageMap.put("url", "http://admin.hxqydyl.com/patient/home/case_notes.shtml?doctorUuid="+sendUuid);
		//内容详情
		}else if(InnerMessageTypeEnum.CONTENT.getValue().equals(universalType)){
			  messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/toolbox/patient_content.shtml?contextUuid="+messageKey+"&action=show");
		//订单
		}else if(InnerMessageTypeEnum.ORDER.getValue().equals(universalType)){
			String orderType = null;
			//如果消息类型是订单
			if("orderUuid".equalsIgnoreCase(messageKey)){
				OrderMain order = (OrderMain) orderService.select(new OrderMain(universalUuid));
				if(order != null) orderType = order.getOrderType();
			}
			// messageMap.put("universalUuid", messageKey+"|"+universalUuid);
			if("2".equals(orderType)){
				messageMap.put("url", "http://admin.hxqydyl.com/patient/my/myorder.shtml");
			} else {
				messageMap.put("url", "http://admin.hxqydyl.com/patient/my/myorder.shtml");
			}
		}
		List<String> userId = new ArrayList<String>();
		userId.add(receiveUuid);
		JPushService.pushSystemMessage(userId, messageMap);

		InnerMessage im = buildInnerMessage(sendUuid, receiveUuid, content, sendType,
				accountType, pushClient, messageKey, universalUuid, universalType);
		im.setTitle("您有一条新的系统消息");	//赋值
		im.setMessageType(InnerMessage.MESSAGE_TYPE_SYSTEM);	//系统消息
		return super.insert(im);
	}

	@Override
	public int insertInnerMessageAndPushForConcernDoctor(String sendUuid, String receiveUuid, String content, String sendType,
	        String accountType, String pushClient, String messageKey,String universalUuid,String universalType) throws Exception {

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

		InnerMessage im = buildInnerMessage(sendUuid, receiveUuid, content, sendType,
				accountType, pushClient, messageKey, universalUuid, universalType);
	    im.setTitle("您有一条新的关注信息");	//赋值
	    im.setMessageType(InnerMessage.MESSAGE_TYPE_PROMOTION);	//系统消息
	    return super.insert(im);
	}

	@Override
	public int insertInnerMessageAndPushForVisitRecord(String sendUuid, String receiveUuid, String content, String sendType,
	        String accountType, String pushClient, String messageKey,String universalUuid,String universalType) throws Exception {

	    // 消息发送
	    Map<String, String> messageMap = new HashMap<String, String>();
	    // 消息体
	    messageMap.put("pushClient", pushClient);
	    messageMap.put("message", content);
	    //messageMap.put("universalUuid", messageKey+"|"+universalUuid);
	    messageMap.put("url", "http://admin.hxqydyl.com/html/follow_up/patient_information.shtml?_customerUuid={"+messageKey+"}");
	    List<String> userId = new ArrayList<String>();
	    userId.add(receiveUuid);
    	JPushService.pushSystemMessage(userId, messageMap);

		InnerMessage im = buildInnerMessage(sendUuid, receiveUuid, content, sendType,
				accountType, pushClient, messageKey, universalUuid, universalType);
		im.setTitle("您有一条新的随访消息");	//赋值
		im.setMessageType(InnerMessage.MESSAGE_TYPE_ORDER);	//系统消息
		return super.insert(im);
	}

	/**
	 * 文件上传
	 *
	 * @param innerMessageModel
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@Override
	public InnerMessage insertForUploadImage(InnerMessage im, MultipartFile[] files) throws Exception {
		ImgUploadResponse iur = imgService.updateForUpload(false, files);
		if(iur.getFiles() != null && iur.getFiles().size() > 0) {
			StringBuffer sbf = new StringBuffer(1000);
			for(FileMeta fm : iur.getFiles())
				StringUtils.appendBuffer(sbf, fm.getId(), sbf.length() > 0 ? "," : null);
			im.setImage(sbf.toString());
		}
		return im;
	}

	/**
	 * 将返回map列转换成InnerMessage对象列
	 * @param rows
	 * @return
	 */
	private List<InnerMessage> convertMap2Object(List<Map<String, Object>> rows) {
		if(ObjectUtils.isEmpty(rows)) return null;
		InnerMessage im = null;
		Image4App[] urls = null;
		List<InnerMessage> lstReturn = new ArrayList<InnerMessage>(rows.size());
		try {
			for(Map<String, Object> r : rows) {
				im = SimpleBean2DBHelper.map2Bean(r, InnerMessage.class);
				if(!ObjectUtils.isEmpty(im.getImage())) {
					urls = imgService.selectImagesByIds(im.getImage().split(","));
					if(!ObjectUtils.isEmpty(urls)) im.setImageUrls(urls);
				}
				lstReturn.add(im);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn;
	}

	/**
	 * 通过参数创建消息实体
	 * @param sendUuid
	 * @param receiveUuid
	 * @param content
	 * @param sendType
	 * @param accountType
	 * @param pushClient
	 * @param messageKey
	 * @param universalUuid
	 * @param universalType
	 * @return
	 */
	private InnerMessage buildInnerMessage(String sendUuid, String receiveUuid,
			String content,	String sendType, String accountType, String pushClient,
			String messageKey, String universalUuid,String universalType) {
		InnerMessage im = new InnerMessage();	//创建新的InnerMessageModel
		im.setSendUser(sendUuid);	//发件人
		im.setContent(content);	//信息内容
		im.setSendType(sendType);	//发信人类别 0:平台 1:医生 2：患者
		im.setAccountType(accountType);	//收信人类别 0:平台 1:医生 2：患者
		im.setSendTime(DateFormatHelper.getNowTimeStr());	//发送时间
		im.setReadStatus(InnerMessage.READ_STATUS_UNREAD);	//未读
		im.setReceiveUser(receiveUuid);	//收件人
		im.setUniversalType(universalType);	//消息的通用的类型
		im.setUniversalUuid(universalUuid);	//消息的通用的id
		return im;
	}
	
	@Override
	public List<Map<String, Object>> selectInnerMessageList(String messageType, String receiveUser) throws Exception {
		if(ObjectUtils.isEmpty(messageType) || ObjectUtils.isEmpty(receiveUser)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT uuid,oper,opeTime,delFlag,title,content,sendUser,receiveUser,sendTime,fjDelStatus,readStatus,sjDelStatus,")
			.append(" messageType,accountType,userImage,image,orderUuid,sendStatus,messageOpenType,sendType,visitRecordUuid,universalType,")
			.append("universalUuid from inner_message where messageType = ? and receiveUser = ? order by sendTime desc");
		return dao.query(sbf.toString(), new Object[]{messageType, receiveUser}, null, getCache());
	}
	
	@Override
	public InnerMessage selectInnMageList(String doctorUuid, String customerUuid, String messageType) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(" select ").append( TABLE_COLUMNS )
		.append(" from inner_message as a where a.messageType=? and a.receiveUser=? and a.sendUser=? order by a.sendTime desc");
		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), new Object[]{messageType,doctorUuid,customerUuid}, null, getCache());
		
		return ObjectUtils.isEmpty(lstReturn) ? null : SimpleBean2DBHelper.map2Bean(lstReturn.get(0), InnerMessage.class);
	}
	
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getCacheName() {return "m1";}
	@Override
	protected String getQueryCacheName() {return "InnerMessageService";}

}