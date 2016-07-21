package com.aebiz.b2b2c.product.producttemplatecate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.producttemplatecate.dao.ProductTemplateCateDAO;
import com.aebiz.b2b2c.product.producttemplatecate.service.ProductTemplateCateService;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateModel;
import com.aebiz.b2b2c.product.producttemplatecate.vo.ProductTemplateCateQueryModel;
/**
 * 模板分类业务实现
 * 
 * 由于模板会很多，所以给它个分类，方便维护和查找
 * 
 * @author huangpinpin
 *
 */
@Service
@Transactional
public class ProductTemplateCateServiceImpl extends BaseServiceImpl<ProductTemplateCateModel,ProductTemplateCateQueryModel> implements ProductTemplateCateService {
	private ProductTemplateCateDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductTemplateCateDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductTemplateCateModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductTemplateCateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductTemplateCateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 根据uuid获取分裂名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getCategoryNameByUuid(String uuid){
		return myDao.getCategoryNameByUuid(uuid);
	}
}