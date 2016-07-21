package com.aebiz.b2b2c.customermgr.mobile.web.customer.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.platformcommunication.service.PlatformCommunicationService;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.websiteoperation.favorite.service.FavoriteService;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;

/**
 * 患者端心灵氧吧接口控制类 12/21
 * 
 * @author szr
 * 
 */
@Controller
@RequestMapping("/app/customer/patientOfHeart")
public class AppHeartController extends AppBaseController {
	/**
	 * 构造方法
	 */
	public AppHeartController() {
		super("", "", AppHeartController.class);
	}

	@Autowired
	ServicestaffService servicestaffService;
	@Autowired
	PlatFormInfoService platFormInfoService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private ContentService contentService;

	@Autowired
	private PlatformCommunicationService platformCommunicationService;

	/**
	 * 获取心灵自助资讯列表</br>
	 * 通过患者id,文章分类id 文章分类为固定的G 12/21
	 * 
	 * @param request
	 * @param response
	 * @param contentCategoryUuid
	 * @param customerUuid
	 * @return
	 */
	
	@RequestMapping(value = "/getHeartContextList", method = RequestMethod.GET)
	public String getHeartContextList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentCategoryUuid") String contentCategoryUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");
		String customerUuid = request.getParameter("customerUuid");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "分类id不能为空"), callback);
			return null;
		}

		// 根据分类id获得文章列表 内容分类uuid
		List<ContentModel> contentModelList = contentService.getByContentCategoryUuid(contentCategoryUuid);
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (contentModelList != null && contentModelList.size() > 0) {
			for (ContentModel cm : contentModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 文章图片
				save.put("img", cm.getImgUrl());
				// 文章标题
				save.put("contentTitle", cm.getContentTitle());
				// 推荐时间
				save.put("createTime", cm.getCreateTime());
				// 作者
				save.put("author", cm.getAuthor());
				// 出处
				save.put("provenance", cm.getProvenance());
				// 文章Uuid
				String contextUuid = cm.getUuid();
				save.put("contextUuid", contextUuid);
				// 根据文章id得到该文章的关注人数 1表示文章
				int foucsNum = 0;
				foucsNum = favoriteService.getNumByContentUuid(contextUuid, "1");
				// 关注人数
				save.put("foucsNum", foucsNum);
				String favoriteUuid = "";
				// 关注状态 根据患者id和文章id返回收藏表uuid
				if (!StringUtil.isEmpty(customerUuid)) {
					favoriteUuid = favoriteService.getUuidByCustomerUuidAndContextUuid(customerUuid, contextUuid);
				}
				if (StringUtil.isEmpty(favoriteUuid)) {
					// 为空表示未关注 0表示未关注
					save.put("foucsState", "0");
					// 关注Uuid
					save.put("favoriteUuid", "");
				} else {
					// 不为空表示已关注，并返回favoriteUuid以便删除使用 1表示已关注
					save.put("foucsState", "1");
					// 关注Uuid
					save.put("favoriteUuid", favoriteUuid);
				}
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 心灵氧吧在线视频 </br>
	 * 查出所有视频 没有热不热播，没有模糊查询 12/21
	 */
	@RequestMapping(value = "/getVideos", method = RequestMethod.POST)
	public String getVideos(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		Object callback = map.get("callback");

		if (StringUtil.isEmpty(customerUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患者主键为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 根据名称(名称填“”)得到视频列表
		List<PlatFormInfoModel> platFormInfoList = platFormInfoService.getByName("", "0");//患者端
		if (platFormInfoList == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "无视频信息"), callback);
			return null;
		}
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (platFormInfoList != null && platFormInfoList.size() > 0) {
			for (PlatFormInfoModel platFormInfoModel : platFormInfoList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 得到视频主键
				String videoUuid = platFormInfoModel.getUuid();
				save.put("uuid", videoUuid);
				// 存入视频标题
				save.put("title", platFormInfoModel.getVideoModel());
				// 存入视频简介
				save.put("videoIntroduction", platFormInfoModel.getVideoIntroduction());
				// 存入发布时间
				save.put("createTime", platFormInfoModel.getCreateTime());
				// 存入发布者
				save.put("userName", platFormInfoModel.getUserName());
				// 收藏视频的人数
				save.put("collectNum", favoriteService.getNumByVedioUuid(videoUuid));
				// 简介图片地址
				save.put("img", platFormInfoModel.getImageUrl());
				// 正在直播的状态 类型1代表是预告，否则的话是正在直播
				save.put("videoType", platFormInfoModel.getType());
				// 该患者是否收藏该视频 sc 0:未收藏 1：收藏
				int sc = favoriteService.getcolVideoByDoctorUuidAndVideoUuid(customerUuid, videoUuid);
				save.put("sc", sc);
				String favoriteUuid = favoriteService.getUuidByCustomerUuidAndVideoUuid(customerUuid, videoUuid);
				// 存入收藏标题
				save.put("favoriteUuid", favoriteUuid);

				relist.add(save);
			}
		}
		// 消息字段
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 添加收藏 2016/1/7
	 * 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @param newsid
	 * @return
	 */
	@RequestMapping(value = "/addFavorite", method = RequestMethod.GET)
	public String addFavorite(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("newsid") String newsid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerUuid,true", "newsid,true", "callback,false" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取收藏类型 收藏类型 1：资讯 2：视频
		String favoriteType = request.getParameter("favoriteType");
		// 获取回调的名
		Object callback = map.get("callback");

		/************************ 添加收藏信息 *************************/
		FavoriteModel fm = new FavoriteModel();
		if (("2").equals(favoriteType) && !StringUtil.isEmpty(newsid) && !StringUtil.isEmpty(customerUuid)) {
			int num = favoriteService.getcolVideoByDoctorUuidAndContenUuid(customerUuid, newsid);
			if (num == 0) {
				fm.setUserId(customerUuid);
				fm.setVideoUuid(newsid);
				fm.setState("1");
				fm.setFavoriteTime(DateFormatHelper.getNowTimeStr());
				fm.setUserType(FavoriteModel.USER_CUSTOMER);
				fm.setFavoriteType("2");
				favoriteService.create(fm);
				jsonMap.put("favoriteUuid", fm.getUuid());
			}
		} else if (!StringUtil.isEmpty(newsid) && !StringUtil.isEmpty(customerUuid)) {
			int num = favoriteService.getStoreTypeByDoctorUuidAndContenUuid(customerUuid, newsid);
			if (num == 0) {
				fm.setUserId(customerUuid);
				fm.setContentUuid(newsid);
				fm.setState("1");
				fm.setFavoriteTime(DateFormatHelper.getNowTimeStr());
				fm.setUserType(FavoriteModel.USER_CUSTOMER);
				fm.setFavoriteType("1");
				favoriteService.create(fm);
				jsonMap.put("favoriteUuid", fm.getUuid());
			}
		}
		jsonMap.put("favoriteUuid", fm.getUuid());
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 更新视频收藏操作
	 * 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @param newsid
	 * @return
	 */
	@RequestMapping(value = "/updateFavorite", method = RequestMethod.GET)
	public String updateFavorite(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("videoUuid") String videoUuid,
			@RequestParam("state") String state) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "customerUuid,true", "videoUuid,true", "state,true", "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		if (!(state.equals("1") || state.equals("0"))) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "状态只能为1或0(1表示收藏 0表示取消收藏)"),
					callback);
			return null;
		}

		/************************ 更新收藏状态 *************************/
		// state=1表示收藏 0表示取消收藏
		if (state.equals("1")) {

			FavoriteModel fm = new FavoriteModel();
			if (!StringUtil.isEmpty(videoUuid) && !StringUtil.isEmpty(customerUuid)) {
				fm.setUserId(customerUuid);
				// 设置为患者类型
				fm.setUserType("2");
				fm.setVideoUuid(videoUuid);
				favoriteService.create(fm);
			}
		} else if (state.equals("0")) {
			// 删除
			if (!(StringUtil.isEmpty(customerUuid) && StringUtil.isEmpty(videoUuid))) {
				favoriteService.deleteByDoctorUuidAndVideouuid(customerUuid, videoUuid);
			}
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
}
