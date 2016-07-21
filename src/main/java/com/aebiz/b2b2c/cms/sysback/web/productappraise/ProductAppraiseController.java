package com.aebiz.b2b2c.cms.sysback.web.productappraise;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.cms.ordershowpic.service.OrderShowPicService;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;
import com.aebiz.b2b2c.cms.productappraise.service.ProductAppraiseService;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseQueryModel;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.product.interactive.product.service.ProductInteractiveService;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/productappraise")
public class ProductAppraiseController extends
		BaseController<ProductAppraiseModel, ProductAppraiseQueryModel> {
	private ProductAppraiseService myService;
	
	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private ProductInteractiveService productInteractiveService;

	@Autowired
	private OrderShowPicService orderShowPicService;
	
	@Autowired
	public void setMyService(ProductAppraiseService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ProductAppraiseController() {
		super("cms/sysback/productappraise", "ProductAppraise",
				ProductAppraiseController.class);
	}
	
	/**
	 * 
	 * 平台后台、商品评价审核页面
	 * 
	 * 需要调用订单系统提供的接口获取订单明细信息，订单号等
	 * 
	 * @param model
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/toReview/{uuid}")
	public String toReview(Model model, @PathVariable("uuid") String uuid) {
		ProductAppraiseModel pam = this.myService.getByUuid(uuid);
		if (pam != null) {
			// 调用订单系统提供的接口获取订单明细
			OrderDetailModel odm = orderDetailService.getByUuid(pam
					.getOrderDetailUuid());
			model.addAttribute("detailModel", odm);
			if (odm != null) {
				// 调用商品系统提供的接口获取商品编号
				ProductInteractiveModel productModel = this.productInteractiveService
						.getProductByUuid(odm.getProductUuid());
				if (productModel != null) {
					pam.setProductNo(productModel.getProductMain()
							.getProductNo());
				}
			}
			String[] tagStr = pam.getAppTag().split(",");
			List<String> tags = Arrays.asList(tagStr);
			pam.setTags(tags);
		}
		model.addAttribute("pam", pam);

		return "cms/sysback/productappraise/ProductAppraiseInfo";
	}
	
	/**
	 * 
	 * 商品评价审核、审核不通过需要输入审核原因<br/>
	 * 
	 * @param reviewType
	 * @param reviewDesc
	 * @return
	 */
	@RequestMapping("/review")
	@ResponseBody
	public String review(@RequestParam("uuid") String uuid,@RequestParam("reviewType") String reviewType,
			@RequestParam("reviewDesc") String reviewDesc) {
		ProductAppraiseModel pam = this.myService.getByUuid(uuid);
		if(pam != null){
			if (ProductAppraiseModel.STATE_REVIEW_SUCCESS.equals(reviewType)) {
				// 审核通过、直接进行数据保存操作
				pam.setState(reviewType);
				this.myService.saveReview(pam);
			} else if (ProductAppraiseModel.STATE_REVIEW_FAIL.equals(reviewType)) {
				// 审核不通过、需要验证审核不通过原因是否输入、字符数是否超过限制
				if (!StringUtil.isEmpty(reviewDesc)) {
					if(reviewDesc.length() > 250){
						return JSON.toJSONString(MessageHelper
								.getMessage("productappraise.m.reviewDescOverLength"));
					}
				}else{
					return JSON.toJSONString(MessageHelper
							.getMessage("productappraise.m.pleaseInputReviewDesc"));
				}
				pam.setState(reviewType);
				pam.setReviewDesc(reviewDesc);
				this.myService.saveReview(pam);
			}
		}else{
			return JSON.toJSONString("fail");
		}
		return JSON.toJSONString("success");
	}
	
}