package com.hxq.mobile.message.InnerMessage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.message.InnerMessage;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.message.InnerMessage.controller.InnerMessageController")
public class InnerMessageController {
	Logger log = LoggerFactory.getLogger(InnerMessageController.class);


	@Resource(name = "com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
	public InnerMessageService innerMessageService;

	@Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
	public CustomerInfoService customerInfoService;
	
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;
	
	@Autowired
	private FileService fileService;
    
	/**
	 * 根据参数返回消息列表
	 * 
	 * @param request
	 * @param response
	 * @param messageType
	 *            消息类型 0最近收藏我的人 1：随访消息 2：在线咨询消息
	 * 
	 */
	@RequestMapping(value = "/app/service/doctor/2.0/getcollectMeMSG", method = RequestMethod.GET)
	public @ResponseBody ApiResult getcollectMeMSG(HttpServletRequest request, 
			@RequestParam("messageType") String messageType, @RequestParam("doctorUuid") String doctorUuid) {
		if (ObjectUtils.isEmpty(messageType) || ObjectUtils.isEmpty(doctorUuid)) return null;
		try {
			List relist = new ArrayList();
			Map<String, Object> save = null;
			CustomerInfo cim = null;
			Customer cm = null;
			String sendType = null;
			String sendUser = null;
			List<Map<String, Object>> messageList = innerMessageService.selectInnerMessageList(messageType, doctorUuid);
			if (messageList != null && messageList.size() > 0) {
				for (Map<String, Object> imm : messageList) {
					save = new HashMap<String, Object>();
					sendType = (String) imm.get("sendType");// 发信人类型 1：医生 2：患者
					sendUser = (String) imm.get("sendUser");// 编号
					
					if (sendType.trim().equals("2")) {  // 获取患者信息
						cim = customerInfoService.selectByCustomerUuid(sendUser);
						cm = (Customer) customerInfoService.select(new Customer(sendUser));
						if (!ObjectUtils.isEmpty(cim)) {
							save.put("customerName", cm.getCustomerName());// 患者名
							Image4App[] url = CompatibleTools.getImages(imgUploadService, fileService, cim.getImage());// 头像
							if (!ObjectUtils.isEmpty(url)) save.put("image", url[0]);
							save.put("age", cim.getAge());
							save.put("sex", cim.getSex());
						}
	//					// 类型为1时加入随访id
	//					if ("1".equals(messageType)) {
	//						save.put("visitRecordUuid", imm.getVisitRecordUuid());
	//					}
						save.put("showContent", imm.get("showContent"));	// 内容
						save.put("sendTime", imm.get("sendTime"));// 时间
						save.put("customerUuid", imm.get("sendUser"));
						save.put("innerMessageId", imm.get("uuid"));
						save.put("readStatus", imm.get("readStatus"));
						relist.add(save);
					} 
				}
			}
			return ApiResult.right(relist);
		} catch (Exception e) {
			log.error("",e);
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}
	
    /**
     * 更改医生的消息中心未读消息
     */
    @RequestMapping(value="/app/service/doctor/2.0/getUpdateInnerMessage", method=RequestMethod.POST)
    public @ResponseBody ApiResult delete(@RequestParam("innerMessageId") String innerMessageId) {
    	if (ObjectUtils.isEmpty(innerMessageId)) return null;
    	InnerMessage innerMessage = new InnerMessage();
		try {
			innerMessage.setUuid(innerMessageId);
			innerMessage.setReadStatus("1");
			innerMessageService.update(innerMessage);
			return ApiResult.right();
		} catch (Exception e) {
			log.error(innerMessageId,"");
			return ApiResult.error(ApiCode.SERVER_ERROR, "修改失败！");
		}
		
    }
}
