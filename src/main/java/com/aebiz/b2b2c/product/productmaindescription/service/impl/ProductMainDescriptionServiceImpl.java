package com.aebiz.b2b2c.product.productmaindescription.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productmaindescription.dao.ProductMainDescriptionDAO;
import com.aebiz.b2b2c.product.productmaindescription.service.ProductMainDescriptionService;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionQueryModel;

@Service
@Transactional
public class ProductMainDescriptionServiceImpl extends BaseServiceImpl<ProductMainDescriptionModel,ProductMainDescriptionQueryModel> implements ProductMainDescriptionService {
	private ProductMainDescriptionDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductMainDescriptionDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductMainDescriptionModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductMainDescriptionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductMainDescriptionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据商品uuid集合 删除描述信息
	 * @param productUuids
	 */
	public void deletesByProductUuids(List<String> productUuids){
		myDao.deletesByProductUuids(productUuids);
	}
	
	/**
	 * 根据商品uuid 查询描述信息
	 * @param productUuid
	 * @return
	 */
	public ProductMainDescriptionModel getByProductUuid(String productUuid){
		return myDao.getByProductUuid(productUuid);
	}
	
	/**
	 * 恢复商品描述表,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public void recycleProductDesc(List<String> productUuids){
		myDao.recycleProductDesc(productUuids);
	}
	/**
	 * 根据商品UUID获取查询描述信息的UUID
	 * @param productUuid
	 * @return
	 */
	@Override
	public String getProductMainDescriptionUuidByProductUuid(String productUuid) {
		return myDao.getUuidByProductUuid(productUuid);
	}
}