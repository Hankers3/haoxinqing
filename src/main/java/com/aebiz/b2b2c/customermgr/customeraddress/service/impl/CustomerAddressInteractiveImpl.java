package com.aebiz.b2b2c.customermgr.customeraddress.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.customermgr.customeraddress.dao.CustomerAddressDAO;
import com.aebiz.b2b2c.customermgr.customeraddress.service.CustomerAddressInteractive;

@Service
@Transactional
public class CustomerAddressInteractiveImpl implements CustomerAddressInteractive {
	private CustomerAddressDAO myDao = null;

	@Autowired
	public void setMyDao(CustomerAddressDAO dao) {
		this.myDao = dao;
	}

}
