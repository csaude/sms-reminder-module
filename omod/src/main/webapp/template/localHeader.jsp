<spring:htmlEscape defaultHtmlEscape="true" />
<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a></li>
				
	<li
		<c:if test='<%=request.getRequestURI().contains("/smssenderlist")%>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/smsrimender/smssenderlist.form"><spring:message
				code="smsrimender.smssenderlist" /></a>
	</li>
				

	<li
		<c:if test='<%=request.getRequestURI().contains("/smssendedlist")%>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/smsrimender/smssendedlist.form"><spring:message
				code="smsrimender.smssendedlist" /></a>
	</li>

	<!-- Add further links here -->
</ul>
<h3>
	<spring:message code="smsreminder.title" />
</h3>
