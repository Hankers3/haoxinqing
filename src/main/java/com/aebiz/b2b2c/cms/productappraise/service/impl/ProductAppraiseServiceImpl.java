package com.aebiz.b2b2c.cms.productappraise.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.productappraise.dao.ProductAppraiseDAO;
import com.aebiz.b2b2c.cms.productappraise.service.ProductAppraiseService;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseModel;
import com.aebiz.b2b2c.cms.productappraise.vo.ProductAppraiseQueryModel;
import com.aebiz.b2b2c.cms.sysback.CMSconst;
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.usercenter.web.productappraise.vo.ProductAppraiseWebModel;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.product.interactive.product.service.ProductInteractiveService;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveQueryModel;
import com.aebiz.b2b2c.product.interactive.productcategoryplatform.service.impl.ProductCategoryPlatformInteractiveServiceImpl;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;

@Service
@Transactional
public class ProductAppraiseServiceImpl extends
		BaseServiceImpl<ProductAppraiseModel, ProductAppraiseQueryModel>
		implements ProductAppraiseService {
	private ProductAppraiseDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	private TagsService tagsService;

	@Autowired
	private ProductInteractiveService productInteractiveService;

	@Autowired
	private ProductCategoryPlatformInteractiveServiceImpl pcpfInteractiveServiceImpl;

	@Autowired
	private CustomerInteractive customerInteractive;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private WebsiteOperateInteractive woInteractive;

	@Autowired
	public void setMyDao(ProductAppraiseDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductAppraiseModel m) {
		m.setUuid(us.getNextUuid("ProductAppraise", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		m.setCustomerUuid(LoginUserHelper.getCustomerLoginUserUuid());
		CustomerModel customerModel = this.customerInteractive
				.getCustomerModelByCondition(m.getCustomerUuid());
		if (customerModel != null) {
			m.setCustomerName(customerModel.getCustomerName());
		}

		// 调用运营系统提供的接口 获取会员评价商品是否需要平台审核配置
		String mark = this.woInteractive
				.getValueByKey(CMSconst.PRODUCT_APP_AUDIT);
		if (CMSconst.PRODUCT_APP_AUDIT_YES.equals(mark)) {
			m.setState(ProductAppraiseModel.STATE_REVIEW_WEI);
		} else {
			m.setState(ProductAppraiseModel.STATE_REVIEW_SUCCESS);
		}

		m.setAppTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ProductAppraiseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ProductAppraiseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 根据订单明细集合获取每条订单明细对应的标签集合<br/>
	 * 
	 * 然后把数据封装到ProductAppraiseWebModel中发送到页面
	 * 
	 * @param ods
	 * @return
	 */
	public List<ProductAppraiseWebModel> getOrderDetailAndTags(
			List<OrderDetailModel> ods) {
		List<ProductAppraiseWebModel> list = new ArrayList<ProductAppraiseWebModel>();
		for (OrderDetailModel odm : ods) {
			ProductAppraiseWebModel wm = new ProductAppraiseWebModel();
			wm.setOdm(odm);
			// 调用商品系统提供的接口根据商品id获取商品
			ProductInteractiveModel pim = this.productInteractiveService
					.getProductByUuid(odm.getProductUuid());
			// 获取商品评价信息根据明细id
			ProductAppraiseModel pam = this.myDao
					.getProductAppByOrderDetailUuid(odm.getUuid());
			if (pam == null) {
				wm.setAppraiseOrNo(ProductAppraiseWebModel.APPRAISE_NO);
				if (pim != null) {
					/*ProductMainImageModel pmim = pim.getProductImage();
					if (pmim != null) {
						wm.setProductPicUrl(pmim.getCenterImageUrl());
					}*/
				}
				list.add(wm);
			} else {
				wm.setAppraiseOrNo(ProductAppraiseWebModel.APPRAISE_YES);
				if (pim != null) {
					/*ProductMainImageModel pmim = pim.getProductImage();
					if (pmim != null) {
						wm.setProductPicUrl(pmim.getCenterImageUrl());
					}*/
					String[] tagStr = pam.getAppTag().split(",");
					List<String> tags = Arrays.asList(tagStr);
					pam.setTags(tags);
					wm.setPam(pam);
				}
				// 调用会员系统提供的接口获取会员的头像
				String customerImage = this.customerInteractive
						.getCustomerInfoRemoteImageUrlByCustomerUuid(pam
								.getCustomerUuid());
				wm.setCustomerImage(customerImage);

				list.add(wm);
			}

		}
		return list;
	}

	/**
	 * 
	 * 获取会员中心的评价列表数据
	 * 
	 * @param qm
	 * @param start
	 * @param page
	 * 
	 * @return
	 */
	public List<ProductAppraiseWebModel> getProductAppList(
			ProductAppraiseQueryModel qm, int start, int page) {
		List<ProductAppraiseWebModel> list = new ArrayList<ProductAppraiseWebModel>();

		// 获取会员中心的评价列表数据
		List<ProductAppraiseModel> proApps = this.myDao.getByCondition(qm,
				start, page);

		if (proApps != null && proApps.size() > 0) {
			for (ProductAppraiseModel app : proApps) {
				ProductAppraiseWebModel wm = new ProductAppraiseWebModel();
				// 调用订单系统提供的接口获取订单明细
				OrderDetailModel odm = this.orderDetailService.getByUuid(app
						.getOrderDetailUuid());
				if (odm != null) {
					wm.setOdm(odm);
					// 调用商品系统提供的接口根据商品id获取商品
					ProductInteractiveModel pim = this.productInteractiveService
							.getProductByUuid(odm.getProductUuid());
					if (pim != null) {
						/*ProductMainImageModel pmim = pim.getProductImage();
						if (pmim != null) {
							wm.setProductPicUrl(pmim.getCenterImageUrl());
						}*/
						String[] tagStr = app.getAppTag().split(",");
						List<String> tags = Arrays.asList(tagStr);
						app.setTags(tags);
						wm.setPam(app);
					}
				}
				list.add(wm);
			}
		}
		return list;
	}

	/**
	 * 平台后台、搜索商品评价列表
	 * 
	 * 调用商品系统配合商品名称的搜索
	 * 
	 * @param qm
	 * @return
	 */
	@Override
	public List<ProductAppraiseModel> getByCondition(
			ProductAppraiseQueryModel qm, int start, int pageShow) {

		// 如果搜索条件中商品名称搜索、则调用商品系统提供的接口查询
		if (!StringUtil.isEmpty(qm.getProductName())) {
			ProductInteractiveQueryModel piqm = new ProductInteractiveQueryModel();
			piqm.setProductName(qm.getProductName());
			List<ProductInteractiveModel> pims = this.productInteractiveService
					.searchProduct(piqm, 0, 0);

			if (pims != null && pims.size() > 0) {
				// 获取满足商品名称搜索条件的商品id集合
				List<String> productIds = new ArrayList<String>();
				for (ProductInteractiveModel pim : pims) {
					productIds.add(pim.getProductMain().getUuid());
				}
				qm.setProductIds(productIds);
			} else {
				return new ArrayList<ProductAppraiseModel>();
			}
		}
		return this.myDao.getByCondition(qm, start, pageShow);
	}

	/**
	 * 平台后台、搜索商品评价列表数据量
	 * 
	 * 调用商品系统配合商品名称的搜索
	 * 
	 * @param qm
	 * @return
	 */
	@Override
	public int getCount(ProductAppraiseQueryModel qm) {

		// 如果搜索条件中商品名称搜索、则调用商品系统提供的接口查询
		if (!StringUtil.isEmpty(qm.getProductName())) {
			ProductInteractiveQueryModel piqm = new ProductInteractiveQueryModel();
			piqm.setProductName(qm.getProductName());
			List<ProductInteractiveModel> pims = this.productInteractiveService
					.searchProduct(piqm, 0, 0);

			if (pims != null && pims.size() > 0) {
				// 获取满足商品名称搜索条件的商品id集合
				List<String> productIds = new ArrayList<String>();
				for (ProductInteractiveModel pim : pims) {
					productIds.add(pim.getProductMain().getUuid());
				}
				qm.setProductIds(productIds);
			} else {
				return 0;
			}
		}
		return this.myDao.getCount(qm);
	}

	/**
	 * 
	 * 保存平台审核商品评价的信息
	 * 
	 * @param m
	 */
	public void saveReview(ProductAppraiseModel m) {
		m.setReviewUserUuid(LoginUserHelper.getCustomerLoginUserUuid());
		m.setReviewTime(DateFormatHelper.getNowTimeStr());

		this.update(m);
	}
}