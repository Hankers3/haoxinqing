package com.hxq.mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.wxcommon.repository.AbstractEntity;

/**
 * 药品主信息实体类
 * 
 * @author 
 * 
 */
@SuppressWarnings("serial")
@Entity(name="product_main")
public class ProductMain extends AbstractEntity<String> {

	@Transient
	public static final String SHELVES = "1";// 上架
	@Transient
	public static final String UNDERCARRIAGE = "0";// 下架
	@Transient
	public static final String UN_AUDITED = "0";// 未审核
	@Transient
	public static final String PASS = "1";// 通过
	@Transient
	public static final String NO_PASS = "2";// 未通过
	@Transient
	public static final String YES_SPEC = "1";// 是规格商品
	@Transient
	public static final String NO_SPEC = "0";// 不是规格商品

	
	/* 编号 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
    public String uuid;
	/* 药品名称(通用名) */
	public String productName;

	/* 英文名称 */
	public String productEnglishName;

	/* 药品类别 */
	public String productType;

	/* 常见治疗适应症 */
	public String commonremedy;

	/* 作用机制 */
	public String mechanismAction;

	/* 应该做的化验检查 */
	public String laboratorExamination;

	/* 用法 */
	public String direction;

	/* 用量 */
	public String dosage;

	/* 药物导致的不良反应机制 */
	public String drugReaction;

	/* 值得注意的不良反应机制 */
	public String mildDrugReaction;

	/* 危险的不良反应机制 */
	public String dangerousDrugReaction;

	/* 禁用 */
	public String forbidden;

	/* 注意事项 */
	public String attention;

	/* 肾功能损害患者 */
	public String renalFunctionDamage;

	/* 肝功能损害患者 */
	public String liverFunctionDamage;

	/* 心功能损害患者 */
	public String cardiacDysfunction;

	/* 老年人 */
	public String oldPeople;

	/* 儿童和青少年 */
	public String youngsters;

	/* 妊娠 */
	public String conception;

	/* 哺乳 */
	public String suckle;

	/* 药物相互作用 */
	public String drugInteractio;

	/* 药物过量 */
	public String overDose;

	/* 主要靶症状(主治症状) */
	public String targets;

	/* 长期使用 */
	public String longRun;

	/* 成瘾性 */
	public String addiction;

	/* 如何停药 */
	public String stopMedicine;

	/* 药代动力学 */
	public String pharmacokinetics;

	/* 图片 */
	public String image;

	/* 商品ID */
	public String productNo;

	/* 特殊人群 */
	public String specialPopulations;

	/* 审核状态 */
	public String auditState;

	/* 创建时间 */
	public String createTime;

	/* 商品状态 */
	public String state;

	/* 赠送积分数量 */
	@Column(columnDefinition="default 0")
	public Integer integral;

	/* 最少所需积分 */
	@Column(columnDefinition="default 0")
	public Integer leastIntegral;

	/* 位置 */
	@Column(columnDefinition="default 0")
	public Integer position;

	public ProductMain() {super();}
	public ProductMain(String uuid) {
		super();
		this.uuid = uuid;
	}
	@Override
	public String getId() {
		return uuid;
	}
	@Override
	public void setId(String id) {
		this.uuid = id;	
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getSpecialPopulations() {
		return specialPopulations;
	}
	public void setSpecialPopulations(String specialPopulations) {
		this.specialPopulations = specialPopulations;
	}
	public String getAuditState() {
		return auditState;
	}
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Integer getLeastIntegral() {
		return leastIntegral;
	}
	public void setLeastIntegral(Integer leastIntegral) {
		this.leastIntegral = leastIntegral;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
}
