package com.aebiz.b2b2c.userfront.common;

public class UserFrontCacheConstant {

	/* 广告系统，名称 */
	public static final String USERFRONT_SYS_NAME = "userfront";

	/* 广告系统，缓存客户端名称 */
	public static final String USERFRONT_CLIENT_NAME = "memCachedClient";
	
	/* 广告的缓存key */
	public static final String USERFRONT_PLATAD_KEY = USERFRONT_SYS_NAME
			+ "/platad";
	
	/* 图片库分类的缓存key */
	public static final String USERFRONT_PLATIMAGECATEGORY_KEY = USERFRONT_SYS_NAME
			+ "/platimagecategory";
	
	/* 图片的缓存key */
	public static final String USERFRONT_PLATIMAGE_KEY = USERFRONT_SYS_NAME
			+ "/plattimagelibrary";
	
	/* 广告关联图片的缓存key */
	public static final String USERFRONT_PLATADIMAGEREL_KEY = USERFRONT_SYS_NAME
			+ "/platadimagerel";
	
	/* 广告关联图片的缓存listkey */
	public static final String USERFRONT_PLATADIMAGEREL_KEY_LIST = USERFRONT_SYS_NAME
			+ "/platadimagerellist";
	
}
