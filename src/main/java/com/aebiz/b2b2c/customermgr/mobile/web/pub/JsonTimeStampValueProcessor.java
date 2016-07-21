package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 类描述：日期处理类
 * @author: dgh
 * @date：  日期：2014-12-27 时间：下午01:46:45
 * @version 1.0
 */
public class JsonTimeStampValueProcessor implements JsonValueProcessor {
	 private String format = "yyyy-MM-dd HH:mm:ss";  
	 //构造器
	 public JsonTimeStampValueProcessor(){
		 
	 }
	 
	 public JsonTimeStampValueProcessor(String format){
		 this.format = format;
	 }

	/**
	 * 对数组的处理
	 */
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value,jsonConfig);
	}

	/**
	 * 对对象的处理
	 */
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value,jsonConfig);
	}
	
	/**
	 * 日期处理，如果是日期类型数据则格式化为日期字符串，否则字符串返回
	 * @param value
	 * @param jsonConfig
	 * @return
	 */
	private Object process(Object value, JsonConfig jsonConfig){
		if(value instanceof java.sql.Timestamp){
			return value == null? null:(new SimpleDateFormat(format)).format((java.sql.Timestamp)value);
		}else{
			return value == null? null:value.toString();
		}
	}

}
