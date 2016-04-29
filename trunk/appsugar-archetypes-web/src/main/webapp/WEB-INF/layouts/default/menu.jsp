<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<shiro:user>
	<%
		String lastUri =(String)request.getAttribute("javax.servlet.forward.request_uri");
		request.setAttribute("lastUri", lastUri);
	%>
	<!-- 确保菜单数据加载 -->
	<tags:menu/>
	
	<div class="menu">
		<div class="panel-group" id="accordionMenu">
		<c:forEach var="menuGroup" items="${application_menu_group_key}">
			<c:if test="${menuGroup.show}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a data-toggle="collapse"
								data-parent="#accordionMenu" href="#${menuGroup.url}">
									<spring:message code="${menuGroup.code}" text="${menuGroup.name}"/>
								</a>
					</div>
					
						<div id="${menuGroup.url}" class="panel-collapse collapse">
							<div class="panel-body">
									<ul class="nav nav-pills nav-stack">
										<c:forEach var="menu" items="${menuGroup.menuList}">
											<c:if test="${menu.show}">
							      			 	<li <c:if test='${fn:contains(lastUri,menu.url)}'> class="active"</c:if>><a href="${ctx}${menu.url}">
							      			 		<spring:message code="${menu.code}" text="${menu.name}"/>
							      			 	</a></li>
						      			 	</c:if>
					      			 	</c:forEach>
					                </ul>
					        </div>
					    </div>
				    
				</div>
			</c:if>
		</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
		$("#accordionMenu li").each(function(i){
			if($(this).attr("class")){
				$($(this).parent().parent().parent()).addClass("in")
			}
		});
	</script>
</shiro:user>