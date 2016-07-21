package com.aebiz.b2b2c.customermgr.webservice.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.webservice.server.service.TestCategoryWebService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.service.QuestionsService;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.service.QuizResultService;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 测试题
 * 
 * @author szr
 * 
 */
@WebService(endpointInterface = "com.aebiz.b2b2c.customermgr.webservice.server.service.TestCategoryWebService")
public class TestCategoryWebServiceImpl implements TestCategoryWebService {

	// 注入测试题库种类service
	@Autowired
	private QuizCategoryService quizCategoryService;
	// 测试题service
	@Autowired
	private QuizResultService quizResultService;
	// 题service
	@Autowired
	private QuestionsService questionsService;
	// 医生service
	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private ServicestaffinfoService servicestaffInfoService;

	/**
	 * 获取平台所有的测试题库种类
	 * 
	 * @param jsonString
	 * @return
	 */
	@Override
	public String getQuestionTypeList(
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
			// 返回list
			List results = new ArrayList();

			// 获取到平台所有的测试题库种类
			List<QuizCategoryModel> quizCategoryList = quizCategoryService
					.getAll();

			// 判断参数是否为空
			if (quizCategoryList == null || quizCategoryList.size() < 0) {
				jsonMap.put("code", "8");
				jsonMap.put("message", "无测试题库种类！");
				jsonObj = (JSONObject) JSON.toJSON(jsonMap);
				return jsonObj.toString();
			}

			// 循环分类得到分类里的测试题
			for (QuizCategoryModel quizCategoryModel : quizCategoryList) {
				// 定义返回map
				Map<String, Object> map = new HashMap<String, Object>();
				// 分类id
				String quizCategoryUuid = quizCategoryModel.getUuid();
				// 题库类编号
				map.put("typeId", quizCategoryUuid);
				// 题库类名
				map.put("typeName", quizCategoryModel.getCategoryName());
				// 根据分类id得到分类List
				List<QuizResultModel> quizResultList = quizResultService
						.getListByQuizCategoryUuid(quizCategoryUuid);
				// 返回list
				List scale = new ArrayList();
				// 循环放入值
				for (QuizResultModel quizResultModel : quizResultList) {
					Map<String, Object> mao = new HashMap<String, Object>();
					// 分数开始
					mao.put("scoreStart", quizResultModel.getStartScore());
					// 分数结束
					mao.put("scoreEnd", quizResultModel.getEndScore());
					// 描述
					mao.put("note", quizResultModel.getResult());
					scale.add(mao);
				}
				// 选项
				map.put("scale", scale);
				// 放入relist中
				results.add(map);
			}

			// 成功参数
			jsonMap.put("results", results);
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

	/**
	 * 根据题库种类来获取改种类下所有测试题目
	 * 
	 * @param jsonString
	 * @return
	 */
	@Override
	public String getQuestionList(
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
			// 获取题库种类编号
			String typeId = (String) jsonObject1.getString("typeId");
			String status = (String) jsonObject1.getString("status");
			// 判断题库种类编号是否为空
			if (StringUtil.isEmpty(typeId)) {
				jsonMap.put("code", "8");
				jsonMap.put("message", "无题库种类编号！");
				jsonObj = (JSONObject) JSON.toJSON(jsonMap);
				return jsonObj.toString();
			}
			if (StringUtil.isEmpty(status)) {
				jsonMap.put("code", "8");
				jsonMap.put("message", "状态为空！");
				jsonObj = (JSONObject) JSON.toJSON(jsonMap);
				return jsonObj.toString();
			}

			// 调用service实现业务逻辑
			List<QuestionsModel> questions = questionsService
					.getQuestionsByQuizCategoryUuid(typeId,status);
			if (questions == null || questions.size() == 0) {
				jsonMap.put("code", "8");
				jsonMap.put("message", "该分类下无测试题！");
				jsonObj = (JSONObject) JSON.toJSON(jsonMap);
				return jsonObj.toString();
			}

			/** ———————————————————————————————————————————————————— **/
			List results = new ArrayList();
			for (QuestionsModel question : questions) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 测试题名
				map.put("questionTitle", question.getTitle());
				map.put("questionId", question.getUuid());
				// 测试题选项
				List<OptionsModel> options = question.getOptionsModels();
				for (OptionsModel optionsModel : options) {
					// 选项名
					map.put("option", optionsModel.getOptionTitle());
					// 选项得分
					map.put("score", optionsModel.getOptionValue());
				}
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
