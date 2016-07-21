package com.aebiz.b2b2c.websiteoperation.symptomstructure.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymptomStructureImportConst {

	
	/**
	 * 症状导出模板列名
	 */
	public static final List<String> PARAMKEYLIST = new ArrayList<String>();
	static{
		//一级分类
		PARAMKEYLIST.add("symptomstructure.m.firstClassification");
		//二级分类
		PARAMKEYLIST.add("symptomstructure.m.secondaryClassification");
		//症状
		PARAMKEYLIST.add("symptomstructure.m.symptom");
		//细分症状
		PARAMKEYLIST.add("symptomstructure.m.breakdownSymptom");
		
		
	}
	
	
	
	
}
