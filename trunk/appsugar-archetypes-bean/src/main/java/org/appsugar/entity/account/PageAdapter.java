package org.appsugar.entity.account;

/**
 * 分页适配
 * @author NewYoung
 * 2016年2月29日下午5:05:35
 */
public class PageAdapter {
	//每页条数
	protected int pageSize = 10;
	//当前页
	protected int pageNum = 0;
	//排序
	protected String sort;
	//排序类型　
	protected Direction direction = Direction.DESC;

	public PageAdapter() {
		super();
	}

	public PageAdapter(int pageSize, int pageNum) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}

	public PageAdapter(int pageSize, int pageNum, String sort, Direction direction) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.sort = sort;
		this.direction = direction;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageAdapter [pageSize=").append(pageSize).append(", pageNum=").append(pageNum).append(", sort=")
				.append(sort).append(", direction=").append(direction).append("]");
		return builder.toString();
	}

	public static enum Direction {
		ASC, DESC;
	}

}
