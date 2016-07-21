package com.aebiz.b2b2c.websiteoperation.sysback.web.questionnaireanswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.service.QuestionnaireAnswerService;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerModel;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerQueryModel;

@Controller
@RequestMapping("/sysback/questionnaireanswer")
public class QuestionnaireAnswerController extends
		BaseController<QuestionnaireAnswerModel, QuestionnaireAnswerQueryModel> {
	private QuestionnaireAnswerService myService;

	@Autowired
	public void setMyService(QuestionnaireAnswerService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public QuestionnaireAnswerController() {
		super("websiteoperation/sysback/questionnaireanswer",
				"QuestionnaireAnswer", QuestionnaireAnswerController.class);
	}
}