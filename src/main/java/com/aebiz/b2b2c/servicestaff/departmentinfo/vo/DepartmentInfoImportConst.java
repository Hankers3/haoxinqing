package com.aebiz.b2b2c.servicestaff.departmentinfo.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentInfoImportConst {

	
	/**
	 * 商品导出模板列名
	 */
	public static final List<String> PARAMKEYLIST = new ArrayList<String>();
	static{
	    
		//科室ID
		PARAMKEYLIST.add("departmentinfoImport.param.departmentId");
		//科室名称
		PARAMKEYLIST.add("departmentinfoImport.param.departmentName");
		//科室电话
		PARAMKEYLIST.add("departmentinfoImport.param.mobile");
		//备注
		PARAMKEYLIST.add("departmentinfoImport.param.note");
		
	}
	
	
	
	
}
