package com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 会员积分枚举类型
 * 
 * @author huyingying
 * 
 */
public enum IntegralType {
	/* 1 代表 每天登陆操作 */
	GET_BY_ORDER("1", MessageHelper.getMessage("vipclubintegrallog.intergralType.get_by_order")),
	
	/* 2 代表开通电话咨询服务 */
	OPEN_MOBILE("2", MessageHelper.getMessage("vipclubintegrallog.intergralType.open_mobile")),
	/* 3代表开通预约加号服务 */
	OPEN_PLUS("3", MessageHelper.getMessage("vipclubintegrallog.intergralType.open_plus")),
	/* 4代表首次完善个人资料 */
	GET_BY_REGISTER("4", MessageHelper.getMessage("vipclubintegrallog.intergralType.get_by_register")),
	/* 其他 */
	REDUCE_BY_PAY("5", MessageHelper.getMessage("vipclubintegrallog.intergralType.reduce_by_pay")),
	// 图文回复
	PIC_REPLY("6", MessageHelper.getMessage("vipclubintegrallog.intergralType.pic_reply")),
	// 7创建病例
	ADD_MEDICALRECORD("7", MessageHelper.getMessage("vipclubintegrallog.intergralType.add_medicalrecord")),
	// 8添加随访患者
	ADD_VISITRECORD("8", MessageHelper.getMessage("vipclubintegrallog.intergralType.add_visitrecord")),
	// 9视频课程
	ADD_SHARE_BY_VIDEO("9", MessageHelper.getMessage("vipclubintegrallog.intergralType.add_share_by_video")),
	// 10文献文章
	ADD_SHARE_BY_ARTICLE("10", MessageHelper.getMessage("vipclubintegrallog.intergralType.add_share_by_article")),
	// 11完成测试题
	ADD_QUIZ("11", MessageHelper.getMessage("vipclubintegrallog.intergralType.add_quiz")),
	//12 发布公告
	ADD_GG("12", MessageHelper.getMessage("vipclubintegrallog.intergralType.add_gonggao")),
	//13 查看资讯
	VIEW_CONTENT("13", MessageHelper.getMessage("vipclubintegrallog.intergralType.view_content")),
	//14 观看视频
	VIEW_VIDEO("14", MessageHelper.getMessage("vipclubintegrallog.intergralType.view_video")),
	//平台增加
	GET_BY_PLATEFORM("15", MessageHelper.getMessage("vipclubintegrallog.intergralType.get_by_plateform")),
	//平台减少
	REDUCE_BY_PLATEFORM("16", MessageHelper.getMessage("vipclubintegrallog.intergralType.reduce_by_plateform"));
	
	private String value = "";
	/* 积分类型名称 */
	private String name = "";

	private IntegralType(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * 获取方法
	 * 
	 * @paparm value
	 * @return name
	 */
	public static String getNameByKey(String value) {
		for (IntegralType c : IntegralType.values()) {
			if (c.getValue().equals(value)) {
				return c.getName();
			}
		}
		return "";
	}
}
