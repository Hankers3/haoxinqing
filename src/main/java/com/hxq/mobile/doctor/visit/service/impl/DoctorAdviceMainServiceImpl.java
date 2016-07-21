package com.hxq.mobile.doctor.visit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.hxq.mobile.doctor.visit.service.DoctorAdviceMainService;
import com.hxq.mobile.doctor.visit.service.DoctorAdviceService;
import com.hxq.mobile.entity.message.InnerMessage;
import com.hxq.mobile.entity.visit.DoctorAdvice;
import com.hxq.mobile.entity.visit.DoctorAdviceMain;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.doctor.visit.service.DoctorAdviceMainService")
public class DoctorAdviceMainServiceImpl extends SpringJdbcSimpleEntityService
	implements DoctorAdviceMainService {

	@Resource(name="com.hxq.mobile.doctor.visit.service.DoctorAdviceService")
	private DoctorAdviceService childService;

	@Resource(name="com.hxq.mobile.doctor.visit.service.DoctorAdviceMainService")
	private DoctorAdviceMainService service;	
	
	@Resource(name="com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
	private InnerMessageService innerMessageService;
	
	public int insertSave2(DoctorAdviceMain bean) throws Exception{
		int intReturn = -1;
		bean.setId(IdentityHelper.uuid2());
    	for(DoctorAdvice da : bean.getChild()) {
    		da.setMainUuid(bean.getId());
    	}
    	intReturn = service.insert(bean);

		//XP消息推送
		innerMessageService.insertInnerMessageAndPush(bean.getServiceStaffUuid(), bean.getCustomerUuid(),
				MessageHelper.getMessage("doctorAdviceMesg.showmessage.newAdd"),
				InnerMessage.ACCOUNT_TYPE_STORE, InnerMessage.ACCOUNT_TYPE_CUSTOMER,
				PushConst.push_client_customer, bean.getCustomerUuid(), bean.getId(),
				InnerMessageTypeEnum.DOCTORADVICE.getValue());
		clearQueryCache();
		return intReturn;
	} 
	
	@Override
	public Map<String, Object> selectLastDoctorAdviceMain(String serviceStaffUuid, String customerUuid) {
		if(ObjectUtils.isEmpty(serviceStaffUuid) || ObjectUtils.isEmpty(customerUuid)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select uuid, drugReaction, cureNote from doctor_advice_main where")
		.append(" serviceStaffUuid = ? and customerUuid = ? and delFlag='0'")
		.append(" order by createTime desc limit 0, 1");

		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(),
				new String[]{serviceStaffUuid, customerUuid}, null, getCache());
		if(ObjectUtils.isEmpty(lstReturn)) return null;

		Map<String, Object> map = lstReturn.get(0);
		List<Map<String, Object>> child = childService.selectListByDoctorAdviceMain(
				StringUtils.trimToEmpty(map.get("uuid")));
		if(ObjectUtils.isEmpty(child)==false) map.put("child", child);
		return map;
	}

	@Override
	public Map<String, Object> selectDoctorAdviceMainByVisitRecord(String serviceStaffUuid, String customerUuid) {
		if(ObjectUtils.isEmpty(serviceStaffUuid) || ObjectUtils.isEmpty(customerUuid)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select preceptUuid from visit_record where serviceStaffUuid = ?")
		.append(" and customerUuid = ? order by createTime desc limit 0, 1");

		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(),
				new String[]{serviceStaffUuid, customerUuid}, null, getCache());
		if(ObjectUtils.isEmpty(lstReturn)) return null;

		Map<String, Object> map = lstReturn.get(0);
		String uuid = StringUtils.trimToEmpty(map.get("preceptUuid"));
		if(ObjectUtils.isEmpty(uuid)) return null;

		map = selectVisitPrecept(uuid);
		if(ObjectUtils.isEmpty(map)) return null;

		//为了保持接口数据项定义一致，对药物不良反应处理和其他治疗按重要医嘱命名
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		mapReturn.put("drugReaction", map.get("drugTherapy"));
		mapReturn.put("cureNote", map.get("sideEffects"));

		List<Map<String, Object>> child = childService.selectListByVisitPrecept(uuid);
		if(ObjectUtils.isEmpty(child)==false) mapReturn.put("child", child);
		return mapReturn;
	}

	@Override
	public int insert(AbstractEntity<?> bean) throws Exception {
		DoctorAdviceMain dam = (DoctorAdviceMain) bean;

		//先将之前的医嘱设置为过期
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("update doctor_advice_main set delFlag='1' where");
		sbf.append(" serviceStaffUuid = ? and customerUuid = ? and delFlag='0'");
		dao.update(sbf.toString(), new String[]{dam.getServiceStaffUuid(), dam.getCustomerUuid()}, null);

		//插入主表
		int intReturn = super.insert(bean);

		//插入子表
		List<DoctorAdvice> child = dam.getChild();
		if(ObjectUtils.isEmpty(child)==false) {
			for(DoctorAdvice da : child) {
				childService.insert(da);
			}
		}
		clearQueryCache();
		return intReturn;
	}

	/**
	 * 获取指定随访方案的药物不良反应处理和其他治疗
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> selectVisitPrecept(String uuid) {
		if(ObjectUtils.isEmpty(uuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"select drugTherapy, sideEffects from visit_precept where uuid = ?",
				new String[]{uuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {return "h1";}

	@Override
	protected String getQueryCacheName() {return "DoctorAdviceMainService";}
}
