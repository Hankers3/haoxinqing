package com.aebiz.b2b2c.cms.contentlist.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListModel;
import com.aebiz.b2b2c.cms.contentlist.vo.ContentListQueryModel;

@Repository
public class ContentListH4Impl extends
		BaseH4Impl<ContentListModel, ContentListQueryModel> implements
		ContentListDAO {

	// 重写父类方法拼接sql语句
	@Override
	protected String getMultiModel() {

		return " , ContentModel as c , ServicestaffModel as s ";
	}

	@Override
	protected String getAppendHql(ContentListQueryModel qm) {

		StringBuffer hql = new StringBuffer(
				" and o.doctorUuid = s.uuid  and o.contentUuid = c.uuid ");
		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			hql.append(" and s.realName like:doctorName ");
		}
		if (!StringUtil.isEmpty(qm.getContentName())) {
			hql.append(" and c.contentTitle like:contentName ");
		}

		// 拼接时间范围的sql语句
		if (!StringUtil.isEmpty(qm.getBeginTime())) {
			hql.append(" and o.createTime >=:beginTime ");
		}
		if (!StringUtil.isEmpty(qm.getEndTime())) {
			hql.append(" and o.createTime <=:endTime ");
		}
		//System.out.println(hql.toString() + super.getAppendHql(qm));
		return hql.toString() + super.getAppendHql(qm);

	}

	// 设置参数的值
	@Override
	protected void setAppendHqlValue(ContentListQueryModel qm, Query q) {

		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			q.setString("doctorName", "%" + qm.getDoctorName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getContentName())) {
			q.setString("contentName", "%" + qm.getContentName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getBeginTime())) {
			q.setString("beginTime", qm.getBeginTime());
		}
		if (!StringUtil.isEmpty(qm.getBeginTime())) {
			q.setString("endTime", qm.getEndTime());
		}
		super.setAppendHqlValue(qm, q);
	}
	/**
         * 
         * @Description: (多条件查询索取列表)    
         * @author XP  
         * @param doctorUuid
         * @param contentUuid
         * @param doctorEmail
         * @return
         * @date 2016-1-20 下午12:46:51
         */
        @Override
        public ContentListModel getContentlistByConditions(String doctorUuid, String contentUuid,
                String doctorEmail) {
            StringBuffer hql = new StringBuffer(
                    "from ContentListModel cm where cm.doctorUuid=:doctorUuid " +
                    "and cm.contentUuid=:contentUuid and cm.email=:doctorEmail and SUBSTR(cm.createTime,1,10) =:createTime  order by cm.createTime desc ");
                
                String nowTime = DateFormatHelper.getNowTimeStr("yyyy-MM-dd");
                Query query = this.getH4Session().createQuery(hql.toString());
                query.setString("doctorUuid", doctorUuid);
                query.setString("contentUuid", contentUuid);
                query.setString("doctorEmail", doctorEmail);
                query.setString("createTime", nowTime);
                
                List<ContentListModel> list = query.list();
                if (list != null && list.size() > 0) {
                        return list.get(0);
                } else {
                        return null;
                }
        }

}
