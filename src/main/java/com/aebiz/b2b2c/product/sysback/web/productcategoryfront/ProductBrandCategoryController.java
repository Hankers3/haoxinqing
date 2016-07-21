package com.aebiz.b2b2c.product.sysback.web.productcategoryfront;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.service.FrontProductCategoryBrandRelService;
import com.aebiz.b2b2c.product.productbrand.service.ProductBrandService;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo.FrontCategoryBrandRelWebModel;

/**
 * 前台商品分类关联品牌控制器
 * 
 */
@Controller
@RequestMapping("/sysback/productcategoryfront/productbrand")
public class ProductBrandCategoryController extends
		BaseController<ProductBrandModel, ProductBrandQueryModel> {

	private ProductBrandService myService;

	@Autowired
	private FrontProductCategoryBrandRelService frontProductCategoryBrandRelService;

	@Autowired
	private FileUploadHelper fileUpload = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyService(ProductBrandService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ProductBrandCategoryController() {
		super("product/sysback/productbrand", "ProductBrand",
				ProductBrandCategoryController.class);
	}

	@RequestMapping(value = { "/searchProductBrands/{nowPage}/{pageShow}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String searchProductBrands(Model model,
			HttpServletRequest request,
			@ModelAttribute("wm") FrontCategoryBrandRelWebModel wm,
			@PathVariable("nowPage") String nowPage) {

		String uuid=request.getParameter("uuid");
		String brandName=request.getParameter("brandName");
		String searchType=request.getParameter("searchType");
		String selectValue=request.getParameter("selectValue");
		wm.setNowPage(Integer.parseInt(nowPage));

		wm.setPageShow(18);

		wm.setSelectValue(selectValue);
		wm.setSearchType(searchType);
		wm.setBrandName(brandName);
		wm.setCategoryUuid(uuid);
		ProductBrandQueryModel qm = new ProductBrandQueryModel();

		qm.setSearchType(searchType);
		if(!StringUtil.isEmpty(selectValue)){
			qm.setLetter(selectValue);
		}
		qm.setBrandName(brandName);
		qm.getMapCondition().put("brandName",
				ConditionOpTypeEnum.Like.getCode());
	
		// 获取分类关联品牌数据发送到页面上去
		List<String> chooseBrandIds = this.frontProductCategoryBrandRelService
				.getBrandIdsByCateUuid(uuid);

		if(chooseBrandIds!=null && chooseBrandIds.size()>0){
			qm.setBrandUuids(chooseBrandIds);
		}
		
		int n = myService.getCount(qm);
		wm.setTotalNum(n);
		
		List<ProductBrandModel> list = myService.getByCondition(qm, wm.getFromNum(), wm.getPageShow());

		wm.setRows(list);

		model.addAttribute("list", list);

		return "product/sysback/productcategoryfront/SearchProductBrands";
	}
	
 
}