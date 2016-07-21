package com.aebiz.b2b2c.websiteoperation.versionupdate.service.impl;

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
import com.aebiz.b2b2c.websiteoperation.versionupdate.dao.VersionUpdateDAO;
import com.aebiz.b2b2c.websiteoperation.versionupdate.service.VersionUpdateService;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateModel;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateQueryModel;

@Service
@Transactional
public class VersionUpdateServiceImpl extends BaseServiceImpl<VersionUpdateModel,VersionUpdateQueryModel> implements VersionUpdateService {
	private VersionUpdateDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(VersionUpdateDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	@Autowired
	private FileUploadHelper fileUpload ;
	

	@Override
	public String create(VersionUpdateModel m) {
		m.setUuid(us.getNextUuid("VersionUpdate",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(VersionUpdateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(VersionUpdateModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public VersionUpdateModel uploadFile(VersionUpdateModel versionUpdate,
			MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "hxq"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date());

					// 如果文件大小不为0
					if (files[i].getSize() <= 0) {
						continue;
					}
					String oldName = fileUpload.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					versionUpdate.setUrl(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				versionUpdate.setUrl("");
			}
		} catch (Exception ex) {
			versionUpdate.setUrl("");
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
		return versionUpdate;
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
	 * 获取最发布的应用
	 * @param versionType
	 * @return
	 */
	@Override
	public VersionUpdateModel getVersionUpdateModel(String versionType) {
		
		return myDao.getVersionUpdateModel(versionType);
	}
}