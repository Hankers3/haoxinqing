package com.aebiz.b2b2c.cms.content.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.dao.ContentDAO;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentQueryModel;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;

@Service
@Transactional
public class ContentServiceImpl extends
		BaseServiceImpl<ContentModel, ContentQueryModel> implements
		ContentService {
	private ContentDAO myDao = null;

	// 编号类型订单
	public static final String NOTYPE_ORDER = "content";

	@Autowired
	private AccountKeyService accountKeyService;

	@Autowired
	private FileUploadHelper fileUpload = null;
	@Autowired
	private UuidService us;
	@Autowired
	private ContentCategoryService categoryService;

	@Autowired
	public void setMyDao(ContentDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ContentModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String contentNo = this.createContentNo();
        m.setContentNo(contentNo);
		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ContentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ContentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void updates(List<String> needUpdateUuids, String state) {
		myDao.updates(needUpdateUuids, state);

	}

	/**
	 * 获取会员分享内容的数据
	 * 
	 * @return
	 */
	@Override
	public ContentModel getShareContent(String contentcategoryNo) {
		ContentCategoryModel contentCategoryModel = categoryService
				.getContentCategoryByCategoryNo(contentcategoryNo);
		if (contentCategoryModel != null) {
			return myDao.getShareContent(contentCategoryModel.getUuid());
		}
		return null;
	}

	/**
	 * 获取App内容
	 * 
	 * @return
	 */
	@Override
	public List<ContentModel> getAppContentModels(String contentcategoryNo) {
		// APPMORE
		ContentCategoryModel contentCategoryModel = categoryService
				.getContentCategoryByCategoryNo(contentcategoryNo);
		List<ContentModel> contentModels = null;
		if (contentCategoryModel != null) {
			String uuid = contentCategoryModel.getUuid();
			if (uuid != null) {
				contentModels = myDao.getContentModelByCategoryUuid(uuid);
			}
		}

		return contentModels;

	}

	/**
	 * 获取App内容
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, String>> getAppContent(String contentcategoryNo) {
		// 获取所有的APP内容的model
		List<ContentModel> contentModels = getAppContentModels(contentcategoryNo);
		List<Map<String, String>> contents = new ArrayList<Map<String, String>>();
		if (contentModels != null && contentModels.size() > 0) {
			// 将APP端需要的每个model的三个参数放入list中
			for (ContentModel contentModel : contentModels) {
				Map<String, String> s = new HashMap<String, String>();
				// uuid
				if (contentModel.getUuid() == null) {
					s.put("contentUuid", "");
				} else {
					s.put("contentUuid", contentModel.getUuid());
				}
				// ContentTitle
				if (contentModel.getContentTitle() == null) {
					s.put("contentTitle", "");
				} else {
					s.put("contentTitle", contentModel.getContentTitle());
				}
				// ContentNote
				if (contentModel.getContentNote() == null) {
					s.put("contentNote", "");
				} else {
					s.put("contentNote", contentModel.getContentNote());
				}
				// 把三个参数放入contents中
				contents.add(s);
			}
		}
		return contents;
	}

	/**
	 * 得到文件后缀
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String getFileSuffix(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return "";
		}
		return fileName.substring(fileName.indexOf("."), fileName.length());
	}

	/**
	 * 上传图片文件
	 */
	@Override
	public ContentModel uploadImage(ContentModel contentModel,
			MultipartFile[] files) {

		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "shopLevelIcon"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date());

					// 如果文件大小不为0
					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUpload.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					contentModel.setImage(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				contentModel.setImage("");
			}
		} catch (Exception ex) {
			contentModel.setImage("");
		}
		if (fileList != null && fileList.size() > 0) {
			MultipartFile[] newFiles = new MultipartFile[fileList.size()];
			String[] newFileNames = new String[fileList.size()];
			for (int i = 0; i < fileList.size(); i++) {
				newFileNames[i] = fileNameList.get(i);
				newFiles[i] = fileList.get(i);
			}
			fileUpload.uploadFiles(newFiles, newFileNames);
		}
		return contentModel;
	}

	/**
	 * 创建医生流水号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createContentNo() {
		String returnNo = "";
		synchronized (NOTYPE_ORDER) {
			// 日期
			String dateStr = DateFormatHelper.getNowTimeStr().substring(0, 10);
			String date = dateStr.replace("-", "");
			int maxcount = getMaxCount();

			if (maxcount == 1000000)
				maxcount = 1;

			// 保存订单流水号
			AccountKeyModel accountKey = accountKeyService
					.getAccountKeyModelById(NOTYPE_ORDER);

			if (accountKey == null) {
				accountKey = new AccountKeyModel();
				accountKey.setId(NOTYPE_ORDER);
				accountKey.setValue("1");
				accountKeyService.create(accountKey);
			} else {
				accountKey.setValue(maxcount + "");
				accountKeyService.update(accountKey);
			}

			// 生成订单号
			String orderno = getId(maxcount + "", 4);
			returnNo = date + orderno;

			return returnNo;
		}

	}

	/**
	 * 返回最大数
	 * 
	 */
	private int getMaxCount() {
		try {
			AccountKeyModel key = accountKeyService
					.getAccountKeyModelById(NOTYPE_ORDER);
			if (key != null) {
				return Integer.parseInt(key.getValue()) + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}

	/**
	 * 获取定长流水号
	 * 
	 * @param sno
	 * @param length
	 * @return
	 */
	public static String getId(String sno, int length) {
		int zeronum = length - ((sno + "").length());

		String returnSno = "";

		for (int i = 0; i < zeronum; i++) {
			returnSno = returnSno + "0";
		}

		returnSno = returnSno + sno;

		return returnSno;
	}

	/**
	 * 根据分类id获得文章列表
	 * 
	 * @param contentCategoryUuid
	 */
	@Override
	public List<ContentModel> getByContentCategoryUuid(
			String contentCategoryUuid) {
		return myDao.getContentListByCategoryUuid(contentCategoryUuid);
	}
	/**
	 * 根据患教分类id获得患教列表
	 * 
	 * @param categoryId
	 */
	@Override
	public List<ContentModel> getCustomerTeachList(String categoryId) {
		return myDao.getCustomerTeachList(categoryId);
		}

	/**
	 * app端搜索资讯信息
	 * @param contentCategoryUuid
	 * @param qm
	 * @return
	 */
	 
	@Override
	public List<ContentModel> getByContentCategoryUuid(
			String contentCategoryUuid, ContentQueryModel qm) {
		
		return myDao.getByContentCategoryUuid(contentCategoryUuid, qm);
	}
	/**
         * 
         * @Description: TODO(返回咨询的list)    
         * @author XP  
         * @return
         * @date 2015-12-27 上午11:24:51
         */
        @Override
        public List<ContentModel> getAllContentModelList() {
            return myDao.getAllContentModelList();
        }
	
}