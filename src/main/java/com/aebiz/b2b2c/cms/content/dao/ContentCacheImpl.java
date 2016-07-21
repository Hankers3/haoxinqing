package com.aebiz.b2b2c.cms.content.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.cms.common.CmsCacheConstant;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class ContentCacheImpl extends
		BaseMemcachedCache<ContentModel, ContentQueryModel> implements
		ContentDAO {

	@Resource(name = CmsCacheConstant.CMS_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ContentDAO contentDAO;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(contentDAO);
	}

	public ContentCacheImpl() {
		super(CmsCacheConstant.CMS_CONTENT);
	}

	@Override
	public String create(ContentModel m) {
		String ret = this.contentDAO.create(m);
		this.updateUuidsCache(m);
		return ret;
	}

	@Override
	public void update(ContentModel m) {
		contentDAO.update(m);
		this.mcc.delete(CmsCacheConstant.CMS_CONTENT + m.getUuid());
	}

	@Override
	public void delete(ContentModel m) {
		super.delete(m);
		super.deleteCache(m);
		this.deleteUuidsCache(m);
	}

	/**
	 * 根据分类UUID获取内容uuid集合
	 */
	@Override
	public List<String> getContentUuidsByCategoryUuid(String categoryUuid) {
		List<String> uuids = this.contentDAO
				.getContentUuidsByCategoryUuid(categoryUuid);
		return uuids;
	}

	protected void updateUuidsCache(ContentModel m) {
		// 从缓存中取得uuid集合对象
		List<String> uuids = this.contentDAO.getContentUuidsByCategoryUuid(m
				.getContentCategoryUuid());

		if (uuids != null && uuids.size() > 0) {
			mcc.set(CmsCacheConstant.CMS_CONTENT_UUIDS + m.getUuid(), uuids);
		}
	}

	protected void deleteUuidsCache(ContentModel m) {
		// 从缓存中取得uuid集合对象
		this.mcc.delete(CmsCacheConstant.CMS_CONTENT_UUIDS
				+ m.getContentCategoryUuid());
	}

	/**
	 * 批量修改状态
	 */
	@Override
	public void updates(List<String> needUpdateUuids, String state) {
		contentDAO.updates(needUpdateUuids, state);
		if (needUpdateUuids != null && needUpdateUuids.size() > 0) {
			for (String uuid : needUpdateUuids) {
				this.mcc.delete(CmsCacheConstant.CMS_CONTENT + uuid);

				ContentModel m = this.contentDAO.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(CmsCacheConstant.CMS_CONTENT + uuid, m);
				}
			}
		}
	}

	/**
	 * 获取分享的内容
	 */
	@Override
	public ContentModel getShareContent(String contentCategoryUuid) {
		ContentModel cm = this.contentDAO.getShareContent(contentCategoryUuid);
		String uuid = cm.getUuid();
		List<ContentModel> listM = new ArrayList<ContentModel>();
		Object obj = this.mcc.get(CmsCacheConstant.CMS_CONTENT + uuid);
		ContentModel m = null;
		if (obj != null) {
			m = (ContentModel) obj;
		} else {
			m = this.contentDAO.getByUuid(uuid);
			if (m != null) {
				this.mcc.set(CmsCacheConstant.CMS_CONTENT + uuid, m);
			}
		}
		return m;
	}

	/**
	 * 根据分类UUID获取内容集合
	 */
	@Override
	public List<ContentModel> getContentModelByCategoryUuid(
			String contentCategoryUuid) {
		List<String> uuids = this
				.getContentUuidsByCategoryUuid(contentCategoryUuid);
		List<ContentModel> listM = new ArrayList<ContentModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(CmsCacheConstant.CMS_CONTENT + uuid);
				if (obj != null) {
					ContentModel m = (ContentModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					ContentModel m = this.contentDAO.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(CmsCacheConstant.CMS_CONTENT + uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public List<ContentModel> getContentListByCategoryUuid(
			String contentCategoryUuid) {
		List<String> uuids = this
				.getContentUuidsByCategoryUuid(contentCategoryUuid);

		List<ContentModel> listM = new ArrayList<ContentModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(CmsCacheConstant.CMS_CONTENT + uuid);
				if (obj != null) {
					ContentModel m = (ContentModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					ContentModel m = this.contentDAO.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(CmsCacheConstant.CMS_CONTENT + uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	@Override
	public List<ContentModel> getCustomerTeachList(String categoryId) {
		List<String> uuids = this.getContentUuidsByCategoryUuid(categoryId);
		List<ContentModel> listM = new ArrayList<ContentModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(CmsCacheConstant.CMS_CONTENT + uuid);
				if (obj != null) {
					ContentModel m = (ContentModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					ContentModel m = this.contentDAO.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(CmsCacheConstant.CMS_CONTENT + uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	/**
	 * 查询
	 */
	@Override
	public List<ContentModel> getByContentCategoryUuid(
			String contentCategoryUuid, ContentQueryModel qm) {
		return this.contentDAO
				.getByContentCategoryUuid(contentCategoryUuid, qm);
	}

	@Override
	public List<ContentModel> getAllContentModelList() {
		return contentDAO.getAllContentModelList();
	}

}
