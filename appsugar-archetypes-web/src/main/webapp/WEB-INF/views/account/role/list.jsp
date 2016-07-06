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
<title>角色管理</title>
</head>
<body>
		<div style="float: right">
			<form action="#" method="POST" class="form-inline">
				<div class="form-group">
				   <input type="text" id="name" name="name" class="form-control input-sm"
						value="${param.name}" placeholder="名称">
			    </div>
				<div class="form-group">
					<input type="text" id="title" name="title" class="form-control input-sm"
						value="${param.title}" placeholder="标题">
						<form:errors path="title" cssClass="error help-inline" />
				</div>
					
				<button type="submit" class="btn btn-primary btn-sm" id="search_btn">
					<spring:message code="search" />
				</button>
			</form>
		</div>
	<table class="table">
		<thead>
			<tr>
				<th>编号</th>
				<th>创建时间</th>
				<th>名称</th>
				<th>标题</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="role" items="${page.content}">
				<tr>
					<td>${role.id}</td>
					<td><fmt:formatDate value="${role.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${role.name}</td>
					<td>${role.title}</td>
					<td>
						<a href="${ctx}/account/role/form?id=${role.id}">查看</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a class="btn btn-primary" href="${ctx}/account/role/form">新建</a>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>
			${message}
		</div>
	</c:if>
	<tags:pagination page="${page}" />
</body>

