package com.aebiz.b2b2c.basebusiness.systembackmgr.web.sysrole;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.SysDeptService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.service.SysManagerRoleRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.service.SysRoleService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/sysrole")
public class SysRoleController extends
		BaseController<SysRoleModel, SysRoleQueryModel> {
	private SysRoleService myService;

	@Autowired
	private SysDeptService sysDeptService;
	
	@Autowired
	private SysManagerRoleRelService sysManagerRoleRelService;

	@Autowired
	public void setMyService(SysRoleService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public SysRoleController() {
		super("basebusiness/systembackmgr/sysrole", "SysRole",
				SysRoleController.class);
	}

	/**
	 * 准备添加角色所需要的数据发送到页面,重写父类的preparedAddData方法
	 * 
	 * 添加角色时，需要选择角色所属的部门，角色的菜单和权限都会根据所在部门分配的菜单和权限来选择
	 * 
	 * 所以需要构造部门的列表发送到页面，供选择
	 * 
	 */
	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		// 获得所有的部门，发送到角色管理进行选择
		sendDeptsToList(model, request);
	}

	/**
	 * 准备角色列表所需要的数据发送到页面,重写父类的preparedListData方法
	 * 
	 * 在做角色查询时，可以通过所属部门进行查询，因此需要构造一个部门列表发送到页面，供查询时选择
	 * 
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		// 获得所有的部门，发送到角色管理进行选择
		sendDeptsToList(model, request);
	}

	/**
	 * 准备更新角色所需要的数据发送到页面,重写父类的preparedUpdateData方法
	 * 
	 * 更新角色时，需要选择角色所属的部门，角色的菜单和权限都会根据所在部门分配的菜单和权限来选择
	 * 
	 * 所以需要构造部门的列表发送到页面，供选择
	 * 
	 */
	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {
		// 获得所有的部门，发送到角色管理进行选择
		sendDeptsToList(model, request);

	}

	/**
	 * 在添加角色的时候，需要验证角色的属性是否符合规范
	 * 
	 * 重写弗父类的checkAdd方法
	 * 
	 * 1.检查角色编号是否重复，如果重复则提示 <br />
	 * 2.检查角色是否为空<br />
	 * 3.检查角色所属部门是否为空<br />
	 * 
	 */
	@Override
	protected boolean checkAdd(Model model, SysRoleModel m,
			HttpServletRequest request) {

		// 判断是否重复
		// 如果是检查ID是否重复
		checkNoExist("", m.getId());

		// 检查名称
		checkName(m.getName());

		// 检查部门编号
		checkDeptUuid(m.getDeptUuid());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

			// 获得所有的部门，发送到角色管理进行选择
			sendDeptsToList(model, request);
			model.addAttribute("m", m);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysrole/SysRoleAdd");
			return false;
		}

		return true;
	}

	/**
	 * 在更新角色的时候，需要验证角色的属性是否符合规范
	 * 
	 * 重写弗父类的checkAdd方法
	 * 
	 * 1.检查角色编号是否重复，如果重复则提示 <br />
	 * 2.检查角色是否为空<br />
	 * 3.检查角色所属部门是否为空<br />
	 * 
	 */
	@Override
	protected boolean checkUpdate(Model model, SysRoleModel m,
			HttpServletRequest request) {

		// 判断是否重复
		// 如果是检查ID是否重复
		checkNoExist(m.getUuid(), m.getId());

		// 检查名称
		checkName(m.getName());

		// 检查部门编号
		checkDeptUuid(m.getDeptUuid());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

			// 获得所有的部门，发送到角色管理进行选择
			sendDeptsToList(model, request);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysrole/SysRoleUpdate");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * *************************************************************************
	 * 
	 */

	/**
	 * 检查名称是否为空
	 * 
	 * @param name
	 */
	private void checkName(String name) {
		// 如果角色名称为空，则抛出错误
		if (StringUtil.isEmpty(name)) {
			this.putErrorMsg("name",
					MessageHelper.getMessage("sysrole.name.empty"));
		}
	}

	/**
	 * 检查部门编号是否为空
	 * 
	 * @param deptUuid
	 */
	private void checkDeptUuid(String deptUuid) {
		// 如果所属部门为空，则抛出错误
		if (StringUtil.isEmpty(deptUuid)) {
			this.putErrorMsg("deptUuid",
					MessageHelper.getMessage("sysrole.deptUuid.empty"));
		}
	}

	/**
	 * 检查角色编号是否重复
	 * 
	 * @param uuid
	 * @param roleId
	 */
	public void checkNoExist(String uuid, String roleId) {
		boolean isExist = myService.check(uuid, roleId);
		if (!isExist) {
			this.putErrorMsg("id",
					MessageHelper.getMessage("sysrole.id.existed"));
		}
	}

	/**
	 * 发送部门数据到页面进行选择查询
	 * 
	 * @param model
	 * @param request
	 */
	public void sendDeptsToList(Model model, HttpServletRequest request) {
		List<SysDeptModel> sdList = sysDeptService.getAll();
		model.addAttribute("sdList", sdList);
	}

	@RequestMapping(value = { "/deletes" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String deletes(
			@RequestParam("selectOne") List<String> needDeleteUuids,
			BaseWebModel wm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		boolean ifRel = checkIfRelSysManager(needDeleteUuids);
		if(ifRel){
			this.bs.deletes(needDeleteUuids);
		}else{
			jsonMap.put("rsp", Boolean.valueOf(false));
			jsonMap.put("msg", "used");
			out.print(JSON.toJSONString(jsonMap));
			return null;
		}

		jsonMap.put("rsp", Boolean.valueOf(true));
		out.print(JSON.toJSONString(jsonMap));

		return null;
	}
	
	/**
	 * 检查角色是否已关联管理员
	 * 
	 * @param uuid
	 * @param roleId
	 */
	public boolean checkIfRelSysManager(List<String> needDeleteUuids) {
		if(needDeleteUuids != null && needDeleteUuids.size()>0){
			List<String> list = null;
			for(String uuid : needDeleteUuids){
				list = sysManagerRoleRelService.getSysManagerRoleRelByRoleUuid(uuid);
				if(list!=null && list.size()>0){
					return false;
				}
			}
		}
		return true;
	}
}