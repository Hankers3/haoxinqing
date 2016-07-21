package com.hxq.mobile.doctor.content.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce;
import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.hxq.mobile.doctor.content.service.ContentService;
import com.hxq.mobile.doctor.content.service.DoctorShareService;

import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;


@Controller("com.hxq.mobile.doctor.content.controller.DoctorContentController")
@RequestMapping("/mobile/doctor/content")
public class DoctorContentController {
	Logger log = LoggerFactory.getLogger(DoctorContentController.class);

	/*资讯Service */
	@Resource(name = "com.hxq.mobile.doctor.content.service.ContentService")
	private ContentService contentService;

	/*医生分享service*/
	@Resource(name = "com.hxq.mobile.doctor.content.service.DoctorShareService")
	private DoctorShareService doctorShareService;
	/*收藏*/
	@Resource(name = "com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce")
	private FavoriteInformationSerivce favoriteInformationSerivce;
	
	/* 图片service */
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;
	
	@Resource(name = "com.hxq.mobile.doctor.common.service.PlatformInfoSerivce")
	private PlatformInfoSerivce platformInfoSerivce;
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;
	/**
	 * 获取资讯查询的列表  & 工具箱——临床量表的查询列表
	 * @param request
	 * @param response
	 * @param contentCategoryUuid 分类内容id （ A表示指南共识、B表示诊疗标准、C表示临床量表、D表示最新资讯、E表示医学文献、F表示专家言论）
	 * @return
	 */
	@RequestMapping(value = "/1.0/getAllContentList", method = RequestMethod.GET)
	public String getAllContentList1(HttpServletRequest request, HttpServletResponse response,
								    @RequestParam("contentCategoryUuid") String contentCategoryUuid,
								    @RequestParam(value = "doctorUuid",required = false) String doctorUuid,
								    @RequestParam(value ="pageCount",required = false) Integer pageCount,
								    @RequestParam(value = "pageNo",required = false) Integer pageNo) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,new String[]{"contentCategoryUuid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		Object callback = map.get("callback");
		// 判断有没有咨询信息
		if (StringUtil.isEmpty(contentCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "没有咨讯信息"), callback);
			return null;
		}
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		Object[] result = contentService.selectByContentCategoryUuid(contentCategoryUuid, pageCount, pageNo);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>)result[0];
		int contentCount = 0;//收藏的数量
		String contentType = null;//资讯类型
		
		if (list != null && list.size() > 0) {	
			for (Map<String, Object> cm : list) {
				Map<String, Object> save = new HashMap<String, Object>();
				if (cm != null) {
					
					contentType = (String) cm.get("contentType");//资讯类型
					
					Map<String, Object> image = platformInfoSerivce.selectFileMapByRemotePaths((String) cm.get("image"));//标题图片
					if (!ObjectUtils.isEmpty(image)) {
						save.put("imgUrl", image.get("remotePaths"));//图片
					}else {
						save.put("imgUrl", "");//图片
					}
					
					if(cm.get("uuid") != null){
						save.put("contentUuid", cm.get("uuid"));//资讯uuid
						contentCount = favoriteInformationSerivce.selectFavoriteInformationByContentUuid((String) cm.get("uuid"));
						save.put("contentCount", contentCount);//收藏总数
					}else{
						save.put("contentUuid", "");//资讯uuid
						save.put("contentCount", contentCount);//收藏总数
					}
					save.put("contentType", contentType);// 3:医脉通 跳转到医脉通自己的
					save.put("contentUrl", cm.get("url"));//链接地址
					
					if (!ObjectUtils.isEmpty(doctorUuid) && !ObjectUtils.isEmpty(cm.get("uuid"))) {
						int storeType = favoriteInformationSerivce.selectStateCollectInformationByCount(doctorUuid, (String) cm.get("uuid"));
						save.put("storeType", storeType);// 0代表未收藏 1代表已收藏
						int shareType = doctorShareService.selectShareTypeByDoctorUuidAndContenUuid(doctorUuid,(String)cm.get("Uuid"));
						save.put("shareType", shareType);// 0代表未分享 1代表已分享
						String favoriteUuid = favoriteInformationSerivce.selectUuidByCustomerUuidAndContextUuid(doctorUuid,(String)cm.get("uuid"));
						save.put("favoriteUuid", favoriteUuid);
					}
					save.put("contentTitle", cm.get("contentTitle"));
					save.put("creatime", cm.get("createTime"));
					
					relist.add(save);
				}
			}
		}
		jsonMap.put("pageNo", result[1]);//当前页号
		jsonMap.put("totalPage", result[2]);//总页数
		jsonMap.put("totalRows", result[3]);//总记录数
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	/**
	 * 获取通过筛选条件可到的资讯列表
	 * @param request
	 * @param response
	 * @param contentCategoryUuid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/1.0/getSearchContentList", method = RequestMethod.POST)
	public String getSearchContentList(HttpServletRequest request, HttpServletResponse response,
									   @RequestParam("contentCategoryUuid") String contentCategoryUuid,
									   @RequestParam(value ="doctorUuid",required =false) String doctorUuid,
									   @RequestParam(value ="pageCount",required =false) Integer pageCount,
									   @RequestParam(value ="pageNo",required =false) Integer pageNo) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,new String[]{"contentCategoryUuid,true", "callback,true"});
		Object callback = map.get("callback");
		// 判断有没有咨询信息
		if (StringUtil.isEmpty(contentCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "没有咨讯信息"), callback);
			return null;
		}
		String contentTitle = request.getParameter("contentTitle");// 获取内容名称
		String illnessId = request.getParameter("illnessId");// 获取疾病编号
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		Object[] lstRows = contentService.selectScreenByContentCategoryUuid(contentCategoryUuid, contentTitle, illnessId, pageCount, pageNo);
		
		List<Map<String, Object>> list = (List<Map<String, Object>>)lstRows[0];//转换格式
		
		if (ObjectUtils.isEmpty(list)) {//最新（推荐）
			lstRows = contentService.selectScreenByContentCategoryUuid(contentCategoryUuid, null, null, pageCount, pageNo);
			list = (List<Map<String, Object>>)lstRows[0];
			jsonMap.put("empty", true);
		}
		if(ObjectUtils.isEmpty(list)) {
			HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
			return null;
		}

		int contentCount = 0;
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> cm : list) {
			Map<String, Object> save = new HashMap<String, Object>();
			if (cm != null) {
				String contentType = (String) cm.get("contentType");
				Map<String, Object> image = platformInfoSerivce.selectFileMapByRemotePaths((String) cm.get("image"));//标题图片
				save.put("imgUrl", image.get("remotePaths"));
				if(cm.get("uuid") != null){
					save.put("contentUuid", cm.get("uuid"));//资讯uuid
					contentCount = favoriteInformationSerivce.selectFavoriteInformationByContentUuid((String) cm.get("uuid"));
					save.put("contentCount", contentCount);//收藏总数
				}else{
					save.put("contentUuid", "");//资讯uuid
					save.put("contentCount", contentCount);//收藏总数
				}
				
				save.put("contentType", contentType);
				save.put("contentUrl", cm.get("url"));
				if (!StringUtil.isEmpty(doctorUuid) && !ObjectUtils.isEmpty(cm.get("uuid"))) {
					int storeType =favoriteInformationSerivce.selectStateCollectInformationByCount(doctorUuid, (String)cm.get("uuid"));
					save.put("storeType", storeType);// 0代表未收藏 1代表已收藏
					int shareType = doctorShareService.selectShareTypeByDoctorUuidAndContenUuid(doctorUuid,(String)cm.get("uuid"));
					save.put("shareType", shareType);// 0代表未分享 1代表已分享
					String favoriteUuid = favoriteInformationSerivce.selectUuidByCustomerUuidAndContextUuid(doctorUuid,(String)cm.get("uuid"));
					save.put("favoriteUuid", favoriteUuid);
				
				}
				save.put("contentTitle", cm.get("contentTitle"));
				save.put("creatime", cm.get("createTime"));
				
				relist.add(save);
			}
		}
		jsonMap.put("pageNo", lstRows[1]);
		jsonMap.put("totalPage", lstRows[2]);
		jsonMap.put("totalRows", lstRows[3]);
		
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	/**
	 * 获取资讯的详细信息& 获取诊断标准的详细信息
	 * contentUuid：资讯id
	 */
	@RequestMapping(value = "/1.0/getContent", method = RequestMethod.GET)
	public String getContentByUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentUuid") String contentUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
	
		// 标志 以及必需要传的参数
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,new String[] { "callback,true", "contentUuid,true" });
		String doctorUuid = request.getParameter("doctorUuid");
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "文章id不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);	
		List<Map<String, Object>> content = contentService.selectContent(contentUuid);
		// 判空
		if (content == null) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "文章为空"), callback);
			return null;
		}
		for (Map<String, Object> contents : content) {
			
			if(!StringUtil.isEmpty(doctorUuid)){
				vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 0, IntegralType.ADD_SHARE_BY_ARTICLE.getValue(),
						VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "分享"+contents.get("contentTitle")+"文章成功，积分加0","");
			}
			jsonMap.put("contentCategoryName",contents.get("categoryName"));// 文章分类名称
			jsonMap.put("contentTitle", contents.get("contentTitle"));// 文章标题
			jsonMap.put("author", contents.get("author"));// 作者
			jsonMap.put("provenance", contents.get("provenance"));// 出处
			jsonMap.put("contentNote", contents.get("contentNote"));// 内容(简介)
			jsonMap.put("introduction", contents.get("introduction"));// 介绍
			jsonMap.put("contentId", contents.get("uuid"));// 文章Uuid
			// 图片地址
			Map<String, Object> image = platformInfoSerivce.selectFileMapByRemotePaths((String) contents.get("image"));
			jsonMap.put("img", image.get("remotePaths"));
			jsonMap.put("createTime", contents.get("createTime"));
		}
				
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	/**
	 * 获取资讯的详细信息& 获取诊断标准的详细信息
	 * contentUuid：资讯id
	 */
	@RequestMapping(value = "/2.0/getContent", method = RequestMethod.GET)
	public ApiResult getContentByUuid_2(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentUuid") String contentUuid) {
		// 设置返回数据编码
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
//		boolean breakOut = false;// return 标志
	
		// 标志 以及必需要传的参数
		
		String doctorUuid = request.getParameter("doctorUuid");
		
/*		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,new String[] { "callback,true", "contentUuid,true" });
	
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}*/
		
//		Object callback = map.get("callback");
		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentUuid)) {
			return ApiResult.error(ApiCode.SERVER_ERROR, "文章id不能为空");
		}
		
		
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		// 设置返回信息
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> content = contentService.selectContent(contentUuid);
		// 判空
		if (content == null) {
			return ApiResult.error(ApiCode.SERVER_ERROR, "文章为空");
		}
		for (Map<String, Object> contents : content) {
			
			if(!StringUtil.isEmpty(doctorUuid)){
				vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 0, IntegralType.ADD_SHARE_BY_ARTICLE.getValue(),
						VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "分享"+contents.get("contentTitle")+"文章成功，积分加0","");
			}
			jsonMap.put("contentCategoryName",contents.get("categoryName"));// 文章分类名称
			jsonMap.put("contentTitle", contents.get("contentTitle"));// 文章标题
			jsonMap.put("author", contents.get("author"));// 作者
			jsonMap.put("provenance", contents.get("provenance"));// 出处
			jsonMap.put("contentNote", contents.get("contentNote"));// 内容(简介)
			jsonMap.put("introduction", contents.get("introduction"));// 介绍
			jsonMap.put("contentId", contents.get("uuid"));// 文章Uuid
			// 图片地址
			Map<String, Object> image = platformInfoSerivce.selectFileMapByRemotePaths((String) contents.get("image"));
			jsonMap.put("img", image.get("remotePaths"));
			jsonMap.put("createTime", contents.get("createTime"));
			
			relist.add(jsonMap);
		}
		
		return ApiResult.right(relist);
		
	}
	
}
