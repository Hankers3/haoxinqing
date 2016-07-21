package com.aebiz.b2b2c.visitprecept.visitpreceptextend.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendQueryModel;

public interface VisitPreceptExtendService extends BaseService<VisitPreceptExtendModel,VisitPreceptExtendQueryModel>{
    /**
     * 获取其它方案的列表
     * @param visitUuid
     * @return
     */
   public List<VisitPreceptExtendModel> getAllVisitPreceptByPreceptUuid(String visitUuid);
 
}
