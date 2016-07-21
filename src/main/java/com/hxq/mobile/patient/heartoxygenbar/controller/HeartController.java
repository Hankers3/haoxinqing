package com.hxq.mobile.patient.heartoxygenbar.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.hxq.mobile.doctor.content.service.FavoriteService;
import com.hxq.mobile.entity.common.Favorite;
import com.hxq.mobile.patient.content.service.ContentService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * @author 作者 E-mail: liuyang
 * @date 创建时间：2016年6月17日 上午11:54:13
 * @version 2.0
 * @parameter
 * @since
 * @return
 */
@Controller("com.hxq.mobile.patient.heartoxygenbar.controller.HeartController")
public class HeartController {
	Logger log = LoggerFactory.getLogger(HeartController.class);

	@Resource(name = "com.hxq.mobile.patient.content.service.ContentService")
	private ContentService contentService;

	/* 图片service */
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgService;

	@Autowired
	private FileService fileService;

	@Resource(name = "com.hxq.mobile.doctor.content.service.FavoriteService")
	private FavoriteService favoriteService;

	/**
	 * 获取心灵自助资讯列表
	 * 通过患者id,文章分类id 文章分类为固定的G 
	 * @param request
	 * @param response
	 * @param contentCategoryUuid
	 * @param customerUuid
	 * @return
	 */
	@RequestMapping(value = "/app/customer/patientOfHeart/2.0/getHeartContentList", method = RequestMethod.GET)
	public @ResponseBody ApiResult getHeartContentList(HttpServletRequest request,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("contentCategoryUuid") String contentCategoryUuid,
			@RequestParam(value = "pageCount", required = false) Integer pageCount,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentCategoryUuid) || StringUtil.isEmpty(customerUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "数据id不能为空!");
		}

		Image4App[] imgUrls = null;
		// 根据分类id获得文章列表 内容分类uuid
		Object[] List = contentService.selectAllHeartList(contentCategoryUuid, pageCount, pageNo);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> contentModelList = (List<Map<String, Object>>) List[0];
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (contentModelList != null && contentModelList.size() > 0) {
			for (Map<String, Object> cm : contentModelList) {
				Map<String, Object> save = cm;

				// 文章图片
				if (!ObjectUtils.isEmpty(cm.get("image"))) {
					imgUrls = CompatibleTools.getImages(imgService, fileService, (String) cm.get("image"));
					if (!ObjectUtils.isEmpty(imgUrls)) {
						save.put("img", imgUrls[0]);
					}
				}
				// 文章Uuid
				String contentUuid = (String) cm.get("uuid");
				save.put("contentUuid", contentUuid);
				// 根据文章id得到该文章的关注人数 1表示文章
				int foucsNum = 0;
				foucsNum = favoriteService.getNumByContentUuid(contentUuid, "1");
				save.put("foucsNum", foucsNum);// 关注人数
				String favoriteUuid = "";
				// 关注状态 根据患者id和文章id返回收藏表uuid
				if (!StringUtil.isEmpty(customerUuid)) {
					favoriteUuid = favoriteService.selectUuidByCustomerUuidAndContextUuid(customerUuid, contentUuid);
				}
				if (StringUtil.isEmpty(favoriteUuid)) {
					
					save.put("foucsState", "0");// 为空表示未关注 0表示未关注
					
					save.put("favoriteUuid", "");// 关注Uuid
				} else {
					// 不为空表示已关注，并返回favoriteUuid以便删除使用 1表示已关注
					save.put("foucsState", "1");
					
					save.put("favoriteUuid", favoriteUuid);// 关注Uuid
				}
				relist.add(save);
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("pageNo", List[1]);//当前页号
		jsonMap.put("totalPage", List[2]);//总页数
		jsonMap.put("totalRows", List[3]);//总记录数
		jsonMap.put("reList", relist);
		return ApiResult.right(jsonMap);
	}

	/**
	 * 获取心灵自助资讯列表
	 * 通过患者id,文章分类id 文章分类为固定的G 12/21
	 * @param request
	 * @param response
	 * @param contentCategoryUuid
	 * @param customerUuid
	 * @return
	 */

	@RequestMapping(value = "/app/customer/patientOfHeart/2.0/getHeartContent", method = RequestMethod.GET)
	public @ResponseBody ApiResult getHeartContent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentUuid") String contentUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String customerUuid = request.getParameter("customerUuid");

		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "资讯id不能为空!");
		}
		Image4App[] imgUrls = null;
		// 根据分类id获得文章列表 内容分类uuid
		Map<String, Object> contentMap = contentService.selectContentByUuid(contentUuid);
		// 定义返回List
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		if (contentMap != null) {
			Map<String, Object> save = contentMap;

			// 文章图片
			if (!ObjectUtils.isEmpty(contentMap.get("image"))) {
				imgUrls = CompatibleTools.getImages(imgService, fileService, (String) contentMap.get("image"));
				if (!ObjectUtils.isEmpty(imgUrls)) {
					save.put("img", imgUrls[0]);
				}
			}

			// 文章Uuid
			save.put("contentUuid", contentUuid);
			// 根据文章id得到该文章的关注人数 1表示文章
			int foucsNum = 0;
			foucsNum = favoriteService.getNumByContentUuid(contentUuid, "1");
			// 关注人数
			save.put("foucsNum", foucsNum);
			String favoriteUuid = "";
			// 关注状态 根据患者id和文章id返回收藏表uuid
			if (!StringUtil.isEmpty(customerUuid)) {
				favoriteUuid = favoriteService.selectUuidByCustomerUuidAndContextUuid(customerUuid, contentUuid);
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

		return ApiResult.right(relist);
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
	@RequestMapping(value = "/app/customer/patientOfHeart/2.0/addFavorite", method = RequestMethod.GET)
	public @ResponseBody ApiResult addFavorite(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid, @RequestParam("newsid") String newsid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取收藏类型 收藏类型 1：资讯 2：视频
		String favoriteType = request.getParameter("favoriteType");
		if (StringUtil.isEmpty(favoriteType)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "收藏类型不能为空");
		}

		/************************ 添加收藏信息 *************************/
		Favorite fm = new Favorite();
		if (!StringUtil.isEmpty(newsid) && !StringUtil.isEmpty(customerUuid)) {
			int num = favoriteService.selectStoreTypeByDoctorUuidAndType(customerUuid, newsid, favoriteType);
			if (num == 0) {
				fm.setUuid(IdentityHelper.uuid2());
				fm.setUserId(customerUuid);
				if(favoriteType.equals("2")){
					fm.setVideoUuid(newsid);
				}else{
					fm.setContentUuid(newsid);
				}
				fm.setState("1");
				fm.setFavoriteTime(DateFormatHelper.getNowTimeStr());
				fm.setUserType(FavoriteModel.USER_CUSTOMER);
				fm.setFavoriteType(favoriteType);
				try {
					favoriteService.insert(fm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return ApiResult.right("操作成功");
	}

}
