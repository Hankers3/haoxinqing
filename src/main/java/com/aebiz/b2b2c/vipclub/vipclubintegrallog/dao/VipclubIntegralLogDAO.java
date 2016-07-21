package com.aebiz.b2b2c.vipclub.vipclubintegrallog.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;

public interface VipclubIntegralLogDAO extends BaseDAO<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> {
	/**
	 * 通过会员id查询会员日志记录
	 * 
	 * @param customerUuid
	 * @return tempList<VipclubIntegralLogModel>
	 */
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid);

	public List<String> getUuidsByCustomerUuid(String customerUuid);

	/**
	 * 通过会员查询model，查询该类型会员积分总数
	 * 
	 * @param qm
	 * @return int
	 */
	public int getTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm);

	/**
	 * 通过会员模糊查询会员日志记录数
	 * 
	 * @param qm
	 *            ,
	 * @return int
	 */
	public int getVipclubIntegralLogModelListCountByConditon(VipclubIntegralLogQueryModel qm);

	/**
	 * 通过会员id查询会员日志记录
	 * 
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return tempList<VipclubIntegralLogModel>
	 */
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid, int pageCount, int pageNo);

	/**
	 * 根据医生id获得用户积分日志实体类
	 * 
	 * @param doctorUuid
	 * @return
	 */
	public List<VipclubIntegralLogModel> getbyDoctorUuid(String doctorUuid);

	/**
	 * 根据患者的id获取用户的积分的列表
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<VipclubIntegralLogModel> getVipclubIntegralLogModelListByUuid(String customerUuid);

	/**
	 * 根据患者的id获取患者的积分列表的uuid
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<String> getVipclubIntegralLogModelUuids(String customerUuid);

	/**
	 * 
	 * @Description: (根据用户id和用户类型以及描述获取积分日志对象)
	 * @author XP
	 * @param doctorid
	 * @param userType
	 * @param intergralType
	 * @param description
	 * @return
	 * @date 2016-1-19 下午1:53:14
	 */
	public VipclubIntegralLogModel getVipclubIntegralLogByConditions(String doctorid, String userType,
			String intergralType, String description);

	public VipclubIntegralLogModel getVipclubIntegralLogByquizResultUuid(String customerUuid, String vipclubUsertypeCus,
			String quizCategoryUuid);

}