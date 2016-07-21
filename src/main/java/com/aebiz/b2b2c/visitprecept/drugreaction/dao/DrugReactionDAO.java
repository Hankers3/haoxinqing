package com.aebiz.b2b2c.visitprecept.drugreaction.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionQueryModel;

public interface DrugReactionDAO extends
		BaseDAO<DrugReactionModel, DrugReactionQueryModel> {

	public DrugReactionModel getLastDrugReactionModelByUuid(String uuid);
	/**
         * 根据随访方案id获取药物不良反应的列表
         * @param visitRecordUuid
         * @return
         */
        public List<DrugReactionModel> getAllDrugReactionByVisitRecordUuid(String visitRecordUuid);

}