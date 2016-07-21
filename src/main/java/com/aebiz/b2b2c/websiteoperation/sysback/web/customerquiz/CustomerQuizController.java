package com.aebiz.b2b2c.websiteoperation.sysback.web.customerquiz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.websiteoperation.customerquiz.service.CustomerQuizService;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizModel;
import com.aebiz.b2b2c.websiteoperation.customerquiz.vo.CustomerQuizQueryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;



@Controller
@RequestMapping("/sysback/customerquiz")
public class CustomerQuizController extends BaseController<CustomerQuizModel,CustomerQuizQueryModel>{
	private CustomerQuizService myService;
	@Autowired
	private QuizCategoryService quizCategoryService;
	@Autowired
	public void  setMyService(CustomerQuizService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerQuizController(){
		super("websiteoperation/sysback/customerquiz","CustomerQuiz",CustomerQuizController.class);
	}
	
	
	
	
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		 List<QuizCategoryModel> quizCategoryModelList = quizCategoryService.getAll();
		 model.addAttribute("quizCategoryModelList", quizCategoryModelList);
	}
	
	
	
	
	
	

	
	
	
	
	

	
	
}