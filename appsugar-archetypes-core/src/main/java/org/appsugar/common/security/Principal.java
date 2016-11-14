package org.appsugar.common.security;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @author NewYoung
 * 2016年3月2日上午10:30:09
 */
public class Principal {

	public final Long id;
	public final String name;

	private Map<String, Object> attributes = Maps.newHashMap();

	public Principal(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key) {
		return (T) attributes.get(key);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Principal [id=").append(id).append(", attributes=").append(attributes).append("]");
		return builder.toString();
	}

}
