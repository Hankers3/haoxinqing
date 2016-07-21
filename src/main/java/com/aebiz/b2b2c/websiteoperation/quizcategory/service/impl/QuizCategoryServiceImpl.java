package com.aebiz.b2b2c.websiteoperation.quizcategory.service.impl;

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
import com.aebiz.b2b2c.websiteoperation.quizcategory.dao.QuizCategoryDAO;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryQueryModel;

@Service
@Transactional
public class QuizCategoryServiceImpl extends BaseServiceImpl<QuizCategoryModel,QuizCategoryQueryModel> implements QuizCategoryService {
	@Autowired
	private FileUploadHelper fileUpload = null;
	
	private QuizCategoryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(QuizCategoryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(QuizCategoryModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		//如果获取创建时间为空 则保存当前时间
		if(StringUtil.isEmpty(m.getCreateTime())){
			m.setCreateTime(DateFormatHelper.getNowTimeStr().substring(0, 10));
		}
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(QuizCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(QuizCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 判断分类名 是否存在
	 * @param categoryName
	 * @return
	 */
	@Override
	public boolean checkCateGoryName(String categoryName) {
		String flag = myDao.checkCateGoryName(categoryName);
		if(flag.equals("1") ){
			return true;
		}
		return false;
	}
	/**
	 * 根据uuid获取分类名
	 * @param uuid
	 * @return
	 */
	@Override
	public String getCategoryName(String uuid) {
		
		return myDao.getCategoryName(uuid);
	}

	@Override
	public QuizCategoryModel uploadImage(QuizCategoryModel qm,
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
					qm.setImage(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				qm.setImage("");
			}
		} catch (Exception ex) {
			qm.setImage("");
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
		return qm;
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
	 * 获取所有展示的分类
	 * @return
	 */
	@Override
	public List<QuizCategoryModel> getQuizCategoryModels() {
		
		return myDao.getQuizCategoryModels();
	}
}