package com.aebiz.b2b2c.cms.innermessage.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;

@Repository
public class InnerMessageH4Impl extends BaseH4Impl<InnerMessageModel, InnerMessageQueryModel>
		implements InnerMessageDAO {
	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 * @return
	 */
	@Override
	public List<InnerMessageModel> getInnerMessageListByMessageType(String messageType, String doctorUuid) {

		String hql = new String(
				"from InnerMessageModel imm where imm.messageType=:messageType and imm.receiveUser=:doctorUuid order by imm.sendTime desc");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("messageType", messageType);
		query.setString("doctorUuid", doctorUuid);
		List<InnerMessageModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据用户Id获取消息列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<InnerMessageModel> getInnerMessageListByUserId(String userId, String readStatus, int pageCount,
			int pageNo) {

		StringBuffer hql = new StringBuffer("from InnerMessageModel imm where imm.receiveUser=:receiveUser ");
		if ("0".equals(readStatus)) {
			hql.append(" and imm.readStatus = '0' ");
		}

		// hdf msgtyp为“1” 2015.06.12
		hql.append(" and imm.messageType ='1' ");

		hql.append(" order by imm.sendTime desc");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("receiveUser", userId);
		// 分页数据
		if (pageCount != 0 && pageNo != 0) {
			query.setFirstResult((pageNo - 1) * pageCount);// 从下标为0开始
			query.setMaxResults(pageCount);
		}
		List<InnerMessageModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 通过参数返回list 消息类型 0最近收藏我的人 1随访消息 2在线咨询消息
	 * 
	 * @param userId
	 * @param string
	 * @return
	 */
	@Override
	public List<InnerMessageModel> getMessageList(String userId, String messageType) {
		String hql = new String(
				"from InnerMessageModel imm where imm.messageType=:messageType and imm.receiveUser=:receiveUser order by imm.sendTime desc");
		Query query = this.getH4Session().createQuery(hql);
		query.setString("messageType", messageType);
		query.setString("receiveUser", userId);
		List<InnerMessageModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 返回消息中心未读数量通过收件人id
	 */
	@Override
	public int getMessageCenterCount(String messageType, String userId) {
		String hql = "select count(uuid) from InnerMessageModel imm where imm.messageType=:messageType and imm.receiveUser=:receiveUser and imm.readStatus=:readStatus";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("messageType", messageType);
		query.setString("receiveUser", userId);
		query.setString("readStatus", InnerMessageModel.READ_STATUS_UNREAD); // 查看未读的
		Number obj = (Number) query.uniqueResult();
		if (obj != null) {
			return obj.intValue();
		}
		return 0;
	}

}
