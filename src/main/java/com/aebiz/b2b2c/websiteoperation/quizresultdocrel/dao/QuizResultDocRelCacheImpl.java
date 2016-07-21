package com.aebiz.b2b2c.websiteoperation.quizresultdocrel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class QuizResultDocRelCacheImpl extends
		BaseMemcachedCache<QuizResultDocRelModel, QuizResultDocRelQueryModel>
		implements QuizResultDocRelDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private QuizResultDocRelDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public QuizResultDocRelCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULTDOCREL_KEY);
	}
	
	@Override
	public void update(QuizResultDocRelModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULTDOCREL_KEY + m.getUuid());
	}
	
	/**
     * 根据医生的id和问题结果的id获得所有关联咨询结果的uuid
     * @param serviceStaffInfoId
     * @param quizResultId
     * @return
     */
	public List<String> getAllQuizResultDocRelModelUuids(String serviceStaffInfoId, String quizResultId) {
		List<String>  uuids  = this.dao.getAllQuizResultDocRelModelUuids(serviceStaffInfoId,quizResultId);
		return uuids;
    }

    @Override
    public List<QuizResultDocRelModel> getListByserviceStaffInfoIdAndquizResultId(
            String serviceStaffInfoId, String quizResultId) {
        List<String> uuids = this.getAllQuizResultDocRelModelUuids(serviceStaffInfoId,quizResultId);
        List<QuizResultDocRelModel> listM = new ArrayList<QuizResultDocRelModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                    Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULTDOCREL_KEY + uuid);
                    if (obj != null) {
                        QuizResultDocRelModel m = (QuizResultDocRelModel)obj;
                        listM.add(m);
                    } else {
                        noUuids.add(uuid);
                    }
                }
                if (noUuids.size() > 0) {
                    for (String uuid : noUuids) {
                        QuizResultDocRelModel m = dao.getByUuid(uuid);
                        if (m != null) {
                            this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULTDOCREL_KEY + uuid, m);
                            listM.add(m);
                        }
                    }
                }
        }
        return listM;
    }

    
    public List<String> getAllQuizResultDocRelModelUuidsByQuizCategoryUuid(String quizCategoryUuid) {
        List<String>  uuids  = this.dao.getAllQuizResultDocRelModelUuidsByQuizCategoryUuid(quizCategoryUuid);

        return uuids;
    }
    
    @Override
    public List<QuizResultDocRelModel> getListByQuizCategoryUuid(String quizCategoryUuid) {
        List<QuizResultDocRelModel> listM =dao.getListByQuizCategoryUuid(quizCategoryUuid);
        
        return listM;
    }


        
      


       
}
