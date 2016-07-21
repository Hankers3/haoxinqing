package com.aebiz.b2b2c.websiteoperation.quizcategory.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.vo.QuizCategoryQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class QuizCategoryCacheImpl extends
		BaseMemcachedCache<QuizCategoryModel, QuizCategoryQueryModel>
		implements QuizCategoryDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private QuizCategoryDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public QuizCategoryCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_QUIZCATEGORY_KEY);
	}
	
	@Override
	public void update(QuizCategoryModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_QUIZCATEGORY_KEY + m.getUuid());
	}

    @Override
    public String checkCateGoryName(String categoryName) {
        return dao.checkCateGoryName(categoryName);
    }

    @Override
    public String getCategoryName(String uuid) {
        Object  obj = (Object) mcc.get(WebsiteOperateCacheConstant.WEBSITE_QUIZCATEGORY_KEY + uuid);
        // 如果categoryName为空，则从数据库中查询
        String categoryName ="";
        if(obj !=null ){
        	QuizCategoryModel qm = (QuizCategoryModel) obj;
        	categoryName = qm.getCategoryName();
        }else{
        	
        	QuizCategoryModel qm = dao.getByUuid(uuid);
        	if(qm !=null){
        		this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_QUIZCATEGORY_KEY + uuid, qm);
        		categoryName = qm.getCategoryName();
        	}
        }
         return categoryName;  
    }

	@Override
	public List<QuizCategoryModel> getQuizCategoryModels() {
        List<String> uuids = this.getQuizCategoryModelUuids();
        List<QuizCategoryModel> listM = new ArrayList<QuizCategoryModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
        	 for (String uuid : uuids) {
                 Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_QUIZCATEGORY_KEY + uuid);
                 if (obj != null) {
                	 QuizCategoryModel m = (QuizCategoryModel)obj;
                     listM.add(m);
                 } else {
                     noUuids.add(uuid);
                 }
        	 }
	         if (noUuids.size() > 0) {
                 for (String uuid : noUuids) {
                	 QuizCategoryModel m = dao.getByUuid(uuid);
                     if (m != null) {
                         this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_QUIZCATEGORY_KEY + uuid, m);
                         listM.add(m);
                     }
                 }
	         }
        }
		//return dao.getQuizCategoryModels();
        return listM;
	}

	@Override
	public List<String> getQuizCategoryModelUuids() {
		
		return dao.getQuizCategoryModelUuids();
	}

}
