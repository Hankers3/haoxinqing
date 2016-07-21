package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.doctor.visit.mapper.CaseGroupMapper;
import com.hxq.mobile.doctor.visit.mapper.CustomerDoctorReleMapper;
import com.hxq.mobile.doctor.visit.mapper.CustomerMapper;
import com.hxq.mobile.doctor.visit.mapper.VisitRecordMapper;
import com.hxq.mobile.doctor.visit.service.DoctorCustomerService;
import com.hxq.mobile.doctor.visit.service.VisitPreceptService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.doctor.visit.status.VisitPreceptStatus;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.visit.CaseGroup;
import com.hxq.mobile.entity.visit.VisitPrecept;
import com.hxq.mobile.entity.visit.VisitRecord;
import com.hxq.mobile.patient.visit.service.CustomerDoctorReleService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.util.repository.MybatisSimpleEntityService;
import com.hxq.mobile.util.repository.SimpleEntityDao;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/5/5 0005. 医生与患者相关操作service实现类
 */
@Service("com.hxq.mobile.doctor.visit.service.impl.DoctorCustomerServiceImpl")
public class DoctorCustomerServiceImpl extends MybatisSimpleEntityService implements DoctorCustomerService {
	@Override
	protected SimpleEntityDao getDao() {
		return null;
	}

	@Override
	protected String getCacheName() {
		return "DoctorCustomerMobileService";
	}

	@Autowired
	private CustomerMapper customerMapper;

	/*
	 * @Autowired private CustomerInfoMapper customerInfoMapper;
	 */

	@Autowired
	@Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
	private CustomerInfoService customerInfoService;

	@Autowired
	private VisitRecordMapper visitRecordMapper;
	@Autowired
	private CustomerDoctorReleMapper customerDoctorReleMapper;
	@Autowired
	private CaseGroupMapper caseGroupMapper;
	@Autowired
	@Resource(name = "com.hxq.mobile.patient.visit.service.CustomerDoctorReleService")
	private CustomerDoctorReleService customerDoctorReleService;
	
	@Autowired
	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitPreceptService")
	private VisitPreceptService visitPreceptService;
	@Autowired
	@Resource(name = "com.hxq.mobile.doctor.visit.service.VisitRecordService")
	private VisitRecordService visitRecordService;

	@Override
	public ApiResult getCustomerByMobile_v2(Map<String, String> map) throws Exception {
		Customer customer = customerMapper.selectByMobile(map.get("mobile"));
		if (ObjectUtils.isEmpty(customer)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "手机号是未注册的患者");
		} else {
			CustomerInfo customerInfo = customerInfoService.selectByCustomerUuid(customer.getUuid());
			if (ObjectUtils.isEmpty(customerInfo)) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "该患者无基本信息");
			}

			List<VisitRecord> visitRecords = visitRecordMapper.selectByCustomerUuid(customer.getUuid());
			if (!ObjectUtils.isEmpty(visitRecords) && visitRecords.size() > 0) {
				if (map.get("doctorUuid").equals(visitRecords.get(0).getServiceStaffUuid())) {
					return ApiResult.error(ApiCode.BAD_REQUEST, "此手机号已经是您的随访患者！");
				} else {
					return ApiResult.error(ApiCode.BAD_REQUEST, "该患者已有随访医生，暂不能添加该患者！   ");
				}
			}
			map.remove("doctorUuid");
			map.remove("mobile");
			map.put("name", customerInfo.getRealName());
			return ApiResult.right(map);
		}
	}

	@Override
	public ApiResult deleteCustomerByCustomerUuidAndGid_v2(String gid, String customerUuid, String doctorUuid)
			throws Exception {
		if (!ObjectUtils.isEmpty(gid)) {
			customerDoctorReleMapper.deleteByCustomerIdAndGroupId(customerUuid, gid);
		} else {
			customerDoctorReleMapper.deleteByCustomerIdAndGroupId(customerUuid, "");
		}
		// 删除该患者的所有随访记录信息
		List<VisitRecord> visits = visitRecordMapper.selectByCustomerUuidAndDoctorUuid(customerUuid, doctorUuid);
		if (visits != null && visits.size() > 0) {
			for (VisitRecord visit : visits) {
				visitRecordMapper.deleteByPrimaryKey(visit.getUuid());
			}
		}
		return ApiResult.right();
	}

	@Override
	public ApiResult selectCustomerByDoctorUuidAndGroupUuid_v2(String doctorUuid, String groupUuid) throws Exception {
		// 根据医生id及分组id获得关系列表
		List<CustomerDoctorRele> customerDoctorReles = new ArrayList<>();
		List<Map<String, Object>> resultlist = new ArrayList<>();
		// 随访方案uuid

		if (!StringUtil.isEmpty(groupUuid)) {
			// 分组id不为空 查询指定分组
			Map<String, Object> map = new HashMap<>();
			map.put("doctorUuid", doctorUuid);
			map.put("groupUuid", groupUuid);
			customerDoctorReles = customerDoctorReleMapper.selectBygroupUuidAndDoctorUuid(groupUuid, doctorUuid);
		} else {
			// 分组id为空，查询所有分组
			// 查出所有分组
			List<CaseGroup> caseGroups = caseGroupMapper.selectByDoctorUuid(doctorUuid);

			// 定义返回List
			List<Map<String, Object>> list = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			map.put("doctorUuid", doctorUuid);
			// 查询随访记录表 (visit_record)
			// List<String> uuids =
			// visitRecordMapper.selectCustomerUuidByDoctorUuid(doctorUuid);
			// 得到医生默认分组的患者信息
			List<CustomerDoctorRele> customerDoctorReleList = customerDoctorReleService
					.selectByDoctorUuidAndGroupUuid(doctorUuid, "0");

			list = getList(customerDoctorReleList, doctorUuid, "");
			Map<String, Object> normaltemp = new HashMap<>();
			normaltemp.put("groupId", "0");
			normaltemp.put("groupName", "默认分组");
			normaltemp.put("customers", list);
			resultlist.add(normaltemp);

			// 得到临时分级信息
			List<Map<String, Object>> listTemp = new ArrayList<>();
			customerDoctorReleList = customerDoctorReleService.selectByDoctorUuidAndGroupUuid(doctorUuid, "-1");

			listTemp = getList(customerDoctorReleList, doctorUuid, "-1");

			normaltemp = new HashMap<>();
			normaltemp.put("groupId", "-1");
			normaltemp.put("groupName", "临时分组");
			normaltemp.put("customers", listTemp);
			resultlist.add(normaltemp);

			// 第二步 查询医生所有的分组
			if (!ObjectUtils.isEmpty(caseGroups)) {
				for (CaseGroup caseGroup : caseGroups) {
					if (!StringUtil.isEmpty(caseGroup.getDoctorUuid())) {
						Map<String, Object> temp = new HashMap<>();

						map.put("doctorUuid", doctorUuid);
						map.put("groupUuid", groupUuid);
						customerDoctorReles = customerDoctorReleMapper
								.selectBygroupUuidAndDoctorUuid(caseGroup.getUuid(), doctorUuid);

						// 定义返回List
						List<Map<String, Object>> relist = new ArrayList<>();

						relist = getList(customerDoctorReles, doctorUuid, "");

						temp.put("groupId", caseGroup.getUuid());
						temp.put("groupName", caseGroup.getGroupName());
						temp.put("customers", relist);
						resultlist.add(temp);
					}
				}
			}
		}
		return ApiResult.right(resultlist);
	}

	// 根据医生患者关系,得到所有患者信息
	public List<Map<String, Object>> getList(List<CustomerDoctorRele> customerDoctorRelesList, String doctorUuid,
			String type)   {
		List<Map<String, Object>> list = new ArrayList<>();
		VisitPrecept visitPrecept = null;
		String visitPreceptUuid = "";
		if (customerDoctorRelesList != null && customerDoctorRelesList.size() > 0) {
			for (CustomerDoctorRele cdr : customerDoctorRelesList) {

				Map<String, Object> save = new HashMap<>();
				String customerUuid = cdr.getCustomerUuid();
				// 通过患者id查询患者基本信息(customer_info)
				CustomerInfo customerInfo = customerInfoService.selectByCustomerUuid(customerUuid);
				if (ObjectUtils.isEmpty(customerInfo)) {
					// 患者uuid
					save.put("customerUuid", "");
					save.put("customerName", "");
					// 患者姓名
					save.put("customerName", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息
					save.put("customerMessage", "");
					// 患者性别
					save.put("sex", "");
					// 患者年龄
					save.put("age", "");
					save.put("applyTime", cdr.getCreateTime());
					// type:-1 临时分组
					if (type.equals("-1")) {
						// 患者未注册 1:未注册,2拒绝
						save.put("status", "1");
					}
				} else {
					// 患者uuid
					save.put("customerUuid", customerUuid);
					// 患者姓名
					save.put("customerName", customerInfo.getRealName());
					// 患者头像
					save.put("image", customerInfo.getImage());
					// 患者信息
					save.put("customerMessage", customerInfo.getIllnessDescription());
					// 患者性别
					save.put("sex", customerInfo.getSex());
					// 患者年龄
					save.put("age", customerInfo.getAge());
					save.put("applyTime", cdr.getCreateTime());

					// 根据delFlag,recommend,医生id,患者id得到随访方案信息
					visitPrecept = visitPreceptService.getMyVisitpreceptByDoctoridAndCustomerId(VisitPreceptStatus.DELFLAG_NORMAL, 
							VisitPreceptStatus.RECOMMEND_CUSTOMER, doctorUuid, customerUuid);
					if (visitPrecept != null) {
						visitPreceptUuid = visitPrecept.getUuid();
					}
					save.put("visitPreceptUuid", visitPreceptUuid);
					// type:-1 临时分组
					if (type.equals("-1")) {
						// 患者未注册 1:未注册,2拒绝
						save.put("status", "1");
					}
				}
				list.add(save);

			}
		}
		return list;
	}

	@Override
	public Map<String, Object> selectCustomerDoctorRele_v2(String uuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCustomer_v2(String id, String uuid, String doctorUuid, String mobile, String name,
			String illnessDescription, String groupId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCustomerNumModel(String doctorUuid) throws Exception {
		return 0;
	}
}
