package com.aebiz.b2b2c.product.productimagelibrary.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productimagelibrary.dao.ProductImageLibraryDAO;
import com.aebiz.b2b2c.product.productimagelibrary.service.ProductImageLibraryService;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryModel;
import com.aebiz.b2b2c.product.productimagelibrary.vo.ProductImageLibraryQueryModel;

@Service
@Transactional
public class ProductImageLibraryServiceImpl extends BaseServiceImpl<ProductImageLibraryModel,ProductImageLibraryQueryModel> implements ProductImageLibraryService {
	private ProductImageLibraryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductImageLibraryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	//上传文件的service ,为了获取文件model
	@Autowired
	private FileService fileService;
	
	//图片上传
	@Autowired
	private FileUploadHelper fileUpload;

	@Override
	public String create(ProductImageLibraryModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		m.setUpLoadTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(ProductImageLibraryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(ProductImageLibraryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	

	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<ProductImageLibraryModel>
	 */
	public List<ProductImageLibraryModel> getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid,int nowPage ,int pageShow){
		return myDao.getProductImageLibraryModelsByCategoryUuid(categoryUuid,nowPage,pageShow);
	}
	
	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return int
	 */
	public int getProductImageLibraryModelsByCategoryUuid(
			String categoryUuid){
		return myDao.getProductImageLibraryModelsByCategoryUuid(categoryUuid);
	}
	
	/**
	 * 上传图片,并返回图片对象
	 * @param myfiles
	 * @return 
	 * ProductImageLibraryModel
	 */
	public ProductImageLibraryModel updloadImage(MultipartFile[] myfiles,String categoryUuid){
			// 校验文件是否已经上传
			List<String> fileNameList = new ArrayList<String>();
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			String picName = "";
			String suffix = "";
			long size = 0;
			int width = 0;
			int height = 0;
			for (MultipartFile myfile : myfiles) {
				picName = System.currentTimeMillis() + "picture";
				fileNameList.add(picName);
				fileList.add(myfile);
				size = myfile.getSize();
				suffix = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf(("."))+1);
				BufferedImage image;
				try {
					image = ImageIO.read(myfile.getInputStream());
					width = image.getWidth();
					height = image.getHeight();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 是否上传
			if (fileList != null && fileList.size() > 0) {
				MultipartFile[] newfiles = new MultipartFile[fileList.size()];
				String[] fileNames = new String[fileList.size()];

				for (int i = 0; i < fileList.size(); i++) {
					newfiles[i] = fileList.get(i);
					fileNames[i] = fileNameList.get(i);
				}
				// 上传文件
				fileUpload.uploadFiles(newfiles, fileNames);
			}
			FileModel file = fileService.getOneFileModel(picName);
			ProductImageLibraryModel image = new ProductImageLibraryModel();
			image.setImageName(file.getFileName());
			image.setImagePath(file.getRemotePaths());
			image.setRealName(file.getFileName());
			image.setImageSpace(size+"kb");
			image.setImageSize(width + "×" + height);
			image.setCategoryUuid(categoryUuid);
			image.setSuffix(suffix);
			
			this.create(image);
			
			return  image;
	}
}