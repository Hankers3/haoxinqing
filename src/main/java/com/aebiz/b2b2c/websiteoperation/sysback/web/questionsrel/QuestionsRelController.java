package com.aebiz.b2b2c.websiteoperation.sysback.web.questionsrel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

import com.aebiz.b2b2c.websiteoperation.questionsrel.service.QuestionsRelService;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelQueryModel;

@Controller
@RequestMapping("/sysback/questionsrel")
public class QuestionsRelController extends
		BaseController<QuestionsRelModel, QuestionsRelQueryModel> {
	private QuestionsRelService myService;

	@Autowired
	public void setMyService(QuestionsRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public QuestionsRelController() {
		super("websiteoperation/sysback/questionsrel", "QuestionsRel",
				QuestionsRelController.class);
	}
}