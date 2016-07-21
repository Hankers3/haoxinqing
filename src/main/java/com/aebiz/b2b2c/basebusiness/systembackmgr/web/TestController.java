package com.aebiz.b2b2c.basebusiness.systembackmgr.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.service.StorePlatMenuService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatmenu.vo.StorePlatMenuQueryModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrole.dao.SysRoleDAO;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

@Controller("businesstest")
@RequestMapping("/sysroletest")
public class TestController extends
		BaseController<StorePlatMenuModel, StorePlatMenuQueryModel> {
	private StorePlatMenuService myService;

	@Autowired
	private SysRoleDAO sysRoleDao;

	@Autowired
	public void setMyService(StorePlatMenuService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public TestController() {
		super("basebusiness/systembackmgr/store/storeplatmenu",
				"StorePlatMenu", TestController.class);
	}

	@RequestMapping(value = { "/test" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toMultiUpdate(HttpServletRequest request) {

		System.out.println("sysRoleDao=====" + sysRoleDao);

		sysRoleDao.setDataDeleteFlag("111", 1);

		return "basebusiness/systembackmgr/store/storeplatmenu/StorePlatMenuUpdate";
	}

}