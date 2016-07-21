package com.aebiz.b2b2c.visitprecept.visitprecept.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptQueryModel;

@Repository
public class VisitPreceptH4Impl extends BaseH4Impl<VisitPreceptModel, VisitPreceptQueryModel>
		implements VisitPreceptDAO {

	@Autowired
	private HospitalInfoService hospitalInfoService;

	/**
	 * 
	 * @Description: (拼接查询条件)
	 * @author XP
	 * @return
	 * @date 2015-12-31 下午4:48:30
	 */
	@Override
	protected String getMultiModel() {

		return " , ServicestaffModel as s  ";
	}

	/**
	 * 
	 * @Description: (拼接多条件查询的语句)
	 * @author XP
	 * @param qm
	 * @return
	 * @date 2015-12-31 下午4:50:26
	 */
	@Override
	protected String getAppendHql(VisitPreceptQueryModel qm) {
		StringBuffer hql = new StringBuffer(" and o.serviceStaffUuid = s.uuid ");
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			hql.append(" order by o.createTime desc");
		}
		// 医生的姓名
		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			hql.append(" and s.realName like:doctorName ");
		}
		// 医生的手机号
		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			hql.append(" and s.mobile like:doctorMobile ");
		}
		// 医生所在医院
		if (!StringUtil.isEmpty(qm.getHospitalName())) {
			hql.append(" and s.hospital in (:uuids) ");
		}
		return hql.toString();
	}

	/**
	 * 
	 * @Description: (给查询条件赋值)
	 * @author XP
	 * @param qm
	 * @param q
	 * @date 2015-12-31 下午4:52:29
	 */
	@Override
	protected void setAppendHqlValue(VisitPreceptQueryModel qm, Query q) {
		// 医生的姓名
		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			q.setString("doctorName", "%" + qm.getDoctorName() + "%");
		}
		// 医生的手机号
		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			q.setString("doctorMobile", "%" + qm.getDoctorMobile() + "%");
		}
		// 医生所在医院
		if (!StringUtil.isEmpty(qm.getHospitalName())) {
			List<String> uuids = hospitalInfoService.getAllUuidsByHospitalName(qm.getHospitalName());
			if (uuids != null && uuids.size() > 0) {
				q.setParameterList("uuids", uuids);
			} else {
				String[] uuid = new String[] { "a", "b", "c", "d", "e" };
				q.setParameterList("uuids", uuid);
			}
		}

	}

	/**
	 * 根据医生的id获取所有的方案名称
	 *
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<VisitPreceptModel> getAllVisitpreceptByDoctorid(String doctorid) {
		String hql = new String("from VisitPreceptModel o where o.serviceStaffUuid=:doctorid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("doctorid", doctorid);
		List<VisitPreceptModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过医生id获取医生的随访方案（推荐）
	 * @return VisitPreceptModel
	 */
	@Override
	public List<VisitPreceptModel> getRecommendVisitpreceptByDoctorid() {
		String hql = new String("from VisitPreceptModel o where recommend='1'");
		Query query = this.getH4Session().createQuery(hql);
		List<VisitPreceptModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过医生id获取医生的随访方案（非推荐）
	 *
	 * @param doctorid doctorid
	 * @return null
	 */
	@Override
	public List<VisitPreceptModel> getMyVisitpreceptByDoctorid(String doctorid) {
		String hql = new String("from VisitPreceptModel o where recommend='0' and o.serviceStaffUuid=:doctorid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("doctorid", doctorid);
		List<VisitPreceptModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过编号获取随访方案名称
	 * 
	 * @return 方案名称
	 */
	@Override
	public String getPreceptNameByUUid(String preceptUuid) {
		if (StringUtil.isEmpty(preceptUuid)) {
			return "";
		}
		String hql = new String(" select o.preceptName from VisitPreceptModel o where o.uuid=:uuid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("uuid", preceptUuid);

		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return "";
	}

	@Override
	public List<String> getAllVisitpreceptUuidsByDoctorid(String doctorid) {
		String hql = new String(" select o.uuid from VisitPreceptModel o where o.serviceStaffUuid=:doctorid ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("doctorid", doctorid);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据随访方案的id删除随访方案
	 * 
	 * @param visitUuid
	 */
	@Override
	public void deleteVisitPreceptServiceByUuid(String visitUuid) {
		String hql = new String("update  VisitPreceptModel set delFlag =:delFlag where uuid =:visitUuid");
		Query query = getH4Session().createQuery(hql);
		query.setString("delFlag", "2");
		query.setString("visitUuid", visitUuid);
		query.executeUpdate();

	}

	/**
	 * 根据随访方案的id删除随访方案的数据
	 * 
	 * @param visitUuid
	 */
	@Override
	public void deleteVisitPrecept(String visitUuid) {
		String hql = new String("delete from VisitPreceptModel where uuid =:visitUuid");
		Query query = getH4Session().createQuery(hql);
		query.setString("visitUuid", visitUuid);
		query.executeUpdate();
	}

}
