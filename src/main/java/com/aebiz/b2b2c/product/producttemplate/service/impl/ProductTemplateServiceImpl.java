package com.aebiz.b2b2c.product.producttemplate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.producttemplate.dao.ProductTemplateDAO;
import com.aebiz.b2b2c.product.producttemplate.service.ProductTemplateService;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateQueryModel;

/**
 * 商品模板业务实现
 * <p>
 * 详情描述：不同种类的商品有不同的模板。<br>
 * 比如：服装它有颜色、尺寸属性;杯子它有颜色、体积属性<br>
 * 商品模板是和商品分类有关联的, 该类主要是后台管理员对商品模板的增删查改业务进行实现<br>
 * 
 * 
 * @author huangpinpin
 * 
 */
@Service
@Transactional
public class ProductTemplateServiceImpl extends BaseServiceImpl<ProductTemplateModel,ProductTemplateQueryModel> implements ProductTemplateService {
	private ProductTemplateDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductTemplateDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductTemplateModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductTemplateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductTemplateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据集合uuid查询模板集合
	 * @param uuids
	 * @return
	 */
	public List<ProductTemplateModel> getProductTemplateByUuids(List<String> uuids){
		return myDao.getProductTemplateByUuids(uuids);
	}
	
	/**
	 * 根据模板uuid查询模板名称
	 * @param uuid
	 * @return
	 */
	public String getTemplateNameByUuids(String uuid){
		return myDao.getTemplateNameByUuids(uuid);
	}
}