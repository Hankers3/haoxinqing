package com.aebiz.b2b2c.product.productmain.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductImportConst {

	
	/**
	 * 商品导出模板列名
	 */
	public static final List<String> PARAMKEYLIST = new ArrayList<String>();
	static{
	    
		//药品分类
		PARAMKEYLIST.add("productImport.param.productType");
		//药品名称
		PARAMKEYLIST.add("productImport.param.productName");
		//药品英文名
		PARAMKEYLIST.add("productImport.param.productEnglishName");
		//常用治疗使用症
		PARAMKEYLIST.add("productImport.param.commonremedy");
		//作用机制
		PARAMKEYLIST.add("productImport.param.mechanismAction");
		//应该做的化验检查
		PARAMKEYLIST.add("productImport.param.laboratorExamination");
		//用法
		PARAMKEYLIST.add("productImport.param.direction");
		//用量
		PARAMKEYLIST.add("productImport.param.dosage");
		//药物导致不良反应的机制
		PARAMKEYLIST.add("productImport.param.drugReaction");
		//值得注意的不良反应
		PARAMKEYLIST.add("productImport.param.mildDrugReaction");
		//危及生命或危险的不良反应
		PARAMKEYLIST.add("productImport.param.dangerousDrugReaction");
		//禁用
		PARAMKEYLIST.add("productImport.param.forbidden");
		//注意事项
		PARAMKEYLIST.add("productImport.param.attention");
		//肾功能损害患者
                PARAMKEYLIST.add("productImport.param.renalFunctionDamage");
               //肝功能损害患者
                PARAMKEYLIST.add("productImport.param.liverFunctionDamage");
               //心功能损害患者
                PARAMKEYLIST.add("productImport.param.cardiacDysfunction");
                //老年人
                PARAMKEYLIST.add("productImport.param.oldPeople");
                //儿童和青少年
                PARAMKEYLIST.add("productImport.param.youngsters");
                //妊娠
                PARAMKEYLIST.add("productImport.param.conception");
                //哺乳
                PARAMKEYLIST.add("productImport.param.suckle");
               //药物相互作用
                PARAMKEYLIST.add("productImport.param.drugInteractio");
               //药物过量
                PARAMKEYLIST.add("productImport.param.overDose");
               //主要靶症状
                PARAMKEYLIST.add("productImport.param.targets");
               //长期使用
                PARAMKEYLIST.add("productImport.param.longRun");
                //成瘾性
                PARAMKEYLIST.add("productImport.param.addiction");
               //如何停药
                PARAMKEYLIST.add("productImport.param.stopMedicine");
               //药代动力学
                PARAMKEYLIST.add("productImport.param.pharmacokinetics");
                
               
                
             
		
		
	
		
	}
	
	
	
	
}
