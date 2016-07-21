package com.aebiz.b2b2c.visitprecept.visitapply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.visitprecept.visitapply.dao.VisitApplyDAO;
import com.aebiz.b2b2c.visitprecept.visitapply.service.VisitApplyService;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyModel;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyQueryModel;

@Service
@Transactional
public class VisitApplyServiceImpl extends BaseServiceImpl<VisitApplyModel,VisitApplyQueryModel> implements VisitApplyService {
	private VisitApplyDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(VisitApplyDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VisitApplyModel m) {
		m.setUuid(us.getNextUuid("VisitApply",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		// 为创建时间赋值
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(VisitApplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(VisitApplyModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	
	/**
	 * 获取患者编号
	 * @param preceptUuid
	 * @return
	 */
	public List<String> getCustomerIds(String preceptUuid){
		return myDao.getCustomerIds(preceptUuid);
		
	}

    @Override
    public List<VisitApplyModel> getAllListByDoctorId(String doctorid) {
        // TODO Auto-generated method stub
        return myDao.getAllListByDoctorId(doctorid);
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

	@Override
	public List<VisitApplyModel> getByPreceptUuid(String doctorUuid, String preceptUuid) {
		return myDao.getByPreceptUuid(doctorUuid,preceptUuid);
	}
}