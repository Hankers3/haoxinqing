package com.aebiz.b2b2c.servicestaff.departmentinfo.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoQueryModel;

public interface DepartmentInfoDAO extends
		BaseDAO<DepartmentInfoModel, DepartmentInfoQueryModel> {
	/**
	 * 检查科室名是否存在
	 * 
	 * @param departmentName
	 * @return
	 */
	public boolean checkDepartmentName(String departmentName, String uuid);

	/**
	 * 获取科室名
	 * 
	 * @param uuid
	 * @return
	 */
	public String getDepartmentNameByUuid(String uuid);

	/**
	 * 获取uuid通过科室名
	 * 
	 * @param departmentUuid
	 * @return
	 */
	public String getUuidByDepartmentName(String departmentName);

	/**
	 * 导入科室的文件
	 * 
	 * @param files
	 * @return
	 */
	public List<Object> updloadExcel(MultipartFile[] files);
	
	/**
	 * 获取所有数据的Uuid
	 * @return
	 */
	public List<String> getAllUuids();
}