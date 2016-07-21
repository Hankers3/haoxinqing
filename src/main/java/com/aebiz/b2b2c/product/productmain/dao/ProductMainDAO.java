package com.aebiz.b2b2c.product.productmain.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainQueryModel;

/**
 * 
 * 商品主表业务数据操作接口
 * 
 * @author huangpinpin
 * 
 */
public interface ProductMainDAO extends
		BaseDAO<ProductMainModel, ProductMainQueryModel> {
	/**
	 * 设置商品上下架,根据所选productUuid集合
	 * 
	 * @param state
	 *            上下架状态
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateState(String state, List<String> productUuids);

	/**
	 * 设置商品上下架,根据所选productUuid
	 * 
	 * @param state
	 *            上下架状态
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateStateByProductUuid(String state, String productUuid);

	/**
	 * 根据商品uuid 查询商品主信息
	 * 
	 * @param productUuid
	 * @return
	 */
	public ProductMainModel getProductMainModelByProductUuid(String productUuid);

	/**
	 * 根据商品uuid修改审核状态
	 * 
	 * @param uuid
	 */
	public void updateAuditStateByUuid(String uuid, String state);

	/**
	 * 根据商品编号来判断是否存在
	 * 
	 * @param productNo
	 * @return
	 */
	public boolean checkProductNoExist(String productNo);

	/**
	 * 根据商品编号来获取商品名称
	 * 
	 * @param productUuid
	 * @return
	 */
	public String getProductNameByUuid(String productUuid);

	/**
	 * 导入药品的excel文件
	 * 
	 * @param myfiles
	 */
	public List<Object> updloadExcel(MultipartFile[] myfiles);

	/**
	 * 
	 * 
	 * @param productName
	 */
	public List<ProductMainModel> getByProductName(String productName);
}