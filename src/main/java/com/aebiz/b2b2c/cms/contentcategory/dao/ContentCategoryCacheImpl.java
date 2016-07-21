package com.aebiz.b2b2c.cms.contentcategory.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.cms.common.CmsCacheConstant;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class ContentCategoryCacheImpl extends
		BaseMemcachedCache<ContentCategoryModel, ContentCategoryQueryModel>
		implements ContentCategoryDAO {

	@Resource(name = CmsCacheConstant.CMS_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ContentCategoryDAO contentCategoryDAO;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(contentCategoryDAO);
	}

	@Override
	public void update(ContentCategoryModel m) {
		contentCategoryDAO.update(m);
		this.mcc.delete(CmsCacheConstant.CMS_CONTENT_CATEGORY + m.getUuid());
	}

	public ContentCategoryCacheImpl() {
		super(CmsCacheConstant.CMS_CONTENT_CATEGORY);
	}

	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 */
	@SuppressWarnings("unchecked")
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid) {
		// 从缓存中取得uuid集合对象
		List<String> uuids = this
				.getSubContentCategoryUuidByParentUuid(parentUuid);

		return this.getContentCategoryListCache(uuids);
	}

	/**
	 * 根据平台父分类uuid查询子分类uuid 该功能用于分类树展开时查询子分类
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSubContentCategoryUuidByParentUuid(
			String parentUuid, String categoryType) {
		// 从缓存中取得uuid集合对象
		List<String> uuids = (List<String>) mcc
				.get(CmsCacheConstant.CMS_CONTENT_CATEGORY_UUIDS + parentUuid
						+ "cty" + categoryType);

		if (uuids == null || uuids.size() <= 0) {
			uuids = this.contentCategoryDAO
					.getSubContentCategoryUuidByParentUuid(parentUuid,
							categoryType);

			if (uuids != null && uuids.size() > 0) {
				mcc.set(CmsCacheConstant.CMS_CONTENT_CATEGORY_UUIDS
						+ parentUuid + "cty" + categoryType, uuids);
			}
		}
		return uuids;
	}

	/**
	 * 根据平台父分类uuid查询子分类uuid 该功能用于分类树展开时查询子分类
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSubContentCategoryUuidByParentUuid(String parentUuid) {
		// 从缓存中取得uuid集合对象
		List<String> uuids = (List<String>) mcc
				.get(CmsCacheConstant.CMS_CONTENT_CATEGORY_UUIDS + parentUuid);

		if (uuids == null || uuids.size() <= 0) {
			uuids = this.contentCategoryDAO
					.getSubContentCategoryUuidByParentUuid(parentUuid);

			if (uuids != null && uuids.size() > 0) {
				mcc.set(CmsCacheConstant.CMS_CONTENT_CATEGORY_UUIDS
						+ parentUuid, uuids);
			}
		}
		return uuids;
	}

	/**
	 * 该方法用于生成fullpath时使用，查询该fullpath是否已经存在
	 */
	public List<ContentCategoryModel> getContentCategoryByFullPath(
			String fullPath) {
		List<String> uuids = this.contentCategoryDAO
				.getContentCategoryUuidsByFullPath(fullPath);

		return this.getContentCategoryListCache(uuids);
	}

	public List<String> getContentCategoryUuidsByFullPath(String fullPath) {
		return this.contentCategoryDAO
				.getContentCategoryUuidsByFullPath(fullPath);
	}

	/**
	 * 根据分类uuid获取分类名称
	 */
	public String getNameByCategoryUuid(String uuid) {
		// 从缓存中取得对象
		ContentCategoryModel ccm = (ContentCategoryModel) mcc
				.get(CmsCacheConstant.CMS_CONTENT_CATEGORY + uuid);

		// 如果对象为空，则从数据库中查询
		if (ccm == null) {
			ccm = (ContentCategoryModel) contentCategoryDAO.getByUuid(uuid);

			// 从数据库中查询，如果存在，则放入到缓存
			if (ccm != null) {
				mcc.set(CmsCacheConstant.CMS_CONTENT_CATEGORY + uuid, ccm);
			}
		}

		// 如果为空，则返回空
		if (ccm == null) {
			return "";
		}

		// 返回名称
		return ccm.getCategoryName();
	}

	/**
	 * 根据集合uuids 查出 分类集合
	 */
	public List<ContentCategoryModel> getCategorysByUuids(List<String> uuids) {
		List<String> categoryUuids = this.contentCategoryDAO
				.getCategoryUuidsByUuids(uuids);

		return this.getContentCategoryListCache(categoryUuids);
	}

	public List<String> getCategoryUuidsByUuids(List<String> uuids) {
		return this.contentCategoryDAO.getCategoryUuidsByUuids(uuids);
	}

	/**
	 * 获取所有根分类集合
	 */
	public List<ContentCategoryModel> getAllRootCategorys() {
		List<String> categoryUuids = this.contentCategoryDAO
				.getAllRootCategoryUuids();

		return this.getContentCategoryListCache(categoryUuids);
	}

	public List<String> getAllRootCategoryUuids() {
		return this.contentCategoryDAO.getAllRootCategoryUuids();
	}

	protected List<ContentCategoryModel> getContentCategoryListCache(
			List<String> uuids) {
		List<ContentCategoryModel> listM = new ArrayList<ContentCategoryModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(CmsCacheConstant.CMS_CONTENT_CATEGORY
						+ uuid);
				if (obj != null) {
					ContentCategoryModel m = (ContentCategoryModel) obj;
					listM.add(m);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					ContentCategoryModel m = this.contentCategoryDAO
							.getByUuid(uuid);
					if (m != null) {
						this.mcc.set(CmsCacheConstant.CMS_CONTENT_CATEGORY
								+ uuid, m);
						listM.add(m);
					}
				}
			}
		}
		return listM;
	}

	/**
	 * 根据uuid获取父类uuid
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public String getParentUuidByUuid(String uuid) {
		return contentCategoryDAO.getParentUuidByUuid(uuid);
	}

	@Override
	public String create(ContentCategoryModel m) {
		String ret = this.contentCategoryDAO.create(m);
		this.updateUuidsCache(m);
		return ret;
	}

	@Override
	public void delete(ContentCategoryModel m) {
		super.delete(m);
		super.deleteCache(m);
		this.deleteUuidsCache(m);
	}

	protected void updateUuidsCache(ContentCategoryModel m) {
		// 从缓存中取得uuid集合对象
		List<String> uuids = this.contentCategoryDAO
				.getSubContentCategoryUuidByParentUuid(m.getUuid());
		// 刷新缓存数据
		if (m != null) {
			if (uuids != null && uuids.size() > 0) {
				mcc.set(CmsCacheConstant.CMS_CONTENT_CATEGORY_UUIDS
						+ m.getUuid(), uuids);
			} else {
				mcc.set(CmsCacheConstant.CMS_CONTENT_CATEGORY_UUIDS
						+ m.getParentUuid(), uuids);
			}
		}
	}

	protected void deleteUuidsCache(ContentCategoryModel m) {
		// 从缓存中取得uuid集合对象
		this.mcc.delete(CmsCacheConstant.CMS_CONTENT_CATEGORY_UUIDS
				+ m.getUuid());
	}

	@Override
	public void deletes(List<String> needDeleteUuids) {
		contentCategoryDAO.deletes(needDeleteUuids);
		for (String uuid : needDeleteUuids) {
			deleteCache(uuid);
		}
	}

	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	@Override
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid, String categoryType) {
		// 从缓存中取得uuid集合对象
		List<String> uuids = this.getSubContentCategoryUuidByParentUuid(
				parentUuid, categoryType);

		return this.getContentCategoryListCache(uuids);
	}

	/**
	 * 根据分类编号获取对象
	 * 
	 * @param categoryNo
	 * @return
	 */
	@Override
	public ContentCategoryModel getContentCategoryByCategoryNo(String categoryNo) {
		ContentCategoryModel m = null;
		Object obj = this.mcc.get(CmsCacheConstant.CMS_CONTENT_CATEGORY
				+ categoryNo);
		if (obj != null) {
			m = (ContentCategoryModel) obj;
		} else {

			m = contentCategoryDAO.getContentCategoryByCategoryNo(categoryNo);
			if (m != null) {
				this.mcc.set(
						CmsCacheConstant.CMS_CONTENT_CATEGORY + categoryNo, m);
			}
		}

		return m;
	}

}
