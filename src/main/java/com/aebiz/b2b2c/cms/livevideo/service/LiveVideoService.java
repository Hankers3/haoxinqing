package com.aebiz.b2b2c.cms.livevideo.service;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.livevideo.vo.LiveVideoModel;
import com.aebiz.b2b2c.cms.livevideo.vo.LiveVideoQueryModel;

public interface LiveVideoService extends BaseService<LiveVideoModel,LiveVideoQueryModel>{
	/**
	 * 文件上传
	 * 
	 * @param  liveVideo
	 * @param files
	 * @return
	 */
	public LiveVideoModel uploadFile(LiveVideoModel liveVideo,
			MultipartFile[] files);
}
