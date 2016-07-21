package com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatQueryModel;

/**
 * 会员积分统计表DAO接口
 * 
 * @author huyingying
 * 
 */
public interface VipclubIntegralStatDAO extends BaseDAO<VipclubIntegralStatModel, VipclubIntegralStatQueryModel> {
	/**
	 * 通过会员id查询会员积分统计表
	 * 
	 * @param customerUuid
	 * @return tempList<VipclubIntegralStatModel>
	 */
	public List<VipclubIntegralStatModel> getByCustomerUuid(String customerUuid);
	
	public List<String> getUuidsByCustomerUuid(String customerUuid);

	/**
	 * 通过会员id,积分类型查询会员积分统计表
	 * 
	 * @param customerUuid
	 * @return integralStatModel
	 */
	public VipclubIntegralStatModel getByCustomerUuidAndIntegralType(String customerUuid, String integralType);
	
	public String getUuidByCustomerUuidAndIntegralType(String customerUuid, String integralType);
	
	/**
	 * 根据会员uuid查询会员可用积分数
	 */
	public int getUsaleIntegralByCustomerUuid(String customerUuid);
	
	
	/**
	 * 通过会员id,积分类型查询会员积分
	 * 
	 * @param customerUuid
	 * @param integralType
	 * @return
	 */
	public int getVipclubIntegralCount(String customerUuid, String integralType);
	
	@Override
	public List<VipclubIntegralStatModel> getByCondition(
			VipclubIntegralStatQueryModel paramQM, int paramInt1, int paramInt2);
}