package com.hxq.mobile.doctor.content.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.content.service.ContentListService;
import com.hxq.mobile.entity.common.ContentList;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;
@Service("com.hxq.mobile.doctor.content.service.ContentListService")
public class ContentListServiceImpl extends SpringJdbcSimpleEntityService implements ContentListService{

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCacheName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getQueryCacheName() {
		// TODO Auto-generated method stub
		return null;
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
    public  ContentList getContentlistByConditions(String doctorUuid, String contentUuid,String doctorEmail) {
    	StringBuffer sbf = new StringBuffer(1000);
    	sbf.append("SELECT uuid,opeTime,delFlag,doctorUuid,contentUuid,createTime,email,contentCategoryUuid,state")
    	.append(" FROM content_list  where doctorUuid=?")
    	.append(" and contentUuid=? and email=?")
    	.append(" order by createTime desc");
    	 List<Map<String, Object>> re = dao.query(sbf.toString(), new Object[]{doctorUuid,contentUuid,doctorEmail}, null, getCache());
    	 return ObjectUtils.isEmpty(re)?null:SimpleBean2DBHelper.map2Bean(re.get(0), ContentList.class);
    	 
//        StringBuffer hql = new StringBuffer(
//                "from ContentListModel cm where cm.doctorUuid=:doctorUuid " +
//                "and cm.contentUuid=:contentUuid and cm.email=:doctorEmail and SUBSTR(cm.createTime,1,10) =:createTime  order by cm.createTime desc ");
//            
//            String nowTime = DateFormatHelper.getNowTimeStr("yyyy-MM-dd");
//            Query query = this.getH4Session().createQuery(hql.toString());
//            query.setString("doctorUuid", doctorUuid);
//            query.setString("contentUuid", contentUuid);
//            query.setString("doctorEmail", doctorEmail);
//            query.setString("createTime", nowTime);
//            
//            List<ContentListModel> list = query.list();
//            if (list != null && list.size() > 0) {
//                    return list.get(0);
//            } else {
//                    return null;
//            }
    }

}
