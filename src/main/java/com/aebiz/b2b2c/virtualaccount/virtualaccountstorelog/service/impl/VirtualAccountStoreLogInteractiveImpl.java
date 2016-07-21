package com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.dao.VirtualAccountStoreLogDAO;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.service.VirtualAccountStoreLogInteractive;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountstorelog.vo.VirtualAccountStoreLogQueryModel;

@Service
@Transactional
public class VirtualAccountStoreLogInteractiveImpl
		extends
		BaseServiceImpl<VirtualAccountStoreLogModel, VirtualAccountStoreLogQueryModel>
		implements VirtualAccountStoreLogInteractive {
	private VirtualAccountStoreLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(VirtualAccountStoreLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VirtualAccountStoreLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(VirtualAccountStoreLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(VirtualAccountStoreLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 商户的虚拟账户日志
	 * 
	 * @param storeUuid
	 *            商户uuid
	 * @param operType
	 *            收支类型
	 * @param operAmount
	 *            操作金额
	 * @param nowBalance
	 *            当前余额
	 * @param documentNo
	 *            单据编号
	 * @param description
	 *            备注
	 * @param frozenState
	 *            冻结状态
	 * @param rechargeType
	 *            类型
	 */
	@Override
	public void saveVirtualAccountStoreLog(String storeUuid, String operType,
			double operAmount, double nowBalance, String documentNo,
			String description, String frozenState, String rechargeType) {
		VirtualAccountStoreLogModel vs = new VirtualAccountStoreLogModel();
		// 商户uuid
		vs.setStoreUuid(storeUuid);
		// 收支类型
		vs.setOperType(operType);
		// 操作金额
		vs.setOperAmount(operAmount);
		// 当前余额
		vs.setNowBalance(nowBalance);
		// 单据编号
		vs.setDocumentNo(documentNo);
		// 备注
		vs.setDescription(description);
		// 冻结状态
		vs.setFrozenState(frozenState);
		// 类型
		vs.setRechargeType(rechargeType);

		this.create(vs);
	}
}