package com.aebiz.b2b2c.visitprecept.common;

public class VisitpreceptCacheConstant {

	/* 随访系统，名称 */
	public static final String VISITPRECEPT_SYS_NAME = "visitprecept";

	/* 随访系统，缓存客户端名称 */
	public static final String VISITPRECEPT_CLIENT_NAME = "memCachedClient";

	/* 病例分类的缓存key */
	public static final String VISITPRECEPT_CASECATEGORY_KEY = VISITPRECEPT_SYS_NAME
			+ "/caseCategory";
	/* 患者分组的缓存 */
	public static final String VISITPRECEPT_CASEGROUP_KEY = VISITPRECEPT_SYS_NAME
			+ "/caseGroup";
	/* 患者分组uuids的缓存 */
	public static final String VISITPRECEPT_CASEGROUP_UUIDS_KEY = VISITPRECEPT_SYS_NAME
			+ "/caseGroupUuids";

	/* 患者和医生关联关系的缓存 */
	public static final String VISITPRECEPT_CUSTOMERDOCTORRELE_KEY = VISITPRECEPT_SYS_NAME
			+ "/customerDoctorRele";
	/* 患者和医生关联关系的uuid集合的缓存 */
	public static final String VISITPRECEPT_CUSTOMERDOCTORRELE_UUIDS_KEY = VISITPRECEPT_SYS_NAME
			+ "/customerDoctorReleUuids";
	/* 患者和医生关联关系中的患者uuid集合的缓存 */
	public static final String VISITPRECEPT_COSTOMERUUIDS_KEY = VISITPRECEPT_SYS_NAME
			+ "/customerUuids";
	/* 患者和医生关联关系中的患者MODEL的缓存 */
	public static final String VISITPRECEPT_CUSTOMERDOCTORRELE_CUSTOMER_KEY = VISITPRECEPT_SYS_NAME
			+ "/customer";
	/* 治疗方案的缓存 */
	public static final String VISITPRECEPT_DOCTORADVICE_KEY = VISITPRECEPT_SYS_NAME
			+ "/doctorAdvice";
	/* 治疗方案的医生uuid集合缓存 */
	public static final String VISITPRECEPT_DOCTORADVICE_DOCTORUUIDS_KEY = VISITPRECEPT_SYS_NAME
			+ "/doctorAdviceDoctorUuids";
	public static final String VISITPRECEPT_DOCTORADVICE_UUIDS_KEY = VISITPRECEPT_SYS_NAME
			+ "/doctorAdviceUuids";
	/* 随访申请的缓存 */
	public static final String VISITPRECEPT_VISITAPPLY_KEY = VISITPRECEPT_SYS_NAME
			+ "/visitapply";
	/* 随访申请患者编号集合的缓存 */
	public static final String VISITPRECEPT_VISITAPPLY_CUSTOMERUUIDS_KEY = VISITPRECEPT_SYS_NAME
			+ "/visitapplyCustomerUuids";
	/* 随访申请Uuid集合的缓存 */
	public static final String VISITPRECEPT_VISITAPPLY_UUIDS_KEY = VISITPRECEPT_SYS_NAME
			+ "/visitapplyUuids";
	/* 随访方案 */
	public static final String VISITPRECEPT_VISITPRECEPT_KEY = VISITPRECEPT_SYS_NAME
			+ "/visitPrecept";

}
