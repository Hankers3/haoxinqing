package com.aebiz.b2b2c.visitprecept.visitrecord.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.visitrecord.dao.VisitRecordDAO;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordQueryModel;

@Service
@Transactional
public class VisitRecordServiceImpl extends BaseServiceImpl<VisitRecordModel, VisitRecordQueryModel>
		implements VisitRecordService {
	private VisitRecordDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(VisitRecordDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VisitRecordModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(VisitRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(VisitRecordModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.delete(m);
	}

	/**
	 * 根据医生编号 获取随访次数
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public int getCountByDoctorUuid(String doctorUuid) {

		return myDao.getCountByDoctorUuid(doctorUuid);
	}

	/**
	 * 根据随访方案Id值来查询记录表来获取用户的Uuid
	 * 
	 */
	public List<String> getAllByUuid(String uuid) {

		List<String> customerIds = myDao.getAllByUuid(uuid);

		return customerIds;
	}

	/**
	 * 根据患者编号 获取随访list
	 * 
	 * @param uuid
	 * @return
	 */
	public List<VisitRecordModel> getByCustomerUuid(String uuid) {
		return myDao.getByCustomerUuid(uuid);
	}

	/**
	 * 根据医生id 获取随访患者的数量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getVisitRecordNumByDoctorId(String doctorId) {
		return myDao.getVisitRecordNumByDoctorId(doctorId);
	}

	/**
	 * 根据随访方案id，医生ID，获取患者列表
	 * 
	 * @param preceptUuid
	 * @return
	 */
	@Override
	public int getCustomerNumByPreceptUuid(String doctorUuid,String preceptUuid) {
		return myDao.getCustomerNumByPreceptUuid(doctorUuid,preceptUuid);
	}

	/**
	 * 
	 * 根据医生ID查找随访申请的list的接口
	 */
	@Override
	public List<VisitRecordModel> getAllListByDoctorId(String doctorid) {
		return myDao.getAllListByDoctorId(doctorid);
	}
	
	/**
	 * 根据医生ID查找所有随访患者
	 * @param doctorUuid
	 * @return
	 */
	public List<String> getCustomerUuidByDoctorUuid(String doctorUuid) {
		return myDao.getCustomerUuidByDoctorUuid(doctorUuid);
	}

	/**
	 * 根据医生的ID获取所有的随访表单信息
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<VisitRecordModel> getAllVisitRecordsByDoctorId(String doctorid) {
		return myDao.getAllListByDoctorId(doctorid);
	}

	/**
	 * 根据随访方案id，医生ID，获取患者列表
	 * @param uuid uuid
	 * @param preceptUuid preceptUuid
     * @return list
     */
	@Override
	public List<VisitRecordModel> getCustomerVisitRecordByUuid(String uuid,String preceptUuid){
		return myDao.getCustomerVisitRecordByUuid(uuid,preceptUuid);
	}

	/**
	 * 根据医生的ID和患者的id获取随访申请的list集合
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<VisitRecordModel> getVisitRecordByCusAndDoc(String customerUuid, String doctorUuid) {
		return myDao.getVisitRecordByCusAndDoc(customerUuid, doctorUuid);
	}
	/**
	 * 根据医生的ID和患者的id获取随访申请的list集合
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public VisitRecordModel getVisitRecord(String customerUuid, String doctorUuid) {
		return myDao.getVisitRecord(customerUuid, doctorUuid);
	}
	/**
	 * 
	 * @Description: (根据随访记录的id获取患者的id)
	 * @author XP
	 * @param visitRecordUuid
	 * @return
	 * @date 2016-1-22 下午2:52:35
	 */
	@Override
	public String getCustomerUuidByUuid(String visitRecordUuid) {
		return myDao.getCustomerUuidByUuid(visitRecordUuid);
	}

	@Override
	public void deleteVisit() {
		myDao.deleteVisit();
		System.out.println("执行删除方法时间" + DateFormatHelper.getNowTimeStr());
	}

	/**
	 * 根据随访方案id得到患者id集合
	 * 
	 * @param visitPreceptUuid
	 * @return
	 */
	@Override
	public List<String> getCostomerUuidsByVisitPreceptUuid(String visitPreceptUuid) {
		return myDao.getCostomerUuidsByVisitPreceptUuid(visitPreceptUuid);
	}
}