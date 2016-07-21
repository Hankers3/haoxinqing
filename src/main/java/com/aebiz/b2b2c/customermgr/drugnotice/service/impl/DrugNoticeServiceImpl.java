package com.aebiz.b2b2c.customermgr.drugnotice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.drugnotice.dao.DrugNoticeDAO;
import com.aebiz.b2b2c.customermgr.drugnotice.service.DrugNoticeService;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeModel;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeQueryModel;

@Service
@Transactional
public class DrugNoticeServiceImpl extends BaseServiceImpl<DrugNoticeModel,DrugNoticeQueryModel> implements DrugNoticeService {
	private DrugNoticeDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(DrugNoticeDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DrugNoticeModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(DrugNoticeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(DrugNoticeModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 
	 * @Description: (根据患者的id获取服药提醒的列表)    
	 * @author XP  
	 * @param customerUuid
	 * @return
	 * @date 2016-1-13 下午3:08:16
	 */
        @Override
        public List<DrugNoticeModel> getDrugNoticeListByCustomerUuid(String customerUuid) {
            return myDao.getDrugNoticeListByCustomerUuid(customerUuid);
        }
}