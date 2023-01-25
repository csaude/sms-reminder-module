<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />

<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/smsreminder/css/smsreminder.css"/>

<h1 align="center">
	<openmrs:message code="LISTA DE SMS ENVIADAS PARA PACIENTES" />
</h1>
<form method="post">

	<div class="row" align="center" >
			<table  id="finalResult" style="width:50%; font-size:12px;" border="1" cellpadding="5" >
					<tr>
						<th><spring:message code="smsreminder.typeNotification"/></th>
						<th><spring:message code="smsreminder.numberOfDays"/></th>
					</tr>
				
			<c:forEach items="${notificationTypes}" var="notificationTypes">
				<tr>
					<td>${notificationTypes.name}</td>
					<td>${notificationTypes.numberOfDays}</td>
				</tr>
			</c:forEach>				
			</table>


		<br>
		<div class="row">
			<input id="subValue" type="submit" value="Exportar para Excel">
		</div>
	</div>
	</div>
	<br>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>