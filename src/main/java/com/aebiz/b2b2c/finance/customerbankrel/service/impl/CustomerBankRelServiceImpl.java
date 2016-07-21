package com.aebiz.b2b2c.finance.customerbankrel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountService;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;
import com.aebiz.b2b2c.finance.customerbankrel.dao.CustomerBankRelDAO;
import com.aebiz.b2b2c.finance.customerbankrel.service.CustomerBankRelService;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelQueryModel;
/**
 * 会员提出绑定申请,申请完,会员是不能立即去验证的,由平台给申请记录中的银行卡打一定的金额,<br>
 * 
 * 然后后台更新这个金额,并且把验证状态修改为待验证状态,<br>
 * 
 * 这时会员可以根据收到的金额去和会员中心验证,如果错误3次那么就不能再次验证了,需要联系平台
 *
 * @author tangyunkai
 *
 * @date 2014年12月20日 下午12:26:57 
 *
 */
@Service
@Transactional
public class CustomerBankRelServiceImpl extends BaseServiceImpl<CustomerBankRelModel,CustomerBankRelQueryModel> implements CustomerBankRelService {
	private CustomerBankRelDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(CustomerBankRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	//注入会员账户service
	@Autowired
	private CustomerAccountService customerAccountService;

	@Override
	public String create(CustomerBankRelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		//获取当前系统时间
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		//获取当前会员ID
		//m.setCustomerUuid(LoginUserHelper.getCustomerLoginUserUuid());
		
		m.setCardValidateState(CustomerBankRelModel.NO_CHECK);
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(CustomerBankRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(CustomerBankRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 在平台打款修改验证金额之前,会员自己可以修改绑定信息
	 * @param m 
	 * void
	 */
	public void updateBankRel(CustomerBankRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getCustomerLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		//获取当前系统时间
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		//获取当前会员ID
		//m.setCustomerUuid(LoginUserHelper.getCustomerLoginUserUuid());
		
		m.setCardValidateState(CustomerBankRelModel.NO_CHECK);
		this.update(m);
	}
	
	/**
	 * 更新验证的金额,把验证状态修改为待验证状态,<br>
	 * 
	 * 会员可以根据银行卡收到的金额去验证
	 * @param uuid
	 * @param vilidateMount 
	 * void
	 */
	public boolean updateVilidateMount(String uuid,String vilidateMount){
		boolean flag = false;
		CustomerBankRelModel bankRelModel = this.getByUuid(uuid);
		if(bankRelModel != null){
			//设置打款的金额
			bankRelModel.setVilidateMount(Float.parseFloat(vilidateMount));
			//修改验证状态为待验证
			bankRelModel.setCardValidateState(CustomerBankRelModel.UNDER_CHECK);
			bankRelModel.setOper(LoginUserHelper.getLoginUserUuid());
			this.update(bankRelModel);
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据会员的uuid查询绑定申请对象,来判断该会员是否已经申请过绑定了
	 * @param uuid
	 * @return 
	 * CustomerBankRelModel
	 */
	public CustomerBankRelModel getCustomerBankRelModelByCustomerUuid(String uuid){
		return myDao.getCustomerBankRelModelByCustomerUuid(uuid);
	}
	
	/**
	 * 验证会员输入的金额,判定是否成功
	 * @param uuid
	 * @param amount
	 * @return 
	 * boolean
	 */
	public CustomerBankRelModel checkValidateAmount(String uuid,String amount){
		//获取绑定申请对象
		CustomerBankRelModel bankRelModel = this.getByUuid(uuid);
		if(bankRelModel != null){
			//输入的金额一致
			if(bankRelModel.getVilidateMount() == Float.parseFloat(amount)){
				//修改会员的账户银行信息和绑定申请的状态
				this.updateCustomerAccountAndCustomerBankRel(bankRelModel);
				
			}else{
				//失败次数加一
				int failTimes = bankRelModel.getFailTimes() + 1;
				bankRelModel.setFailTimes(failTimes);
				
				//如果是失败次数为3次,那么该会员申请的状态置为失败状态
				if(failTimes == 3){
					bankRelModel.setCardValidateState(CustomerBankRelModel.CHECK_FAIL);
				}
				
				bankRelModel.setOper(LoginUserHelper.getCustomerLoginUserUuid());
				this.update(bankRelModel);
			}
		}
		
		return bankRelModel;
	}
	
	/**
	 * 修改会员账户银行卡信息和绑定银行卡申请状态
	 * @param bankRelModel
	 * @return 
	 * boolean
	 */
	private boolean updateCustomerAccountAndCustomerBankRel(CustomerBankRelModel bankRelModel){
		boolean flag = false;
		CustomerAccountModel accountModel = customerAccountService.getCustomerAccountModelByCustomerUuid(bankRelModel.getCustomerUuid());
		if(accountModel != null){
			//银行卡号
			accountModel.setBankNo(bankRelModel.getCardNo());
			//开户名
			accountModel.setCustomerName(bankRelModel.getOpenAccountName());
			//开户行
			accountModel.setBankName(bankRelModel.getOpenAccountBank());
			customerAccountService.update(accountModel);
			
			//申请绑定置为成功
			bankRelModel.setCardValidateState(CustomerBankRelModel.CHECK_PASS);
			bankRelModel.setOper(LoginUserHelper.getCustomerLoginUserUuid());
			bankRelModel.setFailTimes(0);
			this.update(bankRelModel);
			flag = true;
		}
		return flag;
	}
}