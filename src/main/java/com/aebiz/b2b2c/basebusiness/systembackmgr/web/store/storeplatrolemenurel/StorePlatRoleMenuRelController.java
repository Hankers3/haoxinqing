package com.aebiz.b2b2c.basebusiness.systembackmgr.web.store.storeplatrolemenurel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service.StorePlatMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.service.StorePlatPermitService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatpermit.vo.StorePlatPermitQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.service.StorePlatRoleService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.service.StorePlatRoleMenuRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolemenurel.vo.StorePlatRoleMenuRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.service.StorePlatRolePermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolemenurel.vo.SysRoleMenuRelModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

@Controller
@RequestMapping("/sysback/storeplatrolemenurel")
public class StorePlatRoleMenuRelController
		extends
		BaseController<StorePlatRoleMenuRelModel, StorePlatRoleMenuRelQueryModel> {
	private StorePlatRoleMenuRelService myService;

	@Autowired
	private StorePlatRoleService storePlatRoleService;

	@Autowired
	private StorePlatMenuService storePlatMenuService;

	@Autowired
	private StorePlatPermitService storePlatPermitService;

	@Autowired
	private StorePlatRolePermitRelService rolePermitRelService;

	@Autowired
	public void setMyService(StorePlatRoleMenuRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public StorePlatRoleMenuRelController() {
		super("basebusiness/systembackmgr/store/storeplatrolemenurel",
				"StorePlatRoleMenuRel", StorePlatRoleMenuRelController.class);
	}

	/**
	 * 角色选择菜单和权限
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/toChoosePermit/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toChoosePermit(@PathVariable("uuid") String uuid,
			Model model, HttpServletRequest request) {

		StorePlatRoleModel m = storePlatRoleService.getByUuid(uuid);

		model.addAttribute("m", m);

		preparedChooseData(model, m, request);

		String success = request.getParameter("success");
		model.addAttribute("success", success);

		return "basebusiness/systembackmgr/store/storeplatrolemenurel/ChooseMenuList";
	}

	/**
	 * 保存部门和权限以及菜单的关联关系
	 * 
	 * @param deptUuid
	 * @param m
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/tosaverel/{roleUuid}" })
	public String toSaveRelation(@PathVariable("roleUuid") String roleUuid,
			@ModelAttribute("m") SysRoleMenuRelModel m, Model model,
			HttpServletRequest request) {

		String[] menusValues = m.getChoosemenus();

		String[] permitValues = m.getChoosepermits();

		// 保存部门和菜单以及权限的关联关系
		myService.saveRoleAndMenuPermitRel(roleUuid, menusValues, permitValues);

		return "redirect:/sysback/storeplatrolemenurel/toChoosePermit/"
				+ roleUuid + "?success=true";
	}

	/**
	 * *************************************************************************
	 */

	private void preparedChooseData(Model model, StorePlatRoleModel m,
			HttpServletRequest request) {
		// 1查询此角色已经关联的所有菜单
		List<StorePlatRoleMenuRelModel> allReledMenus = myService
				.getAllRelsByRoleUuid(m.getUuid());

		// 2. 查询所有菜单发送到页面和已关联的菜单
		StorePlatMenuQueryModel menuQueryModel = new StorePlatMenuQueryModel();
		menuQueryModel.getMapCondition().put("state", ConditionOpTypeEnum.EQ.getCode());
		List<StorePlatMenuModel> allMenuList = storePlatMenuService.getByCondition(menuQueryModel);

		// 3.组织菜单数据
		// 3.1组织一级菜单
		List<StorePlatMenuModel> topList = genTopMenus(allMenuList,
				allReledMenus);
		model.addAttribute("topList", topList);

		// 3.2构造一级菜单的二级菜单
		Map<StorePlatMenuModel, List<StorePlatMenuModel>> mapOneTwo = genOneToTwoMenus(
				topList, allMenuList, allReledMenus);
		model.addAttribute("mapOneTwo", mapOneTwo);

		// 3.2 组织三级菜单
		Map<StorePlatMenuModel, List<StorePlatMenuModel>> mapTwoThree = genTwoToThreeMenus(
				mapOneTwo, allMenuList, allReledMenus);
		model.addAttribute("mapTwoThree", mapTwoThree);

		// 3.3组织四级菜单
		Map<StorePlatMenuModel, List<StorePlatMenuModel>> mapThreeFour = genTwoToThreeMenus(
				mapTwoThree, allMenuList, allReledMenus);
		model.addAttribute("mapThreeFour", mapThreeFour);

		// 4.查询所有权限和查询已关联的所有权限
		StorePlatPermitQueryModel qm = new StorePlatPermitQueryModel();
		qm.getMapCondition().put("state", ConditionOpTypeEnum.EQ.getCode());
		List<StorePlatPermitModel> allPermitList = storePlatPermitService.getByCondition(qm);
		List<StorePlatRolePermitRelModel> permitRelList = rolePermitRelService
				.getAllPermitRelsByRoleUuid(m.getUuid());

		// 4.1组织三级菜单权限
		Map<StorePlatMenuModel, List<StorePlatPermitModel>> mapTwoThreePermit = genThreeToFourMenusPermits(
				mapTwoThree, allPermitList, permitRelList);

		model.addAttribute("mapTwoThreePermit", mapTwoThreePermit);

		// 4.2组织四级菜单权限
		Map<StorePlatMenuModel, List<StorePlatPermitModel>> mapThreeFourPermit = genThreeToFourMenusPermits(
				mapThreeFour, allPermitList, permitRelList);
		model.addAttribute("mapThreeFourPermit", mapThreeFourPermit);
	}

	/**
	 * 构造一级菜单
	 * 
	 * @param smmList
	 * @return
	 */
	private List<StorePlatMenuModel> genTopMenus(
			List<StorePlatMenuModel> allMenuList,
			List<StorePlatRoleMenuRelModel> allReledMenus) {
		List<StorePlatMenuModel> topList = new ArrayList<StorePlatMenuModel>();
		for (StorePlatMenuModel smm : allMenuList) {
			// 如果父ID为-1，则为一级分类smm
			if ("-1".equals(smm.getParentMenuUuid())) {

				// 通过匹配部门和菜单关联关系,确定是否已经关联
				for (StorePlatRoleMenuRelModel sdmrm : allReledMenus) {
					if (smm.getUuid().equals(sdmrm.getMenuUuid())) {
						smm.setChecked("1");
						break;
					}
				}
				topList.add(smm);
			}
		}

		return topList;
	}

	/**
	 * 构造二级级菜单
	 * 
	 * @param smmList
	 * @return
	 */
	private Map<StorePlatMenuModel, List<StorePlatMenuModel>> genOneToTwoMenus(
			List<StorePlatMenuModel> topMenus,
			List<StorePlatMenuModel> smmList,
			List<StorePlatRoleMenuRelModel> allReledMenus) {

		Map<StorePlatMenuModel, List<StorePlatMenuModel>> oneToTwoMap = new HashMap<StorePlatMenuModel, List<StorePlatMenuModel>>();
		for (StorePlatMenuModel topSmm : topMenus) {
			List<StorePlatMenuModel> Level2List = new ArrayList<StorePlatMenuModel>();
			for (StorePlatMenuModel level2Smm : smmList) {
				if (level2Smm.getParentMenuUuid().equals(topSmm.getUuid())) {

					// 通过匹配部门和菜单关联关系,确定是否已经关联
					for (StorePlatRoleMenuRelModel sdmrm : allReledMenus) {
						if (level2Smm.getUuid().equals(sdmrm.getMenuUuid())) {
							level2Smm.setChecked("1");
							break;
						}
					}

					Level2List.add(level2Smm);
				}
			}

			oneToTwoMap.put(topSmm, Level2List);
		}
		return oneToTwoMap;
	}

	// 3.2 组织三级、四级菜单
	private Map<StorePlatMenuModel, List<StorePlatMenuModel>> genTwoToThreeMenus(
			Map<StorePlatMenuModel, List<StorePlatMenuModel>> mapOneTwo,
			List<StorePlatMenuModel> allMenuList,
			List<StorePlatRoleMenuRelModel> allReledMenus) {

		Map<StorePlatMenuModel, List<StorePlatMenuModel>> mapTwoThree = new HashMap<StorePlatMenuModel, List<StorePlatMenuModel>>();
		for (List<StorePlatMenuModel> listTwo : mapOneTwo.values()) {
			for (StorePlatMenuModel smm2 : listTwo) {
				List<StorePlatMenuModel> tempList = new ArrayList<StorePlatMenuModel>();
				for (StorePlatMenuModel smm3 : allMenuList) {
					if (smm3.getParentMenuUuid().endsWith(smm2.getUuid())) {

						// 判断是否有此菜单
						for (StorePlatRoleMenuRelModel sdmr : allReledMenus) {
							if (sdmr.getMenuUuid().equals(smm3.getUuid())) {
								smm3.setChecked("1");
							}
						}

						// 判断三级菜单是否选中
						tempList.add(smm3);
					}
				}

				// 如果为空，则不发送消息到页面
				if (tempList != null && tempList.size() > 0) {
					mapTwoThree.put(smm2, tempList);
				}
			}
		}

		return mapTwoThree;
	}

	/**
	 * 组织菜单的权限
	 * 
	 * @param mapTwoThree
	 * @param allPermitList
	 * @param permitRelList
	 * @return
	 */
	private Map<StorePlatMenuModel, List<StorePlatPermitModel>> genThreeToFourMenusPermits(
			Map<StorePlatMenuModel, List<StorePlatMenuModel>> mapTwoThree,
			List<StorePlatPermitModel> allPermitList,
			List<StorePlatRolePermitRelModel> permitRelList) {

		Map<StorePlatMenuModel, List<StorePlatPermitModel>> mapTwoThreePermit = new HashMap<StorePlatMenuModel, List<StorePlatPermitModel>>();

		for (List<StorePlatMenuModel> listThree : mapTwoThree.values()) {
			for (StorePlatMenuModel smm3 : listThree) {

				List<StorePlatPermitModel> tempList = new ArrayList<StorePlatPermitModel>();
				for (StorePlatPermitModel spm3 : allPermitList) {

					// 权限的所属菜单以三级菜单的UUid结尾
					if (spm3.getBelongToMenuUuid().endsWith(smm3.getUuid())) {

						// 判断是否有此菜单
						for (StorePlatRolePermitRelModel sdprm : permitRelList) {
							if (sdprm.getPermitUuid().equals(spm3.getUuid())) {
								spm3.setChecked("1");
							}
						}

						// 判断三级菜单是否选中
						tempList.add(spm3);
					}
				}

				if (tempList != null && tempList.size() > 0) {
					mapTwoThreePermit.put(smm3, tempList);
				}
			}
		}

		return mapTwoThreePermit;
	}
}