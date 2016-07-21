package com.aebiz.b2b2c.userfront.platadimagerel.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelModel;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelQueryModel;

public interface PlatAdImageRelDAO
		extends
			BaseDAO<PlatAdImageRelModel, PlatAdImageRelQueryModel> {

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
	
	public  void updateCc(String adUuid );
}