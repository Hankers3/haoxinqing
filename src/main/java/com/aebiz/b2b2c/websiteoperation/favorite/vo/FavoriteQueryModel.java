package com.aebiz.b2b2c.websiteoperation.favorite.vo;

public class FavoriteQueryModel extends FavoriteModel {
	
	private String doctorName =""; 
	
	private String doctorMobile =""; 
	
	private String  customerNameq ="";
	
	private String  customerMobile ="";
	
	private String needFavoriteModel = "";
	
    
    public String getNeedFavoriteModel() {
		return needFavoriteModel;
	}



	public void setNeedFavoriteModel(String needFavoriteModel) {
		this.needFavoriteModel = needFavoriteModel;
	}



		public String getCustomerNameq() {
    		return customerNameq;
    	}



	public void setCustomerNameq(String customerNameq) {
		this.customerNameq = customerNameq;
	}



	public String getCustomerMobile() {
		return customerMobile;
	}



	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}



	public String getDoctorName() {
		return doctorName;
	}



	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}



	public String getDoctorMobile() {
		return doctorMobile;
	}



	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}



	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
