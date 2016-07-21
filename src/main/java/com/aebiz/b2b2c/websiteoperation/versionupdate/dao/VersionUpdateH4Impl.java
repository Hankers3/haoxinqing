package com.aebiz.b2b2c.websiteoperation.versionupdate.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateModel;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateQueryModel;

@Repository
public class VersionUpdateH4Impl extends BaseH4Impl<VersionUpdateModel,VersionUpdateQueryModel> implements VersionUpdateDAO {

	/**
	 * 获取最发布的应用
	 * @param versionType
	 * @return
	 */
	@Override
	public VersionUpdateModel getVersionUpdateModel(String versionType){
		StringBuffer hql = new StringBuffer(" from VersionUpdateModel as o where o.versionType=:versionType ");
		hql.append(" order by o.createTime desc ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("versionType", versionType);
		List list = q.list();
		if(list !=null &&list.size()>0 ){
			return  (VersionUpdateModel) list.get(0);
		}
		
		return null;
	}

}
