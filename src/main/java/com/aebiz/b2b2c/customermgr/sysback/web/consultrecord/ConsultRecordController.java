package com.aebiz.b2b2c.customermgr.sysback.web.consultrecord;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordQueryModel;
import com.aebiz.b2b2c.customermgr.consultrecordreply.service.ConsultRecordReplyService;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/consultrecord")
public class ConsultRecordController extends BaseController<ConsultRecordModel, ConsultRecordQueryModel> {
	private ConsultRecordService myService;

	@Autowired
	public void setMyService(ConsultRecordService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ConsultRecordController() {
		super("/customermgr/sysback/consultrecord", "ConsultRecord", ConsultRecordController.class);
	}

	// 注入患者的service
	private CustomerInfoService customerInfoService;

	@Autowired
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}

	// 注入医生的service
	private ServicestaffService servicestaffService;

	@Autowired
	public void setServicestaffService(ServicestaffService servicestaffService) {
		this.servicestaffService = servicestaffService;
	}

	/* 积分的service */
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;
	/* 图文咨询回复的service */
	@Autowired
	private ConsultRecordReplyService consultRecordReplyService;
	/* 注入标签库service */
	@Autowired
	private TagsService tagsService;

	/**
	 * 跳转到网上资讯的页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toOlineList", method = RequestMethod.GET)
	public String toOlineList(Model model, HttpServletRequest request) {

		return "/customermgr/sysback/consultrecord/ConsultRecordOlineList";
	}

	/**
	 * 跳转到电话资讯的页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toTelephoneList", method = RequestMethod.GET)
	public String toTelephoneList(Model model, HttpServletRequest request) {

		return "/customermgr/sysback/consultrecord/ConsultRecordTelephoneList";
	}

	/**
	 * 跳转到预约加号的页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toAppointmentList", method = RequestMethod.GET)
	public String toAppointmenList(Model model, HttpServletRequest request) {

		return "/customermgr/sysback/consultrecord/ConsultRecordAppointmentList";
	}

	/**
	 * 修改审核状态
	 * 
	 * @param uuid
	 * @param model
	 * @param state
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateState/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public String updateState(@PathVariable("uuid") String uuid, Model model, @RequestParam("state") String state,
			@RequestParam("refuseReason") String refuseReason, HttpServletRequest request,
			HttpServletResponse response) {

		ConsultRecordModel m = myService.getByUuid(uuid);
		if (m != null) {
			m.setState(state);
			m.setRefuseReason(refuseReason);
			m.setDocoterName(LoginUserHelper.getLoginUserUuid());
			myService.update(m);

			VipclubIntegralLogModel vim = vipclubIntegralLogService.getVipclubIntegralLogByConditions(m.getDoctorUuid(),
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, m.getUuid(), m.getUuid());
			if (null != vim && "1".equals(state)) {
				vipclubIntegralLogService.saveVipIntegralLog(m.getDoctorUuid(), "add", 20, "5",
						VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "完成预约加号订单", m.getUuid());
			}
		}
		Map jsonMap = new HashMap();
		jsonMap.put("rsp", Boolean.valueOf(true));
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(jsonMap));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 查看图文咨询详细信息【审核】
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/toDetail/{uuid}")
	public String toDetail(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
		// 通过id获取 ConsultRecordModel对象
		ConsultRecordModel consultRecordModel = myService.getByUuid(uuid);
		preparedUpdateData(model, request);
		model.addAttribute("m", consultRecordModel);
		// 获取患者的customerModel对像
		String customerUuid = consultRecordModel.getCustomerUuid();
		if (!StringUtil.isEmpty(customerUuid)) {
			CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
			model.addAttribute("customerInfoModel", customerInfoModel);
		}

		// 获取医生的ServicestaffModel对像
		String doctorUuid = consultRecordModel.getDoctorUuid();
		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
			model.addAttribute("servicestaffModel", servicestaffModel);
		}
		List<ConsultRecordReplyModel> list = consultRecordReplyService.getConsultRecordReplListByUuid(uuid);
		if (null != list && list.size() > 0) {
			model.addAttribute("list", list);
		}
		String consultTag = consultRecordModel.getTagsUuid();
		List<TagsModel> tags = tagsService.getTagsByDoctorTag(consultTag);
		model.addAttribute("tags", tags);

		List<TagsModel> tagList = tagsService.getTagListByConsultTag(consultTag);
		if (null != tagList && tagList.size() > 0) {
			model.addAttribute("tagList", tagList);
		}

		return "/customermgr/sysback/consultrecord/ConsultRecordDetail";

	}

	/**
	 * 查看图文咨询详细信息【查看】
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/toView/{uuid}")
	public String toView(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
		// 通过id获取 ConsultRecordModel对象
		ConsultRecordModel consultRecordModel = myService.getByUuid(uuid);
		preparedUpdateData(model, request);
		model.addAttribute("m", consultRecordModel);
		// 获取患者的customerModel对像
		String customerUuid = consultRecordModel.getCustomerUuid();
		if (!StringUtil.isEmpty(customerUuid)) {
			CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
			;
			model.addAttribute("customerInfoModel", customerInfoModel);
		}

		// 获取医生的ServicestaffModel对像
		String doctorUuid = consultRecordModel.getDoctorUuid();
		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
			model.addAttribute("servicestaffModel", servicestaffModel);
		}
		List<ConsultRecordReplyModel> list = consultRecordReplyService.getConsultRecordReplListByUuid(uuid);
		if (null != list && list.size() > 0) {
			model.addAttribute("list", list);
		}
		String consultTag = consultRecordModel.getTagsUuid();
		List<TagsModel> tags = tagsService.getTagsByDoctorTag(consultTag);
		model.addAttribute("tags", tags);

		List<TagsModel> tagList = tagsService.getTagListByConsultTag(consultTag);
		if (null != tagList && tagList.size() > 0) {
			model.addAttribute("tagList", tagList);
		}

		return "/customermgr/sysback/consultrecord/ConsultRecordView";

	}

	/**
	 * 
	 * 跳转到图文资讯待审核页面
	 */
	@RequestMapping(value = "/toListUnpass", method = RequestMethod.GET)
	public String toListUnpass(Model model, HttpServletRequest request) {

		return "/customermgr/sysback/consultrecord/ConsultRecordListUnpass";
	}

	/**
	 * 
	 * 跳转到加号待审核页面
	 */
	@RequestMapping(value = "/toAppointmentListWait", method = RequestMethod.GET)
	public String toAppointmentListWait(Model model, HttpServletRequest request) {
		return "/customermgr/sysback/consultrecord/AppointmentListWait";
	}

	/**
	 * 
	 * 跳转到加号详情页【审核】
	 */
	@RequestMapping("/toDetailAppointment/{uuid}")
	public String toDetailAppointment(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
		// 通过id获取 ConsultRecordModel对象
		ConsultRecordModel consultRecordModel = myService.getByUuid(uuid);
		preparedUpdateData(model, request);
		model.addAttribute("m", consultRecordModel);
		// 获取患者的customerModel对像
		String customerUuid = consultRecordModel.getCustomerUuid();
		if (!StringUtil.isEmpty(customerUuid)) {
			CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
			;
			model.addAttribute("customerInfoModel", customerInfoModel);
		}

		// 获取医生的ServicestaffModel对像
		String doctorUuid = consultRecordModel.getDoctorUuid();
		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
			model.addAttribute("servicestaffModel", servicestaffModel);
		}

		// 获取订单的model对象
		/*
		 * String orderUuid = consultRecordModel.getDoctorUuid(); if
		 * (!StringUtil.isEmpty(doctorUuid)) { ServicestaffModel
		 * servicestaffModel = servicestaffService .getByUuid(doctorUuid);
		 * model.addAttribute("servicestaffModel", servicestaffModel); }
		 */

		return "/customermgr/sysback/consultrecord/ConsultRecordDetailAppointment";

	}

	/**
	 * 
	 * 跳转到加号详情页【查看】
	 */
	@RequestMapping("/toViewAppointment/{uuid}")
	public String toViewAppointment(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request) {
		// 通过id获取 ConsultRecordModel对象
		ConsultRecordModel consultRecordModel = myService.getByUuid(uuid);
		preparedUpdateData(model, request);
		model.addAttribute("m", consultRecordModel);
		// 获取患者的customerModel对像
		String customerUuid = consultRecordModel.getCustomerUuid();
		if (!StringUtil.isEmpty(customerUuid)) {
			CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(customerUuid);
			;
			model.addAttribute("customerInfoModel", customerInfoModel);
		}

		// 获取医生的ServicestaffModel对像
		String doctorUuid = consultRecordModel.getDoctorUuid();
		if (!StringUtil.isEmpty(doctorUuid)) {
			ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
			model.addAttribute("servicestaffModel", servicestaffModel);
		}

		return "/customermgr/sysback/consultrecord/ConsultRecordViewAppointment";

	}

	/**
	 * 
	 * @Description: (重写queryList方法)
	 * @author XP
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2016-1-4 下午4:37:59
	 */
	@Override
	@RequestMapping("/queryList")
	public String queryList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		List showList = new ArrayList();

		Map pageParamMap = parsePageParam(request);

		ConsultRecordQueryModel qm = parseQueryModel(request);

		qm = preparedQMFixValue(qm);

		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart")).intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength")).intValue();

		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_" + iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}

		List listData = this.myService.getByConditionq(qm, iDisplayStart, iDisplayLength);

		int totalCount = this.myService.getCount(qm);

		for (int i = 0; i < listData.size(); ++i) {
			ConsultRecordModel m = (ConsultRecordModel) listData.get(i);
			if (null != m) {
				showList.add(m);
			}
		}

		Map jsonMap = new HashMap();

		jsonMap.put("sEcho", pageParamMap.get("sEcho"));
		jsonMap.put("iTotalRecords", Integer.valueOf(totalCount));
		jsonMap.put("iTotalDisplayRecords", Integer.valueOf(totalCount));
		jsonMap.put("aaData", showList);

		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.print(JSON.toJSONString(jsonMap));

		return null;
	}

	/**
	 * 增加医生标签
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/addTag" }, method = { RequestMethod.GET })
	@ResponseBody
	public String addTag(Model model, @RequestParam("uuid") String uuid, @RequestParam("tagId") String tagId,
			HttpServletRequest request) {
		// 调用service 删除医生对应标签
		myService.addTag(uuid, tagId);

		return "success";
	}

	/**
	 * 删除医生标签
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/toDeleteTag" }, method = { RequestMethod.GET })
	@ResponseBody
	public String toDeleteTag(Model model, @RequestParam("uuid") String uuid, @RequestParam("tagUuid") String tagUuid,
			HttpServletRequest request) {
		// 调用service 删除医生对应标签
		myService.deleteTag(uuid, tagUuid);

		return "success";
	}

}