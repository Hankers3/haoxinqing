package com.aebiz.b2b2c.websiteoperation.quizresult.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultModel;
import com.aebiz.b2b2c.websiteoperation.quizresult.vo.QuizResultQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class QuizResultCacheImpl extends
		BaseMemcachedCache<QuizResultModel, QuizResultQueryModel>
		implements QuizResultDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private QuizResultDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public QuizResultCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULT_KEY);
	}
	
	@Override
	public void update(QuizResultModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULT_KEY + m.getUuid());
	}
	
	/**
     * 根据提问分类的uuid获取咨询结果的uuids
     * @param quizCategoryUuid
     * @return
     */
	public List<String> getAllQuizCategoryModelUuids(String quizCategoryUuid) {
	    List<String> uuids = this.dao.getAllQuizCategoryModelUuids(quizCategoryUuid);
        return uuids;
	}
	
    @Override
    public List<QuizResultModel> getListByQuizCategoryUuid(String quizCategoryUuid) {
        List<String> uuids = this.getAllQuizCategoryModelUuids(quizCategoryUuid);
        List<QuizResultModel> listM = new ArrayList<QuizResultModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
            for (String uuid : uuids) {
                    Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULT_KEY + uuid);
                    if (obj != null) {
                        QuizResultModel m = (QuizResultModel)obj;
                            listM.add(m);
                    } else {
                            noUuids.add(uuid);
                    }
            }
            if (noUuids.size() > 0) {
                for (String uuid : noUuids) {
                    QuizResultModel m = dao.getByUuid(uuid);
                    if (m != null) {
                            this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULT_KEY + uuid, m);
                            listM.add(m);
                    }
                }
            }
        }
        return listM;
    }
	/**
	 * 
	 * 根据测试分类ID和分数 获取测试分类结果
	 * @param quizCategoryUuid
	 * @param score
	 * @return
	 */
	@Override
	public QuizResultModel getByQuizCategoryUuid(String quizCategoryUuid,
			String score) {
		
		return dao.getByQuizCategoryUuid(quizCategoryUuid, score);
	}

      


       
}
