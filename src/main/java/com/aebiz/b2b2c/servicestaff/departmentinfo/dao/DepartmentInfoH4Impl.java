package com.aebiz.b2b2c.servicestaff.departmentinfo.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoQueryModel;
import com.aebiz.b2b2c.servicestaff.utils.ExcelUtils;

@Repository
public class DepartmentInfoH4Impl extends
		BaseH4Impl<DepartmentInfoModel, DepartmentInfoQueryModel> implements
		DepartmentInfoDAO {
	/**
	 * 检查科室名是否存在
	 * 
	 * @param departmentName
	 * @return
	 */
	@Override
	public boolean checkDepartmentName(String departmentName, String uuid) {
		if (StringUtil.isEmpty(departmentName)) {
			return false;
		}

		StringBuffer hql = new StringBuffer(
				" select o.uuid from DepartmentInfoModel as o ");
		hql.append(" where o.departmentName =:departmentName ");
		if (!StringUtil.isEmpty(uuid)) {
			hql.append(" and o.uuid !=:uuid ");
		}
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("departmentName", departmentName);
		if (!StringUtil.isEmpty(uuid)) {
			q.setString("uuid", uuid);
		}

		List list = q.list();
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取科室名
	 * 
	 * @param uuid
	 * @return
	 */
	public String getDepartmentNameByUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				" select o.departmentName from DepartmentInfoModel as o where 1=1 ");
		hql.append(" and o.uuid =:uuid ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("uuid", uuid);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}

		return "";

	}

	/**
	 * 获取uuid通过科室名
	 * 
	 * @param departmentUuid
	 * @return
	 */
	@Override
	public String getUuidByDepartmentName(String departmentName) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from DepartmentInfoModel as o where 1=1 ");
		hql.append(" and o.departmentName =:departmentName ");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("departmentName", departmentName);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}

		return "";

	}

	/**
	 * 导入科室的Excel文件
	 */
	@Override
	public List<Object> updloadExcel(MultipartFile[] files) {
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				if (file != null && !file.isEmpty()) {
					DepartmentInfoModel model = new DepartmentInfoModel();
					Object[] columns = { "departmentId", "departmentName",
							"mobile", "note" };
					try {
						List list = ExcelUtils
								.importExcel(file, model, columns);
						if (list != null && list.size() > 0) {
							return list;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取所有数据的Uuid
	 * @return
	 */
	@Override
	public List<String> getAllUuids() {
		StringBuffer hql = new StringBuffer(" select o.uuid from DepartmentInfoModel as o ");
		hql.append(" order by o.createTime desc ");
		Query q = this.getH4Session().createQuery(hql.toString());
		
		List<String> list = q.list();
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}
}
