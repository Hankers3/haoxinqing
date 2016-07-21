package com.aebiz.b2b2c.servicestaff.groupfriends.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 下午1:36:40
 */
@Primary
@Repository
public class GroupFriendsCacheImpl extends
		BaseMemcachedCache<GroupFriendsModel,GroupFriendsQueryModel> implements
		GroupFriendsDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private GroupFriendsDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public GroupFriendsCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_GROUPFRIENDS_KEY);
	}
	/**
	 * 根据医生编号和手机号 查询该人是否已被邀请过
	 * @param mobile
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public boolean isExit(String mobile, String doctorUuid) {
	
		return dao.isExit(mobile, doctorUuid);
	}
	/**
	 * 
	 * @Description: (根据医生的id获取邀请同行的列表)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-19 上午10:53:03
	 */
        @Override
        public List<GroupFriendsModel> getGropFriendListByDoctorUuid(String doctorUuid) {
            return dao.getGropFriendListByDoctorUuid(doctorUuid);
        }

	
}
