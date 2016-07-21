package com.aebiz.b2b2c.websiteoperation.concern.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernQueryModel;

@Repository
public class ConcernH4Impl extends BaseH4Impl<ConcernModel, ConcernQueryModel>
		implements ConcernDAO {
	/**
	 * 通过患者id得到患者关注医生表
	 * 
	 * @param costomerUuid
	 * @return
	 */
	@Override
	public List<ConcernModel> getByCustomerUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select o.uuid,o.opeTime,o.delFlag,o.customerUuid,o.doctorUuid,o.createTime,o.state from ConcernModel o ,ServicestaffModel as s  where  o.doctorUuid =s.uuid and  o.customerUuid=:customerUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		List<ConcernModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 查看患者是否关注该医生
	 */
	@Override
	public int getConcernType(String doctorId, String customerId) {
		StringBuffer hql = new StringBuffer(
				" select count(distinct o.uuid) from ConcernModel as o where o.doctorUuid=:doctorId and o.customerUuid=:customerId ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorId", doctorId);
		q.setString("customerId", customerId);
		return ((Number) q.uniqueResult()).intValue();
	}

	/**
	 * 获取关注该医生的粉丝数
	 */
	@Override
	public int getConcernNumByDoctorId(String doctorId) {
		StringBuffer hql = new StringBuffer(
				" select count(distinct o.uuid) from ConcernModel as o where o.doctorUuid=:doctorId ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorId", doctorId);
		return ((Number) q.uniqueResult()).intValue();
	}

	@Override
	public ConcernModel getByCustomerAndDoctorUuid(String customerUuid,
			String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				"from ConcernModel o where o.customerUuid=:customerUuid and o.doctorUuid=:doctorUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		query.setString("doctorUuid", doctorUuid);
		List<ConcernModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

    @Override
    public List<String> getAllConcernUuids(String customerUuid) {
        StringBuffer hql = new StringBuffer(
                "select crm.uuid from ConcernModel crm , ServicestaffModel as s where  crm.doctorUuid =s.uuid and  crm.customerUuid=:customerUuid ");
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("customerUuid", customerUuid);
            List<String> list = query.list();
            if (list != null && list.size() > 0) {
                    return list;
            }
            return null;
    }

}
