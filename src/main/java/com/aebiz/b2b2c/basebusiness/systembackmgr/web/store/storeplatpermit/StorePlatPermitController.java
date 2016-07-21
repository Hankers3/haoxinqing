package com.aebiz.b2b2c.basebusiness.systembackmgr.web.store.storeplatpermit;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service.StorePlatMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.service.StorePlatPermitService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitEnum;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;

@Controller
@RequestMapping("/sysback/storeplatpermit")
public class StorePlatPermitController extends
		BaseController<StorePlatPermitModel, StorePlatPermitQueryModel> {
	private StorePlatPermitService myService;

	@Autowired
	private StorePlatMenuService storePlatMenuService;

	@Autowired
	public void setMyService(StorePlatPermitService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public StorePlatPermitController() {
		super("basebusiness/systembackmgr/store/storeplatpermit",
				"StorePlatPermit", StorePlatPermitController.class);
	}

	/**
	 * 菜单添加页面，需要将一级菜单发送到页面，以供选择
	 * 
	 * @param model
	 * @param request
	 */
	@Override
	protected void preparedAddData(Model model, HttpServletRequest request) {

		// 发送菜单类型到页面
		sendPermitTypeToPage(model);

		// 发送菜单到页面
		sendMenusToList(model);
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
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		StorePlatPermitModel smm = (StorePlatPermitModel) this.bs
				.getByUuid(uuid);

		// 发送一级菜单到列表
		sendMenusToList(model);

		// 发送菜单类型到页面
		sendPermitTypeToPage(model);

		StorePlatPermitModel resmm = preparedSendUpdateData(smm, request);

		model.addAttribute("m", resmm);

		return "basebusiness/systembackmgr/store/storeplatpermit/StorePlatPermitUpdate";
	}

	@Override
	protected boolean checkAdd(Model model, StorePlatPermitModel m,
			HttpServletRequest request) {

		// 检查name的属性
		checkNameFiled(m.getName());

		// 检查表达式的属性
		checkExpressionField(m.getExpression());

		// 判断菜单是不是最后一级
		checkLastLevel(m, request);

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

			// 发送菜单类型到页面
			sendPermitTypeToPage(model);

			// 发送一级菜单到列表
			sendMenusToList(model);

			// 发送其他几级菜单到列表
			preparedAddSendUpdateData(model, m);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/store/storeplatpermit/StorePlatPermitAdd");
			return false;
		}

		return true;
	}

	@Override
	protected boolean checkUpdate(Model model, StorePlatPermitModel m,
			HttpServletRequest request) {

		// 检查name的属性
		checkNameFiled(m.getName());

		// 检查表达式的属性
		checkExpressionField(m.getExpression());

		// 判断菜单是不是最后一级
		checkLastLevel(m, request);

		// 跳转到角色添加页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {

			// 发送菜单类型到页面
			sendPermitTypeToPage(model);

			// 发送一级菜单到列表
			sendMenusToList(model);

			// 发送其他几级菜单到列表
			preparedAddSendUpdateData(model, m);

			request.setAttribute(ERROR_BACK_URL,
					"basebusiness/systembackmgr/store/storeplatpermit/StorePlatPermitUpdate");
			return false;
		}

		return true;
	}

	/**
	 * ****************************************************
	 * 
	 */

	/**
	 * 检查name的属性
	 * 
	 * @param name
	 */
	private void checkNameFiled(String name) {
		if (StringUtil.isEmpty(name)) {
			this.putErrorMsg("name",
					MessageHelper.getMessage("syspermit.name.empty"));
		}
	}

	/**
	 * 检查表达式的属性
	 * 
	 * @param name
	 */
	private void checkExpressionField(String expression) {
		if (StringUtil.isEmpty(expression)) {
			this.putErrorMsg("expression",
					MessageHelper.getMessage("syspermit.expression.empty"));
		}
	}

	/**
	 * 检查菜单是不是最后一级
	 * 
	 * 如果是菜单权限，则必须要选择到最后一级
	 * 
	 * @param m
	 * @param request
	 */
	private void checkLastLevel(StorePlatPermitModel m,
			HttpServletRequest request) {
		// 判断所选菜单是否最后一级
		if (StringUtil.isEmpty(m.getMenuUuidLevel3())
				&& StringUtil.isEmpty(m.getMenuUuidLevel4())) {
			this.putErrorMsg("menuUuidLevel1",
					MessageHelper.getMessage("syspermit.menun.nolast"));

		} else if (StringUtil.isEmpty(m.getMenuUuidLevel4())) {
			// 如果第四级菜单为空，判断第三级下面是否有子
			List<StorePlatMenuModel> smmList = storePlatMenuService
					.getSubMenus(m.getMenuUuidLevel3());
			if (smmList != null && smmList.size() > 0) {
				this.putErrorMsg("menuUuidLevel1",
						MessageHelper.getMessage("syspermit.menun.nolast"));
			}
		}
	}

	/**
	 * 发送一级菜单到页面
	 * 
	 * @param request
	 */
	private void sendMenusToList(Model model) {
		List<StorePlatMenuModel> returnMenus = storePlatMenuService
				.getTopMenus();
		model.addAttribute("topMenus", returnMenus);
	}

	public void sendPermitTypeToPage(Model model) {
		List<DataTablesPageParam> tagList = new ArrayList<DataTablesPageParam>();

		for (SysPermitEnum spe : SysPermitEnum.values()) {
			DataTablesPageParam dpp = new DataTablesPageParam();

			dpp.setName(spe.getValue());
			dpp.setValue(spe.getName());

			tagList.add(dpp);
		}

		model.addAttribute("permitTypes", tagList);
	}

	private StorePlatPermitModel preparedSendUpdateData(
			StorePlatPermitModel smm, HttpServletRequest request) {
		if (smm != null) {

			List<StorePlatMenuModel> smmList = storePlatMenuService.getAll();

			// 循环查询到一级菜单，将一级菜单返回到页面,不管是有没有父菜单，一级分类都发送到页面供选择
			List<StorePlatMenuModel> level1List = new ArrayList<StorePlatMenuModel>();
			for (StorePlatMenuModel menu1 : smmList) {
				if (menu1 != null
						&& ("-1".equals(menu1.getParentMenuUuid()) || StringUtil
								.isEmpty(menu1.getParentMenuUuid()))) {

					level1List.add(menu1);
				}
			}
			request.setAttribute("level1Menu", level1List);

			// 如果对象不为空，则拆分父uuid,现在拼法是一级|二级|三级|三级
			// String[] parentUuid = smm.getBelongToMenuUuid().split("\\|");

			String parentUuids = storePlatMenuService.getParentUuids(smm
					.getBelongToMenuUuid());
			String[] parentUuid = parentUuids.split("\\|");

			int length = parentUuid.length;
			if (length >= 1) {
				smm.setMenuUuidLevel1(parentUuid[0]);
			}

			// 循环查询到二级菜单，将二级菜单返回到页面
			if (length >= 2) {
				smm.setMenuUuidLevel2(parentUuid[1]);

				List<StorePlatMenuModel> level2List = new ArrayList<StorePlatMenuModel>();
				for (StorePlatMenuModel menu : smmList) {

					// 如果是二级菜单，保存的值，则是一级菜单的值
					if (menu != null
							&& menu.getParentMenuUuid().equals(
									smm.getMenuUuidLevel1())) {

						level2List.add(menu);
					}
				}

				request.setAttribute("level2Menu", level2List);
			}

			// 循环查询到三级菜单，将三级菜单返回到页面
			if (length >= 3) {
				smm.setMenuUuidLevel3(parentUuid[2]);
				List<StorePlatMenuModel> level3List = new ArrayList<StorePlatMenuModel>();
				for (StorePlatMenuModel menu : smmList) {

					// 如果是三级菜单，则保存的是一级|二级
					if (menu != null
							&& menu.getParentMenuUuid().equals(
									smm.getMenuUuidLevel2())) {

						level3List.add(menu);
					}
				}

				request.setAttribute("level3Menu", level3List);
			}

			// 循环查询到三级菜单，将三级菜单返回到页面
			if (length >= 4) {
				smm.setMenuUuidLevel4(parentUuid[3]);
				List<StorePlatMenuModel> level4List = new ArrayList<StorePlatMenuModel>();
				for (StorePlatMenuModel menu : smmList) {

					// 如果是三级菜单，则保存的是一级|二级
					if (menu != null
							&& menu.getParentMenuUuid().equals(
									smm.getMenuUuidLevel3())) {

						level4List.add(menu);
					}
				}

				request.setAttribute("level4Menu", level4List);
			}
		}

		return smm;
	}

	/**
	 * 保存错误，发送菜单信息到页面
	 * 
	 * @param smm
	 * @param request
	 * @return
	 */
	private void preparedAddSendUpdateData(Model model, StorePlatPermitModel smm) {
		if (smm != null) {

			List<StorePlatMenuModel> smmList = storePlatMenuService.getAll();

			// 循环查询到二级菜单，将二级菜单返回到页面
			if (!StringUtil.isEmpty(smm.getMenuUuidLevel2())) {
				List<StorePlatMenuModel> level2List = new ArrayList<StorePlatMenuModel>();
				for (StorePlatMenuModel menu : smmList) {

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
				List<StorePlatMenuModel> level3List = new ArrayList<StorePlatMenuModel>();
				for (StorePlatMenuModel menu : smmList) {

					// 如果是三级菜单，则保存的是一级|二级
					if (menu != null
							&& menu.getParentMenuUuid().equals(
									smm.getMenuUuidLevel2())) {

						level3List.add(menu);
					}
				}

				model.addAttribute("level3Menu", level3List);
			}

			// 循环查询到三级菜单，将三级菜单返回到页面
			if (!StringUtil.isEmpty(smm.getMenuUuidLevel4())) {
				List<StorePlatMenuModel> level4List = new ArrayList<StorePlatMenuModel>();
				for (StorePlatMenuModel menu : smmList) {

					// 如果是三级菜单，则保存的是一级|二级
					if (menu != null
							&& menu.getParentMenuUuid().equals(
									smm.getMenuUuidLevel3())) {

						level4List.add(menu);
					}
				}
				model.addAttribute("level4Menu", level4List);
			}
		}
	}
}