package org.appsugar.controller.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 通用响应
 * @author NewYoung
 * 2016年11月9日上午10:57:04
 */
@JsonInclude(Include.NON_NULL)
public class Response {
	//codes
	public static final int SUCCESS_CODE = 0;
	public static final int ERROR_CODE = -1;
	public static final int UN_AUTHENTICATION_CODE = 401;
	public static final int UN_AUTHORIZATION_CODE = 403;
	public static final int INTERNAL_EXCEPTION_CODE = 503;
	//msgs
	public static final String SUCCESS_MSG = "success";
	public static final String ERROR_MSG = "error";
	public static final String UN_AUTHENTICATION_MSG = "unAuthentication";
	public static final String UN_AUTHORIZATION_MSG = "unAuthorization";
	public static final String INTERNAL_EXCEPTION_MSG = "internalException";

	//操作成功
	public static final Response SUCCESS = new Response(SUCCESS_CODE, SUCCESS_MSG);
	//操作失败
	public static final Response ERROR = new Response(ERROR_CODE, ERROR_MSG);
	//用户未认证
	public static final Response UN_AUTHENTICATION = new Response(UN_AUTHENTICATION_CODE, UN_AUTHENTICATION_MSG);
	//用户未授权
	public static final Response UN_AUTHORIZATION = new Response(UN_AUTHORIZATION_CODE, UN_AUTHORIZATION_MSG);
	//系统内部错误
	public static final Response INTERNAL_EXCEPTION = new Response(INTERNAL_EXCEPTION_CODE, INTERNAL_EXCEPTION_MSG);

	private int code;
	private String msg;
	private Object data;

	public Response(int code, String msg) {
		this(code, msg, null);
	}

	public Response(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 构建一个通用成功的响应
	 */
	public static Response success(Object data) {
		return new Response(SUCCESS_CODE, SUCCESS_MSG, data);
	}

	/**
	 * 构建一个通用失败响应
	 */
	public static Response error(String msg) {
		return new Response(ERROR_CODE, msg);
	}

	/**
	 * 构建一个失败响应
	 */
	public static Response error(int code, String msg) {
		return new Response(code, msg);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [code=").append(code).append(", msg=").append(msg).append(", data=").append(data)
				.append("]");
		return builder.toString();
	}

}
