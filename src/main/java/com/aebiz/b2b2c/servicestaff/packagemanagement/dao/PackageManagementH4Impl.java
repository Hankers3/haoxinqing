package com.aebiz.b2b2c.servicestaff.packagemanagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;

@Repository
public class PackageManagementH4Impl extends BaseH4Impl<PackageManagementModel,PackageManagementQueryModel> implements PackageManagementDAO {

	/**
	 * 根据传入的套餐信息名判断套餐名是否已经存在
	 * 
	 * @param packageName
	 *            套餐名
	 * @return
	 */
	@Override
	public String checkPackageName(String packageName) {
		StringBuffer hql = new StringBuffer(" select o.uuid  from PackageManagementModel  as o ");
		hql.append(" where o.packageName =:packageName ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("packageName", packageName);
		
		List  list =q.list();
		if(list !=null && list.size()>0){
			return "1";
		}
		return "0";
	}
	
	/**
	 * 通过编号 获取套餐名
	 * @param uuid
	 * @return
	 */
	public String getPackageName(String uuid){
		if(StringUtil.isEmpty(uuid)){
			return "";
		}
		StringBuffer hql = new StringBuffer(" select o.packageName  from PackageManagementModel  as o ");
		hql.append(" where o.uuid =:uuid ");
		
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", uuid);
		
		List  list =q.list();
		if(list !=null && list.size()>0){
			return (String) list.get(0);
		}
		
		return "";
		
	}
	/**
	 * 
	 * @Description: (根据医生的id获取私人套餐列表)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-18 下午1:06:09
	 */
        @Override
        public List<PackageManagementModel> getPackageListByDoctorUuid(List<String> uuids) {
            if(uuids == null){
                return null;
            }
            StringBuffer hql = new StringBuffer(" from PackageManagementModel  as o ");
            hql.append(" where o.uuid in (:uuids) ");
            
            Query q = this.getH4Session().createQuery(hql.toString());
            q.setParameterList("uuids", uuids);
            
            List<PackageManagementModel>  list =q.list();
            if(list !=null && list.size()>0){
                    return  list;
            }
            
            return null;
        }
}
