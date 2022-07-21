<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />



<h2><openmrs:message code="LISTA DE SMS POR ENVIAR PARA PACIENTES"/></h2>
<form method="post">
	
	<div class="row">
		<table border="1" class="display" width="100%" cellpadding="2" cellspacing="0"
				style="font-size: 13px;">
		 
			<tr>
					<td>NID</td>
			        <td>Nome Comple</td>
			        <td>Sexo</td>
			        <td>Telefone</td>
			        <td>Ultima Visita</td>
			        <td>Proxima Visita</td>
			        <td>Inicio de TARV</td>
			        <td>Tipo de Paciente</td>  
			</tr>
		
			<c:forEach items="${smss}" var="sms">
			    <tr>   
				        <td>${sms.nid}</td>
				        <td>${sms.fullName}</td>
				        <td>${sms.gender}</td>
				        <td>${sms.phoneNumber}</td>
				        <td>${sms.lastVisitDate}</td>
				        <td>${sms.nextVisitDate}</td>
				        <td>${sms.artStartDate}</td>
				        <td>${sms.sentType}</td>
			    </tr>
			</c:forEach>
			
		</table>
	</div><br>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>