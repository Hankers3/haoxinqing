package com.aebiz.b2b2c.cms.usercenter.web.productappraise;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.ordermainappraise.service.OrderMainAppraiseService;
import com.aebiz.b2b2c.cms.ordermainappraise.vo.OrderMainAppraiseModel;
import com.aebiz.b2b2c.cms.productappraise.service.ProductAppraiseService;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseQueryModel;
import com.aebiz.b2b2c.cms.usercenter.web.productappraise.vo.OrderAppraiseWebModel;
import com.aebiz.b2b2c.cms.usercenter.web.productappraise.vo.ProductAppraiseWebModel;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.store.storecomplex.vo.StoreComplexModel;
import com.aebiz.b2b2c.store.sysback.service.StoreComplexInteractive;

@Controller("UsercenterProductAppraiseController")
@RequestMapping("/usercenter/productappraise")
public class ProductAppraiseController extends
		BaseController<ProductAppraiseModel, ProductAppraiseQueryModel> {
	private ProductAppraiseService myService;

	@Autowired
	private OrderMainAppraiseService omaService;

	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private StoreComplexInteractive storeComplexInteractive;

	@Autowired
	public void setMyService(ProductAppraiseService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public ProductAppraiseController() {
		super("cms/productappraise", "ProductAppraise",
				ProductAppraiseController.class);
	}

	/**
	 * 会员中心去评价页面
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/toAppraise/{orderUuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toAppraise(Model model, HttpServletRequest request,
			@PathVariable("orderUuid") String orderUuid) {
		// 将订单信息发送到页面
		OrderMainModel omm = orderMainService.getByUuid(orderUuid);
		model.addAttribute("omm", omm);

		// 获取订单明细、标签集合的封装model发送到页面
		List<OrderDetailModel> detailList = orderDetailService
				.getOrderDetailAndOrderShowByOrderId(orderUuid);
		if (detailList != null && detailList.size() > 0) {
			List<ProductAppraiseWebModel> list = this.myService
					.getOrderDetailAndTags(detailList);
			model.addAttribute("list", list);
		}

		if (omm != null) {
			/*// 获取商家信息发送到页面
			OrderMainAppraiseModel oam = this.omaService
					.getOrderMainAppraiseByOrderUuid(omm.getUuid());
			model.addAttribute("oam", oam);
			StoreComplexModel scm = this.storeComplexInteractive
					.getStoreComplexModel(omm.getStoreUuid());
			if (scm != null) {
				OrderAppraiseWebModel oawm = new OrderAppraiseWebModel();
				oawm.setStoreName(scm.getStoreName());
				oawm.setStoreAddress(scm.getStoreCompanyInfo()
						.getCompanyAddress());
				oawm.setStoreLogo(scm.getStoreCompanyInfo().getLogoPath());
				oawm.setStoreMobile(scm.getStoreCompanyInfo()
						.getContactMobile());
				model.addAttribute("oawm", oawm);
			}

			// 获取商家的平均评分
			double averageScore = this.omaService.getAverageScore(omm
					.getStoreUuid(),OrderMainAppraiseModel.SCORE_STORE);
			model.addAttribute("averageScore", averageScore);*/
		}

		// 将订单明细列表发送到页面
		return "cms/usercenter/productappraise/ProductAppraiseAddList";
	}

	/**
	 * 
	 * 保存商品评价信息
	 * 
	 * @param orderDetailUuid
	 *            订单明细id
	 * @param appScore
	 *            商品评分
	 * @param appTags
	 *            评价标签
	 * @param content
	 *            评价内容
	 * @return
	 */
	@RequestMapping(value = { "/saveAppraise" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String saveAppraise(Model model,
			@RequestParam("orderDetailUuid") String orderDetailUuid,
			@RequestParam("appScore") String appScore,
			@RequestParam("appTags") String appTags,
			@RequestParam("content") String content) {

		OrderDetailModel odm = this.orderDetailService
				.getByUuid(orderDetailUuid);
		if (odm != null) {
			ProductAppraiseModel m = new ProductAppraiseModel();
			m.setOrderUuid(odm.getOrderMainUuid());
			m.setOrderDetailUuid(orderDetailUuid);
			m.setProductUuid(odm.getProductUuid());
			m.setAppScore(Integer.parseInt(appScore));
			if (!StringUtil.isEmpty(appTags)) {
				appTags = appTags.replaceAll("[\\s\\p{Zs}]+", "");
			}
			m.setAppTag(appTags);
			m.setAppContent(content);
			this.myService.create(m);

			// 封装数据发送到页面
			String[] tagStr = m.getAppTag().split(",");
			List<String> tags = Arrays.asList(tagStr);
			m.setTags(tags);
			model.addAttribute("m", m);
		}

		return "cms/usercenter/productappraise/LoadProductApp";
	}

	/**
	 * 
	 * 保存订单评价
	 * 
	 * @param storeScore
	 *            对商家的评分
	 * @param speedScore
	 *            发货速度的评分
	 * @param descScore
	 *            如实描述的评分
	 * @param serviceScore
	 *            服务态度的评分
	 * @param content
	 *            评价内容
	 * @return
	 */
	@RequestMapping(value = { "/saveOrderAppraise" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String saveOrderAppraise(Model model,
			@RequestParam("orderMainUuid") String orderMainUuid,
			@RequestParam("storeScore") String storeScore,
			@RequestParam("speedScore") String speedScore,
			@RequestParam("descScore") String descScore,
			@RequestParam("serviceScore") String serviceScore,
			@RequestParam("content") String content) {

		OrderMainAppraiseModel omam = new OrderMainAppraiseModel();
		omam.setOrderMainUuid(orderMainUuid);
		omam.setStoreScore(Integer.parseInt(storeScore));
		omam.setSpeedScore(Integer.parseInt(speedScore));
		omam.setDescScore(Integer.parseInt(descScore));
		omam.setServiceScore(Integer.parseInt(serviceScore));
		omam.setContent(content);

		this.omaService.create(omam);
		model.addAttribute("oam", omam);

		return "cms/usercenter/productappraise/LoadOrderApp";
	}

	/**
	 * 
	 * 会员中心去我的评价列表
	 * 
	 * @param wm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toMyAppraise/{nowPage}/{pageShow}")
	public String toMyAppraise(@ModelAttribute("wm") BaseWebModel wm,
			Model model, HttpServletRequest request) {
		ProductAppraiseQueryModel qm = getQueryModel();

		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("ProductAppraise" + "IsQuery");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (ProductAppraiseQueryModel) SecurityUtils.getSubject()
						.getSession()
						.getAttribute("ProductAppraise" + "QueryModel");
			}
		}

		qm.setDelFlag("1");
		qm.getMapCondition().put("delFlag",
				Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));

		qm.setCustomerUuid(LoginUserHelper.getCustomerLoginUserUuid());
		qm.getMapCondition().put("customerUuid",
				Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));

		wm.setTotalNum(this.myService.getCount(qm));
		// 获取订单评价、订单明细、封装到ProductAppraiseWebModel发送到页面
		List<ProductAppraiseWebModel> list = this.myService.getProductAppList(
				qm, wm.getFromNum(), wm.getPageShow());

		wm.setRows(list);

		model.addAttribute("list", list);

		request.setAttribute("qm", qm);

		// 将订单评价明细列表发送到页面
		return "cms/usercenter/productappraise/ProductAppraiseViewList";
	}
}