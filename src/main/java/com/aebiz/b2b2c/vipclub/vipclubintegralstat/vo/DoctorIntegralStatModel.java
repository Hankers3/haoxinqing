package com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo;

/**
 * 页面显示医生积分统计实体类
 * 
 * @author xueli 
 * 
 */
public class DoctorIntegralStatModel {
	
	/* 医生uuid */
	private String doctorUuid;

	/* 医生姓名 */
	private String doctorName;
	
	/* 医生类型 */
	private String userType;
	
	/* 医生可用积分 */
	private int usableIntegral;

	/* 医生已用积分 */
	private int usedIntegral;

	/* 医生累计积分 */
	private int totalIntegral;

	/* 医生购物获得积分 */
	private int shopIntegral;

	/* 医生行为获得积分 */
	private int behaviourIntegral;

	/* 医生奖励获得积分 */
	private int rewardIntegral;

	public String getDoctorUuid() {
		return doctorUuid;
	}

	public void setDoctorUuid(String doctorUuid) {
		this.doctorUuid = doctorUuid;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUsableIntegral() {
		return usableIntegral;
	}

	public void setUsableIntegral(int usableIntegral) {
		this.usableIntegral = usableIntegral;
	}

	public int getUsedIntegral() {
		return usedIntegral;
	}

	public void setUsedIntegral(int usedIntegral) {
		this.usedIntegral = usedIntegral;
	}

	public int getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(int totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public int getShopIntegral() {
		return shopIntegral;
	}

	public void setShopIntegral(int shopIntegral) {
		this.shopIntegral = shopIntegral;
	}

	public int getBehaviourIntegral() {
		return behaviourIntegral;
	}

	public void setBehaviourIntegral(int behaviourIntegral) {
		this.behaviourIntegral = behaviourIntegral;
	}

	public int getRewardIntegral() {
		return rewardIntegral;
	}

	public void setRewardIntegral(int rewardIntegral) {
		this.rewardIntegral = rewardIntegral;
	}
}
