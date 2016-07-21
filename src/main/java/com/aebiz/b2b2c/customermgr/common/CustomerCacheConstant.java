package com.aebiz.b2b2c.customermgr.common;

import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombModel;
import com.aebiz.b2b2c.customermgr.customercomb.vo.CustomerCombQueryModel;

/**
 * 会员缓存相关信息
 * @author zdx
 * @date 2015-5-25
 */
public class CustomerCacheConstant {
	
	/* 会员系统，名称 */
	public static final String CUSTOMER_SYS_NAME = "customer";

	/* 会员系统，缓存客户端名称 */
	public static final String CUSTOMER_CLIENT_NAME = "memCachedClient";
	
	/* 会员的缓存key */
	public static final String CUSTOMER_CUSTOMER_KEY = CUSTOMER_SYS_NAME
			+ "/customer";
	
	       /* 会员的缓存key */
        public static final String CUSTOMER_CUSTOMER_KEY_NAME = CUSTOMER_SYS_NAME
                        + "/customername";
	/* 会员地址的缓存key */
	public static final String CUSTOMER_CUSTOMERADDRESS_KEY = CUSTOMER_SYS_NAME
			+ "/customeraddress";
	/* 会员信息的缓存key */
	public static final String CUSTOMER_CUSTOMERINFO_KEY = CUSTOMER_SYS_NAME
			+ "/customerinfo";
	/* 随访记录的key */
        public static final String CUSTOMER_CUSTOMERAUDIT_KEY = CUSTOMER_SYS_NAME
                        + "/customeraudit";
	
        /* 获取咨询记录的uuids的key */
        public static final String CUSTOMER_CONSULTRECORD_KEY_UUIDS = CUSTOMER_SYS_NAME
                        + "/consultrecorduuids";
        
        /* 随访记录的key */
        public static final String CUSTOMER_CONSULTRECORD_KEY = CUSTOMER_SYS_NAME
                        + "/consultrecord";
        /* 患者的冻结日志的key */
        public static final String CUSTOMER_CUSTOMERFROZENLOG_KEY = CUSTOMER_SYS_NAME
                + "/customerfrozenlog";
        /* 患者的info修改的key */
        public static final String CUSTOMER_CUSTOMERINFOMODIFY_KEY = CUSTOMER_SYS_NAME
                + "/customerinfomodify";
        
        /* 患者登陆日志的key */
        public static final String CUSTOMER_CUSTOMERLOGINLOG_KEY = CUSTOMER_SYS_NAME
                + "/customerloginlog";
        /* 患者登陆日志的key */
        public static final String CUSTOMER_CUSTOMERSHOPLEVEL_KEY = CUSTOMER_SYS_NAME
                + "/customershoplevel";
        /* 患者资源的key */
        public static final String CUSTOMER_CUSTOMERSOURCE_KEY = CUSTOMER_SYS_NAME
                + "/customersource";
        
        /* 患者资源的uuid的key */
        public static final String CUSTOMER_CUSTOMERSOURCE_KEY_UUIDS = CUSTOMER_SYS_NAME
                + "/customersourceuuids";
        
        /* 患者提交内容的key */
        public static final String CUSTOMER_CUSTOMERSUBSCRIBECONTENT_KEY = CUSTOMER_SYS_NAME
                + "/customersubscribecontent";
        
        /* 患者提交申请key */
        public static final String CUSTOMER_CUSTOMERSUBSCRIPTION_KEY = CUSTOMER_SYS_NAME
                + "/customersubscription";
        /* 患者提交申请日志key */
        public static final String CUSTOMER_CUSTOMERSUBSCRIPTIONLOG_KEY = CUSTOMER_SYS_NAME
                + "/customersubscriptionlog";
        /* 患者提交申请日志uuids的key */
        public static final String CUSTOMER_CUSTOMERSUBSCRIPTIONLOG_KEY_UUIDS = CUSTOMER_SYS_NAME
                + "/customersubscriptionloguuids";
        /* 患者临时的key */
        public static final String CUSTOMER_CUSTOMERTEMP_KEY = CUSTOMER_SYS_NAME
                + "/customertemp";
        
        /* 会员的缓存key */
        public static final String CUSTOMER_CUSTOMER_KEY_MOBILE = CUSTOMER_SYS_NAME
                        + "/mobile";
        /* customercomb的缓存key */
        public static final String CUSTOMER_CUSTOMERCOMB_KEY = CUSTOMER_SYS_NAME
                + "/customercomb";
        
        /*患者的分类的缓存的key*/
        public static final String CUSTOMER_CUSTOMERCATEGORY_KEY = CUSTOMER_SYS_NAME
                + "/customercategory";
	
	
}
