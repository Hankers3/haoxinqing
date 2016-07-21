package com.aebiz.b2b2c.cms.contentlist.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListModel;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListQueryModel;

public interface ContentListDAO extends BaseDAO<ContentListModel,ContentListQueryModel>{
    /**
     * 
     * @Description: (多条件查询索取列表)    
     * @author XP  
     * @param doctorUuid
     * @param contentUuid
     * @param doctorEmail
     * @return
     * @date 2016-1-20 下午12:46:51
     */
   public ContentListModel getContentlistByConditions(String doctorUuid, String contentUuid, String doctorEmail);

}