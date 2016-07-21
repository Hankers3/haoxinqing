package com.aebiz.b2b2c.order.orderrouting.vo;

public class OrderRoutingQueryModel extends OrderRoutingModel {
	
	/*按月份分账*/
	public static final String MONTH_ROUTING = "1"; 
	
	/*按年份分账*/
	public static final String YEAR_ROUTING = "2"; 
	
	private String routTime2;
	
	/*分账方式（按日/月/年）*/
	private String fzType = "0";
	
	/*分账的年份(和routMonth一起使用)*/
	private String routYear="";
	
	/*分账的月份(和routYear一起使用)*/
	private String routMonth="";
	
	/*分账的年份*/
	private String routOnlyYear="";
	
	/* 医生id */
	private String doctorUuid="";
	/* 医生姓名 */
	private String doctorRealName ;
	
	/* 医生电话*/
        private String doctorMobile ;
	
	
        
        

       public String getDoctorUuid() {
            return doctorUuid;
        }

        public void setDoctorUuid(String doctorUuid) {
            this.doctorUuid = doctorUuid;
        }

        public String getDoctorRealName() {
            return doctorRealName;
        }

        public void setDoctorRealName(String doctorRealName) {
            this.doctorRealName = doctorRealName;
        }

        public String getDoctorMobile() {
            return doctorMobile;
        }

        public void setDoctorMobile(String doctorMobile) {
            this.doctorMobile = doctorMobile;
        }

	
	public String getRoutOnlyYear() {
		return routOnlyYear;
	}
	public void setRoutOnlyYear(String routOnlyYear) {
		this.routOnlyYear = routOnlyYear;
	}
	public String getRoutYear() {
		return routYear;
	}
	public void setRoutYear(String routYear) {
		this.routYear = routYear;
	}
	public String getRoutMonth() {
		return routMonth;
	}
	public void setRoutMonth(String routMonth) {
		this.routMonth = routMonth;
	}
	public String getFzType() {
		return fzType;
	}
	public void setFzType(String fzType) {
		this.fzType = fzType;
	}
	public void setRoutTime2(String obj){
		this.routTime2 = obj;
	}
	public String getRoutTime2(){
		return this.routTime2;
	}
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[routTime2=" + this.getRoutTime2() + ",]";
	}
}
