package com.hxq.mobile.doctor.visit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.doctor.visit.mapper.CaseGroupMapper;
import com.hxq.mobile.doctor.visit.mapper.CustomerDoctorReleMapper;
import com.hxq.mobile.doctor.visit.service.CaseGroupSerivce;
import com.hxq.mobile.entity.common.ConsultRecord;
import com.hxq.mobile.entity.common.Customer;
import com.hxq.mobile.entity.common.CustomerDoctorRele;
import com.hxq.mobile.entity.common.CustomerInfo;
import com.hxq.mobile.entity.visit.CaseGroup;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.visit.service.impl.CaseGroupSerivceImpl")
public class CaseGroupServiceImpl extends SpringJdbcSimpleEntityService implements CaseGroupSerivce {

	@Autowired
	private CaseGroupMapper caseGroupMapper;

	@Autowired
	private CustomerDoctorReleMapper customerDoctorReleMapper;
	
    @Resource(name = "com.hxq.mobile.common.service.CustomerInfoService")
    private CustomerInfoService customerInfoService;
    
    @Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
    private ImgUploadService imgUploadService;

    @Autowired
    private FileService fileService;

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {
		return "h1";
	}

	@Override
	protected String getQueryCacheName() {
		return null;
	}

	@Override
	public List<Map<String, Object>> getByDoctorUuid(String doctorUuid) {
		if (ObjectUtils.isEmpty(doctorUuid)) {
			return null;
		}
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(
				"SELECT UUID as groupId, groupName,note,state,doctorUuid FROM case_group where doctorUuid=? order by opeTime desc");

		return dao.query(sbf.toString(), new Object[] { doctorUuid }, null, getCache());
	}

	@Override
	public ApiResult insertCaseGroup_v2(String doctorUuid, String groupName) throws Exception {
		CaseGroup caseGroup = new CaseGroup();
		caseGroup.setUuid(IdentityHelper.uuid2());
		caseGroup.setDoctorUuid(doctorUuid);
		caseGroup.setCreateTime(DateFormatHelper.getNowTimeStr());
		caseGroup.setGroupName(groupName);
		caseGroup.setDelFlag("1");
		caseGroup.setOpeTime(DateFormatHelper.getNowTimeStr());
		// 添加
		caseGroupMapper.insertSelective(caseGroup);
		return ApiResult.right();
	}

	@Override
	public ApiResult updateCaseGroup_v2(String groupUuid, String groupName) throws Exception {
		CaseGroup caseGroup = caseGroupMapper.selectByPrimaryKey(groupUuid);
		// 分组id不能为空
		if (caseGroup != null) {
			caseGroup.setGroupName(groupName);
			caseGroupMapper.updateByPrimaryKeySelective(caseGroup);
		}
		return ApiResult.right();
	}

	@Override
	public ApiResult deleteCaseGroup_v2(String groupUuid) throws Exception {
		String flag;
		// 判断customerDoctorRele中是否有值
		List<CustomerDoctorRele> list = customerDoctorReleMapper.selectBygroupUuidAndDoctorUuid(groupUuid, null);
		if (list != null && list.size() > 0) {
			flag = "分组下有患者，将删除后移动到默认分组";
			// 删除分组之后 到默认分组里面
			for (CustomerDoctorRele cdr : list) {
				cdr.setGroupUuid("0");
				customerDoctorReleMapper.updateByPrimaryKey(cdr);
			}
		} else {
			flag = "分组下无患者，直接删除";
		}
		// 得到caseGroupModel
		caseGroupMapper.deleteByPrimaryKey(groupUuid);
		return ApiResult.right(flag);
	}

	@Override
	public List<Map<String, Object>> selectConsultRecordByid(String doctorUuid, String consultType) throws Exception {
		if (ObjectUtils.isEmpty(doctorUuid)) {return null;}
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(
				"select DISTINCT(customerUuid) customerUuid from consult_record where doctorUuid = ? and dealState = ? ");

		return dao.query(sbf.toString(), new Object[] { doctorUuid, consultType }, null, getCache());
	}

	@Override
	public Object selectConsultRecordMap(List<Map<String, Object>> onlines,String doctorUuid) throws Exception {
		// 定义返回List
		List onlinelist = new ArrayList();
		Map<String, Object> jsonMap = new HashMap<>();
		Map<String, Object> save = null;
		Map<String, Object> map = null;
		CustomerInfo customerInfo = null;
		Customer customer = null;
		int countNum = 0;
		if (onlines != null && onlines.size() > 0) {
			for (Map<String,Object> customerMap : onlines) {
				save = new HashMap<String, Object>();
				customerInfo = customerInfoService.selectByCustomerUuid((String) customerMap.get("customerUuid"));
				countNum = selectConsultRecordCount((String) customerMap.get("customerUuid"));
				save.put("countNum", countNum);
				customer = (Customer) customerInfoService.select(new Customer((String) customerMap.get("customerUuid")));
				if(!ObjectUtils.isEmpty(customer)){
					save.put("customerName", customer.getCustomerName());	// 患者姓名
				}else{
					save.put("customerName", "");	// 患者姓名
				}
				map = selectConsultRecordByCustomerUuid((String) customerMap.get("customerUuid"),doctorUuid);
				if(!ObjectUtils.isEmpty(map)){
					save.put("createTime", map.get("createTime"));//咨询时间
					save.put("content", map.get("content"));//咨询信息
					save.put("ifread", map.get("ifread"));//是否已读  0 未读  1 已读
				}else{
					save.put("createTime", "");
					save.put("content", "");
					save.put("ifread", "");
				}
				if (customerInfo == null) {
					// 患者id
					save.put("customerUuid", customerMap.get("customerUuid"));
					// 患者性别
					save.put("sex", "");
					// 患者年龄
					save.put("age", "");
					// 患者时间
					save.put("time", "");
					// 患者头像
					save.put("customerImg", "");
					// 患者信息？
					save.put("customerMessage", "");
					onlinelist.add(save);
				} else {
					// 患者性别
					save.put("sex", customerInfo.getSex());
					// 患者年龄
					save.put("age", customerInfo.getAge());
					// 患者时间
					save.put("time", customerInfo.getOpeTime());
					// 患者头像
					Image4App[] urls = CompatibleTools.getImages(imgUploadService, fileService,customerInfo.getImage());
                    if(!ObjectUtils.isEmpty(urls)){
                    	save.put("customerImg", urls[0]);
                    }
					// 患者病情描述
					save.put("customerMessage", customerInfo.getIllnessDescription());
					save.put("customerUuid", customerInfo.getCustomerUuid());
					onlinelist.add(save);
				}
			}
		}
		jsonMap.put("onlinelist", onlinelist);
		jsonMap.put("onlinelistSize", onlines.size());
		return jsonMap;
	}


	@Override
	public Object selectPatientConsultInformation(String customerUuid,String doctorUuid) throws Exception {
	//	List consultList = new ArrayList();
		List<Map<String, Object>> consultMap = selectConsultRecordInformation(customerUuid,doctorUuid);
		ConsultRecord consult = null;
		if (consultMap != null && consultMap.size() > 0) {
			for (Map<String,Object> map : consultMap) {
				consult = new ConsultRecord((String) map.get("uuid"));
				consult.setIfread("1");
				super.update(consult);
			//	consultList.add(consult);
			}
		}
		return "";
	}
	
	@Override
	public List<Map<String, Object>> selectConsultRecordInformation(String customerUuid,String doctorUuid) throws Exception {
		if (ObjectUtils.isEmpty(customerUuid)) {return null;}
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(
				"SELECT uuid,customerUuid,createTime,content,ifread from consult_record where customerUuid = ? and doctorUuid=? and ifread='0'  and iquestion='0' ");
		return dao.query(sbf.toString(), new Object[] {customerUuid,doctorUuid}, null, getCache());
	}
	
	@Override
	public Map<String, Object> selectConsultRecordByCustomerUuid(String customerUuid,String doctorUuid) throws Exception {
		if(ObjectUtils.isEmpty(customerUuid)) return null;
		List<Map<String, Object>> lstReturn = dao.query(
				"SELECT uuid,customerUuid,createTime,content,ifread from consult_record where customerUuid = ?  and doctorUuid=? ORDER BY createTime DESC LIMIT 0,1 ",
				new String[]{customerUuid,doctorUuid}, null, getCache());
		return ObjectUtils.isEmpty(lstReturn) ? null : lstReturn.get(0);
		
	}
	
	@Override
	public int selectConsultRecordCount(String customerUuid) throws Exception {
		if(ObjectUtils.isEmpty(customerUuid)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" SELECT COUNT(distinct uuid) count from consult_record where customerUuid = ? ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{customerUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	@Override
	public int selectUnreadConsultRecord(String doctorUuid) throws Exception {
		if(ObjectUtils.isEmpty(doctorUuid)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" SELECT COUNT(distinct uuid) count from consult_record where doctorUuid = ? and ifread = '0' and consultType='2'  ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{doctorUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

	@Override
	public Object selectDoctorConsultInformation(String doctorUuid) throws Exception {
		List consultList = new ArrayList();
		List<Map<String, Object>> consultMap = selectDoctorConsultRecordInformation(doctorUuid);
		ConsultRecord consult = null;
		if (consultMap != null && consultMap.size() > 0) {
			for (Map<String,Object> map : consultMap) {
				consult = new ConsultRecord((String) map.get("uuid"));
				consult.setIfread("1");
				super.update(consult);
				consultList.add(consult);
			}
		}
		return consultList;
	}
	
	@Override
	public List<Map<String, Object>> selectDoctorConsultRecordInformation(String doctorUuid) throws Exception {
		if (ObjectUtils.isEmpty(doctorUuid)) {return null;}
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(
				"SELECT uuid,customerUuid,createTime,content,ifread from consult_record where doctorUuid = ? ");
		return dao.query(sbf.toString(), new Object[] {doctorUuid}, null, getCache());
	}

	@Override
	public int selectPatientByUnreadConsultRecord(String customerUuid) throws Exception {
		if(ObjectUtils.isEmpty(customerUuid)) return 0;
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" SELECT COUNT(distinct uuid) count from consult_record where customerUuid = ? and ifread = '0'  and consultType='1' ");
		Integer intReturn =  dao.queryForObject(sqlBuff.toString(), new Object[]{customerUuid}, null, Integer.class);
		return intReturn != null ? intReturn.intValue() : 0;
	}

}
