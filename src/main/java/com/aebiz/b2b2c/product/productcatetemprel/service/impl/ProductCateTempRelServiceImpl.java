package com.aebiz.b2b2c.product.productcatetemprel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productcatetemprel.dao.ProductCateTempRelDAO;
import com.aebiz.b2b2c.product.productcatetemprel.service.ProductCateTempRelService;
import com.aebiz.b2b2c.product.productcatetemprel.vo.ProductCateTempRelModel;
import com.aebiz.b2b2c.product.productcatetemprel.vo.ProductCateTempRelQueryModel;
import com.aebiz.b2b2c.product.producttemplate.service.ProductTemplateService;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
/**
 * 平台分类和商品模板关系业务实现</br>
 * 在操作平台分类的时候，可以关联商品模板</br>
 * 平台分类和商品模板是多对多的关系
 * 
 * @author huangpinpin
 *
 */
@Service
@Transactional
public class ProductCateTempRelServiceImpl extends BaseServiceImpl<ProductCateTempRelModel,ProductCateTempRelQueryModel> implements ProductCateTempRelService {
	private ProductCateTempRelDAO myDao = null;
	@Autowired
	private ProductTemplateService templateService;
	
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductCateTempRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductCateTempRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductCateTempRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductCateTempRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 
	 * 根据分类uuid获取已关联模板uuid</br>
	 * 在分类关联模板时，弹出框列表中屏蔽已经关联该分类的模板关联按钮</br>
	 * 一个分类可以关联多个模板
	 * @param categoryUuid 分类uuid
	 * @return
	 */
	public List<String> getTemplateUuidsByCategoryUuid(String categoryUuid){
		return myDao.getTemplateUuidsByCategoryUuid(categoryUuid);
	}
	
	/**
	 * 关联模板到该分类下</br>
	 * 在分类编辑也中关联模板弹出框中，点击批量关联时使用
	 * @param needRelUuids 关联模板uuid
	 * @param categoryUuid 分类uuids
	 */
	public void relation(List<String> needRelUuids, String categoryUuid){
		if(needRelUuids!=null&&needRelUuids.size()>0){
			for(int i=0;i<needRelUuids.size();i++){
				//由于选择后，list后面会追加个""数据
				if(StringUtil.isEmpty(needRelUuids.get(i))){
					continue;
				}
				//根据标签uuid获取标签
				ProductTemplateModel templateModel=templateService.getByUuid(needRelUuids.get(i));
				
				ProductCateTempRelModel rel=new ProductCateTempRelModel();
				rel.setCategoryUuid(categoryUuid);
				rel.setPosition(0);
				
				rel.setTemplateUuid(templateModel.getUuid());
				
				this.create(rel);
			}
			
		}
	}
	
	/**
	 * 根据模板id集合和分类uuid删除分类模板关系表数据</br>
	 * 在分类关联模板时，弹出框列表中点击取消关联调用
	 * @param needDeleteUuids 模板uuid集合
	 * @param categoryUuid 分类uuid
	 */
	public void deleteByIdAndCategoryUuid(List<String> needDeleteUuids,String categoryUuid){
		myDao.deleteByIdAndCategoryUuid(needDeleteUuids, categoryUuid);
	}
}