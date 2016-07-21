package com.aebiz.b2b2c.websiteoperation.paltcongfig.service;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigModel;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigQueryModel;

public interface PaltCongfigService extends BaseService<PaltCongfigModel,PaltCongfigQueryModel>{

	/**
	 * 创建系统配置信息,数据库中只能存在一条记录
	 * @param m
	 * @return 
	 * PaltCongfigModel
	 */
	public PaltCongfigModel createPlatConfig(PaltCongfigModel m);
	
	/**
	 * 修改商城配置信息
	 * @param m
	 * @param files 
	 * void
	 */
	public void updatePlatConfig(PaltCongfigModel m,MultipartFile[] files) ;
}
