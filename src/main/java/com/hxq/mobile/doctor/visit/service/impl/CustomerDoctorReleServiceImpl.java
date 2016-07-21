package com.hxq.mobile.doctor.visit.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.doctor.visit.service.DoctorCustomerService;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/5/5 0005.
 * 医生与患者相关操作service实现类
 */
@Service("com.hxq.mobile.doctor.visit.service.DoctorCustomerService")
public class CustomerDoctorReleServiceImpl extends SpringJdbcSimpleEntityService implements DoctorCustomerService {

    @Resource(name = "com.hxq.mobile.common.service.CustomerService")
    private CustomerService customerService;

    @Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
    private CustomerInfoService customerInfoService;

	@Override
	public Map<String, Object> selectCustomerDoctorRele_v2(String uuid) {
		if(ObjectUtils.isEmpty(uuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"select uuid,doctorUuid,createTime from customer_doctor_rele where customerUuid = ? and delFlag = '1' LIMIT 0,1",
				new String[]{uuid}, null, getQueryCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
	}

	 /**
     * 医生添加患者成功，患者将收到推送的消息“XXX医生申请成为您的随访医生/通过 ”
     *
     * @return null
     */
	@Override
	public int addCustomer_v2(String id, String uuid, String doctorUuid, String mobile,
			String name, String illnessDescription, String groupId) throws Exception {
		if(id == null || !(id.equalsIgnoreCase("2") || id.equalsIgnoreCase("3"))) return 0;

		int success = 0;
		CustomerInfo customerInfo = null;
		String uuids = null;
		if(id.equalsIgnoreCase("3")) {
			uuids = IdentityHelper.uuid2();
			Customer customer = new Customer(uuids);
			customer.setMobile(mobile);
			success = customerService.insert(customer);

			customerInfo = new CustomerInfo();
			customerInfo.setCustomerUuid(uuids);
			customerInfo.setRealName(name);
			customerInfo.setIllnessDescription(illnessDescription);
			customerInfoService.insert(customerInfo);
		} else {
			customerInfo = customerInfoService.selectByCustomerUuid(uuid);
			if(customerInfo == null) {
				customerInfo = new CustomerInfo(uuid);
				customerInfo.setCustomerUuid(uuid);
				customerInfo.setIllnessDescription(illnessDescription);
				customerInfoService.insert(customerInfo);
			} else {
				CustomerInfo newInfo = new CustomerInfo(customerInfo.getUuid());
				newInfo.setIllnessDescription(illnessDescription);
				customerInfoService.update(newInfo);
			}
		}

		CustomerDoctorRele customerDoctorRele = new CustomerDoctorRele();

		if(ObjectUtils.isEmpty(groupId)){
		Integer g = 0;
			customerDoctorRele.setGroupUuid(g.toString());
		}else{
			customerDoctorRele.setGroupUuid(groupId);
		}
		customerDoctorRele.setDoctorUuid(doctorUuid);
		customerDoctorRele.setCustomerUuid(uuids);
		customerDoctorRele.setDelFlag("1");
		customerDoctorRele.setCreateTime(DateFormatHelper.getNowTimeStr());
		success = customerInfoService.insert(customerDoctorRele);
		return success;
	}

	@Override
	public int selectCustomerNumModel(String doctorUuid) throws Exception {
		if(ObjectUtils.isEmpty(doctorUuid)) return 0;
		String sql = "select count(uuid) count from customer_doctor_rele where doctorUuid=?";
		List<Map<String, Object>> lst = dao.query(sql, new Object[]{doctorUuid}, null, getQueryCache());
		return (ObjectUtils.isEmpty(lst) ? 0 : MathUtils.toInt(lst.get(0).get("count"), 0));
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult getCustomerByMobile_v2(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult deleteCustomerByCustomerUuidAndGid_v2(String gid, String customerUuid, String doctorUuid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult selectCustomerByDoctorUuidAndGroupUuid_v2(String doctorUuid, String groupUuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCacheName() {return null;}
	@Override
	protected String getQueryCacheName() {return null;}
}
