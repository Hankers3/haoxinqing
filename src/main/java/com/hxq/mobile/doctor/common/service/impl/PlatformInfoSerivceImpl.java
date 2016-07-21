package com.hxq.mobile.doctor.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.common.service.PlatformInfoSerivce;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.common.service.PlatformInfoSerivce")
public class PlatformInfoSerivceImpl extends SpringJdbcSimpleEntityService implements PlatformInfoSerivce {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {return "h1";}

	@Override
	protected String getQueryCacheName() {return null;}

	public static String platFormInfoSql = "SELECT uuid,delFlag,opeTime,oper,createTime,endTime,image,number,startTime,"
			+ "state,type,videoAddress,videoIntroduction,videoModel,speaker,userId,userName,userIntroduce,videoHot,videoType,fName "
			+ "from platform_info ";
	
	public static String platformCommunicationSql = "SELECT uuid,delFlag,opeTime,oper,platformUuid,platformrName,questionerUuid,"
			+ "questionerName,userType,problemDescription,createTime,conftimeState,admin,replyMessage,replyTime,conftime,remark,replyState "
			+ "from platform_communication";
	
	@Override
	public Object[] selectPlatFormInfoByName(String name, String videoType, Integer pageCount,Integer pageNo) {
		StringBuffer sql = new StringBuffer(1000);
		List<String> info = new ArrayList<String>();
		sql.append(" from platform_info where 1=1 and videoType = ? ");
		info.add(videoType);
		
		if (!ObjectUtils.isEmpty(name)) {
			sql.append(" and (videoModel like '%").append(name).append("%'");
			sql.append(" or userName like '%").append(name).append("%')");
		}
		
		String strWhere = sql.toString();
		sql.delete(0, sql.length());
		String strTotal = sql.append("select count(1)").append(strWhere).toString();

		sql.delete(0, sql.length());
		String strQuery = sql.append(" SELECT uuid,delFlag,opeTime,oper,createTime,endTime,image,number,startTime,state,type,"
				+ "videoAddress,videoIntroduction,videoModel,speaker,userId,userName,userIntroduce,videoHot,videoType,fName ")
				.append(strWhere).append(" ORDER BY createTime desc ").toString();

		Object[] params = info.isEmpty() ? null :info.toArray();

		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[]{lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows()};  
	}

	@Override
	public Map<String, Object> selectFileMapByRemotePaths(String imgId) {
		String sql = " SELECT uuid,delFlag,opeTime,oper,fileName,remotePaths from file_file where fileName = ? ";
		List<String> staff = new ArrayList<String>();
		staff.add(imgId);
		Object[] CollectInfo = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sql, CollectInfo, null, getQueryCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}

	@Override
	public int selectCommunicationCount(String coMmonId) {
		if(ObjectUtils.isEmpty(coMmonId)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" select count(distinct uuid) count from platform_communication where platformUuid = ? ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{coMmonId}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	@Override
	public Object[] selectCommunicationByVideoUuid(String videoUuid, Integer pageCount,Integer pageNo) {
		StringBuffer sql = new StringBuffer(1000);
		List<String> info = new ArrayList<String>();
		sql.append(" from platform_communication where conftimeState='1' and platformUuid = ? ");
		info.add(videoUuid);
		
		String strWhere = sql.toString();
		sql.delete(0, sql.length());
		String strTotal = sql.append("select count(1)").append(strWhere).toString();

		sql.delete(0, sql.length());
		String strQuery = sql.append("SELECT uuid,delFlag,opeTime,oper,platformUuid,platformrName,questionerUuid,"
				+ "questionerName,userType,problemDescription,createTime,conftimeState,admin,replyMessage,replyTime,conftime,remark,replyState ")
				.append(strWhere).append(" order by createTime desc ").toString();

		Object[] params = info.isEmpty() ? null :info.toArray();

		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[]{lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows()}; 
	}
	
}
