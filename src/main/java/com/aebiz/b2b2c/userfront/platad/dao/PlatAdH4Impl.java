package com.aebiz.b2b2c.userfront.platad.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdModel;
import com.aebiz.b2b2c.userfront.platad.vo.PlatAdQueryModel;

@Repository
public class PlatAdH4Impl extends BaseH4Impl<PlatAdModel,PlatAdQueryModel> implements PlatAdDAO {

	@Override
	protected String getAppendHql(PlatAdQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		if(!StringUtil.isEmpty(qm.getCreateStartTime()) && !StringUtil.isEmpty(qm.getCreateEndTime()) ){
			hql.append(" and substr(o.createTime,1,10) >=:createStartTime and substr(o.createTime,1,10) <=:createEndTime ");
		}
		
		hql.append(super.getAppendHql(qm));
		return hql.toString();
	}
	@Override
	protected void setAppendHqlValue(PlatAdQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getCreateStartTime()) && !StringUtil.isEmpty(qm.getCreateEndTime()) ){
			q.setString("createStartTime", qm.getCreateStartTime());
			q.setString("createEndTime", qm.getCreateEndTime());
		}
		super.setAppendHqlValue(qm, q);
	}
}
