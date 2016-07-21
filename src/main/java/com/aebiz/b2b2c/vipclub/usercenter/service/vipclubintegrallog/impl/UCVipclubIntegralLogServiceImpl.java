package com.aebiz.b2b2c.vipclub.usercenter.service.vipclubintegrallog.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.vipclub.usercenter.service.vipclubintegrallog.UCVipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.dao.VipclubIntegralLogDAO;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.dao.VipclubIntegralStatDAO;

/**
 * 会员积分日志service实现类
 * 
 * @author huyingying
 * 
 */
@Service
@Transactional
public class UCVipclubIntegralLogServiceImpl extends BaseServiceImpl<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> implements UCVipclubIntegralLogService {
	/* 会员积分日志Dao */
	private VipclubIntegralLogDAO myDao = null;

	@Autowired
	/* 会员积分统计表Dao */
	private VipclubIntegralStatDAO statDAO = null;

	/* Uuid服务接口 */
	@Autowired
	private UuidService us;

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
	 * 通过会员查询model，查询该类型会员积分总数
	 * 
	 * @param qm
	 * @return int
	 */
	public int getTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm){
		return myDao.getTypeIntegralSumByConditon(qm);
	}
}