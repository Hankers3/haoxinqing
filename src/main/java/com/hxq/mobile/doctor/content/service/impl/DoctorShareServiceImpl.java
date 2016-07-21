package com.hxq.mobile.doctor.content.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.content.service.DoctorShareService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
@Service("com.hxq.mobile.doctor.content.service.DoctorShareService")
public class DoctorShareServiceImpl extends SpringJdbcSimpleEntityService implements DoctorShareService {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {	return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "DoctorShareService";}

	 /**
     * 根据医生的id和文章的id获取该文章是否被分享
     * @param doctorUuid
     * @param uuid
     * @return
     */
    @Override
    public int selectShareTypeByDoctorUuidAndContenUuid(String doctorUuid, String contentUuid) {
        StringBuffer sbf = new StringBuffer(1000);
        sbf.append(" select count(distinct uuid) from doctor_share  where userUuid=? and contentUuid=? ");
        Integer intReturn =  dao.queryForObject(sbf.toString(), new Object[]{doctorUuid,contentUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;        
    }

}
