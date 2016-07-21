package com.aebiz.b2b2c.finance.withdrawapply.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.withdrawapply.util.DateUtil;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;

@Repository
public class WithdrawApplyH4Impl extends
		BaseH4Impl<WithdrawApplyModel, WithdrawApplyQueryModel> implements
		WithdrawApplyDAO {

	/**
	 * 查询提现记录
	 */
	public List<WithdrawApplyModel> getApplyList(String customerUuid,
			String time, int pageNo, int pageCount) {
		Date dateTime = DateFormatHelper.toDate(DateFormatHelper
				.getNowTimeStr("yyyy-MM-dd 00:00:00"));
		StringBuffer sb = new StringBuffer(
				" from WithdrawApplyModel wam where wam.userUuid =:userUuid");
		if (WithdrawApplyModel.LAST_ONEWEEK.equals(time)
				|| WithdrawApplyModel.LAST_ONEMONTH.equals(time)
				|| WithdrawApplyModel.LAST_THREEMONTH.equals(time)
				|| WithdrawApplyModel.LAST_HALFYEAR.equals(time)) {
			sb.append(" and wam.successTime >=:startSuccessTime and wam.successTime <:endSuccessTime");
		}
		Query query = this.getH4Session().createQuery(sb.toString());
		query.setString("userUuid", customerUuid);
		if (WithdrawApplyModel.LAST_ONEWEEK.equals(time)) {
			query.setString("startSuccessTime", DateFormatHelper.getTimeStr(
					"yyyy-MM-dd 00:00:00",
					DateFormatHelper.getDateBefore(dateTime, 7)));
			query.setString("endSuccessTime", DateFormatHelper.getTimeStr(
					"yyyy-MM-dd 00:00:00",
					DateFormatHelper.getDateAfter(dateTime, 1)));
		} else if (WithdrawApplyModel.LAST_ONEMONTH.equals(time)) {
			query.setString("startSuccessTime", DateFormatHelper.getTimeStr(
					"yyyy-MM-dd 00:00:00",
					DateFormatHelper.getDateBefore(dateTime, 30)));
			query.setString("endSuccessTime", DateFormatHelper.getTimeStr(
					"yyyy-MM-dd 00:00:00",
					DateFormatHelper.getDateAfter(dateTime, 1)));
		} else if (WithdrawApplyModel.LAST_THREEMONTH.equals(time)) {
			query.setString("startSuccessTime", DateFormatHelper.getTimeStr(
					"yyyy-MM-dd 00:00:00",
					DateFormatHelper.getDateBefore(dateTime, 90)));
			query.setString("endSuccessTime", DateFormatHelper.getTimeStr(
					"yyyy-MM-dd 00:00:00",
					DateFormatHelper.getDateAfter(dateTime, 1)));
		} else if (WithdrawApplyModel.LAST_HALFYEAR.equals(time)) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			query.setString(
					"startSuccessTime",
					DateFormatHelper.getTimeStr("yyyy-MM-dd 00:00:00",
							DateUtil.addMonths(timestamp, -6)));
			query.setString("endSuccessTime", DateFormatHelper.getTimeStr(
					"yyyy-MM-dd 00:00:00",
					DateFormatHelper.getDateAfter(dateTime, 1)));
		}
		if (pageCount != 0 && pageNo != 0) {
			query.setFirstResult((pageNo - 1) * pageCount);
			query.setMaxResults(pageCount);
		}
		return query.list();
	}

	@Override
	protected String getMultiModel() {
		return " ,ServicestaffModel as s ";
	}

	@Override
	protected String getAppendHql(WithdrawApplyQueryModel qm) {
		StringBuffer hql = new StringBuffer(" and o.userUuid = s.uuid  and s.regState='1' ");
		
		if (!StringUtil.isEmpty(qm.getDoctorNameq())) {
			hql.append(" and s.realName like:realName ");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMobileq())) {
			hql.append(" and s.mobile like:mobile ");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			hql.append(" order by o.applyTime desc");
		}

		return hql.toString();
	}

	@Override
	protected void setAppendHqlValue(WithdrawApplyQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getDoctorNameq())) {
			q.setString("realName", "%" + qm.getDoctorNameq() + "%");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMobileq())) {
			q.setString("mobile", "%" + qm.getDoctorMobileq() + "%");
		}
	}

	/**
	 * 通过医生查询
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<WithdrawApplyModel> getByDoctorUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(" from WithdrawApplyModel  as o ");
		hql.append(" where o.userUuid =:doctorUuid ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setString("doctorUuid", doctorUuid);
		List<WithdrawApplyModel> list = q.list();
		if (list != null && list.size() > 0)
			return list;
		else
			return null;
	}

}
