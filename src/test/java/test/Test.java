package test;

import org.springframework.web.context.WebApplicationContext;

public class Test {
	public static String APPKEY_CUSTOMER = "";
	
	static{
		APPKEY_CUSTOMER = "aaaaaa";
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
