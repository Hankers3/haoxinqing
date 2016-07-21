package com.aebiz.b2b2c.websiteoperation.sysback.web.versionupdate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.websiteoperation.versionupdate.service.VersionUpdateService;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateModel;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateQueryModel;

@Controller
@RequestMapping("/sysback/versionupdate")
public class VersionUpdateController extends BaseController<VersionUpdateModel,VersionUpdateQueryModel>{
	private VersionUpdateService myService;
	@Autowired
	public void  setMyService(VersionUpdateService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	
	/* 文件上传 */
	@Autowired
	private FileUploadHelper fileUpload;

	
	public VersionUpdateController(){
		super("websiteoperation/sysback/versionupdate","VersionUpdate",VersionUpdateController.class);
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
	public String save(@ModelAttribute("m") VersionUpdateModel m,
			HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files, Model model) {
		myService.uploadFile(m, files);
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		myService.create(m);
		
		return "websiteoperation/sysback/versionupdate/VersionUpdateSuccess";
	}
	
	
}