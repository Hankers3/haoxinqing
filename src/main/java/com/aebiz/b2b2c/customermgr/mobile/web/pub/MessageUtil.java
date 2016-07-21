package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 类描述：信息工具类
 * @author: dgh
 * @date： 日期：2014-12-27 时间：下午03:52:44
 * @version 1.0
 */
public class MessageUtil {

	public static String sendSms(String mobile, String message, String msgId) {

		URL url = null;
		InputStream in = null;
		BufferedReader br = null;
		String resultStr = null;
		try {
			String realUrl = genRealUrl(mobile, message, msgId);
			System.out.println(realUrl);
			url = new URL(realUrl);
			URLConnection conn = url.openConnection();
			in = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			resultStr = sb.toString().trim();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "-1";
		} catch (IOException e) {
			e.printStackTrace();
			return "-1";
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return resultStr;
	}

	private static String genRealUrl(String mobile, String message, String msgId) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder("http://121.52.221.108/send/gsend.aspx");

		sb.append("?name=").append("eas-mhcy");
		sb.append("&pwd=").append("kingdee168");
		sb.append("&dst=").append(mobile);
		sb.append("&msg=" + message);
		sb.append("&sequeid=").append(msgId);

		// String msg = URLEncoder.encode(message, "gb2312");
		sb.append("&message=" + message);
		return sb.toString();
	}

}
