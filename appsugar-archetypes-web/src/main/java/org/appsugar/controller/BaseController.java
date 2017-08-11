package org.appsugar.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.appsugar.bean.domain.Page;
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

	/**
	 * 转换page包涵的数据对象
	 */
	protected <T, S> Page<T> transfer(Page<S> page, Function<S, T> function) {
		List<S> data = page.getContent();
		@SuppressWarnings("unchecked")
		Page<T> result = (Page<T>) page;
		result.setContent(data.stream().map(function).collect(Collectors.toList()));
		return result;
	}

	/**
	 * list转换
	 * @param data
	 * @param function
	 * @return
	 */
	protected <T, S> List<T> transfer(List<S> data, Function<S, T> function) {
		return data.stream().map(function).collect(Collectors.toList());
	}
}
