package com.aebiz.b2b2c.servicestaff.sysback.web.doctorimport;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.servicestaff.doctorimport.service.DoctorImportService;
import com.aebiz.b2b2c.servicestaff.doctorimport.util.ExcelUtils;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportConst;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportQueryModel;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.service.HospitalInfoService;
import com.aebiz.b2b2c.servicestaff.hospitalinfo.vo.HospitalInfoModel;

@Controller
@RequestMapping("/sysback/doctorimport")
public class DoctorImportController extends
		BaseController<DoctorImportModel, DoctorImportQueryModel> {
	private DoctorImportService myService;

	@Autowired
	public void setMyService(DoctorImportService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public DoctorImportController() {
		super("servicestaff/sysback/doctorimport", "DoctorImport",
				DoctorImportController.class);
	}

	/* 注入医院service */
	@Autowired
	private HospitalInfoService hospitalInfoService;

	/**
	 * 进入导入医生界面
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/toImpotDoctor" }, method = { RequestMethod.GET })
	public String toImpotDoctor(Model model, HttpServletRequest request) {
		// preparedListData(model, request);
		return "servicestaff/sysback/doctorimport/DoctorImport";

	}

	/**
	 * 跳转到导出模板页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importData")
	public String importData(
			@RequestParam(value = "myExcelFile") MultipartFile[] myExcelFiles,
			HttpServletResponse response, HttpServletRequest request,
			Model model) throws Exception {
		// Excel文件
		MultipartFile excelFile = null;

		if (myExcelFiles != null && myExcelFiles.length > 0) {
			excelFile = myExcelFiles[0];
		}

		boolean flag = false;
		try {

			flag = importData(excelFile);

		} catch (Exception e) {
			model.addAttribute("error", e);
		}
		model.addAttribute("handleResult", flag);

		return "redirect:toList";
		// return "product/sysback/product/ProductImport";
	}

	/**
	 * 导入医生信息
	 * 
	 * @param file
	 * @param newImagePath
	 * @return
	 */
	private boolean importData(MultipartFile file) {

		if (file != null && !file.isEmpty()) {
			if (myService.importDoctor(file)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 导出医生模板
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public String export(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String tableName = "医院科室信息统计";
		String[] headr = new String[DoctorImportConst.PARAMKEYLIST.size()];
		String[] columns = new String[DoctorImportConst.PARAMKEYLIST.size()];
		for (int i = 0; i < DoctorImportConst.PARAMKEYLIST.size(); i++) {
			headr[i] = MessageHelper.getMessage(DoctorImportConst.PARAMKEYLIST
					.get(i));
			columns[i] = DoctorImportConst.PARAMMAP
					.get(DoctorImportConst.PARAMKEYLIST.get(i));
		}

		List<Object> tmList = new ArrayList<Object>();
		// 实例数据 用不到
		/*
		 * DoctorImportModel dim = new DoctorImportModel();
		 * dim.setDepartmentUuid(DoctorImportConst.PARAM_DEMO_MAP)
		 * tm.setCategoryUuid
		 * (DoctorImportConst.PARAM_DEMO_MAP.get("categoryUuid"));
		 * tm.setProductName
		 * (DoctorImportConst.PARAM_DEMO_MAP.get("productName"));
		 * tm.setAdviceNote(DoctorImportConst.PARAM_DEMO_MAP.get("adviceNote"));
		 * tm.setProductNo(DoctorImportConst.PARAM_DEMO_MAP.get("productNo"));
		 * tm.setListPrice(DoctorImportConst.PARAM_DEMO_MAP.get("listPrice"));
		 * tm.setShopPrice(DoctorImportConst.PARAM_DEMO_MAP.get("shopPrice"));
		 * //tm.setIntegral(ProductImportConst.PARAM_DEMO_MAP.get("integral"));
		 * //tm.setLeastIntegral(ProductImportConst.PARAM_DEMO_MAP.get(
		 * "leastIntegral"));
		 * tm.setBrandUuid(DoctorImportConst.PARAM_DEMO_MAP.get("brandUuid"));
		 * tm.setStoreUuid(DoctorImportConst.PARAM_DEMO_MAP.get("storeUuid"));
		 * tm.setWeight(DoctorImportConst.PARAM_DEMO_MAP.get("weight"));
		 * tm.setWidth(DoctorImportConst.PARAM_DEMO_MAP.get("width"));
		 * tm.setHeight(DoctorImportConst.PARAM_DEMO_MAP.get("height"));
		 * tm.setLength(DoctorImportConst.PARAM_DEMO_MAP.get("length"));
		 * tm.setProductType
		 * (DoctorImportConst.PARAM_DEMO_MAP.get("productType"));
		 * tm.setSpec(DoctorImportConst.PARAM_DEMO_MAP.get("spec"));
		 * tm.setSkuNo(DoctorImportConst.PARAM_DEMO_MAP.get("skuNo"));
		 * tm.setSpecDefault
		 * (DoctorImportConst.PARAM_DEMO_MAP.get("specDefault"));
		 * tmList.add(tm);
		 */
		ExcelUtils.writeExcelByObject(tmList, response, tableName, headr,
				columns);

		return null;
	}

	@Override
	protected void preparedListData(Model model1,
			HttpServletRequest httpservletrequest) {
		List<HospitalInfoModel> hospitalList = hospitalInfoService.getAll();
		model1.addAttribute("hospitalList", hospitalList);
	}
}