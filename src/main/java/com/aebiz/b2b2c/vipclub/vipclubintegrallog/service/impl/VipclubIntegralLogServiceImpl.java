package com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.dao.VipclubIntegralLogDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao.VipclubIntegralStatDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;

/**
 * 会员积分日志service实现类
 * 
 * @author huyingying
 * 
 */
@Service
@Transactional
public class VipclubIntegralLogServiceImpl extends
		BaseServiceImpl<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> implements VipclubIntegralLogService {

	/* 会员积分日志Dao */
	private VipclubIntegralLogDAO myDao = null;

	@Autowired
	/* 会员积分统计表Dao */
	private VipclubIntegralStatDAO statDAO = null;

	/* Uuid服务接口 */
	@Autowired
	private UuidService us;

	/* 注入会员账户接口 */
	@Autowired
	private CustomerAccountInteractive customerAccountInteractive;

	@Autowired
	public void setMyDao(VipclubIntegralLogDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 创建会员积分日志实现方法
	 * 
	 * @param m
	 *            会员积分日志对象
	 * @return ret
	 */
	@Override
	public String create(VipclubIntegralLogModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	/**
	 * 更新会员积分日志实现方法
	 * 
	 * @param m
	 *            会员积分日志对象
	 * @return
	 */
	@Override
	public void update(VipclubIntegralLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除会员积分日志实现方法
	 * 
	 * @param m
	 *            会员积分日志对象
	 * @return
	 */
	@Override
	public void delete(VipclubIntegralLogModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过会员Uuid查询积分日志列表实现方法
	 * 
	 * @param customerUuid
	 *            会员Uuid
	 * @return List<VipclubIntegralLogModel>
	 */
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid) {
		return myDao.getByCustomerUuid(customerUuid);
	}

	/**
	 * 保存一条会员积分日志记录,更新积分统计表，会员账户表积分记录
	 * 
	 * @param customerUuid
	 * @param adaptType
	 * @param changgeIntegralNum
	 * @param description
	 * @return
	 */
	public void saveVipIntegralLog(String customerUuid, String adaptType, int changgeIntegralNum, String intergralType,
			String userType, String content, String description) {
		// 新建一条会员积分日志记录
		VipclubIntegralLogModel integralLogModel = new VipclubIntegralLogModel();

		// 设置会员积分日志记录的会员Uuid
		integralLogModel.setCustomerUuid(customerUuid);

		// 设置用户类型
		integralLogModel.setUserType(userType);
		
		// 保存描述
		integralLogModel.setDescription(content);
		
		// 保存积分获取类型
		integralLogModel.setIntergralType(intergralType);
		
		// 设置会员积分日志记录的搜索条件
		integralLogModel.setSearchType(description);

		// 设置会员积分日志记录的积分数量
		integralLogModel.setIntergralCount(changgeIntegralNum);

		// 查询统计表总积分记录
		VipclubIntegralStatModel integralStatModelTotal = statDAO.getByCustomerUuidAndIntegralType(customerUuid,
				StatIntegralType.TOTAL.getValue());
		if (integralStatModelTotal == null) {
			integralStatModelTotal = new VipclubIntegralStatModel();
			integralStatModelTotal.setIntergralType(StatIntegralType.TOTAL.getValue());
			integralStatModelTotal.setCustomerUuid(customerUuid);
			integralStatModelTotal.setUserType(userType);
			integralStatModelTotal.setUuid(us.getNextUuid());
			integralStatModelTotal.setDelFlag(BaseModel.DEL_FLAG_INVALID);
			integralStatModelTotal.setOper(LoginUserHelper.getLoginUserUuid());
			integralStatModelTotal.setOpeTime(DateFormatHelper.getNowTimeStr());
			statDAO.create(integralStatModelTotal);
		}

		// 查询统计表可用积分记录
		VipclubIntegralStatModel integralStatModelUsable = statDAO.getByCustomerUuidAndIntegralType(customerUuid,
				StatIntegralType.USABLE.getValue());
		if (integralStatModelUsable == null) {
			integralStatModelUsable = new VipclubIntegralStatModel();
			integralStatModelUsable.setIntergralType(StatIntegralType.USABLE.getValue());
			integralStatModelUsable.setCustomerUuid(customerUuid);
			integralStatModelUsable.setUserType(userType);
			integralStatModelUsable.setUuid(us.getNextUuid());
			integralStatModelUsable.setDelFlag(BaseModel.DEL_FLAG_INVALID);
			integralStatModelUsable.setOper(LoginUserHelper.getLoginUserUuid());
			integralStatModelUsable.setOpeTime(DateFormatHelper.getNowTimeStr());
			statDAO.create(integralStatModelUsable);
		}

		// 添加积分,更新统计表,设置积分日志类型
		if (adaptType.equals("add")) {

			// 更新会员统计表的累计积分数
			integralStatModelTotal.setIntergralCount(integralStatModelTotal.getIntergralCount() + changgeIntegralNum);

			// 更新会员统计表的累计积分数
			integralStatModelUsable.setIntergralCount(integralStatModelUsable.getIntergralCount() + changgeIntegralNum);

		} else if (adaptType.equals("reduce")) {

			// 更新会员统计表的可用积分数
			integralStatModelUsable.setIntergralCount(integralStatModelUsable.getIntergralCount() - changgeIntegralNum);

		}
		// 更新积分统计表
		statDAO.update(integralStatModelUsable);
		statDAO.update(integralStatModelTotal);

		// 设置会员操作日志的当前可用积分
		integralLogModel.setNowIntegral(integralStatModelUsable.getIntergralCount());

		// 设置会员操作日志记录创建时间
		integralLogModel.setCreateTime(DateFormatHelper.getNowTimeStr());

        // 设置会员日志记录的删除标志，操作员Uuid，操作时间
        this.create(integralLogModel);
	}

	/**
	 * 通过会员模糊查询会员日志记录数
	 * 
	 * @param qm
	 *            ,
	 * @return int
	 */
	@Override
	public int getVipclubIntegralLogModelListCountByConditon(VipclubIntegralLogQueryModel qm) {

		return myDao.getVipclubIntegralLogModelListCountByConditon(qm);
	}

	/**
	 * 通过会员id查询会员日志记录
	 * 
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return tempList<VipclubIntegralLogModel>
	 */
	@Override
	public List<VipclubIntegralLogModel> getByCustomerUuid(String customerUuid, int pageCount, int pageNo) {

		return myDao.getByCustomerUuid(customerUuid, pageCount, pageNo);
	}

	/**
	 * 根据医生id获得用户积分日志实体类
	 * 
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<VipclubIntegralLogModel> getbyDoctorUuid(String doctorUuid) {
		return myDao.getbyDoctorUuid(doctorUuid);
	}

	/**
	 * 通过会员查询model，查询该类型会员积分总数
	 * 
	 * @param qm
	 * @return int
	 */
	@Override
	public int getTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm) {
		return myDao.getTypeIntegralSumByConditon(qm);
	}

	/**
	 * 根据患者的id获取患者积分列表
	 * 
	 * @param customerUuid
	 * @return
	 */
	@Override
	public List<VipclubIntegralLogModel> getVipclubIntegralLogModelListByUuid(String customerUuid) {
		return myDao.getVipclubIntegralLogModelListByUuid(customerUuid);
	}

	/**
	 * 
	 * @Description: (根据用户类型以及用户id和描述获取日志对象)
	 * @author XP
	 * @param doctorid
	 * @param userType
	 * @param description
	 * @return
	 * @date 2016-1-19 下午1:54:17
	 */
	@Override
	public VipclubIntegralLogModel getVipclubIntegralLogByConditions(String doctorid, String userType,
			String intergralType, String description) {
		return myDao.getVipclubIntegralLogByConditions(doctorid, userType, intergralType, description);
	}

	@Override
	public VipclubIntegralLogModel getVipclubIntegralLogByQuizResultUuid(String customerUuid, String vipclubUsertypeCus,
			String quizCategoryUuid) {
		return myDao.getVipclubIntegralLogByquizResultUuid(customerUuid, vipclubUsertypeCus, quizCategoryUuid);
	}

}