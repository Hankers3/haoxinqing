package com.aebiz.b2b2c.product.productmain.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainQueryModel;

/**
 * 商品主表业务接口
 * 
 * @author huangpinpin
 * 
 */
public interface ProductMainService extends
		BaseService<ProductMainModel, ProductMainQueryModel> {
	/**
	 * 设置商品上架,根据所选productUuid集合
	 * 
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateStateShelves(String productUuids);

	/**
	 * 设置商品下架,根据所选productUuid集合
	 * 
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateStateUndercarriadge(String productUuids);

	/**
	 * 根据商品uuid 查询商品主信息
	 * 
	 * @param productUuid
	 * @return
	 */
	public ProductMainModel getProductMainModelByProductUuid(String productUuid);

	/**
	 * 根据商品uuid修改审核状态为通过
	 * 
	 * @param uuid
	 */
	public void updateAuditStatePass(String uuid);

	/**
	 * 根据商品uuid修改审核状态为不通过
	 * 
	 * @param uuid
	 */
	public void updateAuditStateNoPass(String uuid);

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
	 * 上传Excel文件
	 * 
	 * @param myfiles
	 */
	public List<Object> updloadExcel(MultipartFile[] myfiles);

	/**
	 * 根据药品名获得list
	 * 
	 * @param productUuid
	 * @return
	 */
	public List<ProductMainModel> getByProductName(String productName);
}
