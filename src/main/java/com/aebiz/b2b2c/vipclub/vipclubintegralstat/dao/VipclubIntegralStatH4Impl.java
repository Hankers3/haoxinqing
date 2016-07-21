package com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatQueryModel;

/**
 * 会员积分统计表DAO
 * 
 * @author huyingying
 * 
 */
@Repository
public class VipclubIntegralStatH4Impl extends BaseH4Impl<VipclubIntegralStatModel, VipclubIntegralStatQueryModel>implements VipclubIntegralStatDAO {
	/**
	 * 拼接查询对象
	 * @param qm
	 * @return
	 */
	protected String getMultiModelQm(VipclubIntegralStatQueryModel qm) {
		if(!StringUtil.isEmpty(qm.getUserType())&&"2".equals(qm.getUserType())){
			return " , ServicestaffModel as s ";
		}else{
			return "";
		}
		
	}
	
	//
	@Override
	protected String getAppendHql(VipclubIntegralStatQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		
		if(!StringUtil.isEmpty(qm.getUserType())&&"2".equals(qm.getUserType())){
			hql.append(" and o.customerUuid =s.uuid ");
		}
		if(!StringUtil.isEmpty(qm.getUserType())){
			hql.append(" and o.userType =:userType");
		}
		
		return  hql.toString();
	}
	protected String getAppendHqlOrderBy(VipclubIntegralStatQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by  o.intergralCount  " + qm.getSortType());
		}
		return  hql.toString();
	}
	
	
	
	
	/**
	 * 列表查询按医生编号分组
	 * @param qm
	 * @return
	 */
	private String getAppendHqlGroup(VipclubIntegralStatQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		
		hql.append(" group by o.customerUuid  ");
		return  hql.toString();
	}
	/**
	 * 设置查询值
	 */
	@Override
	protected void setAppendHqlValue(VipclubIntegralStatQueryModel qm, Query q) {
		if(!StringUtil.isEmpty(qm.getUserType())){
			q.setString("userType", qm.getUserType());
		}
	}
	
	@Override
	public List<VipclubIntegralStatModel> getByCondition(
			VipclubIntegralStatQueryModel qm, int start, int pageShow) {
		
		 return getByCondition(false, true, qm, start, pageShow);
	}
	
	/**
	 * 获取总数
	 */
	@Override
	public int getCount(VipclubIntegralStatQueryModel qm) {
		    String hql = "select count(distinct o.customerUuid ) from  VipclubIntegralStatModel as  o" + getMultiModelQm(qm) + " where 1=1 ";
		    
		    hql = hql + prepareHql(qm);
		   // hql = hql + getAppendHql(qm);
		    
		    Query q = getH4Session().createQuery(hql);
		    setValue(qm, q);
		    setAppendHqlValue(qm, q);
		    
		    return ((Number)q.uniqueResult()).intValue();
	}
	
	
	private List getByCondition(boolean onlyUuids, boolean needPage, VipclubIntegralStatQueryModel qm, int start, int pageShow){
	    String hql = "select o" + getMultiSelect() + " from VipclubIntegralStatModel as  o " + getMultiModelQm(qm) + " where 1=1 ";
	    
	    if (onlyUuids) {
	      hql = "select o.uuid from VipclubIntegralStatModel as  o " + getMultiModelQm(qm) + " where 1=1 ";
	    }
	    
	    hql = hql + prepareHql(qm);
	    hql = hql + getAppendHql(qm);
	    hql = hql + getAppendHqlGroup(qm);
	    hql = hql + getAppendHqlOrderBy(qm);
	    
	    Query q = getH4Session().createQuery(hql);
	    setValue(qm, q);
	    setAppendHqlValue(qm, q);
	    
	    if (needPage) {
	      q.setFirstResult(start);
	      q.setMaxResults(pageShow);
	    }
	    
	    if (onlyUuids) {
	      return q.list();
	    }
	    
	    if ((getMultiSelect() != null) && (getMultiSelect().trim().length() > 0)) {
	      List<Object[]> tempList = q.list();
	      return exeResultList(tempList);
	    }
	    List<VipclubIntegralStatModel> retList = q.list();
	    return retList;
	  }
	
	
	/**
	 * 通过会员id查询会员积分统计表
	 * 
	 * @param customerUuid
	 * @return tempList<VipclubIntegralStatModel>
	 */
	public List<VipclubIntegralStatModel> getByCustomerUuid(String customerUuid) {
		
		//判断会员Uuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			return null;
		}
		
		//拼接按照会员Uuid查询的hql语句
		StringBuffer hql = new StringBuffer("select o from VipclubIntegralStatModel  o  where o.customerUuid = :customerUuid order by o.opeTime desc");
		
		Query q = getH4Session().createQuery(hql.toString());
		
		//设置会员Uuid的值
		q.setString("customerUuid", customerUuid);

		return q.list();
	}
	
	public List<String> getUuidsByCustomerUuid(String customerUuid){
		//判断会员Uuid是否为空
		if (StringUtil.isEmpty(customerUuid)) {
			return null;
		}
		
		//拼接按照会员Uuid查询的hql语句
		StringBuffer hql = new StringBuffer("select o.uuid from VipclubIntegralStatModel  o  where o.customerUuid = :customerUuid order by o.opeTime desc");
		
		Query q = getH4Session().createQuery(hql.toString());
		
		//设置会员Uuid的值
		q.setString("customerUuid", customerUuid);

		return q.list();
	}

	/**
	 * 通过会员id,积分类型查询会员积分统计表
	 * 
	 * @param customerUuid
	 * @return integralStatModel
	 */
	public VipclubIntegralStatModel getByCustomerUuidAndIntegralType(String customerUuid, String integralType) {
		
		//判断会员Uuid,积分类型是否为空
		if (StringUtil.isEmpty(customerUuid)|| StringUtil.isEmpty(integralType)) {	
			return null;
		}
		
		//拼接按照会员Uuid和积分类型查询的hql语句
		String hql = "select o from VipclubIntegralStatModel o where o.customerUuid = :customerUuid and o.intergralType = :integralType ";
		
		Query q = getH4Session().createQuery(hql);
		
		//设置会员Uuid的值
		q.setString("customerUuid", customerUuid);
		
		//设置积分类型的值
		q.setString("integralType", integralType);
		
		Object obj = q.uniqueResult();
		if(obj != null){
			return (VipclubIntegralStatModel)obj;
		}
		return null;
	}
	
	public String getUuidByCustomerUuidAndIntegralType(String customerUuid, String integralType){
		//判断会员Uuid,积分类型是否为空
		if (StringUtil.isEmpty(customerUuid)|| StringUtil.isEmpty(integralType)) {	
			return null;
		}
		
		//拼接按照会员Uuid和积分类型查询的hql语句
		String hql = "select o.uuid from VipclubIntegralStatModel o where o.customerUuid = :customerUuid and o.intergralType = :integralType ";
		Query q = getH4Session().createQuery(hql);
		
		//设置会员Uuid的值
		q.setString("customerUuid", customerUuid);
		
		//设置积分类型的值
		q.setString("integralType", integralType);
		
		List<String> uuids = q.list();
		if(uuids != null && uuids.size()>0){
			return (String)uuids.get(0);
		}
		return "";
	}
	
	/**
	 * 根据会员uuid查询会员可用积分数
	 */
	public int getUsaleIntegralByCustomerUuid(String customerUuid){
		//判断会员Uuid,积分类型是否为空
		if (StringUtil.isEmpty(customerUuid)) {	
			return 0;
		}

		//拼接按照会员Uuid和积分类型查询的hql语句
		String hql = "select o.intergralCount from VipclubIntegralStatModel o where o.customerUuid = :customerUuid and o.intergralType = :integralType ";

		Query q = getH4Session().createQuery(hql);

		//设置会员Uuid的值
		q.setString("customerUuid", customerUuid);

		//设置积分类型的值
		q.setString("integralType", "1");

		return ((Number)q.uniqueResult()).intValue();
	}

	/**
	 * 通过会员id,积分类型查询会员积分
	 * 
	 * @param customerUuid
	 * @param integralType
	 * @return
	 */
	@Override
	public int getVipclubIntegralCount(String customerUuid, String integralType) {
		
		int integral =0;
		//判断会员Uuid,积分类型是否为空
		if (StringUtil.isEmpty(customerUuid)|| StringUtil.isEmpty(integralType)) {	
			return integral;
		}
		
		//拼接按照会员Uuid和积分类型查询的hql语句
		String hql = "select o from VipclubIntegralStatModel o where o.customerUuid = :customerUuid and o.intergralType = :integralType order by o.opeTime desc";
		Query q = getH4Session().createQuery(hql);
		
		//设置会员Uuid的值
		q.setString("customerUuid", customerUuid);
		//设置积分类型的值
		q.setString("integralType", integralType);
		
		VipclubIntegralStatModel integralStatModel = (VipclubIntegralStatModel) q.uniqueResult();
		//判断结果是否为空
		if (integralStatModel != null) {	
			 integral =integralStatModel.getIntergralCount();
		}
		
		return integral;
	}
}
