package com.aebiz.b2b2c.visitprecept.visitrecordextend.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendQueryModel;

public interface VisitRecordExtendService extends BaseService<VisitRecordExtendModel,VisitRecordExtendQueryModel>{

	List<VisitRecordExtendModel> getAllByVisitRecordUuid(String uuid);
	  /**
	    * 根据随访id和类型获取随访扩展字段
	    * @param visitRecordUuid
	    * @param type
	    * @return
	    */
	 public List<VisitRecordExtendModel> getAllVisitRecordExtendListByPreceptUuid(String preceptUuid, String type);

}
