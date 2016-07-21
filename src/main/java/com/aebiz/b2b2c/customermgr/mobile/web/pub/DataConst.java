package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.util.HashMap;
import java.util.Map;


/**
 * 类描述：公共数据常量类
 * @author: dgh
 * @date： 日期：2015-03-19 时间：上午10:10:56
 * @version 1.0
 */
public abstract class DataConst {
	
	/*北京左侧经度*/
	public static final double BJzuolongitude = 116.209947;
	/*北京右侧经度*/
	public static final double BJyoulongitude = 116.560646;
	/*北京上纬度*/
	public static final double BJshanglatitude = 40.027516;
	/*北京下纬度*/
	public static final double BJxialatitude = 39.763576;
	
	/*天津左侧经度*/
	public static final double TJzuolongitude = 116.209947;
	/*天津右侧经度*/
	public static final double TJyoulongitude = 116.560646;
	/*天津上纬度*/
	public static final double TJshanglatitude = 40.027516;
	/*天津下纬度*/
	public static final double TJxialatitude = 39.763576;
	
	/*上海左侧经度*/
	public static final double SHzuolongitude = 116.209947;
	/*上海右侧经度*/
	public static final double SHyoulongitude = 116.560646;
	/*上海上纬度*/
	public static final double SHshanglatitude = 40.027516;
	/*上海下纬度*/
	public static final double SHxialatitude = 39.763576;
	
	public static Map<String, Double> jingweidu = new HashMap<String, Double>();
	
	static {
		jingweidu.put("BJzuolongitude", BJzuolongitude);
		jingweidu.put("BJyoulongitude", BJyoulongitude);
		jingweidu.put("BJshanglatitude", BJshanglatitude);
		jingweidu.put("BJxialatitude", BJxialatitude);

		jingweidu.put("TJzuolongitude", TJzuolongitude);
		jingweidu.put("TJyoulongitude", TJyoulongitude);
		jingweidu.put("TJshanglatitude", TJshanglatitude);
		jingweidu.put("TJxialatitude", TJxialatitude);
		
		jingweidu.put("SHzuolongitude", SHzuolongitude);
		jingweidu.put("SHyoulongitude", SHyoulongitude);
		jingweidu.put("SHshanglatitude", SHshanglatitude);
		jingweidu.put("SHxialatitude", SHxialatitude);
		
	}
	
}
