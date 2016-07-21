package com.aebiz.b2b2c.visitprecept.visitcustomercommon.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonModel;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonQueryModel;

public interface VisitCustomerCommonDAO extends BaseDAO<VisitCustomerCommonModel,VisitCustomerCommonQueryModel>{

	/**
	 * 通过医生uuid获取医生的常用项列表
	 * @author xp
	 * @param doctorid
	 * @return
	 */
  public List<VisitCustomerCommonModel> getCustomerCommonListByDoctorid(
			String doctorid);

	/**
	 * 通过医生uuid删除医生的常用项列表
	 * @author xp
	 * @param customerCommonUuid
	 * @return
	 */
 public void deleteCustomerCommonModelByUuid(String customerCommonUuid);

}