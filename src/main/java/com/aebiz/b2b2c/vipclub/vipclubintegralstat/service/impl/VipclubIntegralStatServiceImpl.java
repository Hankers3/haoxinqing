package com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao.VipclubIntegralStatDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.CutomerIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.DoctorIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatQueryModel;

/**
 * 会员积分统计表service
 * 
 * @author huyingying
 * 
 */
@Service
@Transactional
public class VipclubIntegralStatServiceImpl extends BaseServiceImpl<VipclubIntegralStatModel, VipclubIntegralStatQueryModel> implements VipclubIntegralStatService {
	/* 会员积分日志DAO */
	private VipclubIntegralStatDAO myDao = null;
	
	@Autowired
	/* 会员系统接口注入 */
	private CustomerInteractive customerInteractive;
	
	@Autowired
	/* Uuid的service */
	private UuidService us;

	@Autowired
	public void setMyDao(VipclubIntegralStatDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 创建会员积分统计记录
	 * 
	 * @param VipclubIntegralStatModel
	 * @return String
	 */
	@Override
	public String create(VipclubIntegralStatModel m) {
		m.setUuid(us.getNextUuid("VipclubIntegralStat", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	/**
	 * 更新会员积分统计记录
	 * 
	 * @param VipclubIntegralStatModel
	 * @return String
	 */
	@Override
	public void update(VipclubIntegralStatModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除会员积分统计记录
	 * 
	 * @param VipclubIntegralStatModel
	 * @return String
	 */
	@Override
	public void delete(VipclubIntegralStatModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过会员Uuid查询会员积分统计记录
	 * 
	 * @param customerUuid
	 * @return List<VipclubIntegralStatModel>
	 */
	public List<VipclubIntegralStatModel> getByCustomerUuid(String customerUuid) {
		return myDao.getByCustomerUuid(customerUuid);
	}
	
	/**
	 * 通过会员id,积分类型查询会员积分统计表
	 * 
	 * @param customerUuid
	 * @return integralStatModel
	 */
	public VipclubIntegralStatModel getByCustomerUuidAndIntegralType(String customerUuid, String integralType) {
		return myDao.getByCustomerUuidAndIntegralType(customerUuid,integralType);
	}
	
	/**
	 * 通过会员列表,构造页面展示对象列表
	 * 
	 * @param List<CustomerModel>
	 * @return List<CutomerIntegralStatModel>
	 */
	public List<CutomerIntegralStatModel> makeCutomerIntegralStatModel(List<CustomerModel> list) {
		
		// 循环查询出每个Id的对应记录
		List<CutomerIntegralStatModel> showList = new ArrayList();
		
		for (int i = 0; i < list.size(); i++) {
			
			CustomerModel cm = list.get(i);
			
			// 通过会员id查询会员统计表对应记录
			List<VipclubIntegralStatModel> integralStatModels = myDao.getByCustomerUuid(cm.getUuid());
			if (integralStatModels != null && integralStatModels.size() > 0) {
				
				// 新建一个页面展示对象
				CutomerIntegralStatModel cutomerIntegralStatModel = new CutomerIntegralStatModel();
				
				// 设置页面展示对象的会员Uuid
				cutomerIntegralStatModel.setCustomerUuid(cm.getUuid());
				
				// 设施页面展示对象的会员名
				cutomerIntegralStatModel.setCustomerName(cm.getCustomerName());
				
				// 设施页面展示对象的用户类型
				cutomerIntegralStatModel.setUserType(cutomerIntegralStatModel.getUserType());
				// 循环设置每个会员Id所有积分类型的积分数量
				for (int j = 0; j < integralStatModels.size(); j++) {
					VipclubIntegralStatModel integralStatModel = integralStatModels.get(j);
					
					// 设置积分类型为可用积分的积分数量
					if (integralStatModel.getIntergralType().equals(StatIntegralType.USABLE.getValue())) {
						cutomerIntegralStatModel.setUsableIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为已用积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.USED.getValue())) {
						cutomerIntegralStatModel.setUsedIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为累计积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.TOTAL.getValue())) {
						cutomerIntegralStatModel.setTotalIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为购物获得积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.GET_BY_SHOP.getValue())) {
						cutomerIntegralStatModel.setShopIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为行为获得积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.GET_BY_BEHAVIOUR.getValue())) {
						cutomerIntegralStatModel.setBehaviourIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为奖励获得积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.GET_BY_REWARD.getValue())) {
						cutomerIntegralStatModel.setRewardIntegral(integralStatModel.getIntergralCount());
					}
				}
				
				// 添加到showList中去
				showList.add(cutomerIntegralStatModel);
			}
		}
		return showList;
	}
	
	/**
	 * 通过医生列表,构造页面展示对象列表
	 * 
	 * @param List<ServicestaffModel>
	 * @return List<DoctorIntegralStatModel>
	 */
	@Override
	public List<DoctorIntegralStatModel> makeDoctorIntegralStatModel(
			List<ServicestaffModel> list) {
		// 循环查询出每个Id的对应记录
		List<DoctorIntegralStatModel> showList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			ServicestaffModel sm = list.get(i);
			// 通过会员id查询会员统计表对应记录
			List<VipclubIntegralStatModel> integralStatModels = myDao.getByCustomerUuid(sm.getUuid());
			// 通过会员id查询会员统计表对应记录
			if (integralStatModels != null && integralStatModels.size() > 0) {	
				// 新建一个页面展示对象
				DoctorIntegralStatModel doctorIntegralStatModel = new DoctorIntegralStatModel();
				
				// 设置页面展示对象的会员Uuid
				doctorIntegralStatModel.setDoctorUuid(sm.getUuid());
				// 设施页面展示对象的会员名
				doctorIntegralStatModel.setDoctorName(sm.getRealName());
				
				// 设施页面展示对象的用户类型
				doctorIntegralStatModel.setUserType("2");
				
				// 循环设置每个会员Id所有积分类型的积分数量
				for (int j = 0; j < integralStatModels.size(); j++) {
					VipclubIntegralStatModel integralStatModel = integralStatModels.get(j);
		
					// 设置积分类型为可用积分的积分数量
					if (integralStatModel.getIntergralType().equals(StatIntegralType.USABLE.getValue())) {
						doctorIntegralStatModel.setUsableIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为已用积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.USED.getValue())) {
						doctorIntegralStatModel.setUsedIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为累计积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.TOTAL.getValue())) {
						doctorIntegralStatModel.setTotalIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为购物获得积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.GET_BY_SHOP.getValue())) {
						doctorIntegralStatModel.setShopIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为行为获得积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.GET_BY_BEHAVIOUR.getValue())) {
						doctorIntegralStatModel.setBehaviourIntegral(integralStatModel.getIntergralCount());
					}
					
					// 设置积分类型为奖励获得积分的积分数量
					else if (integralStatModel.getIntergralType().equals(StatIntegralType.GET_BY_REWARD.getValue())) {
						doctorIntegralStatModel.setRewardIntegral(integralStatModel.getIntergralCount());
					}
					// 添加到showList中去
				}
				showList.add(doctorIntegralStatModel);
			}
		}
		return showList;
	}
	
	
	/**
	 * 通过会员id,积分类型查询会员积分
	 * 
	 * @param customerUuid
	 * @param integralType
	 * @return
	 */
	@Override
	public int getVipclubIntegralCount(String customerUuid, String integralType) {
		
		return myDao.getVipclubIntegralCount(customerUuid, integralType);
	}

	@Override
	public List<VipclubIntegralStatModel> getByCondition(
			VipclubIntegralStatQueryModel qm, int start, int pageShow) {
		
		return myDao.getByCondition(qm, start, pageShow);
	}
}