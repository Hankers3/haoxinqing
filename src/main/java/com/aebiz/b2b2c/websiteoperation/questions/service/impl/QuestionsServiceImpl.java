package com.aebiz.b2b2c.websiteoperation.questions.service.impl;

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
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.websiteoperation.options.service.OptionsService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.dao.QuestionsDAO;
import com.aebiz.b2b2c.websiteoperation.questions.service.QuestionsService;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsQueryModel;

@Service
@Transactional
public class QuestionsServiceImpl extends
		BaseServiceImpl<QuestionsModel, QuestionsQueryModel> implements
		QuestionsService {
	private QuestionsDAO myDao = null;

	/* 注入uuid service */
	@Autowired
	private UuidService us;

	/* 注入试题service */
	@Autowired
	private OptionsService optionService;
	
	@Autowired
	private FileUploadHelper fileUpload ;
	
	@Autowired
	public void setMyDao(QuestionsDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(QuestionsModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		//设置发布时间
		if(StringUtil.isEmpty(m.getCreateTime())){
			m.setCreateTime(DateFormatHelper.getNowTimeStr());
		}

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(QuestionsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(QuestionsModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除试题，需要先删除试题所关联的选项，然后删除试题
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		for (String questionUuid : needDeleteUuids) {
			optionService.deleteByQuestionUuid(questionUuid);
		}
		super.deletes(needDeleteUuids);
	}

	/**
	 * 在保存试题的时候,同时保存试题的选项
	 * 
	 * 1.保存试题 <br />
	 * 2.删除原先的试题和选项的关联关系<br />
	 * 3.再重新保存所有的选项
	 * 
	 * @param qm
	 *            试题
	 * 
	 * @param omList
	 *            试题选项的列表，需要循环保存
	 */
	@Override
	public void updateQuestion(QuestionsModel qm, List<OptionsModel> omList,MultipartFile[] imgFiles) {
		//上传图片
		this.uploadImage(qm, imgFiles);
		// 保存试题
		this.update(qm);

		// 删除试题的选项
		//optionService.removePreOptions(qm.getUuid());

		// 循环保存试题选项
		saveOptionsBatch(qm.getUuid(), omList);
	}

	/**
	 * 在保存试题的时候,同时保存试题的选项
	 * 
	 * 1.保存试题 <br />
	 * 2.保存所有的选项
	 * 
	 * @param qm
	 *            试题
	 * @param omList
	 *            试题选项的列表，需要循环保存
	 */
	@Override
	public void addQuestion(QuestionsModel qm, List<OptionsModel> omList,MultipartFile[] imgFiles) {
		
		//上传图片
		this.uploadImage(qm, imgFiles);
		// 保存试题
		this.create(qm);

		// 先保存试题，再循环保存试题选项
		saveOptionsBatch(qm.getUuid(), omList);
	}
	
	/**
	 * 文件上传
	 * 
	 * @param customerShopLevelModel
	 * @param files
	 * @return
	 */
	public QuestionsModel uploadImage(QuestionsModel m,MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "questionsIcon"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date());

					// 如果文件大小不为0
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
	 * 
	 * 根据问卷编号查询出所有的问卷试题model
	 * 
	 * @param questionnaireUuid
	 * @return
	 */
	public List<QuestionsModel> getQuestionsByQuestionnaireUuid(
			String questionnaireUuid) {
		return myDao.getQuestionsByQuestionnaireUuid(questionnaireUuid);
	}

	/**
	 * 得到没有被选中的questions
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param choosedQuestionIds
	 * @return
	 */
	@Override
	public List<QuestionsModel> getQuestionsByNotSelected(int iDisplayStart,
			int iDisplayLength, String[] choosedQuestionIds) {
		List<QuestionsModel> qmList = this.myDao.getQuestionsByNotSelected(
				iDisplayStart, iDisplayLength, choosedQuestionIds);
		return qmList;
	}

	/**
	 * 得到没有被选中的questions的数量
	 */
	@Override
	public int getQuestionsCountByNotSelected(String[] choosedQuestionIds) {
		return this.myDao.getQuestionsCountByNotSelected(choosedQuestionIds);
	}

	/**
	 * 批量保存试题的选项，建立选项和试题的关联关系
	 * 
	 * @param questionUuid
	 * @param omList
	 */
	private void saveOptionsBatch(String questionUuid, List<OptionsModel> omList) {
		if (omList != null && omList.size() > 0) {
			for (OptionsModel om : omList) {
				String  uuid = om.getUuid();
				String  title = om.getOptionTitle();
				int value = om.getOptionValue();
				if(!StringUtil.isEmpty(uuid)){
					om= optionService.getByUuid(uuid);
					if(om !=null){
						om.setQuestionUuid(questionUuid);
						om.setOptionValue(value);
						om.setOptionTitle(title);
						optionService.update(om);
					}
				}else{
					try {
						Thread.sleep(1000);
						om.setQuestionUuid(questionUuid);
						om.setCreateTime(DateFormatHelper.getNowTimeStr());
						optionService.create(om);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public List<QuestionsModel> getQuestionsByQuizCategoryUuid(
			String quizCategoryUuid,String state) {
		return myDao.getQuestionsByQuizCategoryUuid(quizCategoryUuid,state);
	}
}