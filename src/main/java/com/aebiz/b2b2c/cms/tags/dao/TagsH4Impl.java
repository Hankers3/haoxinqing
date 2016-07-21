package com.aebiz.b2b2c.cms.tags.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.tags.vo.TagsModel;
import com.aebiz.b2b2c.cms.tags.vo.TagsQueryModel;

@Repository
public class TagsH4Impl extends BaseH4Impl<TagsModel, TagsQueryModel> implements
		TagsDAO {

	@Override
	protected String getAppendHql(TagsQueryModel qm) {
		String hql = " ";
		if (qm != null && qm.getStagsUuid() != null
				&& qm.getStagsUuid().size() > 0) {
			List<String> existStagsUuid = qm.getStagsUuid();
			hql += " and o.uuid not in (";
			for (int i = 0; i < existStagsUuid.size(); i++) {
				hql += "'" + existStagsUuid.get(i) + "'";
				if (i != existStagsUuid.size() - 1) {
					hql += ",";
				}
			}
			hql += ") ";
		}
		hql += super.getAppendHql(qm);
		return hql;
	}

	/**
	 * 
	 * 通过标签分类ID获取该分类下关联的标签
	 * 
	 * @param categoryUuid
	 * @return
	 */
	public List<TagsModel> getTagsByCategoryUuid(String categoryUuid) {
		StringBuffer hql = new StringBuffer(
				"select tm from TagsModel tm,TagsCategoyRelModel tcrm");
		hql.append(" where tm.uuid=tcrm.tagUuid and tcrm.categoryUuid=:categoryUuid");
		
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("categoryUuid", categoryUuid);
		
		return q.list();
	}
	
	/**
	 * 通过编号获取标签名
	 * @param uuid
	 * @return
	 */
	public String  getTagNameByUuid(String uuid){
		if(StringUtil.isEmpty(uuid)){
			return "";
		}
		StringBuffer hql = new StringBuffer("select tm.tagName from TagsModel tm ");
		hql.append(" where  tm.uuid =:uuid ");
		
		Query q = getH4Session().createQuery(hql.toString());
		q.setParameter("uuid", uuid);
		
		List<String> list = q.list();
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		
		return "";
		
	}
	
	/**
	 * 
	 * 通过医生已有标签 获取未关联的标签
	 * 
	 * @param categoryUuid
	 * @return
	 */
	public List<TagsModel> getTagsByDoctorTag(String doctorTag){
		boolean flag = true;
		if(StringUtil.isEmpty(doctorTag)){
			flag =false;
		}
		
		StringBuffer hql = new StringBuffer(" ");
		if(flag){
			 hql.append(" from TagsModel as o where o.uuid not in(:uuids) ");
		}else{
			 hql.append(" from TagsModel as o  ");
		}
		
		Query q = this.getH4Session().createQuery(hql.toString());
		
		if(flag){
			String[] uuids  = doctorTag.split(";");
			q.setParameterList("uuids", uuids);
		}
		List<TagsModel> tags = q.list();
		if(tags !=null && tags.size()>0){
			return tags;
		}
		
		return null;
		
	}
	
	
	
	
	@Override
	public List<String> getTagsUuidsByCategoryUuid(List<String> categoryUuid) {
		StringBuffer hql = new StringBuffer(
				"select tm.uuid from TagsModel tm,TagsCategoyRelModel tcrm");
		hql.append(" where tm.uuid=tcrm.tagUuid and tcrm.introduce=1 and tcrm.categoryUuid in :categoryUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setParameterList("categoryUuid", categoryUuid);

		return q.list();

	}
	/**
         * 
         * @Description: (通过标签id获取标签对象)    
         * @author XP  
         * @param consultTag
         * @return
         * @date 2016-1-21 下午2:54:07
         */
        @Override
        public List<TagsModel> getTagListByConsultTag(String consultTag) {
            if(StringUtil.isEmpty(consultTag)){
                return null;
            }
            StringBuffer hql = new StringBuffer(" ");
                     hql.append(" from TagsModel as o where o.uuid  in (:uuids) ");
            
            Query q = this.getH4Session().createQuery(hql.toString());
            
                    String[] uuids  = consultTag.split(";");
                    q.setParameterList("uuids", uuids);
                    List<TagsModel> tags = q.list();
                    if(tags !=null && tags.size()>0){
                    return tags;
                    }
            
            return null;
        }
}
