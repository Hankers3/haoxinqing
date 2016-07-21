package com.aebiz.b2b2c.basebusiness.systembackmgr.web.store.storeplatrole;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.service.StorePlatRoleService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Controller
@RequestMapping("/sysback/storeplatrole")
public class StorePlatRoleController extends
		BaseController<StorePlatRoleModel, StorePlatRoleQueryModel> {
	private StorePlatRoleService myService;

	@Autowired
	public void setMyService(StorePlatRoleService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public StorePlatRoleController() {
		super("basebusiness/systembackmgr/store/storeplatrole",
				"StorePlatRole", StorePlatRoleController.class);
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
	protected boolean checkAdd(Model model, StorePlatRoleModel m,
			HttpServletRequest request) {

		// 判断是否重复
		// 如果是检查ID是否重复
		checkNoExist("", m.getId());

		// 检查名称
		checkName(m.getName());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

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
	protected boolean checkUpdate(Model model, StorePlatRoleModel m,
			HttpServletRequest request) {

		// 判断是否重复
		// 如果是检查ID是否重复
		checkNoExist(m.getUuid(), m.getId());

		// 检查名称
		checkName(m.getName());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

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
	
	
	@RequestMapping("add")
	 public String add(Model model, StorePlatRoleModel m,
				HttpServletRequest request) 
	 {
		 String sss = myService.create(m);
		 return "redirect:/sysback/storeplatrole/toList";
	 }
	
}