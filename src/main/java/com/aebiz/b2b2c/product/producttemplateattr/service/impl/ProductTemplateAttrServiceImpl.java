package com.aebiz.b2b2c.product.producttemplateattr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.producttemplateattr.dao.ProductTemplateAttrDAO;
import com.aebiz.b2b2c.product.producttemplateattr.service.ProductTemplateAttrService;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrQueryModel;
/**
 * 
 * 商品模板属性业务实现
 * 一个商品有很多属性比如：颜色、尺寸
 * 
 * @author huangpinpin
 *
 */
@Service
@Transactional
public class ProductTemplateAttrServiceImpl extends BaseServiceImpl<ProductTemplateAttrModel,ProductTemplateAttrQueryModel> implements ProductTemplateAttrService {
	private ProductTemplateAttrDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductTemplateAttrDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductTemplateAttrModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductTemplateAttrModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductTemplateAttrModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据模板uuids,获取属性
	 * @param templateUuids
	 * @return
	 */
	public List<ProductTemplateAttrModel> getByUuids(List<String> uuids){
		return myDao.getByUuids(uuids);
	}
}