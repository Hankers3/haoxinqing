package com.hxq.mobile.util.page;

public enum ConditionOpTypeEnum {
	EQ(1), Like(2), LeftLike(3), RightLike(4), GE(5), LT(6), GT(7), IN(8);

	private int code = 0;

	private ConditionOpTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
