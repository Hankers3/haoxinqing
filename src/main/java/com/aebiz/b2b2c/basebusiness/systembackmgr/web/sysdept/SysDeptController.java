package com.aebiz.b2b2c.basebusiness.systembackmgr.web.sysdept;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.SysDeptService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Controller
@RequestMapping("/sysback/sysdept")
public class SysDeptController extends
		BaseController<SysDeptModel, SysDeptQueryModel> {
	private SysDeptService myService;

	@Autowired
	public void setMyService(SysDeptService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public SysDeptController() {
		super("basebusiness/systembackmgr/sysdept", "SysDept",
				SysDeptController.class);
	}

	@Override
	protected boolean checkAdd(Model model, SysDeptModel m,
			HttpServletRequest request) {

		// 如果是检查ID是否重复
		checkNameField("", m.getDepartmentName());

		checkDeptNameField(m.getDepartmentName());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysdept/SysDeptAdd");
			return false;
		}

		return true;
	}

	@Override
	protected boolean checkUpdate(Model model, SysDeptModel m,
			HttpServletRequest request) {
		// 如果是检查ID是否重复
		checkNameField(m.getUuid(), m.getDepartmentName());

		// 检查部门名称
		checkDeptNameField(m.getDepartmentName());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysdept/SysDeptUpdate");
			return false;
		}

		return true;
	}

	/**
	 * *************************************************************************
	 * ****************************
	 * 
	 */
	/**
	 * 检查部门名称是否重复
	 * 
	 * @param uuid
	 * @param deptName
	 */
	private void checkNameField(String uuid, String deptName) {
		boolean isExist = myService.check(uuid, deptName);
		if (!isExist) {
			this.putErrorMsg("departmentName",
					MessageHelper.getMessage("sysdept.deptName.existed"));
		}
	}

	/**
	 * 检查部门名称字段
	 * 
	 * @param deptName
	 */
	private void checkDeptNameField(String deptName) {
		// 如果角色名称为空，则抛出错误
		if (StringUtil.isEmpty(deptName)) {
			this.putErrorMsg("departmentName",
					MessageHelper.getMessage("sysdept.deptName.empty"));
		}
	}
}