package com.aebiz.b2b2c.cms.messageremind.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;


/**
 * 站内消息类型
 * 1:卖家提醒
 * 2:退款提醒
 * 3:促销提醒
 * 4:买家提醒
 * @author huyingying
 *
 */
public enum MessageType {
		
		// 1: 卖家提醒
		SELLERREMIND("1", MessageHelper.getMessage("messageremind.messageType.sellerremind")),

		// 2：退款提醒
		TEFUNDREMIND("2", MessageHelper.getMessage("messageremind.messageType.refundremind")),
		
		// 3:促销提醒
		PROMOTIONREMIND("3", MessageHelper.getMessage("messageremind.messageType.promotionremind")),

		// 4：买家提醒
		BUYERREMIND("4", MessageHelper.getMessage("messageremind.messageType.buyerremind"));

		private String value = "";

		private String name = "";

		private MessageType(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		/**
		 * 对外提供枚举方法
		 * 
		 * @param value
		 * @return String
		 */
		public static String getNameByValue(String value) {
			for (MessageType p : MessageType.values()) {
				if (p.getValue().equals(value)) {
					return p.getName();
				}
			}
			return "";
		}
}
