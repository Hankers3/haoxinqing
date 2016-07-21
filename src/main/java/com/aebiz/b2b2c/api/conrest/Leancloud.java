package com.aebiz.b2b2c.api.conrest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/** 
* @author  作者 E-mail: liuyang
* @date 创建时间：2016年7月1日 下午8:47:50 
* @version 2.0 
* @parameter  
* @since  
* @return  
*/
public class Leancloud {
	
	private static String url = "https://api.leancloud.cn/1.1/classes/_Conversation";
	
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            
            conn.setRequestProperty("X-LC-Id", "Tj74GKRshjbYPHSX3YzJuOQA-gzGzoHsz");
            conn.setRequestProperty("X-LC-Key", "vvs17gXE27ULwVxWQsU8RGv0");
            conn.setRequestProperty("connection", "close");
            conn.setRequestProperty("content-type","application/json");
       
           
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }   
}
