package com.aebiz.b2b2c.websiteoperation.concern.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernModel;
import com.aebiz.b2b2c.websiteoperation.concern.vo.ConcernQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class ConcernCacheImpl extends
		BaseMemcachedCache<ConcernModel, ConcernQueryModel>
		implements ConcernDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ConcernDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ConcernCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_CONCERN_KEY);
	}

	@Override
	public void update(ConcernModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_CONCERN_KEY + m.getUuid());
	}
	
	/**
	 * 获取所有的关注的uuids
	 */
   public List<String> getAllConcernUuids(String customerUuid) {
       List<String>  uuids =  this.dao.getAllConcernUuids(customerUuid);
       return uuids;
   }
	
    @Override
    public List<ConcernModel> getByCustomerUuid(String customerUuid) {
        List<String> uuids = this.getAllConcernUuids(customerUuid);
        List<ConcernModel> listM = new ArrayList<ConcernModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                        Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_CONCERN_KEY + uuid);
                        if (obj != null) {
                            ConcernModel m = (ConcernModel) obj;
                                listM.add(m);
                        } else {
                                noUuids.add(uuid);
                        }
                }
                if (noUuids.size() > 0) {
                        for (String uuid : noUuids) {
                            ConcernModel m = dao.getByUuid(uuid);
                                if (m != null) {
                                        this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_CONCERN_KEY + uuid, m);
                                        listM.add(m);
                                }
                        }
                }
        }
        return listM;
    }

   

    @Override
    public int getConcernType(String doctorId, String customerId) {
        return dao.getConcernType(doctorId, customerId);
    }

    @Override
    public int getConcernNumByDoctorId(String doctorId) {
        return dao.getConcernNumByDoctorId(doctorId);
    }

    @Override
    public ConcernModel getByCustomerAndDoctorUuid(String customerUuid, String doctorUuid) {
        return dao.getByCustomerAndDoctorUuid(customerUuid, doctorUuid);
    }
	
}
