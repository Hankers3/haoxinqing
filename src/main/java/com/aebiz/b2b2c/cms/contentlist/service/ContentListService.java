package com.aebiz.b2b2c.cms.contentlist.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.cms.contentlist.vo.ContentListModel;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListQueryModel;

public interface ContentListService extends BaseService<ContentListModel,ContentListQueryModel>{
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
    ContentListModel getContentlistByConditions(String doctorUuid,
            String contentUuid, String doctorEmail);

}
