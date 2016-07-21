package com.hxq.mobile.vipclub.service;

import java.util.List;

import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.hxq.mobile.entity.common.VipclubIntegralLog;
import com.hxq.mobile.util.repository.SimpleEntityService;

public interface VipclubIntegralLogService extends SimpleEntityService {
	/**
	 * 通过医生或患者id查询会员日志记录
	 *
	 * @param customerUuid
	 * @return tempList<VipclubIntegralLogModel>
	 */
	public List<VipclubIntegralLog> selectByCustomerUuid(String customerUuid);

	/**
	 * 通过会员id查询会员日志记录
	 *
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return tempList<VipclubIntegralLogModel>
	 */
	public List<VipclubIntegralLog> selectByCustomerUuid(String customerUuid, int pageCount, int pageNo);

	/**
	 * 保存一条会员积分日志记录
	 * @param customerUuid
	 * @param adaptType
	 * @param changgeIntegralNum
	 * @param description
	 * @param usetType 1:会员 2 医生
	 * @return
	 * @throws Exception
	 */
	public int insertVipIntegralLog(String customerUuid, String adaptType, int changgeIntegralNum,
			String intergralType, String userType, String content, String description) throws Exception;

	/**
	 * 通过会员模糊查询会员日志记录数
	 * @param qm
	 * @return int
	 */
	public int selectVipclubIntegralLogCountByConditon(VipclubIntegralLogQueryModel qm);

	/**
	 * 通过会员查询model，查询该类型会员积分总数
	 *
	 * @param qm
	 * @return int
	 */
	public int selectTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm);

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
	public VipclubIntegralLog selectVipclubIntegralLogByConditions(String doctorid, String userType, String intergralType, String description);

	public VipclubIntegralLog selectVipclubIntegralLogByQuizResultUuid(String customerUuid, String vipclubUsertypeCus, String quizCategoryUuid);
}
