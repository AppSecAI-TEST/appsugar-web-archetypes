<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html lang="zh">
<head>
<title>AppSugar:<sitemesh:write property="title"/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<t:assets />
<sitemesh:write property="head"/>
</head>

<body>
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<%-- For smartphones and smaller screens --%>
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/'/>">AppSugar</a>
		</div>
		<%@ include file="/WEB-INF/layouts/default/header.jsp"%>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<%@ include file="/WEB-INF/layouts/default/menu.jsp"%>
			</div>
			<div class="col-sm-10">
				<sitemesh:write property="body"/>
			</div>
		</div>
		<%@ include file="/WEB-INF/layouts/default/footer.jsp"%>
	</div>
</body>
</html>