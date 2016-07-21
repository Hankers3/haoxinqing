package com.aebiz.b2b2c.cms.sysback.web.livevideo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.cms.livevideo.service.LiveVideoService;
import com.aebiz.b2b2c.cms.livevideo.vo.LiveVideoModel;
import com.aebiz.b2b2c.cms.livevideo.vo.LiveVideoQueryModel;

@Controller
@RequestMapping("/sysback/livevideo")
public class LiveVideoController extends BaseController<LiveVideoModel,LiveVideoQueryModel>{
	private LiveVideoService myService;
	@Autowired
	public void  setMyService(LiveVideoService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public LiveVideoController(){
		super("cms/sysback/livevideo","LiveVideo",LiveVideoController.class);
	}
	
	/**
	 * 保存内容信息
	 * 
	 * @param m
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public String save(@ModelAttribute("m") LiveVideoModel m,
			HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files, Model model) {
		myService.uploadFile(m, files);
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		myService.create(m);
		
		return "cms/sysback/livevideo/LiveVideoSuccess";
	}
	
	
}