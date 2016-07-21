package com.aebiz.b2b2c.baseframework.message;

import com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator;
import com.wxcommon.util.ObjectUtils;

import java.util.*;

public class MessageHelper {

	public static String getMessage(String msgId) {
		try {
			String value = mapMsgs.get(msgId);
			return value != null ? value : getMessage(msgId, null);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getMessage(String msgId, Object[] params) {
		try {
			getAppResourceBundleMessageSource();
			String value = ServiceLocator.getInstance().getCtx().getMessage(
					msgId, params, new Locale(arm.getLanguage(), arm.getCountry()));
			if(value != null && ObjectUtils.isEmpty(params)) mapMsgs.put(msgId, value);
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	public static Map<String, String> getMapMsgs() {
		return mapMsgs;
	}

	private static Map<String, String> mapMsgs = new HashMap<String, String>();
	private static AppResourceBundleMessageSource arm = null;
	private static AppResourceBundleMessageSource getAppResourceBundleMessageSource() {
		if (arm == null)
			arm = (AppResourceBundleMessageSource) ServiceLocator.getInstance().getCtx().getBean("messageSource");
		return arm;
	}
}
