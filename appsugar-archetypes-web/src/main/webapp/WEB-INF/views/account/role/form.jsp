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
	<form:form  action="/account/role/save" modelAttribute="role" method="post" class="validator">
		<input type="hidden" name="id" value="${role.id}"/>
		<fieldset class="form-group">
	    <label for="name">名称:</label>
	    <input type="text" class="form-control required" id="name" value="${role.name}" name="name" placeholder="输入角色名">
	    <form:errors path="name" cssClass="label label-danger" />
	  </fieldset>
	  <fieldset class="form-group">
	    <label for="title">标题</label>
	    <input type="text" class="form-control required" id="title" value="${role.title}" name="title" placeholder="输入标题">
	    <form:errors path="title" cssClass="label label-danger" />
	  </fieldset>
	  <div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading">权限</div>
		  <div class="panel-body">
		  		<c:forEach var="group" items="${permissions}">
			    	<div class="panel panel-default">
			    		<div class="panel-heading">${group.key}</div>
			    		<div class="panel-body">
			    			<c:forEach var="p" items="${group.value}">
			    				 <label class="label label-info">${p.name}:</label>
			    				 <input  type="checkbox" name="permissionList" value="${p.permission}" <c:if test="${role !=null && role.permissionList.contains(p.permission)}">checked="checked"</c:if>/>
			    			</c:forEach>
			    		</div>
			    	</div>
		    	</c:forEach>
		  </div>
	  </div>
	
		<shiro:hasPermission name="role:edit">
			<input class="btn btn-primary" type="submit" value="保存"/>
		</shiro:hasPermission>
	  
	</form:form>
</body>

