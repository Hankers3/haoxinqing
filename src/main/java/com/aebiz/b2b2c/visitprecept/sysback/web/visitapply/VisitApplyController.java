package com.aebiz.b2b2c.visitprecept.sysback.web.visitapply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerService;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.visitprecept.doctoradvice.service.DoctorAdviceService;
import com.aebiz.b2b2c.visitprecept.doctoradvice.vo.DoctorAdviceModel;
import com.aebiz.b2b2c.visitprecept.drugreaction.service.DrugReactionService;
import com.aebiz.b2b2c.visitprecept.drugreaction.vo.DrugReactionModel;
import com.aebiz.b2b2c.visitprecept.visitapply.service.VisitApplyService;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyModel;
import com.aebiz.b2b2c.visitprecept.visitapply.vo.VisitApplyQueryModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.vo.VisitRecordModel;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.service.VisitRecordExtendService;
import com.aebiz.b2b2c.visitprecept.visitrecordextend.vo.VisitRecordExtendModel;

@Controller
@RequestMapping("/sysback/visitapply")
public class VisitApplyController extends
		BaseController<VisitApplyModel, VisitApplyQueryModel> {
	private VisitApplyService myService;

	@Autowired
	public void setMyService(VisitApplyService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	/* 患者service */
	@Autowired
	private CustomerInfoService customerInfoService;

	/* 患者service */
	@Autowired
	private CustomerService customerService;

	/* 医生service */
	@Autowired
	private ServicestaffService servicestaffService;

	@Autowired
	private DoctorAdviceService doctorAdviceService;
	/* 随访表单service */
	@Autowired
	private VisitRecordService visitRecordService;

	/* 随访方案service */
	@Autowired
	private VisitPreceptService visitPreceptService;
	// 注入不良反应的service
	private DrugReactionService drugReactionService;

	@Autowired
	public void setDrugReactionService(DrugReactionService drugReactionService) {
		this.drugReactionService = drugReactionService;
	}

	// 注入不良反应扩展字段的service
	private VisitRecordExtendService visitRecordExtendService;

	@Autowired
	public void setVisitRecordExtendService(
			VisitRecordExtendService visitRecordExtendService) {
		this.visitRecordExtendService = visitRecordExtendService;
	}

	public VisitApplyController() {
		super("visitprecept/sysback/visitapply", "VisitApply",
				VisitApplyController.class);
	}

	@Override
	protected void preparedListData(Model model1,
			HttpServletRequest httpservletrequest) {

	}

	@Override
	public String toInfo(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		BaseModel m = bs.getByUuid(uuid);
		CustomerModel customerModel = customerService.getByUuid(myService
				.getByUuid(uuid).getCustomerUuid());
		ServicestaffModel servicestaffModel = servicestaffService
				.getByUuid(myService.getByUuid(uuid).getServiceStaffUuid());
		CustomerInfoModel customerInfoModel = customerInfoService
				.getCustomerInfoModelByCustomerUuid(myService.getByUuid(uuid)
						.getCustomerUuid());
		VisitRecordModel visitRecordModel = visitRecordService
				.getByUuid(myService.getByUuid(uuid).getVisitRecordUuid());
		VisitPreceptModel visitPreceptModel = visitPreceptService
				.getByUuid(myService.getByUuid(uuid).getVisitPreceptUuid());
		
		model.addAttribute("customerInfoModel", customerInfoModel); // 存入患者基本信息
		model.addAttribute("customerModel", customerModel); // 存入患者信息
		model.addAttribute("servicestaffModel", servicestaffModel); // 存入医生信息
		model.addAttribute("visitRecordModel", visitRecordModel); // 存入随访表单信息
		model.addAttribute("visitPreceptModel", visitPreceptModel); // 存入随访方案信息

		// 获取随访方案的DoctorAdviceModel对像(已经写好了直接调用就可以)
		if (!StringUtil.isEmpty(uuid)) {

			DoctorAdviceModel doctorAdviceModel = doctorAdviceService.getByPreceptUuid(uuid, "0");
			if (doctorAdviceModel != null) {
				model.addAttribute("doctorAdviceModel", doctorAdviceModel);
			}
		}

		// 获取DrugReactionModel对象
		if (!StringUtil.isEmpty(uuid)) {
			DrugReactionModel drugReactionModel = drugReactionService
					.getLastDrugReactionModelByUuid(uuid);
			if (drugReactionModel != null) {
				model.addAttribute("drugReactionModel", drugReactionModel);
			}
		}

		// 获取VisitRecordExtendModel对象
		if (!StringUtil.isEmpty(uuid)) {
			List<VisitRecordExtendModel> visitRecordExtendModelList = visitRecordExtendService
					.getAllByVisitRecordUuid(uuid);
			if (visitRecordExtendModelList != null
					&& visitRecordExtendModelList.size() > 0) {
				model.addAttribute("visitRecordExtendModelList",
						visitRecordExtendModelList);
			}
		}

		model.addAttribute("m", m);
		return "visitprecept/sysback/visitapply/VisitApplyInfo";
	}
}