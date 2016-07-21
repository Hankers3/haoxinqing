package com.aebiz.b2b2c.visitprecept.sysback.web.visitpreceptextend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.service.VisitPreceptExtendService;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendQueryModel;

@Controller
@RequestMapping("/sysback/visitpreceptextend")
public class VisitPreceptExtendController extends BaseController<VisitPreceptExtendModel,VisitPreceptExtendQueryModel>{
	private VisitPreceptExtendService myService;
	@Autowired
	public void  setMyService(VisitPreceptExtendService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public VisitPreceptExtendController(){
		
		super("/visitprecept/sysback/visitpreceptextend","VisitPreceptExtend",VisitPreceptExtendController.class);
	}
	
	/**
	 * 查看方案管理扩展字段详细信息
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toList/{uuid}")
	public String toList(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 通过id获得model
		model.addAttribute("uuid", uuid);
		return super.toList(model, request);
	}
}