package com.hxq.mobile.util.email;

import java.util.HashMap;
import java.util.Map;

/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年6月17日 上午9:48:34 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
public class MailTemplate {
/*	private MailTemplate(){
		
	}
	private static MailTemplate mailTemplate = null;
	//单例模式
	public static MailTemplate getMailTemplate(){
		if(mailTemplate == null){
			synchronized (mailTemplate) {
				if(mailTemplate == null){
					mailTemplate = new MailTemplate();
				}
			}
		}
		return mailTemplate;
	}*/
	
	public static Map<String, String> map = new HashMap<String, String>();
	
	public static 	StringBuffer context = new StringBuffer();
	
	static{
	
		context.append("<!DOCTYPE html><html lang=\"en\"><head>");
		context.append("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf8\">");
		context.append("</head><body><div style=\"color:#002000;background: #FFFAFA\">");

		context.append("<h1 align=\"center\">%title%</h1>");
		context.append(" <p>%content%</p>");
		context.append("</div>");
		context.append("</body>");
		context.append("</html>");
		
		map.put("template", context.toString());
	}
	
	/**
	 * 根据key从 map中取出value
	 * @param key
	 * @return
	 */
	public static String getContext(String key){
		return map.get(key);
	}
	
}
