package com.aebiz.b2b2c.cms.messageremind.vo;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 站内消息所属类型
 * store:站内消息属于商户
 * customer:站内消息属于会员
 * @author Administrator
 *
 */
public enum AccountType {
	
		// store:商户
		STORE("store", MessageHelper.getMessage("messageremind.accountType.store")),

		// customer:会员
		CUSTOMER("customer", MessageHelper.getMessage("messageremind.accountType.customer"));

		private String value = "";

		private String name = "";

		private AccountType(String value, String name) {
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
			for (AccountType p : AccountType.values()) {
				if (p.getValue().equals(value)) {
					return p.getName();
				}
			}
			return "";
		}
}
