package com.aebiz.b2b2c.order.ordermain.vo;

public class Report {
	//订单总额
	private double totalmoney;
	//积分使用金额
	private double integralmoney;
	//优惠券金额
	private double couponmoney;
	//折扣金额
	private double  discountmoney;
	//实际收入
	private double  realitymoney;
	//支付宝支付
	private double  alipaymoney;
	//微信支付
	private double  weChatmoney;
	//其他支付金额
	private double otherWaysToPaymoney;
	
	//今日成功单数
	private int successorders;
	//今日上门单数
	private int doorinorders;
	//今日取消单数
	private int closeorders;
	//今日申请退款单数
	private int refundorders;
	//今日抢单人次
	private int qiangdan;
	//今日上门人次
	private int doorinpeople;
	
	//今日客单价
	private double  perTicketSales;
	//今日上门金额
	private double doorinmoney;
	//今日取消金额
	private  double closemoney;
	//今日申请退款金额
	private  double refundmoney;
	
	//厨房平米数
	private int kitchen;
	//卫生间平米数
	private int wc;
	//其他房间平米数
	private int otherrooms;
	//抽油烟机台数
	private int kitchenVentilator;
	//冰箱台数
	private int fridge;
	
	//假期时长
	private int vacation;
	//假期占比
	private double vacationrate;
	//上门时长
	private int doorinBetweentime;
	//上门占比
	private double doorinBetweentimeRate;
	//在岗人数
	private int onGuardpeople;
	//在岗占比
	private double onGuardpeopleRate;
	
	//自动枪弹失败占比
	private double autoFailureRate;
	//平均抢单时间
	private int avgGrabOrderTime;
	//丈量时间和服务时间偏差量
	private int measureBiasServicetime;
	//客户评分平均分
	private double serviceScore;
	//互评平均分
	private double score;
	
	
	public double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public double getRealitymoney() {
		return realitymoney;
	}
	public void setRealitymoney(double realitymoney) {
		this.realitymoney = realitymoney;
	}
	public double getAlipaymoney() {
		return alipaymoney;
	}
	public void setAlipaymoney(double alipaymoney) {
		this.alipaymoney = alipaymoney;
	}
	
	public double getOtherWaysToPaymoney() {
		return otherWaysToPaymoney;
	}
	public void setOtherWaysToPaymoney(double otherWaysToPaymoney) {
		this.otherWaysToPaymoney = otherWaysToPaymoney;
	}
	public int getSuccessorders() {
		return successorders;
	}
	public void setSuccessorders(int successorders) {
		this.successorders = successorders;
	}
	public int getDoorinorders() {
		return doorinorders;
	}
	public void setDoorinorders(int doorinorders) {
		this.doorinorders = doorinorders;
	}
	public double getIntegralmoney() {
		return integralmoney;
	}
	public void setIntegralmoney(double integralmoney) {
		this.integralmoney = integralmoney;
	}
	public double getCouponmoney() {
		return couponmoney;
	}
	public void setCouponmoney(double couponmoney) {
		this.couponmoney = couponmoney;
	}
	public double getDiscountmoney() {
		return discountmoney;
	}
	public void setDiscountmoney(double discountmoney) {
		this.discountmoney = discountmoney;
	}
	public double getWeChatmoney() {
		return weChatmoney;
	}
	public void setWeChatmoney(double weChatmoney) {
		this.weChatmoney = weChatmoney;
	}
	public int getCloseorders() {
		return closeorders;
	}
	public void setCloseorders(int closeorders) {
		this.closeorders = closeorders;
	}
	public int getRefundorders() {
		return refundorders;
	}
	public void setRefundorders(int refundorders) {
		this.refundorders = refundorders;
	}
	public int getQiangdan() {
		return qiangdan;
	}
	public void setQiangdan(int qiangdan) {
		this.qiangdan = qiangdan;
	}
	public int getDoorinpeople() {
		return doorinpeople;
	}
	public void setDoorinpeople(int doorinpeople) {
		this.doorinpeople = doorinpeople;
	}

	
	public double getPerTicketSales() {
		return perTicketSales;
	}
	public void setPerTicketSales(double perTicketSales) {
		this.perTicketSales = perTicketSales;
	}
	public double getDoorinmoney() {
		return doorinmoney;
	}
	public void setDoorinmoney(double doorinmoney) {
		this.doorinmoney = doorinmoney;
	}
	public double getClosemoney() {
		return closemoney;
	}
	public void setClosemoney(double closemoney) {
		this.closemoney = closemoney;
	}
	public double getRefundmoney() {
		return refundmoney;
	}
	public void setRefundmoney(double refundmoney) {
		this.refundmoney = refundmoney;
	}
	public int getWc() {
		return wc;
	}
	public void setWc(int wc) {
		this.wc = wc;
	}
	public int getOtherrooms() {
		return otherrooms;
	}
	public void setOtherrooms(int otherrooms) {
		this.otherrooms = otherrooms;
	}
	public int getKitchenVentilator() {
		return kitchenVentilator;
	}
	public void setKitchenVentilator(int kitchenVentilator) {
		this.kitchenVentilator = kitchenVentilator;
	}
	public int getFridge() {
		return fridge;
	}
	public void setFridge(int fridge) {
		this.fridge = fridge;
	}
	public int getVacation() {
		return vacation;
	}
	public void setVacation(int vacation) {
		this.vacation = vacation;
	}
	public double getVacationrate() {
		return vacationrate;
	}
	public void setVacationrate(double vacationrate) {
		this.vacationrate = vacationrate;
	}
	public int getDoorinBetweentime() {
		return doorinBetweentime;
	}
	public void setDoorinBetweentime(int doorinBetweentime) {
		this.doorinBetweentime = doorinBetweentime;
	}
	
	public int getOnGuardpeople() {
		return onGuardpeople;
	}
	public void setOnGuardpeople(int onGuardpeople) {
		this.onGuardpeople = onGuardpeople;
	}
	
	
	public double getDoorinBetweentimeRate() {
		return doorinBetweentimeRate;
	}
	public void setDoorinBetweentimeRate(double doorinBetweentimeRate) {
		this.doorinBetweentimeRate = doorinBetweentimeRate;
	}
	public double getOnGuardpeopleRate() {
		return onGuardpeopleRate;
	}
	public void setOnGuardpeopleRate(double onGuardpeopleRate) {
		this.onGuardpeopleRate = onGuardpeopleRate;
	}
	public double getAutoFailureRate() {
		return autoFailureRate;
	}
	public void setAutoFailureRate(double autoFailureRate) {
		this.autoFailureRate = autoFailureRate;
	}
	public int getAvgGrabOrderTime() {
		return avgGrabOrderTime;
	}
	public void setAvgGrabOrderTime(int avgGrabOrderTime) {
		this.avgGrabOrderTime = avgGrabOrderTime;
	}
	
	public int getKitchen() {
		return kitchen;
	}
	public void setKitchen(int kitchen) {
		this.kitchen = kitchen;
	}
	public int getMeasureBiasServicetime() {
		return measureBiasServicetime;
	}
	public void setMeasureBiasServicetime(int measureBiasServicetime) {
		this.measureBiasServicetime = measureBiasServicetime;
	}
	public double getServiceScore() {
		return serviceScore;
	}
	public void setServiceScore(double serviceScore) {
		this.serviceScore = serviceScore;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
