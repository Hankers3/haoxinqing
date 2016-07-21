package com.hxq.mobile.doctor.content.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.content.service.FavoriteService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.content.service.FavoriteService")
public class FavoriteServiceImpl extends SpringJdbcSimpleEntityService implements FavoriteService {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}

	/**
	 * type 1 :资讯收藏,2:视频收藏
	 */
	@Override  
	public int selectStoreTypeByDoctorUuidAndType(String doctorUuid,String uuid,String type) {
		StringBuffer sbf = new StringBuffer(1000);
		Object[] data = null;
		if(type.equals("1")){
			sbf.append("select count(distinct uuid) from favorite where userId=? and contentUuid=? and state=? ");
			data = new Object[]{doctorUuid,uuid,"1"};
		}else{
			sbf.append("select count(distinct uuid) from favorite where userId=? and videoUuid=? ");
			data = new Object[]{doctorUuid,uuid};
		}
		
		Integer intReturn =  dao.queryForObject(sbf.toString(),data , null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}
	
	/**
	 * 关注状态 根据患者id和文章id查关注状态 
	 * @param customerUuid
	 * @param contextUuid
	 * @return 如果患者已关注则返回值 未关注返回null
	 */
	@Override
	public String selectUuidByCustomerUuidAndContextUuid(String customerUuid,String contentUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(" select o.uuid from favorite as o where o.userId=? and o.contentUuid=? ");
		List<Map<String, Object>> re = dao.query(sbf.toString(), new Object[]{customerUuid,contentUuid}, null, getCache());
		return ObjectUtils.isEmpty(re)?null:re.get(0).get("uuid").toString();
	}
	
	/**
	 * 根据id和状态获取收藏关注的数量
	 */
	@Override
	public int getNumByContentUuid(String contentUuid, String type){
		StringBuffer hql = new StringBuffer(
				" select count(distinct o.userId) from favorite as o where 1=1 ");
		// 代表类型的收藏人数
		if (type.equals("1")) {
			hql.append(" and o.contentUuid =? ");
		} else {
			hql.append(" and o.videoUuid =? ");
			
		}
		Integer intReturn = dao.queryForObject(hql.toString(), new Object[]{contentUuid}, null, Integer.class);
		return intReturn;
	}

	/**
	 * 根据患者的id和收藏状态获取收藏列表
	 */
	@Override
	public List<Map<String, Object>> getFavoriteModelListByCustomerUuidAndState(String customerUuid, String state) {

		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(state)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(" select * from favorite as o where o.userId=? and o.state= ? ");
		List<Map<String, Object>> lst = dao.query(sbf.toString(),new Object[]{customerUuid, state}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst)?null:lst;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "FavoriteService";}

}
