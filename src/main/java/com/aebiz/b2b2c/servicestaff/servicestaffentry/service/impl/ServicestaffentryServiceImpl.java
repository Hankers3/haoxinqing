package com.aebiz.b2b2c.servicestaff.servicestaffentry.service.impl;

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
import com.aebiz.b2b2c.servicestaff.servicestaffentry.dao.ServicestaffentryDAO;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.service.ServicestaffentryService;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryQueryModel;

@Service
@Transactional
public class ServicestaffentryServiceImpl extends BaseServiceImpl<ServicestaffentryModel,ServicestaffentryQueryModel> implements ServicestaffentryService {

	private ServicestaffentryDAO myDao = null;
	
	@Autowired
	private FileUploadHelper fileUpload = null;
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ServicestaffentryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ServicestaffentryModel m) {
		m.setUuid(us.getNextUuid("Servicestaffentry",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ServicestaffentryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ServicestaffentryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	
	/**
	 *通过家政员编号获取家政员入职信息
	 */
	public ServicestaffentryModel getServicestaffentryModelByServicestaffUuid(
			String servicestaffUuid) {
		
		return myDao.getServicestaffentryModelByServicestaffUuid(servicestaffUuid);
	}

	@Override
	public void updateServicestaffentry(ServicestaffentryModel sem,
			MultipartFile[] imgFiles,MultipartFile[] contractImgFiles,
			String[] whethercompletes) {
		
		/*是否完善信息*/
		String whethercomplete = "";
		if(whethercompletes !=null){
			if(whethercompletes.length>0){
				for(int i=0;i<whethercompletes.length;i++){
					whethercomplete = whethercomplete+whethercompletes[i]+";";
				}
			}
		}
		
		if (!StringUtil.isEmpty(sem.getUuid())) {
			this.uploadImage(sem, imgFiles);
			this.uploadImage1(sem, contractImgFiles);
			sem.setWhethercomplete(whethercomplete);
			this.update(sem);
		} else {
		
		}
		
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
	public ServicestaffentryModel uploadImage(ServicestaffentryModel m,
			MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "servicestaffentry"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date());

					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUpload.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					m.setStandardimage(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				m.setStandardimage("");
			}
		} catch (Exception ex) {
			m.setStandardimage("");
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
	 * 保存家政员合同 图片信息
	 * 
	 * @param m
	 *            
	 * @param files
	 *            图片
	 * @return
	 */
	public ServicestaffentryModel uploadImage1(ServicestaffentryModel m,
			MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "servicestaffentry"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date());

					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUpload.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					m.setContract(newName);
					//m.setStandardimage(newName);
					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				m.setContract("");
			}
		} catch (Exception ex) {
			m.setContract("");
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
	 *  更新家政员入职信息公司标准照
	 * @param sem
	 * @param imgFiles
	 * by hdf
	 */
	@Override
	public void updateSNTstandardimage(ServicestaffentryModel sem,
			MultipartFile[] imgFiles) {
			
		if (!StringUtil.isEmpty(sem.getUuid())) {
			this.uploadImage(sem, imgFiles);
			this.update(sem);
		} else {
		
		}
				
	}
}