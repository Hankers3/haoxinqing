package com.aebiz.b2b2c.websiteoperation.sysback.web.questionnaire;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.websiteoperation.questionnaire.service.QuestionnaireService;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireModel;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireQueryModel;
import com.aebiz.b2b2c.websiteoperation.questions.service.QuestionsService;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.service.QuestionsRelService;

@Controller
@RequestMapping("/sysback/questionnaire")
public class QuestionnaireController extends
		BaseController<QuestionnaireModel, QuestionnaireQueryModel> {
	private QuestionnaireService myService;
	@Autowired
	private QuestionsRelService questionsRelService;
	@Autowired
	private QuestionsService questionsService;

	@Autowired
	public void setMyService(QuestionnaireService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public QuestionnaireController() {
		super("websiteoperation/sysback/questionnaire", "Questionnaire",
				QuestionnaireController.class);
	}

	/**
	 * 
	 * 更新问卷之前需要将问卷所关联的试题查询出来发送到页面展示
	 * 
	 */
	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {

		// 1.获得试卷对象
		QuestionnaireModel qnm = (QuestionnaireModel) model.asMap().get("m");

		// 2.通过问卷ID获得所有关联的试题
		List<QuestionsModel> qmList = questionsService
				.getQuestionsByQuestionnaireUuid(qnm.getUuid());

		model.addAttribute("qmList", qmList);
	}

	/**
	 * 
	 * 问卷选择试题,试题选择完成之后需要将选择的试题的标题在页面展示出来
	 * 
	 * 1.获得已经选择了的试题uuid={001 ,002},已经选择的试题不在弹出框中显示 2.试题选择完成之后需要将已经选择的试题在页面上展示出来。
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/chooseQuestion")
	@ResponseBody
	public List<QuestionsModel> chooseQuestion(HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		// 获得已经选择了的试题
		String checkIds = request.getParameter("checkIds");

		// 获得问题对象，发送到页面
		List<QuestionsModel> choosedlist = new ArrayList<QuestionsModel>();
		String[] ids = checkIds.split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (!StringUtil.isEmpty(id)) {
					QuestionsModel qm = questionsService.getByUuid(id);
					choosedlist.add(qm);
				}
			}
		}

		return choosedlist;
	}

	/**
	 * 
	 * 1.更新问卷，需要先更新问卷 2.然后更新问卷和试题的关联关系
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/qupdate" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String questionnaireUpdate(Model model,
			@ModelAttribute("m") QuestionnaireModel m,
			HttpServletRequest request) {

		// 从页面获得到所有的题目编号和位置
		String[] qIds = m.getQuestionIds();
		String[] pValues = m.getPositionValues();

		// 构建题目和位置的列表，以供循环保存
		List<Map<String, String>> questionList = new ArrayList<Map<String, String>>();

		if (qIds != null && qIds.length > 0 && pValues != null
				&& pValues.length > 0) {
			for (int i = 0; i < qIds.length; i++) {
				Map<String, String> qpMap = new HashMap<String, String>();

				// 获得某个具体题目，在页面上设置的具体位置
				if (!StringUtil.isEmpty(qIds[i])
						&& !StringUtil.isEmpty(pValues[i])) {
					qpMap.put(qIds[i], pValues[i]);
				}

				questionList.add(qpMap);
			}
		}
		// 调用更新方法进行更新
		myService.updateQuestionnaire(m, questionList);

		return "websiteoperation/sysback/questionnaire/QuestionnaireSuccess";
	}

	/**
	 * 
	 * 1.先保存问卷 2.再保存问卷试题关联关系
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/qsave" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String questionnaireAdd(Model model,
			@ModelAttribute("m") QuestionnaireModel m,
			HttpServletRequest request) {

		// 获得所有关联的题目编号
		String[] qIds = m.getQuestionIds();
		String[] pValues = m.getPositionValues();

		List<Map<String, String>> questionList = new ArrayList<Map<String, String>>();

		if (qIds != null && qIds.length > 0 && pValues != null
				&& pValues.length > 0) {
			for (int i = 0; i < qIds.length; i++) {
				Map<String, String> qpMap = new HashMap<String, String>();

				// 获得某个具体题目，在页面上设置的具体位置
				if (!StringUtil.isEmpty(qIds[i])
						&& !StringUtil.isEmpty(pValues[i])) {
					qpMap.put(qIds[i], pValues[i]);
				}
				questionList.add(qpMap);
			}
		}
		myService.addQuestionnaire(m, questionList);

		return "websiteoperation/sysback/questionnaire/QuestionnaireSuccess";
	}
}