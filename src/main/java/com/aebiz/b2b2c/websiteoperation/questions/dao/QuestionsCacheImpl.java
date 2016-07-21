package com.aebiz.b2b2c.websiteoperation.questions.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class QuestionsCacheImpl extends
		BaseMemcachedCache<QuestionsModel, QuestionsQueryModel>
		implements QuestionsDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private QuestionsDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	@Override
	public void update(QuestionsModel m) {
		dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_QUESTIONS_KEY+m.getUuid());
	}
	public QuestionsCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_QUESTIONS_KEY);
	}
	
	/**
     * 根据试卷的uuid获取questions的所有的uuids
     * @param questionnaireUuid
     * @return
     */
    public List<String> getAllQuestionsModelUuids(String questionnaireUuid) {
        List<String>  uuids =  this.dao.getAllQuestionsModelUuids(questionnaireUuid);
        return uuids;
    }
        
    /**
     * 通过试卷的Uuid获得所有试题的列表
     * 
     * @param questionnaireUuid
     * @return
     */
    @Override
    public List<QuestionsModel> getQuestionsByQuestionnaireUuid(String questionnaireUuid) {
        List<String> uuids = this.getAllQuestionsModelUuids(questionnaireUuid);
        List<QuestionsModel> listM = new ArrayList<QuestionsModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                        Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_QUESTIONS_KEY + uuid);
                        if (obj != null) {
                            QuestionsModel m = (QuestionsModel)obj;
                                listM.add(m);
                        } else {
                                noUuids.add(uuid);
                        }
                }
                if (noUuids.size() > 0) {
                        for (String uuid : noUuids) {
                            QuestionsModel m = dao.getByUuid(uuid);
                                if (m != null) {
                                        this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_QUESTIONS_KEY + uuid, m);
                                        listM.add(m);
                                }
                        }
                }
        }
        return listM;
    }

   

    @Override
    public int getQuestionsCountByNotSelected(String[] choosedQuestionIds) {
        return dao.getQuestionsCountByNotSelected(choosedQuestionIds);
    }

    @Override
    public List<QuestionsModel> getQuestionsByNotSelected(int iDisplayStart, int iDisplayLength,
            String[] choosedQuestionIds) {
        return dao.getQuestionsByNotSelected(iDisplayStart, iDisplayLength, choosedQuestionIds);
    }

    
    
    /**
     * 根据测试分类的id和测试分类的状态获取questions的所有的uuids
     * @param quizCategoryUuid
     * @param state
     * @return
     */
    public List<String> getAllQuestionsModelUuidsByQuizCategoryUuidAndState(String quizCategoryUuid,
            String state) {
    		List<String>   uuids = this.dao.getAllQuestionsModelUuidsByQuizCategoryUuidAndState(quizCategoryUuid,state);
           return uuids;
    }
    
    @Override
    public List<QuestionsModel> getQuestionsByQuizCategoryUuid(String quizCategoryUuid, String state) {
    	if(StringUtil.isEmpty(state)){
    		state="1";
    	}
        List<String> uuids = this.getAllQuestionsModelUuidsByQuizCategoryUuidAndState(quizCategoryUuid,state);
        List<QuestionsModel> listM = new ArrayList<QuestionsModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                        Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_QUESTIONS_KEY + uuid);
                        if (obj != null) {
                            QuestionsModel m = (QuestionsModel)obj;
                                listM.add(m);
                        } else {
                                noUuids.add(uuid);
                        }
                }
                if (noUuids.size() > 0) {
                        for (String uuid : noUuids) {
                            QuestionsModel m = dao.getByUuid(uuid);
                                if (m != null) {
                                        this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_QUESTIONS_KEY + uuid, m);
                                        listM.add(m);
                                }
                        }
                }
        }
        return listM;
    }
       
}
