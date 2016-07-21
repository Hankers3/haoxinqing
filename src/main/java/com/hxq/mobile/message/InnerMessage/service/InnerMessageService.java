package com.hxq.mobile.message.InnerMessage.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.hxq.mobile.entity.message.InnerMessage;
import com.hxq.mobile.util.repository.SimpleEntityService;

public interface InnerMessageService extends SimpleEntityService {

	/**
	 * 发送站内信
	 *
	 * @param receiveUser
	 * @param title
	 * @param content
	 * @throws Exception
	 */
	public int insertInnerMessage(String receiveUser, String title, String content) throws Exception;

	/**
	 * 删除站内信
	 *
	 * @param needDeleteUuids
	 */
	public void deleteInnerMessage(List<String> needDeleteUuids, String type) throws Exception;

	/**
	 * 根据消息类别获取消息列表
	 *
	 * @param messageType
	 * @param doctorUuid
	 * @return
	 */
	public List<InnerMessage> selectInnerMessageListByMessageType(String messageType, String doctorUuid);

	/**
	 * 根据用户Id获取消息列表
	 *
	 * @param userId
	 * @return
	 */
	public List<InnerMessage> selectInnerMessageListByUserId(String userId,String readStatus,int pageCount,int pageNo);

	/**
	 * 保存订单相关站内信推送消息
	 *
	 * @author zdx
	 * @param orderUuid
	 * @param userId
	 * @param title
	 * @param content
	 * @param msgType
	 * @throws Exception
	 */
	public int insertServiceInnerMessage(String orderUuid, String userId, String title, String content, String msgType) throws Exception;

	/**
	 * 通过uuid得到发送给该医生的消息
	 *
	 * @param userId
	 * @return
	 */
	public List<InnerMessage> selectCollectMeListByReceiveUser(String userId, String messageType);

	/**
	 * 返回消息中心未读数量
	 */
	public int selectMessageCenterCount(String messageType, String userId);

	/**
	 * 保存消息到数据库，并发送到手机终端
	 * @param sendUuid 发送者ID
	 * @param receiveUuid 接受者ID
	 * @param content 消息体
	 * @param sendType 发送者类型
	 * @param accountType 接受者类型
	 * @param pushClient 推送客户端
	 * @param messageKey
	 * @param messageValue
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public int insertInnerMessageAndPush(String sendUuid, String receiveUuid, String content, String sendType,
			String accountType, String pushClient,String messageKey,String messageValue, String string) throws Exception;

	/**
	 * 关注我的人专用消息推送接口
	 * @param customerUuid
	 * @param doctorUuid
	 * @param content
	 * @param accountTypeCustomer
	 * @param accountTypeStore
	 * @param pushClientService
	 * @param string
	 * @param customerUuid2
	 * @param string2
	 * @return
	 * @throws Exception
	 */
	public int insertInnerMessageAndPushForConcernDoctor(String customerUuid, String doctorUuid,
            String content, String accountTypeCustomer, String accountTypeStore, String pushClientService,
            String string, String customerUuid2, String string2) throws Exception;

	/**
	 * 申请随访的消息推送
	 * @param customerUuid
	 * @param doctorUuid
	 * @param content
	 * @param accountTypeCustomer
	 * @param accountTypeStore
	 * @param pushClientCustomer
	 * @param string
	 * @param uuid
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public int insertInnerMessageAndPushForVisitRecord(String customerUuid, String doctorUuid, String content,
            String accountTypeCustomer, String accountTypeStore, String pushClientCustomer, String string,
            String uuid, String value) throws Exception;

	/**
	 * 文件上传
	 *
	 * @param innerMessageModel
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public InnerMessage insertForUploadImage(InnerMessage innerMessage, MultipartFile[] files) throws Exception;

	public List<Map<String, Object>> selectInnerMessageList(String messageType, String doctorUuid) throws Exception;
	
	/**
	 * 根据医生id，患者id， 状态查询消息
	 */
	public InnerMessage selectInnMageList(String doctorUuid,String customerUuid,String messageType);
}
