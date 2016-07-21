package com.aebiz.b2b2c.customermgr.consultrecordreply.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyModel;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyQueryModel;

public interface ConsultRecordReplyService extends BaseService<ConsultRecordReplyModel,ConsultRecordReplyQueryModel>{
        /**
         * 
         * @Description: (根据咨询记录的uuid获取回复消息的列表)    
         * @author XP  
         * @param uuid
         * @return
         * @date 2016-1-21 上午11:53:42
         */
        public List<ConsultRecordReplyModel> getConsultRecordReplListByUuid(String uuid);

}
