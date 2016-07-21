package com.aebiz.b2b2c.customermgr.consultrecordreply.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyModel;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyQueryModel;

@Repository
public class ConsultRecordReplyH4Impl extends BaseH4Impl<ConsultRecordReplyModel,ConsultRecordReplyQueryModel> implements ConsultRecordReplyDAO {
    /**
     * 
     * @Description: (根据咨询记录的uuid获取回复消息的列表)    
     * @author XP  
     * @param uuid
     * @return
     * @date 2016-1-21 上午11:53:42
     */
    @Override
    public List<ConsultRecordReplyModel> getConsultRecordReplListByUuid(String uuid) {
        StringBuffer hql = new StringBuffer(
                " from ConsultRecordReplyModel crm where crm.consultRecordUuid=:uuid  order by crm.createTime asc");
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("uuid", uuid);
            List<ConsultRecordReplyModel> list = query.list();
            if (list != null && list.size() > 0) {
                return list;
            }
            return null;
    }

}
