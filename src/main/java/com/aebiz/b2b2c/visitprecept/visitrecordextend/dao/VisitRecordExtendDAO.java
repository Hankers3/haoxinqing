package com.aebiz.b2b2c.visitprecept.visitrecordextend.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendQueryModel;

public interface VisitRecordExtendDAO extends BaseDAO<VisitRecordExtendModel,VisitRecordExtendQueryModel>{

	List<VisitRecordExtendModel> getAllByVisitRecordUuid(String uuid);

    public List<VisitRecordExtendModel> getAllVisitRecordExtendListByPreceptUuid(String preceptUuid, String type);

}