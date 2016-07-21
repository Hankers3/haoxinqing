package com.aebiz.b2b2c.vipclub.giftpackage.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.promotion.coupontype.vo.CouponTypeModel;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageModel;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageQueryModel;

public interface GiftPackageService extends
		BaseService<GiftPackageModel, GiftPackageQueryModel> {

	/**
	 * 查询礼包关联的优惠券id
	 * 
	 * @param uuid
	 * @param start
	 * @param showPage
	 * @param mark
	 *            是否分页
	 * @return
	 */
	public List<CouponTypeModel> getCouponListByGiftPackageUuid(
			GiftPackageQueryModel qm, int start, int showPage);

	/**
	 * 查询礼包关联的优惠券数量
	 * 
	 * @param uuid
	 * @return
	 */
	public int getCouponListCountByGiftPackageUuid(GiftPackageQueryModel qm);

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
			int start, int showPage);

	/**
	 * 搜索礼包可以关联的优惠券数量<br/>
	 * 
	 * 需要把已关联的排除掉<br/>
	 * 
	 * @param qm
	 * @return
	 */
	public int searchCouponListCount(GiftPackageQueryModel qm);

	/**
	 * 给会员发送礼包成功之后需要将礼包数量减去1
	 * 
	 * @param giftPackageModel
	 */
	public void reduceGiftPackageCount(GiftPackageModel giftPackageModel);
}
