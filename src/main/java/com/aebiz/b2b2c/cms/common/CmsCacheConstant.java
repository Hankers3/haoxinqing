package com.aebiz.b2b2c.cms.common;

/**
 * 
 * CMS系统的memcached的参数设置 <br />
 * 
 * CMS系统的key设置
 * 
 * @author linqiang
 * 
 */
public class CmsCacheConstant {

	/* CMS系统， 名称 */
	public static final String CMS_SYS_NAME = "cms";

	/* CMS系统， 缓存客户端名称 */
	public static final String CMS_MEM_CLIENT_NAME = "memCachedClient";

	/* 频道的缓存key */
	public static final String CMS_CHANNEL = CMS_SYS_NAME + "/channel";
	
	/* 频道UUID集合的缓存key */
	public static final String CMS_CHANNEL_UUIDS = CMS_SYS_NAME + "/channeluuids";

	/* 服务权益的缓存key */
	public static final String CMS_CONSUME_PROTECTION = CMS_SYS_NAME
			+ "/consumeprotection";
	
	/* 服务权益UUID集合的缓存key */
	public static final String CMS_CONSUME_PROTECTION_UUIDS = CMS_SYS_NAME
			+ "/consumeprotectionuuids";

	/* 内容的缓存key */
	public static final String CMS_CONTENT = CMS_SYS_NAME + "/content";

	/* 内容分类UUID集合的缓存key */
	public static final String CMS_CONTENT_UUIDS = CMS_SYS_NAME
			+ "/contentuuids";

	/* 内容分类的缓存key */
	public static final String CMS_CONTENT_CATEGORY = CMS_SYS_NAME
			+ "/contentcategory";

	/* 内容分类UUID集合的缓存key */
	public static final String CMS_CONTENT_CATEGORY_UUIDS = CMS_SYS_NAME
			+ "/contentcategoryuuids";

	/* 站内信的缓存key */
	public static final String CMS_INNER_MESSAGE = CMS_SYS_NAME
			+ "/innermessage";

	/* 网站提醒的缓存key */
	public static final String CMS_MESSAGE_REMIND = CMS_SYS_NAME
			+ "/messageremind";

	/* 订单评价的缓存key */
	public static final String CMS_ORDER_MAIN_APPRAISE = CMS_SYS_NAME
			+ "/ordermainappraise";

	/* 晒单的缓存key */
	public static final String CMS_ORDER_SHOW = CMS_SYS_NAME + "/ordershow";

	/* 晒单展示图片的缓存key */
	public static final String CMS_ORDER_SHOW_PIC = CMS_SYS_NAME
			+ "/ordershowpic";

	/* 商品评价的缓存key */
	public static final String CMS_PRODUCT_APPRAISE = CMS_SYS_NAME
			+ "/productappraise";

	/* 商品权益关联的缓存key */
	public static final String CMS_PROTECTION_PRODUCT_REL = CMS_SYS_NAME
			+ "/protectionproductrel";
	
	/* 商品权益关联UUID集合的缓存key */
	public static final String CMS_PROTECTION_PRODUCT_REL_UUIDS = CMS_SYS_NAME
			+ "/protectionproductreluuids";

	/* 商户权益关联的缓存key */
	public static final String CMS_PROTECTION_STORE_REL = CMS_SYS_NAME
			+ "/protectionstorerel";

	/* 用户消息提醒的缓存key */
	public static final String CMS_REMIND_SUB = CMS_SYS_NAME + "/remindsub";

	/* 商户标签的缓存key */
	public static final String CMS_STORE_TAGS = CMS_SYS_NAME + "/storetags";
	
	/* 商户下商户标签的缓存key */
	public static final String CMS_STORE_TAGS_STORE = CMS_SYS_NAME + "/storetagsstore";

	/* 商户标签会员关联的缓存key */
	public static final String CMS_STORE_TAGS_CUSTOMER = CMS_SYS_NAME
			+ "/storetagscustomer";

	/* 标签的缓存key */
	public static final String CMS_TAGS = CMS_SYS_NAME + "/tags";

	/* 标签分类的缓存key */
	public static final String CMS_TAGS_CATEGORY = CMS_SYS_NAME
			+ "/tagscategory";

	/* 标签、标签分类关系的缓存key */
	public static final String CMS_TAGS_CATEGORY_REL = CMS_SYS_NAME
			+ "/tagscategoryrel";
	
	/* 视频key */
	public static final String CMS_PLAT_FROM_INFO= CMS_SYS_NAME
			+ "/platfrominfo";
	
	/* 视频Uuids  key */
	public static final String CMS_PLAT_FROM_INFO_UUIDS= CMS_SYS_NAME
			+ "/platfrominfouuids";
	/* 视频时间  key */
	public static final String CMS_PLAT_FROM_INFO_TIME= CMS_SYS_NAME
			+ "/platfrominfotime";
	
	
	/* 视频互动*/
	public static final String CMS_PLAT_FROM_COMM= CMS_SYS_NAME
			+ "/platformcommunication";
	
	/* 视频互动Uuids  key */
	public static final String CMS_PLAT_FROM_COMM_UUIDS= CMS_SYS_NAME
			+ "/platformcommunicationuuids";
	
}
