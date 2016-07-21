package com.aebiz.b2b2c.websiteoperation.questionnaire.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireModel;
import com.aebiz.b2b2c.websiteoperation.questionnaire.vo.QuestionnaireQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class QuestionnaireCacheImpl extends
		BaseMemcachedCache<QuestionnaireModel, QuestionnaireQueryModel>
		implements QuestionnaireDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private QuestionnaireDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public QuestionnaireCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_OPERATE_QUESTIONNAIRE);
	}
	
	@Override
	public void update(QuestionnaireModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_OPERATE_QUESTIONNAIRE + m.getUuid());
	}
	
}
