package com.aebiz.b2b2c.servicestaff.packagedoctor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.packagedoctor.dao.PackageDoctorDAO;
import com.aebiz.b2b2c.servicestaff.packagedoctor.service.PackageDoctorService;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorModel;
import com.aebiz.b2b2c.servicestaff.packagedoctor.vo.PackageDoctorQueryModel;

@Service
@Transactional
public class PackageDoctorServiceImpl extends
		BaseServiceImpl<PackageDoctorModel, PackageDoctorQueryModel> implements
		PackageDoctorService {
	private PackageDoctorDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(PackageDoctorDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(PackageDoctorModel m) {
		m.setUuid(us.getNextUuid("PackageDoctor", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(PackageDoctorModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(PackageDoctorModel m) {
		super.delete(m);
	}

	/**
	 * 通过医生id得到对象
	 * 
	 * @param doctorid
	 * @return
	 */
	@Override
	public PackageDoctorModel getByDoctorUuid(String doctorid) {
		return myDao.getByDoctorUuid(doctorid);
	}

	/**
	 * 通过医生id和私人医生id得到对象
	 * 
	 * @param doctorid
	 * @param packageUuid
	 * @return
	 */
	@Override
	public PackageDoctorModel getByDoctorUuidAndPackageUuid(String doctorid,
			String packageUuid) {
		return myDao.getByDoctorUuidAndPackageUuid(doctorid,packageUuid);
	}
	/**
	 * 
	 * @Description: (这里用一句话描述这个方法的作用)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-18 下午1:29:08
	 */
        @Override
        public List<String> getPackageUuidsByDoctorUuid(String doctorUuid) {
            return myDao.getPackageUuidsByDoctorUuid(doctorUuid);
        }
}