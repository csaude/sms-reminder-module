<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />
	<openmrs:htmlInclude
	file="${pageContext.request.contextPath}/moduleResources/smsreminder/css/smsreminder.css" />

<h2>
	<openmrs:message code="Lista De SMS Enviadas Para Pacientes" />
</h2>
<form method="post">

	<div class="row">
		<table id="resultsTable" border="1" class="display" width="100%" cellpadding="2"
			cellspacing="0" style="font-size: 13px;">

			<tr>

				<td>NID</td>
				<td>Nome Completo</td>
				<td>Telefone</td>
				<td>Ultima Visita</td>
				<td>Proxima Visita</td>
				<td>Ultimo Estado</td>
				<td>Data do Estado</td>

			</tr>

			<c:forEach items="${smss}" var="sms">
				<tr>
					<td>${sms.nid}</td>
					<td>${sms.fullName}</td>
					<td>${sms.phoneNumber}</td>
					<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${sms.lastVisitDate}" /></td>
					<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${sms.nextVisitDate}" /></td>
					<td>${sms.lastStatus}</td>
					<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${sms.lastDateStatus}" /></td>
				</tr>
			</c:forEach>

		</table>

		<br>
		<div class="row" align="right">
			<input id="btnExportar" type="button" alt="submit" value="Exportar para Excel" />
		</div>
	</div>
	</div>
	<br>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>