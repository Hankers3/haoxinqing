package com.hxq.mobile.doctor.common.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.hxq.mobile.common.service.BasicDataService;
import com.hxq.mobile.common.service.CustomerInfoService;
import com.hxq.mobile.common.service.CustomerService;
import com.hxq.mobile.common.service.ServiceStaffInfoService;
import com.hxq.mobile.common.service.ServiceStaffService;
import com.hxq.mobile.doctor.order.service.OrderMainService;
import com.hxq.mobile.doctor.visit.service.DoctorCustomerService;
import com.hxq.mobile.doctor.visit.service.IllnessRecordService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.repository.SimpleEntityService;

/**
 * common公共注入service
 */
@Component
public class CommonMediator {
	
	 	@Autowired
	 	private FileUploadHelper fileUpload;
	    @Autowired
	    private FileService fileService;

	    @Resource(name = "com.hxq.mobile.doctor.common.service.impl.DoctorCommonServiceImpl")
	    private DoctorCommonService doctorCommonService;

	    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorCustomerService")
	    private DoctorCustomerService doctorCustomerService;

	    @Resource(name = "com.hxq.mobile.doctor.order.service.OrderMainService")
	    private OrderMainService orderMainService;

	    @Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	    private ImgUploadService imgUploadService;

	    @Resource(name = "com.hxq.mobile.common.service.ServiceStaffService")
	    private ServiceStaffService servicestaffService;

	    @Resource(name = "com.hxq.mobile.common.service.ServiceStaffInfoService")
	    private ServiceStaffInfoService servicestaffinfoService;

	    @Resource(name = "com.hxq.mobile.common.service.BasicDataService")
	    private BasicDataService basicDataService;

	    @Resource(name = "com.hxq.mobile.doctor.visit.service.VisitRecordService")
	    private VisitRecordService visitRecordService;

	    @Resource(name="com.hxq.mobile.common.service.CustomerService")
		private CustomerService customerService;

		@Resource(name="com.hxq.mobile.common.service.CustomerInfoService")
		private CustomerInfoService customerInfoService;
	    
		@Resource(name="com.hxq.mobile.doctor.visit.service.MedicalRecordService")
		private SimpleEntityService simpleEntityService;
		
		@Resource(name="com.hxq.mobile.doctor.visit.service.IllnessRecordService")
		private IllnessRecordService illnessService;

		public FileUploadHelper getFileUpload() {
			return fileUpload;
		}

		public FileService getFileService() {
			return fileService;
		}

		public DoctorCommonService getDoctorCommonService() {
			return doctorCommonService;
		}

		public DoctorCustomerService getDoctorCustomerService() {
			return doctorCustomerService;
		}

		public OrderMainService getOrderMainService() {
			return orderMainService;
		}

		public ImgUploadService getImgUploadService() {
			return imgUploadService;
		}

		public ServiceStaffService getServicestaffService() {
			return servicestaffService;
		}

		public ServiceStaffInfoService getServicestaffinfoService() {
			return servicestaffinfoService;
		}

		public BasicDataService getBasicDataService() {
			return basicDataService;
		}

		public VisitRecordService getVisitRecordService() {
			return visitRecordService;
		}

		public CustomerService getCustomerService() {
			return customerService;
		}

		public CustomerInfoService getCustomerInfoService() {
			return customerInfoService;
		}

		public SimpleEntityService getSimpleEntityService() {
			return simpleEntityService;
		}

		public IllnessRecordService getIllnessService() {
			return illnessService;
		}

}
