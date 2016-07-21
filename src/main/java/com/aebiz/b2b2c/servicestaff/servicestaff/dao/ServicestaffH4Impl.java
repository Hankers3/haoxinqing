package com.aebiz.b2b2c.servicestaff.servicestaff.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.ServicestaffinfoModel;
import com.aebiz.b2b2c.servicestaff.servicestafflevel.service.ServicestafflevelService;

@Repository
public class ServicestaffH4Impl extends BaseH4Impl<ServicestaffModel, ServicestaffQueryModel>
		implements ServicestaffDAO {
	/* 平台家政员等级信息service */
	@Autowired
	private ServicestafflevelService servicestafflevelService;
	/* 医生所在医院的service */
	@Autowired
	private HospitalInfoService hospitalInfoService;

	/**
	 * 关联表查询
	 */
	@Override
	protected String getMultiModel() {
		return ",ServicestaffinfoModel as s ";
	}

	/**
	 * 拼接查询返回值
	 */
	@Override
	protected String getMultiSelect() {

		return ", s";
	}

	/**
	 * 遍历循环对象值
	 */
	@Override
	protected List<ServicestaffModel> exeResultList(List<Object[]> tempList) {
		List<ServicestaffModel> list = new ArrayList<ServicestaffModel>();

		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {
				ServicestaffModel sm = (ServicestaffModel) obj[0];
				if (sm != null) {
					ServicestaffinfoModel sim = (ServicestaffinfoModel) obj[1];
					if (!StringUtil.isEmpty(sim.getRegionName())) {
						sm.setAddress(sim.getRegionName());
					} else {

						sm.setAddress("");
					}

					list.add(sm);
				}
			}
		}
		return list;
	}

	/* 添加sql语句 */
	@Override
	protected String getAppendHql(ServicestaffQueryModel qm) {
		StringBuffer hql = new StringBuffer("");

		hql.append(" and o.uuid = s.serviceStaffUuid ");
		if (!StringUtil.isEmpty(qm.getServiceStaffinfoCertCode())) {
			hql.append(" and s.certCode like:certCode ");
		}

		if (!StringUtil.isEmpty(qm.getServiceStaffinfoSex())) {
			hql.append(" and s.sex like:sex ");
		}

		// 查询待审核医生
		if (!StringUtil.isEmpty(qm.getStatus()) && qm.getStatus().equals("1")) {
			hql.append(" and o.sate =:sate ");
		}

		// 查询待认证医生
		if (!StringUtil.isEmpty(qm.getCertificationType()) && qm.getCertificationType().equals("1")) {
			hql.append(" and o.certificationTyp !=:certificationTyp ");
		}
		// 省
		if (!StringUtil.isEmpty(qm.getProvince())) {
			hql.append(" and s.province =:province ");
		}

		// 市
		if (!StringUtil.isEmpty(qm.getCity())) {
			hql.append(" and s.city =:city ");
		}

		// 查在医生的uuid数组里的数据
		if (qm.getDoctorUuids() != null && qm.getDoctorUuids().size() > 0) {
			hql.append(" and o.uuid like:doctorUuids ");
		}
		// 判断医生是否是名医风采的医生
		if (!StringUtil.isEmpty(qm.getDoctorType())) {
			if (qm.getDoctorType().equals("1")) {
				hql.append(" and o.doctorType =:doctorType ");
			} else {
				hql.append(" and (o.doctorType !=:doctorType or o.doctorType is null) ");
			}
		}

		// 医生标签
		if (!StringUtil.isEmpty(qm.getCheckTags())) {
			String[] doctorTags = StringUtils.split(qm.getCheckTags(), ",");
			hql.append(" and (  ");
			for (int i = 0; i < doctorTags.length; i++) {
				hql.append("  o.doctorTag like:doctorTag or");
				if (i == doctorTags.length - 1) {
					hql.append("  o.doctorTag like:doctorTag ");
				}
			}
			hql.append(" )");

		}

		// 医生所在医院
		if (!StringUtil.isEmpty(qm.getHospitaln())) {
			hql.append(" and o.hospital in(:hospital) ");
		}
		if (qm != null) {
			String sortName = qm.getSortName();
			if(!StringUtil.isEmpty(sortName)&&!sortName.equals("uuid")){
				hql.append(" order by o.").append(qm.getSortName()).append(" ").append(qm.getSortType());
			}else{
				hql.append(" order by o.createTime desc");
			}
		} else {
			hql.append(" order by o.createTime desc");
		}

		return hql.toString();
	}

	/* 为sql语句赋值 */
	@Override
	protected void setAppendHqlValue(ServicestaffQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getServiceStaffinfoCertCode())) {
			q.setString("certCode", "%" + qm.getServiceStaffinfoCertCode() + "%");
		}

		if (!StringUtil.isEmpty(qm.getServiceStaffinfoSex())) {
			q.setString("sex", "%" + qm.getServiceStaffinfoSex() + "%");
		}
		// 判断医生是否是名医风采的医生
		if (!StringUtil.isEmpty(qm.getDoctorType())) {
			q.setString("doctorType", "1");
		}

		if (!StringUtil.isEmpty(qm.getStatus()) && qm.getStatus().equals("1")) {
			q.setString("sate", "1");
		}

		if (!StringUtil.isEmpty(qm.getCertificationType()) && qm.getCertificationType().equals("1")) {
			q.setString("certificationTyp", "1");
		}

		if (!StringUtil.isEmpty(qm.getProvince())) {
			q.setString("province", qm.getProvince());
		}
		if (!StringUtil.isEmpty(qm.getCheckTags())) {
			String[] doctorTags = StringUtils.split(qm.getCheckTags(), ",");
			for (int i = 0; i < doctorTags.length; i++) {
				q.setString("doctorTag", "%" + doctorTags[i] + "%");
			}

		}

		if (!StringUtil.isEmpty(qm.getHospitaln())) {
			List<String> uuids = hospitalInfoService.getAllUuidsByHospitalName(qm.getHospitaln());
			if (null != uuids && uuids.size() > 0) {
				q.setParameterList("hospital", uuids);
			} else {
				String[] hosipital = { "ABCV", "cBCV" };
				q.setParameterList("hospital", hosipital);

			}
		}

		if (qm.getDoctorUuids() != null && qm.getDoctorUuids().size() > 0) {
			// doctorUuids
			q.setParameterList("doctorUuids", qm.getDoctorUuids().toArray());
		}
	}
	/**
	 * 根据传入的用户名判断该用户名是否已经存在
	 * 
	 * @param serviceStaffName
	 *            服务人员用户名
	 * @return
	 */
	public boolean checkServiceStaffNameExist(String serviceStaffName) {
		StringBuffer hql = new StringBuffer("from ServicestaffModel where serviceStaffName=:serviceStaffName");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffName", serviceStaffName);

		List<ServicestaffModel> list = query.list();
		if (list != null && list.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 根据传入的手机号判断手机号是否已经存在
	 * 
	 * @param mobile
	 *            手机号
	 * @return
	 */
	public boolean checkMobileExist(String mobile) {
		StringBuffer hql = new StringBuffer(" from ServicestaffModel where mobile=:mobile");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("mobile", mobile);

		List<ServicestaffModel> list = query.list();
		if (list != null && list.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 检查手机号和密码
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */

	public ServicestaffModel checkMobileAndPassword(String mobile, String password) {

		StringBuffer hql = new StringBuffer(
				" from ServicestaffModel where 1=1 and mobile=:mobile and password=:password");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("mobile", mobile);
		query.setString("password", password);
		List<ServicestaffModel> list = query.list();
		if (list != null && list.size() > 0) {
			ServicestaffModel servicestaffModel = list.get(0);
			if (servicestaffModel != null) {
				return servicestaffModel;
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	/**
	 * 通过传入的uuid得到手机号
	 * 
	 * @param uuid
	 * @return
	 */
	public String getMobileByUuid(String uuid) {
		StringBuffer hql = new StringBuffer("select s.mobile from ServicestaffModel s where 1=1 and uuid=:uuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			String mobile = list.get(0);
			if (!StringUtil.isEmpty(mobile)) {
				return mobile;
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 会员解冻:<br/>
	 * 冻结状态1表示冻结，0表示未冻结<br/>
	 * 需要记录操作人,将冻结状态设置为0，冻结类型设置为空<br/>
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @param oper
	 *            操作人
	 */
	public void unfrozen(List<String> servicestaffUuids, String frozenSate) {

		if (servicestaffUuids != null && servicestaffUuids.size() > 0) {
			for (int i = 0; i < servicestaffUuids.size(); i++) {

				StringBuffer hql = new StringBuffer(" update ServicestaffModel set frozenSate=:frozenSate");
				hql.append(" where uuid=:servicestaffUuid");

				Query query = this.getH4Session().createQuery(hql.toString());
				query.setString("frozenSate", frozenSate);

				String servicestaffUuid = servicestaffUuids.get(i);
				query.setString("servicestaffUuid", servicestaffUuid);

				query.executeUpdate();
			}
		}
	}

	private List getByCondition(boolean onlyUuids, boolean needPage, ServicestaffQueryModel qm, int start,
			int pageShow) {
		String hql = "select o" + getMultiSelect() + " from ServicestaffModel as o " + getMultiModel() + " where 1=1 ";

		if (onlyUuids) {
			hql = "select o.uuid from ServicestaffModel o " + getMultiModel() + " where 1=1 ";
		}

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);
		if (needPage) {
			q.setFirstResult(start);
			q.setMaxResults(pageShow);
		}

		if (onlyUuids) {
			return q.list();
		}
		if ((getMultiSelect() != null) && (getMultiSelect().trim().length() > 0)) {
			List<Object[]> tempList = q.list();
			return exeResultList(tempList);
		}
		List<ServicestaffModel> returnList = new ArrayList<ServicestaffModel>();
		List<ServicestaffModel> retList = q.list();
		if (!StringUtil.isEmpty(qm.getPlanUuid())) {
			if (retList != null && retList.size() > 0) {
				for (int i = 0; i < retList.size(); i++) {
					ServicestaffModel staff = retList.get(i);
					returnList.add(staff);
				}
			}
		} else {
			return retList;
		}
		return returnList;
	}

	@Override
	public List<ServicestaffModel> getByCondition(ServicestaffQueryModel qm, int start, int pageShow) {

		return getByCondition(false, true, qm, start, pageShow);
	}

	/**
	 * 根据订单号计算订单需要家政员的数量
	 * 
	 * @param orderId
	 * @return
	 */
	public int getServiceStaffCountsByOrderId(String orderId) {
		StringBuffer hql = new StringBuffer("select o.needNumbers from OrderMainModel o where 1=1");
		hql.append(" and o.uuid=:uuid");
		Query query = getH4Session().createQuery(hql.toString());
		query.setString("uuid", orderId);
		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 修改医生审核状态
	 * 
	 * @param uuid
	 * @param state
	 */
	public void unpdateState(String uuid, String state, String auditNote) {
		StringBuffer hql = new StringBuffer(" update ServicestaffModel  set sate=:sate and auditNote=:auditNote ");
		hql.append(" where uuid=:servicestaffUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("sate", state);
		query.setString("auditNote", auditNote);
		query.setString("servicestaffUuid", uuid);

		query.executeUpdate();

	}

	/**
	 * 通过编号获取医生真实名称
	 * 
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public String getServiceStaffNameByUuid(String serviceStaffUuid) {
		StringBuffer hql = new StringBuffer(" select o.realName from ServicestaffModel as o where 1=1 ");
		hql.append(" and o.uuid=:serviceStaffUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffUuid", serviceStaffUuid);

		List list = q.list();
		if (list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	/**
	 * 通过家政员名字来获取相应的Uuids
	 * 
	 * @param serviceStaffName
	 * @return
	 */
	@Override
	public List getServiceStaffUuids(String serviceStaffName) {
		StringBuffer hql = new StringBuffer(" select o.uuid from ServicestaffModel as o where 1=1 ");
		hql.append(" and o.serviceStaffName like :serviceStaffName");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffName", "%" + serviceStaffName + "%");

		return q.list();
	}

	/**
	 * 通过家政员名字来获取相应的
	 * 
	 * @param serviceStaffName
	 * @return
	 */
	public List getServiceStaffUuidsByMobile(String mobile) {

		StringBuffer hql = new StringBuffer(" select o.uuid from ServicestaffModel as o where 1=1 ");
		hql.append(" and o.mobile like :mobile");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("mobile", "%" + mobile + "%");

		return q.list();

	}

	/**
	 * 通过编号获取家政员对象
	 * 
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public ServicestaffModel getServicestaffModelByUuid(String serviceStaffUuid) {
		StringBuffer hql = new StringBuffer("from ServicestaffModel where uuid=:serviceStaffUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffUuid", serviceStaffUuid);

		List<ServicestaffModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 获得所有的关联的等级uuid
	 * 
	 * @param serviceStaffUuids
	 * @return
	 */

	public List<String> getServiceStaffLevelUuids(List<String> serviceStaffUuids) {
		StringBuffer hql = new StringBuffer(
				"select s.serviceStaffLevel from ServicestaffModel as s where s.uuid in(:uuids) ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setParameterList("uuids", serviceStaffUuids.toArray());

		return (List<String>) q.list();
	}

	/**
	 * 通过家政员等级来获得所有家政员UUids
	 * 
	 * @param staffLevelUuid
	 * @return
	 */

	public List<String> getServiceStaffUuidsByLevelUuid(String staffLevelUuid) {
		StringBuffer hql = new StringBuffer("select  sm.uuid  from ServicestaffModel as sm where ");
		hql.append(" sm.serviceStaffLevel=:serviceStaffLevel ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffLevel", staffLevelUuid);

		return (List<String>) q.list();
	}

	/**
	 * 修改家政员d冻结状态
	 * 
	 * @param uuid
	 * @param state
	 */
	@Override
	public void unpdateFreezeState(String uuid, String FreezeState) {
		StringBuffer hql = new StringBuffer(" update ServicestaffModel set frozenSate=:frozenSate");
		hql.append(" where uuid=:servicestaffUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("frozenSate", FreezeState);
		query.setString("servicestaffUuid", uuid);

		query.executeUpdate();

	}

	/**
	 * 通过账户或者邮箱或者手机号名称查询出该家政员所有信息
	 * 
	 * @author zdx
	 */
	@Override
	public ServicestaffModel getServicestaffByLoginNameOrMobileOrEmail(String lonigNameOrMobileOrEmail) {
		StringBuffer hql = new StringBuffer(" from ServicestaffModel s where s.serviceStaffName =:serviceStaffName ");
		hql.append(" or s.mobile =:mobile ");
		hql.append(" or s.email =:email ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("serviceStaffName", lonigNameOrMobileOrEmail);
		query.setString("mobile", lonigNameOrMobileOrEmail);
		query.setString("email", lonigNameOrMobileOrEmail);
		List<ServicestaffModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据手机号获取对应的编号
	 * 
	 * @param mobile
	 * @return
	 */
	@Override
	public List<String> getUuidsByMobile(String mobile) {
		StringBuffer hql = new StringBuffer(" select o.uuid  from ServicestaffModel  as o ");
		hql.append(" where o.mobile like:mobile ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("mobile", "%" + mobile + "%");

		return q.list();
	}

	/**
	 * 查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	@Override
	public int getSchedulCount(ServicestaffQueryModel qm) {
		StringBuffer hql = new StringBuffer(
				" select count(o) from ServicestaffModel as o where o.frozenSate=:frozenSate and o.sate=:sate ");

		hql.append(getAppendHqlSchedul(qm));

		Query q = this.getH4Session().createQuery(hql.toString());
		setAppendHqlValueSchedul(qm, q);

		Number num = (Number) q.uniqueResult();

		return num.intValue();
	}

	/**
	 * 查询医生列表数据
	 * 
	 * @param qm
	 * @param start
	 * @param pageShow
	 * @return
	 */
	public List getListByCondition(ServicestaffQueryModel qm, int start, int pageShow) {

		StringBuffer hql = new StringBuffer(
				" select o from ServicestaffModel as o where o.frozenSate=:frozenSate and o.sate=:sate ");

		hql.append(getAppendHqlSchedul(qm));

		Query q = getH4Session().createQuery(hql.toString());
		setAppendHqlValueSchedul(qm, q);
		q.setFirstResult(start);
		q.setMaxResults(pageShow);

		List<ServicestaffModel> retList = q.list();
		if (retList != null && retList.size() > 0) {
			return retList;
		}
		return null;

	}

	/**
	 * 拼接sql语句
	 * 
	 * @param qm
	 * @return
	 */
	public String getAppendHqlSchedul(ServicestaffQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");

		// 用户 姓名
		if (!StringUtil.isEmpty(qm.getServiceStaffName())) {
			hql.append(" and o.serviceStaffName like:serviceStaffName");
		}

		// 手机号码
		String mobile = qm.getMoblie();
		if (!StringUtil.isEmpty(mobile)) {
			hql.append(" and o.mobile like:mobile");
		}

		hql.append(" order by o.opeTime desc ");

		return hql.toString();

	}

	/**
	 * 
	 * 必要的参数值
	 * 
	 * @param qm
	 * @param q
	 */
	public void setAppendHqlValueSchedul(ServicestaffQueryModel qm, Query q) {
		// 审核通过
		q.setString("sate", ServicestaffModel.SERVICESTAFF_SATE_PASS);
		// 未冻结
		q.setString("frozenSate", ServicestaffModel.SERVICESTAFF_FROZENSATE_UNFROZENED);

		if (!StringUtil.isEmpty(qm.getServiceStaffName())) {
			q.setString("serviceStaffName", "%" + qm.getServiceStaffName().trim() + "%");
		}

		if (!StringUtil.isEmpty(qm.getServiceStaffName())) {
			q.setString("serviceStaffName", "%" + qm.getServiceStaffName().trim() + "%");
		}

		String mobile = qm.getMoblie();
		if (!StringUtil.isEmpty(mobile)) {
			q.setString("mobile", "%" + mobile.trim() + "%");
		}

	}

	/**
	 * 查询全部登录状态并且冻结状态的家政员的uuid
	 * 
	 * @param qm
	 * @param frozenstate
	 *            家政员状态
	 * @param state
	 *            冻结状态
	 * @return hdf
	 */
	@Override
	public List<String> getUuidsAboutloginAndUnforzen(String frozenstate, String state) {
		StringBuffer hql = new StringBuffer(
				" select  sm.uuid  from ServicestaffModel as sm ,StaffLoginStatusModel as slm where sm.uuid=slm.serviceStaffUuid");
		hql.append(" and slm.status=:frozenSate");
		hql.append(" and sm.sate=:state");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("frozenSate", frozenstate);
		q.setString("state", state);

		return (List<String>) q.list();
	}

	/**
	 * 获取图文咨询数量接口 咨询类型 0在线咨询即图文咨询 1电话咨询 2预约加号
	 */
	@Override
	public int getOnlineCount(String uuid, String type) {
		String hql = "select count(crm.uuid) from ConsultRecordModel crm where crm.consultType=:type and crm.uuid=:uuid";
		Query query = getH4Session().createQuery(hql);
		// 赋值
		query.setString("type", type);
		query.setString("uuid", uuid);
		Number obj = (Number) query.uniqueResult();
		if (obj != null)
			return obj.intValue();
		else
			return 0;
	}

	/**
	 * 查看电话咨询是否开通
	 */
	@Override
	public String getTelState(String uuid) {
		String hql = "select drm.phone from DoctorRightModel drm where drm.doctorUuid=:uuid";
		Query query = getH4Session().createQuery(hql);
		// 赋值
		query.setString("uuid", uuid);
		String obj = (String) query.uniqueResult();
		if (obj != null)
			// 结果不为空 返回状态，0没开通，1开通
			return obj;
		else
			// 结果为空 返回没开通
			return "0";
	}

	/**
	 * 查看加号咨询是否开通
	 */
	@Override
	public String getOrderState(String uuid) {
		String hql = "select drm.plus from DoctorRightModel drm where drm.doctorUuid=:uuid";
		Query query = getH4Session().createQuery(hql);
		// 赋值
		query.setString("uuid", uuid);
		String obj = (String) query.uniqueResult();
		if (obj != null)
			// 结果不为空 返回状态，0没开通，1开通
			return obj;
		else
			// 结果为空 返回没开通
			return "0";
	}

	/**
	 * 查看私人医生是否开通
	 */
	@Override
	public String getPersonalState(String uuid) {
		String hql = "select drm.personal from DoctorRightModel drm where drm.doctorUuid=:uuid";
		Query query = getH4Session().createQuery(hql);
		// 赋值
		query.setString("uuid", uuid);
		String obj = (String) query.uniqueResult();
		if (obj != null)
			// 结果不为空 返回状态，0没开通，1开通
			return obj;
		else
			// 结果为空 返回没开通
			return "0";
	}

	/**
	 * 获得随访患者数量
	 */
	@Override
	public int getvisitCount(String uuid) {
		String hql = "select count(vrm.uuid) from VisitRecordModel vrm where vrm.serviceStaffUuid=:serviceStaffUuid";
		Query query = getH4Session().createQuery(hql);
		// 赋值
		query.setString("serviceStaffUuid", uuid);
		Number obj = (Number) query.uniqueResult();
		if (obj != null)
			// 结果不为空 返回状态，0没开通，1开通
			return obj.intValue();
		else
			// 结果为空 返回没开通
			return 0;
	}

	@Override
	public String getDoctorNoByUuid(String serviceStaffUuid) {
		StringBuffer hql = new StringBuffer(" select o.doctorNo from ServicestaffModel as o where 1=1 ");
		hql.append(" and o.uuid=:serviceStaffUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffUuid", serviceStaffUuid);

		List list = q.list();
		if (list.size() > 0) {
			return (String) list.get(0);
		}
		return null;
	}

	/**
	 * 获得医生余额
	 */
	@Override
	public double getAccountAmountByUuid(String userUuid) {
		StringBuffer hql = new StringBuffer(" select o.accountAmount from ServicestaffModel as o where 1=1 ");
		hql.append(" and o.uuid=:serviceStaffUuid");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("serviceStaffUuid", userUuid);

		List list = q.list();
		if (list.size() > 0) {
			return (double) list.get(0);
		}
		return 0.0D;
	}

	/**
	 * 批量设置医生是否名医风采
	 * 
	 * @param doctorUuids
	 * @param doctorType
	 */
	public void updateDoctorType(List<String> doctorUuids, String doctorType) {

		StringBuffer hql = new StringBuffer(" update o ServicestaffModel as o  set o.doctorType=:doctorType ");
		hql.append(" where o.uuid in (:uuids)");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorType", doctorType);
		q.setParameterList("uuids", doctorUuids.toArray());

		q.executeUpdate();
	}

	/**
	 * 获得名医
	 * 
	 * @return
	 */
	@Override
	public List<ServicestaffModel> getFamousDoctors() {
		StringBuffer hql = new StringBuffer(" select o , s from ServicestaffModel as o ,ServicestaffinfoModel as s  where o.sate='1' ");
		hql.append(" and  o.uuid=s.serviceStaffUuid  ");
		hql.append(" and o.doctorType=:doctorType");
		hql.append(" and o.doctorType=:doctorType");
		hql.append(" order by  o.createTime desc ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("doctorType", "1"); // 1是名医风采的类型 ； 2不是名医风采的类型
		List list = q.list();
		List<ServicestaffModel> reList = new ArrayList<ServicestaffModel>();
		if (list !=null && list.size() > 0) {
			for(int i=0;i<list.size();i++ ){
				Object[] object = (Object[]) list.get(i);
				if(object !=null){
					ServicestaffModel sm = (ServicestaffModel) object[0];
					ServicestaffinfoModel sim = (ServicestaffinfoModel) object[1];
					sm.setImgUrl(sim.getImgUrl());
					sm.setSex(sim.getSex());
					sm.setTerritory(sim.getTerritory());
					reList.add(sm);
				}
			}
			return reList;
		}
		return null;
	}

	/**
	 * 通过查询条件得到医生List
	 * 
	 * @param doctorName
	 * @param departmentUuid
	 * @return
	 */
	@Override
	public List<ServicestaffModel> getDoctors(String doctorConditon) {
		StringBuffer hql = new StringBuffer(" select o, s , d.phone ,d.teletext ,d.plus ,d.personal  from ServicestaffModel as o , ServicestaffinfoModel as s , DoctorRightModel as d  ");
		hql.append(" where o.sate='1'");
		hql.append(" and  o.uuid=s.serviceStaffUuid and o.uuid = d.doctorUuid ");

		if (!StringUtil.isEmpty(doctorConditon)) {
			hql.append(" and (o.realName like:realName or o.doctorTag like:doctorTag ) ");
		} 
		hql.append(" order by o.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setFirstResult(0);
		q.setMaxResults(30);
		if (!StringUtil.isEmpty(doctorConditon)) {
			q.setString("realName", "%" + doctorConditon + "%");
			q.setString("doctorTag", "%" + doctorConditon + "%");
		}
		
		List list = q.list();
		List<ServicestaffModel> reList = new ArrayList<ServicestaffModel>();
		if (list !=null && list.size() > 0) {
			for(int i=0;i<list.size();i++ ){
				Object[] object = (Object[]) list.get(i);
				if(object !=null){
					ServicestaffModel sm = (ServicestaffModel) object[0];
					ServicestaffinfoModel sim = (ServicestaffinfoModel) object[1];
					String  phone = (String) object[2];
					String  teletext = (String) object[3];
					String  plus = (String) object[4];
					String  personal = (String) object[5];
					
					if(!StringUtil.isEmpty(sim.getSex())){
						sm.setSex(sim.getSex());
					}
					if(!StringUtil.isEmpty(sim.getImgUrl())){
						sm.setImgUrl(sim.getImgUrl());
					}
					if(!StringUtil.isEmpty(sim.getProfessional())){
						sm.setProfessionalt(sim.getProfessional());
					}
					sm.setTelState(phone);
					sm.setTeletext(teletext);
					sm.setPlus(plus);
					sm.setPersonal(personal);
					if(!StringUtil.isEmpty(sim.getTerritory())){
						sm.setTerritory(sim.getTerritory());
					}
					reList.add(sm);
				}
			}
			return reList;
		}
		return null;
	}

	/**
	 * 搜索（筛选）查询医生列表
	 */
	@Override
	public List<ServicestaffModel> getDoctorsBySelect(String city, String[] hospitalUuids, String territorys) {
		StringBuffer hql = new StringBuffer(
				" select s , o  from ServicestaffinfoModel as o,ServicestaffModel as s where s.uuid = o.serviceStaffUuid and s.sate='1' and s.delFlag='1' ");
	
		if (!StringUtil.isEmpty(city)) {
			hql.append(" and ( o.city =:city  or o.region=:city or o.province=:city ) ");
		}
		if (hospitalUuids != null && hospitalUuids.length > 0) {
			hql.append(" and s.hospital in (:hospitalUuids) ");
		}
		if (!StringUtil.isEmpty(territorys)) {
			String[] territorysa = null;
			if (!StringUtil.isEmpty(territorys)) {
				territorysa = territorys.split(",");
			}
			if(territorysa !=null && territorysa.length>0){
				hql.append(" and ( s.doctorTag like:territory"+0);
				for(int i=1;i<territorysa.length;i++){
					hql.append(" or s.doctorTag like:territory"+i);
				}
				hql.append(" ) ");
			}
		}
		
		hql.append(" order by s.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(city)) {
			q.setString("city", city);
		}
		if (hospitalUuids != null && hospitalUuids.length > 0) {
			q.setParameterList("hospitalUuids", hospitalUuids);
		}
		if (!StringUtil.isEmpty(territorys)) {
			String[] territorysa = null;
			if (!StringUtil.isEmpty(territorys)) {
				territorysa = territorys.split(",");
			}
			if(territorysa !=null && territorysa.length>0){
				for(int i=0;i<territorysa.length;i++){
					String territory=territorysa[i];
					q.setString("territory"+i, "%" + territory + "%");
				}
			}
		}
		
		List list = q.list();
		List<ServicestaffModel> reList = new ArrayList<ServicestaffModel>();
		if (list !=null && list.size() > 0) {
			for(int i=0;i<list.size();i++ ){
				Object[] object = (Object[]) list.get(i);
				if(object !=null){
					ServicestaffModel sm = (ServicestaffModel) object[0];
					ServicestaffinfoModel sim = (ServicestaffinfoModel) object[1];
					if(!StringUtil.isEmpty(sim.getSex())){
						sm.setSex(sim.getSex());
					}
					if(!StringUtil.isEmpty(sim.getImgUrl())){
						sm.setImgUrl(sim.getImgUrl());
					}
					if(!StringUtil.isEmpty(sim.getTerritory())){
						sm.setTerritory(sim.getTerritory());
					}
					reList.add(sm);
				}
			}
			return reList;
		}
		return null;
	}

	/**
	 * 获取医生的真实姓名
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public String getRealNameByUuid(String doctorUuid) {
		StringBuffer hql = new StringBuffer(" select o.realName from ServicestaffModel as o where 1=1 ");
		hql.append(" and o.uuid=:doctorUuid");
		Query q = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(doctorUuid)) {
			q.setString("doctorUuid", doctorUuid);
		}
		List list = q.list();
		if (list.size() > 0 && list != null) {
			return (String) list.get(0);
		}
		return "";
	}

	@Override
	public List<String> getFamousDoctorUuids() {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from ServicestaffModel as o where frozenSate='0' and o.sate='1' ");
		hql.append(" and o.doctorType=:doctorType");
		hql.append(" order by o.createTime desc ");

		Query q = this.getH4Session().createQuery(hql.toString());

		q.setString("doctorType", "1"); // 1是名医风采的类型 ； 2不是名医风采的类型

		List list = q.list();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 
	 * @Description: (根据医生的姓名获取医生的uuid)
	 * @author XP
	 * @param customerName1
	 * @return
	 * @date 2016-1-6 下午3:17:35
	 */
	@Override
	public List<String> getDoctorUuids(String customerName1) {
		if (StringUtil.isEmpty(customerName1)) {
			return null;
		}
		StringBuffer hql = new StringBuffer("select cm.uuid from ServicestaffModel as cm where 1=1 ");
		if (!StringUtil.isEmpty(customerName1)) {
			hql.append(" and cm.realName like :customerName ");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerName", "%" + customerName1 + "%");

		List<String> doctorUuids = query.list();
		return doctorUuids;
	}

	/**
	 * 根据医生编号获取医生信息
	 * 
	 * @param doctorNo
	 * @return
	 */
	@Override
	public ServicestaffModel getServicestaffModel(String doctorNo) {
		StringBuffer hql = new StringBuffer(" select o from ServicestaffModel as o ");
		hql.append(" where o.doctorNo=:doctorNo ");
		hql.append(" or  o.uuid=:doctorNo ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("doctorNo", doctorNo);
		List<ServicestaffModel> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}
	
	/**
	 * 通过医生编号或者医生名称获取集合（编号和名称都是模糊查询）
	 * 
	 * @param qm
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	@Override
	public List<ServicestaffModel> getServicestaffModelListByCondition(
			ServicestaffQueryModel qm, int start, int pageShow) {
		StringBuffer hql = new StringBuffer("select c from ServicestaffModel c,VipclubIntegralStatModel v  where 1=1 and c.uuid=v.customerUuid ");

		/*if (!StringUtil.isEmpty(qm.getUuid())) {
			hql.append(" and c.customerId like :customerId ");
		}*/
		if (!StringUtil.isEmpty(qm.getRealName())) {
			hql.append(" and c.realName like :realName ");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			hql.append(" and c.mobile like :mobile ");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by  v.intergralCount  " + qm.getSortType());
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		
		/*if (!StringUtil.isEmpty(qm.getUuid())) {
			query.setString("customerId", "%" + qm.getUuid() + "%");
		}*/
		if (!StringUtil.isEmpty(qm.getRealName())) {
			query.setString("realName", "%" + qm.getRealName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			query.setString("mobile", "%" + qm.getMobile() + "%");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by v.intergralCount " + qm.getSortType());
		}else{
			hql.append(" order by o.createTime desc");
		}
		
		query.setFirstResult(start);
		query.setMaxResults(pageShow);
		List<ServicestaffModel> list  =query.list();
		return list;
	}
	
	/**
	 * 通过医生编号或者医生名称获取集合（编号和名称都是模糊查询）
	 * 
	 * @param qm
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	@Override
	public List<String> getServicestaffUuidsByCondition(
			ServicestaffQueryModel qm, int start, int pageShow) {
		StringBuffer hql = new StringBuffer("select distinct(c.uuid) from ServicestaffModel c,VipclubIntegralStatModel v  where 1=1 and c.uuid=v.customerUuid ");

		/*if (!StringUtil.isEmpty(qm.getUuid())) {
			hql.append(" and c.customerId like :customerId ");
		}*/
		if (!StringUtil.isEmpty(qm.getRealName())) {
			hql.append(" and c.realName like :realName ");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			hql.append(" and c.mobile like :mobile ");
		}
		
		
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by  v.intergralCount  " + qm.getSortType());
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		
		/*if (!StringUtil.isEmpty(qm.getUuid())) {
			query.setString("customerId", "%" + qm.getUuid() + "%");
		}*/
		if (!StringUtil.isEmpty(qm.getRealName())) {
			query.setString("realName", "%" + qm.getRealName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			query.setString("mobile", "%" + qm.getMobile() + "%");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by v.intergralCount " + qm.getSortType());
		}else{
			hql.append(" order by o.createTime desc");
		}
		
		query.setFirstResult(start);
		query.setMaxResults(pageShow);
		List<String> list  =query.list();
		return list;
	}
	
	/**
	 *  通过医生编号或者医生名称模糊查询数量
	 * @param qm
	 * @return
	 */
	@Override
	public int getCountByCondition(ServicestaffQueryModel qm) {
		
		StringBuffer hql = new StringBuffer(
				" select count(DISTINCT c.uuid) from ServicestaffModel c ,VipclubIntegralStatModel v  where 1=1 and c.uuid=v.customerUuid ");
		
		hql.append(" and v.userType ='2' ");
		if (!StringUtil.isEmpty(qm.getRealName())) {
			hql.append(" and c.realName like :realName ");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			hql.append(" and c.mobile like :mobile ");
		}
		
		Query query = this.getH4Session().createQuery(hql.toString());

		if (!StringUtil.isEmpty(qm.getRealName())) {
			query.setString("realName", "%" + qm.getRealName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			query.setString("mobile", "%" + qm.getMobile() + "%");
		}

		return ((Number) query.uniqueResult()).intValue();
	}

}
