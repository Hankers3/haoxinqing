package com.hxq.mobile.doctor.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.common.service.FavoriteInformationSerivce")
public class FavoriteInformationSerivceImpl extends SpringJdbcSimpleEntityService implements FavoriteInformationSerivce {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {return "h1";}

	@Override
	protected String getQueryCacheName() {return null;}

	
	public static String favoriteSql = "select uuid,delFlag,opeTime,oper,userId,favoriteTime,contentUuid,videoUuid,userType,state,tags,"
			+ "favoriteType from favorite ";
			
	
	@Override
	public Map<String, Object> selectCollectInformationByid(String doctorUuid, String videoUuid) {
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(favoriteSql);
		sqlBuff.append(" where userId = ? and videoUuid = ?");
		List<String> staff = new ArrayList<String>();
		staff.add(doctorUuid);
		staff.add(videoUuid);
		Object[] CollectInfo = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sqlBuff.toString(), CollectInfo, null, getQueryCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}

	@Override
	public int selectCollectInformationByCount(String doctorUuid, String videoUuid) {
		if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(videoUuid)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" select count(distinct uuid) count from favorite where userId = ? and videoUuid = ? ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{doctorUuid,videoUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	@Override
	public int selectStateCollectInformationByCount(String doctorUuid, String videoUuid) {
		if(ObjectUtils.isEmpty(doctorUuid) || ObjectUtils.isEmpty(videoUuid)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" select count(distinct uuid) count from favorite where userId = ? and contentUuid = ? and state = '1' ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{doctorUuid,videoUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	@Override
	public int selectFavoriteInformationByContentUuid(String contentUuid) {
		if(ObjectUtils.isEmpty(contentUuid)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" select count(distinct uuid) count from favorite where contentUuid = ? ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{contentUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	@Override
	public Object[] selectFavoriteInformationListById(String doctorid, Integer pageCount,Integer pageNo) {
		StringBuffer sbf = new StringBuffer(1000);
		List<Object> args = new ArrayList<Object>();
		args.add(doctorid);
		
		sbf.append(" from favorite where userId = ? and state = '1' ");
		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();
		
		sbf.delete(0, sbf.length());
		String strQuery = sbf.append(" select uuid,delFlag,opeTime,oper,userId,favoriteTime,contentUuid,videoUuid,userType,state,tags,favoriteType ")
		.append(strWhere).append(" order by favoriteTime desc ").toString();
		
		Object[] params = args.isEmpty() ? null :args.toArray();
		
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[]{lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows()}; 
	}

	@Override
	public int selectFavoriteInformationNumByVedioUuid(String videoUuid, String userType) {
		if(ObjectUtils.isEmpty(videoUuid) || ObjectUtils.isEmpty(userType)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" select count(uuid) count from favorite where videoUuid = ? and userType = ? and state = '1' ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{videoUuid, userType}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}
	
	/**
	 * 关注状态 根据患者id和文章id查关注状态 
	 * @param customerUuid
	 * @param contextUuid
	 * @return 如果患者已关注则返回值 未关注返回null
	 */
	@Override
	public String selectUuidByCustomerUuidAndContextUuid(String customerUuid,String contextUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(" select o.uuid from favorite as o where o.userId=? and o.contentUuid=? ");
		Object[][] array = this.dao.queryForArray(sbf.toString(), new Object[]{
				customerUuid, contextUuid}, null, false, super.getCache());
		return ObjectUtils.isEmpty(array) ? null : (String) array[0][0];
	}
	
	@Override
	public int selectFavoriteNumByContentUuid(String contentUuid) {
		if(ObjectUtils.isEmpty(contentUuid)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" select count(uuid) count from favorite where contentUuid = ? and userType = '1' and state = '1' ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{contentUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}
}
