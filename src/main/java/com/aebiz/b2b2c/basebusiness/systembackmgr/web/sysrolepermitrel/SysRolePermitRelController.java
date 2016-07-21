package com.aebiz.b2b2c.basebusiness.systembackmgr.web.sysrolepermitrel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.service.SysRolePermitRelService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysrolepermitrel.vo.SysRolePermitRelQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;

@Controller
@RequestMapping("/sysback/sysrolepermitrel")
public class SysRolePermitRelController extends
		BaseController<SysRolePermitRelModel, SysRolePermitRelQueryModel> {
	private SysRolePermitRelService myService;

	@Autowired
	public void setMyService(SysRolePermitRelService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public SysRolePermitRelController() {
		super("basebusiness/systembackmgr/sysrolepermitrel",
				"SysRolePermitRel", SysRolePermitRelController.class);
	}
}