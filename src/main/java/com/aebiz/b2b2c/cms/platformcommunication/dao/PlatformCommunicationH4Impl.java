package com.aebiz.b2b2c.cms.platformcommunication.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationModel;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationQueryModel;

@Repository
public class PlatformCommunicationH4Impl extends
		BaseH4Impl<PlatformCommunicationModel, PlatformCommunicationQueryModel>
		implements PlatformCommunicationDAO {

	/**
	 * 修改审核状态
	 */
	@Override
	public String updateState(String uuid, String state) {

		StringBuffer hql = new StringBuffer(
				" update PlatformCommunicationModel as o set o.conftimeState =:conftimeState");
		hql.append(" where o.uuid =:uuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("conftimeState", state);
		q.setString("uuid", uuid);
		q.executeUpdate();

		return "success";
	}

	/**
	 * 通过视频id得到视频中医生的评论信息
	 * 
	 * @param videoUuid
	 * @return
	 */
	@Override
	public List<PlatformCommunicationModel> getByVideoUuid(String videoUuid) {
		StringBuffer hql = new StringBuffer(
				"from PlatformCommunicationModel o where conftimeState='1' ");
		if (!StringUtil.isEmpty(videoUuid)) {
			hql.append(" and o.platformUuid =:platformUuid ");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(videoUuid)) {
			query.setString("platformUuid", videoUuid);
		}

		List<PlatformCommunicationModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过视频id得到视频中医生的评论信息
	 * 
	 * @param videoUuid
	 * @return
	 */
	@Override
	public List<String> getUuidsByVideoUuid(String videoUuid) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from PlatformCommunicationModel o where conftimeState='1' ");
		if (!StringUtil.isEmpty(videoUuid)) {
			hql.append(" and o.platformUuid =:platformUuid ");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(videoUuid)) {
			query.setString("platformUuid", videoUuid);
		}

		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	
}
