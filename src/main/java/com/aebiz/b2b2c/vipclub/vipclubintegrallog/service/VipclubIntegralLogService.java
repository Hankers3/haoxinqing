package com.aebiz.b2b2c.vipclub.vipclubintegrallog.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;

public interface VipclubIntegralLogService extends BaseService<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> {
	/**
	 * 通过会员id查询会员日志记录
	 * 
	 * @param customerUuid
	 * @return tempList<VipclubIntegralLogModel>
	 */
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid);

	/**
	 * 保存一条会员积分日志记录
	 * 
	 * @param customerUuid
	 * @param adaptType
	 * @param changgeIntegralNum
	 * @param description
	 * @param usetType
	 *            1:会员 2 医生
	 * @return
	 */
	public void saveVipIntegralLog(String customerUuid, String adaptType, int changgeIntegralNum, String intergralType,
			String userType, String content, String description);

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
	 * 通过会员查询model，查询该类型会员积分总数
	 * 
	 * @param qm
	 * @return int
	 */
	public int getTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm);

	/**
	 * 
	 * @Description: (根据用户id和用户类型以及描述获取积分日志对象)
	 * @author XP
	 * @param doctorid
	 * @param userType
	 * @param description
	 * @return
	 * @date 2016-1-19 下午1:53:14
	 */
	public VipclubIntegralLogModel getVipclubIntegralLogByConditions(String doctorid, String userType,
			String intergralType, String description);

	/**
	 * 根据患者的id获取患者积分列表
	 * 
	 * @param customerUuid
	 * @return
	 */
	public List<VipclubIntegralLogModel> getVipclubIntegralLogModelListByUuid(String customerUuid);

	public VipclubIntegralLogModel getVipclubIntegralLogByQuizResultUuid(String customerUuid, String vipclubUsertypeCus,
			String quizCategoryUuid);

}
