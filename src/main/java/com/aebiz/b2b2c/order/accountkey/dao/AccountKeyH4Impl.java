package com.aebiz.b2b2c.order.accountkey.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyQueryModel;

@Repository
public class AccountKeyH4Impl extends BaseH4Impl<AccountKeyModel,AccountKeyQueryModel> implements AccountKeyDAO {

	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	@Override
	public AccountKeyModel getAccountKeyModelById(String id) {
		StringBuffer sb = new StringBuffer(
				"from AccountKeyModel akm where 1=1");
		sb.append(" and akm.id=:id ");
		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("id", id);
		List<AccountKeyModel> list = new ArrayList<AccountKeyModel>(); 
		list = q.list();
		if(list != null && list.size()>0){
			return (AccountKeyModel)list.get(0);
		}else{
			return new AccountKeyModel();
		}
	}
	/**
	 * 
	 * @Description: (定时任务将值设置为0)    
	 * @author XP  
	 * @date 2016-1-26 下午1:10:09
	 */
        @Override
        public void updateAccountVlue() {
            StringBuffer sb = new StringBuffer("update AccountKeyModel set value=:value");
            Query q = this.getH4Session().createQuery(sb.toString());
            q.setString("value", "0");
            q.executeUpdate();
        }
    	
	
	
	
	
}
