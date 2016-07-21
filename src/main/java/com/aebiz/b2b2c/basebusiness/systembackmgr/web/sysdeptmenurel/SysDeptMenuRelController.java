package com.aebiz.b2b2c.basebusiness.systembackmgr.web.sysdeptmenurel;

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

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.service.SysDeptService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdept.vo.SysDeptModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.service.SysDeptMenuRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptmenurel.vo.SysDeptMenuRelQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.service.SysDeptPermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysdeptpermitrel.vo.SysDeptPermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.service.SysMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmenu.vo.SysMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.service.SysPermitService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.syspermit.vo.SysPermitModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

@Controller
@RequestMapping("/sysback/sysdeptmenurel")
public class SysDeptMenuRelController extends
		BaseController<SysDeptMenuRelModel, SysDeptMenuRelQueryModel> {

	private SysDeptMenuRelService myService;

	@Autowired
	private SysDeptService sdService;

	@Autowired
	private SysMenuService sysMenuService;

	@Autowired
	private SysPermitService sysPermitService;

	@Autowired
	private SysDeptPermitRelService sysDeptPermitRelService;

	@Autowired
	public void setMyService(SysDeptMenuRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public SysDeptMenuRelController() {
		super("basebusiness/systembackmgr/sysdeptmenurel", "SysDeptMenuRel",
				SysDeptMenuRelController.class);
	}

	/**
	 * 部门选择菜单和权限
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/toChoosePermit/{uuid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toChoosePermit(@PathVariable("uuid") String uuid,
			Model model, HttpServletRequest request) {
		SysDeptModel m = sdService.getByUuid(uuid);

		model.addAttribute("m", m);

		preparedChooseData(model, uuid, request);

		return "basebusiness/systembackmgr/sysdeptmenurel/ChooseMenuList";
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
	@RequestMapping(value = { "/tosaverel/{deptUuid}" })
	public String toSaveRelation(@PathVariable("deptUuid") String deptUuid,
			@ModelAttribute("m") SysDeptMenuRelModel m, Model model,
			HttpServletRequest request) {

		String[] menusValues = m.getChoosemenus();

		String[] permitValues = m.getChoosepermits();

		// 保存部门和菜单以及权限的关联关系
		myService.saveDeptAndMenuPermitRel(deptUuid, menusValues, permitValues);

		// return "redirect:/sysdeptmenurel/toChoosePermit/" + deptUuid;
		return "basebusiness/systembackmgr/sysdeptmenurel/ChooseMenuSuccess";
	}

	/**
	 * *************************************************************************
	 */

	private void preparedChooseData(Model model, String deptUuid,
			HttpServletRequest request) {
		// 1查询此部门已经关联的所有菜单
		List<SysDeptMenuRelModel> allReledMenus = myService
				.getAllRelsByDeptUuid(deptUuid);

		// 2. 查询所有菜单发送到页面和已关联的菜单
		List<SysMenuModel> allMenuList = sysMenuService.getAll();

		// 3.组织菜单数据
		// 3.1组织一级菜单
		List<SysMenuModel> topList = genTopMenus(allMenuList, allReledMenus);
		model.addAttribute("topList", topList);

		// 3.2构造一级菜单的二级菜单
		Map<SysMenuModel, List<SysMenuModel>> mapOneTwo = genOneToTwoMenus(
				topList, allMenuList, allReledMenus);
		model.addAttribute("mapOneTwo", mapOneTwo);

		// 3.2 组织三级菜单
		Map<SysMenuModel, List<SysMenuModel>> mapTwoThree = genTwoToThreeMenus(
				mapOneTwo, allMenuList, allReledMenus);
		model.addAttribute("mapTwoThree", mapTwoThree);

		// 3.3组织四级菜单
		Map<SysMenuModel, List<SysMenuModel>> mapThreeFour = genTwoToThreeMenus(
				mapTwoThree, allMenuList, allReledMenus);
		model.addAttribute("mapThreeFour", mapThreeFour);

		// 4.查询所有权限
		List<SysPermitModel> allPermitList = sysPermitService.getAll();
		List<SysDeptPermitRelModel> permitRelList = sysDeptPermitRelService
				.getAllPermitRelsByDeptUuid(deptUuid);

		// 4.1组织三级菜单权限
		Map<SysMenuModel, List<SysPermitModel>> mapTwoThreePermit = genThreeToFourMenusPermits(
				mapTwoThree, allPermitList, permitRelList);

		model.addAttribute("mapTwoThreePermit", mapTwoThreePermit);

		// 4.2组织四级菜单权限
		Map<SysMenuModel, List<SysPermitModel>> mapThreeFourPermit = genThreeToFourMenusPermits(
				mapThreeFour, allPermitList, permitRelList);
		model.addAttribute("mapThreeFourPermit", mapThreeFourPermit);
	}

	/**
	 * 构造一级菜单
	 * 
	 * @param smmList
	 * @return
	 */
	private List<SysMenuModel> genTopMenus(List<SysMenuModel> allMenuList,
			List<SysDeptMenuRelModel> allReledMenus) {
		List<SysMenuModel> topList = new ArrayList<SysMenuModel>();
		for (SysMenuModel smm : allMenuList) {
			// 如果父ID为-1，则为一级分类smm
			if ("-1".equals(smm.getParentMenuUuid())) {

				// 通过匹配部门和菜单关联关系,确定是否已经关联
				for (SysDeptMenuRelModel sdmrm : allReledMenus) {
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
	private Map<SysMenuModel, List<SysMenuModel>> genOneToTwoMenus(
			List<SysMenuModel> topMenus, List<SysMenuModel> smmList,
			List<SysDeptMenuRelModel> allReledMenus) {

		Map<SysMenuModel, List<SysMenuModel>> oneToTwoMap = new HashMap<SysMenuModel, List<SysMenuModel>>();
		for (SysMenuModel topSmm : topMenus) {
			List<SysMenuModel> Level2List = new ArrayList<SysMenuModel>();
			for (SysMenuModel level2Smm : smmList) {
				if (level2Smm.getParentMenuUuid().equals(topSmm.getUuid())) {

					// 通过匹配部门和菜单关联关系,确定是否已经关联
					for (SysDeptMenuRelModel sdmrm : allReledMenus) {
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
	private Map<SysMenuModel, List<SysMenuModel>> genTwoToThreeMenus(
			Map<SysMenuModel, List<SysMenuModel>> mapOneTwo,
			List<SysMenuModel> allMenuList,
			List<SysDeptMenuRelModel> allReledMenus) {

		Map<SysMenuModel, List<SysMenuModel>> mapTwoThree = new HashMap<SysMenuModel, List<SysMenuModel>>();
		for (List<SysMenuModel> listTwo : mapOneTwo.values()) {
			for (SysMenuModel smm2 : listTwo) {
				List<SysMenuModel> tempList = new ArrayList<SysMenuModel>();
				for (SysMenuModel smm3 : allMenuList) {
					if (smm3.getParentMenuUuid().endsWith(smm2.getUuid())) {

						// 判断是否有此菜单
						for (SysDeptMenuRelModel sdmr : allReledMenus) {
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
	private Map<SysMenuModel, List<SysPermitModel>> genThreeToFourMenusPermits(
			Map<SysMenuModel, List<SysMenuModel>> mapTwoThree,
			List<SysPermitModel> allPermitList,
			List<SysDeptPermitRelModel> permitRelList) {

		Map<SysMenuModel, List<SysPermitModel>> mapTwoThreePermit = new HashMap<SysMenuModel, List<SysPermitModel>>();

		for (List<SysMenuModel> listThree : mapTwoThree.values()) {
			for (SysMenuModel smm3 : listThree) {

				List<SysPermitModel> tempList = new ArrayList<SysPermitModel>();
				for (SysPermitModel spm3 : allPermitList) {

					// 权限的所属菜单以三级菜单的UUid结尾
					if (spm3.getBelongToMenuUuid().endsWith(smm3.getUuid())) {

						// 判断是否有此菜单
						for (SysDeptPermitRelModel sdprm : permitRelList) {
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