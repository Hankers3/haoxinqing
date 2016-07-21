package com.aebiz.b2b2c.cms.consumerprotection.service.impl;

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
import com.aebiz.b2b2c.cms.consumerprotection.dao.ConsumerProtectionDAO;
import com.aebiz.b2b2c.cms.consumerprotection.service.ConsumerProtectionService;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;

@Service
@Transactional
public class ConsumerProtectionServiceImpl extends
		BaseServiceImpl<ConsumerProtectionModel, ConsumerProtectionQueryModel>
		implements ConsumerProtectionService {
	private ConsumerProtectionDAO myDao = null;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(ConsumerProtectionDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ConsumerProtectionModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ConsumerProtectionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ConsumerProtectionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 上传权益图标
	 * 
	 * @param m
	 * @param files
	 * @return
	 */
	public ConsumerProtectionModel uploadImage(ConsumerProtectionModel m, MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "protectionIcon" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

					// 如果文件大小不为0
					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUploadHelper.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					m.setIcon(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				m.setIcon("");
			}
		} catch (Exception ex) {
			m.setIcon("");
		}
		if (fileList != null && fileList.size() > 0) {
			MultipartFile[] newFiles = new MultipartFile[fileList.size()];
			String[] newFileNames = new String[fileList.size()];
			for (int i = 0; i < fileList.size(); i++) {
				newFileNames[i] = fileNameList.get(i);
				newFiles[i] = fileList.get(i);
			}
			fileUploadHelper.uploadFiles(newFiles, newFileNames);
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