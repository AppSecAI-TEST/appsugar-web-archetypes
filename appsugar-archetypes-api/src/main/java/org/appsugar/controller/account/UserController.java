package org.appsugar.controller.account;

import java.util.List;
import java.util.stream.Collectors;

import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.controller.BaseController;
import org.appsugar.controller.domain.Response;
import org.appsugar.controller.domain.account.SimpleUserDto;
import org.appsugar.entity.account.User;
import org.appsugar.entity.account.condition.UserCondition;
import org.appsugar.service.account.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	/**
	 * 根据条件查询用户
	 * @return 返回符合条件的用户基本信息分页数据
	 * @author NewYoung
	 * 2016年11月30日下午4:21:04
	 */
	@RequestMapping("list")
	@ResponseBody
	public Response<Page<SimpleUserDto>> list(UserCondition condition, Pageable pageable) {
		Page<User> page = userService.getByCondition(condition, pageable);
		List<SimpleUserDto> content = page.getContent().stream().map(e -> mapper.map(e, SimpleUserDto.class))
				.collect(Collectors.toList());
		return Response.success(pageTransfer(page, content));
	}
}
