package com.aebiz.b2b2c.customermgr.drugnotice.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeModel;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeQueryModel;

@Repository
public class DrugNoticeH4Impl extends BaseH4Impl<DrugNoticeModel,DrugNoticeQueryModel> implements DrugNoticeDAO {
       /**
        * 
        * @Description: (根据患者的id获取服药提醒的列表)    
        * @author XP  
        * @param customerUuid
        * @return
        * @date 2016-1-13 下午3:10:21
        */
        @Override
        public List<DrugNoticeModel> getDrugNoticeListByCustomerUuid(String customerUuid) {
            StringBuffer hql = new StringBuffer(
                    " from DrugNoticeModel as o  where customerUuid =:customerUuid  order by o.createTime desc ");
            
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("customerUuid", customerUuid);
            
                List<DrugNoticeModel> list = query.list();
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
        }

}
