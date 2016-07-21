package com.aebiz.b2b2c.cms.usercenter.web.ordershow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.ordershow.service.OrderShowService;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowQueryModel;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;
import com.aebiz.b2b2c.cms.usercenter.web.ordershow.vo.OrderShowWebModel;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.product.interactive.product.service.ProductInteractiveService;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;

@Controller("UsercenterOrderShowController")
@RequestMapping("/usercenter/ordershow")
public class OrderShowController extends
		BaseController<OrderShowModel, OrderShowQueryModel> {
	private OrderShowService myService;

	@Autowired
	private FileUploadHelper fileUpload;

	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private ProductInteractiveService productInteractiveService;

	@Autowired
	public void setMyService(OrderShowService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public OrderShowController() {
		super("cms/usercenter/ordershow", "OrderShow",
				OrderShowController.class);
	}

	@RequestMapping("/toMyOrderShow/{nowPage}/{pageShow}")
	public String toMyOrderShow(Model model,
			@ModelAttribute("wm") BaseWebModel wm) {
		String customerUuid = LoginUserHelper.getCustomerLoginUserUuid();

		int num = 0;
		
		/*int num = this.orderDetailService
				.getOrderDetailModelCountByCustomerUuid(customerUuid);*/
		wm.setTotalNum(num);
		List<OrderDetailModel> detailList = null;
		
		/*List<OrderDetailModel> detailList = this.orderDetailService
				.getOrderDetailModelByCustomerUuid(customerUuid,
						wm.getFromNum(), wm.getPageShow());*/
		
		if (detailList != null && detailList.size() > 0) {
			List<OrderShowWebModel> list = this.myService
					.getOrderDetailWebModels(detailList);
			model.addAttribute("list", list);
		}
		wm.setRows(detailList);
		
		return "cms/usercenter/ordershow/OrderShowList";
	}

	/**
	 * 订单列表点击晒单进入到晒单展示页
	 * 
	 * 如果订单明细已经晒单，则可以查看当前晒单
	 * 
	 * 如果订单明细没有晒单，则跳转到晒单页进行晒单发布
	 * 
	 * @param orderUuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/toShareOrder/{orderUuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toShareOrder(@PathVariable("orderUuid") String orderUuid,
			Model model, HttpServletRequest request) {
		// 将订单信息发送到页面
		OrderMainModel omm = orderMainService.getByUuid(orderUuid);
		model.addAttribute("omm", omm);

		// 将订单明细发送到页面
		List<OrderDetailModel> detailList = orderDetailService
				.getOrderDetailAndOrderShowByOrderId(orderUuid);
		if (detailList != null && detailList.size() > 0) {
			List<OrderShowWebModel> list = this.myService
					.getOrderDetailWebModels(detailList);
			model.addAttribute("list", list);
		}

		// 将订单明细列表发送到页面
		return "cms/usercenter/ordershow/OrderShow";
	}

	/**
	 * 晒单列表页查看晒单
	 * 
	 * 如果订单明细未晒单，则跳转到晒单页面进行晒单
	 * 
	 * @param detailUuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/toShare/{detailUuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toShare(@PathVariable("detailUuid") String detailUuid,
			Model model, HttpServletRequest request) {
		// 将订单信息发送到页面
		OrderDetailModel odm = orderDetailService.getByUuid(detailUuid);
		model.addAttribute("detailModel", odm);

		if (odm != null) {
			// 将订单信息发送到页面
			OrderMainModel omm = orderMainService.getByUuid(odm
					.getOrderMainUuid());
			model.addAttribute("omm", omm);

			OrderShowWebModel webModel = new OrderShowWebModel();
			webModel.setOdm(odm);
			// 调用商品系统提供的接口根据商品id获取商品
			ProductInteractiveModel pim = this.productInteractiveService
					.getProductByUuid(odm.getProductUuid());
			if (pim != null) {
				/*ProductMainImageModel pmim = pim.getProductImage();
				if (pmim != null) {
					webModel.setProductPicUrl(pmim.getCenterImageUrl());
				}*/
			}
			model.addAttribute("oswm", webModel);
		}

		// 将订单明细列表发送到页面
		return "cms/usercenter/ordershow/OrderShowDeploy";
	}

	/**
	 * 
	 * 保存晒单信息
	 * 
	 * 如果订单明细已晒单、则不能再次晒单
	 * 
	 * @param detailUuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveShare")
	public String saveShare(OrderShowModel m, Model model,
			HttpServletRequest request) {
		String nowCountStr = request.getParameter("nowCount");
		int nowCount = Integer.parseInt(nowCountStr);
		// 晒单保存数据验证
		if (!checkSave(m, model, request, nowCount)) {
			return "redirect:/toShare/" + m.getOrderDetailUuid();
		}
		// 晒单图片处理
		List<OrderShowPicModel> ospList = doMyfiles(request, nowCount);
		m.setOspList(ospList);
		// 将订单信息发送到页面
		OrderDetailModel odm = orderDetailService.getByUuid(m
				.getOrderDetailUuid());
		if (odm != null) {
			this.myService.saveShare(odm, m);
			model.addAttribute("odm", odm);
			return "cms/usercenter/ordershow/OrderShowSuccess";
		} else {
			return "redirect:/toShare/" + m.getOrderDetailUuid();
		}
	}

	/**
	 * 晒单保存数据验证
	 * 
	 * @param m
	 * @param model
	 * @param request
	 * @return
	 */
	public boolean checkSave(OrderShowModel m, Model model,
			HttpServletRequest request, int nowCount) {
		if (StringUtil.isEmpty(m.getShowTitle())) {
			this.putErrorMsg("showTitle", MessageHelper
					.getMessage("OrderShowModel.showTitle.isEmpty"));
		}
		if (StringUtil.isEmpty(m.getShowContent())) {
			this.putErrorMsg("showContent", MessageHelper
					.getMessage("OrderShowModel.showContent.isEmpty"));
		}
		if (nowCount == 0) {
			this.putErrorMsg("myfiles",
					MessageHelper.getMessage("OrderShowModel.myfiles.isEmpty"));
		}
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 对订单晒单图片的处理封装
	 * 
	 * @param request
	 * @param nowCount
	 * @return
	 */
	public List<OrderShowPicModel> doMyfiles(HttpServletRequest request,
			int nowCount) {
		List<OrderShowPicModel> list = new ArrayList<OrderShowPicModel>();
		for (int n = 0; n < nowCount; n++) {
			OrderShowPicModel m = new OrderShowPicModel();
			String picName = request.getParameter("picName" + n);
			String picUrl = request.getParameter("picUrl" + n);
			String picDesc = request.getParameter("picDesc" + n);
			String frontCover = request.getParameter("frontCover" + n);
			m.setPicName(picName);
			m.setPicUrl(picUrl);
			m.setPicDesc(picDesc);
			m.setFrontCover(frontCover);
			list.add(m);
		}
		return list;
	}

	/**
	 * 
	 * 图片上传
	 * 
	 * @param myfiles
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/uploadFile/{nowCount}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String uploadFile(@RequestParam MultipartFile[] myfiles,
			@PathVariable("nowCount") String nowCount,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		// 校验文件是否已经上传
		String[] fileNameList = new String[1];
		String picName = "";
		for (MultipartFile myfile : myfiles) {

			// 如果上传的文件大小为0
			if (myfile.getSize() <= 0) {
				continue;
			}

			picName = "orderShow" + System.currentTimeMillis();
			fileNameList[0] = picName;
			break;
		}

		// 批量上传文件
		List<FileModel> fmList = fileUpload.uploadFiles(myfiles, fileNameList);

		for (FileModel fm : fmList) {
			model.addAttribute("picName", picName);
			model.addAttribute("remotePath", fm.getRemotePaths());
			model.addAttribute("nowCount", nowCount);
		}

		// fileService
		return "cms/usercenter/ordershow/uploadPic";
	}
}