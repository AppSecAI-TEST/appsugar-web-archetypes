package org.appsugar.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 菜单
 * @author NewYoung
 *
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = 1800184616583572970L;
	@JacksonXmlProperty(isAttribute = true)
	private String name;
	@JacksonXmlProperty(isAttribute = true)
	private String code;
	@JacksonXmlProperty(isAttribute = true)
	private String permissions;
	@JsonIgnore
	private transient String[] permissionsArray;

	public Menu() {
		super();
	}

	public Menu(String name, String code, String permissions) {
		super();
		this.name = name;
		this.code = code;
		setPermissions(permissions);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
		if (Objects.nonNull(permissions)) {
			permissionsArray = permissions.split(",");
		}
	}

	public String[] getPermissionsArray() {
		return permissionsArray;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", code=" + code + ", permissions=" + permissions + "]";
	}

}
