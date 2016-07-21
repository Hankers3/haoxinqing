package com.aebiz.b2b2c.basebusiness.systembackmgr.web.sysmanager;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.SysDeptService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/sysmanager")
public class SysManagerController extends
		BaseController<SysManagerModel, SysManagerQueryModel> {
	private SysManagerService myService;

	@Autowired
	private SysDeptService deptService;

	@Autowired
	public void setMyService(SysManagerService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public SysManagerController() {
		super("basebusiness/systembackmgr/sysmanager", "SysManager",
				SysManagerController.class);
	}

	/**
	 * 菜单添加页面，需要将部门发送到页面，以供选择
	 * 
	 * 管理员需要选择所属部门
	 * 
	 * 添加的数据组织，重写父类的preparedAddData方法
	 * 
	 * @param model
	 * @param request
	 */
	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		sendDeptToList(request);
	}

	/**
	 * 角色查询需要根据所在部门进行查询，需要有部门的下拉菜单供选择，需要组织部门数据发送到页面
	 * 
	 * 列表数据组织，重写父类的preparedListData方法
	 * 
	 * @param model
	 * @param request
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		sendDeptToList(request);
	}

	/**
	 * 菜单编辑页面，需要将部门发送到页面，以供选择
	 * 
	 * 管理员需要选择所属部门
	 * 
	 * 添加的数据组织，重写父类的preparedUpdateData方法
	 * 
	 * @param model
	 * @param request
	 */
	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		sendDeptToList(request);
	}

	/**
	 * 管理员添加校验
	 * 
	 * 1.检验登录名(id)是否存在<br />
	 * 2.检验密码是否为空，且密码和确认密码是否一致<br />
	 * 3.检验所属部门是否为空<br />
	 * 
	 */
	@Override
	protected boolean checkAdd(Model model, SysManagerModel m,
			HttpServletRequest request) {

		// 检查管理员登录帐号是否存在
		checkManagerIdExist(m.getUuid(), m.getId());

		// 检查部门编号是否存在
		checkDeptUuid(m.getDepartmentUuid());

		// 检验密码是否为空，且密码和确认密码是否一致
		checkPwdEquals(m.getPwd(), m.getConfirmPwd());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

			sendDeptToList(request);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysmanager/SysManagerAdd");
			return false;
		}

		return true;
	}

	/**
	 * 管理员更新校验
	 * 
	 * 1.检验登录名(id)是否存在<br />
	 * 2.检验所属部门是否为空<br />
	 * 
	 */
	@Override
	protected boolean checkUpdate(Model model, SysManagerModel m,
			HttpServletRequest request) {

		// 检查管理员登录帐号是否存在
		checkManagerIdExist(m.getUuid(), m.getId());

		// 检查部门编号是否存在
		checkDeptUuid(m.getDepartmentUuid());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

			sendDeptToList(request);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysmanager/SysManagerUpdate");

			return false;
		}
		return true;
	}

	@RequestMapping(value = { "/pwdreset" })
	public String pwdreset(Model model, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("m") SysManagerModel m)
			throws Exception {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 如果密码为空
		if (StringUtil.isEmpty(m.getPwd())) {
			jsonMap.put("message", "pwd_empty");
		}

		// 如果密码不匹配
		if (StringUtil.isEmpty(m.getConfirmPwd())
				&& !m.getPwd().equals(m.getConfirmPwd())) {
			jsonMap.put("message", "no_equal");
		}

		// 得到对象
		SysManagerModel smm = myService.getByUuid(m.getUuid());
		smm.setPwd(m.getPwd());

		myService.update(smm);
		jsonMap.put("message", "success");

		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(jsonMap));

		return null;
	}

	/**
	 * 
	 * *************************************************************************
	 * **********************
	 * 
	 */
	private void checkManagerIdExist(String uuid, String managerId) {
		boolean isExist = myService.check(uuid, managerId);
		// 检验登录名(id)是否存在
		if (!isExist) {
			this.putErrorMsg("id",
					MessageHelper.getMessage("sysmanager.id.existed"));
		}
	}

	private void checkDeptUuid(String deptUuid) {
		// 检验所属部门是否为空
		if (StringUtil.isEmpty(deptUuid)) {
			this.putErrorMsg("departmentUuid",
					MessageHelper.getMessage("sysmanager.departmentUuid.empty"));
		}
	}

	private void checkPwdEquals(String pwd, String confirmPwd) {
		// 检验密码是否为空，且密码和确认密码是否一致
		if (StringUtil.isEmpty(pwd)) {
			this.putErrorMsg("pwd",
					MessageHelper.getMessage("sysmanager.pwd.empty"));
		} else {
			if (!pwd.equals(confirmPwd)) {
				this.putErrorMsg("pwd",
						MessageHelper.getMessage("sysmanager.pwd.notequal"));
			}
		}
	}

	/**
	 * 管理员发送部门信息到页面
	 * 
	 * @param request
	 */
	private void sendDeptToList(HttpServletRequest request) {
		List<SysDeptModel> deptList = deptService.getAll();
		request.setAttribute("returnDept", deptList);
	}
}