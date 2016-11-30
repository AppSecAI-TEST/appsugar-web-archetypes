package org.appsugar.controller.filter;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.appsugar.controller.domain.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用户认证拦截器
 * @author NewYoung
 * 2016年11月17日下午4:53:34
 */
public class FormAuthenticationFilterExt extends FormAuthenticationFilter {

	private byte[] content;

	{
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectMapper om = new ObjectMapper();
			om.writer().writeValue(out, Response.UN_AUTHENTICATION);
			content = out.toByteArray();
			out.close();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		response.getOutputStream().write(this.content);
		return false;
	}

}
