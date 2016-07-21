package com.aebiz.b2b2c.cms.sysback.web.innermessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.innermessagenotice.service.InnerMessageNoticeService;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelService;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestafflevel.service.ServicestafflevelService;
import com.aebiz.b2b2c.servicestaff.servicestafflevel.vo.ServicestafflevelModel;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.service.StaffLoginStatusService;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;

@Controller
@RequestMapping("/sysback/innermessage")
public class InnerMessageController extends
		BaseController<InnerMessageModel, InnerMessageQueryModel> {
	private InnerMessageService innerMessageService;

	@Autowired
	public void setMyService(InnerMessageService bs) {
		this.innerMessageService = bs;
		super.setBs(bs);
	}

	// 注入家政员等级service
	@Autowired
	private ServicestafflevelService stafflevelService;

	@Autowired
	private ServicestaffService serviceStaffService;

	// 注入会员等级service
	@Autowired
	private CustomerShopLevelService customerLevel;

	@Autowired
	private VipclubIntegralStatService vipService;
	@Autowired
	private StaffLoginStatusService staffLoginStatusService;

	public InnerMessageController() {
		super("cms/sysback/innermessage", "InnerMessage",
				InnerMessageController.class);
	}

	// 注入会员service
	@Autowired
	private CustomerService customerService;
	@Autowired
	private InnerMessageNoticeService innerMessageNoticeService;

	/**
	 * 保存会员平台等级
	 * 
	 * @param m
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public String save(@ModelAttribute("m") InnerMessageModel m,
			HttpServletRequest request, Model model) {

		// 校验前台数据
		/*
		 * this.getMapErrorMsg().clear(); if (!checkAdd(model, m, request)) {
		 * request.setAttribute("ShowMsgs", getMapErrorMsg()); return (String)
		 * request.getAttribute("ERROR_BACK_URL"); }
		 * innerMessageService.uploadImage(m, files);
		 */
		// 得到医生或者患者ids
		String DoctorUuids = request.getParameter("doctorUuids");
		String CustomerUuids = request.getParameter("customerUuids");
		String[] doctorUuids = null;
		String[] customerUuids = null;
		if (!StringUtil.isEmpty(DoctorUuids)) {
			doctorUuids = DoctorUuids.split(",");
		}
		if (!StringUtil.isEmpty(CustomerUuids)) {
			customerUuids = CustomerUuids.split(",");
		}
		// 如果医生id不为空就循环添加医生消息
		if (doctorUuids != null && doctorUuids.length > 0) {
			for (String doctorUuid : doctorUuids) {
				//  收件人
				m.setReceiveUser(doctorUuid);
				// 发件人类别为平台
				m.setSendType("0");
				// 发送时间 设为当前时间
				m.setSendTime(DateFormatHelper.getNowTimeStr());
				innerMessageService.create(m);
			}
		}
		// 如果患者id不为空就循环添加患者消息
		if (customerUuids != null && customerUuids.length > 0) {
			for (String customerUuid : customerUuids) {
				//  收件人
				m.setReceiveUser(customerUuid);
				// 发件人类别为平台
				m.setSendType("0");
				// 发送时间 设为当前时间
				m.setSendTime(DateFormatHelper.getNowTimeStr());
				innerMessageService.create(m);
			}
		}
		if (!StringUtil.isEmpty(DoctorUuids)) {
			// 表示添加医生的消息返回到查看医生消息列表的
			request.setAttribute("judge", "1");
		}
		if (!StringUtil.isEmpty(CustomerUuids)) {
			// 表示添加患者的消息返回到查看患者消息列表的
			request.setAttribute("judge", "0");
		}
		return "cms/sysback/innermessage/InnerMessageSuccess";
	}

	/**
	 * 跳转到未发送的站内信管理列表页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toInnerList", method = RequestMethod.GET)
	public String toInnerList(Model model, HttpServletRequest request) {

		return "cms/sysback/innermessage/InnerNoSendMessageList";
	}

	/**
	 * 跳转到家政员按等级推送页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPushInnerMessage/{uuid}", method = RequestMethod.POST)
	public String toPushInnerMessage(Model model,
			@PathVariable("uuid") String innerUuid, HttpServletRequest request) {
		// 得到所有的家政员等级
		List<ServicestafflevelModel> list = stafflevelService.getAll();

		model.addAttribute("levelList", list);
		model.addAttribute("innerUuid", innerUuid);

		return "cms/sysback/innermessage/ToSendMessageList";

	}

	/**
	 * 跳转到会员按等级推送页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPushUserInnerMessage/{uuid}", method = RequestMethod.POST)
	public String toPushUserInnerMessage(Model model,
			@PathVariable("uuid") String innerUuid, HttpServletRequest request) {
		// 得到所有的会员等级
		List<CustomerShopLevelModel> list = customerLevel.getAll();
		model.addAttribute("levelList", list);
		model.addAttribute("innerUuid", innerUuid);

		return "cms/sysback/innermessage/ToSendUserMessageList";

	}

	/**
	 * 发送站内信给客户端
	 * 
	 * @param model
	 * @param innerUuid
	 * @param levelUuid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pushUserInnerMessage/{uuid}", method = RequestMethod.POST)
	@ResponseBody
	public String pushUserInnerMessage(Model model,
			@PathVariable("uuid") String innerUuid,
			@RequestParam("levelUuid") String levelUuid,
			HttpServletRequest request) {
		// 获得要发送的站内信息
		InnerMessageModel im = innerMessageService.getByUuid(innerUuid);

		// 获得当前等级下的所有会员Uuids
		List<String> userUuids = new ArrayList<String>();
		if ("all".equals(levelUuid)) {
			// 所有未冻结的
			userUuids = customerService.getCustomerUuidsByState("0");
		} else {
			CustomerShopLevelModel lm = customerLevel.getByUuid(levelUuid);
			int minIntegral = lm.getMinIntegral();
			int maxIntegral = lm.getMaxIntegral();
			// userUuids = vipService.getCustomerUuids(minIntegral,
			// maxIntegral);
		}
		if (userUuids == null && userUuids.size() <= 0) {
			return "push_null";
		}
		String push_success = "";
		String message_title = im.getTitle();
		String time = DateFormatHelper.getNowTimeStr();
		List sendUuids = new ArrayList();
		if (userUuids != null && userUuids.size() > 0) {
			for (int i = 0; i < userUuids.size(); i++) {
				String userId = userUuids.get(i);
				boolean userLogin = staffLoginStatusService
						.serviceStaffLogin(userId);
				if (userLogin) {
					sendUuids.add(userId);
					InnerMessageNoticeModel innerMessageNoticeModel = new InnerMessageNoticeModel();
					innerMessageNoticeModel.setUserId(userId);
					innerMessageNoticeModel.setMessageUuid(innerUuid);
					innerMessageNoticeModel.setReadStatus("0");
					innerMessageNoticeModel.setOpeTime(time);
					innerMessageNoticeService.create(innerMessageNoticeModel);
				} else {
					continue;
				}
			}
		}
		if (sendUuids.size() <= 0) {
			return "no_user";
		}
		try {

			Map<String, String> map = new HashMap<String, String>();
			// 平台：家政端为1，客户端为2
			map.put("pushClient", "2");
			// 推送消息类型：系统通知：1、消息：2
			map.put("pushType", "1");

			// map.put("pushType", "2");
			map.put("message", message_title);
			map.put("messageId", innerUuid);

			// push_success =JPushService.pushSystemMessage(sendUuids, map);

			if ("push_success".equals(push_success)) {
				im.setSendStatus("1");
				innerMessageService.update(im);
			}
		} catch (Exception e) {
			String message = e.getMessage();
			JSONObject js = JSONObject.fromObject(message);
			JSONObject er = js.getJSONObject("error");

			push_success = er.getString("code");
			// e.printStackTrace();
		}
		return push_success;
	}

	/**
	 * 跳转到医生端消息记录 szr
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toInnerMessageDoctorList", method = RequestMethod.GET)
	public String toInnerMessageDoctorList(Model model,
			HttpServletRequest request) {

		return "cms/sysback/innermessage/InnerMessageDoctorList";

	}

	/**
	 * 跳转到患者端消息记录 szr
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toInnerMessageCustomerList", method = RequestMethod.GET)
	public String toInnerMessageCustomerList(Model model,
			HttpServletRequest request) {

		return "cms/sysback/innermessage/InnerMessageCustomerList";

	}

}