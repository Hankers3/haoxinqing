package com.aebiz.b2b2c.websiteoperation.common;

/**
 * 
 * 运营系统关于缓存的常量
 * 
 * 1.运营系统缓存客户端名称<br />
 * 
 * 2.运营系统缓存的KEY<br />
 * 
 * @author duandeyi
 * 
 */
public class WebsiteOperateCacheConstant {

	/* 运营系统，名称 */
	public static final String WEBSITE_OPERATE_SYS_NAME = "websiteoperate";

	/* 运营系统，缓存客户端名称 */
	public static final String WEBSITE_OPERATE_CLIENT_NAME = "memCachedClient";

        /* 关注的缓存key */
        public static final String WEBSITE_CONCERN_KEY = WEBSITE_OPERATE_SYS_NAME
                        + "/concernkey";
        /* 关注的缓存key */
        public static final String WEBSITE_CONCERN_KEY_UUIDS = WEBSITE_OPERATE_SYS_NAME
                + "/concernuuidkey";

	/* 运营参数的缓存key */
	public static final String WEBSITE_OPERATE_RESOURCE_KEY = WEBSITE_OPERATE_SYS_NAME
			+ "/resourcekey";

	/* 运营设置的缓存key */
	public static final String WEBSITE_OPERATE_WB = WEBSITE_OPERATE_SYS_NAME
			+ "/wb";

	/* 问卷的缓存key */
	public static final String WEBSITE_OPERATE_QUESTIONNAIRE = WEBSITE_OPERATE_SYS_NAME
			+ "/questionnaire";

	/* 系统设置缓存key */
	public static final String WEBSITE_OPERATE_PLATCONFIG = WEBSITE_OPERATE_SYS_NAME
			+ "/platconfig";
	/* 用户咨询缓存key */
	public static final String WEBSITE_CUSTOMERQUIZ_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/customerquiz";
	/* 用户收藏的缓存key */
	public static final String WEBSITE_FAVORITE_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/favorite";
	/* 用户收藏的缓存key */
	public static final String WEBSITE_FAVORITE_UUIDS_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/favoriteuuids";
	/* 用户选项的缓存key */
	public static final String WEBSITE_OPTIONS_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/options";
	/* 用户选项的uuid缓存key */
	public static final String WEBSITE_OPTIONS_UUIDS_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/optionsuuids";
	/* 调查问卷的缓存的key */
	public static final String WEBSITE_QUESTIONNAIREANSWER_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/questionnaireanswer";
	/* 提问的缓存的key */
	public static final String WEBSITE_QUESTIONS_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/questions";
	/* 提问的uuids缓存的key */
	public static final String WEBSITE_QUESTIONS_UUIDS_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/questionsuuid";
	/* 提问关联的缓存的key */
	public static final String WEBSITE_QUESTIONSREL_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/questionsrel";
	/* 提问分类的缓存的key */
	public static final String WEBSITE_QUIZCATEGORY_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/quizcategory";
	/* 提问分类的姓名缓存的key */
	public static final String WEBSITE_QUIZCATEGORY_NAME_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/quizcategoryname";
	/* 咨询结果的缓存的key */
	public static final String WEBSITE_QUIZRESULT_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/quizresult";
	/* 咨询结果的uuid缓存的key */
	public static final String WEBSITE_QUIZRESULT_UUID_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/quizresultuuid";
	/* 咨询结果关联的缓存的key */
	public static final String WEBSITE_QUIZRESULTDOCREL_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/quizresultdocrel";
	/* 咨询结果关联的uuid的缓存的key */
	public static final String WEBSITE_QUIZRESULTDOCREL_UUID_KEY = WEBSITE_OPERATE_SYS_NAME
	        + "/quizresultdocreluuid";
}
