package com.aebiz.b2b2c.websiteoperation.sysback.web.quizresult;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;


import com.aebiz.b2b2c.websiteoperation.quizresult.service.QuizResultService;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultQueryModel;
import com.aebiz.b2b2c.websiteoperation.sysback.web.quizresult.vo.QuizResultWebModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.ModuleEnum;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/quizresult")
public class QuizResultController extends BaseController<QuizResultModel,QuizResultQueryModel>{
	private QuizResultService myService;
	@Autowired
	public void  setMyService(QuizResultService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public QuizResultController(){
		super("websiteoperation/sysback/quizresult","QuizResult",QuizResultController.class);
	}
	/**
	 * 展示该测试分类下的测试分类结果列表
	 * @param quizCategoryUuid 测试分类编号
	 * @return @PathVariable("quizCategoryUuid") String quizCategoryUuid,
	 * @2015-11-20
	 * @author :SZH
	 */
	@RequestMapping(value={"/quizResultList"},method={RequestMethod.GET})
	public String quizResultList( @RequestParam("quizCategoryUuid") String quizCategoryUuid,Model model, HttpServletRequest request){
		model.addAttribute("quizCategoryUuid",quizCategoryUuid);
		return "websiteoperation/sysback/quizresult/QuizResultList";
	}
	
	
	
	
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {

		super.preparedListData(model, request);
	}
	/**
	 * 重写toAdd
	 * @param quizCategoryUuid
	 * @param model
	 * @param request
	 * @return
	 * @2015-11-23
	 * @author :SZH
	 */
	@RequestMapping(value = "/quizResultAdd", method = RequestMethod.GET)
	public String toAdd(@RequestParam("quizCategoryUuid") String quizCategoryUuid,Model model, HttpServletRequest request) {
		model.addAttribute("quizCategoryUuid", quizCategoryUuid);
		return super.toAdd(model, request);
	}
	/**
	 * 重写ADD
	 */
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("m") QuizResultModel m,
			HttpServletRequest request) {
		model.addAttribute("quizCategoryUuid", (m.getQuizCategoryUuid()==null?"":m.getQuizCategoryUuid()));
		return super.add(model, m, request);
	}
	/**
	 * 重写update
	 */
	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute("m") QuizResultModel m,
			HttpServletRequest request) {
		model.addAttribute("quizCategoryUuid", (m.getQuizCategoryUuid()==null?"":m.getQuizCategoryUuid()));
		return super.update(model, m, request);
	}
	
	
	
	
	
}