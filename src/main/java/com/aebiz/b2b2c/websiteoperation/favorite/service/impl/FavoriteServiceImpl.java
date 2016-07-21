package com.aebiz.b2b2c.websiteoperation.favorite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.websiteoperation.favorite.service.FavoriteService;
import com.aebiz.b2b2c.websiteoperation.favorite.dao.FavoriteDAO;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteQueryModel;

@Service
@Transactional
public class FavoriteServiceImpl extends
		BaseServiceImpl<FavoriteModel, FavoriteQueryModel> implements
		FavoriteService {
	private FavoriteDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(FavoriteDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(FavoriteModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(FavoriteModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(FavoriteModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据用户编号获取 用户收藏列表
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<FavoriteModel> getFavoriteModelListByCustomerUuid(
			String doctorid) {
		return myDao.getFavoriteModelListByCustomerUuid(doctorid);
	}

	/**
	 * 删除收藏
	 * 
	 * @param favoriteUuid
	 */
	@Override
	public void deleteFavoriteByUuid(String favoriteUuid) {
		myDao.deleteFavoriteByUuid(favoriteUuid);
	}

	/**
	 * 查看医生是否收藏该
	 * 
	 * @param favoriteUuid
	 */
	@Override
	public int getStoreTypeByDoctorUuidAndContenUuid(String doctorUuid,
			String uuid) {
		return myDao.getStoreTypeByDoctorUuidAndContenUuid(doctorUuid, uuid);
	}

	/**
	 * 通过视频主键查看医生的收藏数量
	 * 
	 * @param vedioUuid
	 * @return
	 */
	@Override
	public int getNumByVedioUuid(String vedioUuid) {
		int num = myDao.getNumByVedioUuid(vedioUuid);
		if (num < 0) {
			num = 0;
		}
		return num;
	}

	/**
	 * 查看医生是否收藏该视频
	 * 
	 * @param favoriteUuid
	 */
	@Override
	public int getcolVideoByDoctorUuidAndVideoUuid(String doctorUuid,
			String videoUuid) {
		return myDao
				.getcolVideoByDoctorUuidAndContenUuid(doctorUuid, videoUuid);
	}

	@Override
	public void deleteByDoctorUuidAndVideouuid(String doctorUuid,
			String videoUuid) {
		myDao.deleteByDoctorUuidAndVideouuid(doctorUuid, videoUuid);
	}

	/**
	 * 根据患者的id和收藏状态获取患者的所有的收藏
	 * 
	 * @param customerUuid
	 * @param string
	 * @return
	 */
	@Override
	public List<FavoriteModel> getFavoriteModelListByCustomerUuidAndState(
			String customerUuid, String state) {
		return myDao.getFavoriteModelListByCustomerUuidAndState(customerUuid,
				state);
	}

	/**
	 * 根据id和状态获取收藏关注的数量
	 */
	@Override
	public int getNumByContentUuid(String contentUuid, String type) {
		return myDao.getNumByContentUuid(contentUuid, type);
	}

	/**
	 * 关注状态 根据患者id和文章id查关注状态
	 * 
	 * @param customerUuid
	 * @param contextUuid
	 * @return
	 */
	@Override
	public String getUuidByCustomerUuidAndContextUuid(String customerUuid,
			String contextUuid) {
		return myDao.getUuidByCustomerUuidAndContextUuid(customerUuid,
				contextUuid);
	}

	/**
	 * 
	 * @Description: (根据医生的id获取关注的状态)
	 * @author XP
	 * @param doctorid
	 * @param newsid
	 * @return
	 * @date 2015-12-31 上午11:41:51
	 */
	@Override
	public int getcolVideoByDoctorUuidAndContenUuid(String doctorid,
			String newsid) {
		return myDao.getcolVideoByDoctorUuidAndContenUuid(doctorid, newsid);
	}

	/**
	 * 
	 * @Description: (根据医生和视频的ID获取收藏的uuid)
	 * @author XP
	 * @param doctorUuid
	 * @param videoUuid
	 * @return
	 * @date 2015-12-31 下午5:19:09
	 */
	@Override
	public String getUuidByCustomerUuidAndVideoUuid(String doctorUuid,
			String videoUuid) {
		return myDao.getUuidByCustomerUuidAndVideoUuid(doctorUuid, videoUuid);
	}
	
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
        public List<FavoriteModel> getByConditionq(FavoriteQueryModel qm, int start,int pageShow) {
            return myDao.getByConditionq(qm, start, pageShow);
    }

}