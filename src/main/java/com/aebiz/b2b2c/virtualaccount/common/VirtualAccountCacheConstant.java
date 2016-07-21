package com.aebiz.b2b2c.virtualaccount.common;

/**
 * 
 * 虚拟账户系统的memcache的参数设置
 * 
 * 1.虚拟账户系统的key设置<br />
 * 2.为了便于扩展，将memcache客户端名称也定义在此
 * 
 * @author cj
 * 
 */
public class VirtualAccountCacheConstant {
	/* 礼品卡系统、名称 */
	private static final String VIRTUAL_ACCOUNT_NAME = "virtualaccount";

	/* 礼品卡系统,缓存客服端名称 */
	public static final String VIRTUAL_ACCOUNT_MEM_CLIENT_NAME = "memCachedClient";

	/* 记录会员虚拟账户的充值信息key */
	public static final String VIRTUAL_ACCOUNT_CUSTOMER_CHARGE = VIRTUAL_ACCOUNT_NAME
			+ "/virtualaccountcustomercharge";
	
	/* 记录会员虚拟账户Uuid的充值信息key */
	public static final String VIRTUAL_ACCOUNT_CUSTOMER_CHARGE_UUIDS = VIRTUAL_ACCOUNT_NAME
			+ "/virtualaccountcustomerchargeuuids";
	
	/* 记录会员虚拟账户Uuid的充值信息key */
	public static final String VIRTUAL_ACCOUNT_CUSTOMER_CHARGE_MOBILE = VIRTUAL_ACCOUNT_NAME
			+ "/virtualaccountcustomerchargemobile";
	

	/* 会员虚拟账户日志表key */
	public static final String VIRTUAL_ACCOUNT_CUSTOMER_LOG = VIRTUAL_ACCOUNT_NAME
			+ "/virtualaccountcustomerlog";
	
	/* 会员获取会员Uuidkey */
	public static final String VIRTUAL_ACCOUNT_CUSTOMER_LOG_UUID = VIRTUAL_ACCOUNT_NAME
			+ "/virtualaccountcustomerloguuid";
	
	/* 会员获取会员UuidSkey */
        public static final String VIRTUAL_ACCOUNT_CUSTOMER_LOG_UUIDS = VIRTUAL_ACCOUNT_NAME
                        + "/virtualaccountcustomerloguuidS";

	/* 记录商户虚拟账户的充值信息key */
	public static final String VIRTUAL_ACCOUNT_STORE_CHARGE = VIRTUAL_ACCOUNT_NAME
			+ "/virtualaccountstorecharge";

	/* 商户虚拟账户日志表key */
	public static final String VIRTUAL_ACCOUNT_STORE_LOG = VIRTUAL_ACCOUNT_NAME
			+ "/virtualaccountstorelog";
}
