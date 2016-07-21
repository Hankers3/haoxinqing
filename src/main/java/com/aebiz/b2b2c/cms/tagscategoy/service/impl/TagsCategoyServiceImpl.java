package com.aebiz.b2b2c.cms.tagscategoy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.tagscategoy.dao.TagsCategoyDAO;
import com.aebiz.b2b2c.cms.tagscategoy.service.TagsCategoryService;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryModel;
import com.aebiz.b2b2c.cms.tagscategoy.vo.TagsCategoryQueryModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.service.TagsCategoyRelService;

@Service
@Transactional
public class TagsCategoyServiceImpl extends
		BaseServiceImpl<TagsCategoryModel, TagsCategoryQueryModel> implements
		TagsCategoryService {
	private TagsCategoyDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private TagsCategoyRelService tagsCategoryRelService;

	@Autowired
	public void setMyDao(TagsCategoyDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(TagsCategoryModel m) {
		m.setUuid(us.getNextUuid("TagsCategoy", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(TagsCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(TagsCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据标签分类uuid获取名称
	 */
	public String getTagsCategoryNameByUuid(String Uuid) {
		if (StringUtil.isEmpty(Uuid)) {
			return "";
		}
		TagsCategoryModel m = getByUuid(Uuid);
		if (m != null) {
			return m.getCategoryName();
		}
		return "";
	}

	/**
	 * 删除标签分类时,同时删除标签和标签分类的关系表数据
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		tagsCategoryRelService.deletesByCategoryUuid(needDeleteUuids);
		super.deletes(needDeleteUuids);
	}
}