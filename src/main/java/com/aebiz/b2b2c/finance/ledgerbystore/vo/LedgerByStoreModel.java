package com.aebiz.b2b2c.finance.ledgerbystore.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.servicestaff.servicestafflevel.vo.ServicestafflevelModel;
import com.aebiz.b2b2c.store.storeaccount.vo.StoreAccountModel;

/**
 * 商户分账表<br>
 * 
 * 当商户设置分账 需要在此设置某商户的分账比率 1.如果是按店铺分账,则直接设置店铺的分账比率
 * 2.如果是按店铺分类分账,则需要设置店铺的分类的分账比率，会有多条记录
 *
 * @author tangyunkai
 *
 * @date 2014年12月9日 下午7:53:45
 *
 */
@Entity
@Table(name = "ledger_by_store")
public class LedgerByStoreModel extends BaseModel {

	/*uuid */
	private String accountUuid;

	/* 分账比率 */
	private double ledgerRate;

	/* 合同编号 */
	private String contractUuid;

	/* 备注 */
	private String note;

	/* 运费是否参与分账 */
	private String freightJoinRouting = "";
	
	/*商户信息*/
	@Transient
	private StoreAccountModel account = new StoreAccountModel();
	
	/*家政员等级信息 new add by xl*/
	@Transient 
	private ServicestafflevelModel staffentry = new ServicestafflevelModel();

	public ServicestafflevelModel getStaffentry() {
		return staffentry;
	}
	
	public void setStaffentry(ServicestafflevelModel staffentry) {
		this.staffentry = staffentry;
	}
	
	public StoreAccountModel getAccount() {
		return account;
	}
	public void setAccount(StoreAccountModel account) {
		this.account = account;
	}
	public String getFreightJoinRouting() {
		return freightJoinRouting;
	}
	public void setFreightJoinRouting(String freightJoinRouting) {
		this.freightJoinRouting = freightJoinRouting;
	}
	public void setAccountUuid(String obj) {
		this.accountUuid = obj;
	}
	public String getAccountUuid() {
		return this.accountUuid;
	}

	public void setLedgerRate(double obj) {
		this.ledgerRate = obj;
	}
	public double getLedgerRate() {
		return this.ledgerRate;
	}

	public void setContractUuid(String obj) {
		this.contractUuid = obj;
	}
	public String getContractUuid() {
		return this.contractUuid;
	}

	public void setNote(String obj) {
		this.note = obj;
	}
	public String getNote() {
		return this.note;
	}
	
	@Override
	public String toString() {
		return "LedgerByStoreModel [accountUuid=" + accountUuid
				+ ", ledgerRate=" + ledgerRate + ", contractUuid="
				+ contractUuid + ", note="
				+ note + ", freightJoinRouting=" + freightJoinRouting + "]";
	}

}
