package com.aebiz.b2b2c.websiteoperation.options.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.favorite.vo.FavoriteModel;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsQueryModel;
import com.aebiz.b2b2c.websiteoperation.questions.vo.QuestionsModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class OptionsCacheImpl extends
		BaseMemcachedCache<OptionsModel, OptionsQueryModel>
		implements OptionsDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private OptionsDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public OptionsCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_OPTIONS_KEY);
	}
	@Override
	public void update(OptionsModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_OPTIONS_KEY + m.getUuid());
	}
	
	/**
	 * 根据医生的id获取收藏的uuids
	 */
	public List<String> getAllOptionsModelUuids(String questionUuid) {
	    List<String>  uuids = this.dao.getAllOptionsModelUuids(questionUuid);
        return uuids;
    }
    
       
    

    @Override
    public void removePreOptions(String questionUuid) {
        dao.removePreOptions(questionUuid);
        List<String> uuids = this.getAllOptionsModelUuids(questionUuid);
        if (uuids != null && uuids.size() > 0) {
        	  for (String uuid : uuids) {
        		  this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_OPTIONS_KEY + uuid);
        	  }
        }
    }

    @Override
    public List<OptionsModel> getOptionsByQuestionUuid(String questionUuid) {
        List<String> uuids = this.getAllOptionsModelUuids(questionUuid);
        List<OptionsModel> listM = new ArrayList<OptionsModel>();
        List<String> noUuids = new ArrayList<String>();
        if (uuids != null && uuids.size() > 0) {
                for (String uuid : uuids) {
                        Object obj = this.mcc.get(WebsiteOperateCacheConstant.WEBSITE_OPTIONS_KEY + uuid);
                        if (obj != null) {
                            OptionsModel m = (OptionsModel)obj;
                                listM.add(m);
                        } else {
                                noUuids.add(uuid);
                        }
                }
                if (noUuids.size() > 0) {
                        for (String uuid : noUuids) {
                            OptionsModel m = dao.getByUuid(uuid);
                                if (m != null) {
                                        this.mcc.set(WebsiteOperateCacheConstant.WEBSITE_OPTIONS_KEY + uuid, m);
                                        listM.add(m);
                                }
                        }
                }
        }
        return listM;
    }

    @Override
    public void deleteByQuestionUuid(String questionUuid) {
        dao.deleteByQuestionUuid(questionUuid);
        List<String> uuids = this.getAllOptionsModelUuids(questionUuid);
        if (uuids != null && uuids.size() > 0) {
        	  for (String uuid : uuids) {
        		  this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_OPTIONS_KEY + uuid);
        	  }
        }
    }
	
}
