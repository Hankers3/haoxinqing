package com.aebiz.b2b2c.customermgr.customershoplevel.service.impl;

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
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.customermgr.customershoplevel.dao.CustomerShopLevelDAO;
import com.aebiz.b2b2c.customermgr.customershoplevel.service.CustomerShopLevelService;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelModel;
import com.aebiz.b2b2c.customermgr.customershoplevel.vo.CustomerShopLevelQueryModel;

@Service
@Transactional
public class CustomerShopLevelServiceImpl extends
		BaseServiceImpl<CustomerShopLevelModel, CustomerShopLevelQueryModel>
		implements CustomerShopLevelService {
	private CustomerShopLevelDAO myDao = null;
	@Autowired
	private FileUploadHelper fileUpload = null;
	@Autowired
	private UuidService us;

	/* 注入平台会员等级service */
	@Autowired
	public void setMyDao(CustomerShopLevelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerShopLevelModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerShopLevelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(CustomerShopLevelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 得到平台会员初始等级编号
	 * 
	 * @return
	 */
	public String getInitLevelUuid() {
		return this.myDao.getInitLevelUuid();
	}

	/**
	 * 得到平台会员等级model的集合
	 */
	
	public List<CustomerShopLevelModel> getShopLevelModelList() {
		return this.myDao.getShopLevelModelList();
	}

	/**
	 * 通过编号获取等级名称
	 */
	public String getLevelNameByUuid(String uuid) {
		return this.myDao.getLevelNameByUuid(uuid);
	}

	/**
	 * 得到会员平台等级名称的集合
	 */
	public List<String> getCustomerShopLevelName() {
		return this.myDao.getCustomerShopLevelName();
	}

	/**
	 * 根据等级名称校验平台会员等级名称是否存在
	 */

	public boolean checkLevelNameExist(String levelName, String uuid) {
		return this.myDao.checkLevelNameExist(levelName, uuid);
	}

	/**
	 * 上传等级图标
	 * 
	 * @param m
	 * @param files
	 * @return
	 */

	public CustomerShopLevelModel uploadImage(CustomerShopLevelModel m,
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

	@Override
	public String getLevelNameByIntergral(int intergralcount) {
		
		return myDao.getLevelNameByIntergral(intergralcount);
	}

	@Override
	public String getUuidByLevelName(String levelName) {
		
		return myDao.getUuidByLevelName(levelName);
	}

}