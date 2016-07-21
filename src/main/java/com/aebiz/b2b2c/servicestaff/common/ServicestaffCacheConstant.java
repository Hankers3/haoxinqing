package com.aebiz.b2b2c.servicestaff.common;


public class ServicestaffCacheConstant {

	/* 医生系统，名称 */
	public static final String SERVICESTAFF_SYS_NAME = "servicestaff";

	/* 医生系统，缓存客户端名称 */
	public static final String SERVICESTAFF_CLIENT_NAME = "memCachedClient";

	/* 医生的缓存key */
	public static final String SERVICESTAFF_SERVICESTAFF_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaff";

	/* 医生基本信息的缓存key */
	public static final String SERVICESTAFF_SERVICESTAFFINFO_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffinfo";
	/* 医院基本信息的缓存key */
	public static final String SERVICESTAFF_HOSPITALINFO_KEY = SERVICESTAFF_SYS_NAME
			+ "/hospitalInfo";
	/* 科室基本信息的缓存key */
	public static final String SERVICESTAFF_DEPARTINFO_KEY = SERVICESTAFF_SYS_NAME
			+ "/departmentInfo";
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
	
	/* 医生关联银行的缓存 */
	public static final String SERVICESTAFF_BANKRELATION_KEY = SERVICESTAFF_SYS_NAME
	        + "/bankrelation";
	/* 医生关联银行的缓存 */
	public static final String SERVICESTAFF_BANKRELATION_KEY_UUIDS = SERVICESTAFF_SYS_NAME
	        + "/bankrelationuuid";

	/* 医生uuid集合的缓存 */
	public static final String SERVICESTAFF_UUIDS_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffUuids";
	/* 名医的uuid集合的缓存 */
	public static final String SERVICESTAFF_FAMOUSUUIDS_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffFamousUuids";
	/* 医生性别的缓存 */
	public static final String SERVICESTAFF_SEX_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffInfoSex";
	/* 证件号码的缓存 */
	public static final String SERVICESTAFF_CERTCODE_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffInfoCertCode";
	/* 所在行业的缓存 */
	public static final String SERVICESTAFF_INDUSTRY_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffInfoIndustry";
	/* 擅长领域的缓存 */
	public static final String SERVICESTAFF_TERRITORY_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffInfoTerritory";
	/* 医生基本信息uuid集合的缓存 */
	public static final String SERVICESTAFF_INFOUUIDS_KEY = SERVICESTAFF_SYS_NAME
			+ "/servicestaffInfoUuids";
	/* 医院名称的缓存 */
	public static final String SERVICESTAFF_HOSPITALNAME_KEY = SERVICESTAFF_SYS_NAME
			+ "/hospitalName";
	/* 医院uuid集合的缓存 */
	public static final String SERVICESTAFF_HOSPITALUUIDS_KEY = SERVICESTAFF_SYS_NAME
			+ "/hospitalUuids";
	/* 医院uuid的缓存 */
	public static final String SERVICESTAFF_HOSPITALUUID_KEY = SERVICESTAFF_SYS_NAME
			+ "/hospitalUuid";
	/* 科室名称的缓存 */
	public static final String SERVICESTAFF_DEPARTMENTNAME_KEY = SERVICESTAFF_SYS_NAME
			+ "/departmentName";
	/* 医生导入信息的缓存 */
	public static final String SERVICESTAFF_DOCTORIMPORT_KEY = SERVICESTAFF_SYS_NAME
			+ "/doctorImport";

	public static final String SERVICESTAFF_DOCTORIMPORTUUIDS_KEY = SERVICESTAFF_SYS_NAME
			+ "/doctorImportUuids";
	/* 医生账单的缓存 */
	public static final String SERVICESTAFF_DOCTORBILL_KEY = SERVICESTAFF_SYS_NAME
	        + "/doctorbill";
	/* 医生类别的缓存 */
	public static final String SERVICESTAFF_DOCTORCATEGORY_KEY = SERVICESTAFF_SYS_NAME
	        + "/doctorcategory";
	/* 医生分组的缓存 */
	public static final String SERVICESTAFF_DOCTORGROUP_KEY = SERVICESTAFF_SYS_NAME
	        + "/doctorgroup";
	/* 医生公告的缓存 */
	public static final String SERVICESTAFF_DOCTORNOTICE_KEY = SERVICESTAFF_SYS_NAME
	        + "/doctornotice";
	/* 医生公告uuid的缓存 */
	public static final String SERVICESTAFF_DOCTORNOTICE_KEY_UUIDS = SERVICESTAFF_SYS_NAME
	        + "/doctornoticeuuids";
	/* 医生权限的缓存 */
	public static final String SERVICESTAFF_DOCTORRIGHT_KEY = SERVICESTAFF_SYS_NAME
	        + "/doctorright";
	/* 医生标签的缓存 */
	public static final String SERVICESTAFF_DOCTORTAG_KEY = SERVICESTAFF_SYS_NAME
	        + "/doctortag";
	/* 医生电话咨询费用的缓存 */
	public static final String SERVICESTAFF_DOCTORTELECOUN_KEY = SERVICESTAFF_SYS_NAME
	        + "/doctortelecoun";
	/* 医生分组的缓存 */
	public static final String SERVICESTAFF_GROUPFRIENDS_KEY = SERVICESTAFF_SYS_NAME
	        + "/groupfriends";
	/* 医生的出诊时间缓存 */
	public static final String SERVICESTAFF_HOMEVISITSET_KEY = SERVICESTAFF_SYS_NAME
	        + "/homevisitset";
	/* 医生的出诊时间uuid缓存 */
	public static final String SERVICESTAFF_HOMEVISITSET_KEY_UUIDS = SERVICESTAFF_SYS_NAME
	        + "/homevisitsetuuids";
	/* 套餐医生关联的缓存*/
	public static final String SERVICESTAFF_PACKAGEDOCTOR_KEY = SERVICESTAFF_SYS_NAME
	        + "/packagedoctor";
	/* 医生套餐的缓存*/
	public static final String SERVICESTAFF_PACKAGEMANAGEMENT_KEY = SERVICESTAFF_SYS_NAME
	        + "/packagemanagement";
	/* 家政信息的缓存*/
	public static final String SERVICESTAFF_SERVICESTAFFCOMB_KEY = SERVICESTAFF_SYS_NAME
	        + "/Servicestaffcomb";
	/* 家政的的缓存 */
        public static final String SERVICESTAFF_SERVICESTAFFCOORD_KEY = SERVICESTAFF_SYS_NAME
                + "/servicestaffcoord";
        /* 医生入口缓存 */
        public static final String SERVICESTAFF_SERVICESTAFFENTRY_KEY = SERVICESTAFF_SYS_NAME
                + "/servicestaffentry";
        /* 家政人员详细信息的缓存 */
        public static final String SERVICESTAFF_SERVICESTAFFINTERVIEW_KEY = SERVICESTAFF_SYS_NAME
                + "/servicestaffinterview";
        /* 医生等级的缓存 */
        public static final String SERVICESTAFF_SERVICESTAFFLEVEL_KEY = SERVICESTAFF_SYS_NAME
                + "/servicestafflevel";
        /* 家政资源缓存 */
        public static final String SERVICESTAFF_SERVICESTAFFSOURCE_KEY = SERVICESTAFF_SYS_NAME
                + "/Servicestaffsource";
        /* 家政登陆状态的缓存 */
        public static final String SERVICESTAFF_STAFFLOGINSTATUS_KEY = SERVICESTAFF_SYS_NAME
                + "/staffloginstatus";
        /* 医生的电话咨询的缓存 */
        public static final String SERVICESTAFF_TELEPHONECOST_KEY = SERVICESTAFF_SYS_NAME
                + "/telephonecost";
        /* 医生的电话设置的缓存 */
        public static final String SERVICESTAFF_TELEPHONECOUNSE_KEY = SERVICESTAFF_SYS_NAME
                + "/telephonecounse";
        /* 医生的电话设置uuids的缓存 */
        public static final String SERVICESTAFF_TELEPHONECOUNSE_KEY_UUIDS = SERVICESTAFF_SYS_NAME
                + "/telephonecounseuuids";
        /* 医生的电话设置uuids的缓存 */
        public static final String SERVICESTAFF_TELEPHONETIME_KEY = SERVICESTAFF_SYS_NAME
                + "/telephonetime";

}
