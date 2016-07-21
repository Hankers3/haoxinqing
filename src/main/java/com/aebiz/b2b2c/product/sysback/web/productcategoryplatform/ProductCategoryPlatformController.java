package com.aebiz.b2b2c.product.sysback.web.productcategoryplatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productcategoryplatform.service.ProductCategoryPlatformService;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformQueryModel;
import com.aebiz.b2b2c.product.productcatetemprel.service.ProductCateTempRelService;
import com.aebiz.b2b2c.product.producttemplate.service.ProductTemplateService;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.service.ProductTemplateAttrRelService;
import com.aebiz.b2b2c.product.sysback.web.productcategoryplatform.vo.ProductCategoryPlatformWebModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryplatform.vo.TreeElement;

/**
 * 平台分类管理控制器
 * 
 * @author huangpinpin
 *
 */
@Controller
@RequestMapping("/sysback/productcategoryplatform")
public class ProductCategoryPlatformController extends BaseController<ProductCategoryPlatformModel,ProductCategoryPlatformQueryModel>{
	private ProductCategoryPlatformService myService;
	@Autowired
	private ProductTemplateService productTemplateService;
	
	@Autowired
	private ProductCateTempRelService categoryTemplateRelService;
	
	@Autowired
	private ProductTemplateAttrRelService productAttributeService;
	
	@Autowired
	public void  setMyService(ProductCategoryPlatformService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public ProductCategoryPlatformController(){
		super("productmgr/sysback/productcategoryplatform","ProductCategoryPlatform",ProductCategoryPlatformController.class);
	}
	/**
	 * 跳转到分类管理页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toCategory", method = RequestMethod.GET)
	public String toCategory(Model model, HttpServletRequest request) {
		return "productmgr/sysback/productcategoryplatform/ProductCategoryList";
	}
	
	/**
	 * 跳转到成功页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toSuccess", method = RequestMethod.GET)
	public String toSuccess(Model model, HttpServletRequest request) {
		
		return "productmgr/sysback/productcategoryplatform/ProductCategorySuccess";
	}
	
	/**
	 * 跳转到批量添加页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toBatchAdd", method = RequestMethod.GET)
	public String toBatchAdd(Model model, HttpServletRequest request) {
		
		return "productmgr/sysback/productcategoryplatform/BatchAddCategory";
	}
	
	/**
	 * 构造分类树
	 * @param key
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getNodes", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeElement> getNodes(@RequestParam(value="key") String key, Model model, HttpServletRequest request) {
		
		String parentUuid=key;
		//得到下级分类
		List<TreeElement> list=getRootList(parentUuid);
		
		return list;
	}
	
	/**
	 * 得到下级分类列表根据父分类uuid
	 * @param parenUuid
	 * @return
	 */
	public List<TreeElement> getRootList(String parenUuid){
		List<TreeElement> nodes=new ArrayList<TreeElement>();
		List<ProductCategoryPlatformModel> list =myService.getSubProductCategoryByParentUuid(parenUuid);
		if(list==null || list.size()==0){
			return nodes;
		}
		for(int i=0;i<list.size();i++){
			ProductCategoryPlatformModel model=list.get(i);
			TreeElement ele=new TreeElement();
			ele.setKey(model.getUuid());
			ele.setTitle(model.getCategoryName());
			List<ProductCategoryPlatformModel> sublist =myService.getSubProductCategoryByParentUuid(model.getUuid());
			if(sublist!=null&&sublist.size()>0){
				ele.setIsLazy(true);
			}
			ele.setIsFolder(true);
			ele.setExpand(false);
			nodes.add(ele);
		}
		return nodes;
	}
	
	
	/**
	 *跳转到添加分类页
	 */
	@Override
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(Model model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "productmgr/sysback/productcategoryplatform/ProductCategoryAdd";
	}
	
	/**
	 * 跳转到分类更新页
	 */
	@Override
	@RequestMapping(value = "/toUpdate/{uuid}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		ProductCategoryPlatformModel m=myService.getByUuid(uuid);
		
		List<TreeElement> list=getRootList(uuid);
		if(list==null||list.size()==0){
			//获取模板uuid集合
			List<String> templateUuids=categoryTemplateRelService.getTemplateUuidsByCategoryUuid(uuid);
			
			String templateName=this.getTemplateName(templateUuids);
			ProductCategoryPlatformWebModel web=new ProductCategoryPlatformWebModel();
			web.setTemplateName(templateName);
			web.setButtonRelTemplate("1");
			model.addAttribute("web", web);
		}
		model.addAttribute("m", m);
		
		return "productmgr/sysback/productcategoryplatform/ProductCategoryUpdate";
	}
	
	/**
	 * 根据模板uuid集合查出模板名称,并转化成格式为：模板1;模板2;
	 * @param templateUuids 模板uuid集合
	 * @return
	 */
	protected String getTemplateName(List<String> templateUuids){
		//拼接字符串，格式为：模板1;模板2; 展示在编辑分类  商品模板文本框内
		StringBuffer templateNameStr=new StringBuffer("");
		if(templateUuids!=null && templateUuids.size()>0){
			for(int i=0;i<templateUuids.size();i++){
				templateNameStr.append(productTemplateService.getTemplateNameByUuids(templateUuids.get(i)) +";");
			}
		}
		return templateNameStr.toString();
	}
	
	
	/**
	 * 跳转到关联模板选择页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toRelatemp", method = RequestMethod.GET)
	public String toRelatemp(Model model, HttpServletRequest request) {
		
		List<ProductTemplateModel> list=productTemplateService.getAll();
		model.addAttribute("templates", list);
		return "productmgr/sysback/productcategoryplatform/Relatemp";
	}
	
	/**
	 * 添加分类
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> save(Model model,
			@ModelAttribute("m") ProductCategoryPlatformModel m,
			HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();
		jsonMap=super.parsePageParam(request);
		//获取添加分类各个字段信息
		String categoryName=(String) jsonMap.get("categoryName");
		String categoryNo=(String) jsonMap.get("categoryNo");
		String parentUuid=(String) jsonMap.get("parentUuid");
		String position=(String) jsonMap.get("position");
		String categoryNote=(String) jsonMap.get("categoryNote");
		String categoryUrl=(String) jsonMap.get("categoryUrl");
		String state=(String) jsonMap.get("state");
		
		//设置值到model
		m.setCategoryName(categoryName);
		m.setCategoryNo(categoryNo);
		m.setParentUuid(parentUuid);
		if(!StringUtil.isEmpty(position)){
			m.setPosition(Integer.parseInt(position));
		}
		m.setCategoryNote(categoryNote);
		m.setCategoryUrl(categoryUrl);
		if(!StringUtil.isEmpty(state)){
			m.setState(state);
		}
		
		ProductCategoryPlatformModel parentCategory=null;
		//查询父分类
		if(!StringUtil.isEmpty(parentUuid)){
			parentCategory=myService.getByUuid(parentUuid);
		}
		//生成fullpath
		if (parentCategory != null) {
			m.setFullpath(myService.getSheetNo(
					parentCategory.getFullpath(), 1));
		} else {
			m.setFullpath(myService.getSheetNo( "", 1));
		}
		
		model.addAttribute("m", m);
		//创建分类
		myService.create(m);
		
		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}
	
	/**
	 * 更新分类
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateCategory(Model model,
			@ModelAttribute("m") ProductCategoryPlatformModel m,
			HttpServletRequest request) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();
		
		jsonMap = super.parsePageParam(request);
		String uuid=(String) jsonMap.get("uuid");
		String categoryName=(String) jsonMap.get("categoryName");
		String categoryNo=(String) jsonMap.get("categoryNo");
		String parentUuid=(String) jsonMap.get("parentUuid");
		String position=(String) jsonMap.get("position");
		String categoryNote=(String) jsonMap.get("categoryNote");
		String categoryUrl=(String) jsonMap.get("categoryUrl");
		String state=(String) jsonMap.get("state");
		
		m=myService.getByUuid(uuid);
		
		model.addAttribute("m", m);
		if(m==null){
			reJsonMap.put("erroMassage", "noEixtUuid");
			return reJsonMap;
		}
		
		m.setCategoryName(categoryName);
		m.setCategoryNo(categoryNo);
		m.setParentUuid(parentUuid);
		if(!StringUtil.isEmpty(state)){
			m.setState(state);
		}
		if(!StringUtil.isEmpty(position)){
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
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addBatch", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addBatch(Model model, HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> reJsonMap = new HashMap<String, Object>();
		jsonMap=super.parsePageParam(request);
		String parentUuid=(String) jsonMap.get("parentUuid");
		List<ProductCategoryPlatformModel> categoryList=new ArrayList<ProductCategoryPlatformModel>();
		for(int i=1;i<=10;i++){
			String categoryName=(String) jsonMap.get("categoryName"+i);
			String categoryNo=(String) jsonMap.get("categoryNo"+i);
			//判断分类名称和分类编号是否为空,空就继续
			if(StringUtil.isEmpty(categoryName)||StringUtil.isEmpty(categoryNo)){
				continue;
			}
			
			ProductCategoryPlatformModel m=new ProductCategoryPlatformModel();
			
			m.setCategoryName(categoryName);
			m.setCategoryNo(categoryNo);
			m.setParentUuid(parentUuid);
			m.setState("0");
			ProductCategoryPlatformModel parentCategory=null;
			if(!StringUtil.isEmpty(parentUuid)){
				parentCategory=myService.getByUuid(parentUuid);
			}
			if (parentCategory != null) {
				m.setFullpath(myService.getSheetNo(
						parentCategory.getFullpath(), 1));
			} else {
				m.setFullpath(myService.getSheetNo( "", 1));
			}
			categoryList.add(m);
		
		}
		myService.addBatch(categoryList);
		
		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}
	
	
//	@RequestMapping(value = "/attributeList", method = RequestMethod.GET)
//	public String attributeList(Model model,
//			@RequestParam("templateUuids") List<String> templateUuids,
//			HttpServletRequest request) {
//		List<ProductCategoryPlatformWebModel> webList=new ArrayList<ProductCategoryPlatformWebModel>();
//		
//		
//		if(templateUuids==null|| templateUuids.size()==0){
//			
//		}
//		List<ProductTemplateModel> list=productTemplateService.getProductTemplateByUuids(templateUuids);
//		if(list!=null && list.size()>0){
//			for(int i=0;i<list.size();i++){
//				ProductCategoryPlatformWebModel web=new ProductCategoryPlatformWebModel();
//				ProductTemplateModel productTemplate=list.get(i);
//				web.setProductTemplate(productTemplate);
//				
//				//TODO 获取该模板下的属性
//				List<ProductTemplateAttrRelModel> attributs=productAttributeService.getAttributeByUuid(productTemplate.getUuid());
//				web.setProductAttributs(attributs);
//				webList.add(web);
//			}
//		}
//
//
//		model.addAttribute("webList", webList);
//	
//		return "productmgr/sysback/productcategoryplatform/ProductAttribute";
//	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model, HttpServletRequest request) {
		
		List<String> uuids=new ArrayList<String>();
		uuids.add("ryPlatform0000000102");
		uuids.add("ryPlatform0000000103");
		Map<String, ProductCategoryPlatformModel> aa=myService.getCategorysByUuids(uuids);
		
		System.out.println("============================="+aa.get("ryPlatform0000000102").getCategoryName());
		System.out.println("============================="+aa.get("ryPlatform0000000103").getCategoryName());
		
		return "";
	}
	
}