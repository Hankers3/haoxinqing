package com.aebiz.b2b2c.customermgr.webservice.client.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.customermgr.webservice.client.service.UserCenterClientService;
import com.aebiz.b2b2c.customermgr.webservice.util.ClientHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserCenterClientServiceImpl implements UserCenterClientService {
	public static String url = "http://localhost:8090/services/api/getManagerService?wsdl";

	@Override
	public String getUserInfo(String sign, String loginName, String pwd) {
		String methodName = "getUserInfo";
		JSONObject jsonObj = null;
		String returnStr = "";
		StringBuffer logStr = new StringBuffer("当前时间:" + DateFormatHelper.getNowTimeStr());
		logStr.append(" method=" + methodName + "\n" + "start-------------------------\n");
		// 请求的json

		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("sign", sign);
		paramMap.put("requestTime", DateFormatHelper.getNowTimeStr());
		paramMap.put("loginName", loginName);
		paramMap.put("pwd", pwd);

		jsonObj = (JSONObject) JSON.toJSON(paramMap);

		logStr.append("请求参数:\n" + jsonObj.toString() + "\n");
		try {

			returnStr = ClientHelper.invoking(methodName, jsonObj.toString(), url);

			logStr.append("返回信息:\n" + returnStr.toString() + "\n");

		} catch (Exception e) {
			Map<Object, Object> paramMap1 = new HashMap<Object, Object>();
			paramMap1.put("code", "1");
			paramMap1.put("message", e.getMessage());

			jsonObj = (JSONObject) JSON.toJSON(paramMap1);
			logStr.append("错误信息：").append(jsonObj.toString()).append(";\n");
		}
		logStr.append("-------------------------end \n");

		System.out.println("==========" + returnStr.toString());
		return returnStr.toString();
	}

	@Override
	public String getManagerInfo(String sign, String loginName, String pwd) {
		String methodName = "getManagerInfo";
		JSONObject jsonObj = null;
		String returnStr = "";
		StringBuffer logStr = new StringBuffer("当前时间:" + DateFormatHelper.getNowTimeStr());
		logStr.append(" method=" + methodName + "\n" + "start-------------------------\n");
		// 请求的json

		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("sign", sign);
		paramMap.put("requestTime", DateFormatHelper.getNowTimeStr());
		paramMap.put("loginName", loginName);
		paramMap.put("pwd", pwd);

		jsonObj = (JSONObject) JSON.toJSON(paramMap);

		logStr.append("请求参数:\n" + jsonObj.toString() + "\n");
		try {
			returnStr = ClientHelper.invoking(methodName, jsonObj.toString(), url);

			logStr.append("返回信息:\n" + returnStr.toString() + "\n");

		} catch (Exception e) {
			Map<Object, Object> paramMap1 = new HashMap<Object, Object>();
			paramMap1.put("code", "1");
			paramMap1.put("message", e.getMessage());
			System.out.println(e);
			jsonObj = (JSONObject) JSON.toJSON(paramMap1);
			logStr.append("错误信息：").append(jsonObj.toString()).append(";\n");
		}
		logStr.append("-------------------------end \n");

		System.out.println("==========" + returnStr.toString());
		return returnStr.toString();
	}

	public String getQuestionService(String sign, String typeId) {
		String methodName = "getQuestionList";
		JSONObject jsonObj = null;
		String returnStr = "";
		StringBuffer logStr = new StringBuffer("当前时间:" + DateFormatHelper.getNowTimeStr());
		logStr.append(" method=" + methodName + "\n" + "start-------------------------\n");
		// 请求的json

		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("sign", sign);
		paramMap.put("requestTime", DateFormatHelper.getNowTimeStr());
		paramMap.put("typeId", typeId);

		jsonObj = (JSONObject) JSON.toJSON(paramMap);

		logStr.append("请求参数:\n" + jsonObj.toString() + "\n");
		try {
			returnStr = ClientHelper.invoking(methodName, jsonObj.toString(), url);

			logStr.append("返回信息:\n" + returnStr.toString() + "\n");

		} catch (Exception e) {
			Map<Object, Object> paramMap1 = new HashMap<Object, Object>();
			paramMap1.put("code", "1");
			paramMap1.put("message", e.getMessage());
			System.out.println(e);
			jsonObj = (JSONObject) JSON.toJSON(paramMap1);
			logStr.append("错误信息：").append(jsonObj.toString()).append(";\n");
		}
		logStr.append("-------------------------end \n");

		System.out.println("==========" + returnStr.toString());
		return returnStr.toString();
	}

	public static void main(String[] args) {
		UserCenterClientServiceImpl impl = new UserCenterClientServiceImpl();
		impl.getManagerInfo("====", "admin", "111111");
	}
}
