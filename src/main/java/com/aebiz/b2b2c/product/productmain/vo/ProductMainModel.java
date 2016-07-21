package com.aebiz.b2b2c.product.productmain.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productcategoryplatform.service.ProductCategoryPlatformService;
import com.aebiz.b2b2c.product.productpub.vo.ProductType;

/**
 * 药品主信息实体类
 * 
 * @author huangpinpin
 * 
 */
@Entity
@Table(name = "product_main")
@Component
public class ProductMainModel extends BaseModel {
	@Transient
	private static ProductCategoryPlatformService pfCategoryService;
	@Transient
	private static FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@Autowired
	public void setPfCategoryService(
			ProductCategoryPlatformService pfCategoryService) {
		this.pfCategoryService = pfCategoryService;
	}

	public static final String SHELVES = "1";// 上架
	public static final String UNDERCARRIAGE = "0";// 下架

	public static final String UN_AUDITED = "0";// 未审核
	public static final String PASS = "1";// 通过
	public static final String NO_PASS = "2";// 未通过

	public static final String YES_SPEC = "1";// 是规格商品
	public static final String NO_SPEC = "0";// 不是规格商品

	/* 药品名称(通用名) */
	private String productName;

	/* 英文名称 */
	private String productEnglishName;

	/* 药品类别 */
	private String productType;

	/* 常见治疗适应症 */
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

	/* 肾功能损害患者 */
	private String renalFunctionDamage;

	/* 肝功能损害患者 */
	private String liverFunctionDamage;

	/* 心功能损害患者 */
	private String cardiacDysfunction;

	/* 老年人 */
	private String oldPeople;

	/* 儿童和青少年 */
	private String youngsters;

	/* 妊娠 */
	private String conception;

	/* 哺乳 */
	private String suckle;

	/* 药物相互作用 */
	private String drugInteractio;

	/* 药物过量 */
	private String overDose;

	/* 主要靶症状(主治症状) */
	private String targets;

	/* 长期使用 */
	private String longRun;

	/* 成瘾性 */
	private String addiction;

	/* 如何停药 */
	private String stopMedicine;

	/* 药代动力学 */
	private String pharmacokinetics;

	/* 图片 */
	private String image;

	/* 图片url */
	@Transient
	private String imageUrl;

	/* 分类名称 */
	@Transient
	private String categoryName;

	/* 商品ID */
	private String productNo;

	/* 特殊人群 */
	private String specialPopulations;

	/* 审核状态 */
	private String auditState;

	/* 创建时间 */
	private String createTime;

	/* 商品状态 */
	private String state;

	/* 赠送积分数量 */
	private int integral;

	/* 最少所需积分 */
	private int leastIntegral;

	/* 审核状态名称 */
	@Transient
	private String auditName;

	/* 上架状态名称 */
	@Transient
	private String stateName;

	/* 商品类型名称 */
	@Transient
	private String productTypeName;

	/* 位置 */
	private int position = 0;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageUrl() {
		if (StringUtil.isEmpty(this.image)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image);
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			return file.getRemotePaths();
		} else {
			return this.getImage();
		}
	}

	public String getLaboratorExamination() {
		return laboratorExamination;
	}

	public void setLaboratorExamination(String laboratorExamination) {
		this.laboratorExamination = laboratorExamination;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

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

	public String getDosage() {
		return dosage;
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

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getMechanismAction() {
		return mechanismAction;
	}

	public void setMechanismAction(String mechanismAction) {
		this.mechanismAction = mechanismAction;
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

	public String getSpecialPopulations() {
		return specialPopulations;
	}

	public void setSpecialPopulations(String specialPopulations) {
		this.specialPopulations = specialPopulations;
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

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductEnglishName() {
		return productEnglishName;
	}

	public void setProductEnglishName(String productEnglishName) {
		this.productEnglishName = productEnglishName;
	}

	public String getCommonremedy() {
		return commonremedy;
	}

	public void setCommonremedy(String commonremedy) {
		this.commonremedy = commonremedy;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getProductTypeName() {
		return ProductType.getNameByKey(this.productType);
	}

	public String getAuditName() {
		if (NO_PASS.equals(auditState)) {
			return MessageHelper.getMessage("system.product.auditNoPass");
		}
		if (PASS.equals(auditState)) {
			return MessageHelper.getMessage("system.product.auditPass");
		}
		if (UN_AUDITED.equals(auditState)) {
			return MessageHelper.getMessage("system.product.unAudit");
		}
		return "";
	}

	public String getStateName() {
		if (SHELVES.equals(state)) {
			return MessageHelper.getMessage("system.product.state1");
		}
		if (UNDERCARRIAGE.equals(state)) {
			return MessageHelper.getMessage("system.product.state2");
		}
		return "";
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setProductName(String obj) {
		this.productName = obj;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductNo(String obj) {
		this.productNo = obj;
	}

	public String getProductNo() {
		return this.productNo;
	}

	public void setAuditState(String obj) {
		this.auditState = obj;
	}

	public String getAuditState() {
		return this.auditState;
	}

	public void setCreateTime(String obj) {
		this.createTime = obj;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setProductType(String obj) {
		this.productType = obj;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setIntegral(int obj) {
		this.integral = obj;
	}

	public int getIntegral() {
		return this.integral;
	}

	public int getLeastIntegral() {
		return leastIntegral;
	}

	public void setLeastIntegral(int leastIntegral) {
		this.leastIntegral = leastIntegral;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[productName=" + this.getProductName() + ",productNo="
				+ this.getProductNo() + ",auditState=" + this.getAuditState()
				+ ",createTime=" + this.getCreateTime() + ",productType="
				+ this.getProductType() + ",state=" + this.getState()
				+ ",integral=" + this.getIntegral() + ",]";
	}
}
