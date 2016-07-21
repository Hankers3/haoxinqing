package com.aebiz.b2b2c.cms.sysback.web.content;

import java.util.ArrayList;
import java.util.List;

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

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentQueryModel;
import com.aebiz.b2b2c.cms.content.vo.ContentSortEnum;
import com.aebiz.b2b2c.cms.content.vo.CountryEnum;
import com.aebiz.b2b2c.cms.content.vo.IllnessEnum;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;

@Controller
@RequestMapping("/sysback/content")
public class ContentController extends
		BaseController<ContentModel, ContentQueryModel> {
	private ContentService myService;

	/* 文件上传 */
	@Autowired
	private FileUploadHelper fileUpload;

	@Autowired
	private ContentCategoryService contentCategoryService;

	@Autowired
	public void setMyService(ContentService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ContentController() {
		super("cms/sysback/content", "Content", ContentController.class);
	}

	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		List<ContentCategoryModel> categorys = contentCategoryService.getAll();
		if (categorys != null && categorys.size() > 0) {
			model.addAttribute("categorys", categorys);
		}
	}

	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		
		List<ContentCategoryModel> categorys = contentCategoryService.getAll();
		
		model.addAttribute("categorys", categorys);
		
		//病例
		sendInellssToPage(model);
		
		//国家
		sendCountryToPage(model);
		
		//类别
		sendSortToPage(model);
	}
	
	/**
	 * 跳转到文章添加页面
	 */
	@Override
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(Model model, HttpServletRequest request) {
		preparedAddData(model, request);
		//获取新增内容分类
		String categoryType = request.getParameter("categoryType");
		//患教分类
		if(!StringUtil.isEmpty(categoryType)&&"1".equals(categoryType)){
			model.addAttribute("categoryType", categoryType);
		}else{
			model.addAttribute("categoryType", "2");
		}
		return "cms/sysback/content/ContentAdd";
	}
	
	@Override
	@RequestMapping(value = "/toUpdate/{uuid}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		preparedUpdateData(model, request);
		
		ContentModel m = myService.getByUuid(uuid);
		model.addAttribute("m", m);
		//获取新增内容分类
		String categoryType = request.getParameter("categoryType");
		//患教分类
		if(!StringUtil.isEmpty(categoryType)&&"1".equals(categoryType)){
			model.addAttribute("categoryType", categoryType);
		}else{
			model.addAttribute("categoryType", "2");
		}
		return "cms/sysback/content/ContentUpdate";
	}

	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		List<ContentCategoryModel> categorys = contentCategoryService.getAll();
		model.addAttribute("categorys", categorys);
		//病例
		sendInellssToPage(model);
		//国家
		sendCountryToPage(model);
		//类别
		sendSortToPage(model);
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
	public String save(@ModelAttribute("m") ContentModel m,
			HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files, Model model) {
		myService.uploadImage(m, files);
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		myService.create(m);
		
		String categoryType = request.getParameter("categoryType");
		if(!StringUtil.isEmpty(categoryType)&&"1".equals(categoryType)){
			model.addAttribute("categoryType", categoryType);
		}else{
			model.addAttribute("categoryType", "2");
		}
		
		return "cms/sysback/content/ContentSuccess";
	}

	/**
	 * 更新内容信息
	 * 
	 * @param m
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String update(@ModelAttribute("m") ContentModel m,
			HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files, Model model) {
		// 更新数据
		m = myService.uploadImage(m, files);
		
		String categoryType = request.getParameter("categoryType");
		if(!StringUtil.isEmpty(categoryType)&&"1".equals(categoryType)){
			model.addAttribute("categoryType", categoryType);
		}else{
			model.addAttribute("categoryType", "2");
		}
		
		myService.update(m);
		// 跳转页面
		return "cms/sysback/content/ContentSuccess";
	}
	/**
	 * 跳转到今日管理页面
	 * @return
	 */
	@RequestMapping(value = "/contentRecommendList",method = RequestMethod.GET)
	public String contentRecommendList(Model model,
			HttpServletRequest request){
		 
		return "cms/sysback/content/ContentRecommendList";
	}
	
	
	
	/**
	 * 跳转到添加轮播图片页面
	 * @return
	 */
	@RequestMapping(value = "/addContentPic",method = RequestMethod.GET)
	public String addContentPic(Model model,
			HttpServletRequest request){
		 
		return "cms/sysback/content/ContentPicAdd";
	}
	
	
	
	/**
	 * 添加轮播图片
	 * @return
	 */
	 @RequestMapping(value={"/addContent"}, method = RequestMethod.POST)
	 
	   public String addContent(Model model,
			   @ModelAttribute("m") ContentModel m,
			   @RequestParam(value="introduction") String introduction, 
			   @RequestParam(value="contentTitle") String contentTitle,
			   @RequestParam(value="contentURL") String contentURL,
			   @RequestParam(value = "files", required = false) MultipartFile[] files,
			   HttpServletRequest request)
	     {
		    myService.uploadImage(m, files);
			m.setIntroduction(introduction);
			m.setContentTitle(contentTitle);
			m.setUrl(contentURL);
			myService.create(m);
		   return this.contentRecommendList(model, request);
		 
	   }
	 
	 
	 
		/**
		 * 跳转到添加轮播图片页面
		 * @return
		 */
		@RequestMapping(value = "/updateContentPic/{uuid}",method = RequestMethod.GET)
		public String updateContentPic(Model model,
				@PathVariable("uuid") String uuid,
				HttpServletRequest request){
			 ContentModel m = myService.getByUuid(uuid);
			 model.addAttribute("m", m);
			return "cms/sysback/content/ContentPicUpdate";
		}
		/**
		 * 修改轮播图片
		 * 
		 * @param m
		 * @param request
		 * @param files
		 * @return
		 */
		@RequestMapping(value = "/contentUpdate", method = RequestMethod.POST)
		public String contentUpdate(
				@ModelAttribute("m") ContentModel m,
				@RequestParam(value="introduction") String introduction, 
				@RequestParam(value="contentTitle") String contentTitle,
				@RequestParam(value="contentNo") String contentNo,
				@RequestParam(value="image") String image,
				@RequestParam(value="createTime") String createTime,
				@RequestParam(value="contentURL") String contentURL,
				@RequestParam(value="uuid") String uuid,
				HttpServletRequest request,
				@RequestParam(value = "files", required = false) MultipartFile[] files,
				Model model) {
				// 上传图片
				this.myService.uploadImage(m, files);
				m.setIntroduction(introduction);
				m.setContentTitle(contentTitle);
				m.setContentNo(contentURL);
				m.setImage(image);
				m.setCreateTime(createTime);
				m.setUrl(contentURL);
				m.setUuid(uuid);
				myService.update(m);
				// 跳转页面
				return this.contentRecommendList(model, request);
		}
		
		/**
		 * 跳转到患教类页面
		 * @param model
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/toContentPatientList",method = RequestMethod.GET)
		public String toContentPatientList(Model model,
				HttpServletRequest request){
			 
			return "cms/sysback/content/ContentPatientList";
		}
		
	/************************************************私有方法******************************************************/
	/**
	 * 疾病类型
	 * @param model
	 */
	private void sendInellssToPage(Model model) {
		
		List<DataTablesPageParam> stateList = new ArrayList<DataTablesPageParam>();

		for (IllnessEnum spe : IllnessEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());
			stateList.add(dpp);
		}

		model.addAttribute("illness", stateList);
	}
	 
	/**
	 * 国家信息
	 * @param model
	 */
	private void sendCountryToPage(Model model) {
		List<DataTablesPageParam> stateList = new ArrayList<DataTablesPageParam>();

		for (CountryEnum spe : CountryEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());
			stateList.add(dpp);
		}

		model.addAttribute("countrys", stateList);
	}
	
	/**
	 * 类别信息
	 * @param model
	 */
	private void sendSortToPage(Model model) {
		List<DataTablesPageParam> stateList = new ArrayList<DataTablesPageParam>();

		for (ContentSortEnum spe : ContentSortEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();
			dpp.setName(spe.getName());
			dpp.setValue(spe.getValue());
			stateList.add(dpp);
		}

		model.addAttribute("sorts", stateList);
		
	}

}