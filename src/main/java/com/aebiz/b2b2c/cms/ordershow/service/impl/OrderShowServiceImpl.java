package com.aebiz.b2b2c.cms.ordershow.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.ordershow.dao.OrderShowDAO;
import com.aebiz.b2b2c.cms.ordershow.service.OrderShowService;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowModel;
import com.aebiz.b2b2c.cms.ordershow.vo.OrderShowQueryModel;
import com.aebiz.b2b2c.cms.ordershowpic.service.OrderShowPicService;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;
import com.aebiz.b2b2c.cms.sysback.CMSconst;
import com.aebiz.b2b2c.cms.usercenter.web.ordershow.vo.OrderShowWebModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.product.interactive.product.service.ProductInteractiveService;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;

@Service
@Transactional
public class OrderShowServiceImpl extends
		BaseServiceImpl<OrderShowModel, OrderShowQueryModel> implements
		OrderShowService {
	private OrderShowDAO myDao = null;

	@Autowired
	private OrderShowPicService orderShowPicService;

	@Autowired
	private CustomerInteractive customerInteractive;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private ProductInteractiveService productInteractiveService;

	@Autowired
	private WebsiteOperateInteractive woInteractive;

	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OrderShowDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderShowModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderShowModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderShowModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 平台后台获取晒单列表<br/>
	 * 
	 * 调用订单系统提供的接口获取订单号、商品名称<br/>
	 * 
	 * 调用商品系统提供的接口获取商品编号<br/>
	 * 
	 */
	@Override
	public List<OrderShowModel> getByCondition(OrderShowQueryModel qm,
			int start, int page) {
		List<OrderShowModel> osms = new ArrayList<OrderShowModel>();
		List<OrderShowModel> list = this.myDao.getByCondition(qm, start, page);
		if (list != null && list.size() > 0) {
			for (OrderShowModel osm : list) {
				// 调用订单系统提供的接口获取订单明细
				OrderDetailModel orderDetailModel = this.orderDetailService
						.getByUuid(osm.getOrderDetailUuid());
				if (orderDetailModel != null) {
					osm.setOrderUuid(orderDetailModel.getOrderMainUuid());
					osm.setProductName(orderDetailModel.getProductName());
					// 调用商品系统提供的接口获取商品编号
					ProductInteractiveModel productModel = this.productInteractiveService
							.getProductByUuid(orderDetailModel.getProductUuid());
					if (productModel != null) {
						osm.setProductNo(productModel.getProductMain()
								.getProductNo());
					}
				}
				// 调用会员系统提供的接口获取会员名称
				CustomerModel customerModel = this.customerInteractive
						.getCustomerModelByCondition(osm.getCustomerUuid());
				if (customerModel != null) {
					osm.setCustomerName(customerModel.getCustomerName());
				}
				osms.add(osm);
			}
		}
		return osms;
	}

	/**
	 * 通过订单明细的UUID查询对应的晒单记录
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderShowModel getOrderShowModelByDetailUuid(String orderDetailUuid) {
		return myDao.getOrderShowModelByDetailUuid(orderDetailUuid);
	}

	/**
	 * 通过订单明细的UUID查询对应的晒单记录和晒单的图片
	 * 
	 * @param orderDetailUuid
	 * @return
	 */
	public OrderShowModel getOrderShowModelAndPicsByDetailUuid(
			String orderDetailUuid) {
		OrderShowModel osm = this
				.getOrderShowModelByDetailUuid(orderDetailUuid);

		if (osm != null) {
			List<OrderShowPicModel> ospmList = orderShowPicService
					.getOrderShowPicModelByShowUuid(osm.getUuid());

			osm.setOspList(ospmList);
		}

		return osm;
	}

	/**
	 * 
	 * 保存晒单、同时保存晒单图片
	 * 
	 * 调用会员系统提供的接口保存晒单人名称
	 * 
	 * @param odm
	 */
	public void saveShare(OrderDetailModel odm, OrderShowModel m) {
		m.setOrderDetailUuid(odm.getUuid());
		m.setProductUuid(odm.getProductUuid());
		m.setCustomerUuid(LoginUserHelper.getCustomerLoginUserUuid());
		// 调用会员系统提供的接口保存晒单人名称
		CustomerModel cm = this.customerInteractive
				.getCustomerModelByCondition(m.getCustomerUuid());
		if (cm != null) {
			m.setCustomerName(cm.getCustomerName());
		}
		m.setShowTime(DateFormatHelper.getNowTimeStr());

		// 调用运营系统提供的接口 获取会员晒单是否需要平台审核配置
		String mark = this.woInteractive
				.getValueByKey(CMSconst.ORDER_SHOW_AUDIT);
		if (CMSconst.ORDER_SHOW_AUDIT_YES.equals(mark)) {
			m.setState(OrderShowModel.STATE_REVIEW_WEI);
		} else {
			m.setState(OrderShowModel.STATE_REVIEW_SUCCESS);
		}

		this.create(m);
		// 保存晒单图片
		List<OrderShowPicModel> ospList = m.getOspList();
		if (ospList != null && ospList.size() > 0) {
			for (OrderShowPicModel opm : ospList) {
				opm.setOrderShowUuid(m.getUuid());
				this.orderShowPicService.create(opm);
			}
		}
	}

	/**
	 * 
	 * 晒单审核、审核不通过需要保存审核不通过原因
	 * 
	 * @param m
	 */
	public void saveReview(OrderShowModel m) {
		m.setReviewUser(LoginUserHelper.getLoginUserUuid());
		m.setReviewTime(DateFormatHelper.getNowTimeStr());
		this.update(m);
	}

	/**
	 * 
	 * 获取每个订单明细和商品图片的封装数据model集合
	 * 
	 * @param detailList
	 * @return
	 */
	public List<OrderShowWebModel> getOrderDetailWebModels(
			List<OrderDetailModel> detailList) {
		List<OrderShowWebModel> list = new ArrayList<OrderShowWebModel>();
		if (detailList != null && detailList.size() > 0) {
			for (OrderDetailModel odm : detailList) {
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
				list.add(webModel);
			}
		}
		return list;
	}

	/**
	 * 通过商品的UUID查询对应的晒单记录和晒单的图片
	 * 
	 * @param productUuid
	 * @return
	 */
	public List<OrderShowModel> getOrderShowModelAndPicsByProductUuid(
			String productUuid, int start, int showPage) {
		List<OrderShowModel> list = this.myDao.getOrderShowModelAndPicsByProductUuid(
				productUuid, start, showPage);
		if (list != null && list.size() > 0) {
			for (OrderShowModel osm : list) {
				if (osm != null) {
					List<OrderShowPicModel> ospmList = orderShowPicService
							.getOrderShowPicModelByShowUuid(osm.getUuid());

					osm.setOspList(ospmList);
				}
			}
		}

		return list;
	}

	/**
	 * 通过商品的UUID查询对应的晒单数量
	 * 
	 * @param productUuid
	 * @return
	 */
	public int getOrderShowModelCountAndPicsByProductUuid(String productUuid) {
		return this.myDao
				.getOrderShowModelCountAndPicsByProductUuid(productUuid);
	}
}