<%@tag import="org.appsugar.bean.MenuConfig"%>
<%@tag import="java.util.ArrayList"%>
<%@tag import="org.apache.commons.collections4.CollectionUtils"%>
<%@tag import="org.appsugar.bean.MenuGroup"%>
<%@tag import="java.util.List"%>
<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%
	//菜单数据缓存到application中
	String MENU_GROUP_KEY=  "application_menu_group_key";
	List<MenuGroup> menuGroupList = (List<MenuGroup>)application.getAttribute(MENU_GROUP_KEY);
	if(CollectionUtils.isNotEmpty(menuGroupList)){
		return;
	}
	menuGroupList = new ArrayList<MenuGroup>();
	application.setAttribute(MENU_GROUP_KEY, menuGroupList);
	//系统管理组
	MenuGroup menuGroup = new MenuGroup("SystemMenu","系统管理","menu.system");
	menuGroupList.add(menuGroup);
	//用户管理
	menuGroup.addChild(new MenuConfig("/account/user","用户管理","menu.user",null,"user:view"));
	//角色管理
	menuGroup.addChild(new MenuConfig("/account/role","角色管理","menu.role",null,"roel:view"));
%>
