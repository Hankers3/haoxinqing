package com.aebiz.b2b2c.visitprecept.customeradvice.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceModel;
import com.aebiz.b2b2c.visitprecept.customeradvice.vo.CustomerAdviceQueryModel;

@Repository
public class CustomerAdviceH4Impl extends BaseH4Impl<CustomerAdviceModel,CustomerAdviceQueryModel> implements CustomerAdviceDAO {
	
        /*
         * 患者service
         */
        @Autowired
        private CustomerService customerService;
        /*
         * 医生的service
         */
        @Autowired
        private ServicestaffService servicestaffService;
        
    
	/**
	 * 通过uuid将处理意见保存到数据库
	 * @param customerUuid  当前数据的uuid
	 * @param refundConten   处理意见
	 * @return
	 */
	//@Override
	public void toUpdate(String customerUuid, String refundConten) {
		StringBuffer hql = new StringBuffer(
				" update CustomerAdviceModel set refundConten=:refundConten , status=:status ,refundTime=:refundTime where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("refundConten", refundConten);
		query.setString("uuid", customerUuid);
		query.setString("status", "1");
		query.setString("refundTime", DateFormatHelper.getNowTimeStr());

		query.executeUpdate();
		
	}
	
	/**
	 * 对搜索的关键字进行sql语句追加
	 */
	@Override
	protected String getAppendHql(CustomerAdviceQueryModel qm) {
		StringBuffer sql=new StringBuffer();
		
		if( qm.getCustomerName1()!=null){
			sql.append(" and o.customerUuid in (:customerUuids) ");
		}
		sql.append(super.getAppendHql(qm));
		return sql.toString();
	}
	
	/**
	 * 对搜索的关键字进行sql语句追加 进行赋值
	 */
	@Override
	protected void setAppendHqlValue(CustomerAdviceQueryModel qm, Query q) {
		if(qm.getCustomerName1()!=null ){
		    //患者的uuids
		    List<String> cuuids = customerService.getCustomerUuids(qm.getCustomerName1());
		    //医生的uuids
		    List<String> duuids = servicestaffService.getDoctorUuids(qm.getCustomerName1());
		    //医生和患者id都为空的情况
		    if(!(null!=cuuids&&cuuids.size()>0)&&!(null!=duuids&&duuids.size()>0)){
		        String[] nuuids = {"ABC","CBA"};   
		        q.setParameterList("customerUuids", nuuids); 
		    } else {
                        q.setParameterList("customerUuids", duuids);
                        q.setParameterList("customerUuids", cuuids);
                    }
		}
		super.setAppendHqlValue(qm, q);
	}


}
