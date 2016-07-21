package com.aebiz.b2b2c.basebusiness.utils;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.moduleinteractive.ModuleInteractiveCallHelper;
/**
 * 内部接口调用公共方法
 * @author aebizqyc
 *
 */
@Component
public class HTTPHepler {
	@Autowired
	private ModuleInteractiveCallHelper mih ;

	private static HTTPHepler ht;
	
	@PostConstruct 
	public void init() {
		ht = this;
		ht.mih= this.mih;

		}

	/**
	 * 调用http请求的共有方法
	 * @param centerName 请求中心的名字
	 * @param methodName 请求的方法
	 * @param mapParams 请求参数 
	 * @return
	 */
	public static String HttpUtil(String centerName,String methodName,Map<String, Object> mapParams){
		String backString = ht.mih.call(centerName, methodName, mapParams);
		return backString;
	}
	
}
