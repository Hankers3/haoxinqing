package com.hxq.mobile.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.entity.common.ImgUploadResponse;
import com.hxq.mobile.entity.common.ImgUploadResponse.FileMeta;
import com.hxq.mobile.entity.common.ServiceStaffComb;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.common.service.ServiceStaffInfoService")
public class ServiceStaffInfoServiceImpl extends SpringJdbcSimpleEntityService implements ServiceStaffInfoService {

		
	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;
	
	private String TABLE_COLUMNS = "info.uuid,info.delFlag,info.opeTime,info.oper,address,age,ageLimit,birthday,certCode,certImage,certType,"
			+ "city,cityName,education,hobby,image,industry,nickName,province,provinceName,region,regionName,serviceStaffJob,serviceStaffUuid,sex,synopsis,"
			+ "info.zipCode,experience,doctorClassify,professional,territory,twoCode,photoOne,photoTwo,photoThree,photoFour,"
			+ "photoFive,alipayAccount,bankAccount,bankName,healthCondition,industrialDsease,maritalStatus,nativePlace";

	/*
	 * 获得名医基本信息
	 */
	@Override
	public List<Map<String, Object>> selectfamousDoctors() {
		String sql = "select o.uuid ,s.territory,s.professional, o.realName ,s.image from service_staff as o "
				+ "left join service_staff_info as s  on  o.uuid=s.serviceStaffUuid  "
				+ "where o.sate='1' and o.doctorType='1' order by  o.createTime desc";
		// o.hospitalName o.departmentName
		List<Map<String, Object>> staffReturn = dao.query(sql, null, null, getQueryCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn);
	}

	/*
	 * 根据医生编号号获取会员基础信息
	 */
	@Override
	public ServiceStaffInfo selectByServiceStaffUuid(String serviceStaffUuid) {
		if (ObjectUtils.isEmpty(serviceStaffUuid))
			return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select ").append(TABLE_COLUMNS).append(" from service_staff_info info");
		sbf.append(" LEFT JOIN basicdata_area_province province ON  info.`province` = province.`uuid`");
		sbf.append(" LEFT JOIN basicdata_area_city city ON  info.`city` = city.`uuid` ");
		sbf.append(" LEFT JOIN basicdata_area_region region ON  info.`region` = region.`uuid` ");
		sbf.append(" where serviceStaffUuid=? limit 0,1");

		List<Map<String, Object>> lstReturn = dao.query(sbf.toString(), new Object[] { serviceStaffUuid }, null,
				getQueryCache());

		return ObjectUtils.isEmpty(lstReturn) ? null
				: SimpleBean2DBHelper.map2Bean(lstReturn.get(0), ServiceStaffInfo.class);
	}

	/**
	 * 更新医生员基础信息
	 * 
	 * @param customerModel
	 * @throws Exception 
	 */
	
	@Override
	public void updateServicestaffinfo(ServiceStaffComb ssc,
			MultipartFile[] imgFiles, MultipartFile[] certFiles) throws Exception {
		ServiceStaffInfo cim = ssc.getServiceStaffInfo();

		if (!StringUtil.isEmpty(cim.getUuid())) {
			this.uploadImage(cim, imgFiles, certFiles);
			update(cim);
		} else {
			cim.setServiceStaffUuid(ssc.getServiceStaff().getUuid());
			// 上传图片
			this.uploadImage(cim, imgFiles, certFiles);
			cim.setUuid(IdentityHelper.uuid2());
			cim.setOpeTime(DateFormatHelper.getNowTimeStr());
			insert(cim);
		}
	}
	
	
	/**
	 * 文件上传
	 * 
	 * @param customerShopLevelModel
	 * @param files
	 * @return
	 */
	public ServiceStaffInfo uploadImage(ServiceStaffInfo m,
			MultipartFile[] imageFile, MultipartFile[] cretFile) {
		
		ImgUploadResponse result = null;
		try {
			String imageUuid = "";
			if (imageFile != null && imageFile.length > 0) {
				result = imgUploadService.updateForUpload(false, imageFile);
				if(result.getFiles() != null && result.getFiles().size() > 0){
					//取出第一条数据
					FileMeta fileMeta = result.getFiles().get(0);
					imageUuid = fileMeta.getId();
				}
				
			} 
			m.setImage(imageUuid);
			
			
			
			String certUuid = "";
			if (cretFile != null && cretFile.length > 0) {
				result = imgUploadService.updateForUpload(false, cretFile);
				if(result.getFiles() != null && result.getFiles().size() > 0){
					//取出第一条数据
					FileMeta fileMeta = result.getFiles().get(0);
					certUuid = fileMeta.getId();
				}
				
			} 
			m.setCertImage(certUuid);
	
		} catch (Exception ex) {
			ex.printStackTrace();
			m.setPhotoFive("");
		}
		
		return m;
	}
	
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCacheName() {
		return "h1";
	}

	@Override
	protected String getQueryCacheName() {
		return "ServicestaffinfoService";
	}
}
