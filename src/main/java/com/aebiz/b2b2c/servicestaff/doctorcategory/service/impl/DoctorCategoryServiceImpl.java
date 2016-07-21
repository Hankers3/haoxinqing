package com.aebiz.b2b2c.servicestaff.doctorcategory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.doctorcategory.dao.DoctorCategoryDAO;
import com.aebiz.b2b2c.servicestaff.doctorcategory.service.DoctorCategoryService;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryModel;
import com.aebiz.b2b2c.servicestaff.doctorcategory.vo.DoctorCategoryQueryModel;

@Service
@Transactional
public class DoctorCategoryServiceImpl extends BaseServiceImpl<DoctorCategoryModel,DoctorCategoryQueryModel> implements DoctorCategoryService {
	private DoctorCategoryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DoctorCategoryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 添加医生分类
	 */
	@Override
	public String create(DoctorCategoryModel m) {
		m.setUuid(us.getNextUuid("DoctorCategory",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());	
		String ret = super.create(m);
		return ret;
	}
	
	/**
	 * 修改医生分类
	 */
	@Override
	public void update(DoctorCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 删除医生分类
	 */
	@Override
	public void delete(DoctorCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	@Override
	public boolean checkCateGoryName(String categoryName) {
		String flag = myDao.checkCateGoryName(categoryName);
		if(flag.equals("1") ){
			return true;
		}
		return false;
	}
}