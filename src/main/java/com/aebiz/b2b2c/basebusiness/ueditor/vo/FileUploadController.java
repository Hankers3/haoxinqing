package com.aebiz.b2b2c.basebusiness.ueditor.vo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.basebusiness.ueditor.ActionEnter;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;

/**
 * 
 * 品牌管理 控制器
 * @author huangpinpin
 *
 */
@Controller
@RequestMapping("/ueditor")
public class FileUploadController  {
	
	@Autowired
	private FileUploadHelper fileUpload ;
	
	
	@RequestMapping(value = { "/toUpload" })
	@ResponseBody
	public String toUploads(HttpServletRequest request ) {
		String rootPath = request.getRealPath("/");
 
		return new ActionEnter( request, rootPath ).exec();
	}
	
	
	
}