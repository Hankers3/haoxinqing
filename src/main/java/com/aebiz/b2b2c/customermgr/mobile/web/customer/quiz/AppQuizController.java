package com.aebiz.b2b2c.customermgr.mobile.web.customer.quiz;

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

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.consultrecord.service.ConsultRecordService;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.servicestaff.doctorright.service.DoctorRightService;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.websiteoperation.customerquiz.service.CustomerQuizService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.service.QuestionsService;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.service.QuizResultService;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.service.QuizResultDocRelService;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;

@Controller
@RequestMapping("/app/pub/quiz")
public class AppQuizController extends AppBaseController {

	public AppQuizController() {
		super("", "", AppQuizController.class);
	}

	/* 注入测试分类 */
	@Autowired
	private QuizCategoryService quizCategoryService;

	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;

	/* 注入测试分类结果 service */
	@Autowired
	private QuizResultService quizResultService;

	/* 注入测试题service */
	@Autowired
	private QuestionsService questionsService;

	/* 注入测试结果导向医生接口 */
	@Autowired
	private QuizResultDocRelService quizResultDocRelService;

	@Autowired
	private DoctorRightService doctorRightService;
	@Autowired
	private ServicestaffService servicestaffService;

	@Autowired
	private ServicestaffinfoService staffinfoService;
	
	@Autowired
	private ConsultRecordService consultRecordService;
	
	@Autowired 
	private CustomerQuizService customerQuizService;

	/**
	 * 获取所有测试分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAllQuizCategory", method = RequestMethod.GET)
	public String getDeleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		// callback
		Object callback = map.get("callback");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();

		// 获取所有的测试题分类
		List<QuizCategoryModel> list = quizCategoryService.getQuizCategoryModels();
		if (list != null && list.size() > 0) {
			for (QuizCategoryModel qc : list) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				noticeMap.put("quizCategoryUuid", qc.getUuid());
				noticeMap.put("quizCategoryName", qc.getCategoryName());
				relist.add(noticeMap);
			}
		}
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 获取测试分类下所有的测试结果
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getQuizResultList", method = RequestMethod.GET)
	public String getQuizResultList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("quizCategoryUuid") String quizCategoryUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		// callback
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(quizCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "测试题分类编号不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();

		List<QuizResultModel> list = quizResultService.getListByQuizCategoryUuid(quizCategoryUuid);

		// 获取所有的测试题分类
		if (list != null && list.size() > 0) {
			for (QuizResultModel qr : list) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				noticeMap.put("quizResultUuid", qr.getUuid());
				noticeMap.put("quizResult", qr.getResult());
				noticeMap.put("startScore", qr.getStartScore());
				noticeMap.put("endScore", qr.getEndScore());
				relist.add(noticeMap);
			}
		}

		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

	/**
	 * 获取测试分类下测试结果
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getQuizResult", method = RequestMethod.GET)
	public String getQuizResult(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("quizCategoryUuid") String quizCategoryUuid, @RequestParam("score") String score) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		String customerUuid = request.getParameter("customerUuid");
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		// callback
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(quizCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "测试题分类编号不能为空"), callback);
			return null;
		}
		// 判断用户是否存在
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();

		QuizResultModel quizResultModel = quizResultService.getByQuizCategoryUuid(quizCategoryUuid, score);
		// 获取测试分类结果
		if (quizResultModel != null) {
			jsonMap.put("quizResultUuid", quizResultModel.getUuid());
			jsonMap.put("quizResult", quizResultModel.getResult());
			jsonMap.put("quizResultNote", quizResultModel.getResultNote());
			// 获取分类
			List<QuizResultDocRelModel> list = quizResultDocRelService
					.getListByQuizResultUuid(quizResultModel.getUuid());
			if (list != null && list.size() > 0) {
				for (QuizResultDocRelModel qrd : list) {
					Map<Object, Object> reMap = new HashMap<Object, Object>();
					String uuid = qrd.getServiceStaffInfoId();
					if (!StringUtil.isEmpty(uuid)) {
						ServicestaffModel servicestaffModel = servicestaffService.getByUuid(uuid);
						ServicestaffinfoModel staffInfo= staffinfoService.getServicestaffinfoModelByServicestaffUuid(uuid);
						if(servicestaffModel !=null){
							// 医生名
							reMap.put("doctorName", servicestaffModel.getRealName());
							// 医生uuid
							reMap.put("doctorUuid", uuid);
							// 医生头像
							if(staffInfo !=null){
								reMap.put("img", staffInfo.getImgUrl());
								// 医生性别
								reMap.put("sex", staffInfo.getSex());
								// 医生职称
								reMap.put("professional", staffInfo.getProfessional());
							}
							// 执业医院
							reMap.put("hospitalName", servicestaffModel.getHospitalName());
							// 执业科室
							reMap.put("departmentName", servicestaffModel.getDepartmentName());
							// 是否开通电话咨询 0：未开通 1：开通
							DoctorRightModel doctorRightModel = doctorRightService.getByDoctorUuid(uuid);
							int serviceCount = 0;
							if (doctorRightModel != null) {
								reMap.put("telState", doctorRightModel.getPhone());
								// 通过医生主键查询医生电话咨询已处理的数量
								serviceCount = consultRecordService.getTeledCount(uuid);
								reMap.put("personal", doctorRightModel.getPersonal());
								reMap.put("plus", doctorRightModel.getPlus());
								reMap.put("teletext", doctorRightModel.getTeletext());
							} else {
								reMap.put("telState", "0");
								reMap.put("personal","0");
								reMap.put("plus", "0");
								reMap.put("teletext", "0");
							}
							
							// 医生服务次数
							reMap.put("serviceCount", serviceCount);
							relist.add(reMap);
						}
					}
				}
			}
		}
		// 增加积分
		// 判断该测试分类是否被分享过
		if (!StringUtil.isEmpty(customerUuid)) {
			VipclubIntegralLogModel vig = vipclubIntegralLogService.getVipclubIntegralLogByQuizResultUuid(customerUuid,
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS, quizCategoryUuid);
			if (null == vig) {
				// 没被分享过，添加积分
				vipclubIntegralLogService.saveVipIntegralLog(customerUuid, "add", 2,
						IntegralType.ADD_QUIZ.getValue(), VipclubIntegralLogModel.VIPCLUB_USERTYPE_CUS,
						"首次完成一套测试题获得积分", quizCategoryUuid);
			} else {
				System.out.println("该套测试题分类已经获得积分,不在获取积分");
			}
		}
		
		//如果有测试数据将此患者自测数据添加到数据库表中做记录
		if (!StringUtil.isEmpty(customerUuid)&&!StringUtil.isEmpty(quizCategoryUuid)&&!StringUtil.isEmpty(score)) {
			String succ = customerQuizService.saveCustomerQuiz(customerUuid, quizCategoryUuid, score);
			if (!StringUtil.isEmpty(succ)) {
				System.out.println("-----患者自测数据添加成功----customerUuid："+customerUuid+",quizCategoryUuid:"+quizCategoryUuid+",score:"+score+"-----");
			}
		}
		
		
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;

	}

	/**
	 * 获取分类下所有的测试题
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAllQuiz", method = RequestMethod.GET)
	public String getAllQuiz(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("quizCategoryUuid") String quizCategoryUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,false" });

		// callback
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(quizCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "测试题分类编号不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		List relist = new ArrayList();
		// 获取所有的测试题
		List<QuestionsModel> list = questionsService.getQuestionsByQuizCategoryUuid(quizCategoryUuid,
				QuestionsModel.STARTUSING);

		// 获取所有的测试题分类
		if (list != null && list.size() > 0) {
			for (QuestionsModel qm : list) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				noticeMap.put("quizUuid", qm.getUuid());
				noticeMap.put("quizTitle", qm.getTitle());
				noticeMap.put("imageUrl", qm.getImageUrl());
				// 获取所有测试选项
				List<OptionsModel> options = qm.getOptionsModels();
				List opts = new ArrayList();
				if (options != null && options.size() > 0) {
					for (OptionsModel option : options) {
						Map<Object, Object> optionMap = new HashMap<Object, Object>();
						optionMap.put("optionUuid", option.getUuid());
						optionMap.put("optionTitle", option.getOptionTitle());
						optionMap.put("optionValue", option.getOptionValue());
						opts.add(optionMap);
					}
				}
				noticeMap.put("options", opts);
				relist.add(noticeMap);
			}
		}

		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}
	
	/**
	 * 获取测试题分类说明
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getQuizCategory", method = RequestMethod.GET)
	public String getQuizCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("quizCategoryUuid") String quizCategoryUuid) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}

		// callback
		Object callback = map.get("callback");
		// 判断用户是否存在
		if (StringUtil.isEmpty(quizCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "测试题分类编号不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		QuizCategoryModel quizCategory = quizCategoryService.getByUuid(quizCategoryUuid);
		// 获取所有的测试题
		List<QuestionsModel> list = questionsService.getQuestionsByQuizCategoryUuid(quizCategoryUuid, "");
		System.out.println("====================" + quizCategoryUuid + "====================");
		// 获取所有的测试题分类
		if (quizCategory != null) {
			jsonMap.put("categoryName", quizCategory.getCategoryName());
			// 测试说明
			jsonMap.put("note", quizCategory.getNote());
			// 填写说明
			jsonMap.put("fillExplain", quizCategory.getFillExplain());
			jsonMap.put("imageUrl", quizCategory.getImgUrl());
			if (list != null && list.size() > 0) {
				jsonMap.put("quizNum", list.size());
			} else {
				jsonMap.put("quizNum", "0");
			}
		}
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	}

}
