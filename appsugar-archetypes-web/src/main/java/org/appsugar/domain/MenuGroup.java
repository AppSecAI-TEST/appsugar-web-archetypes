package org.appsugar.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 菜单组
 * @author NewYoung
 *
 */
public class MenuGroup implements Serializable {

	private static final long serialVersionUID = -2213683716071710583L;
	@JacksonXmlProperty(isAttribute = true)
	private String name;
	@JacksonXmlProperty(isAttribute = true)
	private String code;

	@JacksonXmlProperty(localName = "menu")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Menu> menus;

	public MenuGroup() {
		super();
	}

	public MenuGroup(String name, String code, Menu... menus) {
		super();
		this.name = name;
		this.code = code;
		this.menus = new ArrayList<>(Arrays.asList(menus));
	}

	public MenuGroup(String name, String code, List<Menu> menus) {
		super();
		this.name = name;
		this.code = code;
		this.menus = menus;
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

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "MenuGroup [name=" + name + ", code=" + code + ", menus=" + menus + "]";
	}

}
