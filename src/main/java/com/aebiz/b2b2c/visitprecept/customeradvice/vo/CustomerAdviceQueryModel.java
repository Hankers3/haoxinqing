package com.aebiz.b2b2c.visitprecept.customeradvice.vo;

import java.util.List;

public class CustomerAdviceQueryModel extends CustomerAdviceModel {
	
	private String customerName1;
	
	
	public void setCustomerName1(String customerName1) {
		this.customerName1 = customerName1;
	}
	public String getCustomerName1() {
		return customerName1;
	}

	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,]";
	}
}
