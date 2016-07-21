package com.aebiz.b2b2c.servicestaff.groupfriends.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.groupfriends.dao.GroupFriendsDAO;
import com.aebiz.b2b2c.servicestaff.groupfriends.service.GroupFriendsService;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsQueryModel;

@Service
@Transactional
public class GroupFriendsServiceImpl extends BaseServiceImpl<GroupFriendsModel,GroupFriendsQueryModel> implements GroupFriendsService {
	private GroupFriendsDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(GroupFriendsDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(GroupFriendsModel m) {
		m.setUuid(us.getNextUuid("GroupFriends",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(GroupFriendsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(GroupFriendsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据医生编号和手机号 查询该人是否已被邀请过
	 * @param mobile
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public boolean isExit(String mobile, String doctorUuid) {
		return myDao.isExit(mobile, doctorUuid);
	}
	/**
	 * 
	 * @Description: (根据医生的id获取邀请同行的列表)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-19 上午10:51:10
	 */
        @Override
        public List<GroupFriendsModel> getGropFriendListByDoctorUuid(String doctorUuid) {
            return myDao.getGropFriendListByDoctorUuid(doctorUuid);
        }
	
}