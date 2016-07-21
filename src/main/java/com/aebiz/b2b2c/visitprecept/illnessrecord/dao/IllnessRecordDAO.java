package com.aebiz.b2b2c.visitprecept.illnessrecord.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordModel;
import com.aebiz.b2b2c.visitprecept.illnessrecord.vo.IllnessRecordQueryModel;

public interface IllnessRecordDAO extends
		BaseDAO<IllnessRecordModel, IllnessRecordQueryModel> {
	// 根据患者的id获取用户的所有的病情变化
	public List<String> getAllNoteByCustomerid(String customerid);

	/**
	 * 根据随访编号得到病情变化
	 * 
	 * @param visitRecordUuid
	 * @return
	 */
	public List<IllnessRecordModel> getByVisitRecordUuid(String visitRecordUuid);

}