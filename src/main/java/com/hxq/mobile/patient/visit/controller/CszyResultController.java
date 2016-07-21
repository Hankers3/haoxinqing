package com.hxq.mobile.patient.visit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.hxq.mobile.entity.weixin.ProfessionSubject;
import com.hxq.mobile.patient.visit.service.CsResultService;
import com.hxq.mobile.patient.visit.service.ProfessionGatherService;
import com.hxq.mobile.patient.visit.service.ProfessionSubjectService;
import com.hxq.mobile.patient.visit.service.VisitPreceptService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 *自评医评 
 */
@Controller
public class CszyResultController {
	Logger log = LoggerFactory.getLogger(DoctorsAdviceController2.class);
	
	@Resource(name="com.hxq.mobile.patient.visit.service.ProfessionGatherService")
	private ProfessionGatherService professionGatherService;
	
	/*自评结果*/
	@Resource(name = "com.hxq.mobile.patient.visit.service.CsResultService")
	private CsResultService csResultService;
	
	/*随访方案表*/
	@Resource(name = "com.hxq.mobile.patient.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;
	
	@Resource(name = "com.hxq.mobile.patient.visit.service.ProfessionSubjectService")
	private ProfessionSubjectService professionSubjectService;
	
	/**********************随访医生-随访表单-量表评测保存**************************/
	@RequestMapping(value = "/mobile/patient/visit/2.0/saveAnswer")
	public @ResponseBody ApiResult saveAnswerTwo(HttpServletResponse response,
			@RequestParam(value = "params",required = false) String params,
			@RequestParam(value = "customerUuid",required = false) String uuid) {
		ApiResult ar = null;
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<String,Object> jsonMap = new HashMap<>();
		Map<String,Object> runMap = new HashMap<>();
	
		jsonMap.put("query", runMap);
		//患者id是否存在
		if (ObjectUtils.isEmpty(uuid)){
			ar=ApiResult.error(ApiCode.BAD_REQUEST, "没有该患者");
			return  ar;
		}
		try {
			int success = professionGatherService.gatherAnswers(uuid,params);
			if (success>0){
				ar = ApiResult.right(jsonMap);
				return ar;
			}else {
				ar=ApiResult.error(ApiCode.BAD_REQUEST, "保存失败！");
				return  ar;
			}
		} catch (Exception e) {
			log.error("", e);
			ar=ApiResult.error(ApiCode.SERVER_ERROR, "保存时发生了错误！");
			return  ar;
		}
	}
	/**
	 * 查看自评评历史详情
	 * @param response
	 * @param customerUuid
	 * @param self
	 * @return
	 */
	@RequestMapping(value = "/mobile/patient/visit/1.0/selfDetails",method=RequestMethod.GET)
	public @ResponseBody ApiResult selfDetails(HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("subjectId") String subjectId,
			@RequestParam("complete_date") String complete_date){
			
		customerUuid = StringUtils.trimToEmpty(customerUuid);
		subjectId = StringUtils.trimToEmpty(subjectId);
		complete_date = StringUtils.trimToEmpty(complete_date);
		
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> detailRecord = csResultService.selectDetailedRecord(customerUuid, subjectId,complete_date);
		for (Map<String, Object> map : detailRecord) {
			map.put("content", map.get("content"));
			map.put("sort", map.get("sort"));
			map.put("answer1", map.get("answer1"));
			relist.add(map);
		}
				return ApiResult.right(relist);
	}
	
	
	
	
	
	/**
	 * 查看自评医评的历史记录
	 * 自评量表中的查看历史--SDS抑郁自评量表 医评量表
	 */
	@RequestMapping(value = "/mobile/patient/visit/1.0/answerHistory",method=RequestMethod.GET)
	public @ResponseBody ApiResult answerHistory(HttpServletResponse response,
			@RequestParam("customerUuid") String customerUuid,//患者id
			@RequestParam("self") String self) {//自评 医评
		
		//通过患者id查询历史自评量表 cs_zy_result（专业测试结果）
		List<Map<String, Object>> result = csResultService.selectHistory(customerUuid, self);
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		Map<String, Object> save = null;
		for (Map<String, Object> map : result) {
			save = new HashMap<String, Object>();
			save.put("complete_date", map.get("complete_date").toString());//完成时间
			save.put("result", map.get("result" ));//结果
			save.put("score", map.get("score"));//分值
			save.put("subject_id", map.get("subject_id"));//主题id
			save.put("resultId", map.get("id"));
			relist.add(save);			
		}
		return ApiResult.right(relist);
	}
	
	/**
	 * 
	 * 随访表单_自评/医评量表（列表）
	 */
	@RequestMapping(value = "/mobile/patient/visit/1.0/selfEvaluationList",method=RequestMethod.GET)
	public @ResponseBody ApiResult selfEvaluationList(
			@RequestParam("self") String self,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("serviceStaffUuid") String serviceStaffUuid
			){

		customerUuid = StringUtils.trimToEmpty(customerUuid);
		serviceStaffUuid = StringUtils.trimToEmpty(serviceStaffUuid);
		self = StringUtils.trimToEmpty(self);

		try {
			//查询随访方案  visit_precept 
			Map<String, Object> visitPrecept = visitPreceptService.selectPeriod(serviceStaffUuid, customerUuid);
			//自评量表：心理测试主题id，多个id之间以英文逗号分隔
			String test = self.equalsIgnoreCase("1") ? (String) visitPrecept.get("selfTest") : (String) visitPrecept.get("doctorTest");
			Date period = self.equalsIgnoreCase("1") ? (Date) visitPrecept.get("selfPeriod") : (Date) visitPrecept.get("doctorPeriod");

			//查询 量表的列表
			String[] ids = test.split(",");
			ProfessionSubject subject = null;
			Map<String, Object> reMap = null;
			
			List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
			for(String id : ids) {
				subject = (ProfessionSubject) professionSubjectService.select(new ProfessionSubject(id));	//通过id 返回测试主题的所有信息
				reMap = new HashMap<String, Object>();
				reMap.put("subjectid", subject.getId());
				reMap.put("title", subject.getTitle());
				Map<String, Object> result = csResultService.selectResult(customerUuid,subject.getId(),period);
				if(!ObjectUtils.isEmpty(result)) {
					reMap.put("score", result.get("score"));
					reMap.put("result", result.get("result"));
					reMap.put("analys", result.get("analys"));
				}
				lst.add(reMap);
			}
			return ApiResult.right(lst);
		} catch (Exception e) {
			log.error(customerUuid, e);
		}
		return null;
	}
	
}
