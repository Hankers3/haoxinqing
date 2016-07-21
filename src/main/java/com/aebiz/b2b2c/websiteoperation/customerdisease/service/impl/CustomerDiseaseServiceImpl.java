package com.aebiz.b2b2c.websiteoperation.customerdisease.service.impl;

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
import com.aebiz.b2b2c.websiteoperation.customerdisease.dao.CustomerDiseaseDAO;
import com.aebiz.b2b2c.websiteoperation.customerdisease.service.CustomerDiseaseService;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseModel;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseQueryModel;

@Service
@Transactional
public class CustomerDiseaseServiceImpl extends BaseServiceImpl<CustomerDiseaseModel,CustomerDiseaseQueryModel> implements CustomerDiseaseService {
	private CustomerDiseaseDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerDiseaseDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerDiseaseModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CustomerDiseaseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CustomerDiseaseModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
    
        @Override
        public List<Object> updloadExcel(MultipartFile[] files) {
            return myDao.updloadExcel(files);
        }
}