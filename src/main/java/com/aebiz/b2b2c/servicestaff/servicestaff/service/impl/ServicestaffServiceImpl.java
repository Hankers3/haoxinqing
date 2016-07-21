package com.aebiz.b2b2c.servicestaff.servicestaff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.servicestaff.dao.ServicestaffDAO;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffQueryModel;
import com.wxcommon.util.IdentityHelper;

@Service
@Transactional
public class ServicestaffServiceImpl extends
		BaseServiceImpl<ServicestaffModel, ServicestaffQueryModel> implements
		ServicestaffService {
	
	private ServicestaffDAO myDao = null;
/*	@Autowired
	private UuidService us;
	@Autowired
	private ServicestaffinfoService servicestaffinfoService;
	@Autowired
	private OrderMainAddressService orderMainAddressService;
	@Autowired
	private OrderRoutingService orderRoutingService;
	@Autowired
	private StaffLoginStatusService staffLoginStatusService;
	@Autowired
	private InnerMessageService innerMessageService;*/

	@Autowired
	public void setMyDao(ServicestaffDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ServicestaffModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setDoctorType("2");

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ServicestaffModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ServicestaffModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据传入的用户名判断该用户名是否已经存在
	 * 
	 * @param serviceStaffName
	 *            服务人员用户名
	 * @return
	 */
	public boolean checkServiceStaffNameExist(String serviceStaffName) {
		return this.myDao.checkServiceStaffNameExist(serviceStaffName);
	}

	/**
	 * 根据传入的手机号判断手机号是否已经存在
	 * 
	 * @param mobile
	 *            手机号
	 * @return
	 */
	public boolean checkMobileExist(String mobile) {
		return this.myDao.checkMobileExist(mobile);
	}

	/**
	 * 检查手机号和密码
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	public ServicestaffModel checkMobileAndPassword(String mobile,
			String password) {
		return this.myDao.checkMobileAndPassword(mobile, password);
	}

	/**
	 * 通过传入的uuid得到手机号
	 * 
	 * @param uuid
	 * @return
	 */
	public String getMobileByUuid(String uuid) {
		return this.myDao.getMobileByUuid(uuid);
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
		this.myDao.unfrozen(servicestaffUuids, frozenSate);

	}

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 * @param lat1坐标1的纬度
	 * @param lng2
	 * @param lat2坐标2的纬度
	 * @return
	 */
	public static double GetDistance(double lng1, double lat1, double lng2,
			double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 修改家政员信息状态
	 * 
	 * @param uuid
	 * @param state
	 */
	@Override
	public void unpdateState(String uuid, String state, String auditNote) {
		ServicestaffModel staff = myDao.getServicestaffModelByUuid(uuid);
		staff.setSate(state);
		staff.setAuditNote(auditNote);
		this.update(staff);
	}

	/**
	 * 通过编号获取家政员名称
	 * 
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public String getServiceStaffNameByUuid(String serviceStaffUuid) {

		return myDao.getServiceStaffNameByUuid(serviceStaffUuid);
	}

	// /**
	// * 通过家政员名字来获取相应的
	// *
	// * @param serviceStaffName
	// * @return
	// */
	// @SuppressWarnings("rawtypes")
	// @Override
	// public List getServiceStaffUuids(String serviceStaffName) {
	//
	// return myDao.getServiceStaffUuids(serviceStaffName);
	// }
	//
	// @SuppressWarnings("rawtypes")
	// @Override
	// public List getServiceStaffUuidsByMobile(String mobile) {
	//
	// return myDao.getServiceStaffUuidsByMobile(mobile);
	// }

	/**
	 * 通过编号获取家政员对象
	 * 
	 * @param serviceStaffUuid
	 * @return
	 */
	@Override
	public ServicestaffModel getServicestaffModelByUuid(String serviceStaffUuid) {
		return myDao.getServicestaffModelByUuid(serviceStaffUuid);
	}


	/**
	 * 修改医生冻结状态
	 * 
	 * @param uuid
	 * @param state
	 */
	@Override
	public void unpdateFreezeState(String uuid, String FreezeState) {
		myDao.unpdateFreezeState(uuid, FreezeState);

	}

	/**
	 * 通过账户或者邮箱或者手机号名称查询出该家政员所有信息
	 */
	@Override
	public ServicestaffModel getServicestaffByLoginNameOrMobileOrEmail(
			String lonigNameOrMobileOrEmail) {
		return myDao
				.getServicestaffByLoginNameOrMobileOrEmail(lonigNameOrMobileOrEmail);
	}

	/**
	 * 根据手机号获取对应的编号
	 * 
	 * @param mobile
	 * @return
	 */
	@Override
	public List<String> getUuidsByMobile(String mobile) {

		return myDao.getUuidsByMobile(mobile);
	}

	/**
	 * 查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	@Override
	public int getSchedulCount(ServicestaffQueryModel qm) {

		return myDao.getSchedulCount(qm);
	}

	/**
	 * 获取图文咨询数量接口 咨询类型 在线咨询即图文咨询 1电话咨询 2预约加号
	 */
	@Override
	public int getOnlineCount(String oper, String type) {
		return myDao.getOnlineCount(oper, type);
	}

	/**
	 * 查看电话咨询是否开通
	 */
	@Override
	public String getTelState(String uuid) {
		return myDao.getTelState(uuid);
	}

	/**
	 * 查看加号咨询是否开通
	 */
	@Override
	public String getOrderState(String uuid) {
		return myDao.getOrderState(uuid);
	}

	/**
	 * 查看私人医生是否开通
	 */
	@Override
	public String getPersonalState(String uuid) {
		return myDao.getPersonalState(uuid);
	}

	/**
	 * 获得随访数量
	 */
	@Override
	public int getvisitCount(String uuid) {
		return myDao.getvisitCount(uuid);
	}

	@Override
	public String getDoctorNoByUuid(String serviceStaffUuid) {
		return myDao.getDoctorNoByUuid(serviceStaffUuid);
	}

	/**
	 * 获得医生余额
	 */
	@Override
	public double getAccountAmountByUuid(String userUuid) {
		return myDao.getAccountAmountByUuid(userUuid);
	}

	/**
	 * 删除标签
	 * 
	 * @param uuid
	 * @param tagUuid
	 * @return
	 */
	@Override
	public String deleteTag(String uuid, String tagUuid) {
		if (StringUtil.isEmpty(uuid)) {
			return "";
		}
		ServicestaffModel staffModel = this.getByUuid(uuid);
		// 医生标签
		String doctorTag = staffModel.getDoctorTag();
		String[] tagsUuid = doctorTag.split(";");
		StringBuffer newtag = new StringBuffer("");

		if (tagsUuid != null && tagsUuid.length > 0) {
			for (int i = 0; i < tagsUuid.length; i++) {
				String tagId = tagsUuid[i];
				if (!tagUuid.equals(tagId)) {
					newtag.append(tagId + ";");
				}
			}
		}

		staffModel.setDoctorTag(newtag.toString());
		this.update(staffModel);

		return "success";
	}

	/**
	 * 添加医生标签
	 * 
	 * @param uuid
	 * @param tagUuid
	 * @return
	 */
	public String addTag(String uuid, String tagId) {
		if (StringUtil.isEmpty(uuid)) {
			return "";
		}
		ServicestaffModel staffModel = this.getByUuid(uuid);
		// 医生标签
		String doctorTag = staffModel.getDoctorTag();
		StringBuffer newtag = new StringBuffer("");
		if (StringUtil.isEmpty(doctorTag)) {
			newtag.append(tagId);
		} else {
			newtag.append(doctorTag).append(tagId);
		}

		staffModel.setDoctorTag(newtag.toString());
		this.update(staffModel);

		return "success";

	}

	/**
	 * 批量设置医生是否名医风采
	 * 
	 * @param doctorUuids
	 * @param doctorType
	 */
	public void updateDoctorType(List<String> doctorUuids, String doctorType) {
		myDao.updateDoctorType(doctorUuids, doctorType);
	}

	/**
	 * 获得名医
	 * 
	 * @return
	 */
	@Override
	public List<ServicestaffModel> getFamousDoctors() {
		return myDao.getFamousDoctors();
	}

	/**
	 * 通过查询条件得到医生List
	 * 
	 * @param doctorConditon
	 * @return
	 */
	@Override
	public List<ServicestaffModel> getDoctors(String doctorConditon) {

		return myDao.getDoctors(doctorConditon);
	}

	/**
	 * 通过查询条件得到医生List
	 * 
	 * @return
	 */
	@Override
	public List<ServicestaffModel> getDoctorsBySelect(String city,
			String[] hospitalUuids, String territorys) {
		return myDao.getDoctorsBySelect(city, hospitalUuids, territorys);
	}

	@Override
	public void deleteReal(ServicestaffModel servicestaffModel) {
		myDao.delete(servicestaffModel);

	}

	/**
	 * 获取医生的真实姓名
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public String getRealNameByUuid(String doctorUuid) {
		return myDao.getRealNameByUuid(doctorUuid);
	}
	/**
	 * 
	 * @Description: (根据医生的姓名获取医生的uuid)    
	 * @author XP  
	 * @param customerName1
	 * @return
	 * @date 2016-1-6 下午3:14:04
	 */
    @Override
    public List<String> getDoctorUuids(String customerName1) {
        return myDao.getDoctorUuids(customerName1);
    }
	/**
	 * 根据医生编号获取医生信息
	 * @param doctorNo
	 * @return
	 */
	@Override
	public ServicestaffModel getServicestaffModel(String doctorNo) {
		return myDao.getServicestaffModel(doctorNo);
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
	
		return myDao.getServicestaffModelListByCondition(qm, start, pageShow);
	}
	/**
	 *  通过医生编号或者医生名称模糊查询数量
	 * @param qm
	 * @return
	 */
	@Override
	public int getCountByCondition(ServicestaffQueryModel qm) {
		
		return myDao.getCountByCondition(qm);
	}

}
