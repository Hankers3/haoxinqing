package com.aebiz.b2b2c.order.alipcpay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088511207046990";
	
	// 收款支付宝账号
	public static String seller_email = "";
	// 商户收款账号
	public static final String SELLER = "zfb@guwoba.cn";
	// 商户的私钥
	public static String key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAN29LbEcYSawvkb7GR31GRKaiWZFkGErYUhXOfIPFFXRT0Sh1n1BGzWleBwMID+e2jLdVUuOraz31tBMxelyRqANz39PHJQJrEUvDkz+ZPjBimK6cFRQVxvvzg+XXzlGlqwkkRpetqecgNO1FqztoUMGlxtbG+xB9I3e0T1o07RXAgMBAAECgYBGMT6nlD5EyypQuegHjbD0LKtWDqtXty3Cgqo7L/7V875OEZUebV+5qbnN1yDWQuj7tQjtNCtSV9pOch8seRpVtXD8+L+N8IZfm1q6uXaF/D6AT5SqMdPncu2tFlZMs8P2MgPY68ThtSxXI47ZU0XkEVoALL4RaaRYobbFrPZc4QJBAO+nl7waMTxIochXbvzuWjhlKP9kDQcsT2svZl26WwNyUXRqGDTyMrtDNN2EtSs1YxYX4wfOoTUR9dhulSVItn8CQQDs3MZ1KlVgLcLrlZksHmWj3gS32i6TWjeRN5MjC5I/rb7JyzbI1DVhAXK3abq5xMDtPQkau85MnEdY2MfbWIYpAkAaNjpiWHsKHOeVAfshrvAvOuFBDbBfGHmXP5fyqG9L8TT3hqMfJ15lHPSrRdiK4Uv1Hqe2MuNu6of5JB8r9MgBAkBMdE7SzuFT9DFwnY8dgIpKRIW9Wn2iLoK1Kv0hWMDSMSDePevnEyhPsYn3zfbps6qaaRMhP/GtJNyavw5zrLVRAkAfKeBwT3viwllat9fca5rv/N8gNvNaxQMlAE/2MxiUYNSAbApWGWNd9gfhWQJlpkWI3SMUBnp44OrL+PbHN0Iv";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 安全校验码
	public static String netPayKey = "iqe9dlwd0t53523g5rsvk2xy32nzvecx";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";

}
