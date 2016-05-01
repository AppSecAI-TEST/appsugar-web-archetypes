<%@tag import="com.google.common.collect.Maps"%>
<%@tag import="java.util.Map"%>
<%@ tag body-content="empty" %>
<%@ attribute name="group" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<%
	request.setAttribute("local_string",request.getLocale().toString());
	String resourceLocalSupportKey ="resourceLocalSupport_key";
	Map<String,String> resourceLocalSupport = (Map<String,String>)application.getAttribute(resourceLocalSupportKey);
	if(resourceLocalSupport==null){
		resourceLocalSupport = Maps.newHashMap();
		application.setAttribute(resourceLocalSupportKey, resourceLocalSupport);
		resourceLocalSupport.put("en", "main");
		resourceLocalSupport.put("en_US", "main");
		resourceLocalSupport.put("zh", "main_zh");
		resourceLocalSupport.put("zh_CN", "main_zh");
	}
	
%>

<c:set var="resourceName" value="${resourceLocalSupport_key[local_string] == null ? 'main' : resourceLocalSupport_key[local_string]}" />
<link rel="stylesheet" type="text/css" href="${base}/assets/${resourceName}.css"/>
<script type="text/javascript" src="${base}/assets/${resourceName}.js"></script>


