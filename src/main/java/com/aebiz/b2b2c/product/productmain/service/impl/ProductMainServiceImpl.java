package com.aebiz.b2b2c.product.productmain.service.impl;

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
import com.aebiz.b2b2c.product.productmain.dao.ProductMainDAO;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainQueryModel;

/**
 * 商品主表业务实现
 * 
 * @author huangpinpin
 * 
 */
@Service
@Transactional
public class ProductMainServiceImpl extends
		BaseServiceImpl<ProductMainModel, ProductMainQueryModel> implements
		ProductMainService {
	private ProductMainDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ProductMainDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductMainModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ProductMainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ProductMainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 
	 * 设置商品上架,根据所选productUuid集合
	 * 
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateStateShelves(String productUuid) {
		myDao.updateStateByProductUuid(ProductMainModel.SHELVES, productUuid);
	}

	/**
	 * 
	 * 设置商品下架,根据所选productUuid集合
	 * 
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateStateUndercarriadge(String productUuid) {
		myDao.updateStateByProductUuid(ProductMainModel.UNDERCARRIAGE,
				productUuid);
	}

	/**
	 * 根据商品uuid 查询商品主信息
	 * 
	 * @param productUuid
	 * @return
	 */
	public ProductMainModel getProductMainModelByProductUuid(String productUuid) {
		return myDao.getProductMainModelByProductUuid(productUuid);
	}

	/**
	 * 根据商品uuid修改审核状态为通过
	 * 
	 * @param uuid
	 */
	public void updateAuditStatePass(String uuid) {
		myDao.updateAuditStateByUuid(uuid, ProductMainModel.PASS);
	}

	/**
	 * 根据商品uuid修改审核状态为不通过
	 * 
	 * @param uuid
	 */
	public void updateAuditStateNoPass(String uuid) {
		myDao.updateAuditStateByUuid(uuid, ProductMainModel.NO_PASS);
		// 商品审核不通过，则商品下架
		updateStateUndercarriadge(uuid);
	}

	/**
	 * 根据商品编号来判断是否存在
	 * 
	 * @param productNo
	 * @return
	 */
	public boolean checkProductNoExist(String productNo) {

		return myDao.checkProductNoExist(productNo);
	}

	/**
	 * 根据商品编号来获取商品名称
	 * 
	 * @param productUuid
	 * @return
	 */
	public String getProductNameByUuid(String productUuid) {

		return myDao.getProductNameByUuid(productUuid);
	}

	/**
	 * 导入药品的excel文件
	 * 
	 * @param myfiles
	 */
	@Override
	public List<Object> updloadExcel(MultipartFile[] myfiles) {
		return myDao.updloadExcel(myfiles);
	}

	/**
	 * 根据药品名获得药品
	 * 
	 * @param productName
	 * @return
	 */
	@Override
	public List<ProductMainModel> getByProductName(String productName) {
		return myDao.getByProductName(productName);
	}
}