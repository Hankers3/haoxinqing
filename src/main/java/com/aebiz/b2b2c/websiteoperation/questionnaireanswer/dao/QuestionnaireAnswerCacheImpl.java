package com.aebiz.b2b2c.websiteoperation.questionnaireanswer.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerModel;
import com.aebiz.b2b2c.websiteoperation.questionnaireanswer.vo.QuestionnaireAnswerQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class QuestionnaireAnswerCacheImpl extends
		BaseMemcachedCache<QuestionnaireAnswerModel, QuestionnaireAnswerQueryModel>
		implements QuestionnaireAnswerDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private QuestionnaireAnswerDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public QuestionnaireAnswerCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_QUESTIONNAIREANSWER_KEY);
	}
	@Override
	public void update(QuestionnaireAnswerModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_QUESTIONNAIREANSWER_KEY + m.getUuid());
	}
	
	
	
}
