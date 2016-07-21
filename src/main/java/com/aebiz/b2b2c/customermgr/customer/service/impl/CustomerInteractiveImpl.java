package com.aebiz.b2b2c.customermgr.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.dao.CustomerDAO;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;

@Service
@Transactional
public class CustomerInteractiveImpl implements CustomerInteractive {
	@Autowired
	private static FileService fileService;

	private CustomerDAO myDao = null;

	@Autowired
	public void setMyDao(CustomerDAO dao) {
		this.myDao = dao;
	}

	/**
	 * 通过会员编号或者手机号或者用户名获取会员信息
	 * 
	 * @param param
	 * 
	 * @return
	 */
	@Override
	public CustomerModel getCustomerModelByCondition(String param) {
		return this.myDao.getCustomerModelByCondition(param);
	}

	/**
	 * 通过会员编号或者会员名称获取会员集合（编号和名称都是模糊查询）
	 */
	@Override
	public List<CustomerModel> getCustomerModelListByCondition(
			CustomerQueryModel qm, int start, int pageShow) {
		return this.myDao.getCustomerModelListByCondition(qm, start, pageShow);
	}

	/**
	 * 通过会员会员名称模糊查询会员数量
	 */
	@Override
	public int getCountByCondition(CustomerQueryModel qm) {
		return this.myDao.getCountByCondition(qm);
	}

	/**
	 * 通过会员名编号来获取用户电话号码
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	@Override
	public String getCustomerMobileByUuid(String customerUuid) {

		return this.myDao.getCustomerMobileByUuid(customerUuid);
	}

	/**
	 * 通过会员编号获取会员头像url地址
	 */
	@Override
	public String getCustomerInfoRemoteImageUrlByCustomerUuid(
			String customerUuid) {
		String image = myDao.getCustomerInfoImageByCustomerUuid(customerUuid);

		if (StringUtil.isEmpty(image)) {
			return "";
		}

		FileModel file = fileService.getOneFileModel(image);

		return file.getRemotePaths();
	}

	/**
	 * 通过用户名或者手机号或者邮箱查询该会员信息
	 */
	@Override
	public CustomerModel getCustomerModelByLoginNameOrMobileOrEmail(String param) {
		return myDao.getCustomerModelByLoginNameOrMobileOrEmail(param);
	}


}
