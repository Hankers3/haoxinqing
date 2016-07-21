package com.hxq.mobile.doctor.visit.status;
/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年5月19日 下午7:05:47 
* @version 2.0 
* @parameter  随访方案表中的状态
* @since  
* @return  
*/
public class VisitRecordStatus {
	public static String DELFLAG_UNFINISHED = "0"; //未完成
	public static String DELFLAG_NORMAL = "1"; //正常
	public static String DELFLAG_DEL = "2"; //删除
	
	public static String RECOMMEND_DOCTOR = "0"; //医生的随访方案
	public static String RECOMMEND_CUSTOM = "1"; //系统自定的随访方案
	public static String RECOMMEND_CUSTOMER = "2"; //患者的随访方案

}
