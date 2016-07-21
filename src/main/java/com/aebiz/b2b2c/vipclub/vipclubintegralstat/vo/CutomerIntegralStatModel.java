package com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo;

import javax.persistence.Transient;

/**
 * 页面显示会员积分统计实体类
 * 
 * @author huyingying
 * 
 */
public class CutomerIntegralStatModel {
	/* 会员uuid */
	private String customerUuid;

	/* 会员姓名 */
	private String customerName;
	
	/* 会员类型 */
	private String userType;
	
	/* 会员可用积分 */
	private int usableIntegral;

	/* 会员已用积分 */
	private int usedIntegral;

	/* 会员累计积分 */
	private int totalIntegral;

	/* 会员购物获得积分 */
	private int shopIntegral;

	/* 会员行为获得积分 */
	private int behaviourIntegral;

	/* 会员奖励获得积分 */
	private int rewardIntegral;
	/* 会员手机号 */
	private String cutomerMobile ;

	
    
        public String getCutomerMobile() {
        return cutomerMobile;
    }

    public void setCutomerMobile(String cutomerMobile) {
        this.cutomerMobile = cutomerMobile;
    }

        public String getCustomerUuid() {
    		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
