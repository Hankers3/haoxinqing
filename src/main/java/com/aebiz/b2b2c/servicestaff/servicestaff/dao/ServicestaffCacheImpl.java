package com.aebiz.b2b2c.servicestaff.servicestaff.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 医生的缓存
 * 
 * @author szr
 * 
 */
@Primary
@Repository
public class ServicestaffCacheImpl extends
		BaseMemcachedCache<ServicestaffModel, ServicestaffQueryModel> implements
		ServicestaffDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ServicestaffDAO servicestaffDAO;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(servicestaffDAO);
	}

	public ServicestaffCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY);
	}

	@Override
	public String create(ServicestaffModel m) {
		String ret = this.servicestaffDAO.create(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY+"list");
		return ret;
	}

	@Override
	public void delete(ServicestaffModel m) {
		super.delete(m);
		super.deleteCache(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY+"list");

	}

	@Override
	public void update(ServicestaffModel m) {
		servicestaffDAO.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
				+ m.getUuid());
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY+"list");
	}

	@Override
	public boolean checkServiceStaffNameExist(String serviceStaffName) {

		return servicestaffDAO.checkServiceStaffNameExist(serviceStaffName);
	}

	@Override
	public boolean checkMobileExist(String mobile) {

		return servicestaffDAO.checkMobileExist(mobile);
	}

	@Override
	public ServicestaffModel checkMobileAndPassword(String mobile,
			String password) {

		return servicestaffDAO.checkMobileAndPassword(mobile, password);
	}

	@Override
	public String getMobileByUuid(String uuid) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
						+ uuid);
		String mobile = "";
		ServicestaffModel servicestaffModel = null;
		if (obj != null) {
			servicestaffModel = (ServicestaffModel) obj;
			mobile = servicestaffModel.getMobile();
		} else {
			servicestaffModel = servicestaffDAO.getByUuid(uuid);
			if (servicestaffModel != null) {
				mobile = servicestaffModel.getMobile();
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
								+ uuid, servicestaffModel);
			}

		}

		return mobile;
	}

	@Override
	public void unfrozen(List<String> servicestaffUuids, String frozenSate) {
		servicestaffDAO.unfrozen(servicestaffUuids, frozenSate);
		if (servicestaffUuids != null) {
			for (String uuid : servicestaffUuids) {
				this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
						+ uuid);
				ServicestaffModel m = servicestaffDAO.getByUuid(uuid);
				if (m != null) {
					this.mcc.set(
							ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
									+ uuid, m);
				}

			}
		}

	}

	@Override
	public void unpdateState(String uuid, String state, String auditNote) {
		servicestaffDAO.unpdateState(uuid, state, auditNote);
		if (!StringUtil.isEmpty(uuid)) {
			this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
					+ uuid);
			ServicestaffModel m = servicestaffDAO.getByUuid(uuid);
			if (m != null) {
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
								+ uuid, m);
			}
			this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY+"list");
		}

	}

	@Override
	public String getServiceStaffNameByUuid(String serviceStaffUuid) {
		if (!StringUtil.isEmpty(serviceStaffUuid)) {
			Object obj = this.mcc
					.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
							+ serviceStaffUuid);
			ServicestaffModel servicestaffModel = new ServicestaffModel();
			String realName = "";
			if (obj != null) {
				servicestaffModel = (ServicestaffModel) obj;
				realName = servicestaffModel.getRealName();
			} else {
				servicestaffModel = servicestaffDAO.getByUuid(serviceStaffUuid);
				if (servicestaffModel != null) {
					realName = servicestaffModel.getRealName();
					this.mcc.set(
							ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
									+ serviceStaffUuid, servicestaffModel);
				}
			}

			return realName;
		}
		return "";
	}

	@Override
	public ServicestaffModel getServicestaffModelByUuid(String serviceStaffUuid) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
						+ serviceStaffUuid);
		ServicestaffModel servicestaffModel = new ServicestaffModel();
		if (obj != null) {
			servicestaffModel = (ServicestaffModel) obj;
		} else {
			servicestaffModel = servicestaffDAO
					.getServicestaffModelByUuid(serviceStaffUuid);
			if (servicestaffModel != null) {
				this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_MOBILE_KEY
						+ serviceStaffUuid, servicestaffModel);
			}
		}

		return servicestaffModel;
	}


	@Override
	public void unpdateFreezeState(String uuid, String FreezeState) {
		servicestaffDAO.unpdateFreezeState(uuid, FreezeState);
		if (!StringUtil.isEmpty(uuid)) {
			this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
					+ uuid);
			ServicestaffModel m = servicestaffDAO.getByUuid(uuid);
			if (m != null) {
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
								+ uuid, m);
			}
			this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY+"list");
		}
	}

	@Override
	public ServicestaffModel getServicestaffByLoginNameOrMobileOrEmail(
			String lonigNameOrMobileOrEmail) {
		return servicestaffDAO
				.getServicestaffByLoginNameOrMobileOrEmail(lonigNameOrMobileOrEmail);
	}

	@Override
	public List<String> getUuidsByMobile(String mobile) {
		return servicestaffDAO.getUuidsByMobile(mobile);
	}

	@Override
	public int getSchedulCount(ServicestaffQueryModel qm) {
		return servicestaffDAO.getSchedulCount(qm);
	}

	@Override
	public List<String> getUuidsAboutloginAndUnforzen(String frozenstate,
			String state) {
		return servicestaffDAO
				.getUuidsAboutloginAndUnforzen(frozenstate, state);
	}

	@Override
	public int getOnlineCount(String oper, String type) {
		return servicestaffDAO.getOnlineCount(oper, type);
	}

	@Override
	public String getTelState(String uuid) {
		return servicestaffDAO.getTelState(uuid);
	}

	@Override
	public String getOrderState(String uuid) {
		return servicestaffDAO.getOrderState(uuid);
	}

	@Override
	public String getPersonalState(String uuid) {
		return servicestaffDAO.getPersonalState(uuid);
	}

	@Override
	public int getvisitCount(String uuid) {
		return servicestaffDAO.getvisitCount(uuid);
	}

	@Override
	public String getDoctorNoByUuid(String serviceStaffUuid) {
		String doctorNo = "";
		ServicestaffModel servicestaffModel = new ServicestaffModel();
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY);
		if (obj != null) {
			servicestaffModel = (ServicestaffModel) obj;
			doctorNo = servicestaffModel.getDoctorNo();
		} else {
			servicestaffModel = servicestaffDAO.getByUuid(serviceStaffUuid);
			if (servicestaffModel != null) {
				doctorNo = servicestaffModel.getDoctorNo();
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
								+ serviceStaffUuid, servicestaffModel);
			}
		}
		return doctorNo;
	}

	@Override
	public double getAccountAmountByUuid(String userUuid) {
		return servicestaffDAO.getAccountAmountByUuid(userUuid);
	}

	@Override
	public void updateDoctorType(List<String> doctorUuids, String doctorType) {
		servicestaffDAO.updateDoctorType(doctorUuids, doctorType);
		if (doctorUuids != null && doctorUuids.size() > 0) {
			for (String doctorUuid : doctorUuids) {
				this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
						+ doctorUuid);
				ServicestaffModel m = servicestaffDAO.getByUuid(doctorUuid);
				if (m != null) {
					this.mcc.set(
							ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
									+ doctorUuid, m);
				}
			}
			this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY+"list");
		}
	}

	@Override
	public List<ServicestaffModel> getFamousDoctors() {
		// 返回对象集合
		List<ServicestaffModel> famousDoctors = new ArrayList<ServicestaffModel>();
		// 所以uuids
		List<String> uuids = this.getFamousDoctorUuids();
		// 循环
		if (uuids != null && uuids.size() > 0) {
			for (String uuid : uuids) {
				// Model对象
				ServicestaffModel servicestaffModel = new ServicestaffModel();
				// 从缓存中取值
				Object objModel = this.mcc
						.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
								+ uuid);
				if (objModel != null) {
					servicestaffModel = (ServicestaffModel) objModel;
				} else {
					// 获得医生model
					servicestaffModel = servicestaffDAO.getByUuid(uuid);
					if (servicestaffModel != null) {
						// 将医生model放入缓存
						this.mcc.set(
								ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
										+ uuid, servicestaffModel);
					}
				}
				// 将model存入返回值
				famousDoctors.add(servicestaffModel);
			}
		}
		// 返回
		return famousDoctors;
	}
	/**
	 * 获取医生列表 条件为空时保存到缓存中
	 */
	@Override
	public List<ServicestaffModel> getDoctors(String doctorConditon) {
		List<ServicestaffModel> reList =null ;
		if(!StringUtil.isEmpty(doctorConditon)){
			return servicestaffDAO.getDoctors(doctorConditon);
		}else{
			Object obj= this.mcc.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
							+ "list");
			if(obj !=null){
				reList = (List<ServicestaffModel>) obj;
			}else{
				reList = servicestaffDAO.getDoctors(doctorConditon);
				if(reList !=null && reList.size()>0){
					this.mcc.set(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
							+ "list",reList);
				}
			}
			return reList;
		}
	}

	@Override
	public List<ServicestaffModel> getDoctorsBySelect(String city,
			String[] hospitalUuids, String territorys) {
		return servicestaffDAO.getDoctorsBySelect(city, hospitalUuids,
				territorys);
	}

	@Override
	public String getRealNameByUuid(String doctorUuid) {
		Object obj = this.mcc
				.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
						+ doctorUuid);
		ServicestaffModel servicestaffModel = new ServicestaffModel();
		String realName = "";
		if (obj != null) {
			servicestaffModel = (ServicestaffModel) obj;
			realName = servicestaffModel.getRealName();
		} else {
			servicestaffModel = servicestaffDAO.getByUuid(doctorUuid);
			if (servicestaffModel != null) {
				realName = servicestaffModel.getRealName();
				this.mcc.set(
						ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
								+ doctorUuid, servicestaffModel);
			}
		}

		return realName;
	}

	@Override
	public List<String> getFamousDoctorUuids() {
		// uuids集合
		List<String> uuids = servicestaffDAO.getFamousDoctorUuids();

		return uuids;
	}

	@Override
	public List getServiceStaffUuids(String serviceStaffName) {
		return servicestaffDAO.getServiceStaffUuids(serviceStaffName);
	}

	@Override
	public List getServiceStaffUuidsByMobile(String mobile) {
		return servicestaffDAO.getServiceStaffUuidsByMobile(mobile);
	}

    @Override
    public List<String> getDoctorUuids(String customerName1) {
        return servicestaffDAO.getDoctorUuids(customerName1);
    }
	/**
	 * 根据医生编号获取医生信息
	 * @param doctorNo
	 * @return
	 */
	@Override
	public ServicestaffModel getServicestaffModel(String doctorNo) {
		return servicestaffDAO.getServicestaffModel(doctorNo);
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
		List<String> uuids = this.servicestaffDAO.getServicestaffUuidsByCondition(qm, start, pageShow);
		List<ServicestaffModel> listM = new ArrayList<ServicestaffModel>();
		for (String uuid : uuids) {
			Object obj = mcc.get(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
					+ uuid);
			if (obj != null) {
				ServicestaffModel m = (ServicestaffModel) obj;
				listM.add(m);
			} else {
				ServicestaffModel m = servicestaffDAO.getByUuid(uuid); 
				if(m !=null){
					mcc.set(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFF_KEY
							+ uuid,m);
					listM.add(m);
				}
			}
		}
		return listM;
	}
	
	@Override
	public List<String> getServicestaffUuidsByCondition(
			ServicestaffQueryModel qm, int start, int pageShow) {
		
		return servicestaffDAO.getServicestaffUuidsByCondition(qm, start, pageShow);
	}
	
	/**
	 *  通过医生编号或者医生名称模糊查询数量
	 * @param qm
	 * @return
	 */
	@Override
	public int getCountByCondition(ServicestaffQueryModel qm) {
		
		return servicestaffDAO.getCountByCondition(qm);
	}



}
