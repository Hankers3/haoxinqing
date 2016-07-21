package com.aebiz.b2b2c.finance.usercenter.dao.usercenterwithdrawapply;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;

@Repository
public class UserCenterWithdrawApplyH4Impl extends
		BaseH4Impl<WithdrawApplyModel, WithdrawApplyQueryModel> implements
		UserCenterWithdrawApplyDAO {

	/* 三个月内 */
	private static final String TL = "1";

	/* 三个月前 */
	private static final String TM = "2";

	/* 一年内的 */
	private static final String TY = "3";

	/**
	 * 用于时间的计算<br/>
	 * 如当前时间为2014-12-12，当time为 3 时，返回的为2014-09-12
	 * 
	 * @param int time 数字
	 */
	private String getResultDate(String time) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -Integer.parseInt(time));// 月份减一
		return DateFormatHelper.getTimeStr(calendar.getTime());
	}

	@Override
	protected String getAppendHql(WithdrawApplyQueryModel qm) {
		StringBuffer whereHql = new StringBuffer();
		if (qm != null) {

			if (qm.getUserUuid() != null) {
				whereHql.append(" and userUuid = :userUuid ");
			}

			if (qm.getTime().equals(TL)) {// 三个月内
				whereHql.append(" and  applyTime >= :applyTime ");
			}

			if (qm.getTime().equals(TM)) {// 三个月前
				whereHql.append(" and applyTime  < :applyTime ");
			}

			if (qm.getTime().equals(TY)) {// 一年内
				whereHql.append(" and applyTime >= :applyTime ");
			}
		}
		return whereHql.toString() + super.getAppendHql(qm);
	}

	@Override
	protected void setAppendHqlValue(WithdrawApplyQueryModel qm, Query q) {

		if (qm.getUserUuid() != null) {
			q.setParameter("userUuid", qm.getUserUuid());
		}

		if (!StringUtil.isEmpty(qm.getTime())) {
			q.setParameter("applyTime", this.getResultDate(qm.getTime()));
		}
	}
	
	/**
	 * 查询会员申请在某段时间内的总数
	 * @param customerUuid
	 * @param state 0:未处理  1:体现成功  2:体现失败
	 * @param timeType 1:3个月内  2:三个月前 3:一年内
	 * @return  int
	 */
	public int getCustomerWithdrawAppllyCount(String customerUuid,String state,String timeType){
		StringBuffer hql = new StringBuffer(" from WithdrawApplyModel w where 1=1 ");
		hql.append(" and w.userType =:userType ");
		hql.append(" and w.userUuid =:userUuid ");
		
		if(!StringUtil.isEmpty(state)){
			hql.append(" and w.state =:state ");
		}
		if(!StringUtil.isEmpty(timeType)){
			if (timeType.equals(TL)) {// 三个月内
				hql.append(" and  w.applyTime >= :applyTime ");
			}
			
			if (timeType.equals(TM)) {// 三个月前
				hql.append(" and w.applyTime  < :applyTime ");
			}
			
			if (timeType.equals(TY)) {// 一年内
				hql.append(" and w.applyTime >= :applyTime ");
			}
		}
		
		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setString("userType", WithdrawApplyModel.USERTYPE_CUSTOMER);
		query.setString("userUuid", customerUuid);
		
		if(!StringUtil.isEmpty(state)){
			query.setString("state", state);
		}
		
		if(!StringUtil.isEmpty(timeType)){
			if (timeType.equals(TL)) {// 三个月内
				query.setString("applyTime", this.getResultDate("3"));
			}
			
			if (timeType.equals(TM)) {// 三个月前
				query.setString("applyTime", this.getResultDate("3"));
			}
			
			if (timeType.equals(TY)) {// 一年内
				query.setString("applyTime", this.getResultDate("12"));
			}
		}
		List list = query.list();
		if(list != null && list.size()> 0){
			return list.size();
		}
		return 0;
	}
	
	/**
	 * 查询会员在某段时间内的体现申请,带分页
	 * @param customerUuid
	 * @param state
	 * @param timeType
	 * @param fromPage
	 * @param pageShow
	 * @return 
	 * List<WithdrawApplyModel>
	 */
	public List<WithdrawApplyModel> getCustomerWithdrawAppllyList(String customerUuid,String state,String timeType,String fromPage,String pageShow){
		StringBuffer hql = new StringBuffer(" from WithdrawApplyModel w where 1=1 ");
		hql.append(" and w.userType =:userType ");
		hql.append(" and w.userUuid =:userUuid ");
		
		if(!StringUtil.isEmpty(state)){
			hql.append(" and w.state =:state ");
		}
		if(!StringUtil.isEmpty(timeType)){
			if (timeType.equals(TL)) {// 三个月内
				hql.append(" and  w.applyTime >= :applyTime ");
			}
			
			if (timeType.equals(TM)) {// 三个月前
				hql.append(" and w.applyTime  < :applyTime ");
			}
			
			if (timeType.equals(TY)) {// 一年内
				hql.append(" and w.applyTime >= :applyTime ");
			}
		}
		
		hql.append(" order by w.successTime desc,w.applyTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setString("userType", WithdrawApplyModel.USERTYPE_CUSTOMER);
		query.setString("userUuid", customerUuid);
		
		if(!StringUtil.isEmpty(state)){
			query.setString("state", state);
		}
		
		if(!StringUtil.isEmpty(timeType)){
			if (timeType.equals(TL)) {// 三个月内
				query.setString("applyTime", this.getResultDate("3"));
			}
			
			if (timeType.equals(TM)) {// 三个月前
				query.setString("applyTime", this.getResultDate("3"));
			}
			
			if (timeType.equals(TY)) {// 一年内
				query.setString("applyTime", this.getResultDate("12"));
			}
		}
		
		query.setFirstResult(Integer.parseInt(fromPage));
		query.setMaxResults(Integer.parseInt(pageShow));
		
		return (List<WithdrawApplyModel>)query.list();
	}
}
