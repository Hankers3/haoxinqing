package com.aebiz.b2b2c.websiteoperation.websiteoperate.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 模块名称：<br>
 * 1.storeParam 商户系统参数<br>
 * 2.customerParam 会员系统参数<br>
 * 3.orderParam 订单系统参数<br>
 * 4.promotionParam 促销系统参数<br>
 * 5.productParam 商品系统参数<br>
 * 6.productStockParam 商品库存参数<br>
 * 7.systemParam 系统参数<br>
 * 8.vipclubParam 会员俱乐部参数<br>
 * 9.withdrawParam 提现参数<br>
 * 10.basicDataParam 基础数据参数<br>
 * 11.cartManagerParam 购物车参数<br>
 * 12.cmsParam cms参数<br>
 * 13.financeParam 财务系统参数<br>
 * 14.giftCardParam 礼品卡参数<br>
 * 15.contentParam 内容参数<br>
 * 16.returnOrExchangeParam 退换货参数<br>
 * 17.shipmentParam 物流系统参数<br>
 * 18.vipClubParam 会员俱乐部参数<br>
 * 19.virtualAccountParam 虚拟账户参数<br>
 * 20.websiteOperateParam 运营系统参数<br>
 * 21.productPictureSizeParam 商品图片尺寸
 * 
 * @author likun
 * 
 */
public enum ModuleEnum {
	storeParam("1", MessageHelper
			.getMessage("websiteoperate.paramSet.storeParam")), customerParam(
			"2", MessageHelper
					.getMessage("websiteoperate.paramSet.customerParam")), orderParam(
			"3", MessageHelper.getMessage("websiteoperate.paramSet.orderParam")), promotionParam(
			"4", MessageHelper
					.getMessage("websiteoperate.paramSet.promotionParam")), productParam(
			"5", MessageHelper
					.getMessage("websiteoperate.paramSet.productParam")), productStockParam(
			"6", MessageHelper
					.getMessage("websiteoperate.paramSet.productStockParam")), systemParam(
			"7", MessageHelper
					.getMessage("websiteoperate.paramSet.systemParam")), integralParam(
			"8", MessageHelper
					.getMessage("websiteoperate.paramSet.integralParam")), withdrawParam(
			"9", MessageHelper
					.getMessage("websiteoperate.paramSet.withdrawParam")), basicDataParam(
			"10", MessageHelper
					.getMessage("websiteoperate.paramSet.basicDataParam")), cartManagerParam(
			"11", MessageHelper
					.getMessage("websiteoperate.paramSet.cartManagerParam")), cmsParam(
			"12", MessageHelper.getMessage("websiteoperate.paramSet.cmsParam")), financeParam(
			"13", MessageHelper
					.getMessage("websiteoperate.paramSet.financeParam")), giftCardParam(
			"14", MessageHelper
					.getMessage("websiteoperate.paramSet.giftCardParam")), contentParam(
			"15", MessageHelper
					.getMessage("websiteoperate.paramSet.contentParam")), returnOrExchangeParam(
			"16",
			MessageHelper
					.getMessage("websiteoperate.paramSet.returnOrExchangeParam")), shipmentParam(
			"17", MessageHelper
					.getMessage("websiteoperate.paramSet.shipmentParam")), vipClubParam(
			"18", MessageHelper
					.getMessage("websiteoperate.paramSet.vipClubParam")), virtualAccountParam(
			"19", MessageHelper
					.getMessage("websiteoperate.paramSet.virtualAccountParam")), websiteOperateParam(
			"20", MessageHelper
					.getMessage("websiteoperate.paramSet.websiteOperateParam")), productPictureSizeParam(
			"21", MessageHelper
					.getMessage("websiteoperate.paramSet.productPictureSizeParam"));

	private String key;
	private String value;

	private ModuleEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getValue(String key) {
		for (ModuleEnum me : ModuleEnum.values()) {
			if (key.equals(me.key)) {
				return me.value;
			}
		}
		return "";
	}
}
