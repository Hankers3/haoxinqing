package com.hxq.mobile.doctor.content.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.content.service.ContentService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.content.service.ContentService")
public class ContentServiceImpl extends SpringJdbcSimpleEntityService implements ContentService{

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}

	/**
	 * 获取资讯 & 诊断标准的列表 
	 *  需要分页
	 */
	@Override
	public Object[] selectByContentCategoryUuid(String contentCategoryUuid, Integer pageCount,Integer pageNo) {
		StringBuffer sbf = new StringBuffer(1000);
		List<Object> args = new ArrayList<Object>();
		args.add(contentCategoryUuid);
		
		sbf.append(" from content where contentCategoryUuid= ? and state='1' ");
		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();
		
		sbf.delete(0, sbf.length());
		String strQuery = sbf.append("select contentType,image,uuid,contentType,url,contentTitle,createTime,author,provenance")
		.append(strWhere).append(" order by createTime desc").toString();
		
		Object[] params = args.isEmpty() ? null :args.toArray();
		
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[]{lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows()};  
	}

	/**
	 * 获取通过条件筛选后的资讯列表
	 * @param contentCategoryUuid
	 * @return
	 */
	public Object[] selectScreenByContentCategoryUuid(
			String contentCategoryUuid, String contentName, String illnessId,Integer pageCount, Integer pageNo) {
		StringBuffer sql=new StringBuffer(1000);
		List<Object> args = new ArrayList<Object>();
		
		sql.append("from content where state='1'");
		// 分类编号
		if (!ObjectUtils.isEmpty(contentCategoryUuid)) {
			sql.append(" and contentCategoryUuid=?");
			args.add(contentCategoryUuid);
		}
		// 内容标题或作者
		if (!ObjectUtils.isEmpty(contentName)) {
			sql.append(" and (contentTitle like ? or author like ?)");
			String str = (new StringBuffer(contentName)).append("%").toString();
			args.add(str);
			args.add(str);
		}
		// 疾病编号
		if (!ObjectUtils.isEmpty(illnessId) && !"0".equals(illnessId)) {
			sql.append(" and illnessId =?");
			args.add(illnessId);
		}
		String strWhere = sql.toString();
		
		sql.delete(0, sql.length());
		String strTotal = sql.append("select count(1)").append(strWhere).toString();

		sql.delete(0, sql.length());
		String strQuery = sql.append("select contentType, image, uuid, url,contentTitle ")
				.append(strWhere).append(" order by createTime desc").toString();

		Object[] params = args.isEmpty() ? null :args.toArray();

//		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
//		return page.queryByPage(strTotal, strQuery, params, null); 
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[]{lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows()}; 
	}

	/**
	 * 获取资讯的详细信息& 获取诊断标准的详细信息
	 */
	public List<Map<String, Object>> selectContent(String uuid){
		StringBuffer sbf = new StringBuffer(1000);
		
		sbf.append("select b.categoryName,a.contentTitle,a.author,a.provenance,a.contentNote,a.introduction,a.uuid,a.image,a.createTime")
		.append(" from content as a LEFT JOIN content_category as b on  a.contentCategoryUuid = b.uuid")
		.append(" where a.uuid =?");
		List<Map<String, Object>> re = dao.query(sbf.toString(), new Object[]{uuid}, null, getQueryCache());
		return ObjectUtils.isEmpty(re)?null:re;
	}
	
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {	return "ContentService";}
}
