package com.aebiz.b2b2c.basicdata.commom;

/**
 * 
 * 基础数据库关于缓存的常量
 * 
 * 1.基础数据库缓存客户端名称<br />
 * 
 * 2.基础数据库缓存的KEY<br />
 * 
 * @author duandeyi
 * 
 */
public class BasicDataCacheConstant {

	/* 基础数据库系统，名称 */
	public static final String BASIC_DATA_SYS_NAME = "basicdata";

	/* 基础数据库系统，缓存客户端名称 */
	public static final String BASIC_DATA_MEM_CLIENT_NAME = "memCachedClient";

	/* 商圈的缓存key */
	public static final String BASIC_DATA_BUSINESS_CIRCLE = BASIC_DATA_SYS_NAME
			+ "/businesscicle";

	/* 基础数据省key */
	public static final String BASIC_DATA_PROVINCE = BASIC_DATA_SYS_NAME
			+ "/province";

	/* 基础数据城市key */
	public static final String BASIC_DATA_CITY = BASIC_DATA_SYS_NAME + "/city";

	/* 基础数据区key */
	public static final String BASIC_DATA_REGION = BASIC_DATA_SYS_NAME
			+ "/region";

	/* 基础数据库物流公司key */
	public static final String BASIC_DATA_LOGISTICSCP = BASIC_DATA_SYS_NAME
			+ "/logisticscp";

	public static final String BASIC_DATA_SENSITIVEWORD = BASIC_DATA_SYS_NAME
			+ "/sensitiveword";
}
