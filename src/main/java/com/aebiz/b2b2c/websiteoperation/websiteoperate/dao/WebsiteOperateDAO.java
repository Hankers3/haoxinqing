package com.aebiz.b2b2c.websiteoperation.websiteoperate.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateQueryModel;

public interface WebsiteOperateDAO extends
		BaseDAO<WebsiteOperateModel, WebsiteOperateQueryModel> {

	/**
	 * 校验编号是否重复
	 * 
	 * @param resourceKey
	 *            编号
	 * @return
	 */
	public boolean checkKeyExist(String resourceKey);

	/**
	 * ****************************运营系统对外接口*************************************
	 * 
	 * 
	 * 通过运营参数key值获取value值
	 * 
	 * @return
	 */
	public String getValueByKey(String key);

	/**
	 * 通过运营参数key更新value值
	 */
	public void updateValueByKey(String key, String value);

	/**
	 * 通过模块名称获取参数列表
	 * 
	 * @param moduleName
	 *            模块名称
	 * @return
	 */
	public List<WebsiteOperateModel> getWebsiteOperateModelListByModuleName(
			String moduleName);

	/**
	 * 
	 * 通过运营参数的值，获得运营对象
	 * 
	 * @param resourceKey
	 * @return
	 */
	public WebsiteOperateModel getByResourceKey(String resourceKey);

}