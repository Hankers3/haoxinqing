package com.aebiz.b2b2c.cms.tags.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.tags.dao.TagsDAO;
import com.aebiz.b2b2c.cms.tags.service.TagsService;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tags.vo.TagsQueryModel;
import com.aebiz.b2b2c.cms.tagscategoyrel.service.TagsCategoyRelService;

@Service
@Transactional
public class TagsServiceImpl extends BaseServiceImpl<TagsModel, TagsQueryModel>
		implements TagsService {
	private TagsDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private TagsCategoyRelService tagsCategoryRelService;

	@Autowired
	public void setMyDao(TagsDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(TagsModel m) {
		m.setUuid(us.getNextUuid("Tags", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(TagsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(TagsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除标签时,同时删除标签和标签分类的关系表数据
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		tagsCategoryRelService.deletesByStagUuid(needDeleteUuids);
		super.deletes(needDeleteUuids);
	}
	
	/**
	 * 
	 * 通过标签分类ID获取该分类下关联的标签
	 * 
	 * @param categoryUuid
	 * @return
	 */
	public List<TagsModel> getTagsByCategoryUuid(String categoryUuid){
		return this.myDao.getTagsByCategoryUuid(categoryUuid);
	}
	
	/**
	 * 通过编号获取标签名
	 * @param uuid
	 * @return
	 */
	public String  getTagNameByUuid(String uuid){
		return myDao.getTagNameByUuid(uuid);
	}
	
	/**
	 * 
	 * 通过医生已有标签 获取未关联的标签
	 * 
	 * @param categoryUuid
	 * @return
	 */
	@Override
	public List<TagsModel> getTagsByDoctorTag(String doctorTag) {
		
		return myDao.getTagsByDoctorTag(doctorTag);
	}
	/**
	 * 
	 * @Description: (根据标签id获取标签对象列表)    
	 * @author XP  
	 * @param consultTag
	 * @return
	 * @date 2016-1-21 下午2:53:08
	 */
        @Override
        public List<TagsModel> getTagListByConsultTag(String consultTag) {
            return myDao.getTagListByConsultTag(consultTag);
        }
}