package org.appsugar.controller.shiro;

import java.io.Serializable;

/**
 * 自定义principal
 * @author NewYoung
 * 2016年12月27日下午1:24:40
 */
public class Principal implements Serializable {
	private static final long serialVersionUID = -8422899369911795302L;

	private Long id;

	public Principal() {
		super();
	}

	public Principal(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Principal [id=" + id + "]";
	}

}
