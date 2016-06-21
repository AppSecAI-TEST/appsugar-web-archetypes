<%@ attribute name="var" type="java.lang.String" required="true"%>
<%@ attribute name="enumClassName" type="java.lang.String" required="true"%>
<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%
	Class<?> enumClass = Class.forName(enumClassName);
	if(!enumClass.isEnum()){
		throw new IllegalArgumentException(enumClass+" is not a enum");
	}
	request.setAttribute(var, enumClass.getEnumConstants());
%>
