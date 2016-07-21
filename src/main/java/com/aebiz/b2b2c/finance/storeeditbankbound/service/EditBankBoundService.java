package com.aebiz.b2b2c.finance.storeeditbankbound.service;


import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundModel;
import com.aebiz.b2b2c.finance.storeeditbankbound.vo.EditBankBoundQueryModel;

/**
 * 商户更改银行卡绑定。<br/>
 * 1.如果该商户未绑定银行卡，或者验证状态为未验证状态，或者是验证成功状态，则进入银行卡绑定页面。<br/>
 * &nbsp1.1.进入修改银行卡绑定，查询出该商户的财务信息<br/>
 * &nbsp1.2.如果信息为空，则需要填写商户的财务信息<br/>
 * 2.如果该商户已经绑定银行卡，或者验证状态为待验证状态，或者是验证失败状态，则进入银行卡验证状态。<br/>
 * &nbsp1.1.商户进行银行卡验证成功更改状态为成功状态<br/>
 * &nbsp1.2.商户进行银行卡验证失败更改状态为待验证状态，记录验证失败的次数，如果验证次数大于等于3，则冻结该商户。<br/>
 * 
 * @author liuyongtao
 * 
 */
public interface EditBankBoundService extends BaseService<EditBankBoundModel,EditBankBoundQueryModel>{

	/**
	 * 修改商户银行卡绑定信息
	 * @param m
	 */
	public void updateBankBound(EditBankBoundModel m);
	
	/**
	 * 通过商户的id等到一个商户银行卡绑定对象
	 * @param storeUuid	商户id
	 * @return	商户银行卡绑定对象
	 */
	public EditBankBoundModel getStoreEditBankBoundByStoreUuid(String storeUuid);

	/**
	 * 商户更新验证金额状态<br/>
	 * 如果验证成功，更新状态为验证成功 返回true<br/>
	 * 如果验证失败，更新状态为待验证，并更改验证失败数次 返回false<br/>
	 * @param ebbm
	 * @param vilidateMount
	 */
	public boolean updataVilidateMount(EditBankBoundModel ebbm, String vilidateMount);
}
