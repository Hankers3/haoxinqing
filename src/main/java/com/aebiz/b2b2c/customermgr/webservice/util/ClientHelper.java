package com.aebiz.b2b2c.customermgr.webservice.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class ClientHelper {

	/**
	 * webservice 公共方法
	 * */
	public static String invoking(String methodName, String paramJson,
			String url) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(url);
		String returnXml = "";
		try {
			Object[] res = client
					.invoke(methodName, new Object[] { paramJson });
			returnXml = res[0].toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnXml;
	}

}
