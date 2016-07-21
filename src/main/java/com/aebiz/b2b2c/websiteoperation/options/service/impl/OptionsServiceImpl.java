package com.aebiz.b2b2c.websiteoperation.options.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.websiteoperation.options.dao.OptionsDAO;
import com.aebiz.b2b2c.websiteoperation.options.service.OptionsService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsQueryModel;

@Service
@Transactional
public class OptionsServiceImpl extends
		BaseServiceImpl<OptionsModel, OptionsQueryModel> implements
		OptionsService {
	private OptionsDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(OptionsDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OptionsModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OptionsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OptionsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除某个试题的所有选项
	 * 
	 * 在编辑和保存试题的时候，需要删除所有的选项，再根据页面提交的数据进行保存
	 * 
	 * @param questionUuid
	 */
	public void removePreOptions(String questionUuid) {
		myDao.removePreOptions(questionUuid);
	}

	/**
	 * 通过试题的UUID查询试题的所有选项
	 * 
	 * @param questionUuid
	 */
	public List<OptionsModel> getOptionsByQuestionUuid(String questionUuid) {
		return myDao.getOptionsByQuestionUuid(questionUuid);
	}

	/**
	 * 根据试题编号删除选项
	 */
	@Override
	public void deleteByQuestionUuid(String questionUuid) {
		myDao.deleteByQuestionUuid(questionUuid);
	}
}