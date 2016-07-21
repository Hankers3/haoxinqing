package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo;

public class VirtualAccountCustomerLogQueryModel extends VirtualAccountCustomerLogModel {
	/*患者姓名*/
    private String realName = "" ;
    /*患者用户名*/
    private String nickName = "" ;
    /*患者手机号*/
    private String customerMobile = "" ;
    /*订单的支付类型*/
    private String customerPayType = "" ;
    
    /*消费类型*/
    private String payTypeq = "" ;
    
    private String searchType="";
        
	
	public String getSearchType() {
        return searchType;
    }


    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }


    public String getPayTypeq() {
        return payTypeq;
    }


    public void setPayTypeq(String payTypeq) {
        this.payTypeq = payTypeq;
    }


    public String getRealName() {
        return realName;
    }


    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getNickName() {
        return nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getCustomerMobile() {
        return customerMobile;
    }


    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }


    public String getCustomerPayType() {
        return customerPayType;
    }


    public void setCustomerPayType(String customerPayType) {
        this.customerPayType = customerPayType;
    }


    public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[]";
	}
}
