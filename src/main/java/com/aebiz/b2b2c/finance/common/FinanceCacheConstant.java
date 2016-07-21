package com.aebiz.b2b2c.finance.common;

/**
 * 
 * 财务系统的memcache的参数设置
 * 
 * 1.财务系统的key设置<br />
 * 2.为了便于扩展，将memcache客户端名称也定义在此
 * 
 * @author cj
 * 
 */
public class FinanceCacheConstant {
	/* 财务系统、名称 */
	private static final String FINANCE_NAME = "finance";

	/* 财务系统,缓存客服端名称 */
	public static final String FINANCE_MEM_CLIENT_NAME = "memCachedClient";

	/* 会员的账户信息key */
	public static final String FINANCE_CUSTOMER_ACCOUNT = FINANCE_NAME
			+ "/customeraccount";
	/* 会员的账户信息key */
	public static final String FINANCE_CUSTOMER_ACCOUNT_UUID = FINANCE_NAME
	        + "/customeraccountuuid";

	/* 会员的账户信息key 以CustonerUuid作为KEY */
	public static final String FINANCE_CUSTOMER_ACCOUNT_CID = FINANCE_NAME
			+ "/customermodeluuid";

	/* 会员和银行卡绑定key */
	public static final String FINANCE_CUSTOMER_BANK_REL = FINANCE_NAME
			+ "/customerbankrel";

	/* 会员和银行卡绑定key,CUSTOMERUUID作为KEY */
	public static final String FINANCE_CUSTOMER_BANK_REL_CID = FINANCE_NAME
			+ "/customerbankrelcid";

	/* 发票管理key */
	public static final String FINANCE_INVOICE = FINANCE_NAME + "/invoice";

	/* 商户分账key,key值为uuid */
	public static final String FINANCE_LEDGER_BY_STORE = FINANCE_NAME
			+ "/ledgerbystore";

	/* 商户分账key,key值为ACCOUNTID */
	public static final String FINANCE_LEDGER_BY_STORE_ACCOUNTID = FINANCE_NAME
			+ "/ledgerbystoreaccountid";

	/* 商户分类分账key ,uuid做为key */
	public static final String FINANCE_LEDGER_BY_STORECATEGORY = FINANCE_NAME
			+ "/ledgerbystorecategory";

	/* 商户分类分账key ,accountId和categoryId联合做为key */
	public static final String FINANCE_LEDGER_BY_STORECATEGORY_AIDWITHCID = FINANCE_NAME
			+ "/ledgerbystorecategoryaidwithcid";

	/* 商户分账修改日志key */
	public static final String FINANCE_LEDGER_BY_STORE_LOG = FINANCE_NAME
			+ "/ledgerbystorelog";

	/* 退款单日志key */
	public static final String FINANCE_ORDER_RETURN_MONEY_LOG = FINANCE_NAME
			+ "/orderreturnmoneylog";

	/* 退款key */
	public static final String FINANCE_ORDER_RETURN_MONEY_MAIN = FINANCE_NAME
			+ "/orderreturnmoneymain";

	/* 商户保证金key */
	public static final String FINANCE_STORE_BOND_CHARGE = FINANCE_NAME
			+ "/storebondcharge";

	/* 商户银行卡绑定信息key */
	public static final String FINANCE_STORE_EDIT_BANK_BOUND = FINANCE_NAME
			+ "/storeeditbankbound";

	/* 记录商户的账户信息key */
	public static final String FINANCE_STORE_FINANCE_ACCOUNT = FINANCE_NAME
			+ "/storefinanceaccount";

	/* 商户发票key */
	public static final String FINANCE_STORE_INVOICE_SET = FINANCE_NAME
			+ "/storeinvoiceset";

	/* 商户发票管理信息 key,此KEY用UUID作为key */
	public static final String FINANCE_STORE_INVOICE_AMOUNT = FINANCE_NAME
			+ "/storeinvoiceamount";

	/* 商户发票管理信息 key,此KEY用商户ID作为key */
	public static final String FINANCE_STORE_INVOICE_AMOUNT_SID = FINANCE_NAME
			+ "/storeinvoiceamountsid";

	public static final String FINANCE_STORE_INVOICE_AMOUNT_LOG = FINANCE_NAME
			+ "/storeinvoiceamountlog";

	/* 商户服务费key */
	public static final String FINANCE_STORE_SERVICE_CHARGE = FINANCE_NAME
			+ "/storeservicecharge";

	/* 提现申请key */
	public static final String FINANCE_WITHDRAW_APPLY = FINANCE_NAME
			+ "/withdrawapply";

}
