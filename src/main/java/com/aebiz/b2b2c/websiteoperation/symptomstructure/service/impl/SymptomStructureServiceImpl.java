package com.aebiz.b2b2c.websiteoperation.symptomstructure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.dao.SymptomStructureDAO;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.service.SymptomStructureService;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureModel;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureQueryModel;

@Service
@Transactional
public class SymptomStructureServiceImpl extends BaseServiceImpl<SymptomStructureModel,SymptomStructureQueryModel> implements SymptomStructureService {
	private SymptomStructureDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(SymptomStructureDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(SymptomStructureModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(SymptomStructureModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(SymptomStructureModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 
	 * @Description: (导入症状数据)    
	 * @author XP  
	 * @param files
	 * @return
	 * @date 2016-1-8 下午3:09:52
	 */
        @Override
        public List<Object> updloadExcel(MultipartFile[] files) {
            return myDao.updloadExcel(files);
        }
}