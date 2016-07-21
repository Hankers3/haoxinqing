package com.aebiz.b2b2c.product.sysback.web.productcategoryfront;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.service.FrontProductCategoryBrandRelService;
import com.aebiz.b2b2c.product.frontproductcatetemprel.service.FrontProductCateTempRelService;
import com.aebiz.b2b2c.product.productbrand.service.ProductBrandService;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productcategoryfront.service.ProductCategoryFrontService;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontQueryModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.SelectAttributeJson;
import com.aebiz.b2b2c.product.productcategoryplatform.service.ProductCategoryPlatformService;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productcategoryrel.service.ProductCategoryRelService;
import com.aebiz.b2b2c.product.productcatetemprel.service.ProductCateTempRelService;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo.AttributeAndValueWebModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo.ProductCategoryFrontWebModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryplatform.vo.TreeElement;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/productcategoryfront")
public class ProductCategoryFrontController
		extends
		BaseController<ProductCategoryFrontModel, ProductCategoryFrontQueryModel> {

	/* 文件上传 */
	@Autowired
	private FileUploadHelper fileUpload;

	private ProductCategoryFrontService myService;

	@Autowired
	private ProductCategoryPlatformService platfromService;

	@Autowired
	private ProductCategoryRelService productCategoryRelService;

	@Autowired
	private ProductCateTempRelService tempRelService;



	@Autowired
	private FrontProductCateTempRelService frontCategoryAttributeRelService;


	@Autowired
	private FrontProductCategoryBrandRelService frontProductCategoryBrandRelService;

	@Autowired
	private ProductBrandService productBrandService;

	@Autowired
	public void setMyService(ProductCategoryFrontService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ProductCategoryFrontController() {
		super("product/sysback/productcategoryfront", "ProductCategoryFront",
				ProductCategoryFrontController.class);
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
		return "product/sysback/productcategoryfront/ProductCategoryList";
	}

	/**
	 * 跳转到添加分类页
	 */
	@Override
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(Model model, HttpServletRequest request) {

		return "product/sysback/productcategoryfront/ProductCategoryAdd";
	}

	/**
	 * 跳转到添加关联后台分类页
	 */
	@RequestMapping(value = "/toPlatform", method = RequestMethod.POST)
	public String toPlatform(Model model,
			@RequestParam("categoryUuid") String categoryUuid,
			HttpServletRequest request) {

		ProductCategoryFrontModel m = myService.getByUuid(categoryUuid);
		model.addAttribute("m", m);
		return "product/sysback/productcategoryfront/ProductCategoryPlatform";
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
			@ModelAttribute("m") ProductCategoryFrontModel m,
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
		// 设置分类价格属性
		String categoryPrice = (String) jsonMap.get("categoryPrice");
		if (!StringUtil.isEmpty(categoryPrice)) {
			String[] str = categoryPrice.split(",");
			m.setCategoryPrice(Arrays.asList(str));
		}

		// 设置值到model
		m.setCategoryName(categoryName.trim());
		m.setCategoryNo(categoryNo.trim());
		m.setParentUuid(parentUuid);
		m.setIcon(icon);
		if (!StringUtil.isEmpty(position)) {
			m.setPosition(Integer.parseInt(position));
		}
		m.setCategoryNote(categoryNote.trim());
		m.setCategoryUrl(categoryUrl.trim());
		if (!StringUtil.isEmpty(state)) {
			m.setState(state);
		}

		ProductCategoryFrontModel parentCategory = null;
		// 查询父分类
		if (!StringUtil.isEmpty(parentUuid)) {
			parentCategory = myService.getByUuid(parentUuid);
		}

		//生成fullpath
		if (parentCategory != null) {
			m.setFullpath(myService.getSheetNo(
					parentCategory.getFullpath(), 1));
		} else {
			m.setFullpath(myService.getSheetNo( "", 1));
		}
		
		model.addAttribute("m", m);
		// 创建分类
		myService.create(m);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
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
		return "product/sysback/productcategoryfront/BatchAddCategory";
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
		List<ProductCategoryFrontModel> categoryList = new ArrayList<ProductCategoryFrontModel>();
		for (int i = 1; i <= 10; i++) {
			String categoryName = (String) jsonMap.get("categoryName" + i);
			String enCategoryName = (String) jsonMap.get("enCategoryName" + i);
			// 判断分类名称和分类英文名称是否为空,空就继续
			if (StringUtil.isEmpty(categoryName)
					|| StringUtil.isEmpty(enCategoryName)) {
				continue;
			}

			ProductCategoryFrontModel m = new ProductCategoryFrontModel();

			m.setCategoryName(categoryName);
			m.setEnCategoryName(enCategoryName);
			m.setParentUuid(parentUuid);
			m.setState("0");
			ProductCategoryFrontModel parentCategory = null;
			if (!StringUtil.isEmpty(parentUuid)) {
				parentCategory = myService.getByUuid(parentUuid);
			}
			categoryList.add(m);
		}

		myService.addBatch(categoryList);

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
	 */
	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateCategory(Model model,
			@ModelAttribute("m") ProductCategoryFrontModel m,
			HttpServletRequest request) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();

		jsonMap = super.parsePageParam(request);
		String uuid = (String) jsonMap.get("uuid");
		String categoryName = (String) jsonMap.get("categoryName");
		String categoryNo = (String) jsonMap.get("categoryNo");
		String parentUuid = (String) jsonMap.get("parentUuid");
		String position = (String) jsonMap.get("position");
		String categoryNote = (String) jsonMap.get("categoryNote");
		String categoryUrl = (String) jsonMap.get("categoryUrl");
		String state = (String) jsonMap.get("state");
		String icon = (String) jsonMap.get("icon");

		m = myService.getByUuid(uuid);

		// 设置分类价格属性
		String categoryPrice = (String) jsonMap.get("categoryPrice");
		if (!StringUtil.isEmpty(categoryPrice)) {
			String[] str = categoryPrice.split(",");
			m.setCategoryPrice(Arrays.asList(str));
		}

		model.addAttribute("m", m);
		if (m == null) {
			reJsonMap.put("erroMassage", "noEixtUuid");
			return reJsonMap;
		}

		boolean isAddLog=false;
		
		if(!m.getCategoryName().equals(categoryName.trim())){
			isAddLog=true;
		}
		
		m.setCategoryName(categoryName.trim());
		m.setCategoryNo(categoryNo.trim());
		m.setParentUuid(parentUuid.trim());
		m.setIcon(icon);
		if (!StringUtil.isEmpty(state)) {
			m.setState(state);
		}
		if (!StringUtil.isEmpty(position)) {
			m.setPosition(Integer.parseInt(position));
		}
		m.setCategoryNote(categoryNote.trim());
		m.setCategoryUrl(categoryUrl.trim());

		myService.updateCategory(m, isAddLog);

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

	/**
	 * 跳转到成功页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toSuccess", method = RequestMethod.GET)
	public String toSuccess(Model model, HttpServletRequest request) {

		return "product/sysback/productcategoryfront/ProductCategorySuccess";
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
			Model model, HttpServletRequest request) {

		String parentUuid = key;
		// 得到下级分类
		List<TreeElement> list = getRootList(parentUuid);

		return list;
	}

	/**
	 * 构造分类树
	 * 
	 * @param key
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bulidPlatfromCategory", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeElement> bulidPlatfromCategory(
			@RequestParam(value = "key") String key,
			@RequestParam(value = "categoryUuid") String categoryUuid,
			Model model, HttpServletRequest request) {

		String parentUuid = key;
		// 得到下级分类
		List<TreeElement> list = getRootAllList(parentUuid, categoryUuid);

		return list;
	}

	/**
	 * 跳转关联属性页面
	 * 
	 * @param categoryUuid
	 *            前台分类uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toAttribute", method = RequestMethod.POST)
	public String toAttribute(
			@RequestParam(value = "categoryUuid") String categoryUuid,
			Model model, HttpServletRequest request) {
		ProductCategoryFrontWebModel web = new ProductCategoryFrontWebModel();

		List<String> categoryPlatfromUuids = productCategoryRelService
				.getPlatfromCategoryUuidsByFrontCategoryUuid(categoryUuid);
		if (categoryPlatfromUuids == null || categoryPlatfromUuids.size() == 0) {
			// TODO 没有关联后台分类
		}
		web.setFrontCategoryUuid(categoryUuid);
		List<ProductTemplateAttrModel> attributes = myService
				.canSelectAttributeList(categoryUuid, categoryPlatfromUuids);
		List<AttributeAndValueWebModel> attributeAndValues = myService
				.buildAttributeWebList(attributes, categoryUuid);

		web.setAttributes(attributeAndValues);
		model.addAttribute("web", web);

		return "product/sysback/productcategoryfront/ProductAttribute";
	}

	@RequestMapping(value = "/saveAttribute", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveAttribute(Model model,
			HttpServletRequest request) {
		Map<String, Object> reJsonMap = new HashMap<String, Object>();

		String frontCategoryUuid = request.getParameter("frontCategoryUuid");
		if (StringUtil.isEmpty(frontCategoryUuid)) {
			reJsonMap.put("noSelectCategory", Boolean.valueOf(true));
			return reJsonMap;
		}

		String attributeUuids = request.getParameter("attributeUuids");

		if (StringUtil.isEmpty(attributeUuids)) {
			reJsonMap.put("noSelect", Boolean.valueOf(true));
			return reJsonMap;
		}

		List<SelectAttributeJson> attributesJson = new ArrayList<SelectAttributeJson>();

		String[] attributeUuid = attributeUuids.split(",");
		for (String temp : attributeUuid) {
			if (StringUtil.isEmpty(temp)) {
				continue;
			}
			/* 获取页面数据 start */
			String selectValueUuids = "";
			String type = request.getParameter("type" + temp);
			if (AttributeAndValueWebModel.NO.equals(type)) {
				selectValueUuids = request.getParameter("noValueSelect" + temp);
			} else {
				selectValueUuids = request
						.getParameter("yesValueSelect" + temp);
			}

			List<String> valueNames = getSelectValueList(request, temp, type,
					selectValueUuids);
			String attributeName = request.getParameter("attributeName" + temp);
			String attributeEnName = request.getParameter("attributeEnName"
					+ temp);
			String attributeUnit = request.getParameter("attributeUnit" + temp);
			String position = request.getParameter("position" + temp);
			int positionInt = 0;
			if (!StringUtil.isEmpty(position)) {
				positionInt = Integer.parseInt(position);
			}
			/* 获取页面数据 end */

			/* 组织json对象 start */
			SelectAttributeJson attributeJson = new SelectAttributeJson();
			attributeJson.setType(type);
			attributeJson.setAttributeEnName(attributeEnName);
			attributeJson.setAttributeName(attributeName);
			attributeJson.setAttributeUuid(temp);
			attributeJson.setSelectValue(valueNames);
			attributeJson.setUnit(attributeUnit);
			attributeJson.setPosition(positionInt);
			/* 组织json对象 end */

			attributesJson.add(attributeJson);
		}

		System.out.println(JSON.toJSONString(attributesJson));
		String temp = JSON.toJSONString(attributesJson);

		
		frontCategoryAttributeRelService.saveAttribute(frontCategoryUuid, temp);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}

	private List<String> getSelectValueList(HttpServletRequest request,
			String attributeUuid, String type, String selectValueUuids) {
		List<String> list = new ArrayList<String>();
		if (!StringUtil.isEmpty(selectValueUuids)) {
			String[] selectValueUuid = selectValueUuids.split(",");
			for (String temp : selectValueUuid) {
				if (StringUtil.isEmpty(temp)) {
					continue;
				}
				if (AttributeAndValueWebModel.NO.equals(type)) {
					String noValueName = request.getParameter("noValueName"
							+ attributeUuid + temp);
					list.add(noValueName);
				} else {
					String noValueName = request.getParameter("yesValueName"
							+ attributeUuid + temp);
					list.add(noValueName);
				}

			}
		}

		return list;
	}

	/**
	 * 前台关联后台分类
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batchRelCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> batchRelCategory(
			Model model,
			@RequestParam("frontCategoryUuid") String frontCategoryUuid,
			@RequestParam("selectCategoryUuids") List<String> selectCategoryUuids,
			HttpServletRequest request) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();

		productCategoryRelService.batchAdd(selectCategoryUuids,
				frontCategoryUuid);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}

	/**
	 * 获取平台分类
	 * 
	 * @param parenUuid
	 * @return
	 */
	public List<TreeElement> getRootAllList(String parenUuid,
			String categoryUuid) {
		List<TreeElement> nodes = new ArrayList<TreeElement>();
		List<ProductCategoryPlatformModel> list = platfromService
				.getSubProductCategoryByParentUuid(parenUuid);
		if (list == null || list.size() == 0) {
			return nodes;
		}
		for (int i = 0; i < list.size(); i++) {
			ProductCategoryPlatformModel model = list.get(i);
			TreeElement ele = new TreeElement();
			ele.setKey(model.getUuid());
			ele.setTitle(model.getCategoryName());
			List<ProductCategoryPlatformModel> sublist = platfromService
					.getSubProductCategoryByParentUuid(model.getUuid());
			if (sublist != null && sublist.size() > 0) {
				// ele.setIsLazy(true);
				List<TreeElement> children = getRootAllList(model.getUuid(),
						categoryUuid);
				ele.setChildren(children);
			}
			if (productCategoryRelService.isHasRelCategory(categoryUuid,
					model.getUuid())) {
				ele.setSelect(true);
			}

			ele.setIsFolder(true);
			ele.setExpand(true);
			ele.setHideCheckbox(false);
			nodes.add(ele);
		}
		return nodes;

	}

	/**
	 * 得到下级分类列表根据父分类uuid
	 * 
	 * @param parenUuid
	 * @return
	 */
	public List<TreeElement> getRootList(String parenUuid) {
		List<TreeElement> nodes = new ArrayList<TreeElement>();
		List<ProductCategoryFrontModel> list = myService
				.getSubProductCategoryByParentUuid(parenUuid);
		if (list == null || list.size() == 0) {
			return nodes;
		}
		for (int i = 0; i < list.size(); i++) {
			ProductCategoryFrontModel model = list.get(i);
			TreeElement ele = new TreeElement();
			ele.setKey(model.getUuid());
			ele.setTitle(model.getCategoryName());
			List<ProductCategoryFrontModel> sublist = myService
					.getSubProductCategoryByParentUuid(model.getUuid());
			if (sublist != null && sublist.size() > 0) {
				ele.setIsLazy(true);
			}
			ele.setIsFolder(true);
			ele.setExpand(false);
			ele.setHideCheckbox(false);
			nodes.add(ele);
		}
		return nodes;
	}
}