package com.aebiz.b2b2c.visitprecept.visitprecept.service.impl;

import java.util.List;

import com.wxcommon.util.IdentityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.visitprecept.dao.VisitPreceptDAO;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptQueryModel;

@Service
@Transactional
public class VisitPreceptServiceImpl extends BaseServiceImpl<VisitPreceptModel, VisitPreceptQueryModel>
		implements VisitPreceptService {
	private VisitPreceptDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(VisitPreceptDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 创建随访方案
	 * 
	 * @param m
	 * @return
	 */
	@Override
	public String create(VisitPreceptModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	/**
	 * 更新随访方案
	 * 
	 * @param m
	 * @return
	 */
	@Override
	public void update(VisitPreceptModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除随访方案
	 * 
	 * @param m
	 * @return
	 */
	@Override
	public void delete(VisitPreceptModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通过编号获取随访方案名称
	 * 
	 * @param preceptUuid
	 * @return 方案名称
	 */
	public String getPreceptNameByUUid(String preceptUuid) {
		return myDao.getPreceptNameByUUid(preceptUuid);

	}

	/**
	 * 通过医生id获取所有的随访方案名称
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<VisitPreceptModel> getAllVisitpreceptByDoctorid(String doctorid) {
		return myDao.getAllVisitpreceptByDoctorid(doctorid);
	}

	/**
	 * 通过医生id获取所有的随访方案名称
	 * @return
	 */
	@Override
	public List<VisitPreceptModel> getRecommendVisitpreceptByDoctorid() {
		return myDao.getRecommendVisitpreceptByDoctorid();
	}

	/**
	 * 通过医生id获取医生的随访方案（非推荐）
	 *
	 * @param doctorid
	 * @return
	 */
	@Override
	public List<VisitPreceptModel> getMyVisitpreceptByDoctorid(String doctorid) {
		return myDao.getMyVisitpreceptByDoctorid(doctorid);
	}

	/**
	 * 根据随访方案的id删除随访方案
	 * 
	 * @param visitUuid
	 */
	@Override
	public void deleteVisitPreceptServiceByUuid(String visitUuid) {
		myDao.deleteVisitPreceptServiceByUuid(visitUuid);
	}

	/**
	 * 根据随访方案的id删除随访方案的数据
	 * 
	 * @param visitUuid
	 */
	@Override
	public void deleteVisitPrecept(String visitUuid) {
		myDao.deleteVisitPrecept(visitUuid);
	}

}