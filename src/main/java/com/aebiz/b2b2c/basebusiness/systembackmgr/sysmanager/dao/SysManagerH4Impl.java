package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Repository
public class SysManagerH4Impl extends
		BaseH4Impl<SysManagerModel, SysManagerQueryModel> implements
		SysManagerDAO {

	/**
	 * 查询是否已经存在
	 * 
	 * @param uuid
	 * @param menuId
	 * @return
	 */
	public SysManagerModel getManagerByManagerIdAndUuid(String uuid,
			String managerId) {
		String hql = "select o from SysManagerModel o where 1=1 ";
		hql += " and o.id = :managerId";

		if (!StringUtil.isEmpty(uuid)) {
			hql += " and o.uuid <> :managerUuid";
		}

		Query q = this.getH4Session().createQuery(hql);
		if (!StringUtil.isEmpty(uuid)) {
			q.setString("managerUuid", uuid);
		}
		q.setString("managerId", managerId);

		List<SysManagerModel> list = q.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	public String getSysManagerNameByUuid(String uuid) {
		StringBuffer sb = new StringBuffer(
				"select smm.name from SysManagerModel smm where 1=1");
		sb.append(" and smm.uuid=:uuid");

		Query query = this.getH4Session().createQuery(sb.toString());
		query.setString("uuid", uuid);

		List list = query.list();
		String name = "";
		if (list != null && list.size() > 0) {
			name = (String) list.get(0);

			return name;
		} 
		return name;
	}
	/**
	 * 
	 * 根据用户名和密码获取系统管理员
	 */
        public SysManagerModel getSysManagerModelByLoginNameAndPassword(String loginName, String pwd) {
            StringBuffer sb = new StringBuffer(
                            " from SysManagerModel smm  where 1=1");
            sb.append(" and smm.id=:loginName");
            sb.append(" and smm.pwd=:pwd");
            Query query = this.getH4Session().createQuery(sb.toString());
            query.setString("loginName", loginName);
            query.setString("pwd", pwd);
            List list = query.list();
            if (list != null && list.size() > 0) {
                return  (SysManagerModel) list.get(0);
            } 
            return null;
        }

        public String getManagerUuidByManagerIdAndUuid(String uuid, String managerId) {
    		String hql = "select o.uuid from SysManagerModel o where 1=1 ";
    		hql += " and o.id = :managerId";

    		if (!StringUtil.isEmpty(uuid)) {
    			hql += " and o.uuid <> :managerUuid";
    		}

    		Query q = this.getH4Session().createQuery(hql);
    		if (!StringUtil.isEmpty(uuid)) {
    			q.setString("managerUuid", uuid);
    		}
    		q.setString("managerId", managerId);

    		List<String> list = q.list();
    		if (list != null && list.size() > 0) {
    			return list.get(0);
    		}

    		return null;
    	}
	
        
        
    
}
