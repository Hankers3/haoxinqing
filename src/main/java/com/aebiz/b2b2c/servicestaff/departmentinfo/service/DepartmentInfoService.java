package com.aebiz.b2b2c.servicestaff.departmentinfo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoQueryModel;

public interface DepartmentInfoService extends BaseService<DepartmentInfoModel,DepartmentInfoQueryModel>{
	/**
	 * 检查科室名是否存在
	 * @param departmentName
	 * @return
	 */
	public boolean checkDepartmentName(String departmentName,String uuid);
	
	/**
	 * 获取科室名
	 * @param uuid
	 * @return
	 */
	public String getDepartmentNameByUuid(String uuid);
	/**
	 * 获取uuid通过科室名
	 * @param departmentUuid
	 * @return
	 */
	public String getUuidByDepartmentName(String departmentUuid);
	
	/**
         * 导入科室的excel文件
         * @param departmentUuid
         * @return
         */
         public List<Object> updloadExcel(MultipartFile[] files);
}
