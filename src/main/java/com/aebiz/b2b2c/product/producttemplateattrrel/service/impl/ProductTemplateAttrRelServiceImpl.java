package com.aebiz.b2b2c.product.producttemplateattrrel.service.impl;

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
import com.aebiz.b2b2c.product.producttemplateattr.service.ProductTemplateAttrService;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.dao.ProductTemplateAttrRelDAO;
import com.aebiz.b2b2c.product.producttemplateattrrel.service.ProductTemplateAttrRelService;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.vo.ProductTemplateAttrRelQueryModel;

/**
 * 
 * 商品模板和属性关系业务实现
 * 
 * 一个模板可以关联多个属性，比如模板里面有颜色/尺寸等属性
 * 
 * @author huangpinpin
 *
 */
@Service
@Transactional
public class ProductTemplateAttrRelServiceImpl extends BaseServiceImpl<ProductTemplateAttrRelModel,ProductTemplateAttrRelQueryModel> implements ProductTemplateAttrRelService {
	private ProductTemplateAttrRelDAO myDao = null;
	
	@Autowired
	private ProductTemplateAttrService attributeSerivce;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductTemplateAttrRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductTemplateAttrRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductTemplateAttrRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductTemplateAttrRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据模板uuid查找已经关联该模板的属性uuid
	 * 用于过滤已经关联该模板属性的列表查询
	 * @param templateUuid 模板uuid
	 * @return
	 */
	public List<String> getAttributeUuidsByUuid(String templateUuid) {
		return myDao.getAttributeUuidsByUuid(templateUuid);
	}
	
	/**
	 * 关联所选择的属性到该模板下
	 * @param needRelUuids 所选属性uuid
	 * @param templateUuid 模板uuid
	 */
	public void relation(List<String> needRelUuids, String templateUuid) {
		if(needRelUuids!=null&&needRelUuids.size()>0){
			for(int i=0;i<needRelUuids.size();i++){
				//由于选择后，list后面会追加个""数据
				if(StringUtil.isEmpty(needRelUuids.get(i))){
					continue;
				}
				//根据属性uuid获取属性
				ProductTemplateAttrModel atrributeModel=attributeSerivce.getByUuid(needRelUuids.get(i));
				
				ProductTemplateAttrRelModel rel=new ProductTemplateAttrRelModel();
				rel.setTemplateUuid(templateUuid);
				rel.setCanAttribute(rel.ATTRIBUTE_NO);
				rel.setAttributeUuid(atrributeModel.getUuid());
				this.create(rel);
				
			}
			
		}
	}
	
	/**
	 * 批量更新模板属性关系表
	 * @param selectRelUuids
	 */
	public void batchUpdate(List<String> selectRelUuids) {
		if(selectRelUuids!=null&&selectRelUuids.size()>0){
			for(int i=0;i<selectRelUuids.size();i++){
				
				String tempStr=selectRelUuids.get(i);
				//由于选择后，list后面会追加个""数据
				if(StringUtil.isEmpty(tempStr)){
					continue;
				}
				
				//index=0表示uuid,index=1表示类型值,index=3表示位置
				String[] datas=tempStr.split("\\|");
				ProductTemplateAttrRelModel m=this.getByUuid(datas[0]);
				m.setCanAttribute(datas[1]);
				m.setType(datas[2]);
				if(!StringUtil.isEmpty(datas[3])){//数据不为空,转换为int类型
					m.setPosition(Integer.parseInt(datas[3]));
				}
				
				this.update(m);
			}
			
		}
	}
	
	/**
	 * 根据模板uuid查找已经关联该模板的属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getAttributeByUuid(String templateUuid){
		return myDao.getAttributeByUuid(templateUuid);
	}
	
	/**
	 * 根据模板uuid查找规格属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getSpecAttributeUuidsByUuid(String templateUuid){
		return myDao.getSpecAttributeUuidsByUuid(templateUuid);
	}
	
	/**
	 * 根据模板uuid查找不是规格属性集合
	 * @param templateUuid 模板
	 * @return
	 */
	public List<ProductTemplateAttrRelModel> getNotSpecAttributeUuidsByUuid(String templateUuid){
		return myDao.getNotSpecAttributeUuidsByUuid(templateUuid);
	}
	
}