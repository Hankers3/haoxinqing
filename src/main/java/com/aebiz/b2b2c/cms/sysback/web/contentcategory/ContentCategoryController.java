package com.aebiz.b2b2c.cms.sysback.web.contentcategory;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;
import com.aebiz.b2b2c.cms.sysback.web.contentcategory.vo.SelectCategoryModel;
import com.aebiz.b2b2c.cms.sysback.web.contentcategory.vo.TreeElement;

@Controller
@RequestMapping("/sysback/contentcategory")
public class ContentCategoryController extends
		BaseController<ContentCategoryModel, ContentCategoryQueryModel> {
	/* 文件上传 */
	@Autowired
	private FileUploadHelper fileUpload;
	
	private ContentCategoryService myService;

	@Autowired
	public void setMyService(ContentCategoryService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ContentCategoryController() {
		super("cms/contentcategory", "ContentCategory",
				ContentCategoryController.class);
	}

	/**
	 * 跳转到分类管理页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toCategory", method = RequestMethod.GET)
	public String toCategory(Model model, HttpServletRequest request) {
		return "cms/sysback/contentcategory/ContentCategoryList";
	}

	/**
	 * 跳转到成功页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toSuccess", method = RequestMethod.GET)
	public String toSuccess(Model model, HttpServletRequest request) {

		return "cms/sysback/contentcategory/ContentCategorySuccess";
	}

	/**
	 * 跳转到批量添加页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toBatchAdd", method = RequestMethod.GET)
	public String toBatchAdd(Model model, HttpServletRequest request) {

		return "cms/sysback/contentcategory/BatchAddCategory";
	}

	/**
	 * 构造分类树
	 * 
	 * @param key
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getNodes", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeElement> getNodes(@RequestParam(value = "key") String key,
			@RequestParam(value = "categoryType") String categoryType,
			Model model, HttpServletRequest request) {

		String parentUuid = key;
		// 得到下级分类
		List<TreeElement> list = getRootList(parentUuid,categoryType);
		
		return list;
	}

	/**
	 * 得到下级分类列表根据父分类uuid
	 * 
	 * @param parenUuid
	 * @return
	 */
	public List<TreeElement> getRootList(String parenUuid,String categoryType) {
		List<TreeElement> nodes = new ArrayList<TreeElement>();
		List<ContentCategoryModel> list = null;
		if(!StringUtil.isEmpty(categoryType)){
			 list = myService.getSubContentCategoryByParentUuid(parenUuid,categoryType);
		}else{
			 list = myService.getSubContentCategoryByParentUuid(parenUuid);
		}
		
		if (list == null || list.size() == 0) {
			return nodes;
		}
		for (int i = 0; i < list.size(); i++) {
			ContentCategoryModel model = list.get(i);
			TreeElement ele = new TreeElement();
			ele.setKey(model.getUuid());
			ele.setTitle(model.getCategoryName());
			List<ContentCategoryModel> sublist = myService
					.getSubContentCategoryByParentUuid(model.getUuid());
			if (sublist != null && sublist.size() > 0) {
				ele.setIsLazy(true);
			}
			ele.setIsFolder(true);
			ele.setExpand(false);
			nodes.add(ele);
		}
		return nodes;
	}
	/**
	 * 跳转到添加分类页
	 */
	@Override
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(Model model, HttpServletRequest request) {
		
		return "cms/sysback/contentcategory/ContentCategoryAdd";
	}

	/**
	 * 跳转到分类更新页
	 */
	@Override
	@RequestMapping(value = "/toUpdate/{uuid}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		ContentCategoryModel m = myService.getByUuid(uuid);

		model.addAttribute("m", m);

		return "cms/sysback/contentcategory/ContentCategoryUpdate";
	}

	/**
	 * 跳转患教类型分类页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toPatientEducationCategory", method = RequestMethod.GET)
	public String toPatientEducationCategory(Model model, HttpServletRequest request) {
		
		return "cms/sysback/contentcategory/patientEducationCategoryList";
	}
	
	/**
	 * 跳转患教类型分类页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toAddPatientEducationCategory", method = RequestMethod.GET)
	public String toAddPatientEducationCategory(Model model, HttpServletRequest request) {
		
		return "cms/sysback/contentcategory/patientEducationCategoryListAdd";
	}
	
	
	/**
	 * 跳转到分类修改页面
	 */

	@RequestMapping(value = "/toUpdatePatientEducationCategory/{uuid}", method = RequestMethod.GET)
	public String toUpdatePatientEducationCategory(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		ContentCategoryModel m = myService.getByUuid(uuid);

		model.addAttribute("m", m);

		return "cms/sysback/contentcategory/patientEducationCategoryListUpdate";
	}
	

	/**
	 * 添加分类
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> save(Model model,
			@ModelAttribute("m") ContentCategoryModel m,
			HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();
		jsonMap = super.parsePageParam(request);
		// 获取添加分类各个字段信息
		String categoryName = (String) jsonMap.get("categoryName");
		String categoryNo = (String) jsonMap.get("categoryNo");
		String parentUuid = (String) jsonMap.get("parentUuid");
		String position = (String) jsonMap.get("position");
		String categoryNote = (String) jsonMap.get("categoryNote");
		String categoryUrl = (String) jsonMap.get("categoryUrl");
		String state = (String) jsonMap.get("state");
		String icon = (String) jsonMap.get("icon");
		String categoryType = (String) jsonMap.get("categoryType");
		
		// 设置值到model
		m.setCategoryName(categoryName);
		m.setCategoryNo(categoryNo);
		m.setParentUuid(parentUuid);
		m.setImage(icon);
		m.setCategoryType(categoryType);
		m.setPosition(Integer.parseInt(position));

		m.setCategoryNote(categoryNote);
		m.setCategoryUrl(categoryUrl);
		m.setState(state);


		ContentCategoryModel parentCategory = null;
		// 查询父分类
		if (!StringUtil.isEmpty(parentUuid)) {
			parentCategory = myService.getByUuid(parentUuid);
		}
		// 生成fullpath
		if (parentCategory != null) {
			m.setFullpath(myService.getSheetNo(parentCategory.getFullpath(), 1));
		} else {
			m.setFullpath(myService.getSheetNo("", 1));
		}

		model.addAttribute("m", m);
		// 创建分类
		myService.create(m);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}

	/**
	 * 更新分类
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateCategory(Model model,
			@ModelAttribute("m") ContentCategoryModel m,
			HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();

		jsonMap = super.parsePageParam(request);
		String uuid = (String) jsonMap.get("uuid");
		String categoryName = (String) jsonMap.get("categoryName");
		String categoryNote = (String) jsonMap.get("categoryNote");
		String categoryNo = (String) jsonMap.get("categoryNo");
		String parentUuid = (String) jsonMap.get("parentUuid");
		String position = (String) jsonMap.get("position");
		String categoryUrl = (String) jsonMap.get("categoryUrl");
		String state = (String) jsonMap.get("state");
		String icon = (String) jsonMap.get("icon");

		m = myService.getByUuid(uuid);

		model.addAttribute("m", m);
		if (m == null) {
			reJsonMap.put("erroMassage", "noEixtUuid");
			return reJsonMap;
		}

		m.setCategoryName(categoryName);
		m.setCategoryNo(categoryNo);
		m.setParentUuid(parentUuid);
		m.setImage(icon);
		if (!StringUtil.isEmpty(state)) {
			m.setState(state);
		}
		if (!StringUtil.isEmpty(position)) {
			m.setPosition(Integer.parseInt(position));
		}
		m.setCategoryNote(categoryNote);
		m.setCategoryUrl(categoryUrl);

		myService.update(m);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}

	/**
	 * 批量添加分类
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addBatch", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addBatch(Model model, HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();
		jsonMap = super.parsePageParam(request);
		String parentUuid = (String) jsonMap.get("parentUuid");
		List<ContentCategoryModel> categoryList = new ArrayList<ContentCategoryModel>();
		for (int i = 1; i <= 10; i++) {
			String categoryName = (String) jsonMap.get("categoryName" + i);
			String categoryNo = (String) jsonMap.get("categoryNo" + i);
			// 判断分类名称和分类编号是否为空,空就继续
			if (StringUtil.isEmpty(categoryName)
					|| StringUtil.isEmpty(categoryNo)) {
				continue;
			}

			ContentCategoryModel m = new ContentCategoryModel();

			m.setCategoryName(categoryName);
			m.setCategoryNo(categoryNo);
			m.setParentUuid(parentUuid);
			m.setState("0");
			ContentCategoryModel parentCategory = null;
			if (!StringUtil.isEmpty(parentUuid)) {
				parentCategory = myService.getByUuid(parentUuid);
			}
			if (parentCategory != null) {
				m.setFullpath(myService.getSheetNo(
						parentCategory.getFullpath(), 1));
			} else {
				m.setFullpath(myService.getSheetNo("", 1));
			}
			categoryList.add(m);

		}
		myService.addBatch(categoryList);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}
	
	/**
	 * 上传分类图标图片，将图片名称传到页面
	 * 
	 * @param myfiles
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/uploadFile" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String uploadFile(@RequestParam MultipartFile[] myfiles,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 校验文件是否已经上传
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		String picName = "";
		for (MultipartFile myfile : myfiles) {
			picName = System.currentTimeMillis() + "picture";
			fileNameList.add(picName);
			fileList.add(myfile);
		}

		// 是否上传
		if (fileList != null && fileList.size() > 0) {
			MultipartFile[] newfiles = new MultipartFile[fileList.size()];
			String[] fileNames = new String[fileList.size()];

			for (int i = 0; i < fileList.size(); i++) {
				newfiles[i] = fileList.get(i);
				fileNames[i] = fileNameList.get(i);
			}
			// 上传文件
			fileUpload.uploadFiles(newfiles, fileNames);
		}

		PrintWriter out = response.getWriter();

		out.print(picName);

		return null;
	}
	
	private List<SelectCategoryModel> getSelectCategoryModels(List<ContentCategoryModel> list,List<String> selectUuid){
		List<SelectCategoryModel> tempList = new ArrayList<SelectCategoryModel>();
		if(list!=null && list.size()>0){
			for (ContentCategoryModel m : list) {
				SelectCategoryModel newM=new SelectCategoryModel();
				newM.setCategoryUuid(m.getUuid());
				newM.setCategoryName(m.getCategoryName());
				newM.setParentUuid(m.getParentUuid());
				if(selectUuid.contains(m.getUuid())){
					newM.setSelected("selected");
				}
				tempList.add(newM);
			}
		}
		
		return tempList;
	}
	
	
	@RequestMapping(value = "/showSubCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> showSubCategory(Model model,@RequestParam(value = "leafUuid") String leafUuid,
			HttpServletRequest request) {
		List<String> selectUuid=new ArrayList<String>();
		Map<String, Object> map=new HashMap<String, Object>();
		
		if(!StringUtil.isEmpty(leafUuid)){
			myService.getSelectCategoryUuid(leafUuid, selectUuid);
			System.out.println(selectUuid);
			
			for (int i=0;i<selectUuid.size();i++) {
				String uuid=selectUuid.get(i);
				List<ContentCategoryModel> list=myService.getSameLevelCategoryByUuid(uuid);
				List<SelectCategoryModel> showList=this.getSelectCategoryModels(list, selectUuid);
				map.put("level"+i, showList);
			}
			
			if(!StringUtil.isEmpty(leafUuid)){
				List leafList = myService
						.getSubContentCategoryByParentUuid(leafUuid);
				List<SelectCategoryModel> showList=this.getSelectCategoryModels(leafList, selectUuid);
				map.put("level"+selectUuid.size(), showList);
			}
			
		}
		
		List<ContentCategoryModel> list=myService.getSubContentCategoryByParentUuid("");
		List<SelectCategoryModel> showList=this.getSelectCategoryModels(list, selectUuid);
		map.put("level0", showList);
		
		map.put("rsp", Boolean.valueOf(true));
		return map;
	}
	
	@RequestMapping(value = "/changeSubCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> changeSubCategory(Model model,@RequestParam(value = "level") String level,@RequestParam(value = "parentUuid") String parentUuid,
			HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<ContentCategoryModel> leafList = myService.getSubContentCategoryByParentUuid(parentUuid);
		List<String> selectUuid=new ArrayList<String>();
		
		if(!StringUtil.isEmpty(parentUuid)){
			List<SelectCategoryModel> showList=this.getSelectCategoryModels(leafList, selectUuid);
			map.put("showList", showList);
		}
		if(!StringUtil.isEmpty(level)){
			String replitStr=level.substring(5, level.length());
			if(!StringUtil.isEmpty(replitStr)){
				int index=Integer.parseInt(replitStr);
				map.put("level", "level"+(++index));
			}
		}
		
		map.put("rsp", Boolean.valueOf(true));
		return map;
	}
}