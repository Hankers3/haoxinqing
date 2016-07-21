package com.aebiz.b2b2c.visitprecept.customerdoctorrele.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.dao.CustomerDoctorReleDAO;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.service.CustomerDoctorReleService;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleModel;
import com.aebiz.b2b2c.visitprecept.customerdoctorrele.vo.CustomerDoctorReleQueryModel;

@Service
@Transactional
public class CustomerDoctorReleServiceImpl extends
		BaseServiceImpl<CustomerDoctorReleModel, CustomerDoctorReleQueryModel>
		implements CustomerDoctorReleService {
	private CustomerDoctorReleDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerDoctorReleDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerDoctorReleModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerDoctorReleModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerDoctorReleModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据医生编号获取患者数量
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getCustomerNumByDoctorUuid(String doctorUuid) {
		return myDao.getCustomerNumByDoctorUuid(doctorUuid);

	}

	/**
	 * 根据患者编号获取医生数量
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getDoctorNumByCustomerUuid(String customerUuid) {
		return myDao.getDoctorNumByCustomerUuid(customerUuid);

	}

	/**
	 * 根据医生编号获取患者信息
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<CustomerModel> getcostomerListByDoctorUuid(String doctorUuid) {
		return myDao.getcostomerListByDoctorUuid(doctorUuid);
	}

	/**
	 * 根据医生编号获取医患关系信息
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<CustomerDoctorReleModel> getByDoctorUuid(String doctorUuid) {
		return myDao.getByDoctorUuid(doctorUuid);
	}

	/**
	 * 根据医生编号及分组id获取医患关系信息
	 * 
	 * @param doctorUuid
	 * @param groupId
	 * @return
	 */
	@Override
	public List<CustomerDoctorReleModel> getByDoctorUuidAndGroupId(
			String doctorUuid, String groupId) {
		return myDao.getByDoctorUuidAndGroupId(doctorUuid, groupId);
	}

	/**
	 * 根据患者的id获取医生的Uuid
	 * 
	 * @param customerid
	 * @return
	 */
	//@Override
	//public String getDoctorUuidByCustomerUuid(String customerid) {
		//return myDao.getDoctorUuidByCustomerUuid(customerid);
	//}

	@Override
	public void deleteByCustomerIdAndGroupId(String customerId, String groupId) {
		myDao.deleteByCustomerIdAndGroupId(customerId, groupId);
	}

	/**
	 * 判断患者分组下是否有患者
	 * 
	 * @return
	 */
	@Override
	public List getByGroupUuid(String gid) {
		return myDao.getByGroupUuid(gid);
	}

	/**
	 * 通过医生和患者id找到model
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public CustomerDoctorReleModel getByCustomerUuidAndDoctorUuid(
			String customerUuid, String groupUuid) {
		return myDao.getByCustomerUuidAndDoctorUuid(customerUuid, groupUuid);
	}

}