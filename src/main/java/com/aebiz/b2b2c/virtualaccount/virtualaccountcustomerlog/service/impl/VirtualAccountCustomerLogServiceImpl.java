package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.dao.VirtualAccountCustomerLogDAO;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.service.VirtualAccountCustomerLogService;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogQueryModel;

@Service
@Transactional
public class VirtualAccountCustomerLogServiceImpl
		extends
		BaseServiceImpl<VirtualAccountCustomerLogModel, VirtualAccountCustomerLogQueryModel>
		implements VirtualAccountCustomerLogService {
	private VirtualAccountCustomerLogDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(VirtualAccountCustomerLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VirtualAccountCustomerLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(VirtualAccountCustomerLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(VirtualAccountCustomerLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 查询会员收支纪录的总数<br>
	 * 
	 * @param customerUuid
	 * @param operType
	 *            为空时,查全部 0:查收入的 1:查支出的
	 * @return int
	 */
	public int getCustomerAccountLogCount(String customerUuid, String operType) {
		return myDao.getCustomerAccountLogCount(customerUuid, operType);
	}

	/**
	 * 根据订单id获取对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public VirtualAccountCustomerLogModel getVirtualAccountCustomerLogModelByOrderUuid(
			String orderUuid) {
		return myDao.getVirtualAccountCustomerLogModelByOrderUuid(orderUuid);
	}

	/**
	 * 根据消费记录的Uuid查询患者的uuid
	 */
	@Override
	public String getCustomerUuidByLogsUuid(String uuid) {
		return myDao.getCustomerUuidByLogsUuid(uuid);
	}
	/**
         * 根据患者的id获取患者的消费记录日志列表
         * @param customerUuid
         * @return
         */
        @Override
        public List<VirtualAccountCustomerLogModel> getVirtualAccountCustomerLogListByCustomerUuid(
                String customerUuid) {
            return myDao.getVirtualAccountCustomerLogListByCustomerUuid(customerUuid);
        }

}