package com.aebiz.b2b2c.websiteoperation.questionsrel.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelModel;
import com.aebiz.b2b2c.websiteoperation.questionsrel.vo.QuestionsRelQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class QuestionsRelCacheImpl extends
		BaseMemcachedCache<QuestionsRelModel, QuestionsRelQueryModel>
		implements QuestionsRelDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private QuestionsRelDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public QuestionsRelCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_QUESTIONSREL_KEY);
	}
	
	@Override
	public void update(QuestionsRelModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_QUESTIONSREL_KEY + m.getUuid());
	}


    @Override
    public void deleteByQuestionnaireUuid(String questionnaireUuid) {
        dao.deleteByQuestionnaireUuid(questionnaireUuid);
    }
	
       
}
