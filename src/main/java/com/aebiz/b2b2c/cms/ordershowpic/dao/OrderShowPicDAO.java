package com.aebiz.b2b2c.cms.ordershowpic.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicModel;
import com.aebiz.b2b2c.cms.ordershowpic.vo.OrderShowPicQueryModel;

public interface OrderShowPicDAO extends
		BaseDAO<OrderShowPicModel, OrderShowPicQueryModel> {

	/**
	 * 通过晒单的编号获得晒单所有的图片
	 * 
	 * @param orderShowUuid
	 * @return
	 */
	public List<OrderShowPicModel> getOrderShowPicModelByShowUuid(
			String orderShowUuid);
}