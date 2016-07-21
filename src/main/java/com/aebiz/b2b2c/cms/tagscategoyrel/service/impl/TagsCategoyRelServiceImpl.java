package com.aebiz.b2b2c.cms.tagscategoyrel.service.impl;

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
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tagscategoy.service.TagsCategoryService;
import com.aebiz.b2b2c.cms.tagscategoyrel.dao.TagsCategoyRelDAO;
import com.aebiz.b2b2c.cms.tagscategoyrel.service.TagsCategoyRelService;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.vo.TagsCategoyRelQueryModel;

/**
 * 标签和标签分类的关系业务实现 主要是实现标签和标签分类的关系，一个标签分类可以关联多个标签，一个标签可以被多个标签分类关联
 * 标签可以用于前台用户评论、商品收藏标签等 使用标签分类，方便后台维护标签
 * 
 * @author huangpinpin
 * 
 */
@Service
@Transactional
public class TagsCategoyRelServiceImpl extends
		BaseServiceImpl<TagsCategoyRelModel, TagsCategoyRelQueryModel>
		implements TagsCategoyRelService {
	private TagsCategoyRelDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private TagsService tagsService;
	@Autowired
	private TagsCategoryService tagsCategoryService;

	@Autowired
	public void setMyDao(TagsCategoyRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(TagsCategoyRelModel m) {
		m.setUuid(us.getNextUuid("TCRel", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setPosition("0");
		m.setIntroduce("0");

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(TagsCategoyRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(TagsCategoyRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 批量关联标签到某个分类
	 * 
	 * @param needRelUuids
	 *            关联的标签uuid
	 * @param categoryId
	 *            关联分类的uuid
	 */
	public void relation(List<String> needRelUuids, String categoryUuid) {

		if (needRelUuids != null && needRelUuids.size() > 0) {
			for (int i = 0; i < needRelUuids.size(); i++) {
				// 由于选择后，list后面会追加个""数据
				if (StringUtil.isEmpty(needRelUuids.get(i))) {
					continue;
				}
				// 根据标签uuid获取标签
				TagsModel tags = tagsService.getByUuid(needRelUuids.get(i));

				TagsCategoyRelModel rel = new TagsCategoyRelModel();
				rel.setCategoryUuid(categoryUuid);
				rel.setTagUuid(tags.getUuid());
				this.create(rel);
			}

		}
	}

	/**
	 * 批量更新标签和分类关系表 主要更新位置
	 * 
	 * @param needRelUuids
	 */
	public void batchUpdate(List<String> selectRelUuids) {
		if (selectRelUuids != null && selectRelUuids.size() > 0) {
			for (int i = 0; i < selectRelUuids.size(); i++) {

				String tempStr = selectRelUuids.get(i);
				// 由于选择后，list后面会追加个""数据
				if (StringUtil.isEmpty(tempStr)) {
					continue;
				}

				// index=0表示uuid,index=1表示position值,index=2表示是否推荐
				String[] datas = tempStr.split("\\|");
				TagsCategoyRelModel m = this.getByUuid(datas[0]);
				m.setPosition(datas[1]);
				m.setIntroduce(datas[2]);

				this.update(m);
			}

		}

	}

	/**
	 * 根据标签分类uuid查找已关联的标签
	 * 
	 * @param categoryUuid
	 *            标签分类uuid
	 * @return
	 */
	public List<String> existTagesUuids(String categoryUuid) {
		return this.myDao.getRelByCategoryUuid(categoryUuid);
	}

	/**
	 * 根据标签分类uuid,删除标签和标签分类关系表数据
	 * 
	 * @param categoryUuids
	 *            标签uuid
	 * @return
	 */
	public void deletesByCategoryUuid(List<String> categoryUuids) {
		this.myDao.deletesByCategoryUuid(categoryUuids);
	}

	/**
	 * 根据标签uuid,删除标签和标签分类关系表数据
	 * 
	 * @param tagUuids
	 *            标签uuid
	 * @return
	 */
	public void deletesByStagUuid(List<String> stagUuids) {
		this.myDao.deletesBytagUuid(stagUuids);
	}
}