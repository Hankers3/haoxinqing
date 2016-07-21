package com.aebiz.b2b2c.visitprecept.illnessrecord.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordModel;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordQueryModel;

public interface IllnessRecordService extends BaseService<IllnessRecordModel,IllnessRecordQueryModel>{
    //根据患者的Id查询
    public	List<String> getAllNoteByCustomerid(String customerid);

    /**
     * 根据随访编号得到病情变化
     * @param visitRecordUuid
     * @return
     */
	public List<IllnessRecordModel> getByVisitRecordUuid(String visitRecordUuid);

}
