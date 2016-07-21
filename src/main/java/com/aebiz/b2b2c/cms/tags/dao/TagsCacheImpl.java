package com.aebiz.b2b2c.cms.tags.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.cms.common.CmsCacheConstant;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tags.vo.TagsQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class TagsCacheImpl extends
		BaseMemcachedCache<TagsModel, TagsQueryModel> implements TagsDAO {

	@Resource(name = CmsCacheConstant.CMS_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private TagsDAO tagsDAO;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(tagsDAO);
	}

	@Override
	public void update(TagsModel m) {
		tagsDAO.update(m);
		this.mcc.delete(CmsCacheConstant.CMS_TAGS + m.getUuid());
	}

	public TagsCacheImpl() {
		super(CmsCacheConstant.CMS_TAGS);
	}

	/**
	 * 通过标签分类ID获取该分类下关联的标签
	 */
	public List<TagsModel> getTagsByCategoryUuid(List<String> categoryUuid) {
		List<String> uuids = tagsDAO.getTagsUuidsByCategoryUuid(categoryUuid);

		List<TagsModel> listM = new ArrayList<TagsModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(CmsCacheConstant.CMS_TAGS + uuid);
				if (obj != null) {
					TagsModel m = (TagsModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					TagsModel m = tagsDAO.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(CmsCacheConstant.CMS_TAGS + uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public List<String> getTagsUuidsByCategoryUuid(List<String> categoryUuid) {
		return tagsDAO.getTagsUuidsByCategoryUuid(categoryUuid);
	}

	@Override
	public List<TagsModel> getTagsByCategoryUuid(String categoryUuid) {
		List list = new ArrayList();
		list.add(categoryUuid);
		List<String> uuids = tagsDAO.getTagsUuidsByCategoryUuid(list);

		List<TagsModel> listM = new ArrayList<TagsModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(CmsCacheConstant.CMS_TAGS + uuid);
				if (obj != null) {
					TagsModel m = (TagsModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					TagsModel m = tagsDAO.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(CmsCacheConstant.CMS_TAGS + uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public String getTagNameByUuid(String uuid) {
		Object obj = this.mcc.get(CmsCacheConstant.CMS_TAGS + uuid);
		String tagName="";
		TagsModel tags = null;
		if(obj !=null){
			tags = (TagsModel) obj;
			return tags.getTagName();
		}else{
			TagsModel m = tagsDAO.getByUuid(uuid);
			if (m != null) {
				tagName = m.getTagName();
				this.mcc.set(CmsCacheConstant.CMS_TAGS + uuid, m);
			}
		}
		return tagName;
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

		return tagsDAO.getTagsByDoctorTag(doctorTag);
	}
    @Override
    public List<TagsModel> getTagListByConsultTag(String consultTag) {
        return tagsDAO.getTagListByConsultTag(consultTag);
    }
    
}
