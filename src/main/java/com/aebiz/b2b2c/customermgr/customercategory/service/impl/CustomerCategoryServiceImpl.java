package com.aebiz.b2b2c.customermgr.customercategory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customercategory.dao.CustomerCategoryDAO;
import com.aebiz.b2b2c.customermgr.customercategory.service.CustomerCategoryService;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryModel;
import com.aebiz.b2b2c.customermgr.customercategory.vo.CustomerCategoryQueryModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;

@Service
@Transactional
public class CustomerCategoryServiceImpl extends BaseServiceImpl<CustomerCategoryModel,CustomerCategoryQueryModel> implements CustomerCategoryService {
	private CustomerCategoryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerCategoryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 添加患者分类
	 */
	@Override
	public String create(CustomerCategoryModel m) {
		m.setUuid(us.getNextUuid("CustomerCategory",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());  // 添加时间
		m.setState("1");    // 添加状态
		
		String ret = super.create(m);
		return ret;
	}
	
	/**
	 * 更新患者分类
	 */
	@Override
	public void update(CustomerCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 删除患者分类
	 */
	@Override
	public void delete(CustomerCategoryModel m) {
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

	/**
	 * 得到患者分类model的集合
	 */
	@Override
	public List<CustomerCategoryModel> getCustomerCategoryModelList() {
		return this.myDao.getCustomerCategoryModelList();
	}
	
	
	
}