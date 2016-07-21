package com.hxq.mobile.util.api;

public enum ApiCode {
	/**
	 * 正确响应
	 */
	OK("OK", 200),

	/**
	 * 客户端提交参数不正确
	 */
	BAD_REQUEST("Bad request", 400),

	/**
	 * 用户未开通服务
	 */
	FORBIDDEN("Forbidden", 403),

	/**
	 * 没有找到请求资源
	 */
	NOT_FOUND("Not found", 404),

	/**
	 * 服务器异常
	 */
	SERVER_ERROR("Internal Server Error", 500);

    private int index;
    private String name;
    private ApiCode(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public String getName() {
        return name;
    }
	public int getIndex() {
		return index;
	}
}
