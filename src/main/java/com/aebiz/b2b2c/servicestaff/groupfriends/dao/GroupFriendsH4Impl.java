package com.aebiz.b2b2c.servicestaff.groupfriends.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsModel;
import com.aebiz.b2b2c.servicestaff.groupfriends.vo.GroupFriendsQueryModel;

@Repository
public class GroupFriendsH4Impl extends BaseH4Impl<GroupFriendsModel,GroupFriendsQueryModel> implements GroupFriendsDAO {
	/**
	 * 根据医生编号和手机号 查询该人是否已被邀请过
	 * @param mobile
	 * @param doctorUuid
	 * @return
	 */
	public boolean isExit(String mobile,String doctorUuid){
		StringBuffer hql = new StringBuffer(" select o.uuid from GroupFriendsModel as o where 1=1  ");
		hql.append(" and o.mobile =:mobile");
		hql.append(" and o.doctorUuid =:doctorUuid");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("mobile",mobile);
		q.setString("doctorUuid",doctorUuid);
		
		List list = q.list();
		if(list !=null && list.size()>0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @Description: (根据医生的id获取邀请同行的列表)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-19 上午10:54:47
	 */
        @Override
        public List<GroupFriendsModel> getGropFriendListByDoctorUuid(String doctorUuid) {
            StringBuffer hql = new StringBuffer(" from GroupFriendsModel as o where o.doctorUuid =:doctorUuid order by o.createTime desc");
            
            Query q = this.getH4Session().createQuery(hql.toString());
            q.setString("doctorUuid",doctorUuid);
            
            List<GroupFriendsModel> list = q.list();
            if(list !=null && list.size()>0){
                return list;
            }
            return null;
        }

}
