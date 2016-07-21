package com.aebiz.b2b2c.vipclub.vipclubintegrallog.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.QueryTimeType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;

/**
 * 会员积分日志DAO
 * 
 * @author huyingying
 * 
 */
@Repository
public class VipclubIntegralLogH4Impl extends BaseH4Impl<VipclubIntegralLogModel, VipclubIntegralLogQueryModel>
		implements VipclubIntegralLogDAO {
	/**
	 * 通过会员id查询会员日志记录
	 * 
	 * @param customerUuid
	 * @return tempList<VipclubIntegralLogModel>
	 */
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid) {

		// 判断会员Uuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {

			return null;
		}
		// 拼接按照会员Uuid查询的hql语句
		StringBuffer hql = new StringBuffer(
				"select o from VipclubIntegralLogModel  o  where o.customerUuid=:customerUuid order by o.opeTime desc");

		Query q = getH4Session().createQuery(hql.toString());

		// 设置会员Uuid的值
		q.setString("customerUuid", customerUuid);

		return q.list();
	}

	public List<String> getUuidsByCustomerUuid(String customerUuid) {
		// 判断会员Uuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {

			return null;
		}
		// 拼接按照会员Uuid查询的hql语句
		StringBuffer hql = new StringBuffer(
				"select o.uuid from VipclubIntegralLogModel  o  where o.customerUuid=:customerUuid order by o.opeTime desc");

		Query q = getH4Session().createQuery(hql.toString());

		// 设置会员Uuid的值
		q.setString("customerUuid", customerUuid);

		return q.list();
	}

	@Override
	/**
	 * 通过日期范围查询会员积分日志记录,重写getAppendHql方法
	 * 
	 * @param qm
	 * @return tempList<VipclubIntegralLogModel>
	 */
	protected String getAppendHql(VipclubIntegralLogQueryModel qm) {
		StringBuffer hql = new StringBuffer("");

		// 判断qm是否为空
		if (qm != null) {

			// 最小时间不为空，拼接的hql语句
			if (!StringUtil.isEmpty(qm.getMinTime()) && !StringUtil.isEmpty(qm.getMaxTime())) {
				hql.append(" and o.createTime > :minTime and o.createTime < :maxTime ");
			}

			// 最大时间不为空，拼接的hql语句
			if (!StringUtil.isEmpty(qm.getCustomerUuid())) {
				hql.append(" and o.customerUuid = :customerUuid ");
			}

			// 查询记录时间的hql语句
			if (!StringUtil.isEmpty(qm.getQueryTime()) && !StringUtil.isEmpty(qm.getQueryTimeType())) {

				// 拼接查询三个月以内和一年以内的查询语句
				if (qm.getQueryTimeType().equals(QueryTimeType.IN_THREE_MONTH.getValue())
						|| qm.getQueryTimeType().equals(QueryTimeType.IN_ONE_YEAR.getValue())) {
					hql.append(" and o.createTime > :createTime ");
				}

				// 拼接查询三个月以前的查询语句
				if (qm.getQueryTimeType().equals(QueryTimeType.OUT_THREE_MONTH.getValue())) {
					hql.append(" and o.createTime < :createTime ");
				}
			}

			// 查询记录类型为增加的hql语句
			if ("add".equals(qm.getIntegrals())) {
				hql.append(
						" and ( o.intergralType = :intergralType_get_by_order or o.intergralType = :intergralType_get_by_register or o.intergralType = :intergralType_get_by_comment or o.intergralType = :intergralType_get_by_plateform ) ");
			}

			// 查询记录类型为减少的hql语句
			if ("reduce".equals(qm.getIntegrals())) {
				hql.append(
						" and ( o.intergralType = :intergralType_reduce_by_pay or o.intergralType = :intergralType_reduce_by_lottery or o.intergralType = :intergralType_reduce_by_plateform ) ");
			}
		}

		// 添加排序语句
		return hql.append(" order by o.createTime desc ").toString();
	}

	/**
	 * 通过日期范围查询会员积分日志记录,重写setAppendHql方法
	 * 
	 * @param qm
	 *            ,q
	 */
	protected void setAppendHqlValue(VipclubIntegralLogQueryModel qm, Query q) {

		// 判断qm是否为空
		if (qm != null) {

			// 最小，最大时间不为空时，为最大，最小时间设置值
			if (!StringUtil.isEmpty(qm.getMinTime()) && !StringUtil.isEmpty(qm.getMaxTime())) {
				q.setString("minTime", qm.getMinTime());
				q.setString("maxTime", qm.getMaxTime());
			}

			// 会员Uuid不为空时，为会员Uuid设置值
			if (!StringUtil.isEmpty(qm.getCustomerUuid())) {
				q.setString("customerUuid", qm.getCustomerUuid());
			}

			// 查询增加类型的积分日志记录设置值
			if ("add".equals(qm.getIntegrals())) {
				q.setString("intergralType_get_by_order", IntegralType.GET_BY_ORDER.getValue());
				q.setString("intergralType_get_by_register", IntegralType.GET_BY_REGISTER.getValue());
				q.setString("intergralType_get_by_plateform", IntegralType.GET_BY_PLATEFORM.getValue());
				q.setString("customerUuid", qm.getCustomerUuid());
			}

			// 设置查询时间的值
			if (!StringUtil.isEmpty(qm.getQueryTime()) && !StringUtil.isEmpty(qm.getQueryTimeType())) {
				q.setString("createTime", qm.getQueryTime());
			}

			// 查询减少类型的积分日志记录设置值
			if ("reduce".equals(qm.getIntegrals())) {
				q.setString("intergralType_reduce_by_pay", IntegralType.REDUCE_BY_PAY.getValue());
				q.setString("intergralType_reduce_by_plateform", IntegralType.REDUCE_BY_PLATEFORM.getValue());
				q.setString("customerUuid", qm.getCustomerUuid());
			}
		}
	}

	/**
	 * 通过会员查询model，查询该类型会员积分总数
	 * 
	 * @param qm
	 * @return int
	 */
	public int getTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm) {

		// 判断会员Uuid是否为空
		if (StringUtil.isEmpty(qm.getCustomerUuid())) {
			return 0;
		}

		// 拼接查询符合条件的记录数的查询语句
		StringBuffer hql = new StringBuffer("select sum(o.intergralCount) from VipclubIntegralLogModel  o  where 1=1 ");
		hql.append(getAppendHql(qm));
		Query q = getH4Session().createQuery(hql.toString());
		setAppendHqlValue(qm, q);

		// 返回查询结果记录数
		Object object = q.uniqueResult();
		if (object != null) {
			return ((Number) object).intValue();
		}
		return 0;
	}

	/**
	 * 通过会员模糊查询会员日志记录数
	 * 
	 * @param qm
	 *            ,
	 * @return int
	 */
	public int getVipclubIntegralLogModelListCountByConditon(VipclubIntegralLogQueryModel qm) {

		return super.getCount(qm);
	}

	/**
	 * 通过会员id查询会员日志记录
	 * 
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid, int pageCount, int pageNo) {
		// 判断会员Uuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			return null;
		}
		// 拼接按照会员Uuid查询的hql语句
		StringBuffer hql = new StringBuffer(
				" from VipclubIntegralLogModel  o  where o.customerUuid=:customerUuid order by o.createTime desc");

		Query q = getH4Session().createQuery(hql.toString());
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			q.setFirstResult((pageNo - 1) * pageCount);// //从下标为0开始
			q.setMaxResults(pageCount);
		}
		// 设置会员Uuid的值
		q.setString("customerUuid", customerUuid);

		List<VipclubIntegralLogModel> tempList = q.list();

		if (tempList != null && tempList.size() > 0) {
			return tempList;
		}
		return null;
	}

	/**
	 * 根据医生id获得用户积分日志实体类
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<VipclubIntegralLogModel> getbyDoctorUuid(String doctorUuid) {
		// 判断Uuid是否为空
		if (StringUtil.isEmpty(doctorUuid)) {
			return null;
		}
		// 拼接按照Uuid查询的hql语句
		StringBuffer hql = new StringBuffer(
				" from VipclubIntegralLogModel  o  where o.customerUuid=:doctorUuid order by o.createTime desc");

		Query q = getH4Session().createQuery(hql.toString());

		// 设置会员Uuid的值
		q.setString("doctorUuid", doctorUuid);

		List<VipclubIntegralLogModel> tempList = q.list();

		if (tempList != null && tempList.size() > 0) {
			return tempList;
		}
		return null;
	}

	/**
	 * 根据患者的id获取患者的积分记录
	 */

	@Override
	public List<VipclubIntegralLogModel> getVipclubIntegralLogModelListByUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(" from VipclubIntegralLogModel crm where  crm.customerUuid=:customerUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		List<VipclubIntegralLogModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据患者的id获取患者积分的uuids
	 */
	@Override
	public List<String> getVipclubIntegralLogModelUuids(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select crm.uuid from VipclubIntegralLogModel crm where  crm.customerUuid=:customerUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 
	 * @Description: (根据用户类型以及用户id和描述获取日志对象)
	 * @author XP
	 * @param doctorid
	 * @param userType
	 * @param description
	 * @return
	 * @date 2016-1-19 下午1:54:17
	 */
	@Override
	public VipclubIntegralLogModel getVipclubIntegralLogByConditions(String doctorid, String userType,
			String intergralType, String description) {
		StringBuffer hql = new StringBuffer(" from VipclubIntegralLogModel crm where 1=1 ");
		if (!StringUtil.isEmpty(doctorid)) {
			hql.append(" and crm.customerUuid=:customerUuid ");
		}
		if (!StringUtil.isEmpty(userType)) {
			hql.append(" and crm.userType=:userType ");
		}
		if (!StringUtil.isEmpty(intergralType)) {
			hql.append(" and crm.intergralType=:intergralType ");
		}
		if (!StringUtil.isEmpty(description)) {
			hql.append(" and crm.searchType=:search ");
		}
		// 类型为登陆增加积分时，判断时间
		if ("1".equals(userType) || "2".equals(userType)) {
			hql.append(" and crm.createTime like:nowTime ");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		if ("1".equals(userType) || "2".equals(userType)) {
			String nowTime = DateFormatHelper.getNowTimeStr().substring(0, 10);
			query.setString("nowTime", "%" + nowTime + "%");
		}
		if (!StringUtil.isEmpty(doctorid)) {
			query.setString("customerUuid", doctorid);
		}

		if (!StringUtil.isEmpty(description)) {
			// 查询用字段
			query.setString("search", description);
		}
		if (!StringUtil.isEmpty(userType)) {
			query.setString("userType", userType);
		}
		if (!StringUtil.isEmpty(intergralType)) {
			query.setString("intergralType", intergralType);
		}
		List<VipclubIntegralLogModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public VipclubIntegralLogModel getVipclubIntegralLogByquizResultUuid(String customerUuid, String userType,
			String searchType) {
		StringBuffer hql = new StringBuffer(
				" from VipclubIntegralLogModel crm where crm.customerUuid=:customerUuid and crm.userType=:userType "
						+ "and crm.searchType=:searchType and createTime like:nowTime ");
		Query query = this.getH4Session().createQuery(hql.toString());
		String nowTime = DateFormatHelper.getNowTimeStr().substring(0, 9);
		query.setString("nowTime", "%" + nowTime + "%");
		query.setString("customerUuid", customerUuid);
		query.setString("userType", userType);
		query.setString("searchType", searchType);
		List<VipclubIntegralLogModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
