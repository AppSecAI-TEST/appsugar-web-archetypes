package org.appsugar.controller;

import java.util.List;

import org.appsugar.bean.domain.Page;

/**
 * 基础controller
 * @author NewYoung
 * 2016年11月30日下午4:16:36
 */
public abstract class BaseController {

	/**
	 * page数据转换
	 * @author NewYoung
	 * 2016年11月30日下午4:18:23
	 */
	@SuppressWarnings("unchecked")
	protected <T> Page<T> pageTransfer(Page<?> page, List<T> data) {
		Page<T> newPage = (Page<T>) page;
		newPage.setContent(data);
		return newPage;
	}
}
