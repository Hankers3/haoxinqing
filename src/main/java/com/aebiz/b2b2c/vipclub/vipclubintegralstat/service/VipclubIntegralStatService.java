package com.aebiz.b2b2c.vipclub.vipclubintegralstat.service;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.CutomerIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.DoctorIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatQueryModel;

/**
 * 会员积分统计表Service接口
 * 
 * @author huyingying
 * 
 */
public interface VipclubIntegralStatService extends BaseService<VipclubIntegralStatModel, VipclubIntegralStatQueryModel> {
	/**
	 * 通过会员Uuid查询会员积分统计记录
	 * 
	 * @param customerUuid
	 * @return List<VipclubIntegralStatModel>
	 */
	public List<VipclubIntegralStatModel> getByCustomerUuid(String customerUuid);

	/**
	 * 通过会员id,积分类型查询会员积分统计表
	 * 
	 * @param customerUuid
	 * @return integralStatModel
	 */
	public VipclubIntegralStatModel getByCustomerUuidAndIntegralType(String customerUuid, String integralType);
	/**
	 * 通过会员列表构造页面显示对象集合
	 * @param list
	 * @return List<CutomerIntegralStatModel>
	 */
	public List<CutomerIntegralStatModel> makeCutomerIntegralStatModel(List<CustomerModel> list);
	
	/**
	 * 通过会员列表构造页面显示对象集合
	 * @param list
	 * @return List<CutomerIntegralStatModel>
	 */
	public List<DoctorIntegralStatModel> makeDoctorIntegralStatModel(List<ServicestaffModel> list);
	
	/**
	 * 通过会员id,积分类型查询会员积分
	 * 
	 * @param customerUuid
	 * @param integralType
	 * @return
	 */
	public int getVipclubIntegralCount(String customerUuid, String integralType);
	
	/**
	 * 查询数据
	 */
	public List<VipclubIntegralStatModel> getByCondition(VipclubIntegralStatQueryModel paramQM, int paramInt1, int paramInt2);
}
