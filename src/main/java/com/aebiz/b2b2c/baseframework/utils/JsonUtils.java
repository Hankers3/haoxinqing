package com.aebiz.b2b2c.baseframework.utils;

import com.alibaba.fastjson.JSON;


public class JsonUtils {
    public static String toJson(Object o){  
        try {  
            return JSON.toJSONString(o);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    } 
    public static Object json2Model(String json,Class cls){
    	return JSON.parseObject(json,cls);  	
    }
}
