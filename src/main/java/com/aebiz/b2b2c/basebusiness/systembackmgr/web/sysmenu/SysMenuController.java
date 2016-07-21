package com.aebiz.b2b2c.basebusiness.systembackmgr.web.sysmenu;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service.SysMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/sysmenu")
public class SysMenuController extends
		BaseController<SysMenuModel, SysMenuQueryModel> {
	private SysMenuService myService;

	@Autowired
	public void setMyService(SysMenuService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public SysMenuController() {
		super("basebusiness/systembackmgr/sysmenu", "SysMenu",
				SysMenuController.class);
	}

	/**
	 * 菜单添加页面，需要将一级菜单发送到页面，以供选择
	 * 
	 * @param model
	 * @param request
	 */
	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {
		// 发送一级菜单到页面
		genLevelOneMenus(model, request);
	}

	/**
	 * 菜单编辑
	 * 
	 * 重写父类的菜单编辑，因为要对menu中的parentUuid进行拆分解析，发送到页面供选择
	 * 
	 * parentUuid的组合规则为一级菜单|二级菜单|三级菜单
	 * 
	 */
	@RequestMapping(value = { "/toMultiUpdate/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toMultiUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		SysMenuModel smm = (SysMenuModel) this.bs.getByUuid(uuid);

		if (smm != null) {

			// 循环查询到一级菜单，将一级菜单返回到页面,不管是有没有父菜单，一级分类都发送到页面供选择
			genLevelOneMenus(model, request);

			// 将二、三级菜单发送到页面
			List<SysMenuModel> smmList = myService.getAll();
			convertUpdateParentMenus(model, smmList, smm);
		}

		return "basebusiness/systembackmgr/sysmenu/SysMenuUpdate";
	}

	@Override
	protected boolean checkAdd(Model model, SysMenuModel m,
			HttpServletRequest request) {

		// 如果是检查ID是否重复
		checkNoExist("", m.getId());

		// 检查菜单名称
		checkName(m.getName());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

			// 循环查询到一级菜单，将一级菜单返回到页面,不管是有没有父菜单，一级分类都发送到页面供选择
			genLevelOneMenus(model, request);

			// 将二、三、四级惨淡发送到页面
			List<SysMenuModel> smmList = myService.getAll();
			convertAddParentMenus(model, smmList, m);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysmenu/SysMenuAdd");
			return false;
		}

		return true;
	}

	@Override
	protected boolean checkUpdate(Model model, SysMenuModel m,
			HttpServletRequest request) {

		// 如果是检查ID是否重复
		checkNoExist(m.getUuid(), m.getId());

		// 检查菜单名称
		checkName(m.getName());

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			// 循环查询到一级菜单，将一级菜单返回到页面,不管是有没有父菜单，一级分类都发送到页面供选择
			genLevelOneMenus(model, request);

			// 将二、三、四级菜单发送到页面
			List<SysMenuModel> smmList = myService.getAll();
			convertAddParentMenus(model, smmList, m);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/sysmenu/SysMenuUpdate");
			return false;
		}

		return true;
	}

	/**
	 * 前台通过ajax获得菜单的子菜单
	 * 
	 * 获得菜单的子菜单
	 * 
	 * @return
	 */
	@RequestMapping("/getSubMenus")
	public String getSubMenus(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("topMenuUuid") String parentUuidMenu)
			throws Exception {

		List<SysMenuModel> smmList = myService.getSubMenus(parentUuidMenu);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("smmList", smmList);

		int length = 0;
		if (smmList != null && smmList.size() > 0) {
			length = smmList.size();
		}

		jsonMap.put("length", length);

		response.setContentType("charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(jsonMap));

		return null;
	}

	/**
	 * 
	 * *************************************************************************
	 * *************************************************************************
	 * 
	 */

	/**
	 * 检查编号是否存在
	 * 
	 * @param menuId
	 */
	private void checkNoExist(String uuid, String menuId) {
		boolean isExist = myService.check(uuid, menuId);

		if (!isExist) {
			this.putErrorMsg("id",
					MessageHelper.getMessage("sysmenu.id.existed"));
		}
	}

	/**
	 * 检查菜单名称
	 * 
	 * @param menuName
	 */
	private void checkName(String menuName) {
		// 如果角色名称为空，则抛出错误
		if (StringUtil.isEmpty(menuName)) {
			this.putErrorMsg("name",
					MessageHelper.getMessage("sysmenu.name.invalid"));
		}
	}

	/**
	 * 构造一级参数发送到页面
	 * 
	 * @param smmList
	 * @param request
	 */
	private void genLevelOneMenus(Model model, HttpServletRequest request) {

		List<SysMenuModel> smmList = myService.getTopMenus();

		// 循环查询到一级菜单，将一级菜单返回到页面,不管是有没有父菜单，一级分类都发送到页面供选择
		model.addAttribute("topMenus", smmList);
	}

	private void convertUpdateParentMenus(Model model,
			List<SysMenuModel> smmList, SysMenuModel smm) {

		// 如果对象不为空，则拆分父uuid,现在拼法是一级|二级|三级|
		// String[] parentUuid = smm.getParentMenuUuid().split("\\|");

		String parentUuids = myService.getParentUuids(smm.getParentMenuUuid());

		String[] parentUuid = parentUuids.split("\\|");

		int length = parentUuid.length;
		if (length >= 1) {
			smm.setMenuUuidLevel1(parentUuid[0]);
		}

		// 循环查询到二级菜单，将二级菜单返回到页面
		if (length >= 2) {
			smm.setMenuUuidLevel2(parentUuid[1]);

			List<SysMenuModel> level2List = new ArrayList<SysMenuModel>();
			for (SysMenuModel menu : smmList) {

				// 如果是二级菜单，保存的值，则是一级菜单的值
				if (menu != null
						&& menu.getParentMenuUuid().equals(
								smm.getMenuUuidLevel1())) {

					level2List.add(menu);
				}
			}

			model.addAttribute("level2Menu", level2List);
		}

		// 循环查询到三级菜单，将三级菜单返回到页面
		if (length >= 3) {
			smm.setMenuUuidLevel3(parentUuid[2]);
			List<SysMenuModel> level3List = new ArrayList<SysMenuModel>();
			for (SysMenuModel menu : smmList) {

				// 如果是三级菜单，则保存的是一级|二级
				if (menu != null
						&& menu.getParentMenuUuid().equals(
								smm.getMenuUuidLevel2())) {

					level3List.add(menu);
				}
			}

			model.addAttribute("level3Menu", level3List);
		}

		model.addAttribute("m", smm);

	}

	private void convertAddParentMenus(Model model, List<SysMenuModel> smmList,
			SysMenuModel smm) {

		// 循环查询到二级菜单，将二级菜单返回到页面
		if (!StringUtil.isEmpty(smm.getMenuUuidLevel2())) {
			List<SysMenuModel> level2List = new ArrayList<SysMenuModel>();
			for (SysMenuModel menu : smmList) {

				// 如果是二级菜单，保存的值，则是一级菜单的值
				if (menu != null
						&& menu.getParentMenuUuid().equals(
								smm.getMenuUuidLevel1())) {

					level2List.add(menu);
				}
			}
			model.addAttribute("level2Menu", level2List);
		}

		// 循环查询到三级菜单，将三级菜单返回到页面
		if (!StringUtil.isEmpty(smm.getMenuUuidLevel3())) {
			List<SysMenuModel> level3List = new ArrayList<SysMenuModel>();
			for (SysMenuModel menu : smmList) {

				// 如果是三级菜单，则保存的是一级|二级
				if (menu != null
						&& menu.getParentMenuUuid().equals(
								smm.getMenuUuidLevel2())) {

					level3List.add(menu);
				}
			}
			model.addAttribute("level3Menu", level3List);
		}
	}
}