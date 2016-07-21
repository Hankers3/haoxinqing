package com.aebiz.b2b2c.cms.consumerprotection.service;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;

/**
 * 权益保障服务管理,如全国包邮，七天退款等
 * 
 * 平台发布一些权益保障服务、如：7天退换货等<br/>
 * 供商户参与服务。
 * 
 * @author XiaoY
 *
 */
public interface ConsumerProtectionService extends
		BaseService<ConsumerProtectionModel, ConsumerProtectionQueryModel> {
	
	/**
	 *  上传权益图标
	 * @param m
	 * @param files
	 * @return
	 */
	public ConsumerProtectionModel uploadImage(ConsumerProtectionModel m, MultipartFile[] files);

}
