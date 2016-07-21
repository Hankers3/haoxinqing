package com.aebiz.b2b2c.product.productmainaudit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productmainaudit.dao.ProductMainAuditDAO;
import com.aebiz.b2b2c.product.productmainaudit.service.ProductMainAuditService;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditModel;
import com.aebiz.b2b2c.product.productmainaudit.vo.ProductMainAuditQueryModel;

@Service
@Transactional
public class ProductMainAuditServiceImpl extends BaseServiceImpl<ProductMainAuditModel,ProductMainAuditQueryModel> implements ProductMainAuditService {
	private ProductMainAuditDAO myDao = null;
	@Autowired
	private UuidService us;
	
	@Autowired
	private ProductMainService productMainService;
	@Autowired
	public void setMyDao(ProductMainAuditDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductMainAuditModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		//审核人
		m.setAuditMan(LoginUserHelper.getLoginUserUuid());
		//审核时间
		m.setAuditTime(DateFormatHelper.getNowTimeStr());
		//审核状态不通过
		m.setAuditState(ProductMainAuditModel.NO_PASS);
		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(ProductMainAuditModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		//审核人
		m.setAuditMan(LoginUserHelper.getLoginUserUuid());
		//审核时间
		m.setAuditTime(DateFormatHelper.getNowTimeStr());
		//审核状态不通过
		m.setAuditState(ProductMainAuditModel.NO_PASS);
		super.update(m);
	}
	
	/**
	 * 删除审核记录
	 */
	@Override
	public void delete(ProductMainAuditModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据字段和商品uuid，删除审核记录
	 * @param auditField
	 * @param productUuid
	 * @return
	 */
	public void deleteByAuditFieldAndProductUuid(String auditField,
			String productUuid) {
		myDao.deleteByAuditFieldAndProductUuid(auditField, productUuid);
	}
	
	/**
	 * 根据字段和商品uuid,查询审核记录
	 * @param auditField
	 * @param productUuid
	 * @return
	 */
	public ProductMainAuditModel getByAuditFieldAndProductUuid(String auditField,String productUuid){
		return myDao.getByAuditFieldAndProductUuid(auditField, productUuid);
	}
	
	/**
	 * 创建字段审核不通过记录
	 * @param m
	 * @return
	 */
	public void noPassFieldName(ProductMainAuditModel m){
		ProductMainAuditModel productAudit=myDao.getByAuditFieldAndProductUuid(m.getAuditField(), m.getProductUuid());
		if(productAudit!=null){
			//设置个字段值
			productAudit.setAuditField(m.getAuditField());
			productAudit.setAuditType(m.getAuditType());
			productAudit.setProductUuid(m.getProductUuid());
			productAudit.setAuditReason(m.getAuditReason());
			update(productAudit);
		}else{
			create(m);
		}
		//修改商品审核状态为不通过
		productMainService.updateAuditStateNoPass(m.getProductUuid());
	}
	
	/**
	 * 判断该商品是否存在审核不通过记录
	 * 
	 * @param productUuid 商品uuid
	 * @return
	 */
	public boolean isExist(String productUuid){
		int rel=myDao.getCountByProductUuid(productUuid);
		if(rel>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据商品Uuid获取审核记录
	 * @param productUuid
	 * @return
	 */
	public List<ProductMainAuditModel> getByProductUuid(String productUuid){
		return myDao.getByProductUuid(productUuid);
	}
	
	/**
	 * 根据审核类型和商品uuid,删除审核记录集合
	 * @param auditType 审核类型
	 * @param productUuid 商品Uuid
	 * @return
	 */
	public void deleteByAuditTypeAndProductUuid(String auditType,String productUuid){
		myDao.deleteByAuditTypeAndProductUuid(auditType, productUuid);
		
	}
	
	/**
	 * 审核字段通过
	 * @param auditField 字段	
	 * @param productUuid 商品uuid
	 */
	public void auditPass(String auditField,String productUuid){
		//删除该商品字段审核不通过的记录
		deleteByAuditFieldAndProductUuid(auditField, productUuid);
		//如果没有审核不通过的记录，设置商品审核状态为通过
		if(!isExist(productUuid)){
			productMainService.updateAuditStatePass(productUuid);
		}
	}
	
	/**
	 * 批量审核字段通过
	 * @param auditType	审核类型
	 * @param productUuid	商品uuid
	 */
	public void bacthAuditPass(String auditType,String productUuid){
		//删除商品字段审核记录
		deleteByAuditTypeAndProductUuid(auditType, productUuid);
		//如果没有审核不通过的记录，设置商品审核状态为通过
		if(!isExist(productUuid)){
			productMainService.updateAuditStatePass(productUuid);
		}
	}
}