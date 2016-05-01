<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="navbar-collapse collapse" id="navbar">
	<ul class="nav navbar-nav">
		<shiro:notAuthenticated>		
			<li class="active"><a href="<c:url value="/login"/>">登录</a></li>
		</shiro:notAuthenticated>
		<shiro:user>
			<li><a href="<c:url value="/logout"/>">退出</a></li>
		</shiro:user>
	</ul>
</div>
