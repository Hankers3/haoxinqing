package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.hxq.mobile.doctor.visit.service.DoctorAdviceService;
import com.hxq.mobile.doctor.visit.service.DoctorCustomerService;
import com.hxq.mobile.doctor.visit.service.HealthGuideService;
import com.hxq.mobile.doctor.visit.service.VisitApplyService;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.doctor.visit.service.VisitRecordExtendService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.doctor.visit.status.VisitPreceptStatus;
import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.entity.visit.DoctorAdvice;
import com.hxq.mobile.entity.visit.HealthGuide;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.entity.visit.VisitPreceptPush;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.entity.visit.VisitRecordExtend;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.hxq.mobile.vipclub.service.VipclubIntegralLogService;
import com.hxq.mobile.vipclub.service.VipclubIntegralStatService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * 随访方案
 *
 */
@Service("com.hxq.mobile.doctor.visit.service.VisitPreceptService")
public class VisitPreceptServiceImpl extends SpringJdbcSimpleEntityService
	implements VisitPreceptService {
	
	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;
	
	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;
	
	/** 积分的service */
	@Resource(name = "com.hxq.mobile.vipclub.service.VipclubIntegralLogService")
	private VipclubIntegralLogService vipclubIntegralLogService;

	@Resource(name = "com.hxq.mobile.vipclub.service.VipclubIntegralStatService")
	private VipclubIntegralStatService vipclubIntegralStatService;

	/* 消息service */
	@Resource(name="com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
	private InnerMessageService innerMessageService;
	
    //重要医嘱
    @Autowired
    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorAdviceService")
    private DoctorAdviceService doctorAdviceService;
    
    // 医生端随访记录
    @Autowired
    @Resource(name = "com.hxq.mobile.doctor.visit.service.VisitRecordExtendService")
    private VisitRecordExtendService visitRecordExtendService;
    
    //健康指导
    @Autowired
    @Resource(name = "com.hxq.mobile.doctor.visit.service.HealthGuideService")
    private HealthGuideService healthGuideService;
   
    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorCustomerService")
    private DoctorCustomerService doctorCustomerService;

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {return "h1";}

	public Map<String, Object> selectCustomerDoctorReleByid(String customerUuid, String doctorUuid) {
		String sql =  " SELECT doctorUuid, customerUuid, groupUuid, createTime FROM customer_doctor_rele WHERE doctorUuid = ? AND customerUuid = ? ";
		List<String> info = new ArrayList<String>();
		info.add(doctorUuid);
		info.add(customerUuid);
		Object[] cusInfo = info.isEmpty() ? null : info.toArray();
		List<Map<String, Object>> cusInfoReturn = dao.query(sql, cusInfo, null, getCache());
		return (ObjectUtils.isEmpty(cusInfoReturn) ? null : cusInfoReturn.get(0));
	}

	public Map<String, Object> selectVisitPreceptPushByid(String doctorUuid, String customerUuid) {
		String sql =  " SELECT uuid,doctorUuid,customerUuid,visitPreceptUuid,period,pushTime,createTime,pushTimes from visit_precept_push WHERE doctorUuid = ? AND customerUuid = ? ";
		List<String> info = new ArrayList<String>();
		info.add(doctorUuid);
		info.add(customerUuid);
		Object[] cusInfo = info.isEmpty() ? null : info.toArray();
		List<Map<String, Object>> cusInfoReturn = dao.query(sql, cusInfo, null, getCache());
		return (ObjectUtils.isEmpty(cusInfoReturn) ? null : cusInfoReturn.get(0));
	}
	
	public int updateAgreeVisitApply(String customerUuid, String doctorUuid, String doctorName,
			String visitPreceptUuid, String visitApplyUuid, Integer period) throws Exception {
		int intReturn = -1;
		Map<String, Object> cm = selectCustomerDoctorReleByid(customerUuid, doctorUuid);
		if (cm == null) {
			CustomerDoctorRele cus = new CustomerDoctorRele();
			cus.setCustomerUuid(customerUuid);
			cus.setDoctorUuid(doctorUuid);
			cus.setGroupUuid("");
			intReturn = super.insert(cus); 
		}

		//将已关联的全部失效
		dao.update("update visit_apply set applyState='3' where customerUuid=? and applyState='1'", 
					new Object[]{customerUuid}, null);
		//同意并关联随访方案
		VisitApply va = new VisitApply(visitApplyUuid);
		va.setApplyState("1");
		va.setCustomerUuid(customerUuid);
		va.setVisitPreceptUuid(visitPreceptUuid);
		visitApplyService.update(va);
		
		//将已有的随访记录表单变成未完成
		dao.update("update visit_record set visitState='1' where customerUuid=? and visitState='0'", 
				new Object[]{customerUuid}, null);
		//生成首次随访记录表单
		VisitRecord vr = new VisitRecord();
		vr.setCustomerUuid(customerUuid);
		vr.setPreceptUuid(visitPreceptUuid);
		vr.setServiceStaffUuid(doctorUuid);
		visitRecordService.insert(vr);

		/******* 16/02/01 同时添加 随访周期消息推送表 ************/
		// 根据随访id得倒随访推送表。
//		Map<String, Object> vppm = selectVisitPreceptPushByid(doctorUuid, customerUuid);
//		if (vppm == null) {// 如果存在就更新。不存在就添加
//			VisitPreceptPush vpp = new VisitPreceptPush();
//			vpp.setCreateTime(DateFormatHelper.getNowTimeStr());// 创建时间
//			vpp.setDoctorUuid(doctorUuid);// 医生id
//			vpp.setCustomerUuid(customerUuid);// 患者id
//			vpp.setPeriod(period != null ? period.toString() : null);// 方案周期
//			vpp.setPushTime(DateFormatHelper.getNowTimeStr());// 推送时间
//			vpp.setPushTimes(0);// 推送次数
//			vpp.setVisitPreceptUuid(visitPreceptUuid);// 方案id
//			.insert(vpp);
//		}
		/************************ 添加结束 *************************/
	
	/*	// 添加随访患者成功，医生积分+20
		if (!StringUtil.isEmpty(doctorUuid)) {
			vipclubIntegralLogService.insertVipIntegralLog(doctorUuid, "add",
					20, IntegralType.ADD_VISITRECORD.getValue(),
					VipclubIntegralLog.VIPCLUB_USERTYPE_DOC,
					"添加随访患者成功，积分加20", "");
		}
		// 添加随访患者成功，患者积分+5
		if (!StringUtil.isEmpty(customerUuid)) {
			vipclubIntegralLogService.insertVipIntegralLog(customerUuid,
					"add", 5, IntegralType.ADD_VISITRECORD.getValue(),
					VipclubIntegralLog.VIPCLUB_USERTYPE_CUS,
					"添加随访成功，积分加5", "");
		}

		// 保存消息到数据库，并推送到手机终端
		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
			String content = doctorName	+ MessageHelper.getMessage("addVisitRecord.showmessage.newAdd");
			innerMessageService.insertInnerMessageAndPush(doctorUuid, customerUuid,
					content, InnerMessage.ACCOUNT_TYPE_STORE,
					InnerMessage.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "doctorUuid",
					doctorUuid,
					InnerMessageTypeEnum.VISITDETAIL.getValue());
		}*/
		clearQueryCache();
		return intReturn;
	}
	
	/**
	 * 通过医生id,患者id,方案id,进行方案的copy
	 * @param doctorUuid
	 * @param customerUuid
	 * @param visitPreceptUuid
	 * @throws Exception
	 */
	public String addVisitPrecept(String doctorUuid, String customerUuid, String visitPreceptUuid) throws Exception{
		//根据uuid得到 visit_precept数据
		Map<String, Object> visitPreceptMap =  getByUuid(visitPreceptUuid);
		
		VisitPrecept visitPrecept = SimpleBean2DBHelper.map2Bean(visitPreceptMap, VisitPrecept.class);
		
		String uuid = IdentityHelper.uuid2();
		visitPrecept.setUuid(uuid);
		visitPrecept.setCustomerUuid(customerUuid);
		visitPrecept.setServiceStaffUuid(doctorUuid);
		visitPrecept.setRecommend(VisitPreceptStatus.RECOMMEND_CUSTOMER);

		//添加一个新的随访方案
		insert(visitPrecept);
		
		//查询出visitPreceptUuid方案的药物用量
		Map<String,Object> doctorMap = new HashMap<String,Object>();
		doctorMap.put("visitRecordUuid",visitPreceptUuid);
		doctorMap.put("serviceStaffUuid", doctorUuid);
		doctorMap.put("customerUuid", customerUuid);
		
		List<Map<String, Object>> doctorAdviceMap =  doctorAdviceService.selectListByVisitPrecept(visitPreceptUuid);
		
		//将map转成list
		List<DoctorAdvice> doctorAdviceList = SimpleBean2DBHelper.mapList2BeanList(doctorAdviceMap, DoctorAdvice.class);
		if(doctorAdviceList != null && doctorAdviceList.size() > 0){
			for (DoctorAdvice doctorAdvice : doctorAdviceList) {
				//设置新的uuid
				doctorAdvice.setUuid(IdentityHelper.uuid2());
				//设置新的方案id
				doctorAdvice.setVisitRecordUuid(uuid);
				doctorAdvice.setCustomerUuid(customerUuid);
				doctorAdvice.setCreateTime(new Date());
				//添加新的方案药物数据
				doctorAdviceService.insert(doctorAdvice); 
			}
		}

		
		
		List<Map<String,Object>> visitRecordExtendMap =   visitRecordExtendService.getAllVisitPreceptByPreceptUuid(visitPreceptUuid);
		List<VisitRecordExtend> visitRecordExtendList = SimpleBean2DBHelper.mapList2BeanList(visitRecordExtendMap, VisitRecordExtend.class);
		if(visitRecordExtendList != null && visitRecordExtendList.size() > 0){
			for (VisitRecordExtend visitRecordExtend : visitRecordExtendList) {
				visitRecordExtend.setUuid(IdentityHelper.uuid2());
				//设置新的方案id
				visitRecordExtend.setVisitRecordUuid(uuid);
				
				visitRecordExtendService.insert(visitRecordExtend);
			}
		}
		List<Map<String,Object>> healthGuideMap = healthGuideService.getHealthGuideByDoctorUuidAndVisitUuid(doctorUuid, visitPreceptUuid);
		List<HealthGuide> healthGuideList = SimpleBean2DBHelper.mapList2BeanList(healthGuideMap, HealthGuide.class);
		if(healthGuideList != null && healthGuideList.size() > 0){
			for (HealthGuide healthGuide : healthGuideList) {
				healthGuide.setUuid(IdentityHelper.uuid2());
				healthGuide.setNotevisitRecordUuid(uuid);
				healthGuide.setCustomerUuid(customerUuid);
				healthGuide.setCreateTime(DateFormatHelper.getNowTimeStr());
				
				healthGuideService.insert(healthGuide);
			}
		}

		return uuid;
	}
	

	public int updateVisitRecord(String doctorUuid, String customerUuid, String visitPreceptUuid,
			Integer period) throws Exception {
		//更新随访申请的随访方案
		Map<String, Object> form = new HashMap<String, Object>();
		form.put("customerUuid", customerUuid);
		form.put("doctorUuid", doctorUuid);
		form.put("visitState", "0");
		List<Map<String, Object>> lst;

		int intReturn = -1;
		lst = visitApplyService.selectList(form, false);
		if(!ObjectUtils.isEmpty(lst)) {
			Map<String, Object> row = lst.get(0);
			VisitApply va = new VisitApply((String) row.get("uuid"));
			va.setVisitPreceptUuid(visitPreceptUuid);
			va.setApplyState("1");
			intReturn = visitApplyService.update(va);
		}
		
		String preceptUuid = addVisitPrecept(doctorUuid, customerUuid, visitPreceptUuid);
		
		Map<String, Object> docCusMap = doctorCustomerService.selectCustomerDoctorRele_v2(customerUuid);
		String dCuuid = null;
		String dCdoctorUuid = null;
		CustomerDoctorRele cusDocRele = null;
		if(!ObjectUtils.isEmpty(docCusMap)){
			dCuuid = (String) docCusMap.get("uuid");
			dCdoctorUuid = (String) docCusMap.get("doctorUuid");
		}
		if(ObjectUtils.isEmpty(dCdoctorUuid)){
			//新建关联关系
			cusDocRele = new CustomerDoctorRele(IdentityHelper.uuid2());
			cusDocRele.setDoctorUuid(doctorUuid);
			cusDocRele.setCustomerUuid(customerUuid);
			cusDocRele.setGroupUuid("0");
			cusDocRele.setCreateTime(DateFormatHelper.getNowTimeStr());
			doctorCustomerService.insert(cusDocRele);
		}else if(!dCdoctorUuid.equalsIgnoreCase(doctorUuid)){
			//进行逻辑删除
			cusDocRele = new CustomerDoctorRele(dCuuid);
			cusDocRele.setDelFlag("2");
			doctorCustomerService.update(cusDocRele);
			//新建关联关系
			cusDocRele.setUuid(IdentityHelper.uuid2());
			cusDocRele.setDoctorUuid(doctorUuid);
			cusDocRele.setCustomerUuid(customerUuid);
			cusDocRele.setGroupUuid("0");
			cusDocRele.setCreateTime(DateFormatHelper.getNowTimeStr());
			doctorCustomerService.insert(cusDocRele);
		}
		
		/******* 16/02/01 同时添加 随访周期消息推送表 ************/
		// 根据随访id得到随访推送表 
		Map<String, Object> vppm = selectVisitPreceptPushByid(doctorUuid,customerUuid);
		VisitPreceptPush visitPush = new VisitPreceptPush();
		// 如果存在就更新。不存在就添加
		if (vppm == null) {
			visitPush.setCreateTime(DateFormatHelper.getNowTimeStr());// 创建时间
			visitPush.setDoctorUuid(doctorUuid);// 医生id
			visitPush.setCustomerUuid(customerUuid);// 患者id
			visitPush.setPeriod(period != null ? period.toString() : null);// 方案周期
			visitPush.setPushTime(DateFormatHelper.getNowTimeStr());// 推送时间
			visitPush.setVisitPreceptUuid(preceptUuid);// 方案id
			super.insert(visitPush);
        } else {
            if(!((String) vppm.get("visitPreceptUuid")).equals(visitPreceptUuid)){
            	int pushTimes = (int) vppm.get("pushTimes");
            	visitPush.setVisitPreceptUuid(preceptUuid);//方案id
            	visitPush.setPushTime(DateFormatHelper.getNowTimeStr());// 推送时间
            	visitPush.setPushTimes(pushTimes + 1);// 推送次数+1
            	super.update(visitPush);// 更新
            }
        }

		/************************ 添加结束 *************************/
		// 保存消息到数据库，并推送到手机终端
/*		ServiceStaff ser = (ServiceStaff) super.select(new ServiceStaff(doctorUuid));
		String doctorName = ser.getRealName();
		if (!StringUtil.isEmpty(customerUuid) && !StringUtil.isEmpty(doctorUuid)) {
			String content = doctorName + MessageHelper.getMessage("addVisitRecord.showmessage.newAdd");
			innerMessageService.insertInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessage.ACCOUNT_TYPE_STORE, InnerMessage.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "doctorUuid", doctorUuid, InnerMessageTypeEnum.VISITDETAIL.getValue());
		}*/
		clearQueryCache();
		return intReturn;
	}


	/**
	 * 根据uuid得到随访方案信息
	 * @param visitUuid
	 * @return
	 */
	public Map<String,Object> getByUuid(String visitUuid){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(visitPreceptSql);
		sqlBuff.append(" where uuid=?");
		
		List<String> info = new ArrayList<String>();
		info.add(visitUuid);
		Object[] cusInfo = info.isEmpty() ? null : info.toArray();
		List<Map<String, Object>> cusInfoReturn = dao.query(sqlBuff.toString(), cusInfo, null, getCache());
		return (ObjectUtils.isEmpty(cusInfoReturn) ? null : cusInfoReturn.get(0));
	}
	

	

	@Override
	protected String getQueryCacheName() {return "VisitPreceptService";}
	
	public static String visitPreceptSql = "SELECT   uuid,  oper,  opeTime,  delFlag,  serviceStaffUuid,  preceptName, drugTherapy,  sideEffects, "
			+ " dietGuide,  sport,    sleep,  period,  electrocardiogram,  renal, weight,  bloodRoutine,  hepatic,  visitUuid,  createTime, "
			+ " hospitalUuid,  recommend,  selfTest,  doctorTest,selfPeriod,doctorPeriod "
			+ " FROM   visit_precept ";

	@Override
	public List<Map<String, Object>> getMyVisitpreceptByDoctorid(String doctorUuid) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(visitPreceptSql);
		sqlBuff.append(" where recommend='0' and serviceStaffUuid=?");
		List<String> info = new ArrayList<String>();
		info.add(doctorUuid);
		Object[] cusInfo = info.isEmpty() ? null : info.toArray();
		List<Map<String, Object>> cusInfoReturn = dao.query(sqlBuff.toString(), cusInfo, null, getCache());
		return (ObjectUtils.isEmpty(cusInfoReturn) ? null : cusInfoReturn);
	}
	
	@Override
	public VisitPrecept getMyVisitpreceptByDoctoridAndCustomerId(String delFlag,String recommend,String doctorUuid,String customerUuid) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(visitPreceptSql);
		sqlBuff.append(" where");
		sqlBuff.append(" delFlag=?  ");
		sqlBuff.append(" and recommend=?");
		sqlBuff.append(" and serviceStaffUuid=?");
		sqlBuff.append(" and customerUuid=?");
		List<String> info = new ArrayList<String>();
		info.add(delFlag);
		info.add(recommend);
		info.add(doctorUuid);
		info.add(customerUuid);
		Object[] cusInfo = info.isEmpty() ? null : info.toArray();
		List<Map<String, Object>> visitPrecptList = dao.query(sqlBuff.toString(), cusInfo, null, getCache());
		
		return ObjectUtils.isEmpty(visitPrecptList) ? null : SimpleBean2DBHelper.map2Bean(visitPrecptList.get(0), VisitPrecept.class);
	}
	
	/**
	 * 根据医生id,获取所有患者的方案数据
	 * @param delFlag		方案是否删除
	 * @param recommend	 	方案的类型(2:医生,患者方案)
	 * @param doctorUuid	医生id
	 * @return
	 */
	@Override
	public List<Map<String,Object>> getVisitpreceptByDoctorid(String delFlag,String recommend,String doctorUuid){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("SELECT   uuid,  customerUuid,  serviceStaffUuid FROM   visit_precept");
		sqlBuff.append(" where");
		sqlBuff.append(" delFlag=?  ");
		sqlBuff.append(" and recommend=?");
		sqlBuff.append(" and serviceStaffUuid=?");
		List<String> info = new ArrayList<String>();
		info.add(delFlag);
		info.add(recommend);
		info.add(doctorUuid);
		Object[] cusInfo = info.isEmpty() ? null : info.toArray();
		List<Map<String, Object>> cusInfoReturn = dao.query(sqlBuff.toString(), cusInfo, null, getCache());
		return (ObjectUtils.isEmpty(cusInfoReturn) ? null : cusInfoReturn);
	}
}