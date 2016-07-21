package com.aebiz.b2b2c.servicestaff.hospitalinfo.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalInfoConst {

	/**
	 * 医院导出模板列名
	 */
	public static final List<String> PARAMKEYLIST = new ArrayList<String>();
	static {
		// 医院名称
		PARAMKEYLIST.add("hospitalinfo.m.hospitalName");
		// 医院级别
		PARAMKEYLIST.add("hospitalinfo.m.hospitalLevel");
		// 省
		PARAMKEYLIST.add("hospitalinfo.m.province");
		// 市
		PARAMKEYLIST.add("hospitalinfo.m.city");
		// 区
		PARAMKEYLIST.add("hospitalinfo.m.region");
		
	}

	/**
	 * Excle列对应商品字段名称以及列表号
	 */
	public static final Map<String, String> PARAMMAP = new HashMap<String, String>();
	public static final Map<String, Integer> PARAM_NO_MAP = new HashMap<String, Integer>();
	public static final Map<String, String> PARAM_DEMO_MAP = new HashMap<String, String>();
	static {
		int i = 0;
		PARAMMAP.put("hospitalinfo.m.hospitalName", "hospitalName");
		PARAM_NO_MAP.put("hospitalName", ++i);
		PARAM_DEMO_MAP.put("hospitalName", "海淀医院");
		
		PARAMMAP.put("hospitalinfo.m.hospitalLevel", "hospitalLevel");
		PARAM_NO_MAP.put("hospitalLevel", ++i);
		PARAM_DEMO_MAP.put("hospitalLevel", "级别不详");
		
		PARAMMAP.put("hospitalinfo.m.province", "province");
		PARAM_NO_MAP.put("province", ++i);
		PARAM_DEMO_MAP.put("province", "北京市");
		
		PARAMMAP.put("hospitalinfo.m.city", "city");
		PARAM_NO_MAP.put("city", ++i);
		PARAM_DEMO_MAP.put("city", "北京市");
		
		PARAMMAP.put("hospitalinfo.m.region", "region");
		PARAM_NO_MAP.put("region", ++i);
		PARAM_DEMO_MAP.put("region", "海淀区");
		
		
	}

}
