package com.aebiz.b2b2c.cms.innermessage.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;

public interface InnerMessageService extends BaseService<InnerMessageModel, InnerMessageQueryModel> {

	/**
	 * 发送站内信
	 * 
	 * @param receiveUser
	 * @param title
	 * @param content
	 */
	public void saveInnerMessage(String receiveUser, String title, String content);

	/**
	 * 删除站内信
	 * 
	 * @param needDeleteUuids
	 */
	public void deleteInnerMessage(List<String> needDeleteUuids, String type);

	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 * @param doctorUuid
	 * @return
	 */

	public List<InnerMessageModel> getInnerMessageListByMessageType(String messageType, String doctorUuid);

	/**
	 * 根据用户Id获取消息列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<InnerMessageModel> getInnerMessageListByUserId(String userId, String readStatus, int pageCount,
			int pageNo);

	/**
	 * 文件上传
	 * 
	 * @param innerMessageModel
	 * @param files
	 * @return
	 */
	public InnerMessageModel uploadImage(InnerMessageModel innerMessageModel, MultipartFile[] files);

	/**
	 * 保存订单相关站内信推送消息
	 * 
	 * @author zdx
	 * @param orderUuid
	 * @param userId
	 * @param title
	 * @param content
	 * @param msgType
	 */
	public void saveServiceInnerMessage(String orderUuid, String userId, String title, String content, String msgType);

	/**
	 * 通过uuid得到发送给该医生的消息
	 * 
	 * @param userId
	 * @return
	 */
	public List<InnerMessageModel> getCollectMeListByReceiveUser(String userId, String messageType);

	/**
	 * 返回消息中心未读数量
	 */
	public int getMessageCenterCount(String messageType, String userId);

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
	 * @param string 
	 */
	public void saveInnerMessageAndPush(String sendUuid, String receiveUuid, String content, String sendType,
			String accountType, String pushClient,String messageKey,String messageValue, String string);
	/**
	 * 
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
	public void saveInnerMessageAndPushForConcernDoctor(String customerUuid, String doctorUuid,
            String content, String accountTypeCustomer, String accountTypeStore, String pushClientService,
            String string, String customerUuid2, String string2);
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
	public void saveInnerMessageAndPushForVisitRecord(String customerUuid, String doctorUuid, String content,
            String accountTypeCustomer, String accountTypeStore, String pushClientCustomer, String string,
            String uuid, String value);


}
