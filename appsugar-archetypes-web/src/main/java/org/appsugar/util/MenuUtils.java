package org.appsugar.util;
/**
 * 菜单辅助
 * @author NewYoung
 *
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.appsugar.domain.MenuGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class MenuUtils {
	private static List<MenuGroup> cache = null;

	public static List<MenuGroup> readMenuGroup() {
		try (InputStream in = MenuUtils.class.getClassLoader().getResourceAsStream("menu.xml")) {
			XmlMapper mapper = new XmlMapper();
			ArrayList<MenuGroup> groups = mapper.readerFor(new TypeReference<ArrayList<MenuGroup>>() {
			}).readValue(in);
			return groups;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<MenuGroup> getCachedMenuGroup() {
		if (Objects.isNull(cache)) {
			synchronized (MenuUtils.class) {
				if (Objects.isNull(cache)) {
					cache = readMenuGroup();
				}
			}
		}
		return cache;
	}
}
