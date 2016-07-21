package com.aebiz.b2b2c.servicestaff.doctorimport.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.departmentinfo.service.DepartmentInfoService;
import com.aebiz.b2b2c.servicestaff.doctorimport.dao.DoctorImportDAO;
import com.aebiz.b2b2c.servicestaff.doctorimport.service.DoctorImportService;
import com.aebiz.b2b2c.servicestaff.doctorimport.util.ExcelUtils;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportQueryModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.service.impl.ServicestaffcombServiceImpl;
import com.aebiz.b2b2c.servicestaff.servicestaffinfo.vo.DoctorProfessionalEnum;

@Service
@Transactional
public class DoctorImportServiceImpl extends
		BaseServiceImpl<DoctorImportModel, DoctorImportQueryModel> implements
		DoctorImportService {
	private DoctorImportDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(DoctorImportDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/* 注解医生复合service */
	@Autowired
	private ServicestaffcombServiceImpl ServicestaffcombServiceImpl;

	/* 注解医院service */
	@Autowired
	private HospitalInfoService hospitalInfoService;
	/* 注解科室service */
	@Autowired
	private DepartmentInfoService departmentInfoService;

	@Override
	public String create(DoctorImportModel m) {
		m.setUuid(us.getNextUuid("DoctorImport", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(DoctorImportModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(DoctorImportModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据真实姓名查询 医生信息
	 * 
	 * @param realName
	 * @return
	 */
	@Override
	public List<DoctorImportModel> getDoctorByRealName(String realName) {

		return myDao.getDoctorByRealName(realName);
	}

	/**
	 * 导入医生 void
	 */
	@Override
	public boolean importDoctor(MultipartFile file) {
		if (file != null) {

			if (file != null && !file.isEmpty()) {
				DoctorImportModel model = new DoctorImportModel();
				Object[] columns = { "hospitalUuid", "departmentUuid",
						"realName", "mobile", "email", "professional", };
				try {
					List list = ExcelUtils.importExcel(file, model, columns);
					System.out.println(list);
					if (list != null && list.size() > 0) {
						for (Object object : list) {
							if (object != null) {
								DoctorImportModel doctorImportModel = (DoctorImportModel) object;
								String hospitalName = "";
								hospitalName = doctorImportModel
										.getHospitalUuid();
								String departmentName = "";
								departmentName = doctorImportModel
										.getDepartmentUuid();
								// 根据医院名得到医院id
								String hospitalUuid = "";
								hospitalUuid = hospitalInfoService
										.getUuidByHospitalName(hospitalName);
								doctorImportModel.setHospitalUuid(hospitalUuid);
								// 根据科室名得到科室id
								String departmentUuid = "";
								departmentUuid = departmentInfoService
										.getUuidByDepartmentName(departmentName);
								doctorImportModel
										.setDepartmentUuid(departmentUuid);
								// 根据职称名得到职称id
								String professional = "";
								professional = doctorImportModel
										.getProfessional();
								// 通过枚举中的value找到key
								professional = DoctorProfessionalEnum
										.getkey(professional);
								doctorImportModel.setProfessional(professional);
								// 把表格中的信息保存到数据库
								doctorImportModel.setUuid(us.getNextUuid(
										"DoctorImport", 10));
								doctorImportModel
										.setDelFlag(BaseModel.DEL_FLAG_VALID);
								doctorImportModel.setOper(LoginUserHelper
										.getLoginUserUuid());
								doctorImportModel.setOpeTime(DateFormatHelper
										.getNowTimeStr());
								doctorImportModel
										.setCreateTime(DateFormatHelper
												.getNowTimeStr());
								// 流水号
								doctorImportModel
										.setDoctorId(ServicestaffcombServiceImpl
												.createDoctorNo());

								// 创建医生
								myDao.create(doctorImportModel);
							}
						}
					}

					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

}