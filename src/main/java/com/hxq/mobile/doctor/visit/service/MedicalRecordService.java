package com.hxq.mobile.doctor.visit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.hxq.mobile.entity.common.ImgUpload;
import com.hxq.mobile.entity.visit.MedicalRecord;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.repository.SimpleEntityService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 患者病例
 *
 */
@Service("com.hxq.mobile.doctor.visit.service.MedicalRecordService")
public class MedicalRecordService extends SpringJdbcSimpleEntityService {

	@Resource(name="com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;
	
	@Resource(name="com.hxq.mobile.doctor.visit.service.MedicalRecordService")
	private SimpleEntityService service;

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		if(RequestUtil.isEmpty(form, "customerUuid")) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> columns = new ArrayList<String>();

		sbf.append(" from medical_record as a left join service_staff as c on a.doctorUuid=c.uuid");

		sbf.append(columns.isEmpty()?" where":" and").append(" a.customerUuid = ?");
		columns.add(RequestUtil.getFormValue(form, "customerUuid"));

		if(RequestUtil.isEmpty(form, "serviceStaffUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.doctorUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "serviceStaffUuid"));
		} else if(RequestUtil.isEmpty(form, "doctorUuid")==false) {
			sbf.append(columns.isEmpty()?" where":" and").append(" a.doctorUuid = ?");
			columns.add(RequestUtil.getFormValue(form, "doctorUuid"));
		}

		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();

		sbf.delete(0, sbf.length());
		String strQuery = sbf.append("select a.uuid,a.opeTime,a.createTime,a.caseCategoryType,a.doctorUuid,")
				.append("a.seeDoctorTime,a.image1,a.image2,a.image3,a.image4,a.image5,a.hospitalUuid,c.realName")
				.append(strWhere).append(" order by a.createTime desc").toString();

		List<Map<String, Object>> lstReturn = null;
		Object[] params = columns.isEmpty() ? null : columns.toArray();
		if(isPagination != null && isPagination.booleanValue()) {
			PaginationHelper page = new PaginationHelper(dao, getCache(), form);
			lstReturn = page.queryByPage(strTotal, strQuery, params, null);
		} else {
			lstReturn = dao.query(strQuery, params, null, getCache());
		}
		if(ObjectUtils.isEmpty(lstReturn)) return null;

		for(Map<String, Object> row : lstReturn) {
			doImgUrl(row, "image1");
			doImgUrl(row, "image2");
			doImgUrl(row, "image3");
			doImgUrl(row, "image4");
			doImgUrl(row, "image5");
		}
		return lstReturn;
	}

	/**
	 * 根据图片id获取图片url
	 * @param row
	 * @param imgColumn
	 * @throws Exception
	 */
	private void doImgUrl(Map<String, Object> row, String imgColumn) throws Exception {
		String id = StringUtils.trimToEmpty(row.get(imgColumn));
		if(id.length() < 1 || id.startsWith("http:") || id.startsWith("https:")) return;
		ImgUpload imginfo = (ImgUpload) imgUploadService.select(new ImgUpload(id));
		if(imginfo != null) row.put(imgColumn, imginfo.imgUrl);
	}

	public int insertSave2(MedicalRecord bean) throws Exception{
		int intReturn = -1;
		bean.setCreateTime(DateFormatHelper.getNowTimeStr());
    	if(ObjectUtils.isEmpty(bean.getSeeDoctorTime())) {
    		bean.setSeeDoctorTime(DateFormatHelper.getNowTimeStr());
    	}
    	intReturn = service.insert(bean);
		return intReturn;
	}
	
	@Override
	protected String getCacheName() {return "h1";}

	@Override
	protected String getQueryCacheName() {
		// TODO Auto-generated method stub
		return null;
	}
}
