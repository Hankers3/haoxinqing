package com.aebiz.b2b2c.visitprecept.drugreaction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.visitprecept.drugreaction.service.DrugReactionService;
import com.aebiz.b2b2c.visitprecept.drugreaction.dao.DrugReactionDAO;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionQueryModel;

@Service
@Transactional
public class DrugReactionServiceImpl extends BaseServiceImpl<DrugReactionModel,DrugReactionQueryModel> implements DrugReactionService {
	private DrugReactionDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DrugReactionDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DrugReactionModel m) {
		m.setUuid(us.getNextUuid("DrugReaction",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DrugReactionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DrugReactionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
		
	
	//根据随访表单的Uuid值来获取不良反应对象
	public DrugReactionModel getLastDrugReactionModelByUuid(String uuid) {
		return myDao.getLastDrugReactionModelByUuid(uuid);
	}
	/**
         * 根据随访方案id获取药物不良反应的列表
         * @param visitRecordUuid
         * @return
         */
        @Override
        public List<DrugReactionModel> getAllDrugReactionByVisitRecordUuid(String visitRecordUuid) {
            return myDao.getAllDrugReactionByVisitRecordUuid(visitRecordUuid);
        }
}