package com.aebiz.b2b2c.visitprecept.drugreaction.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionQueryModel;

public interface DrugReactionService extends BaseService<DrugReactionModel,DrugReactionQueryModel>{

	DrugReactionModel getLastDrugReactionModelByUuid(String uuid);
	/**
	 * 根据随访方案id获取药物不良反应的列表
	 * @param visitRecordUuid
	 * @return
	 */
      public List<DrugReactionModel> getAllDrugReactionByVisitRecordUuid(String visitRecordUuid);

}
