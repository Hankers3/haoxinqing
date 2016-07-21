package com.aebiz.b2b2c.websiteoperation.paltcongfig.service.impl;

import org.jsoup.helper.DataUtil;
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
import com.aebiz.b2b2c.store.storecompanyinfo.vo.StoreCompanyInfoModel;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.dao.PaltCongfigDAO;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.service.PaltCongfigService;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigModel;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigQueryModel;

@Service
@Transactional
public class PaltCongfigServiceImpl extends BaseServiceImpl<PaltCongfigModel,PaltCongfigQueryModel> implements PaltCongfigService {
	private PaltCongfigDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(PaltCongfigDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	// 注入上传文件帮助类
	@Autowired
	private FileUploadHelper fileUpload ;
		
	/**
	 * 创建系统配置信息,数据库中只能存在一条记录,如果数据库有则更新,不存在则先添加再更新
	 * @param m
	 * @return 
	 * PaltCongfigModel
	 */
	@Override
	public PaltCongfigModel createPlatConfig(PaltCongfigModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		super.create(m);
		return m;
	}
	
	@Override
	public String create(PaltCongfigModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		super.create(m);
		return "";
	}
	
	/**
	 * 修改商城的配置信息
	 */
	@Override
	public void updatePlatConfig(PaltCongfigModel m,MultipartFile[] files) {
		if(files != null && files.length> 0 ){
			this.upLoadLogo(m, files);
		}
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void update(PaltCongfigModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(PaltCongfigModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 上传商城model和微信图片
	 * 
	 * @param model
	 */
	private void upLoadLogo(PaltCongfigModel model, MultipartFile[] files) {
		// 给上传的的文件名重新命名，避免文件名重复覆盖原来的导致发生错误
		String[] names = new String[4];

		// 将文件的名字放进对象
		try {
			// 第1个文件为Logo，将名字装入对象
			String fileName1 = this
					.getNewName(files, names, model.getUuid(), 0);
			if (!StringUtil.isEmpty(fileName1)) {
				model.setPlatLogo(fileName1);
			}
			// 第2个文件为微信图片，将名字装入对象
			String fileName2 = this
					.getNewName(files, names, model.getUuid(), 1);
			if (!StringUtil.isEmpty(fileName2)) {
				model.setWeixinImage(fileName2);
			}
			// 第3个文件为头部广告图片，将名字装入对象
			String fileName3 = this
					.getNewName(files, names, model.getUuid(), 2);
			if (!StringUtil.isEmpty(fileName3)) {
				model.setTopAd(fileName3);
			}
			// 第2个文件为底部广告图片，将名字装入对象
			String fileName4 = this
					.getNewName(files, names, model.getUuid(), 3);
			if (!StringUtil.isEmpty(fileName4)) {
				model.setBottomAd(fileName4);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 将新的文件名数组传入
		fileUpload.uploadFiles(files, names);
	}

	/**
	 * 给上传的文件新的文件名
	 * 
	 * @param files
	 * @param names
	 * @param uuid
	 * @param i
	 * @return String
	 *
	 */
	private String getNewName(MultipartFile[] files, String[] names,
			String uuid, int i) {
		MultipartFile file = (MultipartFile) files[i];
		if (file != null && !file.isEmpty()) {
			// 重新命名文件
			String fileName;
			try {
				fileName = System.currentTimeMillis() + fileUpload.getFileName(file);
				names[i] = fileName;
				return fileName;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "";
	}
}