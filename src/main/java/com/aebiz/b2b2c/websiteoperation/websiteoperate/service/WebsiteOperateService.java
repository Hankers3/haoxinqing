package com.aebiz.b2b2c.websiteoperation.websiteoperate.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateQueryModel;

public interface WebsiteOperateService extends
		BaseService<WebsiteOperateModel, WebsiteOperateQueryModel> {
	/**
	 * 通过paramContent获取参数值展示数据
	 * 
	 * @param paramContent
	 * @return
	 */
	public List<DataTablesPageParam> getParamValueShowNameByParamContent(
			String paramContent);

	/**
	 * 校验编号是否重复
	 * 
	 * @param resourceKey
	 *            编号
	 * @return
	 */
	public boolean checkKeyExist(String resourceKey);

}
