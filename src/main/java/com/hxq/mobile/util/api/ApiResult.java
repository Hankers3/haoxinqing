package com.hxq.mobile.util.api;

/**
 * 用于APP访问接口返回值：成功（成功信息）或失败（失败信息）。
 */
public final class ApiResult {

	/**
	 * 返回结果代码
	 */
	private int code = 200;
	/**
	 * 返回结果对象
	 */
	private Object value = null;
	/**
	 * 返回错误提示信息
	 */
	private String message = null;

	private ApiResult(int code, Object value, String message) {
		this.code = code;
		this.value = value;
		this.message = message;
	}

	/**
	 * 返回正确，无结果
	 * @return
	 */
	public static ApiResult right() {
		return new ApiResult(ApiCode.OK.getIndex(), null, null);
	}

	/**
	 * 返回正确，有结果
	 * @param result 结果内容
	 * @return
	 */
	public static ApiResult right(Object result) {
		return new ApiResult(ApiCode.OK.getIndex(), result, null);
	}

	/**
	 * 返回错误信息，无错误信息时使用默认代码信息
	 * @param code 错误代码
	 * @param message 文字表述的错误信息
	 * @return
	 */
	public static ApiResult error(ApiCode code, String message) {
		return new ApiResult(code.getIndex(), null,
			(message==null || message.trim().length() < 1) ? code.getName() : message.trim());
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}