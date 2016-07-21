package com.aebiz.b2b2c.visitprecept.sysback.web.visitrecord;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.service.DoctorAdviceService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.service.DrugReactionService;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordQueryModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.service.VisitRecordExtendService;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;

@Controller
@RequestMapping("/sysback/visitrecord")
public class VisitRecordController extends BaseController<VisitRecordModel,VisitRecordQueryModel>{
	private VisitRecordService myService;
	@Autowired
	public void  setMyService(VisitRecordService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public VisitRecordController(){
		super("visitprecept/sysback/visitrecord","VisitRecord",VisitRecordController.class);
	}
	
	// 注入医生的service
	@Autowired
	private ServicestaffService servicestaffService;

	// 注入患者的service
	@Autowired
	private CustomerInfoService customerInfoService;
	
	// 注入随访方案的service
	@Autowired
	private DoctorAdviceService doctorAdviceService;
	// 注入不良反应的service
	@Autowired
	private DrugReactionService drugReactionService;

	// 注入不良反应扩展字段的service
	@Autowired
	private VisitRecordExtendService visitRecordExtendService;


	/**
	 * 查看随访申请详细信息
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toDetail/{uuid}")
	public String toView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
	  //通过id获取VisitRecordModel对象
		VisitRecordModel visitRecordModel = myService.getByUuid(uuid);
		preparedUpdateData(model, request);
		model.addAttribute("m", visitRecordModel);
		//获取医生的servicestaffModel对像
		String serviceStaffUuid = visitRecordModel.getServiceStaffUuid(); 
		if(!StringUtil.isEmpty(serviceStaffUuid)){
			ServicestaffModel servicestaffModel = servicestaffService.getByUuid(serviceStaffUuid);
			model.addAttribute("servicestaffModel", servicestaffModel);
		}
		
		//获取患者的servicestaffModel对像
		String customerUuid = visitRecordModel.getCustomerUuid(); 
		if(!StringUtil.isEmpty(customerUuid)){
			CustomerInfoModel customerInfoModel = customerInfoService.getByUuid(customerUuid);
		   model.addAttribute("customerInfoModel", customerInfoModel);
		}
		
		//获取随访方案的DoctorAdviceModel对像
		if(!StringUtil.isEmpty(uuid)){
			DoctorAdviceModel  doctorAdviceModel =doctorAdviceService.getByPreceptUuid(uuid, "0");
			if(doctorAdviceModel!=null){
		         model.addAttribute("doctorAdviceModel", doctorAdviceModel);
		    }
		}
		
       //获取DrugReactionModel对象
        if(!StringUtil.isEmpty(uuid)){
        DrugReactionModel drugReactionModel = drugReactionService.getLastDrugReactionModelByUuid(uuid);
        if(drugReactionModel!=null){
         model.addAttribute("drugReactionModel", drugReactionModel);
         }
		}
        
        //获取VisitRecordExtendModel对象
        if(!StringUtil.isEmpty(uuid)){
         List<VisitRecordExtendModel> visitRecordExtendModelList=visitRecordExtendService.getAllByVisitRecordUuid(uuid);
        if(visitRecordExtendModelList!=null&&visitRecordExtendModelList.size()>0){
         model.addAttribute("visitRecordExtendModelList", visitRecordExtendModelList);
         }
		}
		return "visitprecept/sysback/visitrecord/VisitRecordDetail";
		
	}
	}
	
	
