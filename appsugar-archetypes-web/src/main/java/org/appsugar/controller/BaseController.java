package org.appsugar.controller;

import org.appsugar.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Response<Void> onBindException(BindingResult result) {
		StringBuilder errMsg = new StringBuilder();
		result.getAllErrors().forEach(e -> errMsg.append(e.getDefaultMessage()).append(","));
		String err = errMsg.substring(0, errMsg.length() - 1);
		return Response.error(err);
	}

}
