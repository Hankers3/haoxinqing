package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 类描述：Json工具类
 * @author: dgh
 * @date： 日期：2014-12-27 时间：下午01:45:29
 * @version 1.0
 */
public class JsonUtils {
	
	/**
	 * 把对象转化为json字符串，如果为空则返回{}。
	 * @param object 待转化的对象
	 * @return 返回json字符串
	 */
	public static String getJSONString(Object object) {
		String jsonString = null;

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new JsonDateValueProcessor());//注册日期处理器

		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class,
				new JsonTimeStampValueProcessor());//注册时间戳处理器

		if (object != null) {
			if (object instanceof Collection || object instanceof Object[]) {//如果是集合则当成集合数组处理
				jsonString = JSONArray.fromObject(object, jsonConfig)
						.toString();
			} else {//如果是对象则如此处理
				jsonString = JSONObject.fromObject(object, jsonConfig)
						.toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}

}
