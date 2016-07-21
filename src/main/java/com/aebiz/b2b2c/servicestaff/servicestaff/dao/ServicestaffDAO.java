package com.aebiz.b2b2c.servicestaff.servicestaff.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffQueryModel;

public interface ServicestaffDAO extends
		BaseDAO<ServicestaffModel, ServicestaffQueryModel> {

	/**
	 * 检查用户名是否存在
	 * 
	 * @param serviceStaffName
	 *            用户名
	 * @return
	 */
	public boolean checkServiceStaffNameExist(String serviceStaffName);

	/**
	 * 检查手机号是否存在
	 * 
	 * @param mobile
	 *            手机号
	 * @return
	 */
	public boolean checkMobileExist(String mobile);

	/**
	 * 检查手机号和密码
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	public ServicestaffModel checkMobileAndPassword(String mobile,
			String password);

	/**
	 * 根据uuid得到手机号
	 * 
	 * @param uuid
	 * @return
	 */
	public String getMobileByUuid(String uuid);

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
	public void unfrozen(List<String> servicestaffUuids, String frozenSate);

	/**
	 * 修改家政员信息状态
	 * 
	 * @param uuid
	 * @param state
	 */
	public void unpdateState(String uuid, String state, String auditNote);

	/**
	 * 通过编号获取家政员名称
	 * 
	 * @param serviceStaffUuid
	 * @return
	 */
	public String getServiceStaffNameByUuid(String serviceStaffUuid);

	/**
	 * 通过家政员名字来获取相应的
	 * 
	 * @param serviceStaffName
	 * @return
	 */
	public List getServiceStaffUuids(String serviceStaffName);

	/**
	 * 通过家政员名字来获取相应的
	 * 
	 * @param serviceStaffName
	 * @return
	 */
	public List getServiceStaffUuidsByMobile(String mobile);

	/**
	 * 通过编号获取家政员对象
	 * 
	 * @param serviceStaffUuid
	 * @return
	 */
	public ServicestaffModel getServicestaffModelByUuid(String serviceStaffUuid);


	/**
	 * 修改家政员d冻结状态
	 * 
	 * @param uuid
	 * @param state
	 */
	public void unpdateFreezeState(String uuid, String FreezeState);

	/**
	 * 通过账户或者邮箱或者手机号名称查询出该家政员所有信息
	 * 
	 * @param lonigNameOrMobileOrEmail
	 * @return
	 * @author zdx
	 */

	public ServicestaffModel getServicestaffByLoginNameOrMobileOrEmail(
			String lonigNameOrMobileOrEmail);


	/**
	 * 根据手机号获取对应的编号
	 * 
	 * @param mobile
	 * @return
	 */
	public List<String> getUuidsByMobile(String mobile);

	/**
	 * 查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getSchedulCount(ServicestaffQueryModel qm);

	/**
	 * 查询全部登录状态和冻结状态的家政员的uuid
	 * 
	 * @param qm
	 * @param frozenstate
	 *            家政员状态
	 * @param state
	 *            冻结状态
	 * @return hdf
	 */
	public List<String> getUuidsAboutloginAndUnforzen(String frozenstate,
			String state);

	/**
	 * 获取图文咨询数量接口 咨询类型 在线咨询即图文咨询 1电话咨询 2预约加号
	 */
	public int getOnlineCount(String oper, String type);

	/**
	 * 查看电话咨询是否开通
	 */
	public String getTelState(String uuid);

	/**
	 * 查看加号咨询是否开通
	 */
	public String getOrderState(String uuid);

	/**
	 * 查看私人医生是否开通
	 */
	String getPersonalState(String uuid);

	/**
	 * 获得随访数量
	 */
	public int getvisitCount(String uuid);

	/**
	 * 获得doctorNo
	 */
	public String getDoctorNoByUuid(String serviceStaffUuid);

	/**
	 * 获得医生余额
	 */
	public double getAccountAmountByUuid(String userUuid);

	/**
	 * 批量设置医生是否名医风采
	 * 
	 * @param doctorUuids
	 * @param doctorType
	 */
	public void updateDoctorType(List<String> doctorUuids, String doctorType);

	/**
	 * 获得名医
	 * 
	 * @return
	 */
	public List<ServicestaffModel> getFamousDoctors();

	/**
	 * 获得名医uuids
	 * 
	 * @return
	 */
	public List<String> getFamousDoctorUuids();

	/**
	 * 通过查询条件得到医生List
	 * 
	 * @param doctorName
	 * @return
	 */
	public List<ServicestaffModel> getDoctors(String doctorConditon);

	/**
	 * 获取医生的真实姓名
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public String getRealNameByUuid(String doctorUuid);
	/**
	 * 
	 * @Description: (根据医生的姓名获取医生的uuid)    
	 * @author XP  
	 * @param customerName1
	 * @return
	 * @date 2016-1-6 下午3:14:49
	 */
	public List<String> getDoctorUuids(String customerName1);

	public List<ServicestaffModel> getDoctorsBySelect(String city,
			String[] hospitalUuids, String territorys);
	/**
	 * 根据医生编号获取医生信息
	 * @param doctorNo
	 * @return
	 */
	public ServicestaffModel getServicestaffModel(String doctorNo);
	
	
	/**
	 * 通过医生编号或者医生名称获取集合（编号和名称都是模糊查询）
	 * 
	 * @param qm
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<ServicestaffModel> getServicestaffModelListByCondition(ServicestaffQueryModel qm, int start, int pageShow);
	
	/**
	 * 通过医生编号或者医生名称获取集合（编号和名称都是模糊查询）
	 * 
	 * @param qm
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<String> getServicestaffUuidsByCondition(ServicestaffQueryModel qm, int start, int pageShow);
	
	
	/**
	 *  通过医生编号或者医生名称模糊查询数量
	 * @param qm
	 * @return
	 */
	public int getCountByCondition(ServicestaffQueryModel qm);
}