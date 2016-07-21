package com.aebiz.b2b2c.websiteoperation.options.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsQueryModel;

public interface OptionsService extends
		BaseService<OptionsModel, OptionsQueryModel> {

	/**
	 * 删除某个试题的所有选项
	 * 
	 * 在编辑和保存试题的时候，需要删除所有的选项，再根据页面提交的数据进行保存
	 * 
	 * @param questionUuid
	 */
	public void removePreOptions(String questionUuid);

	/**
	 * 
	 * 通过试题的UUID查询试题的所有选项
	 * 
	 * @param questionUuid
	 */
	public List<OptionsModel> getOptionsByQuestionUuid(String questionUuid);

	/**
	 * 根据试题编号删除选项
	 * 
	 * @param questionUuid
	 *            试题uuid
	 */
	public void deleteByQuestionUuid(String questionUuid);
}
