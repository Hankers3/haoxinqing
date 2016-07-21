package com.aebiz.b2b2c.cms.platforminfo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoQueryModel;

@Repository
public class PlatFormInfoH4Impl extends BaseH4Impl<PlatFormInfoModel, PlatFormInfoQueryModel>
		implements PlatFormInfoDAO {

	/* 添加sql语句 */
	@Override
	protected String getAppendHql(PlatFormInfoQueryModel qm) {
		StringBuffer hql = new StringBuffer("");

		if (!StringUtil.isEmpty(qm.getCreateTime2())) {
			hql.append(" and SUBSTR(o.createTime,1,10) =:createTime ");
		}
		if (qm != null) {
			hql.append(" order by o.").append(qm.getSortName()).append(" ").append(qm.getSortType());
		} else {
			hql.append(" order by o.opeTime desc ");
		}

		return hql.toString();
	}

	/* 为sql语句赋值 */
	@Override
	protected void setAppendHqlValue(PlatFormInfoQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getCreateTime2())) {
			q.setString("createTime", qm.getCreateTime2());
		}

	}

	/**
	 * 通过医生id得到预约课程
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<PlatFormInfoModel> getByUserid(String userId) {
		String hql = new String("from PlatFormInfoModel pim where pim.userId =:userId ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("userId", userId);
		List<PlatFormInfoModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过医生id得到预约课程
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> getUuidsByUserid(String userId) {
		String hql = new String("select pim.uuid from PlatFormInfoModel pim where pim.userId =:userId ");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("userId", userId);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过名称得到视频
	 * 
	 * @return
	 */
	@Override
	public List<PlatFormInfoModel> getByName(String name, String videoType) {
		StringBuffer hql = new StringBuffer("from PlatFormInfoModel pim where 1=1 ");
		if (!StringUtil.isEmpty(name)) {
			hql.append(" and (pim.videoModel like:videoModel or userName like:userName) ");
		}
		if (!StringUtil.isEmpty(videoType)) {
			hql.append(" and pim.videoType =:videoType ");
		}
		hql.append(" order by pim.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(name)) {
			query.setString("videoModel", "%" + name + "%");
			query.setString("userName", "%" + name + "%");
		}
		if (!StringUtil.isEmpty(videoType)) {
			query.setString("videoType", videoType);
		}

		List<PlatFormInfoModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据视频的UUid获取该视频的开讲时间
	 * 
	 * @param vidoUuid
	 * @return
	 */
	@Override
	public String getStartTimeByVidoUuid(String vidoUuid) {
		StringBuffer hql = new StringBuffer("select o.startTime from PlatFormInfoModel as o where o.uuid=:vidoUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(vidoUuid)) {
			query.setString("vidoUuid", vidoUuid);
		}
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
