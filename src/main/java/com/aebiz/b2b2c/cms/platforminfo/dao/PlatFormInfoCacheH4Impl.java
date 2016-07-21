package com.aebiz.b2b2c.cms.platforminfo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.cms.common.CmsCacheConstant;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoQueryModel;
import com.danga.MemCached.MemCachedClient;

public class PlatFormInfoCacheH4Impl extends
		BaseMemcachedCache<PlatFormInfoModel, PlatFormInfoQueryModel> implements
		PlatFormInfoDAO {

	public PlatFormInfoCacheH4Impl() {
		super(CmsCacheConstant.CMS_PLAT_FROM_INFO);
	}

	@Resource(name = CmsCacheConstant.CMS_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private PlatFormInfoDAO plaInfoDAO;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(plaInfoDAO);
	}

	@Override
	public void update(PlatFormInfoModel m) {
		plaInfoDAO.update(m);
		this.mcc.delete(CmsCacheConstant.CMS_PLAT_FROM_INFO + m.getUuid());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUuidsByUserid(String userId) {
		List<String> uuids = (List<String>) mcc
				.get(CmsCacheConstant.CMS_PLAT_FROM_INFO_UUIDS + userId);

		if (uuids == null || uuids.size() < 0) {
			uuids = plaInfoDAO.getUuidsByUserid(userId);
			if (uuids == null || uuids.size() < 0) {
				this.mcc.set(
						CmsCacheConstant.CMS_PLAT_FROM_INFO_UUIDS + userId,
						uuids);
			}
		}
		return uuids;
	}

	@Override
	public List<PlatFormInfoModel> getByUserid(String userId) {
		List<String> uuids = this.getUuidsByUserid(userId);

		List<PlatFormInfoModel> listM = new ArrayList<PlatFormInfoModel>();
		List<String> noUuids = new ArrayList<String>();

		// 循环从缓存中选取 如果有就走缓存 没有从数据库取出 加入缓存
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				Object obj = this.mcc.get(CmsCacheConstant.CMS_PLAT_FROM_INFO
						+ uuid);
				if (obj != null) {
					PlatFormInfoModel pm = (PlatFormInfoModel) obj;
					listM.add(pm);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					PlatFormInfoModel pm = plaInfoDAO.getByUuid(uuid);
					if (pm != null) {
						this.mcc.set(
								CmsCacheConstant.CMS_PLAT_FROM_INFO + uuid, pm);
						listM.add(pm);
					}
				}
			}
		}

		return listM;
	}

	@Override
	public List<PlatFormInfoModel> getByName(String name,String videoType) {
		return plaInfoDAO.getByName(name,videoType);
	}

	@Override
	public String getStartTimeByVidoUuid(String vidoUuid) {
		Object obj = this.mcc.get(CmsCacheConstant.CMS_PLAT_FROM_INFO_TIME
				+ vidoUuid);
		String startTime = "";
		if (obj != null) {
			startTime = (String) obj;
		} else {
			startTime = plaInfoDAO.getStartTimeByVidoUuid(vidoUuid);
			this.mcc.set(CmsCacheConstant.CMS_PLAT_FROM_INFO_TIME + vidoUuid,
					startTime);
		}
		return startTime;
	}

}
