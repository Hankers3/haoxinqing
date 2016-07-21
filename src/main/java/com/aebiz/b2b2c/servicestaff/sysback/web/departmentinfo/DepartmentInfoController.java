package com.aebiz.b2b2c.servicestaff.sysback.web.departmentinfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.servicestaff.departmentinfo.service.DepartmentInfoService;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoImportConst;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoQueryModel;
import com.aebiz.b2b2c.servicestaff.utils.ExcelUtils;

@Controller
@RequestMapping("/sysback/departmentinfo")
public class DepartmentInfoController extends
		BaseController<DepartmentInfoModel, DepartmentInfoQueryModel> {
	private DepartmentInfoService myService;

	@Autowired
	public void setMyService(DepartmentInfoService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public DepartmentInfoController() {
		super("servicestaff/sysback/departmentinfo", "DepartmentInfo",
				DepartmentInfoController.class);
	}

	/**
	 * 新增科室信息
	 */
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("m") DepartmentInfoModel m,
			HttpServletRequest request) {
		m.setCreateTime(DateFormatHelper.getNowTimeStr());
		return super.add(model, m, request);
	}

	/**
	 * 检查科室名是否存在
	 * 
	 * @param model
	 * @param departmentName
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	@ResponseBody
	public String checkName(Model model,
			@RequestParam("departmentName") String departmentName,
			@RequestParam("uuid") String uuid, HttpServletRequest request) {

		// 调用service 来检查科室名是否存在
		boolean flag = myService.checkDepartmentName(departmentName, uuid);
		if (flag) {
			return "true";
		}

		return "false";

	}

	/**
	 * 跳转到部门导入页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/departmentInfoImport", method = RequestMethod.GET)
	public String departmentInfoImport(Model model, HttpServletRequest request) {
		return "servicestaff/sysback/departmentinfo/DepartmentInfoImport";

	}

	/**
	 * 导出药品模板
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/departmentinfoTemplateExport", method = RequestMethod.GET)
	public String departmentinfoTemplateExport(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String tableName = "科室模板";
		String[] headr = new String[DepartmentInfoImportConst.PARAMKEYLIST
				.size()];
		String[] columns = new String[DepartmentInfoImportConst.PARAMKEYLIST
				.size()];
		for (int i = 0; i < DepartmentInfoImportConst.PARAMKEYLIST.size(); i++) {
			headr[i] = MessageHelper
					.getMessage(DepartmentInfoImportConst.PARAMKEYLIST.get(i));
		}

		List<Object> tmList = new ArrayList<Object>();
		ExcelUtils.writeExcelByObject(tmList, response, tableName, headr,
				columns);
		return null;
	}

	/**
	 * 上传Excel文件
	 * 
	 * @param myfiles
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importData", method = { RequestMethod.POST })
	public String importData(
			@RequestParam(value = "myExcelFile") MultipartFile[] files,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 调用上传excel的方法
		List<Object> list = myService.updloadExcel(files);
		StringBuffer erroMessage = new StringBuffer("");
		if (list != null && list.size() > 0) {
			int i = 1;
			for (Object object : list) {
				if (object != null) {
					try {

						DepartmentInfoModel pmm = (DepartmentInfoModel) object;
						if (!StringUtil.isEmpty(pmm.getDepartmentName())) {
							myService.create(pmm);
						}
					} catch (Exception e) {
						erroMessage.append("第" + i + "条数据问题;");
					}
					i++;
				}
			}
		}
		model.addAttribute("erroMessage", erroMessage);
		return "servicestaff/sysback/departmentinfo/DepartmentInfoList";
	}

}