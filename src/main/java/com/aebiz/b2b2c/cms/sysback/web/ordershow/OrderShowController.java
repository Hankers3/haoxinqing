package com.aebiz.b2b2c.cms.sysback.web.ordershow;

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
import com.aebiz.b2b2c.cms.ordershow.service.OrderShowService;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowQueryModel;
import com.aebiz.b2b2c.cms.ordershowpic.service.OrderShowPicService;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.product.interactive.product.service.ProductInteractiveService;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/ordershow")
public class OrderShowController extends
		BaseController<OrderShowModel, OrderShowQueryModel> {
	private OrderShowService myService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private ProductInteractiveService productInteractiveService;

	@Autowired
	private OrderShowPicService orderShowPicService;

	@Autowired
	public void setMyService(OrderShowService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public OrderShowController() {
		super("cms/sysback/ordershow", "OrderShow", OrderShowController.class);
	}

	/**
	 * 
	 * 平台后台、订单晒单审核页面
	 * 
	 * 需要调用订单系统提供的接口获取订单明细信息，订单号等
	 * 
	 * @param model
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/toReview/{uuid}")
	public String toReview(Model model, @PathVariable("uuid") String uuid) {
		OrderShowModel osm = this.myService.getByUuid(uuid);
		if (osm != null) {
			// 调用订单系统提供的接口获取订单明细
			OrderDetailModel odm = orderDetailService.getByUuid(osm
					.getOrderDetailUuid());
			model.addAttribute("detailModel", odm);
			if (odm != null) {
				// 调用商品系统提供的接口获取商品编号
				ProductInteractiveModel productModel = this.productInteractiveService
						.getProductByUuid(odm.getProductUuid());
				if (productModel != null) {
					osm.setProductNo(productModel.getProductMain()
							.getProductNo());
				}
			}
			List<OrderShowPicModel> ospmList = orderShowPicService
					.getOrderShowPicModelByShowUuid(osm.getUuid());
			osm.setOspList(ospmList);
		}
		model.addAttribute("orderShowModel", osm);

		return "cms/sysback/ordershow/OrderShowInfo";
	}

	/**
	 * 
	 * 晒单审核、审核不通过需要输入审核原因<br/>
	 * 
	 * @param reviewType
	 * @param reviewDesc
	 * @return
	 */
	@RequestMapping("/review")
	@ResponseBody
	public String review(@RequestParam("uuid") String uuid,@RequestParam("reviewType") String reviewType,
			@RequestParam("reviewDesc") String reviewDesc) {
		OrderShowModel osm = this.myService.getByUuid(uuid);
		if(osm != null){
			if (OrderShowModel.STATE_REVIEW_SUCCESS.equals(reviewType)) {
				// 审核通过、直接进行数据保存操作
				osm.setState(reviewType);
				this.myService.saveReview(osm);
			} else if (OrderShowModel.STATE_REVIEW_FAIL.equals(reviewType)) {
				// 审核不通过、需要验证审核不通过原因是否输入、字符数是否超过限制
				if (!StringUtil.isEmpty(reviewDesc)) {
					if(reviewDesc.length() > 250){
						return JSON.toJSONString(MessageHelper
								.getMessage("ordershow.m.reviewDescOverLength"));
					}
				}else{
					return JSON.toJSONString(MessageHelper
							.getMessage("ordershow.m.pleaseInputReviewDesc"));
				}
				osm.setState(reviewType);
				osm.setReviewDesc(reviewDesc);
				this.myService.saveReview(osm);
			}
		}else{
			return JSON.toJSONString("fail");
		}
		return JSON.toJSONString("success");
	}

}