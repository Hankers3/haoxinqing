package com.aebiz.b2b2c.websiteoperation.websiteoperate.service;

import java.util.List;

import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;

/**
 * 运营系统对外接口
 * 
 * @author likun
 * 
 */
public interface WebsiteOperateInteractive {
	/**
	 * 通过运营参数key值获取value值
	 * 
	 * @param key
	 *            参数key值
	 * @return
	 */
	public String getValueByKey(String key);

	/**
	 * 通过运营参数key更新value值
	 * 
	 * @param key
	 *            参数key值
	 * @param value
	 *            参数value值
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

}
