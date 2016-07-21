package com.aebiz.b2b2c.vipclub.giftpackage.service.impl;

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
import com.aebiz.b2b2c.promotion.coupontype.service.CouponTypeService;
import com.aebiz.b2b2c.promotion.coupontype.vo.CouponTypeModel;
import com.aebiz.b2b2c.promotion.coupontype.vo.CouponTypeQueryModel;
import com.aebiz.b2b2c.vipclub.giftpackage.dao.GiftPackageDAO;
import com.aebiz.b2b2c.vipclub.giftpackage.service.GiftPackageService;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageModel;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageQueryModel;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.service.GiftPackageCouponRelService;

@Service
@Transactional
public class GiftPackageServiceImpl extends
		BaseServiceImpl<GiftPackageModel, GiftPackageQueryModel> implements
		GiftPackageService {
	private GiftPackageDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	private GiftPackageCouponRelService gpcrService;

	@Autowired
	private CouponTypeService couponTypeService;

	@Autowired
	public void setMyDao(GiftPackageDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(GiftPackageModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(GiftPackageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(GiftPackageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 查询礼包关联的优惠券id
	 * 
	 * @param uuid
	 * @param start
	 * @param showPage
	 * @return
	 */
	public List<CouponTypeModel> getCouponListByGiftPackageUuid(
			GiftPackageQueryModel qm, int start, int showPage) {
		List<CouponTypeModel> list = new ArrayList<CouponTypeModel>();
		List<String> couponIds = this.gpcrService.getCouponIdsByGiftUuid(qm
				.getUuid());
		if (couponIds != null && couponIds.size() > 0) {
			// 调用促销系统提供的接口获取可关联的优惠券
			CouponTypeQueryModel ctqm = new CouponTypeQueryModel();
			ctqm.setStoreUuid(qm.getStoreUuid());
			ctqm.setCouponTypeName(qm.getCouponTypeName());
			ctqm.setInIds(couponIds);
			ctqm.setSortName(qm.getSortName());
			ctqm.setSortType(qm.getSortType());
			list = this.couponTypeService.searchCoupons(ctqm, start, showPage);
		}
		return list;
	}

	/**
	 * 查询礼包关联的优惠券数量
	 * 
	 * @param uuid
	 * @return
	 */
	public int getCouponListCountByGiftPackageUuid(GiftPackageQueryModel qm) {
		int count = 0;
		// 获取已关联的优惠券id集合
		List<String> couponIds = this.gpcrService.getCouponIdsByGiftUuid(qm
				.getUuid());

		if (couponIds != null && couponIds.size() > 0) {
			// 调用促销系统提供的接口获取可关联的优惠券
			CouponTypeQueryModel ctqm = new CouponTypeQueryModel();
			ctqm.setStoreUuid(qm.getStoreUuid());
			ctqm.setCouponTypeName(qm.getCouponTypeName());
			ctqm.setInIds(couponIds);
			count = this.couponTypeService.searchCouponsCount(ctqm);
		}

		return count;
	}

	/**
	 * 搜索礼包可以关联的优惠券<br/>
	 * 
	 * 需要把已关联的排除掉<br/>
	 * 
	 * @param uuid
	 * @param start
	 * @param showPage
	 * @return
	 */
	public List<CouponTypeModel> searchCouponList(GiftPackageQueryModel qm,
			int start, int showPage) {
		// 获取已关联的优惠券id集合
		List<String> couponIds = this.gpcrService.getCouponIdsByGiftUuid(qm
				.getUuid());

		// 调用促销系统提供的接口获取可关联的优惠券
		CouponTypeQueryModel ctqm = new CouponTypeQueryModel();
		ctqm.setStoreUuid(qm.getStoreUuid());
		ctqm.setCouponTypeName(qm.getCouponTypeName());
		ctqm.setIds(couponIds);
		ctqm.setSortName(qm.getSortName());
		ctqm.setSortType(qm.getSortType());
		List<CouponTypeModel> list = this.couponTypeService.searchCoupons(ctqm,
				start, showPage);

		return list;
	}

	/**
	 * 搜索礼包可以关联的优惠券数量<br/>
	 * 
	 * 需要把已关联的排除掉<br/>
	 * 
	 * @param qm
	 * @return
	 */
	public int searchCouponListCount(GiftPackageQueryModel qm) {
		// 获取已关联的优惠券id集合
		List<String> couponIds = this.gpcrService.getCouponIdsByGiftUuid(qm
				.getUuid());

		// 调用促销系统提供的接口获取可关联的优惠券
		CouponTypeQueryModel ctqm = new CouponTypeQueryModel();
		ctqm.setStoreUuid(qm.getStoreUuid());
		ctqm.setCouponTypeName(qm.getCouponTypeName());
		ctqm.setIds(couponIds);
		int count = this.couponTypeService.searchCouponsCount(ctqm);

		return count;
	}

	/**
	 * 给会员发送礼包成功之后需要将礼包数量减去1
	 */
	@Override
	public void reduceGiftPackageCount(GiftPackageModel giftPackageModel) {
		myDao.reduceGiftPackageCount(giftPackageModel);
	}
}