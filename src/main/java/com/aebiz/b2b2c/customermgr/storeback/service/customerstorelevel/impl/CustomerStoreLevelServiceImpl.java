package com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelQueryModel;
import com.aebiz.b2b2c.customermgr.storeback.dao.customerstorelevel.CustomerStoreLevelDAO;
import com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel.CustomerStoreLevelService;

@Service
@Transactional
public class CustomerStoreLevelServiceImpl extends
		BaseServiceImpl<CustomerStoreLevelModel, CustomerStoreLevelQueryModel>
		implements CustomerStoreLevelService {
	private CustomerStoreLevelDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private FileUploadHelper fileUpload = null;
	@Autowired
	private FileService fileService;

	@Autowired
	public void setMyDao(CustomerStoreLevelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerStoreLevelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		// 调用商户系统接口获取商户编号
		String storeUuid = "store01";
		m.setStoreUuid(storeUuid);

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerStoreLevelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		super.update(m);
	}

	@Override
	public void delete(CustomerStoreLevelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 校验商户会员等级名称是否存在
	 */
	@Override
	public boolean checkLevelNameExist(String levelName, String uuid) {
		return this.myDao.checkLevelNameExist(levelName, uuid);
	}

	/**
	 * 根据商户编号获取商户会员等级的集合
	 */
	@Override
	public List<CustomerStoreLevelModel> getStoreLevelModelList(String storeUuid) {
		return this.myDao.getStoreLevelModelList(storeUuid);
	}

	/**
	 * 上传等级图标
	 * 
	 * @param m
	 * @param files
	 * @return
	 */
	public CustomerStoreLevelModel uploadImage(CustomerStoreLevelModel m,
			MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "storeLevelIcon"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date());
					// 如果文件大小不为0
					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUpload.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					m.setLevelIcon(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				m.setLevelIcon("");
			}
		} catch (Exception ex) {
			m.setLevelIcon("");
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
		return m;
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
}