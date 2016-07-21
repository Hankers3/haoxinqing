package com.aebiz.b2b2c.servicestaff.groupfriends.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsQueryModel;

public interface GroupFriendsDAO extends BaseDAO<GroupFriendsModel,GroupFriendsQueryModel>{

	/**
	 * 根据医生编号和手机号 查询该人是否已被邀请过
	 * @param mobile
	 * @param doctorUuid
	 * @return
	 */
	public boolean isExit(String mobile,String doctorUuid);
	/**
	 * 
	 * @Description: (根据医生的id获取邀请同行的列表)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-19 上午10:52:04
	 */
	public List<GroupFriendsModel> getGropFriendListByDoctorUuid(String doctorUuid);
}