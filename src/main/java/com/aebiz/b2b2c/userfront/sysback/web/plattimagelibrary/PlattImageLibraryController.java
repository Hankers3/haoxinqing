package com.aebiz.b2b2c.userfront.sysback.web.plattimagelibrary;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.service.PlattImageLibraryService;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryQueryModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/plattimagelibrary")
public class PlattImageLibraryController
		extends
			BaseController<PlattImageLibraryModel, PlattImageLibraryQueryModel> {
	private PlattImageLibraryService myService;
	@Autowired
	public void setMyService(PlattImageLibraryService bs) {
		this.myService = bs;
		super.setBs(bs);
	}
	public PlattImageLibraryController() {
		super("userfront/sysback/plattimagelibrary", "PlattImageLibrary",
				PlattImageLibraryController.class);
	}

	/**
	 * 根据图片分类的uuid获取该分类下的所有的图片
	 * 
	 * @param model
	 * @param categoryUuid
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/toImageList/{categoryUuid}/{nowPage}/{pageShow}/{type}", method = RequestMethod.GET)
	public String toImageList(Model model,
			@PathVariable(value = "categoryUuid") String categoryUuid,@PathVariable(value = "nowPage") String nowPage,@PathVariable(value = "pageShow") String pageShow,@PathVariable(value = "type") String type,
			HttpServletRequest request) {
		int count = myService.getPlattImageLibraryModelsByCategoryUuid(categoryUuid);
		
		BaseWebModel wm = new BaseWebModel();
		wm.setNowPage(Integer.parseInt(nowPage));
		wm.setPageShow(Integer.parseInt(pageShow));
		wm.setTotalNum(count);
		
		List<PlattImageLibraryModel> imageList = myService
				.getPlattImageLibraryModelsByCategoryUuid(categoryUuid,wm.getFromNum(),Integer.parseInt(pageShow));
		
		if (imageList != null && imageList.size() > 0) {
			model.addAttribute("imageList", imageList);
			wm.setTotalNum(count);
			wm.setRows(imageList);
			
			model.addAttribute("wm", wm);
		}
		model.addAttribute("categoryUuid", categoryUuid);
		model.addAttribute("type", type);
		
		

		return "userfront/sysback/plattimagelibrary/PlattImageLibraryRight";
	}	
	/**
	 * 根据图片分类的uuid获取该分类下的所有的图片
	 * 
	 * @param model
	 * @param categoryUuid
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/toImage/{categoryUuid}/{nowPage}/{pageShow}/{type}", method = RequestMethod.GET)
	public String toImage(Model model,
			@PathVariable(value = "categoryUuid") String categoryUuid,@PathVariable(value = "nowPage") String nowPage,@PathVariable(value = "pageShow") String pageShow,@PathVariable(value = "type") String type,
			HttpServletRequest request) {
		int count = myService.getPlattImageLibraryModelsByCategoryUuid(categoryUuid);
		
		BaseWebModel wm = new BaseWebModel();
		wm.setNowPage(Integer.parseInt(nowPage));
		wm.setPageShow(Integer.parseInt(pageShow));
		wm.setTotalNum(count);
		
		List<PlattImageLibraryModel> imageList = myService
				.getPlattImageLibraryModelsByCategoryUuid(categoryUuid,wm.getFromNum(),Integer.parseInt(pageShow));
		
		if (imageList != null && imageList.size() > 0) {
			model.addAttribute("imageList", imageList);
			wm.setTotalNum(count);
			wm.setRows(imageList);
			
			model.addAttribute("wm", wm);
		}
		model.addAttribute("categoryUuid", categoryUuid);
		model.addAttribute("type", type);
		
		

		return "userfront/sysback/plattimagelibrary/PlattImageLibrary";
	}

	/**
	 * 上传图片
	 * 
	 * @param myfiles
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/uploadFile/{categoryUuid}"}, method = {RequestMethod.POST})
	@ResponseBody
	public String uploadFile(@RequestParam MultipartFile[] myfiles,@PathVariable("categoryUuid") String categoryUuid,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//调用图片上传方法
		PlattImageLibraryModel image = myService.updloadImage(myfiles,categoryUuid);
		
		return	JSON.toJSONString(image);
	}
	
	/**
	 * 删除图片
	 * @param uuid
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception 
	 * void
	 */
	@RequestMapping(value = {"/deleteImage"}, method = {RequestMethod.GET})
	@ResponseBody
	public String deleteImage(@RequestParam("uuid") String uuid,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> uuidList = new ArrayList<String>();
		uuidList.add(uuid);
		myService.deletes(uuidList);
		
		return "success";
	}
	
	/**
	 * 重命名图片
	 * @param uuid
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * String
	 */
	@RequestMapping(value = {"/rename"}, method = {RequestMethod.GET})
	@ResponseBody
	public String rename(@RequestParam("uuid") String uuid,@RequestParam("newName") String newName,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PlattImageLibraryModel image = myService.getByUuid(uuid);
		
		if(image != null){
			image.setImageName(newName);
			myService.updateCell(image, "imageName");
		}
				
		return "success";
	}
}