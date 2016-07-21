package com.hxq.mobile.patient.visit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.hxq.mobile.entity.weixin.CsZySubject;
import com.hxq.mobile.patient.visit.service.DoctorAdviceMainService;
import com.hxq.mobile.patient.visit.service.ProfessionGatherService;
import com.hxq.mobile.patient.visit.service.VisitApplyService;
import com.hxq.mobile.weixin.service.CsZySubjectService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 医嘱指导列表和详情  
 *
 */
@Controller
@RequestMapping("/mobile/patient/visit")
public class PatientVisitController {
	Logger log = LoggerFactory.getLogger(PatientVisitController.class);

	@Resource(name="com.hxq.mobile.patient.visit.service.DoctorAdviceMainService")
	private DoctorAdviceMainService adviceMainService;

	/* 随访的service */
	@Autowired
	private VisitPreceptService visitPreceptService;

	@Resource(name="com.hxq.mobile.weixin.service.csZySubjectService")
	private CsZySubjectService csZySubjectService;

	@Resource(name="com.hxq.mobile.patient.visit.service.ProfessionGatherService")
	private ProfessionGatherService professionGatherService;

	/*
	 *注入申请随访  
	 */
	@Resource(name="com.hxq.mobile.patient.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;
	
	/****************************************重要医嘱详情**********************************************************/	
	//重要医嘱-列表  返回全部的数据  方法一：
	/*@RequestMapping(value = "2.0/getDoctorsAdviceList", method = RequestMethod.GET)
	public @ResponseBody  List<DoctorAdviceMain> getDoctorAdviceList1(WebRequest request){
      
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		String customerUuid = RequestUtil.getFormValue(form, "customerUuid");
		if(ObjectUtils.isEmpty(customerUuid)) return null;

		List<DoctorAdviceMain> lstResult = null;
		try {
			
			String serviceStaffUuid = RequestUtil.getFormValue(form, "serviceStaffUuid");
			if(ObjectUtils.isEmpty(serviceStaffUuid)){
				Map<String, Object> map = adviceMainService.selectVisitApplyByCustomer(customerUuid);
				serviceStaffUuid = StringUtils.trimToEmpty(map.get("serviceStaffUuid"));
				form.put("serviceStaffUuid", new String[]{serviceStaffUuid});
			}

			List<Map<String, Object>> lstMain = adviceMainService.selectList(form, false);
			if(lstMain==null) return null;
	
			DoctorAdviceMain parent = null;
			List<Map<String, Object>> lstChild = null;
			lstResult = new ArrayList<DoctorAdviceMain>(lstMain.size());

//				String uuid = IdentityHelper.uuid2();//uuid
			for(Map<String, Object> main : lstMain) {
				parent = BeanUtils.map2Bean(main, DoctorAdviceMain.class, false);
				lstChild = adviceMainService.selectChildList(StringUtils.trimToEmpty(main.get("uuid")));
				if(lstChild!=null) {
					parent.setChild(new ArrayList<DoctorAdvice>(lstChild.size()));
					for(Map<String, Object> child : lstChild) {
						parent.getChild().add(BeanUtils.map2Bean(child, DoctorAdvice.class, false));
					}
				}
				lstResult.add(parent);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return lstResult;
	}*/
	/**
	 * 重要医嘱详情 方法二：
	 * @param request
	 * @return
	 */
// /mobile/patient/visit/1.0/getDoctorsAdviceList
	@RequestMapping(value = "/1.0/getDoctorsAdviceList", method=RequestMethod.GET)
	public ResponseEntity<String> getDoctorAdviceList2(WebRequest request) {
    
		Map<String, Object> form = RequestUtil.cleanParameterMap(request.getParameterMap());
		String customerUuid = RequestUtil.getFormValue(form, "customerUuid");
		if(ObjectUtils.isEmpty(customerUuid)) return null;
		List<Map<String, Object>> lstMain = null;
		try {
					
			String serviceStaffUuid = RequestUtil.getFormValue(form, "serviceStaffUuid");
			if(ObjectUtils.isEmpty(serviceStaffUuid)){
				Map<String, Object> map = adviceMainService.selectVisitApplyByCustomer(customerUuid);
				serviceStaffUuid = StringUtils.trimToEmpty(map.get("serviceStaffUuid"));
				form.put("serviceStaffUuid", new String[]{serviceStaffUuid});
			}
	
			lstMain = adviceMainService.selectList(form, false);
			if(lstMain==null) return null;
			List<Map<String, Object>> lstChild = null;
			for(Map<String, Object> parent : lstMain) {
				lstChild = adviceMainService.selectChildList(StringUtils.trimToEmpty(parent.get("uuid")));
				if(!ObjectUtils.isEmpty(lstChild)) {
					parent.put("child", lstChild);
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return JsonUtil.ResponseJson(JsonUtil.toJSONString(lstMain, true, true));
	}

	// TODO:根据随访id查询医评，自评列表
	/**
	 * 查询医评 自评
	 * @param response response
	 * @param type 查询类型
	 * @return string
	 */
	@RequestMapping(value = "/1.0/getTestByVisitUuid", method = RequestMethod.GET)
	public String getTestByVisitUuid(HttpServletResponse response, @RequestParam(value = "type",required = false) String type,@RequestParam(value = "visitUuid",required = false) String visitUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<String,Object> jsonMap = new HashMap<>();
		Map<String,Object> runMap = new HashMap<>();

		if (ObjectUtils.isEmpty(visitUuid)){
			runMap.put("success", SysConst.FAIL);
			runMap.put("message", "请指定随访的id！");
			jsonMap.put("query", runMap);
			HttpServletUtils.outJson(response, jsonMap);
			return null;
		}
		runMap.put("success", SysConst.SUCCESS);
		runMap.put("message", SysConst.SUCCESSMESSAGE);
		jsonMap.put("query", runMap);
		try {
			// 获取随访方案的集合列表
			VisitPreceptModel visitPreceptModel = visitPreceptService.getByUuid(visitUuid);
			String selfTest = visitPreceptModel.getSelfTest();
			String doctorTest = visitPreceptModel.getDoctorTest();


			List<CsZySubject> list;
			if (!ObjectUtils.isEmpty(type)){
				if ("1".equals(type)||"doctorTest".equals(type)){
					list = this.getList(doctorTest);
					jsonMap.put("relist", list);

				}
				else if ("2".equals(type)||"selfTest".equals(type)){
					list = this.getList(selfTest);
					jsonMap.put("relist", list);
				}else{
					jsonMap.put("relist", null);
				}
			}
		} catch (Exception e) {
			e.getMessage();
			jsonMap.put("relist", null);
		}
		HttpServletUtils.outJson(response, jsonMap);
		return null;
	}

	private List<CsZySubject> getList(String str){
		if (!ObjectUtils.isEmpty(str)){
			String[] arr;
			if (str.indexOf(",")>0){
				arr = str.split(",");
			}else if (str.indexOf("，")>0){
				arr = str.split("，");
			}else{
				arr = new String[]{str};
			}
			List<CsZySubject> list = new ArrayList<>();
			if (!ObjectUtils.isEmpty(arr)){
				for (String temp:arr){
					try {
						// 获取随访方案的集合列表
						CsZySubject subject = csZySubjectService.selectByPrimaryKey(temp);
						list.add(subject);
					} catch (Exception e) {
						e.getMessage();
					}
				}
			}
			return list;
		}else{
			return null;
		}
	}

	//TODO:评测答案保存
		@RequestMapping(value = "/1.0/saveAnswer")
	public String saveAnswer(HttpServletResponse response,@RequestParam(value = "params",required = false) String params,@RequestParam(value = "customerUuid",required = false) String uuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 设置返回信息
		Map<String,Object> jsonMap = new HashMap<>();
		Map<String,Object> runMap = new HashMap<>();

		jsonMap.put("query", runMap);
		if (ObjectUtils.isEmpty(uuid)){
			runMap.put("success", SysConst.FAIL);
			runMap.put("message", "没有患者！");
			HttpServletUtils.outJson(response, jsonMap);
			return null;
		}
		runMap.put("success", SysConst.SUCCESS);
		runMap.put("message", SysConst.SUCCESSMESSAGE);

		try {
			int success = professionGatherService.gatherAnswers(uuid,params);
			if (success>0){
				HttpServletUtils.outJson(response, jsonMap);
				return null;
			}else {
				runMap.put("success", SysConst.FAIL);
				runMap.put("message", "保存失败！");
				HttpServletUtils.outJson(response, jsonMap);
				return null;
			}
		} catch (Exception e) {
			log.error("", e);
			runMap.put("success", SysConst.FAIL);
			runMap.put("message", "保存时发生了错误！");
			HttpServletUtils.outJson(response, jsonMap);
			return null;
		}

	}

}
