package com.hxq.mobile.doctor.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce;
import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.hxq.mobile.entity.common.Favorite;
import com.hxq.mobile.entity.common.PlatFormInfo;
import com.hxq.mobile.entity.visit.Content;
import com.hxq.mobile.util.CompatibleTools;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.doctor.common.controller.FavoriteInformationController")
public class FavoriteInformationController {
	Logger log = LoggerFactory.getLogger(FavoriteInformationController.class);
	
	@Resource(name = "com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce")
	private FavoriteInformationSerivce favoriteInformationSerivce;

	@Resource(name = "com.hxq.mobile.doctor.common.service.PlatformInfoSerivce")
	private PlatformInfoSerivce platformInfoSerivce;
	
	/**
	 * 获取收藏列表信息
	 *
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/app/pub/doctor/1.0/getFavoriteModelList", method = RequestMethod.GET)
	public String getFavoriteModelLists(HttpServletRequest request, HttpServletResponse response,
									   @RequestParam("doctorid") String doctorid,
									   @RequestParam(value ="pageCount",required = false) Integer pageCount,
									   @RequestParam(value = "pageNo",required = false) Integer pageNo) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,new String[] { "callback,true", "doctorid,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断用户是否登陆
		if (ObjectUtils.isEmpty(doctorid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "用户未登录"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获取用户收藏列表
		Object[] result = favoriteInformationSerivce.selectFavoriteInformationListById(doctorid,pageCount, pageNo);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> favoriteModelList = (List<Map<String, Object>>)result[0];
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		Map<String, Object> favoriteMap = null;
		Map<String, Object> platformMap = null;
		String contentUuid = null;// 内容uuid
		String videoUuid = null;// 视频uuid
		String favoriteUuid = null;// 收藏的id
		Content cm;//内容资讯
		PlatFormInfo pm;
		int ContentNum = 0;
		int VedNum = 0;
		int CoMmonCount = 0;
		try {
			if (favoriteModelList != null && favoriteModelList.size() > 0) {
				for (Map<String, Object> fml : favoriteModelList) {
					// 组装返回到客户端的医生信息
					favoriteMap = new HashMap<>();
					if (fml != null) {
						contentUuid = (String) fml.get("contentUuid");
						videoUuid = (String) fml.get("videoUuid");
						favoriteUuid = (String) fml.get("uuid");
					}
					
					if (!ObjectUtils.isEmpty(contentUuid)) {
							cm = (Content)favoriteInformationSerivce.select(new Content(contentUuid));
							if (cm != null) {
							platformMap = new HashMap<>();
							platformMap = platformInfoSerivce.selectFileMapByRemotePaths(cm.getImage());  
							if(platformMap != null){
								favoriteMap.put("imaUrl", platformMap.get("remotePaths"));
							}else{
								favoriteMap.put("imaUrl", "");
							}
							ContentNum = favoriteInformationSerivce.selectFavoriteNumByContentUuid(contentUuid);
							favoriteMap.put("ContentNum", ContentNum);// 收藏文章人数
							VedNum = favoriteInformationSerivce.selectFavoriteInformationNumByVedioUuid(videoUuid,"1");
							favoriteMap.put("collectNum", VedNum);// 收藏视频人数
							CoMmonCount = platformInfoSerivce.selectCommunicationCount(videoUuid);
							favoriteMap.put("CoMmonCount", CoMmonCount);// 评论视频人数
							favoriteMap.put("contentUuid", contentUuid);
							favoriteMap.put("contentTitle", cm.getContentTitle());
							favoriteMap.put("createTime", cm.getOpeTime());
							favoriteMap.put("favoriteUuid", favoriteUuid);
							favoriteMap.put("favoriteType", "1");
							relist.add(favoriteMap);
						}
					}
					if (!ObjectUtils.isEmpty(videoUuid)) {
						pm = (PlatFormInfo)favoriteInformationSerivce.select(new PlatFormInfo(videoUuid));
						if (pm != null) {
							platformMap = new HashMap<>();
							platformMap = platformInfoSerivce.selectFileMapByRemotePaths(pm.getImage());  
							if(platformMap != null){
								favoriteMap.put("imaUrl", platformMap.get("remotePaths"));
							}else{
								favoriteMap.put("imaUrl", "");
							}
							VedNum = favoriteInformationSerivce.selectFavoriteInformationNumByVedioUuid(videoUuid,"1");
							favoriteMap.put("collectNum", VedNum);// 收藏人数
							CoMmonCount = platformInfoSerivce.selectCommunicationCount(videoUuid);
							favoriteMap.put("CoMmonCount", CoMmonCount);// 评论人数
							favoriteMap.put("contentUuid", videoUuid);
							favoriteMap.put("favoriteType", "2");
							favoriteMap.put("contentTitle", pm.getVideoModel());
							favoriteMap.put("videoIntroduction", pm.getVideoIntroduction());
							favoriteMap.put("createTime", pm.getCreateTime());
							favoriteMap.put("favoriteUuid", favoriteUuid);
							relist.add(favoriteMap);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("pageNo", result[1]);//当前页号
		jsonMap.put("totalPage", result[2]);//总页数
		jsonMap.put("totalRows", result[3]);//总记录数
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加收藏
	 *
	 * @param request
	 * @param response
	 * @param doctorid
	 * @param newsid
	 * @return
	 * @author
	 */
	@RequestMapping(value = "/app/pub/doctor/1.0/addFavorite", method = RequestMethod.GET)
	public String addFavorites(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("doctorid") String doctorid, @RequestParam("newsid") String videoUuid, @RequestParam("favoriteType") String favoriteType) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,
				new String[]{"doctorid,true", "newsid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 添加收藏信息 *************************/
		Favorite fm = null;
		try {
			// 获取收藏类型 2代表是视频的类型(视频的)
			if (("2").equals(favoriteType) && !ObjectUtils.isEmpty(videoUuid) && !ObjectUtils.isEmpty(doctorid)) {
				int num = favoriteInformationSerivce.selectCollectInformationByCount(doctorid, videoUuid);
				if (num == 0) {
					fm = new Favorite(IdentityHelper.uuid2());
					fm.setUserId(doctorid);
					fm.setVideoUuid(videoUuid);
					fm.setState("1");
					fm.setFavoriteTime(DateFormatHelper.getNowTimeStr());
					fm.setUserType(FavoriteModel.USER_DOCTOR);
					fm.setFavoriteType("2");
					favoriteInformationSerivce.insert(fm);
					jsonMap.put("favoriteUuid", fm.getUuid());
				}
			} else if (!ObjectUtils.isEmpty(videoUuid) && !ObjectUtils.isEmpty(doctorid)) {
				int nums = favoriteInformationSerivce.selectStateCollectInformationByCount(doctorid, videoUuid);
				if (nums == 0) {
					fm = new Favorite(IdentityHelper.uuid2());
					fm.setUserId(doctorid);
					fm.setContentUuid(videoUuid);
					fm.setState("1");
					fm.setFavoriteTime(DateFormatHelper.getNowTimeStr());
					fm.setUserType(FavoriteModel.USER_DOCTOR);
					fm.setFavoriteType("1");
					favoriteInformationSerivce.insert(fm);
					jsonMap.put("favoriteUuid", fm.getUuid());
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		jsonMap.put("favoriteUuid", fm.getUuid());
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
	
	/**
	 * 删除收藏
	 *
	 * @param request
	 * @param response
	 * @param favoriteUuid
	 * @return
	 * @author xp
	 */
	@RequestMapping(value = "/app/pub/doctor/1.0/delFavorite", method = RequestMethod.GET)
	public String delFavorites(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam("favoriteUuid") String favoriteUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut,
				new String[]{"favoriteUuid,true", "callback,false"});
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 删除收藏信息 *************************/
		if (ObjectUtils.isEmpty(favoriteUuid)) {
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "收藏Id为空"), callback);
			return null;
		}

		Favorite fm = new Favorite();
		fm.setUuid(favoriteUuid);
		try {
			favoriteInformationSerivce.delete(fm);
		} catch (Exception e) {
			log.error("",e);
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
}