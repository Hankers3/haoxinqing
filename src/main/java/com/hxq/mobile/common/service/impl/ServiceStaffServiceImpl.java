package com.hxq.mobile.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.common.service.TagsService;
import com.hxq.mobile.entity.common.QueryUtil;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.entity.common.Tags;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.DBUtils;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.common.service.ServiceStaffService")
public class ServiceStaffServiceImpl extends SpringJdbcSimpleEntityService implements ServiceStaffService{

	@Resource(name="com.hxq.mobile.common.service.ServiceStaffInfoService")
	private ServiceStaffInfoService servicestaffinfoService;

	@Resource(name = "com.hxq.mobile.common.service.imp.TagsService")
	private TagsService tagService;

	private String TABLE_SERVICESTAFF = "uuid,delFlag,opeTime,oper,activeCode,createTime,date,deviceToken,email,frozenSate,frozenTyp,lastWrongLoginTime,loginErrorTimes,"
			+ "mobile,password,serviceStaffLevel,serviceStaffName,accountAmount,availableAmount,deposit,depositState,depositTime,sate,yonStatus,certificationTyp,certificationApplyTime,"
			+ "certificationTime,certificationNote,auditTime,auditNote,frozenTime,frozenNote,traversetime,status,hospital,department,departmentLine,realName,serialNumber,doctorTag,doctorNo,"
			+ "doctorType,regState,totalPrice,serviceTimes";
		
	private static String SERVICE_STAFF = "o.uuid,o.realName,o.mobile,o.doctorNo,o.email,o.departmentLine,o.sate,o.frozenSate,o.frozenTyp,o.regState,o.activeCode,"
			+ "o.loginErrorTimes,o.createTime,o.deviceToken,o.frozenNote,"
	+"s.image,s.certImage,o.hospital,o.department,s.sex,o.serviceTimes,s.professional,p.hospitalName,q.departmentName";
	
	/**
	 * 获得名医
	 */
	@Override
	public List<Map<String, Object>> selectFamousDoctors() {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.uuid, a.hospital, a.department, a.realName, b.image, b.territory, b.professional")
		.append(" from service_staff as a left join service_staff_info as b on a.uuid=b.serviceStaffUuid")
		.append(" where a.sate='1' and a.doctorType='1' order by a.sort asc,a.createTime desc");
		return dao.query(sbf.toString(), null, null, getQueryCache());
	}

	/**
	 * 通过查询条件得到医生List
	 */
	@Override
	public  Object[] selectDoctors(Map<String, Object> form) {

		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<>();

		String realName = RequestUtil.getFormValue(form, "doctorConditon");//医生真实姓名
		String pageNo = RequestUtil.getFormValue(form, "pageNo");
		String pageCount = RequestUtil.getFormValue(form, "pageCount");
		String area = RequestUtil.getFormValue(form, "city");
		String strTerritorys = RequestUtil.getFormValue(form, "territorys");// 医生标签
		String strHospitalUuids = RequestUtil.getFormValue(form, "hospitalUuids");
		String[] hospitalUuids = null;
		if (!ObjectUtils.isEmpty(strHospitalUuids)) {
			hospitalUuids = strHospitalUuids.split(",");
		}

		sbf.append(" from service_staff as o LEFT JOIN service_staff_info as s on o.uuid=s.serviceStaffUuid")
		.append(" LEFT JOIN hospital_info as p on p.uuid=o.hospital")
		.append(" LEFT JOIN department_info as q on q.uuid=o.department")
		.append(" where o.sate='1' and o.delFlag='1' and o.frozenSate ='0' ");
		if (!ObjectUtils.isEmpty(area)) {
			sbf.append(" and (s.city =? or s.region=? or s.province=?)");
			columns.add(area);
			columns.add(area);
			columns.add(area);
		}

		if(!ObjectUtils.isEmpty(realName)){
			sbf.append(" and (o.serviceStaffName like ? or o.realName like ?)");
			String str = (new StringBuffer(realName)).append("%").toString();
			columns.add(str);
			columns.add(str);
		}
		
		if (!ObjectUtils.isEmpty(hospitalUuids)) {
			sbf.append(" and (").append(DBUtils.makeMultiLikeSQL(hospitalUuids, "o.hospital", false, true)).append(")");
		}
		if (!ObjectUtils.isEmpty(strTerritorys)) {
			sbf.append(" and (").append(DBUtils.makeMySqlFindInSetMultiSQL(
					strTerritorys.split(","), "s.territory", true, true)).append(")");
		}

		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();

		sbf.delete(0, sbf.length());
		String strQuery = sbf.append("select o.uuid,o.realName,s.image,o.hospital,o.department,s.sex,o.serviceTimes,s.professional,p.hospitalName,q.departmentName")
				.append(strWhere).append(" order by o.sort ASC,o.createTime desc").toString();

		Object[] params = columns.isEmpty() ? null : columns.toArray();
//		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), form);
//		return page.queryByPage(strTotal, strQuery, params,	null);
		
//		Object[] params = args.isEmpty() ? null :args.toArray();
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[]{lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows()}; 
	}

	@Override
	public int updateDoctorAndInfo(ServiceStaff doctor, ServiceStaffInfo info) throws Exception {
		//doctor.setSate("3");//审核状态：0待审核/未认证； 1审核通过/已认证； 2未通过审核/未通过认证；3认证中
		super.update(doctor);
		int intReturn = 0;
		ServiceStaffInfo old = servicestaffinfoService.selectByServiceStaffUuid(doctor.getUuid());
		if(null == old){
			info.setUuid(IdentityHelper.uuid2());
			info.setServiceStaffUuid(doctor.getUuid());
			intReturn = super.insert(info);
		} else {
			info.setUuid(old.getUuid());
			intReturn = super.update(info);
		}
		return intReturn;
	}
	
	/**
	 * 更新医生信息
	 * @param doctor
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateDoctor(ServiceStaff doctor) throws Exception{
		return  super.update(doctor);
		
	}

	@Override
	public ServiceStaff selectServiceStaffModelByMobile(String mobile) throws Exception {
		if(ObjectUtils.isEmpty(mobile)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT ").append(TABLE_SERVICESTAFF).append(" FROM service_staff WHERE mobile=? LIMIT 0,1");
		List<Map<String, Object>> lst = dao.query(sbf.toString(),
				new Object[]{StringUtils.trimToEmpty(mobile)}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), ServiceStaff.class);
	}
	
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "ServiceStaffService";}


	@Override
	public List<ServiceStaff> queryServiceStaff(ServiceStaff serviceStaff, QueryUtil query) {
		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();
		sbf.append(" select ");
		sbf.append(" o.uuid, o.realName,o.mobile,o.email,p.hospitalName,info.professional,info.territory as serviceStaffTerritory, ");
		sbf.append(" o.departmentLine,o.hospital,o.department,o.createTime,sate,d.departmentName,o.frozenSate,o.sort,");
		sbf.append(" CONCAT_WS('',province.`provinceName`,city.`cityName`,region.`regionName`) as address ");

		sbf.append(" from service_staff as o ");
		sbf.append(" left join department_info as d on d.uuid=o.department");
		sbf.append(" left join hospital_info as p on p.uuid=o.hospital");
		sbf.append(" LEFT JOIN service_staff_info info ON o.uuid = info.`serviceStaffUuid` ");
		sbf.append(" LEFT JOIN basicdata_area_province province ON  info.`province` = province.`uuid` ");
		sbf.append(" LEFT JOIN basicdata_area_city city ON  info.`city` = city.`uuid` ");
		sbf.append(" LEFT JOIN basicdata_area_region region ON  info.`region` = region.`uuid`");

		sbf.append(" where  regState = 1 ");
		if (!ObjectUtils.isEmpty(serviceStaff.getSate())) {
			sbf.append(" and sate in (").append(serviceStaff.getSate()).append(")");
//			args.add(serviceStaff.getSate());
		}
		if (!ObjectUtils.isEmpty(serviceStaff.getRealName())) {
			sbf.append(" and o.realName like '%").append(serviceStaff.getRealName()).append("%'");
		//	args.add(serviceStaff.getRealName());
		}
		if (!ObjectUtils.isEmpty(serviceStaff.getMobile())) {
			sbf.append(" and o.mobile like '%").append(serviceStaff.getMobile()).append("%'");
//			args.add(serviceStaff.getMobile());
		}
		if (!ObjectUtils.isEmpty(serviceStaff.getEmail())) {
//			sbf.append(" and email like '%?%'");
			sbf.append(" and o.email like '%").append(serviceStaff.getEmail()).append("%'");
//			args.add(serviceStaff.getEmail());
		}
		if (!ObjectUtils.isEmpty(serviceStaff.getHospital())) {
			sbf.append(" and o.hospital = ? ");

			args.add(serviceStaff.getHospital());
		}
		if(!ObjectUtils.isEmpty(serviceStaff.getDoctorType())){
			sbf.append(" and o.doctorType = ? ");

			args.add(serviceStaff.getDoctorType());
		}
		sbf.append(" order by ").append(query.getSortName()).append(" ").append(query.getSortType());
		sbf.append(" limit ").append(query.getStart()).append(",").append(query.getLength());

		Object[] params = args.isEmpty() ? null : args.toArray();
		List<Map<String, Object>> list = dao.query(sbf.toString(), params, null, getQueryCache());
		return ObjectUtils.isEmpty(list) ? null : SimpleBean2DBHelper.mapList2BeanList(list, ServiceStaff.class);

	}


	
	/**
	 * 查询符合条件的数据总条数
	 * 
	 * @param serviceStaff
	 * @return
	 */
	@Override
	public long queryCount(ServiceStaff serviceStaff) {
		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();
		sbf.append(" select  count(*) as totalCount ");

		sbf.append(" from service_staff ");

		sbf.append(" where  regState = 1 ");
		if (!ObjectUtils.isEmpty(serviceStaff.getSate())) {
			sbf.append(" and sate in (").append(serviceStaff.getSate()).append(")");
//			args.add(serviceStaff.getSate());
		}
		if (!ObjectUtils.isEmpty(serviceStaff.getRealName())) {
			sbf.append(" and realName like '%").append(serviceStaff.getRealName()).append("%'");
		//	args.add(serviceStaff.getRealName());
		}
		if (!ObjectUtils.isEmpty(serviceStaff.getMobile())) {
			sbf.append(" and mobile like '%").append(serviceStaff.getMobile()).append("%'");
//			args.add(serviceStaff.getMobile());
		}
		if (!ObjectUtils.isEmpty(serviceStaff.getEmail())) {
//			sbf.append(" and email like '%?%'");
			sbf.append(" and email like '%").append(serviceStaff.getEmail()).append("%'");
//			args.add(serviceStaff.getEmail());
		}
		
		if (!ObjectUtils.isEmpty(serviceStaff.getHospital())) {
			sbf.append(" and hospital = ? ");
			args.add(serviceStaff.getHospital());
		}
		if(!ObjectUtils.isEmpty(serviceStaff.getDoctorType())){
			sbf.append(" and doctorType = ? ");

			args.add(serviceStaff.getDoctorType());
		}

		Object[] params = args.isEmpty() ? null : args.toArray();
		List<Map<String, Object>> list = dao.query(sbf.toString(), params, null, getQueryCache());
		long count = 0;
		if (list != null && list.size() > 0) {
			count = (Long)list.get(0).get("totalCount");
		}
		return count;
	}

	/**
	 * 根据uuid获取医生信息
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	@Override
	public ServiceStaff getServiceStaffByUuid(String uuid) throws Exception {

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<>();

		sbf.append("select ").append(SERVICE_STAFF);
		sbf.append(" from service_staff as o LEFT JOIN service_staff_info as s on o.uuid=s.serviceStaffUuid")
				.append(" LEFT JOIN hospital_info as p on p.uuid=o.hospital")
				.append(" LEFT JOIN department_info as q on q.uuid=o.department").append(" where ");

		if (!ObjectUtils.isEmpty(uuid)) {
			sbf.append("  o.uuid=? ");
			args.add(uuid);

		}

		Object[] params = args.isEmpty() ? null : args.toArray();

		List<Map<String, Object>> list = dao.query(sbf.toString(), params, null, getQueryCache());

		ServiceStaff serviceStaff = null;

		if (list != null && list.size() > 0) {
			serviceStaff = ObjectUtils.isEmpty(list) ? null
					: SimpleBean2DBHelper.map2Bean(list.get(0), ServiceStaff.class);

			String tags = serviceStaff.getDoctorTag();
			List<Tags> tagList = tagService.getTagsList(tags);
			serviceStaff.setDoctorTags(tagList);
		}
		return serviceStaff;
	}


}
