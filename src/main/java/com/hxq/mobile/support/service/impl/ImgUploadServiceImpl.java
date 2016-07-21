package com.hxq.mobile.support.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.product.utils.ImageUtils;
import com.hxq.mobile.entity.common.ImgUpload;
import com.hxq.mobile.entity.common.ImgUploadResponse;
import com.hxq.mobile.support.dao.ImgUploadDao;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.UploadFileUtils;
import com.hxq.mobile.util.repository.MybatisSimpleEntityService;
import com.hxq.mobile.util.repository.SimpleEntityDao;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

import fm.last.moji.spring.SpringMojiBean;

@Service("com.hxq.mobile.support.service.ImgUploadService")
public class ImgUploadServiceImpl extends MybatisSimpleEntityService
	implements ImgUploadService {
	Logger log = LoggerFactory.getLogger(ImgUploadServiceImpl.class);

	@Autowired
	private SpringMojiBean moji;

	@Override
	public ImgUploadResponse updateForUpload(boolean thumbnail, MultipartFile[] files) throws Exception {
		if(files==null || files.length < 1) return null;

    	String tmp = null;
    	StringBuffer sbf = null;
    	ImgUpload imgBean = null;
		String[] uploadResult,uploadThumbnail = null;
    	ImgUploadResponse result = new ImgUploadResponse();

    	initParameters();

       	for (MultipartFile current : files) {
       		imgBean = new ImgUpload();
       		imgBean.setImgName(current.getOriginalFilename());

       		if(current.isEmpty()) {
        		result.add(imgBean.getImgName(), "file is empty!");
        		continue;
        	}

        	//上传文件系统
        	uploadResult = UploadFileUtils.uploadFileToServer(current, moji);

        	//写入上传图片记录表
        	imgBean.setId(uploadResult[0]);
    		imgBean.setImgUrl(uploadResult[1]);
    		imgBean.setSuffix(uploadResult[2]);
    		imgBean.setFlag("0");

    		//生成缩略图
    		uploadThumbnail = null;
    		if(thumbnail == true) {
    			tmp = ImageUtils.getCompressedImage(uploadResult[1], im4java_tempPath, "small",
    					thumbnail_width, thumbnail_height, false, "", "");

    			sbf = new StringBuffer(1000);
    			sbf.append(im4java_tempPath).append(tmp);
    			uploadThumbnail = UploadFileUtils.uploadFileToServer(sbf.toString(), moji);

    			sbf.delete(0, sbf.length());
    			sbf.append(uploadThumbnail[0]).append(".").append(uploadThumbnail[2]);
        		imgBean.setThumbnailId(sbf.toString());
        		imgBean.setThumbnailUrl(uploadThumbnail[1]);
    		}

    		super.insert(imgBean);

    		//生成返回信息
    		result.add(imgBean.getId(), imgBean.getImgName(), current.getSize(),
    				uploadResult[1], uploadThumbnail != null ? uploadThumbnail[1] : null);
        }
        return result;
    }

	@Override
	public boolean updateForBindingBusiness(String tbName, String pkValue, String... ids) {
		if(ObjectUtils.isEmpty(tbName) || ObjectUtils.isEmpty(pkValue)
				|| ObjectUtils.isEmpty(ids)) return false;
		String strTb = tbName.toLowerCase(Locale.ENGLISH);
		String strPk = pkValue.toLowerCase(Locale.ENGLISH);
		ImgUpload bean = null;
		for(String id : ids) {
			if(ObjectUtils.isEmpty(id)==false) {
				bean = new ImgUpload(id);
				bean.setTbName(strTb);
				bean.setPkValue(strPk);
				super.update(bean);
			}
		}
		return true;
	}

	@Override
	public int delete(AbstractEntity<?> id) {
		//查询源文件
    	ImgUpload old = (ImgUpload) super.select(id);
    	if(old == null) return 0;
    	//删除文件服务器上的文件
    	StringBuffer sbf = new StringBuffer(1000);
    	sbf.append(old.getId());
    	if(ObjectUtils.isEmpty(old.getSuffix())==false) {
    		sbf.append(".").append(old.getSuffix());
    	}
    	//删除记录表
		return super.delete(id);
	}

	@Override
	public String[] selectImagesByTableName(String tbName, String pkValue) {
		if(ObjectUtils.isEmpty(tbName) || ObjectUtils.isEmpty(pkValue)) return null;
		Map<String, Object> mapIn = new HashMap<String, Object>();
		mapIn.put("tbName", tbName.toLowerCase(Locale.ENGLISH));
		mapIn.put("pkValue", pkValue.toLowerCase(Locale.ENGLISH));
		List<Map<String, Object>> lst = super.selectList(mapIn, false);
		if(ObjectUtils.isEmpty(lst)) return null;

		String url = null;
		List<String> urls = new ArrayList<String>(lst.size());
		for(Map<String, Object> row : lst) {
			url = StringUtils.trimToEmpty(row.get("imgUrl"));
			if(ObjectUtils.isEmpty(url)==false)	urls.add(url);
		}
		return urls.toArray(new String[0]);
	}

	@Override
	public Image4App[] selectImagesByIds(String... ids) {
		if(ObjectUtils.isEmpty(ids)) return null;
		ImgUpload iu = null;
		List<Image4App> urls = new ArrayList<Image4App>();
		for(String id : ids) {
			if(ObjectUtils.isEmpty(id)) continue;
			iu = (ImgUpload) super.select(new ImgUpload(id));
			if(iu == null || ObjectUtils.isEmpty(iu.getImgUrl())) {
				urls.add(new Image4App(id,null));
			} else if(ObjectUtils.isEmpty(iu.getThumbnailUrl())) {
				urls.add(new Image4App(iu.getImgUrl(),null));
			} else {
				urls.add(new Image4App(iu.getImgUrl(),iu.getThumbnailUrl()));
			}
		}
		return urls.toArray(new Image4App[]{});
	}

	private String im4java_tempPath = null;
	private int thumbnail_width = 200;
	private int thumbnail_height = 200;
	private void initParameters() {
		im4java_tempPath = MessageHelper.getMessage("im4java.tempPath");// 图片压缩后的存储路径
		if(ObjectUtils.isEmpty(im4java_tempPath))
			im4java_tempPath = System.getProperty("java.io.tmpdir");
		if (im4java_tempPath.endsWith(File.separator) != true)
			im4java_tempPath = (new StringBuffer(im4java_tempPath)).append(File.separator).toString();

		thumbnail_width = MathUtils.toInt(MessageHelper.getMessage("thumbnail.width"),200);// 缩略图宽
		thumbnail_height = MathUtils.toInt(MessageHelper.getMessage("thumbnail.height"),200);// 缩略图高
	}

	protected SimpleEntityDao dao;
    @Autowired
    public void setDao(ImgUploadDao dao) {
        this.dao = dao;
    }
	@Override
	protected SimpleEntityDao getDao() {
		return dao;
	}
	@Override
	protected String getCacheName() {return "h1";}
}
