package com.aebiz.b2b2c.customermgr.mobile.web.pub;


/**
 * 类描述：公共常量数据类
 *
 * @version 1.0
 * @author: dgh
 * @date： 日期：2013-11-14 时间：下午01:26:56
 */
public abstract class SysConst {

    public static String DEFAULT_CHARACTOR_ENCONDING = "UTF-8";

    public static String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");

    public static String JSON_TYPE = "text/javasctipt";


    /***********************
     * 返回结果编码表
     ***************************/

    //成功返回结果编码
    public static String SUCCESS = "1";

    //失败返回结果编码
    public static String FAIL = "0";

    //成功返回结果信息
    public static String SUCCESSMESSAGE = "操作成功";

    //成功返回结果信息
    public static String FAILMESSAGE = "操作失败";

    //用户id不能为空
    public static String USERID_IS_NOT_NULL = "用户id不能为空";

    //用户id不能为空
    public static String USER_REQUIRED = "用户id不能为空";

    //用户不存在
    public static String CUSTOMER_IS_NO_EXIST = "用户不存在";

    //用户id不能为空
    public static String MOBILE_IS_NOT_NULL = "手机号不能为空";
}
