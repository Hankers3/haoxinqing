package com.aebiz.b2b2c.finance.ledgerbystorecategory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.dao.LedgerByStoreCategoryDAO;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.service.LedgerByStoreCategoryService;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryModel;
import com.aebiz.b2b2c.finance.ledgerbystorecategory.vo.LedgerByStoreCategoryQueryModel;
import com.aebiz.b2b2c.finance.ledgerbystorelog.service.LedgerByStoreLogService;
import com.aebiz.b2b2c.finance.ledgerbystorelog.vo.LedgerByStoreLogModel;

@Service
@Transactional
public class LedgerByStoreCategoryServiceImpl extends BaseServiceImpl<LedgerByStoreCategoryModel,LedgerByStoreCategoryQueryModel> implements LedgerByStoreCategoryService {
	private LedgerByStoreCategoryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(LedgerByStoreCategoryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	
	//注入修改商户分账比率的日志service
	@Autowired
	private LedgerByStoreLogService byStoreLogService;

	@Override
	public String create(LedgerByStoreCategoryModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(LedgerByStoreCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(LedgerByStoreCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据商户的uuid和商品分类的uuid获取LedgerByStoreCategoryModel对象
	 * 
	 * @param accountUuid
	 * @param categoryUuid
	 * @return LedgerByStoreCategoryModel
	 */
	public LedgerByStoreCategoryModel getLedgerByStoreCategoryModelByAccountUuidAndCategoryUuid(
			String accountUuid, String categoryUuid){
		return myDao.getLedgerByStoreCategoryModelByAccountUuidAndCategoryUuid(accountUuid, categoryUuid);
	}
	
	/**
	 * 添加或者更新商户商品分类的分账比率和运费是否参与分账信息
	 * @param accountUuid 商户uuid
	 * @param ledgerRate 分账比率
	 * @param categoryUuid 分类uuid
	 * @return 
	 * boolean
	 */
	public boolean addOrUpdateLedgerRate(String accountUuid,String ledgerRate,String categoryUuid){
		boolean flag = false;
		//从数据库中读取该商户的分账设置信息
		LedgerByStoreCategoryModel ledgerByStoreCategory = this.getLedgerByStoreCategoryModelByAccountUuidAndCategoryUuid(accountUuid, categoryUuid);
		
		if(ledgerByStoreCategory != null){
			//增加修改日志
			LedgerByStoreLogModel byStoreLogModel = new LedgerByStoreLogModel();
			byStoreLogModel.setBeforeRate(ledgerByStoreCategory.getLedgerRate());
			byStoreLogModel.setAfterRate(Double.parseDouble(ledgerRate));
			byStoreLogService.create(byStoreLogModel);
			
			//更新商户的分账比率信息
			ledgerByStoreCategory.setLedgerRate(Double.parseDouble(ledgerRate));
			
			this.update(ledgerByStoreCategory);
			flag = true;
			
		}else{
			ledgerByStoreCategory = new LedgerByStoreCategoryModel();
			ledgerByStoreCategory.setLedgerRate(Double.parseDouble(ledgerRate));
			ledgerByStoreCategory.setCategoryUuid(categoryUuid);
			ledgerByStoreCategory.setAccountUuid(accountUuid);
			
			this.create(ledgerByStoreCategory);
			flag = true;
		}
		
		return flag;
	}
}