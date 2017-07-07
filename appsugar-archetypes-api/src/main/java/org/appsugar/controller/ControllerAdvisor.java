package org.appsugar.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.appsugar.controller.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.common.base.Throwables;

@RestControllerAdvice
public class ControllerAdvisor {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	/**
	 * 用户未授权
	 */
	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public Response<Void> onAuthorization() {
		return Response.UN_AUTHORIZATION;
	}

	/**
	 * 服务器内异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response<Void> onException(Exception ex) {
		logger.error("internal exception ", ex);
		return Response.error(Throwables.getRootCause(ex).getMessage());
	}
}
