package com.aebiz.b2b2c.finance.invoice.vo;

public class InvoiceQueryModel extends InvoiceModel {
	
	private String needInvoice;
	
	public String getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(String needInvoice) {
		this.needInvoice = needInvoice;
	}

	public String toString() {
		return "Model" + this.getClass().getName() + "," + super.toString()
				+ " ,[]";
	}
}
