package com.hxq.mobile.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxq.mobile.manage.dao.AppPublishDao;
import com.hxq.mobile.util.repository.SimpleEntityDao;
import com.hxq.mobile.util.repository.MybatisSimpleEntityService;

@Service("com.hxq.mobile.manage.service.AppManageService")
public class AppManageService extends MybatisSimpleEntityService {

    protected SimpleEntityDao dao;
    @Autowired
    public void setDao(AppPublishDao dao) {
        this.dao = dao;
    }
	@Override
	protected SimpleEntityDao getDao() {
		return dao;
	}
	@Override
	protected String getCacheName() {return "m1";}
}
