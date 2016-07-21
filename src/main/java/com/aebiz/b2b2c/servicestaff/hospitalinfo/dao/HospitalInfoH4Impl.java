package com.aebiz.b2b2c.servicestaff.hospitalinfo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import sun.swing.StringUIClientPropertyKey;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoQueryModel;

@Repository
public class HospitalInfoH4Impl extends BaseH4Impl<HospitalInfoModel, HospitalInfoQueryModel>
		implements HospitalInfoDAO {
	/**
	 * 获取通过编号医院名
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public String getHospitalNameByUuid(String uuid) {
		StringBuffer hql = new StringBuffer(" select o.hospitalName from HospitalInfoModel as o where 1=1 ");
		hql.append(" and o.uuid =:uuid ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", uuid);
		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	/**
	 * 获取所有医院信息
	 * 
	 * @return
	 */
	@Override
	public List<HospitalInfoModel> getHospitals() {
		StringBuffer hql = new StringBuffer(" from HospitalInfoModel as o  where 1=1 ");
		Query q = this.getH4Session().createQuery(hql.toString());

		List<HospitalInfoModel> list = q.list();
		return list;
	}

	/**
	 * 获取编号通过医院名
	 * 
	 * @param hospitalName
	 * @return
	 */
	@Override
	public String getUuidByHospitalName(String hospitalName) {
		StringBuffer hql = new StringBuffer(" select o.uuid from HospitalInfoModel as o where 1=1 ");
		hql.append(" and o.hospitalName =:hospitalName ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("hospitalName", hospitalName);
		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	@Override
	public List<String> getAllUuids() {
		StringBuffer hql = new StringBuffer(" select o.uuid from HospitalInfoModel as o   ");
		hql.append(" order by o.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		List<String> uuids = q.list();
		if (uuids != null && uuids.size() > 0) {
			return uuids;
		}
		return null;
	}

	/**
	 * 通过区id得到医院
	 * 
	 * @param regionUuid
	 * @return
	 */
	@Override
	public List<HospitalInfoModel> getByRegionUuid(String regionUuid) {
		StringBuffer hql = new StringBuffer(" from HospitalInfoModel as o  where o.region =:regionUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("regionUuid", regionUuid);
		List<HospitalInfoModel> obj = q.list();
		if (obj != null && obj.size() > 0) {
			return obj;
		}
		return null;
	}

	/**
	 * 
	 * @Description: (根据医院的名称获取医院的uuids)
	 * @author XP
	 * @param hospitalName
	 * @return
	 * @date 2015-12-31 下午6:40:07
	 */
	@Override
	public List<String> getAllUuidsByHospitalName(String hospitalName) {
		StringBuffer hql = new StringBuffer(" select o.uuid from HospitalInfoModel as o where 1=1 ");
		hql.append(" and o.hospitalName like:hospitalName ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("hospitalName", "%" + hospitalName + "%");
		List list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<HospitalInfoModel> getByCityUuid(String regionUuid) {
		StringBuffer hql = new StringBuffer(" from HospitalInfoModel as o  where o.city =:regionUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("regionUuid", regionUuid);
		List<HospitalInfoModel> obj = q.list();
		if (obj != null && obj.size() > 0) {
			return obj;
		}
		return null;
	}
	
	/**
	 * 查询医院信息
	 * @param provinceUuid
	 * @param cityUuid
	 * @param regionUuid
	 * @return
	 */
	@Override
	public List<HospitalInfoModel> getByProvinceUuid(String provinceUuid,String cityUuid,String regionUuid){
		StringBuffer hql = new StringBuffer(" from HospitalInfoModel as o  where 1=1 ");
		if(!StringUtil.isEmpty(provinceUuid)){
			hql.append(" and o.province =:provinceUuid");
		}
		if(!StringUtil.isEmpty(cityUuid)){
			hql.append(" and o.city =:cityUuid");
		}
		if(!StringUtil.isEmpty(regionUuid)){
			hql.append(" and o.region =:regionUuid");
		}
		hql.append(" order by o.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(provinceUuid)){
			q.setString("provinceUuid", provinceUuid);
		}
		if(!StringUtil.isEmpty(cityUuid)){
			q.setString("cityUuid", cityUuid);
		}
		if(!StringUtil.isEmpty(regionUuid)){
			q.setString("regionUuid", regionUuid);
		}
		
		List<HospitalInfoModel> obj = q.list();
		if (obj != null && obj.size() > 0) {
			return obj;
		}
		return null;
	}
}
