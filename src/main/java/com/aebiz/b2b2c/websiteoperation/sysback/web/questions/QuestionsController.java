package com.aebiz.b2b2c.websiteoperation.sysback.web.questions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.options.service.OptionsService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.service.QuestionsService;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionTypeEnum;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsQueryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/questions")
public class QuestionsController extends
		BaseController<QuestionsModel, QuestionsQueryModel> {
	private QuestionsService myService;
	@Autowired
	private OptionsService optionsService;
	@Autowired
	private UuidService us;
	/*测试分类*/
	@Autowired
	private QuizCategoryService quizCategoryService;

	@Autowired
	public void setMyService(QuestionsService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public QuestionsController() {
		super("websiteoperation/sysback/questions", "Questions",
				QuestionsController.class);
	}

	/**
	 * 将试题的类型发送到页面,以供选择使用
	 */
	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		sendQuizCategoryList(model);
	}
	
	
    @Override
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList(Model model, HttpServletRequest request) {
    	String quizCategoryUuid = request.getParameter("quizCategoryUuid");
    	model.addAttribute("quizCategoryUuid", quizCategoryUuid);
    	preparedListData(model, request);
    	return "websiteoperation/sysback/questions/QuestionsList";
    }
    
    @Override
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model, HttpServletRequest request) {
    	String quizCategoryUuid = request.getParameter("quizCategoryUuid");
    	model.addAttribute("quizCategoryUuid", quizCategoryUuid);
    	return super.toAdd(model, request);
    }
	/**
	 * 保存试题和选项
	 * 
	 * @param qm
	 * @param request
	 * @return
	 */
	@RequestMapping("/addquestion")
	public String addQuerstion(
			@ModelAttribute("m") QuestionsModel m,
			@RequestParam(value = "imgFile", required = false) MultipartFile[] imgFiles,
			HttpServletRequest request) {

		int count = Integer.parseInt(request.getParameter("temp"));

		// 循环获得试题的选项
		List<OptionsModel> omList = new ArrayList<OptionsModel>();
		for (int i = 1; i < count; i++) {
			OptionsModel om = new OptionsModel();
			String name = "newDiv" + i;
			String name1 = "score" + i;
			int optionValue=0;
			String optionTitle = request.getParameter(name);
			String value = request.getParameter(name1);
			
			if (StringUtil.isEmpty(optionTitle)) {
				continue;
			}
			
			if (!StringUtil.isEmpty(value)) {
				optionValue = Integer.parseInt(value);
			}
			om.setOptionTitle(optionTitle);
			om.setOptionValue(optionValue);
			omList.add(om);
		}
		//
		request.setAttribute("quizCategoryUuid", m.getQuizCategoryUuid());
		myService.addQuestion(m, omList,imgFiles);
		return "websiteoperation/sysback/questions/QuestionsSuccess";
	}

	/**
	 * 查询当前试题所拥有的选项，发送到页面
	 */
	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		// 将试题的类型发送到页面展示
		sendQuizCategoryList(model);
		// ?
		QuestionsModel qm = (QuestionsModel) model.asMap().get("m");
		// 得到选择list
		List<OptionsModel> optionsList = optionsService
				.getOptionsByQuestionUuid(qm.getUuid());

		model.addAttribute("num", optionsList.size()); // 选项的数量存入num
		model.addAttribute("optionsList", optionsList);
	}

	/**
	 * 更新试题和选项
	 * 
	 * @param qm
	 * @param request
	 * @return
	 */
	@RequestMapping("/updatequestion")
	public String updateQuerstion(@ModelAttribute("m") QuestionsModel m,
			@RequestParam(value = "imgFile", required = false) MultipartFile[] imgFiles,
			HttpServletRequest request) {

		int count = Integer.parseInt(request.getParameter("temp"));

		// 循环获得试题的选项及分数
		List<OptionsModel> omList = new ArrayList<OptionsModel>();
		for (int i = 0; i < count; i++) {
			OptionsModel om = new OptionsModel();
			String name = "newDiv" + i;
			String uuid = "uuid" + i;
			String score = "score" + i;
			String optionTitle = request.getParameter(name);
			String optionValue = request.getParameter(score);
			String uuidValue = request.getParameter(uuid);

			if (StringUtil.isEmpty(optionTitle)
					&& StringUtil.isEmpty(optionValue)) {
				continue;
			}
			if(!StringUtil.isEmpty(uuidValue)){
				om.setUuid(uuidValue);	
			}
			om.setQuestionUuid(m.getUuid());
			om.setOptionTitle(optionTitle); // 设置选项名
			om.setOptionValue(Integer.parseInt(optionValue)); // 设置分数
			omList.add(om); // 放入list中
		}
		//
		request.setAttribute("quizCategoryUuid", m.getQuizCategoryUuid());
		myService.updateQuestion(m, omList,imgFiles);
		return "websiteoperation/sysback/questions/QuestionsList";
	}



	/**
	 * 设置选项和分数
	 * 
	 * @param qm
	 * @param request
	 * @return
	 */
	@RequestMapping("/toOption/{uuid}")
	public String toOption(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 通过id获得model
		QuestionsModel questionsModel = myService.getByUuid(uuid);

		// 存入，以便页面使用
		model.addAttribute("m", questionsModel);

		// 做相应的操作
		preparedUpdateData(model, request);

		return "websiteoperation/sysback/options/options";
	}

	/**
	 * 将测试分类发送到页面,以供选择使用
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		//sendQuestionTypeShowName(model);
		//sendQuizCategoryList(model);
	}

	/**
	 * 选择试题弹出框，已经被选中或者已经被试卷关联的试题在再次选择的时候不会显示出来
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/questionLists")
	public String questionLists(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		List<QuestionsModel> showList = new ArrayList<QuestionsModel>();

		Map<String, Object> pageParamMap = parsePageParam(request);

		QuestionsQueryModel qm = super.parseQueryModel(request);

		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart"))
				.intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength"))
				.intValue();

		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_"
				+ iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap
				.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}

		String[] idArray = null;
		// 获得已经选择的题目
		String idStr = request.getParameter("choosedIds");
		idArray = convertToArrays(idStr);

		List<QuestionsModel> listData = null;
		listData = this.myService.getQuestionsByNotSelected(iDisplayStart,
				iDisplayLength, idArray);

		int totalCount = this.myService.getQuestionsCountByNotSelected(idArray);

		if (listData != null && listData.size() > 0) {
			for (int i = 0; i < listData.size(); i++) {
				QuestionsModel m = (QuestionsModel) listData.get(i);
				showList.add(m);
			}
		}

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("sEcho", pageParamMap.get("sEcho"));
		jsonMap.put("iTotalRecords", Integer.valueOf(totalCount));
		jsonMap.put("iTotalDisplayRecords", Integer.valueOf(totalCount));
		jsonMap.put("aaData", showList);

		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.print(JSON.toJSONString(jsonMap));

		return null;
	}

	// ===========================================================私有方法=====================================================================
	/**
	 * 将试题的类型发送到页面展示
	 * 
	 * @param model
	 */
	private void sendQuestionTypeShowName(Model model) {
		List<DataTablesPageParam> questionTypeList = new ArrayList<DataTablesPageParam>();

		for (QuestionTypeEnum qt : QuestionTypeEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(qt.getKey());
			dpp.setValue(qt.getValue());
			questionTypeList.add(dpp);
		}
		model.addAttribute("questionTypeList", questionTypeList);
	}
	/**
	 * 将测试分类信息发送到页面展示
	 * 
	 * @param model
	 */
	private void sendQuizCategoryList(Model model) {
		//得到所有测试分类
		List<QuizCategoryModel> quizCategoryModelList = quizCategoryService.getAll();
		
		model.addAttribute("questionTypeList", quizCategoryModelList);
	}

	/**
	 * 将页面传过来的以","结尾的字符串转换成字符串数组
	 * 
	 * @param idStr
	 *            id字符串
	 * @return
	 */
	private String[] convertToArrays(String idStr) {
		String[] idArray = null;
		if (!StringUtil.isEmpty(idStr)) {
			if (idStr.endsWith(",")) {
				idStr.substring(0, idStr.length() - 1);
			}
			idArray = idStr.split(",");
		}
		return idArray;
	}
}