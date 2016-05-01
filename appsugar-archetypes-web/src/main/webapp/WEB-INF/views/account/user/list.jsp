<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head>
<title>用户管理</title>
</head>
<body>
	<div style="float: right">
		<form action="#" method="POST" class="form-inline">
			<input type="text" name="name" class="form-control input-sm"
				value="${param.name}" placeholder="名称">
			<button type="submit" class="btn btn-primary btn-sm" id="search_btn">
				<spring:message code="search" />
			</button>
		</form>
	</div>

	<table class="table">
		<thead>
			<tr>
				<th><spring:message code="id" /></th>
				<th><spring:message code="createdAt" /></th>
				<th><spring:message code="name" /></th>
				<th><spring:message code="loginName" /></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="user" items="${page.content}">
				<tr>
					<td>${user.id}</td>
					<td><fmt:formatDate value="${user.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${user.name}</td>
					<td>${user.loginName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${page}" />
</body>

