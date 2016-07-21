package com.aebiz.b2b2c.cms.innermessagenotice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeModel;
import com.aebiz.b2b2c.cms.innermessagenotice.vo.InnerMessageNoticeQueryModel;


@Repository
public class InnerMessageNoticeH4Impl extends BaseH4Impl<InnerMessageNoticeModel,InnerMessageNoticeQueryModel> implements InnerMessageNoticeDAO {

	
	/**
	 * 通过接收人编号获取通知列表
	 * @param userId
	 * @return
	 */
	@Override
	public List<InnerMessageNoticeModel> getByUserId(String userId,int pageCount,int pageNo) {
		StringBuffer hql = new StringBuffer("from InnerMessageNoticeModel imnm where imnm.userId=:userId ");
		hql.append(" order by imnm.opeTime desc");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("userId", userId);
		//分页数据
		if(pageCount != 0 && pageNo !=0){
			query.setFirstResult((pageNo-1)*pageCount);//从下标为0开始
			query.setMaxResults(pageCount);
		}
		List<InnerMessageNoticeModel> list = query.list();
		if(list != null && list.size()>0 ){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 站内信息消息
	 * 
	 * @param messageUuid
	 * @param userId
	 * @return
	 */
	@Override
	public InnerMessageNoticeModel getInnerMessageNoticeModel(
			String messageUuid, String userId) {
		StringBuffer hql = new StringBuffer(" from InnerMessageNoticeModel as o ");
		hql.append(" where o.userId =:userId");
		hql.append(" and o.messageUuid =:messageUuid");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("userId", userId);
		q.setString("messageUuid", messageUuid);
		
		List list = q.list();
		if(list !=null && list.size()>0){
			return (InnerMessageNoticeModel) list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据用户Id获取未读通知列表 
	 * @param userId
	 * @return
	 * hedongfei
	 */
	@Override
	public List<InnerMessageNoticeModel> getInnerMessageListByUserId(
			String userId, String readStatus, int pageCount, int pageNo) {

		StringBuffer hql = new StringBuffer("from InnerMessageNoticeModel imm where imm.userId=:userId ");
		if("0".equals(readStatus)){
			hql.append(" and imm.readStatus = '0' ");
		}
		//hql.append(" order by imm.sendTime desc");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("userId", userId);
		//分页数据
		if(pageCount != 0 && pageNo !=0){
			query.setFirstResult((pageNo-1)*pageCount);//从下标为0开始
			query.setMaxResults(pageCount);
		}
		List<InnerMessageNoticeModel> list = query.list();
		if(list != null && list.size()>0 ){
			return list;
		}else{
			return null;
		}
	}

}
