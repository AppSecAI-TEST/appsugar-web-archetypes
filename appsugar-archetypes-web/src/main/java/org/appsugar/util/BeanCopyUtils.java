package org.appsugar.util;

import java.util.Arrays;

import org.springframework.beans.BeanUtils;

import com.querydsl.core.types.Path;

/**
 * 对象拷贝工具
 * @author NewYoung
 *
 */
public class BeanCopyUtils {

	/**
	 * 把source里面的属性copy到target中
	 * @param source 源
	 * @param target 目标
	 * @param ignores 不拷贝的属性
	 */
	public static <T> T map(Object source, T target, Path<?>... ignores) {
		return map(source, target, Arrays.stream(ignores).map(p -> p.getMetadata().getName()).toArray(String[]::new));
	}

	/**
	 *@see BeanCopyUtils#map(Object, Object, Path...) 
	 */
	public static <T> T map(Object source, T target, String... ignores) {
		BeanUtils.copyProperties(source, target, ignores);
		return target;
	}
}
