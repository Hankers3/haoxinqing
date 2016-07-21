package com.aebiz.b2b2c.finance.ledgerbystorelog.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;

/**
 * 分账修改日志表<br>
 * 
 * 当有更改时，需要详细记录分账的操作记录
 *
 * @author tangyunkai
 *
 * @date 2014年12月9日 下午7:58:19
 *
 */
@Entity
@Table(name = "ledger_by_store_log")
public class LedgerByStoreLogModel extends BaseModel {

	/*uuid */
	private String accountUuid;

	/* 修改前的分账比率 */
	private double beforeRate;

	/* 修改后的分账比率 */
	private double afterRate;

	/* 修改人 */
	private String managerUuid;

	/* 修改时间 */
	private String updateTime;

	/* 备注 */
	private String note;

	/* 商品分类uuid */
	private String productCategoryUuid;

	public void setAccountUuid(String obj) {
		this.accountUuid = obj;
	}
	public String getAccountUuid() {
		return this.accountUuid;
	}

	public void setBeforeRate(double obj) {
		this.beforeRate = obj;
	}
	public double getBeforeRate() {
		return this.beforeRate;
	}

	public void setAfterRate(double obj) {
		this.afterRate = obj;
	}
	public double getAfterRate() {
		return this.afterRate;
	}

	public void setManagerUuid(String obj) {
		this.managerUuid = obj;
	}
	public String getManagerUuid() {
		return this.managerUuid;
	}

	public void setUpdateTime(String obj) {
		this.updateTime = obj;
	}
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setNote(String obj) {
		this.note = obj;
	}
	public String getNote() {
		return this.note;
	}

	public void setProductCategoryUuid(String obj) {
		this.productCategoryUuid = obj;
	}
	public String getProductCategoryUuid() {
		return this.productCategoryUuid;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[accountUuid=" + this.getAccountUuid() + ",beforeRate="
				+ this.getBeforeRate() + ",afterRate=" + this.getAfterRate()
				+ ",managerUuid=" + this.getManagerUuid() + ",updateTime="
				+ this.getUpdateTime() + ",note=" + this.getNote()
				+ ",productCategoryUuid=" + this.getProductCategoryUuid()
				+ ",]";
	}
}
