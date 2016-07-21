package com.aebiz.b2b2c.websiteoperation.favorite.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteQueryModel;

public interface FavoriteService extends
		BaseService<FavoriteModel, FavoriteQueryModel> {
	/**
	 * 根据用户编号获取 用户收藏列表
	 * 
	 * @param doctorid
	 * @return
	 */
	public List<FavoriteModel> getFavoriteModelListByCustomerUuid(
			String doctorid);

	/**
	 * 删除收藏
	 * 
	 * @param favoriteUuid
	 */
	public void deleteFavoriteByUuid(String favoriteUuid);

	/**
	 * 查看医生是否收藏该
	 * 
	 * @param favoriteUuid
	 */
	public int getStoreTypeByDoctorUuidAndContenUuid(String doctorUuid,
			String uuid);

	/**
	 * 通过视频主键查看医生的收藏数量
	 * 
	 * @param vedioUuid
	 * @return
	 */
	public int getNumByVedioUuid(String vedioUuid);

	/**
	 * 查看医生是否收藏该视频
	 * 
	 * @param favoriteUuid
	 */
	public int getcolVideoByDoctorUuidAndVideoUuid(String doctorUuid,
			String videoUuid);

	/**
	 * 删除通过医生id和视频id
	 * 
	 * 
	 */
	public void deleteByDoctorUuidAndVideouuid(String doctorUuid,
			String videoUuid);

	/**
	 * 根据患者的id和收藏状态获取患者的所有的收藏
	 * 
	 * @param customerUuid
	 * @param string
	 * @return
	 */
	public List<FavoriteModel> getFavoriteModelListByCustomerUuidAndState(
			String customerUuid, String string);

	/**
	 * 根据id和状态获取收藏的人数
	 * 
	 * @param contentUuid
	 * @param type
	 * @return
	 */
	public int getNumByContentUuid(String contentUuid, String type);

	/**
	 * 关注状态 根据患者id和文章id查关注状态
	 * 
	 * @param customerUuid
	 * @param contextUuid
	 * @return
	 */
	public String getUuidByCustomerUuidAndContextUuid(String customerUuid,
			String contextUuid);
	/**
	 * 
	 * @Description: (根据医生id和视频的查看关注状态)    
	 * @author XP  
	 * @param doctorid
	 * @param newsid
	 * @return
	 * @date 2015-12-31 上午11:40:57
	 */
	public int getcolVideoByDoctorUuidAndContenUuid(String doctorid, String newsid);
	/**
	 * 
	 * @Description: (根据医生和视频的ID获取收藏的uuid)    
	 * @author XP  
	 * @param doctorUuid
	 * @param videoUuid
	 * @return
	 * @date 2015-12-31 下午5:19:09
	 */
	public String getUuidByCustomerUuidAndVideoUuid(String doctorUuid, String videoUuid);
	/**
	 * 
	 * @Description: (获取收藏列表的数据)    
	 * @author XP  
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 * @date 2016-1-4 下午5:06:55
	 */
	public List<FavoriteModel> getByConditionq(FavoriteQueryModel qm, int start,int pageShow);
}
