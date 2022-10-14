<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />


<h2>
	<openmrs:message code="LISTA DE SMS ENVIADAS PARA PACIENTES" />
</h2>
<form method="post">

	<div class="row">
		<table border="1" class="display" width="100%" cellpadding="2"
			cellspacing="0" style="font-size: 13px;">

			<tr>

				<td>ID_SMS</td>
				<td>NID</td>
				<td>Nome Completo</td>
				<td>Sexo</td>
				<td>Data do Alerta</td>
				<td>Telefone</td>
				<td>Ultima Visita</td>
				<td>Proxima Visita</td>
				<td>Estado do Envio</td>
				<td>Descricao</td>

			</tr>

			<c:forEach items="${smss}" var="sms">
				<tr>
					<td>${sms.msgId}</td>
					<td>${sms.nid}</td>
					<td>${sms.fullName}</td>
					<td>${sms.gender}</td>
					<td>${sms.alertDate}</td>
					<td>${sms.phoneNumber}</td>
					<td>${sms.lastVisitDate}</td>
					<td>${sms.nextVisitDate}</td>
					<td>${sms.status}</td>
					<td>${sms.statusDescriptionReason}</td>

				</tr>
			</c:forEach>

		</table>

		<div class="submit-btn" align="right">
			 <input type="submit"
				value='Exportar'
				name="export" />
		</div>

	</div>
	<br>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>