package com.aebiz.b2b2c.product.productmodifylog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.product.productmodifylog.service.ProductModifyLogService;
import com.aebiz.b2b2c.product.productmodifylog.dao.ProductModifyLogDAO;
import com.aebiz.b2b2c.product.productmodifylog.vo.ProductModifyLogModel;
import com.aebiz.b2b2c.product.productmodifylog.vo.ProductModifyLogQueryModel;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class ProductModifyLogServiceImpl extends BaseServiceImpl<ProductModifyLogModel,ProductModifyLogQueryModel> implements ProductModifyLogService {
	private ProductModifyLogDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductModifyLogDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductModifyLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductModifyLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductModifyLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 记录商品操作日志
	 * 
	 * @param oldObj 旧的数据
	 * @param newObj 新的数据
	 * @param oldPrice	旧价格
	 * @param newPrice	新价格
	 */
	public void createLog(Object oldObj,Object newObj,double oldPrice,double newPrice){
		ProductModifyLogModel log=new ProductModifyLogModel();
		String beforOther=JSON.toJSONString(oldObj);	
		log.setBeforOther(beforOther);
		String endOther=JSON.toJSONString(newObj);	
		log.setEndOther(endOther);
		log.setBeforePrice(oldPrice);
		log.setEndPrice(newPrice);
		
		create(log);
		
	}
	
}