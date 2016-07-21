package com.aebiz.b2b2c.cms.content.utils;

import com.alibaba.druid.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by sunchao on 15/12/29 and { 写文件工具类 }
 */
public class WriteFileUtils {

    //写文件，支持中文字符，在linux redhad下测试过
    public static void writeLog(String str) {

        try {
            String path = getFilePath() + "outsource_timestamp.log";
            File file = new File(path);
            if (!file.exists()) file.createNewFile();
            FileOutputStream out = new FileOutputStream(file, false); //如果追加方式用true
            StringBuffer sb = new StringBuffer();
            sb.append(str + "\n");
            out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    public static String readLog() {
        StringBuffer sb = new StringBuffer();
        String tempstr = null;
        try {
            String path = getFilePath() + "outsource_timestamp.log";
            File file = new File(path);
            if (!file.exists()) throw new FileNotFoundException();
            //读取
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while ((tempstr = br.readLine()) != null)
                sb.append(tempstr);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        return sb.toString();
    }

    public static String getFilePath() {
        URL classpathURI = WriteFileUtils.class.getClassLoader().getResource("");
        String classpath = classpathURI.getPath();
        //去掉classes
        int index = classpath.indexOf("classes");
        classpath = classpath.substring(0, index);
        return classpath;
    }

    public static void writeTimestamp(String timestamp) {
        writeLog("ymttimestamp:" + timestamp);
    }

    public static String loadTimestamp() {
        String log = readLog();
        if (StringUtils.isEmpty(log)) return "";
        int index = log.indexOf(":") + 1;
        return log.substring(index);
    }
}
