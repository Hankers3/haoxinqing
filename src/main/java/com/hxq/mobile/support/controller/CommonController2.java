package com.hxq.mobile.support.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.hxq.mobile.entity.common.ImgUploadResponse;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.StringUtils;

@Controller
public class CommonController2 {
	Logger log = LoggerFactory.getLogger(CommonController2.class);

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	/**
	 * 上传图片
	 */
	@RequestMapping(value = "/app/support/common/1.0/uploadimg")
	public @ResponseBody ApiResult uploadFile(NativeWebRequest request,
			@RequestParam(value = "thumbnail", required = false) String thumbnail,
			@RequestParam(value = "files", required = false) MultipartFile[] files) {
		ImgUploadResponse result = null;
		boolean needThumbnail = StringUtils.trimToEmpty(thumbnail).toLowerCase().contains("true");
		try {
			result = imgUploadService.updateForUpload(needThumbnail, files);
			return result != null ? ApiResult.right(result.getFiles())
					: ApiResult.error(ApiCode.BAD_REQUEST,"");
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, "上传图片出错！");
		}
	}
}
