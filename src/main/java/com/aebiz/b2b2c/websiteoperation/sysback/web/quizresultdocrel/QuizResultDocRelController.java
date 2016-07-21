package com.aebiz.b2b2c.websiteoperation.sysback.web.quizresultdocrel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.service.ServicestaffinfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.service.QuizResultDocRelService;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelQueryModel;

@Controller
@RequestMapping("/sysback/quizResultDocRel")
public class QuizResultDocRelController extends BaseController<QuizResultDocRelModel,QuizResultDocRelQueryModel>{
	private QuizResultDocRelService myService;
	@Autowired
	public void  setMyService(QuizResultDocRelService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public QuizResultDocRelController(){
		super("websiteoperation/sysback/quizresultdocrel","QuizResultDocRel",QuizResultDocRelController.class);
	}
	@Autowired
	private ServicestaffinfoService servicestaffinfoService;
	
	
	@RequestMapping("/queryDocList")
	public String queryDocList(HttpServletResponse arg0, HttpServletRequest arg1)
			throws Exception {
		
		return "redirect:/sysback/servicestaffinfo/queryList";
	}
	@RequestMapping(value={"/quizResultDocRelList"},method={RequestMethod.GET})
	public String toList( @RequestParam("quizResultUuid") String quizResultUuid,Model model, HttpServletRequest request){
		//根据测试分类编号查询测试分类结果
		List<QuizResultDocRelModel> quizResultDocRelModels = myService.getListByQuizResultUuid(quizResultUuid);
		model.addAttribute("quizResultUuid",quizResultUuid);
		model.addAttribute("quizResultDocRelModels",quizResultDocRelModels);
		return "websiteoperation/sysback/quizResultDocRel/QuizResultDocRelList";
	}
	
	@RequestMapping("/chooseDoc")
	@ResponseBody
	public List<ServicestaffinfoModel> chooseQuestion(HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		// 获得已经选择了的医生ID和quizResultId
		String checkIds = request.getParameter("checkIds");
		String quizResultId = request.getParameter("quizResultId");
		// 获得问题对象，发送到页面
		List<ServicestaffinfoModel> choosedlist = new ArrayList<ServicestaffinfoModel>();
		String[] ids = checkIds.split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (!StringUtil.isEmpty(id)) {
					ServicestaffinfoModel qm = servicestaffinfoService.getByUuid(id);
					choosedlist.add(qm);
				}
				//如果不重复，则保存对象
				if(!myService.isRepeat(id, quizResultId)){
					myService.create(id, quizResultId);
				}
				
				
			}
		}

		return choosedlist;
	}
	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(QuizResultDocRelModel m, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.delete(m, request);
	}
	
}