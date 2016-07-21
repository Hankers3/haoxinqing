package com.aebiz.b2b2c.servicestaff.common;


public class ServicestaffInfoCacheConstant {

	/* 医生系统，名称 */
	public static final String SERVICESTAFF_SYS_NAME = "servicestaff";

	/* 医生系统，缓存客户端名称 */
	public static final String SERVICESTAFF_CLIENT_NAME = "memCachedClient";

	/* 医生的缓存key */
	public static final String SERVICESTAFF_SERVICESTAFF_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaff";

	public static final String SERVICESTAFF_SERVICESTAFFINFO_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffinfo";

	/* 医生等级的缓存key */
	public static final String SERVICESTAFF_SERVICESTAFFLEVEL_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestafflevel";
	/* 医生真实姓名的缓存 */
	public static final String SERVICESTAFF_REALNAME_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffRealname";
	/* 医生手机号的缓存 */
	public static final String SERVICESTAFF_MOBILE_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffmobile";

	/* 医生集合的缓存 */
	public static final String SERVICESTAFF_SERVICESTAFF_LIST_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffList";

	/* 电话开通状态的缓存 */
	public static final String SERVICESTAFF_TELSTATE_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffTelState";

	/* 加号咨询开通状态的缓存 */
	public static final String SERVICESTAFF_ORDERSTATE_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffOrderState";

	/* 医生流水号的缓存 */
	public static final String SERVICESTAFF_DOCTORNO_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffDoctorNo";
	
}
