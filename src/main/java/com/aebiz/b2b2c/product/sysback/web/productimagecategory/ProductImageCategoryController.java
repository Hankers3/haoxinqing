package com.aebiz.b2b2c.product.sysback.web.productimagecategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.product.productimagecategory.service.ProductImageCategoryService;
import com.aebiz.b2b2c.product.productimagecategory.vo.ProductImageCategoryModel;
import com.aebiz.b2b2c.product.productimagecategory.vo.ProductImageCategoryQueryModel;
import com.aebiz.b2b2c.product.productimagelibrary.service.ProductImageLibraryService;
import com.aebiz.b2b2c.product.sysback.web.productcategoryplatform.vo.TreeElement;

@Controller
@RequestMapping("/sysback/productimagecategory")
public class ProductImageCategoryController
		extends
			BaseController<ProductImageCategoryModel, ProductImageCategoryQueryModel> {
	private ProductImageCategoryService myService;
	@Autowired
	public void setMyService(ProductImageCategoryService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	// 注入商品图片的service
	@Autowired
	private ProductImageLibraryService imageLibraryService;

	public ProductImageCategoryController() {
		super("product/sysback/productimagecategory",
				"ProductImageCategory",
				ProductImageCategoryController.class);
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
		List<TreeElement> list = myService.getRootList(parentUuid);

		return list;
	}

	/**
	 * 添加图片分类
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> save(Model model,
			@RequestParam(value = "categoryName") String categoryName,
			@RequestParam(value = "parentUuid") String parentUuid,
			HttpServletRequest request) {
		Map<String, Object> reJsonMap = new HashMap<String, Object>();

		ProductImageCategoryModel m = new ProductImageCategoryModel();
		// 分类名称
		m.setCategoryName(categoryName);
		// 父分类
		m.setParentUuid(parentUuid);

		// TODO 获取当前登陆的商户的uuid
		String storeUuid = "ccountUuid0000000061";
		m.setStoreUuid(storeUuid);

		// 创建分类
		myService.create(m);
		model.addAttribute("m", m);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}

	/**
	 * 编辑图片分类
	 * 
	 * @param model
	 * @param categoryName
	 * @param parentUuid
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateCategory(Model model,
			@RequestParam(value = "categoryName") String categoryName,
			@RequestParam(value = "selectUuid") String selectUuid,
			HttpServletRequest request) {
		Map<String, Object> reJsonMap = new HashMap<String, Object>();

		ProductImageCategoryModel m = myService.getByUuid(selectUuid);
		// 分类名称
		m.setCategoryName(categoryName);

		myService.updateCell(m, "categoryName");

		model.addAttribute("m", m);

		reJsonMap.put("rsp", Boolean.valueOf(true));
		return reJsonMap;
	}

	/**
	 * 删除图片分类以及子分类,如果该分类或子分类下有图片则,需要提示用户先删除图片后才能删除
	 * 
	 * @param selectUuid
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             String
	 */
	@RequestMapping(value = {"/deleteCategory"}, method = {RequestMethod.GET})
	@ResponseBody
	public String deleteCategory(@RequestParam("selectUuid") String selectUuid,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> uuidList = new ArrayList<String>();
		uuidList = myService.getSubCategory(selectUuid);
		uuidList.add(selectUuid);

		for (String uuid : uuidList) {
			int count = imageLibraryService
					.getProductImageLibraryModelsByCategoryUuid(uuid);
			if (count > 0) {
				return "hasImage";
			}
		}
		myService.deletes(uuidList);
		return "success";
	}

	/**
	 * 获取当前登录的商户的uuid
	 */
	protected ProductImageCategoryQueryModel preparedQMFixValue(
			ProductImageCategoryQueryModel qm) {
		// TODO 获取当前登陆的商户的uuid
		String storeUuid = "ccountUuid0000000061";
		qm.setStoreUuid(storeUuid);
		qm.getMapCondition().put("storeUuid", ConditionOpTypeEnum.EQ.getCode());
		return super.preparedQMFixValue(qm);
	}

}