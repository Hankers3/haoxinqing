package com.aebiz.b2b2c.product.producttemplate.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateQueryModel;


/**
 * 商品模板业务接口
 * <p>
 * 详情描述：不同种类的商品有不同的模板。<br>
 * 比如：服装它有颜色、尺寸属性;杯子它有颜色、体积属性<br>
 * 商品模板是和商品分类有关联的, 该类主要是后台管理员对商品模板的增删查改业务进行实现<br>
 * 
 * 
 * @author huangpinpin
 * 
 */
public interface ProductTemplateService extends BaseService<ProductTemplateModel,ProductTemplateQueryModel>{
	
	/**
	 * 根据集合uuid查询模板集合
	 * @param uuids
	 * @return
	 */
	public List<ProductTemplateModel> getProductTemplateByUuids(List<String> uuids);
	/**
	 * 根据模板uuid查询模板名称
	 * @param uuid
	 * @return
	 */
	public String getTemplateNameByUuids(String uuid);
	
}
