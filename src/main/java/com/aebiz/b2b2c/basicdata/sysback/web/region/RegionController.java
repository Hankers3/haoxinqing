package com.aebiz.b2b2c.basicdata.sysback.web.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.basicdata.region.service.RegionService;
import com.aebiz.b2b2c.basicdata.region.vo.RegionModel;
import com.aebiz.b2b2c.basicdata.region.vo.RegionQueryModel;

@Controller
@RequestMapping("/sysback/region")
public class RegionController extends
		BaseController<RegionModel, RegionQueryModel> {
	private RegionService myService;

	@Autowired
	public void setMyService(RegionService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public RegionController() {
		super("basicdata/sysback/region", "Region", RegionController.class);
	}
}