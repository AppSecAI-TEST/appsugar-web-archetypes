<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.appsugar.domain.Page" required="true"%>
<%@ attribute name="onclick" type="java.lang.String" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getPageNumber() + 1;
int size = page.getPageSize();
int begin = Math.max(1, current - size/2);
int end = Math.min(begin + (size - 1), page.getTotalPage());

request.setAttribute("current", current);
request.setAttribute("size", page.getPageSize());
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>
<script type="text/javascript">
	function  jumpToPage(page,pageSize){
		var sortType = '${sortType}';
		var originalForm = document.forms[0];
		var form = $(originalForm);
		addParameterToForm(form,"pageParam","pageNumber",page - 1);
		addParameterToForm(form,"pageSizeParam","pageSize",pageSize);
		addParameterToForm(form,"sortParam","sortType",sortType);
		form.submit();
	}
</script>
<nav style="float: right;padding-top: 0px;margin-top: 0px;">
	<ul  class="pagination">
		 <% if (page.getPageNumber()>0){%>
               	<li><a href="javascript:jumpToPage(1,${size})" >&lt;&lt;</a></li>
                <li><a href="javascript:jumpToPage(${current - 1},${size})">&lt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a href="javascript:void(0);">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="javascript:jumpToPage(${i},${size})">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNext()){%>
               	<li><a href="javascript:jumpToPage(${current+1},${size})">&gt;</a></li>
                <li><a href="javascript:jumpToPage(${page.totalPage},${size})">&gt;&gt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
         <%} %>

	</ul>
</nav>

