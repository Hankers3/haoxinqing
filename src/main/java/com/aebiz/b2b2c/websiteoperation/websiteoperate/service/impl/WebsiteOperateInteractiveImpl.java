package com.aebiz.b2b2c.websiteoperation.websiteoperate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.websiteoperation.websiteoperate.dao.WebsiteOperateDAO;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;

@Service
@Transactional
public class WebsiteOperateInteractiveImpl implements WebsiteOperateInteractive {
	private WebsiteOperateDAO myDao = null;

	@Autowired
	public void setMyDao(WebsiteOperateDAO dao) {
		this.myDao = dao;
	}

	/**
	 * 通过运营参数key值获取value值
	 */
	@Override
	public String getValueByKey(String key) {
		return myDao.getValueByKey(key);
	}

	/**
	 * 通过运营参数key更新value值
	 */
	@Override
	public void updateValueByKey(String key, String value) {
		myDao.updateValueByKey(key, value);
	}

	/**
	 * 通过模块名称获取参数列表
	 */
	@Override
	public List<WebsiteOperateModel> getWebsiteOperateModelListByModuleName(
			String moduleName) {
		return myDao.getWebsiteOperateModelListByModuleName(moduleName);
	}
}
