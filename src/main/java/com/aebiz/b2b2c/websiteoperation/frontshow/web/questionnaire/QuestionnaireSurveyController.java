package com.aebiz.b2b2c.websiteoperation.frontshow.web.questionnaire;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.websiteoperation.questionnaire.service.QuestionnaireService;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireModel;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireQueryModel;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.service.QuestionnaireAnswerService;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerModel;
import com.aebiz.b2b2c.websiteoperation.questions.service.QuestionsService;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;

@Controller
@RequestMapping("/questionnairesurvey")
public class QuestionnaireSurveyController extends
		BaseController<QuestionnaireModel, QuestionnaireQueryModel> {
	private QuestionnaireService myService;

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private QuestionnaireAnswerService questionnaireAnswerService;

	@Autowired
	public void setMyService(QuestionnaireService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public QuestionnaireSurveyController() {
		super("websiteoperation/sysback/questionnaire", "Questionnaire",
				QuestionnaireSurveyController.class);
	}

	/**
	 * 进入问卷调查页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/toQuestionnaireSurvey")
	public String toQuestionnaireSurvey(Model model) {
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();
		String uuid = "07e63a56162d441a9a49305cd2936803";

		QuestionnaireModel questionnaireModel = myService.getByUuid(uuid);
		List<QuestionsModel> questions = questionsService
				.getQuestionsByQuestionnaireUuid(uuid);

		model.addAttribute("questionnaire", questionnaireModel);
		model.addAttribute("questions", questions);
		model.addAttribute("customerUuid", customerUuid);

		return "websiteoperation/frontback/questionnaire/QuestionnaireSurvey";
	}

	/**
	 * 保存问卷调查答案
	 * 
	 * @return
	 */
	@RequestMapping("/doQuestionnaireSurvey")
	public String doQuestionnaireSurvey(
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("questionnaireUuid") String questionnaireUuid,
			HttpServletRequest request, HttpServletResponse response) {

		List<QuestionsModel> questions = questionsService
				.getQuestionsByQuestionnaireUuid(questionnaireUuid);

		for (QuestionsModel qm : questions) {
			QuestionnaireAnswerModel qam = new QuestionnaireAnswerModel();

			String questionUuid = qm.getUuid();
			String value = stringArrayJoin(
					request.getParameterValues(questionUuid), ",");

			qam.setCustomerUuid(customerUuid);
			qam.setQuestionnaireUuid(questionnaireUuid);
			qam.setQuestionUuid(questionUuid);
			qam.setAnswer(value);

			questionnaireAnswerService.create(qam);
		}

		return "websiteoperation/frontback/questionnaire/Success";
	}

	/**
	 * 将字符串数组转换层字符串
	 * 
	 * @param strArray
	 * @param separator
	 * @return
	 */
	private String stringArrayJoin(String[] strArray, String separator) {
		if (strArray != null && strArray.length > 0) {
			StringBuffer strbuf = new StringBuffer();
			for (int i = 0; i < strArray.length; i++) {
				strbuf.append(separator).append(strArray[i]);
			}
			return strbuf.deleteCharAt(0).toString();
		}
		return "";
	}
}