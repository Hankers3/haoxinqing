package com.aebiz.b2b2c.vipclub.giftpackagecouponrel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.dao.GiftPackageCouponRelDAO;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.service.GiftPackageCouponRelService;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.vo.GiftPackageCouponRelQueryModel;

@Service
@Transactional
public class GiftPackageCouponRelServiceImpl
		extends
		BaseServiceImpl<GiftPackageCouponRelModel, GiftPackageCouponRelQueryModel>
		implements GiftPackageCouponRelService {
	private GiftPackageCouponRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(GiftPackageCouponRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(GiftPackageCouponRelModel m) {
		m.setUuid(us.getNextUuid("GiftPackageCouponRel", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(GiftPackageCouponRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(GiftPackageCouponRelModel m) {
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
	public List<String> getCouponIdsByGiftUuid(String uuid) {
		return this.myDao.getCouponIdsByGiftUuid(uuid);
	}
	
	/**
	 * 
	  * 保存礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param couponIds
	 *            优惠券id集合
	 */
	public void saveChooseCoupon(String uuid,List<String> couponIds){
		for(String couponId:couponIds){
			GiftPackageCouponRelModel m = new GiftPackageCouponRelModel();
			m.setGiftPackageUuid(uuid);
			m.setCouponUuid(couponId);
			this.create(m);
		}
	}
	
	/**
	 * 
	  * 删除礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param couponIds
	 *            优惠券id集合
	 */
	public void removeRelate(String uuid,List<String> couponIds){
		for(String couponId:couponIds){
			this.myDao.removeRelate(uuid,couponId);
		}
	}
}