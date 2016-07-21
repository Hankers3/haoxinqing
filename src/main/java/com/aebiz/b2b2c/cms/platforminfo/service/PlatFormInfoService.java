package com.aebiz.b2b2c.cms.platforminfo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoQueryModel;

public interface PlatFormInfoService extends BaseService<PlatFormInfoModel,PlatFormInfoQueryModel>{
	/**
	 * 上传视频
	 * @param m
	 * @param files
	 * @return
	 */
	public PlatFormInfoModel uploadVideo(PlatFormInfoModel m,
			MultipartFile[] files);
	/**
	 * 上传图片
	 * @param m
	 * @param files
	 * @return
	 */
	public PlatFormInfoModel uploadImage(PlatFormInfoModel m,
			MultipartFile[] files);

	/**
	 * 通过医生id得到预约课程
	 * @param userId
	 * @return
	 */
	public List<PlatFormInfoModel> getByUserid(String userId);

	/**
	 * 通过名称得到视频
	 * @param videoType 
	 * @return
	 */
	public List<PlatFormInfoModel> getByName(String name, String videoType);
	/**
	 * 根据视频的UUid获取该视频的开讲时间
	 * @param vidoUuid
	 * @return
	 */
    public String getStartTimeByVidoUuid(String vidoUuid);
    
    public void TranscodingByContentId(String id);

}
