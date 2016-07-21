package com.aebiz.b2b2c.product.productmain.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;

/**
 * 商品导入临时 信息表
 * @author wenchao
 *
 */
@Component
public class ProductTempModel extends BaseModel {
	
	public static final String STOREUUID_DEFAULT = "4f41fb4edb4b485cac3b391112a9c2cf";
	
	
	private static WebsiteOperateInteractive websiteService;
	
	@Autowired
	public void WebsiteOperateInteractive(WebsiteOperateInteractive ws) {
		websiteService = ws;
	}
	
	/****************ProductMain*********************/
	/* 商品名称 */
        private String productName;
        /* 英文名称 */
        private String productEnglishName;
        /* 药品类别 */
        private String productType;

        /* 常用治疗适用症状 */
        private String commonremedy;

        /* 作用机制 */
        private String mechanismAction;

        /* 应该做的化验检查 */
        private String laboratorExamination;
        
        /* 用法 */
        private String direction;
        
        /* 用量 */
        private String dosage;

        /* 药物导致的不良反应机制 */
        private String drugReaction;

        /* 值得注意的不良反应机制 */
        private String mildDrugReaction;

        /* 危险的不良反应机制 */
        private String dangerousDrugReaction;

        /* 禁用 */
        private String forbidden;

        /* 注意事项 */
        private String attention;

        /* 药物相互作用 */
        private String drugInteractio;
        /* 药物过量 */
        private String overDose;

        /* 主治症状 */
        private String targets;

        /* 长期使用 */
        private String longRun;

        /* 成瘾性 */
        private String addiction;

        /* 如何停药 */
        private String stopMedicine;

        /* 药代动力学 */
        private String pharmacokinetics;

        
        
        /* 肾功能损害患者 */
        private String renalFunctionDamage;  
        
        /* 肝功能损害患者 */
        private String liverFunctionDamage;  
        
        /* 心功能损害患者 */
        private String cardiacDysfunction;  
        
        /* 老年人 */
        private String  oldPeople;
        
        /* 儿童和青少年 */
        private String  youngsters;
        
        /* 妊娠*/
        private String conception;
        
        /* 哺乳*/
        private String suckle;
        
        

        public String getConception() {
            return conception;
        }

        public void setConception(String conception) {
            this.conception = conception;
        }

        public String getSuckle() {
            return suckle;
        }

        public void setSuckle(String suckle) {
            this.suckle = suckle;
        }
        

        public String getOldPeople() {
            return oldPeople;
        }

        public void setOldPeople(String oldPeople) {
            this.oldPeople = oldPeople;
        }

        public String getYoungsters() {
            return youngsters;
        }

        public void setYoungsters(String youngsters) {
            this.youngsters = youngsters;
        }
        
        
        

        public String getRenalFunctionDamage() {
            return renalFunctionDamage;
        }

        public void setRenalFunctionDamage(String renalFunctionDamage) {
            this.renalFunctionDamage = renalFunctionDamage;
        }

        public String getLiverFunctionDamage() {
            return liverFunctionDamage;
        }

        public void setLiverFunctionDamage(String liverFunctionDamage) {
            this.liverFunctionDamage = liverFunctionDamage;
        }

        public String getCardiacDysfunction() {
            return cardiacDysfunction;
        }

        public void setCardiacDysfunction(String cardiacDysfunction) {
            this.cardiacDysfunction = cardiacDysfunction;
        }

        
        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductEnglishName() {
            return productEnglishName;
        }

        public void setProductEnglishName(String productEnglishName) {
            this.productEnglishName = productEnglishName;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getCommonremedy() {
            return commonremedy;
        }

        public void setCommonremedy(String commonremedy) {
            this.commonremedy = commonremedy;
        }

        public String getMechanismAction() {
            return mechanismAction;
        }

        public void setMechanismAction(String mechanismAction) {
            this.mechanismAction = mechanismAction;
        }

        public String getLaboratorExamination() {
            return laboratorExamination;
        }

        public void setLaboratorExamination(String laboratorExamination) {
            this.laboratorExamination = laboratorExamination;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getDrugReaction() {
            return drugReaction;
        }

        public void setDrugReaction(String drugReaction) {
            this.drugReaction = drugReaction;
        }

        public String getMildDrugReaction() {
            return mildDrugReaction;
        }

        public void setMildDrugReaction(String mildDrugReaction) {
            this.mildDrugReaction = mildDrugReaction;
        }

        public String getDangerousDrugReaction() {
            return dangerousDrugReaction;
        }

        public void setDangerousDrugReaction(String dangerousDrugReaction) {
            this.dangerousDrugReaction = dangerousDrugReaction;
        }

        public String getForbidden() {
            return forbidden;
        }

        public void setForbidden(String forbidden) {
            this.forbidden = forbidden;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }


        public String getDrugInteractio() {
            return drugInteractio;
        }

        public void setDrugInteractio(String drugInteractio) {
            this.drugInteractio = drugInteractio;
        }

        public String getOverDose() {
            return overDose;
        }

        public void setOverDose(String overDose) {
            this.overDose = overDose;
        }

        public String getTargets() {
            return targets;
        }

        public void setTargets(String targets) {
            this.targets = targets;
        }

        public String getLongRun() {
            return longRun;
        }

        public void setLongRun(String longRun) {
            this.longRun = longRun;
        }

        public String getAddiction() {
            return addiction;
        }

        public void setAddiction(String addiction) {
            this.addiction = addiction;
        }

        public String getStopMedicine() {
            return stopMedicine;
        }

        public void setStopMedicine(String stopMedicine) {
            this.stopMedicine = stopMedicine;
        }

        public String getPharmacokinetics() {
            return pharmacokinetics;
        }

        public void setPharmacokinetics(String pharmacokinetics) {
            this.pharmacokinetics = pharmacokinetics;
        }


       
        


}
	
	