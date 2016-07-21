package com.aebiz.b2b2c.customermgr.customersource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customersource.dao.CustomerSourceDAO;
import com.aebiz.b2b2c.customermgr.customersource.service.CustomerSourceService;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceQueryModel;

@Service
@Transactional
public class CustomerSourceServiceImpl extends
		BaseServiceImpl<CustomerSourceModel, CustomerSourceQueryModel>
		implements CustomerSourceService {
	private CustomerSourceDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerSourceDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerSourceModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerSourceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerSourceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过会员编号获取会员来源信息
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	public CustomerSourceModel getCustomerSourceModelByCustomerUuid(
			String customerUuid) {
		return this.myDao.getCustomerSourceModelByCustomerUuid(customerUuid);
	}

	/**
	 * 邀请码是否存在  存在返回1、不存在返回0
	 * @param inviteCode
	 * @return
	 */
	@Override
	public String inviteCodeIsExist(String inviteCode) {
		List<String> list = myDao.getInviteCodes();
		if (list.contains(inviteCode)) {
			return "1";
		}else {
			return "0";
		}
	}
	/**
	 * 判断用户注册时生成的邀请码是否存在
	 * @param cashId 邀请码
	 * @return true为存在；false为不存在
	 */
	@Override
	public boolean MyInviteCodeIsExist(String cashId) {
		//根据cashId在CustomerSourceModel的MyInviteCode=cashId查询
		List<CustomerSourceModel> list = myDao.getModelByMyInviteCodes(cashId);
		if(list.size()>0&&null!=list)
		{
			return true;
		}
		return false;
	}

	@Override
	public CustomerSourceModel getModelByMyInviteCodes(String cashId) {
		List<CustomerSourceModel> list = myDao.getModelByMyInviteCodes(cashId);
		if(list!=null&&list!=null){
			return list.get(0);
		}
		return null;
	}
}