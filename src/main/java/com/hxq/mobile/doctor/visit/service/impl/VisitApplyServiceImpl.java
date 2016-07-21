package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageTypeEnum;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.doctor.visit.service.VisitApplyService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.message.InnerMessage;
import com.hxq.mobile.entity.visit.VisitApply;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.visit.service.VisitApplyService")
public class VisitApplyServiceImpl extends SpringJdbcSimpleEntityService implements VisitApplyService {

	@Resource(name="com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;

	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitApplyService")
	private VisitApplyService visitApplyService;
	
	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;
	
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;
	
	@Resource(name="com.hxq.mobile.message.InnerMessage.service.InnerMessageService")
	private InnerMessageService innerMessageService;
	
	@Override
	public int update(AbstractEntity<?> bean) throws Exception {
		VisitApply va = (VisitApply) bean;
		String sql = "select uuid from visit_apply where customerUuid =? and (applyState='0' or applyState='1') and uuid<>?";
		List<String> columns = new ArrayList<String>();
		columns.add(va.getCustomerUuid());
		columns.add(va.getUuid());
		Object[][] uuids = dao.queryForArray(sql, columns.isEmpty() ? null : columns.toArray(), null, false);
		VisitApply vaOther = null;
		for(Object[] ary : uuids) {
			vaOther = new VisitApply((String) ary[0]);
			vaOther.setApplyState("3");
			super.update(vaOther);
		}
		return super.update(bean);
	}

	/**
	 * 根据条件查询随访申请列表
	 */
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append("select uuid,customerUuid,serviceStaffUuid,applyState,createTime,visitPreceptUuid from visit_apply as a");

		if(RequestUtil.isEmpty(form, "customerUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.customerUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "customerUuid"));
		}

		if(RequestUtil.isEmpty(form, "serviceStaffUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.serviceStaffUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "serviceStaffUuid"));
		} else if(RequestUtil.isEmpty(form, "doctorUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.serviceStaffUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "doctorUuid"));
		}

		if(RequestUtil.isEmpty(form, "applyState")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.applyState = ?");
			columns.add(RequestUtil.getFormValue(form, "applyState"));
		} else {//默认返回待处理的
			sbf.append(columns.isEmpty()?" where":" and").append(" a.applyState = '0'");
		}

		sbf.append(" ORDER BY a.createTime");

		Object[] params = columns.isEmpty() ? null : columns.toArray();
		return dao.query(sbf.toString(), params, null, getCache());
	}

	@Override
	public Map<String, Object> selectCustomerModel(String customerUuid) {
		String sql = " select c.mobile,c.email from customer c where c.customerId = ? ";
		List<String> staff = new ArrayList<String>();
		staff.add(customerUuid);
		Object[] staffparams = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sql, staffparams, null, getQueryCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}

	@Override
	public Map<String, Object> selectVisitApplyListByUuid(String visitApplyUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select v.customerUuid,v.serviceStaffUuid,s.realName from visit_apply as v")
		.append(" left join service_staff s on s.uuid = v.serviceStaffUuid ")
		.append(" where v.applyState = '0' and v.uuid = ?");
		List<Map<String, Object>> lst = dao.query(sbf.toString(),
				new Object[]{visitApplyUuid}, null, getQueryCache());
		return (ObjectUtils.isEmpty(lst) ? null : lst.get(0));
	}

	@Override
	public Map<String, Object> selectApplyDetail(String customerUuid,String applyUuid,String doctorUuid,String symptoms) throws Exception{
		Map<String, Object> customerInfoMap = new HashMap<>();
		Map<String, Object> relist = new HashMap<>();
		Map<String, Object> customerModel = visitApplyService.selectCustomerModel(customerUuid);
		if(ObjectUtils.isEmpty(customerModel)==false) {
			customerInfoMap.put("mobile", customerModel.get("mobile"));// 手机号
			customerInfoMap.put("email", customerModel.get("email"));// 电子邮箱
		}
		CustomerInfo customerInfo = null;
		customerInfo = customerInfoService.selectByCustomerUuid(customerUuid);
		if (customerInfo != null) {
			customerInfoMap.put("nickname", customerInfo.getNickName());// 昵称
			customerInfoMap.put("realName", customerInfo.getRealName());// 真实姓名
			customerInfoMap.put("sex", customerInfo.getSex());// 1是男 ，2是女
			customerInfoMap.put("certCode", customerInfo.getCertCode());// 出生年月
			customerInfoMap.put("marryState", customerInfo.getMarryState());// 婚姻状况
			customerInfoMap.put("industry", customerInfo.getIndustry());// 职业
			customerInfoMap.put("address", customerInfo.getAddress());// 住址
			customerInfoMap.put("diseaseTime", customerInfo.getDiseaseTime());// 病程
			customerInfoMap.put("firstDiagnosis", customerInfo.getFirstDiagnosis());// 首次就诊时间
			customerInfoMap.put("ifStart", customerInfo.getIfStart());// 是否首发
			customerInfoMap.put("seizureTimes",	customerInfo.getSeizureTimes());// 复发次数
			customerInfoMap.put("height", customerInfo.getHeight());// 身高
			customerInfoMap.put("weight", customerInfo.getWeight());// 体重
			customerInfoMap.put("nearlyDrugs", customerInfo.getNearlyDrugs());// 近3个月使用药物
			customerInfoMap.put("illnessDescription", customerInfo.getIllnessDescription());// 病情描述
			customerInfoMap.put("age", customerInfo.getAge());// 年龄
			customerInfoMap.put("diagnose",	customerInfo.getIllnessDescription());// 诊断描述
			customerInfoMap.put("applyUuid", applyUuid);// 申请uuid
			customerInfoMap.put("customerUuid",	customerUuid);// 患者uuid
			customerInfoMap.put("doctorUuid", doctorUuid);// 医生uuid
			customerInfoMap.put("symptoms", symptoms);// 症状表现和既往治疗经过symptoms
		}

		// 获取图片信息
		String[] imgs = imgUploadService.selectImagesByTableName("visit_apply", applyUuid);
		if (ObjectUtils.isEmpty(imgs) == false) customerInfoMap.put("imgs", imgs);
		
		relist.putAll(customerInfoMap);
		return relist;
	}

	@Override	
	public Map<String, Object> selectVisitApplyList(String customerUuid,String doctorUuid,String applyUuid,String createTime) throws Exception{
		// 组装返回到客户端的患者信息
		Map<String, Object> visitApplyMap = new HashMap<>();
		CustomerInfo cim = null;
		if (!StringUtil.isEmpty(customerUuid)) {
			cim = customerInfoService.selectByCustomerUuid(customerUuid);
			if (cim != null && !StringUtil.isEmpty(doctorUuid)) {
				visitApplyMap.put("realName", cim.getRealName());
				visitApplyMap.put("sex", cim.getSex());
				visitApplyMap.put("age", cim.getAge());

				if (!StringUtil.isEmpty(createTime) && createTime.length() > 10) {
					visitApplyMap.put("createTime",createTime);
				} else {
					visitApplyMap.put("createTime", cim.getOpeTime());
				}
				visitApplyMap.put("customerUuid", customerUuid);
				visitApplyMap.put("doctorUuid", doctorUuid);
				visitApplyMap.put("applyUuid", applyUuid);
				visitApplyMap.put("imgUrl", cim.getImage());
				visitApplyMap.put("illnessDescription", cim.getIllnessDescription());
			}
		}
		return visitApplyMap;
	}

	@Override
	public int updateForRefuseVivistApply(String applyUuid,String refuseReason) throws Exception{
		int intReturn = -1;
		//拒绝患者的申请
		VisitApply va = new VisitApply(applyUuid);
		va.setApplyState("2");
		va.setRefuseReason(refuseReason);
		intReturn = visitApplyService.update(va);
		va = (VisitApply) visitApplyService.select(new VisitApply(applyUuid));
		//发送消息
		if (!StringUtil.isEmpty(va.getServiceStaffUuid())
				&& !StringUtil.isEmpty(va.getCustomerUuid())) {
			String content = MessageHelper
					.getMessage("refuseVivistApply.showmessage.newAdd")
					+ refuseReason;
			innerMessageService.insertInnerMessageAndPush(
					va.getServiceStaffUuid(), va.getCustomerUuid(),
					content, InnerMessage.ACCOUNT_TYPE_STORE,
					InnerMessage.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "doctorUuid",
					va.getServiceStaffUuid(),
					InnerMessageTypeEnum.VISITDETAIL.getValue());
		}
		clearQueryCache();
		return intReturn;
	}

	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "VisitApplyService";}

	@Override
	public List<Map<String, Object>> getByPreceptUuid(String doctorUuid, String preceptUuid) throws Exception {
		return null;
	}
}
