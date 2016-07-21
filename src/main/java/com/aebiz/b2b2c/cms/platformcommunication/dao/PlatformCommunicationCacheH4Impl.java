package com.aebiz.b2b2c.cms.platformcommunication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.cms.common.CmsCacheConstant;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationModel;
import com.aebiz.b2b2c.cms.platformcommunication.vo.PlatformCommunicationQueryModel;
import com.danga.MemCached.MemCachedClient;

public class PlatformCommunicationCacheH4Impl
		extends
		BaseMemcachedCache<PlatformCommunicationModel, PlatformCommunicationQueryModel>
		implements PlatformCommunicationDAO {

	public PlatformCommunicationCacheH4Impl() {
		super(CmsCacheConstant.CMS_PLAT_FROM_COMM);
	}

	@Resource(name = CmsCacheConstant.CMS_MEM_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private PlatformCommunicationDAO platformCommDAO;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(platformCommDAO);
	}

	@Override
	public void update(PlatformCommunicationModel m) {
		platformCommDAO.update(m);
		this.mcc.delete(CmsCacheConstant.CMS_PLAT_FROM_COMM + m.getUuid());
	}

	@Override
	public String updateState(String uuid, String state) {
		platformCommDAO.updateState(uuid, state);
		this.mcc.delete(CmsCacheConstant.CMS_PLAT_FROM_COMM + uuid);

		return "success";
	}

	/**
	 * 通过视频id得到视频中医生的评论信息
	 * 
	 * @param videoUuid
	 * @return
	 */
	@Override
	public List<PlatformCommunicationModel> getByVideoUuid(String videoUuid) {

		// 获取缓存中存在的uuid
		List<String> uuids = (List<String>) this.mcc
				.get(CmsCacheConstant.CMS_PLAT_FROM_COMM_UUIDS + videoUuid);

		List<PlatformCommunicationModel> listM = new ArrayList<PlatformCommunicationModel>();
		List<String> noUuids = new ArrayList<String>();
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				// 根据当前uuid 从缓存中获取对象
				Object obj = this.mcc.get(CmsCacheConstant.CMS_PLAT_FROM_COMM
						+ uuid);
				if (obj != null) {
					PlatformCommunicationModel pcm = (PlatformCommunicationModel) obj;
					listM.add(pcm);
				} else {
					noUuids.add(uuid);
				}
			}
			if (noUuids.size() > 0) {
				for (String uuid : noUuids) {
					PlatformCommunicationModel pcm = this.platformCommDAO
							.getByUuid(uuid);
					if (pcm != null) {
						this.mcc.set(
								CmsCacheConstant.CMS_PLAT_FROM_COMM + uuid, pcm);
						listM.add(pcm);
					}
				}
			}

		}
		return listM;
	}

	/**
	 * 通过视频id得到视频评论信息Uuids
	 * 
	 * @param videoUuid
	 * @return
	 */
	@Override
	public List<String> getUuidsByVideoUuid(String videoUuid) {
		List<String> uuids = (List<String>) this.mcc
				.get(CmsCacheConstant.CMS_PLAT_FROM_COMM_UUIDS + videoUuid);

		if (uuids == null || uuids.size() < 0) {
			uuids = this.platformCommDAO.getUuidsByVideoUuid(videoUuid);
			if (uuids == null || uuids.size() < 0) {
				this.mcc.set(CmsCacheConstant.CMS_PLAT_FROM_COMM_UUIDS
						+ videoUuid, uuids);
			}
		}
		return uuids;
	}

}
