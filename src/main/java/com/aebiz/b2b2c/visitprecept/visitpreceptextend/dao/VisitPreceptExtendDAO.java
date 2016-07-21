package com.aebiz.b2b2c.visitprecept.visitpreceptextend.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendQueryModel;

public interface VisitPreceptExtendDAO extends BaseDAO<VisitPreceptExtendModel,VisitPreceptExtendQueryModel>{
    /**
     * 获取其它方案的列表
     * @param visitUuid
     * @return
     */
  public  List<VisitPreceptExtendModel> getAllVisitPreceptByPreceptUuid(String visitUuid);



}