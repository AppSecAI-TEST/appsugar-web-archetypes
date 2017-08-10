package org.appsugar.controller.domain;

import java.util.ArrayList;
import java.util.List;

import org.appsugar.domain.Menu;
import org.appsugar.domain.MenuGroup;
import org.appsugar.util.MenuUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;

import junit.framework.TestCase;

/**
 * 菜单序列化测试
 * @author NewYoung
 *
 */
public class MenuSerializationTest extends TestCase {
	private static final Logger logger = LoggerFactory.getLogger(MenuSerializationTest.class);

	@Test
	public void testSerialization() throws Exception {
		Menu user = new Menu("user", "user.management", "user:view");
		Menu role = new Menu("role", "role.management", "role:view");
		MenuGroup group = new MenuGroup("系统管理", "system.management", user, role);
		XmlMapper mapper = new XmlMapper();
		String value = mapper.writeValueAsString(Lists.newArrayList(group));
		ArrayList<MenuGroup> groups = mapper.readerFor(new TypeReference<ArrayList<MenuGroup>>() {
		}).readValue(value);
		logger.debug("serilization value is {} groups is {}", value, groups);
	}

	@Test
	public void testDeserialization() throws Exception {
		List<MenuGroup> groups = MenuUtils.readMenuGroup();
		logger.debug("menu group is {}", groups);
	}
}
