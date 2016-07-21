package com.aebiz.b2b2c.product.frontproductcatetemprel.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.aebiz.b2b2c.product.frontplatformcategorylog.service.FrontPlatformCategoryLogService;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogModel;
import com.aebiz.b2b2c.product.frontproductcatetemprel.dao.FrontProductCateTempRelDAO;
import com.aebiz.b2b2c.product.frontproductcatetemprel.service.FrontProductCateTempRelService;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelModel;
import com.aebiz.b2b2c.product.frontproductcatetemprel.vo.FrontProductCateTempRelQueryModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.SelectAttributeJson;
import com.aebiz.b2b2c.product.productcategoryrel.service.ProductCategoryRelService;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class FrontProductCateTempRelServiceImpl
		extends
		BaseServiceImpl<FrontProductCateTempRelModel, FrontProductCateTempRelQueryModel>
		implements FrontProductCateTempRelService {
	private FrontProductCateTempRelDAO myDao = null;
	
	@Autowired
	private FrontPlatformCategoryLogService categoryLogService;
	
	@Autowired
	private ProductCategoryRelService categoryRelService;
	
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(FrontProductCateTempRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(FrontProductCateTempRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(FrontProductCateTempRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(FrontProductCateTempRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据前台分类id获取关联的分类属性模板json数据
	 * 
	 * @param categoryUuid
	 * 				前台分类uuid
	 * @return
	 */
	public List<SelectAttributeJson> getAttrJsonByCategoryUuid(
			String categoryUuid) {
		List<SelectAttributeJson> returnList = new ArrayList<SelectAttributeJson>();
		String json = this.myDao.getAttrJsonByCategoryUuid(categoryUuid);
		if(!StringUtil.isEmpty(json)){
			returnList = JSON.parseArray(json, SelectAttributeJson.class);
			//排序
			if(returnList.size()>0){
				Collections.sort(returnList, new Comparator(){
					public int compare(Object o1, Object o2) {
				       int n1 = ((SelectAttributeJson)o1).getPosition();
				       int n2 = ((SelectAttributeJson)o2).getPosition();
				       return n1-n2;
				     }
				});
			}
		}
		return returnList;
	}
	
	/**
	 * 根据前台分类uuid,获取关联数据对象
	 * 
	 * @param frontCategoryUuid
	 * @return
	 */
	public FrontProductCateTempRelModel getByFrontCategoryUuid(String frontCategoryUuid){
		return myDao.getByFrontCategoryUuid(frontCategoryUuid);
	}
	
	/**
	 * 把属性集合按位置排序
	 * 
	 * @param tempList
	 * @return
	 */
	private List<SelectAttributeJson> sortAttributes(List<SelectAttributeJson> tempList){
		Collections.sort(tempList, new Comparator(){
			public int compare(Object o1, Object o2) {
		       int n1 = ((SelectAttributeJson)o1).getPosition();
		       int n2 = ((SelectAttributeJson)o2).getPosition();
		       return n1-n2;
		     }
		});
		return tempList;
	}
	
	
	/**
	 * 根据前台分类uuid集合,获取属性模板json数据集合
	 * 
	 * @param categoryUuids
	 * 				前台分类uuid集合
	 * @return
	 */
	public List<SelectAttributeJson> getAttrJsonsByCategoryUuids(List<String> categoryUuids){
		List<SelectAttributeJson> list = new ArrayList<SelectAttributeJson>();
		List<String> jsonStrs=myDao.getAttrJsonsByCategoryUuids(categoryUuids);
		if(jsonStrs==null || jsonStrs.size()==0){
			return null;
		}
		for (String jsonStr : jsonStrs) {
			if(!StringUtil.isEmpty(jsonStr)){
				List<SelectAttributeJson> tempList = JSON.parseArray(jsonStr, SelectAttributeJson.class);
				list.addAll(tempList);
			}
		}
		sortAttributes(list);
		return list;
	}
	
	/**
	 * 前台分类关联属性
	 * 
	 * @param frontCategoryUuid
	 * @param attrJson
	 */
	public void saveAttribute(String frontCategoryUuid,String attrJson){
		
		FrontProductCateTempRelModel m = getByFrontCategoryUuid(frontCategoryUuid);
		if (m != null) {
			m.setAttrJson(attrJson);
			update(m);
		} else {
			FrontProductCateTempRelModel relModel = new FrontProductCateTempRelModel();
			relModel.setCategoryUuid(frontCategoryUuid);
			relModel.setAttrJson(attrJson);
			create(relModel);
		}
		
		List<String> platfromCategoryUuids = categoryRelService.getPlatfromCategoryUuidsByFrontCategoryUuid(frontCategoryUuid);
		categoryLogService.bathAddLog(frontCategoryUuid, platfromCategoryUuids, FrontPlatformCategoryLogModel.UPDATE);
		
	}
}