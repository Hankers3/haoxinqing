package com.aebiz.b2b2c.visitprecept.visitcustomercommon.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonModel;
import com.aebiz.b2b2c.visitprecept.visitcustomercommon.vo.VisitCustomerCommonQueryModel;


@Repository
public class VisitCustomerCommonH4Impl extends BaseH4Impl<VisitCustomerCommonModel,VisitCustomerCommonQueryModel> implements VisitCustomerCommonDAO {
	
	/**
	 * 通过医生uuid获取医生的常用项列表
	 * @author xp
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<VisitCustomerCommonModel> getCustomerCommonListByDoctorid(
			String doctorid) {
	     StringBuffer hql = new StringBuffer(" from VisitCustomerCommonModel as v where v.serviceStatffUuid =:doctorid order by v.createTime");
	        Query query = getH4Session().createQuery(hql.toString());
	        query.setString("doctorid", doctorid);
	        List list = query.list();
	        if(list != null && list.size()>0)
	            return list;
	        else
	            return null;
	}
	
	/**
	 * 通过医生uuid删除医生的常用项列表
	 * @author xp
	 * @param customerCommonUuid
	 * @return
	 */
	@Override
	public void deleteCustomerCommonModelByUuid(String customerCommonUuid) {
		
		StringBuffer hql = new StringBuffer(
	 " delete from  VisitCustomerCommonModel where uuid=:customerCommonUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerCommonUuid", customerCommonUuid);
		query.executeUpdate();
	}
	

}
