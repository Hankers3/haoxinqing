package com.aebiz.b2b2c.product.productmainbaseprice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productmainbaseprice.dao.ProductMainBasePriceDAO;
import com.aebiz.b2b2c.product.productmainbaseprice.service.ProductMainBasePriceService;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceQueryModel;
/**
 * 
 * 商品价格信息接口
 * @author huangpinpin
 *
 */
@Service
@Transactional
public class ProductMainBasePriceServiceImpl extends BaseServiceImpl<ProductMainBasePriceModel,ProductMainBasePriceQueryModel> implements ProductMainBasePriceService {
	private ProductMainBasePriceDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductMainBasePriceDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductMainBasePriceModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductMainBasePriceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductMainBasePriceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据商品uuid集合 删除价格信息
	 * 在删除商品时间
	 * @param productUuids
	 */
	public void deletesByProductUuids(List<String> productUuids){
		myDao.deletesByProductUuids(productUuids);
	}
	
	/**
	 * 根据productUuid获取商品价格信息model
	 * @param productUuid
	 * @return
	 */
	public ProductMainBasePriceModel getProductMainBasePriceModelByProductUuid(String productUuid){
		return myDao.getProductMainBasePriceModelByProductUuid(productUuid);
	}
	
	/**
	 * 根据productUuid获取商品价格
	 * 
	 * @param productUuid
	 * @return
	 */
	public double getShopPrice(String productUuid) {
		return myDao.getShopPrice(productUuid);
	}
	
	
	/**
	 * 根据productUuid获取计费方式
	 * 
	 * @param productUuid
	 * @return
	 */
	public String getChargetype(String productUuid) {
		
		return myDao.getChargetype(productUuid);
	}
}