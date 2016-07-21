package com.aebiz.b2b2c.customermgr.customercomb.vo;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customeraudit.vo.CustomerAuthModel;
import com.aebiz.b2b2c.customermgr.customerfrozenlog.vo.CustomerFrozenLogModel;
import com.aebiz.b2b2c.customermgr.customerinfo.vo.CustomerInfoModel;
import com.aebiz.b2b2c.customermgr.customersource.vo.CustomerSourceModel;

public class CustomerCombModel extends BaseModel {

	/* 会员账户信息 */
	private CustomerModel customerModel = new CustomerModel();

	/* 会员基础信息 */
	private CustomerInfoModel customerInfoModel = new CustomerInfoModel();

	/* 会员实名认证信息 */
	//private CustomerAuthModel customerAuthModel = new CustomerAuthModel();

	/* 会员来源信息 */
	private CustomerSourceModel customerSourceModel = new CustomerSourceModel();

	/* 会员冻结日志 */
	private CustomerFrozenLogModel customerFrozenLogModel = new CustomerFrozenLogModel();


	public CustomerModel getCustomerModel() {
		return customerModel;
	}

	public void setCustomerModel(CustomerModel customerModel) {
		this.customerModel = customerModel;
	} 

	public CustomerInfoModel getCustomerInfoModel() {
		return customerInfoModel;
	}

	public void setCustomerInfoModel(CustomerInfoModel customerInfoModel) {
		this.customerInfoModel = customerInfoModel;
	}

	/*public CustomerAuthModel getCustomerAuthModel() {
		return customerAuthModel;
	}

	public void setCustomerAuthModel(CustomerAuthModel customerAuthModel) {
		this.customerAuthModel = customerAuthModel;
	}*/

	public CustomerSourceModel getCustomerSourceModel() {
		return customerSourceModel;
	}

	public void setCustomerSourceModel(CustomerSourceModel customerSourceModel) {
		this.customerSourceModel = customerSourceModel;
	}

	public CustomerFrozenLogModel getCustomerFrozenLogModel() {
		return customerFrozenLogModel;
	}

	public void setCustomerFrozenLogModel(
			CustomerFrozenLogModel customerFrozenLogModel) {
		this.customerFrozenLogModel = customerFrozenLogModel;
	}



	@Override
	public String toString() {
		return "CustomerCombModel [customerModel=" + customerModel
				+ ", customerInfoModel=" + customerInfoModel
				//+ ", customerAuthModel=" + customerAuthModel
				+ ", customerSourceModel=" + customerSourceModel
				+ ", customerFrozenLogModel=" + customerFrozenLogModel + "]";
	}

}
