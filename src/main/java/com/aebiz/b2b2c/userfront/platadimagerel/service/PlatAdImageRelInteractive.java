package com.aebiz.b2b2c.userfront.platadimagerel.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelModel;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelQueryModel;

/**
 * 广告关联图片,不用管图片的效果,只需关联上图片即可,图片的效果由广告组件来做
 *
 * @author tangyunkai
 *
 * @date 2015年1月6日 下午8:36:32 
 *
 */
public interface PlatAdImageRelInteractive
		extends
			BaseService<PlatAdImageRelModel, PlatAdImageRelQueryModel> {

	/**
	 * 根据广告的uuid获取该广告关联的所有关联关系的uuid
	 * @param adUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getPlatAdImageRelModelUuidsByAdUuid(String adUuid);
	
	/**
	 * 根据广告的uuid获取该广告关联的所有的图片
	 * @param adUuid
	 * @return 
	 * List<PlatAdImageRelModel>
	 */
	public List<PlatAdImageRelModel> getPlatAdImageRelModelsByAdUuid(String adUuid);
}
