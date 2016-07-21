package com.aebiz.b2b2c.finance.storeeditbankbound.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.basicdata.interactive.service.AreasInteractiveService;
import com.aebiz.b2b2c.finance.storeeditbankbound.dao.EditBankBoundDAO;
import com.aebiz.b2b2c.finance.storeeditbankbound.service.EditBankBoundService;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundModel;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundQueryModel;

@Service
@Transactional
public class EditBankBoundServiceImpl extends BaseServiceImpl<EditBankBoundModel,EditBankBoundQueryModel> implements EditBankBoundService {
	private EditBankBoundDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(EditBankBoundDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	//注入基础数据库系统接口,为了获取省市县名字
	@Autowired
	private AreasInteractiveService areasInteractiveService;

	@Override
	public String create(EditBankBoundModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		//分别获取省市县名称存到库里
		String addressname = areasInteractiveService.getAreaName(m.getProvince(), m.getCity(), m.getRegion());
		m.setBankArea(addressname);
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(EditBankBoundModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(EditBankBoundModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 修改商户银行卡绑定信息<br/>
	 * @param m
	 */
	public void updateBankBound(EditBankBoundModel m) {
		if(m != null && !StringUtil.isEmpty(m.getUuid())){
			EditBankBoundModel sebbm = myDao.getByUuid(m.getUuid());
			if(sebbm != null){
				sebbm.setOpenAccountName(m.getOpenAccountName());
				sebbm.setCompanyAccountNo(m.getCompanyAccountNo());
				sebbm.setBankNo(m.getBankNo());
				sebbm.setOpenAccountBankName(m.getOpenAccountBankName());
				sebbm.setMobile(m.getMobile());
				sebbm.setBankArea(m.getBankArea());
				sebbm.setApplyTime(DateFormatHelper.getNowTimeStr());
				sebbm.setVilidateTime("");
				if(!StringUtil.isEmpty(m.getMobile())){
					sebbm.setDescription(m.getDescription());
				}else{
					sebbm.setDescription("");
				}
				sebbm.setProvince(m.getProvince());
				sebbm.setCity(m.getCity());
				sebbm.setRegion(m.getRegion());
				
				//分别获取省市县名称存到库里
				String addressname = areasInteractiveService.getAreaName(m.getProvince(), m.getCity(), m.getRegion());
				sebbm.setBankArea(addressname);
				
				myDao.update(sebbm);
			}
		}
	}

	/**
	 * 通过商户的id查到一个商户银行卡绑定对象
	 * @param storeUuid	商户id
	 * @return	商户银行卡绑定对象
	 */
	public EditBankBoundModel getStoreEditBankBoundByStoreUuid(String storeUuid) {
		return myDao.getStoreEditBankBoundByStoreUuid(storeUuid);
	}

	/**
	 * 商户更新验证金额状态<br/>
	 * 如果验证成功，更新状态为验证成功<br/>
	 * 如果验证失败，更新状态为待验证，并更改验证失败数次<br/>
	 * @param ebbm
	 * @param vilidateMount
	 */
	public boolean updataVilidateMount(EditBankBoundModel ebbm,String vilidateMount) {
		if(!StringUtil.isEmpty(vilidateMount) && ebbm != null){
			if(Float.parseFloat(vilidateMount) == ebbm.getVilidateMount()){
				//验证成功
				ebbm.setCardValidateState(EditBankBoundModel.CARD_VALIDATE_STATE_SUCCESS);
				ebbm.setVilidateTime(DateFormatHelper.getNowTimeStr());
				this.update(ebbm);
				return true;
			}else{
				//验证失败,验证状态为待验证
				ebbm.setCardValidateState(EditBankBoundModel.CARD_VALIDATE_STATE_WAIT);
				if(StringUtil.isEmpty(ebbm.getFailTimes())){
					ebbm.setFailTimes("1");
				}else{
					ebbm.setFailTimes((Integer.parseInt(ebbm.getFailTimes()) + 1) + "");
				}
				ebbm.setVilidateTime(DateFormatHelper.getNowTimeStr());
				this.update(ebbm);
				return false;
			}
		}
		return false;
	}
}