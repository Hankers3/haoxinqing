package com.aebiz.b2b2c.websiteoperation.customerdisease.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDiseaseImportConst {

	
	/**
	 * 商品导出模板列名
	 */
	public static final List<String> PARAMKEYLIST = new ArrayList<String>();
	static{
		//疾病分类
		PARAMKEYLIST.add("customerdisease.m.diseaseCategory");
		//疾病名称
		PARAMKEYLIST.add("customerdisease.m.diseaseName");
		//定义和描述
		PARAMKEYLIST.add("customerdisease.m.diseaseDescription");
		//病因
		PARAMKEYLIST.add("customerdisease.m.pathogeny");
		//临床表现
		PARAMKEYLIST.add("customerdisease.m.clinicalFeature");
		//治疗
		PARAMKEYLIST.add("customerdisease.m.remedy");
		//发病与预后
		PARAMKEYLIST.add("customerdisease.m.morbidity");
		//预防
		PARAMKEYLIST.add("customerdisease.m.precaution");
		
		
	
		
	}
	
	
	
	
}
