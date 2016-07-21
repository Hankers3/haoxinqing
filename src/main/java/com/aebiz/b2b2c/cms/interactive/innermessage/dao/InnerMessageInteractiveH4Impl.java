package com.aebiz.b2b2c.cms.interactive.innermessage.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.interactive.innermessage.vo.InnerMessageInteractiveModel;

@Repository
public class InnerMessageInteractiveH4Impl extends
		BaseH4Impl<InnerMessageModel, InnerMessageQueryModel> implements
		InnerMessageInteractiveDAO {
	/**
	 * 对外接口：获取会员所有未读消息
	 */
	@Override
	public List<InnerMessageInteractiveModel> getUnreadMessageList(
			String userUuid) {
		StringBuffer hql = new StringBuffer(
				"select imm.sendUser, imm.userImage, imm.title, imm.content, imm.sendTime from InnerMessageModel imm where 1=1");
		
		hql.append(" and imm.receiveUser=:receiveUser");
		
		hql.append(" and imm.readStatus=:readStatus");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("receiveUser", userUuid);
		query.setString("readStatus", InnerMessageModel.READ_STATUS_UNREAD);

		List<Object[]> list = query.list();
		
		if (list != null && list.size() > 0) {
			List<InnerMessageInteractiveModel> outList = convertInteractiveList(list);
			return outList;
		}

		return null;
	}
	
	/**
	 * 对外接口：获取会员所有未读消息数
	 */
	@Override
	public int getUnreadMessageCount(String userUuid) {

		String hql = "select count(*) from InnerMessageModel imm where imm.receiveUser=:receiveUser and imm.readStatus=:readStatus";
		

		Query query = this.getH4Session().createQuery(hql.toString());
		
		query.setString("receiveUser", userUuid);
		query.setString("readStatus", InnerMessageModel.READ_STATUS_UNREAD);

		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 转换成对换接口的model
	 * 
	 * @param tempList
	 * @return
	 */
	private List<InnerMessageInteractiveModel> convertInteractiveList(
			List<Object[]> tempList) {
		List<InnerMessageInteractiveModel> list = new ArrayList();
		if ((tempList != null) && (tempList.size() > 0)) {
			for (Object[] obj : tempList) {
				String sendUser = (String) obj[0];
				String userImage = (String) obj[1];
				String title = (String) obj[2];
				String content = (String) obj[3];
				String sendTime = (String) obj[4];

				InnerMessageInteractiveModel interactiveModel = new InnerMessageInteractiveModel();
				interactiveModel.setSendUser(sendUser);
				interactiveModel.setUserImage(userImage);
				interactiveModel.setTitle(title);
				interactiveModel.setContent(content);
				interactiveModel.setSendTime(sendTime);

				list.add(interactiveModel);
			}
		}

		return list;
	}

}
