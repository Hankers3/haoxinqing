package com.aebiz.b2b2c.product.productcategoryrel.service.impl;

import java.util.ArrayList;
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
import com.aebiz.b2b2c.product.productcategoryrel.dao.ProductCategoryRelDAO;
import com.aebiz.b2b2c.product.productcategoryrel.service.ProductCategoryRelService;
import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelModel;
import com.aebiz.b2b2c.product.productcategoryrel.vo.ProductCategoryRelQueryModel;

@Service
@Transactional
public class ProductCategoryRelServiceImpl extends BaseServiceImpl<ProductCategoryRelModel,ProductCategoryRelQueryModel> implements ProductCategoryRelService {
	private ProductCategoryRelDAO myDao = null;
	
	@Autowired
	private FrontPlatformCategoryLogService categoryLogService;
	
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductCategoryRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductCategoryRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductCategoryRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductCategoryRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 判断是否已经关联
	 * @param frontCategoryUuid
	 * 					前天分类uuid
	 * @param platCategoryUuid
	 * 					后台分类uuid
	 * @return
	 */
	public boolean isHasRelCategory(String frontCategoryUuid,String platCategoryUuid){
		ProductCategoryRelModel model=myDao.getByFrontCategoryUuidAndPlatCategoryUuid(frontCategoryUuid, platCategoryUuid);
		if(model!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * 前台关联后台分类
	 * 
	 * @param platCategoryUuids
	 * @param frontCategoryUuid
	 */
	public void batchAdd(List<String> platCategoryUuids,String frontCategoryUuid){
		List<String> oldPlatCategoryUuids= getPlatfromCategoryUuidsByFrontCategoryUuid(frontCategoryUuid);
		if(platCategoryUuids!=null && platCategoryUuids.size()>0){
			List<String> addPlatfromCategoryUuids=new ArrayList<String>();
			for (String platCategoryUuid : platCategoryUuids) {
				if(StringUtil.isEmpty(platCategoryUuid)){
					continue;
				}
				// 旧数据里面包含选择的分类，则继续
				if(oldPlatCategoryUuids.contains(platCategoryUuid)){
					continue;
				}
				ProductCategoryRelModel model=new ProductCategoryRelModel();
				model.setPlatCategoryUuid(platCategoryUuid);
				model.setFrontCategoryUuid(frontCategoryUuid);
				this.create(model);
				addPlatfromCategoryUuids.add(platCategoryUuid);
			}
			
			List<String> deletePlatfromCategoryUuids=new ArrayList<String>();
			for (String oldDate : oldPlatCategoryUuids) {
				// 选择的分类里面没有包含旧数据 则删除
				if(!platCategoryUuids.contains(oldDate)){
					deletePlatfromCategoryUuids.add(oldDate);
				}
			}
			if(deletePlatfromCategoryUuids!=null && deletePlatfromCategoryUuids.size()>0){
				deletesByFrontAndPlatfromCategoryUuids(deletePlatfromCategoryUuids, frontCategoryUuid);
				categoryLogService.bathAddLog(frontCategoryUuid, deletePlatfromCategoryUuids, FrontPlatformCategoryLogModel.DELETE);
			}
			
			categoryLogService.bathAddLog(frontCategoryUuid, addPlatfromCategoryUuids, FrontPlatformCategoryLogModel.ADD);
		}else{
			// 没有选择 分类
			deletesByFrontAndPlatfromCategoryUuids(oldPlatCategoryUuids, frontCategoryUuid);
			categoryLogService.bathAddLog(frontCategoryUuid, oldPlatCategoryUuids, FrontPlatformCategoryLogModel.DELETE);
		}
	}
	
	/**
	 * 根据前台分类uuid 获取后台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidsByFrontCategoryUuid(String frontCategoryUuid){
		return myDao.getPlatfromCategoryUuidsByFrontCategoryUuid(frontCategoryUuid);
	}
	
	/**
	 * 根据后台分类uuid 获取前台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getFrontCategoryUuidsByPlatCategoryUuid(String platCategoryUuid){
		return myDao.getFrontCategoryUuidsByPlatCategoryUuid(platCategoryUuid);
	}
	
	/**
	 * 根据前台分类uuid集合, 获取后台分类uuid集合
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidsByFrontCategoryUuids(List<String> frontCategoryUuids){
		return myDao.getPlatfromCategoryUuidsByFrontCategoryUuids(frontCategoryUuids);
	}
	
	/**
	 * 删除没有关联的后台分类
	 * 
	 * @param needDeleteUuids
	 * @param frontCategoryUuid
	 */
	public void deletesByFrontAndPlatfromCategoryUuids(List<String> needDeleteUuids,String frontCategoryUuid){
		myDao.deletesByFrontAndPlatfromCategoryUuids(needDeleteUuids, frontCategoryUuid);
	}
}