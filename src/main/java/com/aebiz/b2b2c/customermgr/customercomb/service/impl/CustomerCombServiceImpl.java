package com.aebiz.b2b2c.customermgr.customercomb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customercomb.dao.CustomerCombDao;
import com.aebiz.b2b2c.customermgr.customercomb.service.CustomerCombService;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombQueryModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.service.CustomerFrozenLogService;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customersource.service.CustomerSourceService;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.finance.customeraccount.vo.CustomerAccountModel;

@Service
@Transactional
public class CustomerCombServiceImpl extends
		BaseServiceImpl<CustomerCombModel, CustomerCombQueryModel> implements
		CustomerCombService {
	private CustomerCombDao myDao = null;
	@Autowired
	private UuidService us;
	/* 会员信息service */
	@Autowired
	private CustomerService customerService;

	/* 会员基础信息service */
	@Autowired
	private CustomerInfoService customerInfoService;


	/* 会员来源信息service */
	@Autowired
	private CustomerSourceService customerSourceService;

	/* 会员账户信息service */
	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;

	/* 会员冻结日志信息service */   
	@Autowired
	private CustomerFrozenLogService customerFrozenLogService;

	public void setMyDao(CustomerCombDao myDao) {
		this.myDao = myDao;
		super.setDao(myDao);
	}

	/**
	 * 添加会员
	 */
	@Override
	public void createCustomer(CustomerCombModel m) {
		// 添加会员账户信息
		customerService.create(m.getCustomerModel());

		// 将会员基础信息和会员账户信息关联
		this.createCustomerInfo(m.getCustomerModel());

		// 将会员实名认证信息和会员账户信息关联
		//this.createCustomerAuth(m.getCustomerModel());

		// 将会员来源信息和会员账户信息关联
		this.createCustomerSource(m.getCustomerModel());

		this.createCustomerAccount(m.getCustomerModel());

	}

	/**
	 * 添加会员账户信息
	 * 
	 * @param customerModel
	 */
	private void createCustomerAccount(CustomerModel customerModel) {
		CustomerAccountModel customerAccountModel = new CustomerAccountModel();
		customerAccountModel.setCustomerUuid(customerModel.getUuid());

		customerAccountInteractive.create(customerAccountModel);
	}

	/**
	 * 将会员基础信息和会员账户信息关联
	 * 
	 * 
	 * @param customerModel
	 */
	private void createCustomerInfo(CustomerModel customerModel) {
		String customerUuid = customerModel.getUuid();
		CustomerInfoModel customerInfoModel = new CustomerInfoModel();
		customerInfoModel.setCustomerUuid(customerUuid);

		customerInfoService.create(customerInfoModel);
	}

	/**
	 * 将会员实名认证信息和会员账户信息关联
	 * 
	 * @param customerModel
	 */
	private void createCustomerAuth(CustomerModel customerModel) {
		String customerUuid = customerModel.getUuid();
		CustomerAuthModel customerAuthModel = new CustomerAuthModel();
		customerAuthModel.setCustomerUuid(customerUuid);

		//customerAuthService.create(customerAuthModel);
	}

	/**
	 * 将会员来源信息和会员账户信息关联
	 * 
	 * @param customerModel
	 */
	private void createCustomerSource(CustomerModel customerModel) {
		String customerUuid = customerModel.getUuid();
		CustomerSourceModel customerSourceModel = new CustomerSourceModel();
		customerSourceModel.setCustomerUuid(customerUuid);

		customerSourceService.create(customerSourceModel);
	}

	/**
	 * 通过会员编号得到会员账户信息和基础信息封装到复合model中
	 */
	@Override
	public CustomerCombModel getCustomerCombModelByCustomerUuid(String uuid) {
		CustomerCombModel customerCombModel = new CustomerCombModel();
		// 得到会员账户信息
		CustomerModel customerModel = customerService.getByUuid(uuid);
		customerCombModel.setCustomerModel(customerModel);

		// 得到会员基础信息
		CustomerInfoModel customerInfoModel = customerInfoService
				.getCustomerInfoModelByCustomerUuid(uuid);
		customerCombModel.setCustomerInfoModel(customerInfoModel);

		// 得到会员最新冻结或者解冻日志信息
		CustomerFrozenLogModel customerFrozenLogModel;
		if (customerModel.getFrozenState().equals(
				CustomerFrozenLogModel.FROZEN_OPER_FREZON)) {
			customerFrozenLogModel = customerFrozenLogService
					.getFrozenLog(uuid);
		} else {
			customerFrozenLogModel = customerFrozenLogService
					.getUnFrozenLog(uuid);
		}
		customerCombModel.setCustomerFrozenLogModel(customerFrozenLogModel);

		return customerCombModel;
	}

	/**
	 * 根据会员编号获取会员账户信息，基础信息，实名认证信息，来源信息
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public CustomerCombModel getAllCustomerCombModelByCustomerUuid(String uuid) {
		CustomerCombModel customerCombModel = new CustomerCombModel();
		// 得到会员账户信息
		CustomerModel customerModel = customerService.getByUuid(uuid);
		customerCombModel.setCustomerModel(customerModel);

		// 得到会员基础信息
		CustomerInfoModel customerInfoModel = customerInfoService
				.getCustomerInfoModelByCustomerUuid(uuid);
		customerCombModel.setCustomerInfoModel(customerInfoModel);

		// 得到会员实名认证信息
		//CustomerAuthModel customerAuthModel = customerAuthService
				//.getCustomerAuthModelByCustomerUuid(uuid);
		//customerCombModel.setCustomerAuthModel(customerAuthModel);

		// 得到会员来源信息
		CustomerSourceModel customerSourceModel = customerSourceService
				.getCustomerSourceModelByCustomerUuid(uuid);
		customerCombModel.setCustomerSourceModel(customerSourceModel);

		// 得到会员最新冻结或者解冻日志信息
		CustomerFrozenLogModel customerFrozenLogModel;
		if (customerModel.getFrozenState().equals(
				CustomerFrozenLogModel.FROZEN_OPER_FREZON)) {
			customerFrozenLogModel = customerFrozenLogService
					.getFrozenLog(uuid);
		} else {
			customerFrozenLogModel = customerFrozenLogService
					.getUnFrozenLog(uuid);
		}
		customerCombModel.setCustomerFrozenLogModel(customerFrozenLogModel);

		return customerCombModel;

	}

	/**
	 * 更新会员账户信息
	 */
	@Override
	public void updateCustomerAccountInfo(CustomerModel customerModel) {
		customerService.update(customerModel);
	}

	/**
	 * 更新会员基础信息
	 */
	@Override
	public void updateCustomerBaseInfo(CustomerCombModel customerCombModel,
			MultipartFile[] imgFiles) {
		customerInfoService.updateCustomerInfo(customerCombModel, imgFiles);
	}

	/**
	 * 会员更新页面添加会员冻结或解冻日志
	 */
	@Override
	public void updateCustomerFrozenLog(CustomerCombModel customerCombModel) {
		String note = customerCombModel.getCustomerFrozenLogModel().getNote();
		String frozenState = customerCombModel.getCustomerModel()
				.getFrozenState();
		String oper = LoginUserHelper.getLoginUserUuid();
		String frozenType = "";
		String customerUuid = customerCombModel.getCustomerModel().getUuid();

		if (frozenState.equals(CustomerFrozenLogModel.FROZEN_OPER_FREZON)) {
			frozenType = customerCombModel.getCustomerModel().getFrozenType();
			customerFrozenLogService.addFrozenLog(customerUuid, frozenType,
					note, oper);
		} else {
			customerFrozenLogService.addUnFrozenLog(customerUuid, note, oper);
		}
	}
	

}
