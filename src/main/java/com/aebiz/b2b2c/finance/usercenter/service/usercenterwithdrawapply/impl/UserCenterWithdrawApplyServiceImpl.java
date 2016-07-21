package com.aebiz.b2b2c.finance.usercenter.service.usercenterwithdrawapply.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.usercenter.dao.usercenterwithdrawapply.UserCenterWithdrawApplyDAO;
import com.aebiz.b2b2c.finance.usercenter.service.usercenterwithdrawapply.UserCenterWithdrawApplyService;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyModel;
import com.aebiz.b2b2c.finance.withdrawapply.vo.WithdrawApplyQueryModel;

@Service
@Transactional
public class UserCenterWithdrawApplyServiceImpl extends BaseServiceImpl<WithdrawApplyModel,WithdrawApplyQueryModel> implements UserCenterWithdrawApplyService {
	
	private UserCenterWithdrawApplyDAO myDao = null;
	
	@Autowired
	private UuidService us;
	
	@Autowired
	public void setMyDao(UserCenterWithdrawApplyDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	// 会员帐户财务信息
	@Autowired
	private CustomerAccountService customerAccountService;
	
	@Override
	public String create(WithdrawApplyModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(WithdrawApplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(WithdrawApplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	public void saveExtractionMoney(CustomerAccountModel caModel, WithdrawApplyModel paramM, CustomerModel cm, String userCenterUuid, String applyMoney, String note) {
		if (caModel != null) {
			// 保存提现信息
			paramM = new WithdrawApplyModel();
			// 会员
			paramM.setUserType(WithdrawApplyModel.USERTYPE_CUSTOMER);
			paramM.setUserUuid(userCenterUuid);
			// 会员余额
			paramM.setMoneyType(WithdrawApplyModel.COUSTOMER);

			paramM.setApplyMoney(Float.valueOf(applyMoney));

			paramM.setApplyTime(DateFormatHelper.getNowTimeStr());
			// 未处理
			paramM.setState(WithdrawApplyModel.APPLY_STATE_UNHANDLE);
			paramM.setNote("");
			paramM.setFailReason("");
			paramM.setSuccessTime("");
			if (!StringUtil.isEmpty(note)) {
				paramM.setNote(note);
			} else {
				paramM.setNote("");
			}

			if (cm != null) {
				paramM.setUserNo(cm.getCustomerId());
				paramM.setUserName(cm.getCustomerName());
			}

			// 更改会员财务帐户

			// 修改会员余额
			caModel.setAccountBalance(caModel.getAccountBalance() - Float.valueOf(applyMoney));
			// 冻结资金
			caModel.setVirtualFrezonMount(caModel.getVirtualFrezonMount() + Float.valueOf(applyMoney));
		}
		//修改会员财务信息
		customerAccountService.update(caModel);
		//添加会员提现信息
		this.create(paramM);
	}
}