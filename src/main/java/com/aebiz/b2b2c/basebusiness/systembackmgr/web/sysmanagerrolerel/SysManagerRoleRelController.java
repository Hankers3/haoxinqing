package com.aebiz.b2b2c.basebusiness.systembackmgr.web.sysmanagerrolerel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.service.SysManagerRoleRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanagerrolerel.vo.SysManagerRoleRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.service.SysRoleService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.vo.SysRoleModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

@Controller
@RequestMapping("/sysback/sysmanagerrolerel")
public class SysManagerRoleRelController extends
		BaseController<SysManagerRoleRelModel, SysManagerRoleRelQueryModel> {
	private SysManagerRoleRelService myService;

	@Autowired
	public void setMyService(SysManagerRoleRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	@Autowired
	private SysManagerService sysManagerService;

	@Autowired
	private SysRoleService sysRoleService;

	public SysManagerRoleRelController() {
		super("basebusiness/systembackmgr/sysmanagerrolerel",
				"SysManagerRoleRel", SysManagerRoleRelController.class);
	}

	/**
	 * 管理员选择角色
	 * 
	 * 一个管理员可以关联多个角色，当选择角色的时候，展示管理员所属机构的角色，进行选择
	 * 
	 * 
	 * @param managerId
	 *            管理员ID
	 */
	@RequestMapping(value = "/toRelatedRole/{managerId}", method = { RequestMethod.GET })
	public String toRelatedRole(@PathVariable("managerId") String managerId,
			Model model) {

		SysManagerModel smm = (SysManagerModel) sysManagerService
				.getByUuid(managerId);

		// 发送部门下的所有角色和此管理员已关联的角色到页面
		preparedManagerRoleData(smm, model);

		model.addAttribute("m", smm);

		return "basebusiness/systembackmgr/sysmanagerrolerel/SysManagerRoleRel";
	}

	@RequestMapping(value = "/saveRelatedRole/{managerId}", method = { RequestMethod.GET })
	public String saveRelatedRole(@PathVariable("managerId") String managerId,
			@ModelAttribute("m") SysManagerModel smm, Model model) {

		// 获得选择的Uuid
		String[] selected = smm.getRoleUuids();

		// 调用service关联
		myService.saveRelatedRole(managerId, selected);

		return "basebusiness/systembackmgr/sysmanagerrolerel/SysManagerRoleRelSuccess";
	}

	/**
	 * 
	 * *************************************************************************
	 * 
	 */

	/**
	 * 组织管理员和角色的数据
	 * 
	 * 1.管理员部门下，已选的角色 <br />
	 * 2.管理员部门下，未选择的角色 <br />
	 * 
	 * @param managerId
	 * @param model
	 */
	private void preparedManagerRoleData(SysManagerModel smm, Model model) {
		String deptUuid = smm.getDepartmentUuid();

		// 1.获得部门下所有的角色
		List<SysRoleModel> allRoles = sysRoleService
				.getSysRoleModelsByDeptUuid(deptUuid);
		//系统角色
		SysRoleModel roleModel =  sysRoleService.getByUuid(SysRoleModel.SYSROEL);
		allRoles.add(roleModel);
		
		// 2.获得管理员已经关联的角色
		List<SysRoleModel> reledRoles = sysRoleService
				.getSysRoleModelByManagerUuid(smm.getUuid());

		for (SysRoleModel srm : allRoles) {
			for (SysRoleModel reledSrm : reledRoles) {
				if(reledSrm !=null && srm !=null){
					// 如果已经有管理，则从列表中删除
					if (srm.getUuid().equals(reledSrm.getUuid())) {
						srm.setChecked("1");
					}
				}
				
			}
		}

		// 3.发送到页面
		model.addAttribute("allRoles", allRoles);
	}
}