package com.aebiz.b2b2c.visitprecept.visitrecordextend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo.VisitPreceptExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.service.VisitRecordExtendService;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.dao.VisitRecordExtendDAO;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendQueryModel;

@Service
@Transactional
public class VisitRecordExtendServiceImpl extends BaseServiceImpl<VisitRecordExtendModel,VisitRecordExtendQueryModel> implements VisitRecordExtendService {
	private VisitRecordExtendDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(VisitRecordExtendDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VisitRecordExtendModel m) {
		m.setUuid(us.getNextUuid("VisitRecordExtend",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(VisitRecordExtendModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(VisitRecordExtendModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 获取访问记录的扩展字段的集合
	 */
	public List<VisitRecordExtendModel> getAllByVisitRecordUuid(String uuid) {
		return myDao.getAllByVisitRecordUuid(uuid);
	}

	  /**
         * 根据随访id和类型获取随访扩展字段
         * @param visitRecordUuid
         * @param type
         * @return
         */
        @Override
        public List<VisitRecordExtendModel> getAllVisitRecordExtendListByPreceptUuid(
                String preceptUuid, String type) {
            return myDao.getAllVisitRecordExtendListByPreceptUuid(preceptUuid,type);
        }
}