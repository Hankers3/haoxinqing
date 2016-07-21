package com.aebiz.b2b2c.customermgr.customerinfo.service.impl;

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
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customerinfo.dao.CustomerInfoDAO;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoQueryModel;

@Service
@Transactional
public class CustomerInfoServiceImpl extends BaseServiceImpl<CustomerInfoModel, CustomerInfoQueryModel>
		implements CustomerInfoService {
	private CustomerInfoDAO myDao = null;
	@Autowired
	private FileUploadHelper fileUpload = null;
	@Autowired
	private FileService fileService;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(CustomerInfoDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(CustomerInfoModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(CustomerInfoModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据会员编号获取会员基础信息
	 */
	public CustomerInfoModel getCustomerInfoModelByCustomerUuid(String customerUuid) {
		return this.myDao.getCustomerInfoModelByCustomerUuid(customerUuid);
	}

	/**
	 * 保存会员图像信息
	 * 
	 * @param m
	 *            会员图像
	 * @param files
	 *            图片
	 * @return
	 */
	public CustomerInfoModel uploadImage(CustomerInfoModel m, MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "customerInfo" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUpload.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					m.setImage(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				m.setImage("");
			}
		} catch (Exception ex) {
			m.setImage("");
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

	/**
	 * 更新会员基础信息
	 * 
	 * @param customerModel
	 */
	public void updateCustomerInfo(CustomerCombModel customerCombModel, MultipartFile[] imgFiles) {
		CustomerInfoModel cim = customerCombModel.getCustomerInfoModel();

		if (!StringUtil.isEmpty(cim.getUuid())) {
			this.uploadImage(cim, imgFiles);
			this.update(cim);
		} else {
			cim.setCustomerUuid(customerCombModel.getCustomerModel().getUuid());
			/* 上传图片 */
			this.uploadImage(cim, imgFiles);
			this.create(cim);
		}
	}

	/**
	 * 更新会员实名认证信息的时候需要同时更新会员基础信息
	 * 
	 * @param cutomerAuthModel
	 */
	public void updateCustomerInfo(CustomerAuthModel customerAuthModel) {
		CustomerInfoModel customerInfoModel = getCustomerInfoModelByCustomerUuid(customerAuthModel.getCustomerUuid());

		customerInfoModel.setRealName(customerAuthModel.getRealName());
		customerInfoModel.setCertType(customerAuthModel.getCertType());
		customerInfoModel.setCertCode(customerAuthModel.getCertCode());

		super.update(customerInfoModel);
	}

	/**
	 * 更新会员头像信息
	 */
	@Override
	public void updateImage(String customerUuid, String image) {
		myDao.updateImage(customerUuid, image);
	}

	/**
	 * 根据客户信息查询客户的realName
	 */
	@Override
	public String getRealNameByUuid(String uuid) {
		return myDao.getRealNameByUuid(uuid);
	}

	/**
	 * 根据客户信息查询客户的性别
	 */
	@Override
	public String getSexByUuid(String uuid) {
		return myDao.getSexByUuid(uuid);
	}

	/**
	 * 根据客户信息查询客户的生日
	 */
	@Override
	public String getBirthdayByUuid(String uuid) {
		return myDao.getBirthdayByUuid(uuid);
	}

	/**
	 * 根据客户信息查询客户的年龄
	 */
	@Override
	public String getAgeByUuid(String customerUuid) {
		return myDao.getAgeByUuid(customerUuid);
	}

	/**
	 * 根据出生日期获得age的大小
	 * 
	 * @param customerUuid
	 * @param birthday
	 * @return
	 */
	@Override
	public void updateAgeByBirthday() {
		List<CustomerInfoModel> list = myDao.getAll();

		if (list != null && list.size() > 0) {
			for (CustomerInfoModel cim : list) {
				if (cim != null) {
					myDao.updateAgeByBirthday(cim.getCustomerUuid(), cim.getBirthday());
				}
			}
		}
	}

}