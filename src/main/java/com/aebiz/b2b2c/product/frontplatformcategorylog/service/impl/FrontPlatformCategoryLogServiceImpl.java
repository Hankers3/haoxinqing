package com.aebiz.b2b2c.product.frontplatformcategorylog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.product.frontplatformcategorylog.service.FrontPlatformCategoryLogService;
import com.aebiz.b2b2c.product.frontplatformcategorylog.dao.FrontPlatformCategoryLogDAO;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogModel;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogQueryModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;

@Service
@Transactional
public class FrontPlatformCategoryLogServiceImpl extends BaseServiceImpl<FrontPlatformCategoryLogModel,FrontPlatformCategoryLogQueryModel> implements FrontPlatformCategoryLogService {
	private FrontPlatformCategoryLogDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(FrontPlatformCategoryLogDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(FrontPlatformCategoryLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(FrontPlatformCategoryLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	@Override
	public void delete(FrontPlatformCategoryLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 获取所有操作得后台分类uuid集合
	 * 
	 * @return
	 */
	public List<String> getPlatfromCategoryUuidNoSame(){
		return myDao.getPlatfromCategoryUuidNoSame();
	}
	
	/**
	 * 批量添加分类日志
	 * 
	 * @param frontCategoryUuid
	 * @param platfromCategoryUuids
	 * @param type
	 */
	public void bathAddLog(String frontCategoryUuid,List<String> platfromCategoryUuids,String type){
		if(platfromCategoryUuids==null || platfromCategoryUuids.size()==0){
			return ;
		}
		
		for (String platfromCategoryUuid : platfromCategoryUuids) {
			if(StringUtil.isEmpty(platfromCategoryUuid)){
				continue;
			}
			FrontPlatformCategoryLogModel m=new FrontPlatformCategoryLogModel();
			m.setFrontCategoryuuid(frontCategoryUuid);
			m.setPlatformcategoryUuid(platfromCategoryUuid);
			m.setType(type);
			create(m);
		}
	}
	
}