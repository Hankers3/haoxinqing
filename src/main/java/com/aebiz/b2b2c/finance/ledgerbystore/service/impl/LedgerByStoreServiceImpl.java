package com.aebiz.b2b2c.finance.ledgerbystore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.ledgerbystore.dao.LedgerByStoreDAO;
import com.aebiz.b2b2c.finance.ledgerbystore.service.LedgerByStoreService;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreModel;
import com.aebiz.b2b2c.finance.ledgerbystore.vo.LedgerByStoreQueryModel;
import com.aebiz.b2b2c.finance.ledgerbystorelog.service.LedgerByStoreLogService;
import com.aebiz.b2b2c.finance.ledgerbystorelog.vo.LedgerByStoreLogModel;

@Service
@Transactional
public class LedgerByStoreServiceImpl extends BaseServiceImpl<LedgerByStoreModel,LedgerByStoreQueryModel> implements LedgerByStoreService {
	private LedgerByStoreDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(LedgerByStoreDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	//注入修改商户分账比率的日志service
	@Autowired
	private LedgerByStoreLogService byStoreLogService;

	@Override
	public String create(LedgerByStoreModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(LedgerByStoreModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(LedgerByStoreModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据accountUuid查询LedgerByStoreModel对象
	 * @param accountUuid
	 * @return 
	 * LedgerByStoreModel
	 */
	public LedgerByStoreModel getLedgerByStoreModelByAccountUuid(String accountUuid){
		return myDao.getLedgerByStoreModelByAccountUuid(accountUuid);
	}
	
	/**
	 * 添加或者更新分账比率和运费是否参与分账信息
	 * @param accountUuid 商户uuid
	 * @param ledgerRate 分账比率
	 * @param freightJoinRouting 运费是否参与分账
	 * @return  boolean
	 */
	public boolean addOrUpdateLedgerRate(String accountUuid,String ledgerRate,String freightJoinRouting){
		boolean flag = false;
		//从数据库中读取该商户的分账设置信息
		LedgerByStoreModel byStoreModel = this.getLedgerByStoreModelByAccountUuid(accountUuid);
		
		if(byStoreModel != null){
			//增加修改日志
			LedgerByStoreLogModel byStoreLogModel = new LedgerByStoreLogModel();
			byStoreLogModel.setBeforeRate(byStoreModel.getLedgerRate());
			byStoreLogModel.setAfterRate(Double.parseDouble(ledgerRate));
			byStoreLogService.create(byStoreLogModel);
			
			//更新分账比率信息
			byStoreModel.setLedgerRate(Double.parseDouble(ledgerRate));
			byStoreModel.setFreightJoinRouting(freightJoinRouting);
			this.update(byStoreModel);
			flag = true;
			
		}else{
			byStoreModel = new LedgerByStoreModel();
			byStoreModel.setLedgerRate(Double.parseDouble(ledgerRate));
			byStoreModel.setFreightJoinRouting(freightJoinRouting);
			byStoreModel.setAccountUuid(accountUuid);
			this.create(byStoreModel);
			flag = true;
		}
		
		return flag;
	}
	/**
	 * 获取所需的百分比总和
	 * 
	 * @param accountUuids
	 * @return
	 */
	public double sumLedgerRate(List<String> accountUuids) {
		
		return myDao.sumLedgerRate(accountUuids);
	}
}