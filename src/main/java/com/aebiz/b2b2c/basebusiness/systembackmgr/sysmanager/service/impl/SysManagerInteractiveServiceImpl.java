package com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.dao.SysManagerDAO;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerInteractiveService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;

@Service
@Transactional
public class SysManagerInteractiveServiceImpl extends BaseServiceImpl<SysManagerModel, SysManagerQueryModel> implements SysManagerInteractiveService{
	
	private SysManagerDAO myDao;
	
	@Autowired
	public void setMyDao(SysManagerDAO myDao) {
		this.myDao = myDao;
		super.setDao(myDao);
	}



	public String getSysManagerNameByUuid(String uuid) {
		return myDao.getSysManagerNameByUuid(uuid);
	}

}
