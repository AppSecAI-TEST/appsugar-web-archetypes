package org.appsugar.repository.extend;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 扩展分页适配
 * @author NewYoung
 * 2016年2月29日下午5:19:48
 */
public class PageAdapter extends org.appsugar.entity.account.PageAdapter {

	public PageAdapter() {
		super();
	}

	public PageAdapter(int pageSize, int pageNum, String sort, Direction direction) {
		super(pageSize, pageNum, sort, direction);
	}

	public PageAdapter(int pageSize, int pageNum) {
		super(pageSize, pageNum);
	}

	public Pageable toPageable() {
		if (StringUtils.isNotBlank(sort)) {
			return new PageRequest(pageNum, pageSize,
					new Sort(org.springframework.data.domain.Sort.Direction.valueOf(direction.name()), sort));
		}
		return new PageRequest(pageNum, pageSize);

	}
}
