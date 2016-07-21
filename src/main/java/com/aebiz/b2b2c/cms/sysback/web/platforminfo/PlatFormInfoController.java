package com.aebiz.b2b2c.cms.sysback.web.platforminfo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoQueryModel;

@Controller
@RequestMapping("/sysback/platforminfo")
public class PlatFormInfoController extends BaseController<PlatFormInfoModel,PlatFormInfoQueryModel>{
	private PlatFormInfoService myService;
	@Autowired
	private FileUploadHelper fileUpload;
	@Autowired
	public void  setMyService(PlatFormInfoService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public PlatFormInfoController(){
		super("/cms/sysback/platforminfo","PlatFormInfo",PlatFormInfoController.class);
	}
	
	@RequestMapping(value={"/doAdd"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String add(Model model, @ModelAttribute("m") PlatFormInfoModel m, HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files,	
			@RequestParam("filesImg") MultipartFile[] filesImg){
	  if (!checkAdd(model, m, request)) {
	    request.setAttribute("ShowMsgs", new HashMap());
	    return (String)request.getAttribute("ERROR_BACK_URL");
	  }
	  cleanQuerySession(request);
	  //上传头像
	  myService.uploadImage(m, filesImg);
	  //上传视频
	  this.myService.uploadVideo(m, files);
	  this.myService.create(m);
	  //上传后的视频在这里进行转码
	  myService.TranscodingByContentId(m.getUuid());
	  return "/cms/sysback/platforminfo/PlatFormInfoSuccess";
	}
	
	@RequestMapping(value={"/doUpdate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public String update(Model model, @ModelAttribute("m") PlatFormInfoModel m, HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files,
			@RequestParam("filesImg") MultipartFile[] filesImg){
	  if (!checkUpdate(model, m, request)) {
	    request.setAttribute("ShowMsgs", new HashMap());
	    return (String)request.getAttribute("ERROR_BACK_URL");
	  }
	  cleanQuerySession(request);
	  //上传头像
	  myService.uploadImage(m, filesImg);
	  //上传视频
	  this.myService.uploadVideo(m, files);
	  this.myService.update(m);
	  myService.TranscodingByContentId(m.getUuid());
	  return "/cms/sysback/platforminfo/PlatFormInfoSuccess";
	}
	
	@RequestMapping(value = { "/uploadFile" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Map<String, String> uploadFile(@RequestParam MultipartFile[] myfiles,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> fileNameList = new ArrayList();
		List<MultipartFile> fileList = new ArrayList();
		List<FileModel> files = new ArrayList<FileModel>();
		String picName = "";
		for (MultipartFile myfile : myfiles) {
			picName = System.currentTimeMillis() + "picture";
			fileNameList.add(picName);
			fileList.add(myfile);
		}

		if ((fileList != null) && (fileList.size() > 0)) {
			MultipartFile[] newfiles = new MultipartFile[fileList.size()];
			String[] fileNames = new String[fileList.size()];

			for (int i = 0; i < fileList.size(); i++) {
				newfiles[i] = ((MultipartFile) fileList.get(i));
				fileNames[i] = ((String) fileNameList.get(i));
			}
			//上传文件
			fileUpload.uploadFiles(newfiles, fileNames);
		}
		
		PrintWriter out = response.getWriter();

		out.print(picName);

		return null;
	}
}