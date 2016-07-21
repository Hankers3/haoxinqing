package com.aebiz.b2b2c.websiteoperation.favorite.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteQueryModel;

@Repository
public class FavoriteH4Impl extends
		BaseH4Impl<FavoriteModel, FavoriteQueryModel> implements FavoriteDAO {
	// 重写父类的方法
	public List<FavoriteModel> getByConditionq(FavoriteQueryModel qm, int start,
			int pageShow) {

		return getByCondition(false, true, qm, start, pageShow);
	}
	
	public String getMultiModel(FavoriteQueryModel qm) {
		if (qm.getUserType().equals("1")) {
		    return " , ServicestaffModel as  s where 1=1 and o.userId = s.uuid ";
		} else {
		    return " , CustomerModel as  c  where 1=1 and o.userId = c.uuid ";
		}
	};

	// 重写父类的方法自己拼写sql语句
	private List getByCondition(boolean onlyUuids, boolean needPage,
			FavoriteQueryModel qm, int start, int pageShow) {
		StringBuffer hql = new StringBuffer("select o ");
		hql.append(" from FavoriteModel o ");
		hql.append(getMultiModel(qm));
		
		hql = hql.append(prepareHql(qm));
		hql = hql.append(getAppendHql(qm));

		Query q = getH4Session().createQuery(hql.toString());
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		if (needPage) {
			q.setFirstResult(start);
			q.setMaxResults(pageShow);
		}

		if (onlyUuids) {
			return q.list();
		}

		if ((getMultiSelect() != null)
				&& (getMultiSelect().trim().length() > 0)) {
			List tempList = q.list();
			return exeResultList(tempList);
		}
		List retList = q.list();
		return retList;
	}

	
	// 拼接查询条件用的
	@Override
	protected String getAppendHql(FavoriteQueryModel qm) {

		StringBuffer hql = new StringBuffer("");
		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			hql.append(" and s.realName like:doctorName ");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			hql.append(" and s.mobile like:doctorMobile ");
		}

		if (!StringUtil.isEmpty(qm.getCustomerNameq())) {
			hql.append(" and c.customerName like:customerNameq ");
		}
		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			hql.append(" and c.mobile like:customerMobile ");
		}
		if (!StringUtil.isEmpty(qm.getUserType())) {
		    hql.append(" and o.userType =:userType ");
		}
		if(!StringUtil.isEmpty(qm.getNeedFavoriteModel())){
			hql.append(" and o.contentUuid = c.uuid ' ");
		}
		if (qm != null) {
		    hql.append(" order by o.").append(qm.getSortName()).append(" ").append(qm.getSortType());
		     }else {
                   hql.append(" order by o.opeTime desc ");
		     }
		return hql.toString();

	}

	// 设置参数的值
	@Override
	protected void setAppendHqlValue(FavoriteQueryModel qm, Query q) {
	    
		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			q.setString("doctorName", "%" + qm.getDoctorName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctorMobile())) {
			q.setString("doctorMobile", "%" + qm.getDoctorMobile() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerNameq())) {
			q.setString("customerNameq", "%" + qm.getCustomerNameq() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			q.setString("customerMobile", "%" + qm.getCustomerMobile() + "%");
		}
		if (!StringUtil.isEmpty(qm.getUserType())) {
		    q.setString("userType",  qm.getUserType());
		}
		super.setAppendHqlValue(qm, q);
	}

	// 重写count方法
	public int getCount(FavoriteQueryModel qm) {
		String hql = "select count(o) from  FavoriteModel  as  o "
				+ getMultiModel(qm);
		//hql = hql +" and o.uuid !=''";
		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);
		
		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		return ((Number) q.uniqueResult()).intValue();
	}

	// 根据用户id查询用户的所有收藏
	@Override
	public List<FavoriteModel> getFavoriteModelListByCustomerUuid(
			String doctorid) {
		StringBuffer hql = new StringBuffer(
		" from FavoriteModel as o where 1=1 and o.userId=:userId and o.state =:state ");
		hql.append(" order by o.favoriteTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("userId", doctorid);
		q.setString("state", "1");

		List<FavoriteModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据收藏id值将收藏删除
	@Override
	public void deleteFavoriteByUuid(String favoriteUuid) {
		String hql = new String(
				"delete  FavoriteModel  where uuid =:favoriteUuid");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("favoriteUuid", favoriteUuid);
		query.executeUpdate();
	}

	/**
	 * 查看医生是否收藏该
	 * 
	 * @param favoriteUuid
	 */
	@Override
	public int getStoreTypeByDoctorUuidAndContenUuid(String doctorUuid,
			String uuid) {
		StringBuffer hql = new StringBuffer(
				" select count(distinct o.uuid) from FavoriteModel as o where o.userId=:doctorId and o.contentUuid=:uuid and o.state =:state ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorId", doctorUuid);
		q.setString("uuid", uuid);
		q.setString("state", "1");
		Object obj =q.uniqueResult();
		if(obj!=null){
		    return ((Number) obj).intValue();
		}
		return 0;
	}

	/**
	 * 通过视频主键查看医生的收藏数量
	 * 
	 * @param vedioUuid
	 * @return
	 */
	@Override
	public int getNumByVedioUuid(String vedioUuid) {
		StringBuffer hql = new StringBuffer(
				" select count(o.uuid) from FavoriteModel as o where o.videoUuid=:videoUuid and o.userType=:userType and o.state =:state ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("videoUuid", vedioUuid);
		/* 用户类型 1：医生 2：患者 */
		q.setString("userType", "1");
		q.setString("state", "1");
		Object obj =q.uniqueResult();
                if(obj!=null){
                    return ((Number) obj).intValue();
                }
                return 0;
	}

	/**
	 * 根据医生的id获取所有的收藏的uuids
	 */
	@Override
	public List<String> getAllFavoriteModelUuids(String doctorid) {
		StringBuffer hql = new StringBuffer(
				"select crm.uuid from FavoriteModel crm where crm.userId=:doctorid and crm.state =:state ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("doctorid", doctorid);
		query.setString("state", "1");
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 查看医生是否收藏该视频
	 * 
	 * @param favoriteUuid
	 */
	@Override
	public int getcolVideoByDoctorUuidAndContenUuid(String doctorUuid,
			String videoUuid) {
		StringBuffer hql = new StringBuffer(
				" select count(distinct o.uuid) from FavoriteModel as o where o.userId=:doctorId and o.videoUuid=:videoUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorId", doctorUuid);
		q.setString("videoUuid", videoUuid);
		Object obj =q.uniqueResult();
        if(obj!=null){
            return ((Number) obj).intValue();
        }
        return 0;
	}

	@Override
	public void deleteByDoctorUuidAndVideouuid(String doctorUuid,
			String videoUuid) {
		StringBuffer hql = new StringBuffer(
				" delete from FavoriteModel where userId=:doctorId and videoUuid=:videoUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorId", doctorUuid);
		q.setString("videoUuid", videoUuid);
	}

	/**
	 * 根据患者的id和收藏状态获取收藏列表
	 */
	@Override
	public List<FavoriteModel> getFavoriteModelListByCustomerUuidAndState(
			String customerUuid, String state) {
		StringBuffer hql = new StringBuffer(
				"  from FavoriteModel as o where o.userId=:customerUuid and o.state=:state ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", customerUuid);
		q.setString("state", state);
		List<FavoriteModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
         * 
         */
	@Override
	public int getNumByContentUuid(String contentUuid, String type) {
		StringBuffer hql = new StringBuffer(
				" select count(distinct o.userId) from FavoriteModel as o where 1=1 ");
		// 代表类型的收藏人数
		if (type.equals("1")) {
			hql.append(" and o.contentUuid =:contentUuid ");
			Query q = this.getH4Session().createQuery(hql.toString());
			q.setString("contentUuid", contentUuid);
			Object obj =q.uniqueResult();
	                if(obj!=null){
	                    return ((Number) obj).intValue();
	                }
	                return 0;
		} else {
			hql.append(" and o.videoUuid =:videoUuid ");
			Query q = this.getH4Session().createQuery(hql.toString());
			q.setString("videoUuid", contentUuid);
			Object obj =q.uniqueResult();
	                if(obj!=null){
	                    return ((Number) obj).intValue();
	                }
	                return 0;
		}
	}

	/**
	 * 关注状态 根据患者id和文章id查关注状态
	 * 
	 * @param customerUuid
	 * @param contextUuid
	 * @return 如果患者已关注则返回值 未关注返回null
	 */
	@Override
	public String getUuidByCustomerUuidAndContextUuid(String customerUuid,
			String contextUuid) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from FavoriteModel as o where o.userId=:customerUuid and o.contentUuid=:contextUuid ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("customerUuid", customerUuid);
		q.setString("contextUuid", contextUuid);
		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return null;
	}
	/**
     * 
     * @Description: (根据医生的id和视频的id获取收藏的uuid)    
     * @author XP  
     * @param doctorUuid
     * @param videoUuid
     * @return
     * @date 2015-12-31 下午5:20:35
     */
    @Override
    public String getUuidByCustomerUuidAndVideoUuid(String doctorUuid, String videoUuid) {
        StringBuffer hql = new StringBuffer(
                " select o.uuid from FavoriteModel as o where o.videoUuid=:videoUuid ");
	        if(!StringUtil.isEmpty(doctorUuid)){
	        	 hql.append(" and o.userId=:doctorUuid ");
	        }
            Query q = this.getH4Session().createQuery(hql.toString());
            if(!StringUtil.isEmpty(doctorUuid)){
            	q.setString("doctorUuid", doctorUuid);
            }
            
            q.setString("videoUuid", videoUuid);
            List list = q.list();
            if (list != null && list.size() > 0) {
                    return (String) list.get(0);
            }
            return null;
    }


}
