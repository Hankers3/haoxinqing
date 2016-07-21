package com.aebiz.b2b2c.finance.ledgerbystorecategory.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.store.storeaccount.vo.StoreAccountModel;

/**
 * 商户分类分账<br>
 * 
 * 设置商户关联的分类的分账比率
 *
 * @author tangyunkai
 *
 * @date 2014年12月11日 上午10:01:20
 *
 */
@Entity
@Table(name = "ledger_by_store_category")
public class LedgerByStoreCategoryModel extends BaseModel {

	/* 商户uuid */
	private String accountUuid;

	/* 分账比率 */
	private double ledgerRate = 0;

	/* 分类编号 */
	private String categoryUuid;

	/* 备注 */
	private String note;
	
	/*商户关联的分类的名称*/
	@Transient
	private String categorynames = "";
	
	/*商户信息*/
	@Transient
	private StoreAccountModel account = new StoreAccountModel();

	public StoreAccountModel getAccount() {
		return account;
	}
	public void setAccount(StoreAccountModel account) {
		this.account = account;
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

	public void setCategoryUuid(String obj) {
		this.categoryUuid = obj;
	}
	public String getCategoryUuid() {
		return this.categoryUuid;
	}

	public void setNote(String obj) {
		this.note = obj;
	}
	public String getNote() {
		return this.note;
	}

	public String getCategorynames() {
		return categorynames;
	}
	public void setCategorynames(String categorynames) {
		this.categorynames = categorynames;
	}
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[accountUuid=" + this.getAccountUuid() + ",ledgerRate="
				+ this.getLedgerRate() + ",categoryUuid="
				+ this.getCategoryUuid() + ",note=" + this.getNote() + ",]";
	}
}
