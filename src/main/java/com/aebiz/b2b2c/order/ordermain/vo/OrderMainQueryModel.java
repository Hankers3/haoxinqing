package com.aebiz.b2b2c.order.ordermain.vo;

public class OrderMainQueryModel extends OrderMainModel {

	/* 页面是展开还是关闭 */
	private String showOrClose = "";
	
	/*查询订单时间（当前日期）*/
	private String searchNowDate="";
	
	/*搜索类型 1:电话咨询订单 2：私人套餐订单 */
	private String serachtype="";
	
	/*查询地区*/
	private String  city;
	
	/*查询电话*/
	private String mobile;
	
	/*查询备选人称呼*/
	private String alternative;
	
	/*查询备备选人电话*/
	private String alternativePhone;
	
	/*服务时间*/
	private String serviceTime;
	
	/*省份*/
	private String province;
	
	/*区县*/
	private String region;
	
	/*医生姓名*/
	private String serviceStaffName = "" ;
	
	/*医生电话*/
	private String serviceStaffMobile = "" ;
	
	/*创建时间开始*/
	private String createStartTime="";
	
	/* 创建时间结束*/
	private String createEndTime="";
	
	/*订单状态*/
	private String orderState="";
	
	/*患者用户名*/
	private String customerName="";
	
	/*患者手机号*/
	private String customerMobile="";
	
	/*电话订单预约开始时间*/
	private String beginTime="";
	/*电话订单预约结束时间*/
	private String endTime="";
	
	
	public String getBeginTime() {
        return beginTime;
        }
    
        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }
    
        public String getEndTime() {
            return endTime;
        }
    
        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    
        public String getCustomerName() {
    		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}
	public String getServiceStaffName() {
		return serviceStaffName;
	}

	public void setServiceStaffName(String serviceStaffName) {
		this.serviceStaffName = serviceStaffName;
	}

	public String getServiceStaffMobile() {
		return serviceStaffMobile;
	}

	public void setServiceStaffMobile(String serviceStaffMobile) {
		this.serviceStaffMobile = serviceStaffMobile;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAlternative() {
		return alternative;
	}

	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}

	public String getAlternativePhone() {
		return alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSearchNowDate() {
		return searchNowDate;
	}

	public void setSearchNowDate(String searchNowDate) {
		this.searchNowDate = searchNowDate;
	}

	public String getSerachtype() {
		return serachtype;
	}

	public void setSerachtype(String serachtype) {
		this.serachtype = serachtype;
	}

	public String getShowOrClose() {
		return showOrClose;
	}

	public void setShowOrClose(String showOrClose) {
		this.showOrClose = showOrClose;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[]";
	}
	

}
