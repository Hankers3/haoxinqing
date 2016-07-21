package com.aebiz.b2b2c.customermgr.mobile.web.pub;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;

import fm.last.moji.spring.SpringMojiBean;

/**
 * 类描述：手机端工具类
 * 
 * @author: dgh
 * @date： 日期：2014-12-27 时间：下午01:45:29
 * @version 1.0
 */
public class MobileUtils {

	private static Logger log = LoggerFactory.getLogger(FileUploadHelper.class);
	@Autowired
	private FileService fileService;
	@Autowired
	private SpringMojiBean moji;
	// 编号类型订单
	public static final String INVITE_CODE = "inviteCode";
	
	@Autowired
	private AccountKeyService accountKeyService;

	/**
	 * 通过一个时间获取该时间对应的星期
	 * 
	 * @param time
	 * @return
	 */
	public static String getWeekByTime(String time) {
		// y 年
		// m 月
		// d 日
		int y = Integer.parseInt(time.substring(0, 4));
		int m = Integer.parseInt(time.substring(5, 7));
		int d = Integer.parseInt(time.substring(8, 10));
		if (m == 1) {
			m = 13;
			y--;
		}
		if (m == 2) {
			m = 14;
			y--;
		}
		int week = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
		String weekstr = "";
		switch (week) {
		case 0:
			weekstr = "星期一";
			break;
		case 1:
			weekstr = "星期二";
			break;
		case 2:
			weekstr = "星期三";
			break;
		case 3:
			weekstr = "星期四";
			break;
		case 4:
			weekstr = "星期五";
			break;
		case 5:
			weekstr = "星期六";
			break;
		case 6:
			weekstr = "星期日";
			break;
		}
		return weekstr;
	}

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 * @param lat1坐标1的纬度
	 * @param lng2
	 * @param lat2坐标2的纬度
	 * @return
	 */
	public static double GetDistance(double lng1, double lat1, double lng2,
			double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	// //base64字符串转化成图片
//	public static String GenerateImage(String imgStr) { //
//		// 对字节数组字符串进行Base64解码并生成图片
//		if (imgStr == null) // 图像数据为空
//			return "";
//		BASE64Decoder decoder = new BASE64Decoder();
//		try {
//			// Base64解码
//			byte[] b = decoder.decodeBuffer(imgStr);
//			for (int i = 0; i < b.length; ++i) {
//				if (b[i] < 0) {// 调整异常数据
//					b[i] += 256;
//				}
//			}
//			// 生成jpeg图片
//			String imgFilePath = "d://222.jpg";// 新生成的图片
//			OutputStream out = new FileOutputStream(imgFilePath);
//			out.write(b);
//			out.flush();
//			out.close();
//			return imgFilePath;
//		} catch (Exception e) {
//			return "";
//		}
//	}
	
	
	// 对字节数组字符串进行Base64解码并返回byte[]
	public static byte[] GenerateImage(String imgStr) { 
		if (imgStr == null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			return b;
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 创建生成邀请码
	 * @return
	 * @throws Exception
	 */
//	public String createInviteCode(){
//		//随机数为5位
//		int digit = 5;
//		synchronized (INVITE_CODE) {
//			// 获取随机数
//			StringBuffer key = new StringBuffer("");
//
//			if (digit == 0 || !StringUtil.isEmpty("")) {
//				key = new StringBuffer("");
//			} else {
//				key = this.getRandomCodes(digit);
//			}
//
//			// 获取随机数表的值
//			AccountKeyModel accountKey = accountKeyService.getAccountKeyModelById(INVITE_CODE);
//			if (accountKey != null) {
//				key.append(accountKey.getValue());
//				accountKey.setValue((Integer.parseInt(accountKey.getValue()) + 1) + "");
//				accountKeyService.update(accountKey);
//			}
//			return key.toString();
//		}
//
//	}

	/**
	 * 获取随机数
	 * @param digit
	 * @return
	 */
	public static StringBuffer getRandomCodes(int digit) {
		String[] source = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "g", "k", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
				"y", "z" };
		Random rdm = new Random();
		StringBuffer head = new StringBuffer();
		for (int i = 0; i < digit; i++)
			head.append(source[(rdm.nextInt() >>> 1) % 36]);
		return head;
	}
	
	/**
	 * double转int
	 * @param d
	 * @return
	 */
	public static int getIntFromDouble(double d){
		BigDecimal b = new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP);
		int i = Integer.parseInt(b.toString());
		return i;
	}
	/**
	 * 获取今天之前i天的星期与日期
	 * @param i 今天前i天
	 * @return 今天之前i天的星期与日期，格式为："星期四 2015-03-05"
	 */
		public static String getWeekAndDate(int i)
		{
			//获得当前日期   传入int类型的
			String date = getDateBeforeToday(i);
			//获得当前星期
			String week = getWeekByTime(date);
			//拼接起来中间用空格隔开,并截取date0-10个
			return week + " " +date.substring(0,10);
			
		}
		/**
		 * 获取今天之前i天的星期与日期
		 * @param i
		 * @return 今天之前i天的日期，格式为：2015-03-04 16:23:09
		 */
		public static String getDateBeforeToday(int i)
		{
			 Date date = DateFormatHelper.getDateBefore(new Date(),i);
			 
			 return DateFormatHelper.getTimeStr(date);
		}
		/**金额格式化
		 * 
		 */
		public static double format(double price) {
			DecimalFormat formatter = new DecimalFormat("0.00");
			return Double.parseDouble(formatter.format(price));
		}
		
		/**
		 * 根据http请求获取IP地址
		 * @param request
		 * @return
		 */
		public static String getIpAddr(HttpServletRequest request) {
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Real-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
		/**
		 * 
		 * @param operate
		 * @param paramInfos
		 * @param paramNames
		 * @param baseModel
		 * @return
		 * @2015-4-21
		 * @author :SZH
		 */
		public static boolean setOrGetModelParams(String operate,String[] paramInfos,String[] paramNames,BaseModel baseModel){
			for(int i=0;i<paramInfos.length;i++)
			{
				if(!setOrGetModelParam(operate,paramInfos[i],paramNames[i],baseModel))
				{
					return false;
				}
			}
			return true;
		}
		/**
		 * 根据传入的字段和entities的对象执行对应的set或者get方法
		 * @param operate get/set
		 * @param paramInfo 参数值
		 * @param param 参数名
		 * @param baseModel entities
		 * @return
		 * @2015-4-15
		 * @author :SZH
		 */
		public static boolean setOrGetModelParam(String operate,String paramInfo,String paramName,BaseModel baseModel){
			//获取操作，set或者get
			StringBuffer operation = new StringBuffer(operate);
			//将字段名首字母转为大写
			paramName = lowToUpper(paramName);
			//拼接要执行的方法
			String methodName = operation.append(paramName).toString();
			//获取对象的类的class对象
			Class baseClazz = baseModel.getClass();
			try {
				//获取方法
				Method operatModel = baseClazz.getMethod(methodName,String.class);
				//执行方法
				operatModel.invoke(baseModel, paramInfo);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		/**
		 * 将字段名的第一个字母转为大写,其它字母为小写
		 * @param param
		 * @return
		 * @2015-4-21
		 * @author :SZH
		 */
		public static String lowToUpper(String param){
			//把字段全部转为小写
			param = param.toLowerCase();
			//把字段首字母转为大写
			String lowStrFirst = param.substring(0, 1);
			String upperStrFirst = lowStrFirst.toUpperCase();
			StringBuffer newParam = new StringBuffer(upperStrFirst);
			return newParam.append(param.substring(1,param.length())).toString();
		}
}


