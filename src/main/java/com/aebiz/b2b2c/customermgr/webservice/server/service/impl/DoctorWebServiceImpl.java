package com.aebiz.b2b2c.customermgr.webservice.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.webservice.server.service.DoctorWebService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 医生
 * 
 * @author szr
 * 
 */
@WebService(endpointInterface = "com.aebiz.b2b2c.customermgr.webservice.server.service.DoctorWebService")
public class DoctorWebServiceImpl implements DoctorWebService {

	// 医生service
	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private ServicestaffinfoService servicestaffInfoService;

	/**
	 * 获取平台注册医生信息
	 * 
	 * @param jsonString
	 * @return
	 */
	@Override
	public String getDoctorInfoService(
			@WebParam(name = "jsonString") String jsonString) {
		// 定义返回数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 返回的jsonString
		JSONObject jsonObj = null;
		// 判断请求json参数是否为空
		if (StringUtil.isEmpty(jsonString)) {
			jsonMap.put("code", "2");
			jsonMap.put("message", "参数为空");
			jsonObj = (JSONObject) JSON.toJSON(jsonMap);
			return jsonObj.toString();
		}
		try {
			// 解密后的json串
			// String rejsonString = des.get3DESDecrypt(jsonString,
			// UtilHelper.token);
			// 把请求jsonString转化成json对象
			JSONObject jsonObject1 = JSON.parseObject(jsonString);
			// 根据签名sign获取到签名值
			String sign = jsonObject1.getString("sign");
			// 判断jsonString参数值是否为空
			if (StringUtil.isEmpty(sign)) {
				jsonMap.put("code", "1");
				jsonMap.put("message", "无效签名");
				jsonObj = (JSONObject) JSON.toJSON(jsonMap);
				return jsonObj.toString();
			}
			// 判断签名是否正确
			// if(!sign.equals(MD5Util.md5(UtilHelper.token))){
			// jsonMap.put("code", "1");
			// jsonMap.put("message", "签名不正确！");
			// jsonObj = (JSONObject) JSON.toJSON(jsonMap);
			// return des.get3DESEncrypt(jsonObj.toString(), UtilHelper.token);
			// }
			// 获取医生注册Id
			List<String> doctorIds = (List<String>) jsonObject1
					.get("doctorIds");
			// 判断题库种类编号是否为空
			if (doctorIds == null || doctorIds.size() == 0) {
				jsonMap.put("code", "8");
				jsonMap.put("message", "医生注册Id为空！");
				jsonObj = (JSONObject) JSON.toJSON(jsonMap);
				return jsonObj.toString();
			}

			// 调用service实现业务逻辑

			/** ———————————————————————————————————————————————————— **/
			List results = new ArrayList();
			for (String doctorId : doctorIds) {
				// 根据doctorId得到医生对象
				ServicestaffModel servicestaffModel = servicestaffService.getServicestaffModel(doctorId);
				ServicestaffinfoModel servicestaffInfoModel = null;
				if(servicestaffModel !=null){
					 servicestaffInfoModel = servicestaffInfoService
								.getServicestaffinfoModelByServicestaffUuid(servicestaffModel.getUuid());
				}
				// 判是否为空
				if (servicestaffModel == null || servicestaffInfoModel == null) {
					jsonMap.put("code", "8");
					jsonMap.put("message", "此医生id没有医生信息");
					jsonObj = (JSONObject) JSON.toJSON(jsonMap);
					return jsonObj.toString();
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				// 医生注册Id
				map.put("doctorId", doctorId);
				map.put("mobile", servicestaffModel.getMobile());
				//医生编号
				map.put("doctorNo", servicestaffModel.getDoctorNo());
				// 真实姓名
				map.put("realName", servicestaffModel.getRealName());
				// 医院
				map.put("hospital", servicestaffModel.getHospitalName());
				// 性别
				map.put("sex", servicestaffInfoModel.getSex());
				// 职称
				map.put("professional", servicestaffInfoModel.getProfessional());
				// 擅长领域
				map.put("interest", servicestaffInfoModel.getTerritory());
				// 自我介绍
				map.put("introduce", servicestaffInfoModel.getSynopsis());

				results.add(map);
			}
			jsonMap.put("results", results);

			/** ———————————————————————————————————————————————————— **/
			// 成功参数
			jsonMap.put("code", "0");
			jsonMap.put("message", "成功");
			jsonObj = (JSONObject) JSON.toJSON(jsonMap);

		} catch (Exception e) {
			e.printStackTrace();
			// 报错捕获
			jsonMap.put("code", "1");
			jsonMap.put("message", "系统出错");
			jsonObj = (JSONObject) JSON.toJSON(jsonMap);

		}
		System.out.println("return:jsonString ======================="
				+ jsonObj.toString());
		return jsonObj.toString();
	}
}
