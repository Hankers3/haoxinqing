package com.aebiz.b2b2c.cms.contentcategory.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.contentcategory.dao.ContentCategoryDAO;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryInteractive;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;

@Service
@Transactional
public class ContentCategoryInteractiveImpl extends
		BaseServiceImpl<ContentCategoryModel, ContentCategoryQueryModel>
		implements ContentCategoryInteractive {
	private ContentCategoryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private ContentService contentService;

	@Autowired
	public void setMyDao(ContentCategoryDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 获取所有的根分类
	 * 
	 * @return
	 */
	public List<ContentCategoryModel> getAllRootCategory() {
		return myDao.getAllRootCategorys();
	}

	@Override
	public List<Map.Entry<ContentCategoryModel, List<ContentModel>>> getContentCategoryByUuid(
			String uuid) {
		List<ContentCategoryModel> list = this.myDao
				.getSubContentCategoryByParentUuid(uuid,"");

		Map<ContentCategoryModel, List<ContentModel>> map = new HashMap<ContentCategoryModel, List<ContentModel>>();
		if (list != null && list.size() > 0) {
			for (ContentCategoryModel cc : list) {
//				List<ContentModel> contentList = this.contentService
//						.getContentsByCategoryUuid(cc.getUuid());
//				map.put(cc, contentList);
			}
		}

		List<Map.Entry<ContentCategoryModel, List<ContentModel>>> mlist = new ArrayList<Map.Entry<ContentCategoryModel, List<ContentModel>>>(
				map.entrySet());

		Collections
				.sort(mlist,
						new Comparator<Map.Entry<ContentCategoryModel, List<ContentModel>>>() {
							public int compare(
									Map.Entry<ContentCategoryModel, List<ContentModel>> o1,
									Map.Entry<ContentCategoryModel, List<ContentModel>> o2) {
								if (o1.getKey().getPosition() > o2.getKey()
										.getPosition())
									return 1;
								if (o1.getKey().getPosition() == o2.getKey()
										.getPosition())
									return 0;
								else
									return -1;
							}

						});

		return mlist;
	}

	@Override
	public Map<ContentCategoryModel, List<ContentModel>> getHelpContents(
			String uuid) {
		Map<ContentCategoryModel, List<ContentModel>> map = new HashMap<ContentCategoryModel, List<ContentModel>>();
		ContentCategoryModel cc = this.myDao.getByUuid(uuid);
		if (cc != null) {
			/*List<ContentModel> contentList = this.contentService
					.getContentsByCategoryUuid(cc.getUuid(),"");
				map.put(cc, contentList);*/
		}
		return map;
	}

}