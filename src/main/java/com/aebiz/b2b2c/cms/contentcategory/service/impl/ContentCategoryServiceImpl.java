package com.aebiz.b2b2c.cms.contentcategory.service.impl;

import java.util.ArrayList;
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
import com.aebiz.b2b2c.cms.contentcategory.dao.ContentCategoryDAO;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryQueryModel;

@Service
@Transactional
public class ContentCategoryServiceImpl extends
		BaseServiceImpl<ContentCategoryModel, ContentCategoryQueryModel>
		implements ContentCategoryService {
	// fullpath长度
	public static final int FULLPATH_LENGTH = 33;

	private ContentCategoryDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ContentCategoryDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ContentCategoryModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ContentCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ContentCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除分类包括其子所有分类
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		myDao.deletes(needDeleteUuids);
		for (int i = 0; i < needDeleteUuids.size(); i++) {
			String uuid = needDeleteUuids.get(i);
			List<String> subUuids = this
					.getSubContentCategoryUuidByParentUuid(uuid);
			if (subUuids == null || subUuids.size() == 0) {// 没有子分类
				return;
			}
			deletes(subUuids);
		}

	}

	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid) {
		return this.myDao.getSubContentCategoryByParentUuid(parentUuid);
	}

	/**
	 * 根据平台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSubContentCategoryByParentUuid(
			String parentUuid, String categoryType) {
		return this.myDao.getSubContentCategoryByParentUuid(parentUuid,
				categoryType);
	}

	/**
	 * 根据平台父分类uuid查询子分类uuid
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<String> getSubContentCategoryUuidByParentUuid(String parentUuid) {
		return this.myDao.getSubContentCategoryUuidByParentUuid(parentUuid);
	}

	/**
	 * 根据fullpath查询分类集合
	 * 
	 * @param fullPath
	 * @return
	 */
	public List<ContentCategoryModel> getContentCategoryPlatformModelByFullPath(
			String fullPath) {
		return this.myDao.getContentCategoryByFullPath(fullPath);
	}

	/**
	 * 生成fullpath
	 * 
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String getSheetNo(String parentId, int sheetIdNo) {
		if (sheetIdNo <= 0)
			sheetIdNo = 1;
		String sheetId = convertSheetIdNo(parentId, sheetIdNo);

		List list = this.myDao.getContentCategoryByFullPath(sheetId);
		if (list != null && list.size() > 0) {
			return getSheetNo(parentId, ++sheetIdNo);
		}

		return sheetId;
	}

	/**
	 * fullpath规则
	 * 
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String convertSheetIdNo(String parentId, int sheetIdNo) {

		String parentPex = "";
		int breakIndex = -1;
		if (!StringUtil.isEmpty(parentId)) {
			int length = FULLPATH_LENGTH / 3;
			for (int i = 0; i <= length - 1; i++) {
				int startIndex = i * 3;
				int endIndex = (i + 1) * 3;

				String value = parentId.substring(startIndex, endIndex);

				if (Integer.parseInt(value) == 0) {
					breakIndex = i - 1;

					break;
				}
			}
		}

		parentPex = parentId.substring(0, (breakIndex + 1) * 3);

		int zeronum = 3 - ((sheetIdNo + "").length());

		String currentId = "";

		for (int i = 0; i < zeronum; i++) {
			currentId = currentId + "0";
		}

		currentId = currentId + sheetIdNo;

		String returnStr = parentPex + currentId;
		// zeronum = FULLPATH_LENGTH - (breakIndex + 1) * 3;
		zeronum = FULLPATH_LENGTH - returnStr.length();

		for (int i = 0; i < zeronum; i++) {
			returnStr = returnStr + "0";
		}

		return returnStr;
	}

	/**
	 * 批量添加分类
	 * 
	 * @param list
	 */
	public void addBatch(List<ContentCategoryModel> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			ContentCategoryModel m = list.get(i);
			this.create(m);
		}
	}

	/**
	 * 根据分类uuid获取分类名称
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public String getNameByCategoryUuid(String uuid) {
		return myDao.getNameByCategoryUuid(uuid);
	}

	/**
	 * 获取分类路径全名称 例：通用分类>电子>手机
	 * 
	 * @param uuid
	 *            分类uuid
	 * @param parentName
	 * @return
	 */
	public String getAllNameByUuid(String uuid, String parentName) {
		ContentCategoryModel categoryModel = getByUuid(uuid);

		if (categoryModel != null) {

			if (StringUtil.isEmpty(categoryModel.getParentUuid())) {
				parentName = categoryModel.getCategoryName() + ">" + parentName;
				return parentName;
			} else {
				if (!StringUtil.isEmpty(parentName)) {
					parentName = categoryModel.getCategoryName() + ">"
							+ parentName;
				} else {
					parentName = categoryModel.getCategoryName();
				}

				return getAllNameByUuid(categoryModel.getParentUuid(),
						parentName);
			}
		}

		return "";
	}

	/**
	 * 根据uuid获取父类uuid
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public String getParentUuidByUuid(String uuid) {
		return myDao.getParentUuidByUuid(uuid);
	}

	/**
	 * 根据最后级分类uuid,获取各级所要选中的分类uuid list格式：['1','11','111','1111']
	 * 
	 * @param leafUuid
	 *            最后选择的分类uuid
	 * @return
	 */
	public void getSelectCategoryUuid(String leafUuid, List<String> list) {
		// 根据分类uuid获取父类uuid;
		String uuid = this.getParentUuidByUuid(leafUuid);
		if (!StringUtil.isEmpty(uuid)) {
			getSelectCategoryUuid(uuid, list);
		}
		if (!StringUtil.isEmpty(leafUuid)) {
			list.add(leafUuid);
		}

	}

	/**
	 * 取某个分类同级的分类集合
	 * 
	 * @param uuid
	 *            分类uuid
	 * @return
	 */
	public List<ContentCategoryModel> getSameLevelCategoryByUuid(String uuid) {
		if (StringUtil.isEmpty(uuid)) {
			return null;
		}
		String parentUuid = getParentUuidByUuid(uuid);
		return getSubContentCategoryByParentUuid(parentUuid);
	}

	/**
	 * 根据分类编号获取对象
	 * 
	 * @param categoryNo
	 * @return
	 */
	@Override
	public ContentCategoryModel getContentCategoryByCategoryNo(String categoryNo) {

		return myDao.getContentCategoryByCategoryNo(categoryNo);
	}

}