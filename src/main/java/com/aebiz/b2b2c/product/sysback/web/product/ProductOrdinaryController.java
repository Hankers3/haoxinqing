package com.aebiz.b2b2c.product.sysback.web.product;

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
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productcategoryplatform.service.ProductCategoryPlatformService;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productmain.vo.ProductImportConst;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmainaudit.service.ProductMainAuditService;
import com.aebiz.b2b2c.product.productmainbaseprice.service.ProductMainBasePriceService;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productmaindescription.service.ProductMainDescriptionService;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.product.productordinary.service.ProductOrdinaryService;
import com.aebiz.b2b2c.product.productpub.service.ProductService;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductType;
import com.aebiz.b2b2c.product.sysback.web.product.vo.FieldReason;
import com.aebiz.b2b2c.product.sysback.web.product.vo.ProductWebModel;
import com.aebiz.b2b2c.product.utils.ExcelUtils;

@Controller
@RequestMapping("/sysback/product")
public class ProductOrdinaryController extends
		BaseController<ProductModel, ProductQueryModel> {
	private ProductOrdinaryService myService;

	/* 主商品的service */
	@Autowired
	private ProductMainService mainService;
	
	/* 药品描述的service */
        @Autowired
        private ProductMainDescriptionService productMainDescriptionService;

	/* 商品审核service */
	@Autowired
	private ProductMainAuditService auditService;

	/* 商品公用service */
	@Autowired
	private ProductService productService;

	/* 商品基础价格service */
	@Autowired
	private ProductMainBasePriceService priceService;

	/* 文件上传service */
	@Autowired
	private FileUploadHelper fileUpload;

	/* 文件service */
	@Autowired
	private FileService fileService;

	/* 商品分类service */
	@Autowired
	private ProductCategoryPlatformService pfCategoryService;



	/* 药品的service */
	@Autowired
	private ProductMainService productMainService;

	@Autowired
	public void setMyService(ProductOrdinaryService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ProductOrdinaryController() {
		super("productmgr/sysback/productordinary", "ProductOrdinary",
				ProductOrdinaryController.class);
	}

	/**
	 * 设置查询条件
	 */
	protected ProductQueryModel preparedQMFixValue(ProductQueryModel qm) {
		// 设置普通商品
		qm.setProductTypeq(ProductType.HOUSEKEEP.getValue());
		return super.preparedQMFixValue(qm);
	}

	/**
	 * 普通商品管理
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String toList(Model model, HttpServletRequest request) {
		ProductWebModel web = new ProductWebModel();
		// 商品状态为上架
		web.setState(ProductMainModel.SHELVES);
		model.addAttribute("web", web);

		return "productmgr/sysback/product/ProductOrdinaryList";
	}

	/**
	 * 跳转预览商品页面
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toView/{uuid}", method = RequestMethod.GET)
	public String toView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 商品描述信息

		model.addAttribute("m", "");
		return "productmgr/sysback/product/ProductAudit";
	}

	/**
	 * 跳转预览商品页面
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteByUuid/{uuid}", method = RequestMethod.GET)
	public String deleteByUuid(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 商品描述信息
		ProductMainModel pm = mainService.getByUuid(uuid);
		if (pm != null) {
			mainService.delete(pm);
		}
		return "productmgr/sysback/product/ProductOrdinaryList";
	}

	/**
	 * 跳转预览商品页面
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewByUuid/{uuid}", method = RequestMethod.GET)
	public String viewByUuid(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 商品描述信息
		ProductMainModel pm = mainService.getByUuid(uuid);
		if (pm != null) {
			model.addAttribute("m", pm);
		}
		String pdmUuid = productMainDescriptionService.getProductMainDescriptionUuidByProductUuid(uuid);
		
		if(!StringUtil.isEmpty(pdmUuid)){
		    ProductMainDescriptionModel pdm = productMainDescriptionService.getByUuid(pdmUuid);
		    if(pdm!=null){
		        model.addAttribute("pdm", pdm);
		    }
		}
		
		return "productmgr/sysback/product/ProductView";
	}

	/**
	 * 跳转审核商品页面
	 * 
	 * @param uuid
	 *            商品uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toAudit/{uuid}", method = RequestMethod.GET)
	public String toAudit(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {

		ProductModel product = new ProductModel();
		// 封装model 设置到页面
		// 商品主信息
		ProductMainModel productMain = mainService.getByUuid(uuid);
		// 商品价格信息
		ProductMainBasePriceModel productPrice = priceService
				.getProductMainBasePriceModelByProductUuid(uuid);

		product.setProductMain(productMain);

		FieldReason fieldReason = new FieldReason(productMain.getUuid());
		model.addAttribute("reason", fieldReason);

		model.addAttribute("m", product);
		return "productmgr/sysback/product/ProductAudit";
	}

	/**
	 * 普通商品审核
	 */
	@RequestMapping(value = "/toAudit", method = RequestMethod.GET)
	public String toAudit(Model model, HttpServletRequest request) {
		ProductWebModel web = new ProductWebModel();
		// 商品状态为未审核
		web.setAuditState(ProductMainModel.UN_AUDITED);
		model.addAttribute("web", web);

		return "productmgr/sysback/product/ProductOrdinaryAuditList";
	}

	@Override
	@RequestMapping(value = "/toUpdate/{uuid}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		// 商品主信息
		ProductModel product = new ProductModel();
		// 封装model 设置到页面
		// 商品主信息
		ProductMainModel productMain = mainService.getByUuid(uuid);
		// 商品价格信息
		ProductMainBasePriceModel productPrice = priceService
				.getProductMainBasePriceModelByProductUuid(uuid);

		product.setProductMain(productMain);

		FieldReason fieldReason = new FieldReason(productMain.getUuid());
		model.addAttribute("reason", fieldReason);

		model.addAttribute("m", product);

		// 该对象，用来传递值到页面
		ProductWebModel web = new ProductWebModel();
		// 商品类型
		web.setType(productMain.getProductType());
		model.addAttribute("web", web);

		preparedAddData(model, request);
		return "productmgr/sysback/product/ProductOrdinaryUpdate";
	}

	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute("m") ProductModel m,
			HttpServletRequest request) {
		mainService.update(m.getProductMain());
		/* return super.update(model, m, request); */
		return "productmgr/sysback/productordinary/ProductOrdinarySuccess";
	}

	/**
	 * 向添加的页面發送一些準備數據
	 * 
	 * @param model
	 * @param request
	 */
	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		Map<String, String> chargeTypes = new HashMap<String, String>();

		chargeTypes.put(ProductModel.CHARGETYPE_SQUARE,
				MessageHelper.getMessage("productmain.chargetype.square"));
		chargeTypes.put(ProductModel.CHARGETYPE_NUMBER,
				MessageHelper.getMessage("productmain.chargetype.number"));

		Map<String, String> scopeTypes = new HashMap<String, String>();
		scopeTypes.put(ProductModel.SCOPE_APP,
				MessageHelper.getMessage("productmain.scope.app"));
		scopeTypes.put(ProductModel.SCOPE_WEB,
				MessageHelper.getMessage("productmain.scope.web"));

		model.addAttribute("chargeTypes", chargeTypes);
		model.addAttribute("scopeTypes", scopeTypes);
	}

	/**
	 * 发布商品第一步，选择商品类型页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toStep1", method = RequestMethod.GET)
	public String toStep1(Model model, HttpServletRequest request) {
		return "productmgr/sysback/product/ProductStep1";
	}

	@Override
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(Model model, HttpServletRequest request) {
		return super.toAdd(model, request);
	}

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("m") ProductModel m,
			HttpServletRequest request) {
		ProductMainModel productMainModel = m.getProductMain();
		ProductMainDescriptionModel pdm = m.getProductDescription();
		productMainModel.setCreateTime(DateFormatHelper.getNowTimeStr());
		mainService.create(productMainModel);
		productMainDescriptionService.create(pdm);
		
		
		if(productMainModel!=null&&pdm!=null){
		    pdm.setProductUuid(productMainModel.getUuid());
		
		    productMainDescriptionService.update(pdm);
		}
		return "productmgr/sysback/productordinary/ProductOrdinarySuccess";
	}

	/**
	 * 商品发布选择分类
	 * 
	 * @param grade
	 * @param parentUuuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> categoryList(
			@RequestParam(value = "grade") String grade,
			@RequestParam(value = "parentUuuid") String parentUuuid,
			Model model, HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 只能查出该商户所属分类,暂时先这么写着
		List<ProductCategoryPlatformModel> list = new ArrayList<ProductCategoryPlatformModel>();
		if (StringUtil.isEmpty(parentUuuid)) {
			list = pfCategoryService.getSubProductCategoryByParentUuid("");
		} else {
			list = pfCategoryService
					.getSubProductCategoryByParentUuid(parentUuuid);
		}
		if (list == null || list.size() == 0) {
			jsonMap.put("isRoot", Boolean.valueOf(true));
		}

		jsonMap.put("categoryList", list);
		if ("firstUl".equals(grade)) {
			jsonMap.put("grade", "secondUl");
		}

		if ("secondUl".equals(grade)) {
			jsonMap.put("grade", "thirdUl");
		}

		if ("thirdUl".equals(grade)) {
			jsonMap.put("grade", "fourthUl");
		}

		return jsonMap;
	}

	/**
	 * 获取分类全路径名称
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllPathName", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllPathName(
			@RequestParam(value = "uuid") String uuid, Model model,
			HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 只能查出该商户所属分类,暂时先这么写着
		String pathName = pfCategoryService.getAllNameByUuid(uuid, "");
		jsonMap.put("pathName", pathName);
		return jsonMap;
	}

	@RequestMapping(value = "/toAttribute", method = RequestMethod.GET)
	public String toAttribute(Model model, HttpServletRequest request) {

		return "productmgr/sysback/product/ProductAttribute";
	}

	/**
	 * 发布普通商品
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("m") ProductModel m,
			HttpServletRequest request) {

		// 商品默认是审核通过，先发后审核，通过运营设置
		String countStr = request.getParameter("count");
		int count = 0;
		if (!StringUtil.isEmpty(countStr)) {
			count = Integer.parseInt(countStr);
		}

		// 创建商品
		myService.save(m);

		return "productmgr/sysback/productordinary/ProductOrdinarySuccess";
	}

	/**
	 * 判断该用户名是否已经存在
	 * 
	 * @param customerName
	 */
	@RequestMapping(value = { "/checkProductNo" }, method = { RequestMethod.GET })
	@ResponseBody
	public String checkProductNoExist(
			@RequestParam("checkProductNo") String checkProductNo,
			HttpServletRequest request, HttpServletResponse response) {
		if (mainService.checkProductNoExist(checkProductNo)) {
			return "true";
		}

		return "false";
	}

	/**
	 * 删除主商品信息和基本价格信息
	 */
	@Override
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public String deletes(
			@RequestParam("selectOne") List<String> needDeleteUuids,
			BaseWebModel wm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 删除城市关联表中对应的记录
		//productCityService.removeCityListByProId(needDeleteUuids);
		// 根据商品关联的uuid删除基础价格
		priceService.deletesByProductUuids(needDeleteUuids);

		return super.deletes(needDeleteUuids, wm, request, response);
	}

	/**
	 * 
	 * 
	 * @param productUuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setproductcity", method = RequestMethod.GET)
	public String setProductCity(
			@RequestParam("productUuid") String productUuid, Model model,
			HttpServletRequest request) {

		model.addAttribute("productUuid", productUuid);
		return "productmgr/sysback/productcity/RelationCityList";

	}

	/**
	 * 跳转到药品导入页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/productImport", method = RequestMethod.GET)
	public String productImport(Model model, HttpServletRequest request) {
		return "productmgr/sysback/product/ProductImport";

	}

	/**
	 * 上传图片包括主图和多角度视图
	 * 
	 * @param myfiles
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importData", method = { RequestMethod.POST })
	public String importData(
			@RequestParam(value = "myExcelFile") MultipartFile[] files,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 调用上传excel的方法
		List<Object> list = productMainService.updloadExcel(files);
		StringBuffer erroMessage = new StringBuffer("");
		if (list != null && list.size() > 0) {
			int i = 2;
			for (Object object : list) {
				if (object != null) {
					try {
						ProductMainModel pmm = (ProductMainModel) object;
						ProductMainDescriptionModel pdm = new ProductMainDescriptionModel();
						if (!StringUtil.isEmpty(pmm.getProductName())) {
						    String laboratorExamination = pmm.getLaboratorExamination();
						    String attention = pmm.getAttention();
						    String drugInteractio = pmm.getDrugInteractio();
						    pmm.setLaboratorExamination("");
						    pmm.setAttention("");
						    pmm.setDrugInteractio("");
						    productMainService.create(pmm);
						    pdm.setLaboratorExamination(laboratorExamination);
						    pdm.setAttention(attention);
						    pdm.setDrugInteractio(drugInteractio);
						    if(pmm!=null){
						    pdm.setProductUuid(pmm.getUuid());
						    }
						    productMainDescriptionService.create(pdm);
						}
					} catch (Exception e) {
						erroMessage.append("第" + i + "条数据问题;");
					}
					i++;
				}
			}
		}
		model.addAttribute("erroMessage", erroMessage);

		return "productmgr/sysback/product/ProductOrdinaryList";
	}

	/**
	 * 导出药品模板
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/productTemplateExport", method = RequestMethod.GET)
	public String productTemplateExport(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String tableName = "药品模板";
		String[] headr = new String[ProductImportConst.PARAMKEYLIST.size()];
		String[] columns = new String[ProductImportConst.PARAMKEYLIST.size()];
		for (int i = 0; i < ProductImportConst.PARAMKEYLIST.size(); i++) {
			headr[i] = MessageHelper.getMessage(ProductImportConst.PARAMKEYLIST
					.get(i));
		}

		List<Object> tmList = new ArrayList<Object>();
		ExcelUtils.writeExcelByObject(tmList, response, tableName, headr,
				columns);
		return null;
	}

}