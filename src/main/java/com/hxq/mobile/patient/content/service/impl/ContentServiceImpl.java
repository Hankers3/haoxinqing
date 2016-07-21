package com.hxq.mobile.patient.content.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.hxq.mobile.patient.content.service.ContentService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.patient.content.service.ContentService")
public class ContentServiceImpl extends SpringJdbcSimpleEntityService implements ContentService{

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "ContentService";}

	/**
	 * 返回咨询的list
	 */
	@Override
	public Object[] selectAllHeartList(String contentCategoryUuid,Integer pageCount, Integer pageNo) {
	
        StringBuffer sbf = new StringBuffer(1000);
		List<Object> args = new ArrayList<Object>();
		args.add(contentCategoryUuid);
		
		sbf.append(" from content where contentCategoryUuid= ? and state='1' ");
		String strWhere = sbf.toString();
		
		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();
		
		sbf.delete(0, sbf.length());
		String strQuery = sbf.append("select uuid,contentTitle,createTime,author,provenance,contentNote,image,url")
		.append(strWhere).append(" order by createTime desc").toString();
		
        Object[] params = args.isEmpty() ? null :args.toArray();
		
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[]{lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows()}; 
	}
	@Override
	public List<Map<String,Object>> selectAllContentList(String contentCategoryUuid) {
	
		StringBuffer sql = new StringBuffer("select uuid,contentTitle,createTime,author,provenance,contentNote,image,url from content");
		sql.append(" where state='1' ");
		
		Object [] data = null;
		
		if(!StringUtil.isEmpty(contentCategoryUuid)){
			sql.append(" and contentCategoryUuid=?");
			data = new Object[]{contentCategoryUuid};
		}
		
		sql.append(" order by createTime desc ");
		List<Map<String, Object>> staffReturn = dao.query(sql.toString(), data, null, getCache());	
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn);
	
	}
	

	/**
	 * 根据咨询id,返回信息
	 */
	@Override
	public Map<String,Object> selectContentByUuid(String contentUuid) {
	
		StringBuffer sql = new StringBuffer("select uuid,contentTitle,createTime,author,provenance,contentNote,image,url from content");
		sql.append(" where state='1' ");
		
		Object [] data = null;
		
		if(!StringUtil.isEmpty(contentUuid)){
			sql.append(" and uuid=?");
			data = new Object[]{contentUuid};
		}
		
		List<Map<String, Object>> staffReturn = dao.query(sql.toString(), data, null, getCache());	
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	
	}
}
