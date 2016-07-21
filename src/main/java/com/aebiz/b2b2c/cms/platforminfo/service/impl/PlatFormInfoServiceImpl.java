package com.aebiz.b2b2c.cms.platforminfo.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.basebusiness.utils.DateUtil;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.platforminfo.dao.PlatFormInfoDAO;
import com.aebiz.b2b2c.cms.platforminfo.helper.PlatFormInfoHelper;
import com.aebiz.b2b2c.cms.platforminfo.service.ContentTranscodingRelationService;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.ContentTranscodingRelation;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoQueryModel;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.PartETag;

@Service
@Transactional
public class PlatFormInfoServiceImpl extends
		BaseServiceImpl<PlatFormInfoModel, PlatFormInfoQueryModel> implements
		PlatFormInfoService {
	private PlatFormInfoDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private FileUploadHelper fileUpload = null;
	@Autowired
	private FileService fileService;
	@Autowired
	private PlatFormInfoHelper platFormInfoHelper;
	@Autowired
	private ContentTranscodingRelationService ctrs;

	@Autowired
	public void setMyDao(PlatFormInfoDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(PlatFormInfoModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(PlatFormInfoModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(PlatFormInfoModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 上传视频
	 */
	@Override
	public PlatFormInfoModel uploadVideo(PlatFormInfoModel platFormInfoModel,
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

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				platFormInfoModel.setVideoAddress("");
			}
		} catch (Exception ex) {
			platFormInfoModel.setVideoAddress("");
		}
		if (fileList != null && fileList.size() > 0) {
			MultipartFile[] newFiles = new MultipartFile[fileList.size()];
			String[] newFileNames = new String[fileList.size()];
			for (int i = 0; i < fileList.size(); i++) {
				newFileNames[i] = fileNameList.get(i);
				newFiles[i] = fileList.get(i);
			}
			//String url = platFormInfoHelper.uploadFile(newFiles[0],newFileNames[0]);
			platFormInfoModel.setfName(newFileNames[0]);
			String url;
			try {
				url = multipartUpload(newFiles[0],newFileNames[0]);
				platFormInfoModel.setVideoAddress(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return platFormInfoModel;
	}

	
	/**
	 * 上传图片文件
	 */
	@Override
	public PlatFormInfoModel uploadImage(PlatFormInfoModel platFormInfoModel,
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
					platFormInfoModel.setImage(newName);
					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				platFormInfoModel.setImage("");
			}
		} catch (Exception ex) {
				platFormInfoModel.setImage("");
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

		return platFormInfoModel;
	}
	
	
	/**
	 * 分块上传
	 * @param file
	 * @param fName
	 * @return
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public String multipartUpload(MultipartFile file, String fName) throws Exception{
		try {
			String returnUrl = "";
			String isComplete = "";
			//获取BosClient
			BosClient client = PlatFormInfoHelper.createClient();
			//源桶名称
			String sourceBucketName = MessageHelper.getMessage("bos.bucketName");
			//初始化分块上传 获取上传任务id
			String uploadId = PlatFormInfoHelper.initiateMultipartUpload(client, sourceBucketName, fName);
			//分块上传返回块列表
			List<PartETag> partETags = PlatFormInfoHelper.multipartUpload(client, sourceBucketName, fName, uploadId, file);
			//完成分块上传
			isComplete = PlatFormInfoHelper.completeUpload(client, sourceBucketName, fName, uploadId, partETags);
			if (!StringUtil.isEmpty(isComplete)&& "true".equals(isComplete)) {
				returnUrl = PlatFormInfoHelper.generatePresignedUrl(fName);
			}
			System.out.println("-----------returnUrl"+returnUrl+"---------------------------------");
			return returnUrl;
		} catch (Exception e) {
			throw new Exception("multipart_upload_error");
		}
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
	 * 通过医生id得到预约课程
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<PlatFormInfoModel> getByUserid(String userId) {
		return myDao.getByUserid(userId);
	}

	/**
	 * 通过名称得到视频
	 * 
	 * @return
	 */
	@Override
	public List<PlatFormInfoModel> getByName(String name, String videoType) {
		return myDao.getByName(name,videoType);
	}

	/**
	 * 根据视频的UUid获取该视频的开讲时间
	 * 
	 * @param vidoUuid
	 * @return
	 */
	@Override
	public String getStartTimeByVidoUuid(String vidoUuid) {
		return myDao.getStartTimeByVidoUuid(vidoUuid);
	}
	
	//根据相关参数进行转码 返回文件名称
	public String transcodingMedia(ContentTranscodingRelation ctr,String fName,String randomKey) throws ServiceException {
		try {
			String fNameTranscoding =  "";
			String fNameTranscodingAndJobId =  "";
			String jobId = "";
			fNameTranscodingAndJobId = PlatFormInfoHelper.transcodingMedia(fName,randomKey);
			if (fNameTranscodingAndJobId.indexOf(",")>0) {
				String fNameAndJobId [] = fNameTranscodingAndJobId.split(",");
				fNameTranscoding = fNameAndJobId[0];
				jobId = fNameAndJobId[1];
			}
			ctr.setJobId(jobId);
			return fNameTranscoding;
		} catch (Exception ex) {
			//转码异常后把已经上传的文件删除否则会造成内容未保存成功，而百度云上有上传的文件，从而出现垃圾数据。
//			if (!StringUtil.isEmpty(fName)) {
//				PlatFormInfoHelper.deleteFile(fName);
//				throw new ServiceException("transcoding_error");
//			}
			ex.printStackTrace();
			throw new ServiceException("transcoding_error");
		}
	}
	
	// 自动转码
	public  void autoTranscodingMedia() {
		// 获取所有内容
		try {
			//get all
			List<PlatFormInfoModel> contents = this.getAll();
			for (int i = 0; i < contents.size(); i++) {
				PlatFormInfoModel content = contents.get(i);

				String fName = content.getfName();
				String sid = content.getUuid();
				String fNameTranscoding = "";
				// 如果视频名称里面不包含sid才去进行转码任务
				if (fName.indexOf(sid) < 0) {

					ContentTranscodingRelation ctr = new ContentTranscodingRelation();
					ctr.setContentName(content.getVideoModel());
					ctr.setSourceFileName(content.getfName());
					ctr.setSourceFileUrl(content.getVideoAddress());
					ctr.setTargetFileName(content.getfName());
					ctr.setTargetFileUrl(content.getVideoAddress());
					ctr.setCreateTime(DateUtil.getSysDateTimeString());
					ctr.setContentSid(content.getUuid());

					ctrs.create(ctr);
					// 转码
					fNameTranscoding = transcodingMedia(ctr,
							content.getfName(), content.getUuid());
					content.setfName(fNameTranscoding);
					// 获取转码后文件的地址保存
					String targetKeyUrl = PlatFormInfoHelper
							.generatePresignedUrl(fNameTranscoding);
					if (!StringUtil.isEmpty(targetKeyUrl)) {
						ctr.setTargetFileName(fNameTranscoding);
						ctr.setTargetFileUrl(targetKeyUrl);
						ctrs.update(ctr);
					}
					this.update(content);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 查询需要转码的视频的内容并转码
	 * 
	 * @return
	 */
	public  String getNoTranscodingContents() {
		List<PlatFormInfoModel> contents = this.getAll();
		for (int i = 0; i < contents.size(); i++) {
			PlatFormInfoModel con = contents.get(i);
			String name = con.getVideoModel();
			
			String fileName = con.getVideoAddress();
			String sid = con.getUuid();
			String transcoding = "transcoding";
			String tStr = fileName.substring(0,
					fileName.indexOf("?authorization="));
			// 如果不包含sid和transcoding 这两个则此视频的url是未经过转码的
			if (tStr.indexOf(sid) < 0 || tStr.indexOf(transcoding) < 0) {
				try {
					ContentTranscodingRelation ctr = ctrs.getBySid(sid);
					String targetFileUrl = ctr.getTargetFileUrl();
					String targeturl = targetFileUrl.substring(0,
							targetFileUrl.indexOf("?authorization="));
					if (targeturl.indexOf(sid) >= 0
							&& targeturl.indexOf(transcoding) >= 0) {
						con.setVideoAddress(targetFileUrl);
						this.update(con);
						
					} else {
						// TranscodingByContentId(con.getId());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return "success";
	}

	/**
	 * 根据内容id来对其关联的视频进行转码
	 * 
	 * @param id
	 */
	public  void TranscodingByContentId(String id) {
		// 获取所有内容
		try {
			PlatFormInfoModel content = this.getByUuid(id);

			String fName = content.getfName();
			String sid = content.getUuid();
			String fNameTranscoding = "";
			// 如果视频名称里面不包含sid才去进行转码任务
			if (fName.indexOf(sid) < 0) {

				ContentTranscodingRelation ctr = new ContentTranscodingRelation();
				ctr.setContentName(content.getVideoModel());
				ctr.setSourceFileName(content.getfName());
				ctr.setSourceFileUrl(content.getVideoAddress());
				ctr.setTargetFileName(content.getfName());
				ctr.setTargetFileUrl(content.getVideoAddress());
				ctr.setCreateTime(DateUtil.getSysDateTimeString());
				ctr.setContentSid(content.getUuid());

				ctrs.create(ctr);
				// 转码
				fNameTranscoding = transcodingMedia(ctr,
						content.getfName(), content.getUuid());

				// 获取转码后文件的地址保存
				String targetKeyUrl = PlatFormInfoHelper
						.generatePresignedUrl(fNameTranscoding);
				if (!StringUtil.isEmpty(targetKeyUrl)) {
					ctr.setTargetFileName(fNameTranscoding);
					ctr.setTargetFileUrl(targetKeyUrl);
					ctrs.update(ctr);
				}
				content.setfName(fNameTranscoding);
				content.setVideoAddress(targetKeyUrl);
				update(content);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}