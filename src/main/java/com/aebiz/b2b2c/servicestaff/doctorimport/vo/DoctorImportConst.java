package com.aebiz.b2b2c.servicestaff.doctorimport.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorImportConst {

	/**
	 * 商品导出模板列名
	 */
	public static final List<String> PARAMKEYLIST = new ArrayList<String>();
	static {
		// 医院官方名称
		PARAMKEYLIST.add("doctorimport.m.hospitalUuid");
		// 科室官方名称
		PARAMKEYLIST.add("doctorimport.m.departmentUuid");
		// 医生姓名
		PARAMKEYLIST.add("doctorimport.m.realName");
		// 手机号
		PARAMKEYLIST.add("doctorimport.m.mobile");
		// 邮箱
		PARAMKEYLIST.add("doctorimport.m.email");
		// 职称
		PARAMKEYLIST.add("doctorimport.m.professional");
		// 职务
		// PARAMKEYLIST.add("doctorimport.m.doctorClassify");
		// 个人简介
		// PARAMKEYLIST.add("doctorimport.m.synopsis");
	}

	/**
	 * Excle列对应商品字段名称以及列表号
	 */
	public static final Map<String, String> PARAMMAP = new HashMap<String, String>();
	public static final Map<String, Integer> PARAM_NO_MAP = new HashMap<String, Integer>();
	public static final Map<String, String> PARAM_DEMO_MAP = new HashMap<String, String>();
	static {
		int i = 0;
		PARAMMAP.put("doctorimport.m.realName", "realName");
		PARAM_NO_MAP.put("realName", ++i);
		PARAM_DEMO_MAP.put("realName", "孙忠瑞");

		PARAMMAP.put("doctorimport.m.mobile", "mobile");
		PARAM_NO_MAP.put("mobile", ++i);
		PARAM_DEMO_MAP.put("mobile", "15712899137");

		PARAMMAP.put("doctorimport.m.email", "email");
		PARAM_NO_MAP.put("email", ++i);
		PARAM_DEMO_MAP.put("email", "962141347@qq.com");

		PARAMMAP.put("doctorimport.m.professional", "professional");
		PARAM_NO_MAP.put("professional", ++i);
		PARAM_DEMO_MAP.put("professional", "院长");

		PARAMMAP.put("doctorimport.m.doctorClassify", "doctorClassify");
		PARAM_NO_MAP.put("doctorClassify", ++i);
		PARAM_DEMO_MAP.put("doctorClassify", "一切职务");

		PARAMMAP.put("doctorimport.m.hospitalUuid", "hospitalUuid");
		PARAM_NO_MAP.put("hospitalUuid", ++i);
		PARAM_DEMO_MAP.put("hospitalUuid", "universe");

		PARAMMAP.put("doctorimport.m.departmentUuid", "departmentUuid");
		PARAM_NO_MAP.put("departmentUuid", ++i);
		PARAM_DEMO_MAP.put("departmentUuid", "所有科室");

		PARAMMAP.put("doctorimport.m.synopsis", "synopsis");
		PARAM_NO_MAP.put("synopsis", ++i);
		PARAM_DEMO_MAP.put("synopsis", "全知全能，就是神!");
	}

}
