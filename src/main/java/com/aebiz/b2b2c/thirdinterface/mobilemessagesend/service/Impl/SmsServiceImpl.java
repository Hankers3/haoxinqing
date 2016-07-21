package com.aebiz.b2b2c.thirdinterface.mobilemessagesend.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.thirdinterface.mobilemessage.service.MobileMessageService;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageModel;
import com.aebiz.b2b2c.thirdinterface.mobilemessagesend.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {
	@Autowired
	private MobileMessageService mobileMessageService;

	/**
	 * 发送短信接口
	 * 
	 * @param mobile
	 *            发送手机号
	 * @param message
	 *            发送的信息内容
	 */
	public void sendSms(String mobile, String message){
		MobileMessageModel m = this.mobileMessageService.getModel();
		if (m != null) {
			try {
				StringBuilder sb = new StringBuilder();
				sb.append("sname=").append(m.getUserId());
				sb.append("&spwd=").append(m.getPassword());
				sb.append("&scorpid=").append(m.getScorpid());
				sb.append("&sprdid=").append(m.getSprdid());
				sb.append("&sdst=").append(mobile);
				String msg = URLEncoder.encode(message, "utf-8");
				sb.append("&smsg=" + msg);

				send(sb.toString(), m.getUrl());
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String send(String postData, String postUrl) {
		try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            readStringXml(result);
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
	}
	
	public static void readStringXml(String xml) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);// 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			String msgState = rootElt.elementTextTrim("MsgState"); // 拿到head下的子节点script下的字节点username的值
			String reserve = rootElt.elementTextTrim("Reserve"); // 拿到head下的子节点script下的字节点username的值
			System.out.println("发送结果状态:" + reserve);
			System.out.println("发送结果信息:" + msgState);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
