package com.aebiz.b2b2c.visitprecept.sysback.web.medicalrecord;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.visitprecept.casecategory.service.CaseCategoryService;
import com.aebiz.b2b2c.visitprecept.casecategory.vo.CaseCategoryModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.service.DoctorAdviceService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.service.MedicalRecordService;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordModel;
import com.aebiz.b2b2c.visitprecept.medicalrecord.vo.MedicalRecordQueryModel;

@Controller
@RequestMapping("/sysback/medicalrecord")
public class MedicalRecordController extends BaseController<MedicalRecordModel,MedicalRecordQueryModel>{
	private MedicalRecordService myService;
	@Autowired
	public void  setMyService(MedicalRecordService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	
	/* 分类service */
	@Autowired
	private CaseCategoryService caseCategoryService;

	/* 患者service */
	@Autowired
	private CustomerInfoService customerInfoService;
	
	/* 医生建议service */
	@Autowired
	private DoctorAdviceService doctorAdviceService;
	
	/* 患者service */
	@Autowired
	private CustomerService customerService;
	
	/* 医生service */
	@Autowired
	private ServicestaffService servicestaffService;

	
	public MedicalRecordController(){
		super("visitprecept/sysback/medicalrecord","MedicalRecord",MedicalRecordController.class);
	}
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		List<CaseCategoryModel> caseCategoryList = caseCategoryService.getAll();
		model.addAttribute("caseCategoryList",caseCategoryList);
	}
	
	
	@Override
	public String toInfo(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		MedicalRecordModel m = myService.getByUuid(uuid);
		//获取患者编号
		String cutomerUuid = m.getCustomerUuid();
		
		//获取医生编号
		String doctorUuid = m.getDoctorUuid();
		
		//治疗方案编号
		String preceptUuid = m.getCurePreceptUuid();
		
        CustomerModel customerModel = customerService.getByUuid(cutomerUuid);
		
        ServicestaffModel servicestaffModel = servicestaffService.getByUuid(doctorUuid);
		CustomerInfoModel customerInfoModel = customerInfoService.getCustomerInfoModelByCustomerUuid(cutomerUuid);
		
		/*类型 0治疗方案 1 其他治疗   根据随访编号查治疗方案*/
		DoctorAdviceModel doctorAdviceModel = doctorAdviceService.getByPreceptUuid(preceptUuid,"0"); 
		DoctorAdviceModel otherDoctorAdviceModel = doctorAdviceService.getByPreceptUuid(preceptUuid,"1");  
		
		model.addAttribute("customerInfoModel", customerInfoModel);
		model.addAttribute("customerModel", customerModel);
		model.addAttribute("servicestaffModel", servicestaffModel);
		model.addAttribute("doctorAdviceModel", doctorAdviceModel);
		model.addAttribute("otherDoctorAdviceModel", otherDoctorAdviceModel);
        model.addAttribute("m", m);
		return "visitprecept/sysback/medicalrecord/MedicalRecordInfo";
	}
	
}