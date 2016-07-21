package com.aebiz.b2b2c.basebusiness.systembackmgr.web.store.storeplatrolepermitrel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.service.StorePlatRolePermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrolepermitrel.vo.StorePlatRolePermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

@Controller
@RequestMapping("/sysback/storeplatrolepermitrel")
public class StorePlatRolePermitRelController
		extends
		BaseController<StorePlatRolePermitRelModel, StorePlatRolePermitRelQueryModel> {
	private StorePlatRolePermitRelService myService;

	@Autowired
	public void setMyService(StorePlatRolePermitRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public StorePlatRolePermitRelController() {
		super("basebusiness/systembackmgr/store/storeplatrolepermitrel",
				"StorePlatRolePermitRel",
				StorePlatRolePermitRelController.class);
	}
}