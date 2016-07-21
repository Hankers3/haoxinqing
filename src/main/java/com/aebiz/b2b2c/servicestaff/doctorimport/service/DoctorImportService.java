package com.aebiz.b2b2c.servicestaff.doctorimport.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportQueryModel;

public interface DoctorImportService extends BaseService<DoctorImportModel,DoctorImportQueryModel>{
	/**
	 * 根据真实姓名查询 医生信息
	 * @param realName
	 * @return
	 */
	public List<DoctorImportModel> getDoctorByRealName(String realName);
	/**
	 * 导入医生,并创建数据到表
	 */
	public boolean importDoctor(MultipartFile file);
}
