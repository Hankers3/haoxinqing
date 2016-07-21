package com.aebiz.b2b2c.visitprecept.visitcustomercommon.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonModel;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonQueryModel;


public interface VisitCustomerCommonService extends BaseService<VisitCustomerCommonModel,VisitCustomerCommonQueryModel>{
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
