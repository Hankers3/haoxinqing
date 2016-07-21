package com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo;

/**
 * 会员积分日志查询实体类
 * 
 * @author huyingying
 * 
 */
public class VipclubIntegralLogQueryModel extends VipclubIntegralLogModel {
	
	/* 最小时间范围 */
	private String minTime;
	
	/* 最大时间范围 */
	private String maxTime;
	
	/* 会员的Uuid */
	private String customerUuid;
	
	/*积分类型的属性*/
	private String integrals;
	
	/*积分查询时间属性*/
	private String queryTime;
	
	/*积分查询时间类型*/
	private String queryTimeType;
	
	public String getQueryTimeType() {
		return queryTimeType;
	}

	public void setQueryTimeType(String queryTimeType) {
		this.queryTimeType = queryTimeType;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getIntegrals() {
		return integrals;
	}

	public void setIntegrals(String integrals) {
		this.integrals = integrals;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public void setMinTime(String obj) {
		this.minTime = obj;
	}

	public String getMinTime() {
		return this.minTime;
	}

	public void setMaxTime(String obj) {
		this.maxTime = obj;
	}

	public String getMaxTime() {
		return this.maxTime;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[minTime=" + this.getMinTime() + ",maxTime="
				+ this.getMaxTime() + ",]";
	}
}
