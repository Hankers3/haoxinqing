package com.hxq.mobile.doctor.content.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.hxq.mobile.doctor.content.service.ContentListService;
import com.hxq.mobile.doctor.content.service.ContentService;
import com.hxq.mobile.entity.common.ContentList;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.visit.Content;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.email.EmailUtil;
import com.hxq.mobile.util.email.Mail;
import com.wxcommon.util.ObjectUtils;

/**
 * 诊断标准 需要的表：content
 * 
 * @author dell
 *
 */
@Controller("com.hxq.mobile.doctor.content.controller.DiagnosticCriteriaController")
public class DiagnosticCriteriaController {

	@Resource(name = "com.hxq.mobile.doctor.content.service.ContentService")
	private ContentService contentService;

	@Resource(name = "com.hxq.mobile.doctor.common.service.PlatformInfoSerivce")
	private PlatformInfoSerivce platformInfoSerivce;

	@Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
	private ServiceStaffService serviceStaffService;

	/**
	 * 获取文章的service
	 */
	// @Autowired
	@Resource(name = "com.hxq.mobile.doctor.content.service.ContentListService")
	private ContentListService contentListService;

	/**
	 * 工具箱——诊断标准-查询的列表
	 * 
	 * @param request
	 * @param response
	 * @param contentCategoryUuid
	 *            内容uuid
	 * @param pageCount
	 *            条数
	 * @param pageNo
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "/app/service/tools/1.0/getContentList", method = RequestMethod.GET)
	public String getCustomerListByDoctorUuidAndGroupId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentCategoryUuid") String contentCategoryUuid,
			@RequestParam(value = "pageCount", required = false) Integer pageCount,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,
				new String[] { "callback,true", "contentCategoryUuid,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "分类id不能为空"),
					callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据分类id获得文章列表 内容分类uuid
		Object[] contentList = contentService.selectByContentCategoryUuid(contentCategoryUuid, pageCount, pageNo);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) contentList[0];
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();

		if (list != null && list.size() > 0) {
			for (Map<String, Object> cm : list) {
				Map<String, Object> save = new HashMap<String, Object>();

				Map<String, Object> image = platformInfoSerivce.selectFileMapByRemotePaths((String) cm.get("image"));// 标题图片
				save.put("img", image.get("remotePaths"));// 文章图片
				save.put("contentTitle", cm.get("contentTitle"));// 文章标题
				save.put("createTime", cm.get("createTime"));// 推荐时间
				save.put("author", cm.get("author"));// 作者
				save.put("provenance", cm.get("provenance"));// 出处
				save.put("contextUuid", cm.get("uuid"));// 文章Uuid
				relist.add(save);
			}
		}
		jsonMap.put("pageNo", contentList[1]);
		jsonMap.put("totalPage", contentList[2]);
		jsonMap.put("totalRows", contentList[3]);
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 工具箱——诊断标准详情-索取资料接口
	 * 
	 * @param request
	 * @param response
	 * @param doctorUuid
	 *            医生的id
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/1.0/getDoctorEmail", method = RequestMethod.GET)
	public String getDoctorEmail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("doctorUuid") String doctorUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,
				new String[] { "callback,true", "doctorUuid,true" }); // 标志
		// 以及必需要传的参数
		Object callback = map.get("callback");

		// 判断doctorUuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "没有该医生"),
					callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// ServicestaffModel sm = servicestaffService.getByUuid(doctorUuid);
		ServiceStaff sm = null;

		try {
			sm = (ServiceStaff) serviceStaffService.select(new ServiceStaff(doctorUuid));// 根据主键id查询医生信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != sm) {
			jsonMap.put("doctorEmail", sm.getEmail());// 消息字段
		} else {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "用户未登录，请登录"),
					callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 发送邮件、分享给用户
	 * 
	 * @param request
	 * @param doctorUuid：医生id
	 * @param contentUuid：资讯id
	 * @param doctorEmail：医生邮件
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/1.0/addContentList", method = RequestMethod.POST)
	public String addContentList(HttpServletRequest request, @RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("contentUuid") String contentUuid, @RequestParam("doctorEmail") String doctorEmail,
			HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		Map<String, String> map = CompatibleTools.getParam(request, response, false,
				new String[] { "callback,false", "doctorUuid,true", "contentUuid,true", "doctorEmail,true" });
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断参数是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "请登录"), callback);
			return null;
		}
		// 判断参数是否为空
		if (StringUtil.isEmpty(contentUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "文章不存在"),
					callback);
			return null;
		}
		// 判断参数是否为空
		if (StringUtil.isEmpty(doctorEmail)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "医生的邮箱不能为空"),
					callback);
			return null;
		}

		ContentList ctm = contentListService.getContentlistByConditions(doctorUuid, contentUuid, doctorEmail);// 通过contentList信息列表
		boolean sendMail = sendMail(doctorEmail, contentUuid);// 发送邮件

		if (ObjectUtils.isEmpty(ctm)) {
			try {
				// 新增获取文章的对象
				ContentList clm = new ContentList();
				if (sendMail) {
					clm.setState("1");
				} else {
					clm.setState("0");
				}
				clm.setDoctorUuid(doctorUuid);
				clm.setContentUuid(contentUuid);
				clm.setEmail(doctorEmail);
				clm.setCreateTime(DateFormatHelper.getNowTimeStr());
				contentListService.insert(clm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (sendMail) {
					if (ctm.getState().equals("0")) {
						ctm.setState("1");
						contentListService.update(ctm);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!sendMail) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "发送失败，请稍后重试。"),
					callback);
			return null;
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 判断邮件是否发送成功
	 * 
	 * @param doctorEmail
	 * @param contentUuid
	 * @return true：成功， false：失败
	 */
	private boolean sendMail(String doctorEmail, String contentUuid) {
		Mail user = new Mail();
		Content contentModel;
		try {
			contentModel = (Content) contentService.select(new Content(contentUuid));
			String contentUrl = contentModel.getUrl();

			String content = "";
			if (!TextUtils.isEmpty(contentUrl)) {
				content = new StringBuffer("请点击:").append(contentUrl).toString();
			} else {
				content = contentModel.getIntroduction();
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", content);
			map.put("title", contentModel.getContentTitle());
			user.setMap(map);

			user.setTo(doctorEmail);
			try {
				user.setSubject(MimeUtility.encodeText("好心情推荐文章提示邮件", "utf-8", "B"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			boolean isSendSuc = EmailUtil.sendTemplate(user);

			return isSendSuc;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return false;
		}
	}

}
