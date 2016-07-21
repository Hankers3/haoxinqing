package com.aebiz.b2b2c.visitprecept.casegroup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupModel;
import com.aebiz.b2b2c.visitprecept.casegroup.vo.CaseGroupQueryModel;
import com.aebiz.b2b2c.visitprecept.common.VisitpreceptCacheConstant;
import com.danga.MemCached.MemCachedClient;

/**
 * @author szr
 */
@Primary
@Repository
public class CaseGroupCacheImpl extends
		BaseMemcachedCache<CaseGroupModel, CaseGroupQueryModel> implements
		CaseGroupDAO {

	@Resource(name = VisitpreceptCacheConstant.VISITPRECEPT_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private CaseGroupDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public CaseGroupCacheImpl() {
		super(VisitpreceptCacheConstant.VISITPRECEPT_CASEGROUP_KEY);
	}

	@Override
	public void update(CaseGroupModel m) {
		dao.update(m);
		this.mcc.delete(VisitpreceptCacheConstant.VISITPRECEPT_CASEGROUP_KEY
				+ m.getUuid());
	}

	@Override
	public List<CaseGroupModel> getByDoctorUuid(String doctorUuid) {
		// 返回对象集合
		List<CaseGroupModel> caseGroupList = new ArrayList<CaseGroupModel>();
		// 所以uuids
		List<String> uuids = this.getUuidsByDoctorUuid(doctorUuid);
		// 循环
		if(uuids !=null && uuids.size()>0){
			for (String uuid : uuids) {
				// Model对象
				CaseGroupModel caseGroupModel = new CaseGroupModel();
				// 从缓存中取值
				Object objModel = this.mcc
						.get(VisitpreceptCacheConstant.VISITPRECEPT_CASEGROUP_KEY
								+ uuid);
				if (objModel != null) {
					caseGroupModel = (CaseGroupModel) objModel;
				} else {
					// 获得model
					caseGroupModel = dao.getByUuid(uuid);
					if (caseGroupModel != null) {
						// model放入缓存
						this.mcc.set(
								VisitpreceptCacheConstant.VISITPRECEPT_CASEGROUP_KEY
										+ uuid, caseGroupModel);
					}
				}
				// 将model存入返回值
				caseGroupList.add(caseGroupModel);
			}
		}
		// 返回
		return caseGroupList;
	}

	@Override
	public List<String> getUuidsByDoctorUuid(String doctorUuid) {
		// uuids集合
		List<String> uuids = dao.getUuidsByDoctorUuid(doctorUuid);
		return uuids;
	}

}
