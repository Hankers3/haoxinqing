package com.aebiz.b2b2c.vipclub.giftpackage.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageModel;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageQueryModel;

public interface GiftPackageDAO extends
		BaseDAO<GiftPackageModel, GiftPackageQueryModel> {
	/**
	 * 给会员发送礼包成功之后需要将礼包数量减去1
	 * 
	 * @param giftPackageModel
	 */
	public void reduceGiftPackageCount(GiftPackageModel giftPackageModel);
}