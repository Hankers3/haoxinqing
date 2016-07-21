package com.hxq.mobile.support.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxq.mobile.entity.common.AppPublish;
import com.hxq.mobile.support.dao.AppVersionDao;
import com.hxq.mobile.support.service.AppVersionService;
import com.hxq.mobile.util.repository.MybatisSimpleEntityService;

/**
 * Created by alice on 2016/3/1 0001
 */
@Service("com.hxq.mobile.support.service.AppVersionService")
public class AppVersionServiceImp extends MybatisSimpleEntityService
	implements AppVersionService {

    @Override
    public AppPublish selectVersion(String type) {
		return dao.selectVersion(type);
    }

    @Override
    public List<AppPublish> selectLast(String type, String last) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("last", last);
        return dao.selectLast(map);
    }

	protected AppVersionDao dao;
    @Autowired
    public void setDao(AppVersionDao dao) {
        this.dao = dao;
    }
	@Override
	protected AppVersionDao getDao() {
		return dao;
	}
	@Override
	protected String getCacheName() {return "h1";}
}
