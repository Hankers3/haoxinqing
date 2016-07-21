package com.hxq.mobile.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.hxq.mobile.support.service.ImgUploadService;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

public class CompatibleTools {

	/**
	 * 获取上传图像，兼容旧版本(全网)
	 * @param ids
	 * @return
	 */
	public static Image4App[] getImages(ImgUploadService imgService, FileService fileService, String... ids) {
		if(imgService==null || fileService==null || ids==null || ids.length<1) return null;

		String str = null;
		String url = null;
		FileModel fm = null;
		Image4App[] imgs = imgService.selectImagesByIds(ids);

		for(Image4App img : imgs) {
			if(ObjectUtils.isEmpty(img) ||
					(ObjectUtils.isEmpty(img.getLarge()) && ObjectUtils.isEmpty(img.getSmall()))) continue;

			str = StringUtils.trimToEmpty(img.getLarge()).toLowerCase();
			if(str.length() > 0 && !str.startsWith("http://") && !str.startsWith("https://")) {
				fm = fileService.getOneFileModel(str);
				url = fm != null ? fm.getRemotePaths() : str;
				if(!ObjectUtils.isEmpty(url)) img.setLarge(url);
			}
			str = StringUtils.trimToEmpty(img.getSmall()).toLowerCase();
			if(str.length() > 0 && !str.startsWith("http://") && !str.startsWith("https://")) {
				fm = fileService.getOneFileModel(str);
				url = fm != null ? fm.getRemotePaths() : str;
				if(!ObjectUtils.isEmpty(url)) img.setLarge(url);
			}
		}
		return imgs;
	}

	/**
	 * 兼容全网接口返回格式
	 * @param response
	 * @param message 为空时返回成功，否则返回错误
	 */
	public static void returnJsonByOldVersion(HttpServletResponse response, String message) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(ObjectUtils.isEmpty(message)) {
			HttpServletUtils.outJsonCallBack(response,
					CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE), null);
		} else {
			HttpServletUtils.outJsonCallBack(response,
					CompatibleTools.getFailJsonMap(SysConst.FAIL, message), null);
		}
	}
	/**
	 * 返回错误信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public static Map<Object,Object> getFailJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}
	
	/**
	 * 返回成功信息调用的组装jsonMap
	 * @param code
	 * @param message
	 * @return
	 */
	public static Map<Object,Object> getSucJsonMap(String code,String message){
		Map<Object,Object> jsonMap = new HashMap<Object,Object>();
		Map<Object,Object> runMap = new HashMap<Object,Object>();
		runMap.put("success", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}

	/**
	 * 获取参数值并且进行非空判断
	 * @param request
	 * @param response
	 * @param breakOut 标志位
	 * @param params 传入参数，为数组格式
	 */
	public static Map<String,String> getParam(HttpServletRequest request,HttpServletResponse response,boolean breakOut,String[] params){
		Map<String,String> paramMap = new HashMap<String,String>();
		for(int i=0;i<params.length;i++){
			String paramName = params[i].split(",")[0];
			String temp = paramName;//暂时存储参数名
			String isCheck = params[i].split(",")[1];
			paramName = request.getParameter(paramName);
			paramMap.put(temp, paramName);
			if("true".equals(isCheck) && StringUtil.isEmpty(paramName)){
				HttpServletUtils.outJson(response, getFailJsonMap(SysConst.FAIL, temp+" is null"));
				breakOut = true;
			}
			paramMap.put("breakOut", String.valueOf(breakOut));
		}
		return paramMap;
	}
}
