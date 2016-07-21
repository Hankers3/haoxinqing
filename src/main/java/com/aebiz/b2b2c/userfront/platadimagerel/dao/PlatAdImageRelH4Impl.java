package com.aebiz.b2b2c.userfront.platadimagerel.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelModel;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelQueryModel;

@Repository
public class PlatAdImageRelH4Impl
		extends
			BaseH4Impl<PlatAdImageRelModel, PlatAdImageRelQueryModel>
		implements
			PlatAdImageRelDAO {

	/**
	 * 根据广告的uuid获取该广告关联的所有关联关系的uuid
	 * @param adUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getPlatAdImageRelModelUuidsByAdUuid(String adUuid){
		StringBuffer hql = new StringBuffer();
		hql.append("select p.uuid from PlatAdImageRelModel p where p.adUuid =:adUuid order by p.position asc ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("adUuid", adUuid);
		
		return query.list();
		
	}
	
	/**
	 * 根据广告的uuid获取该广告关联的所有的图片
	 * @param adUuid
	 * @return 
	 * List<PlatAdImageRelModel>
	 */
	public List<PlatAdImageRelModel> getPlatAdImageRelModelsByAdUuid(String adUuid){
		StringBuffer hql = new StringBuffer();
		hql.append("from PlatAdImageRelModel p where p.adUuid =:adUuid order by p.position asc ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("adUuid", adUuid);
		
		return query.list();
		
	}

	@Override
	public void updateCc(String adUuid) {
		
		
	}
}
