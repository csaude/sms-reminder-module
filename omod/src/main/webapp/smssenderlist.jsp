<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2><openmrs:message code="SMS LIST"/></h2>
<form  method="POST">
	
	<div class="row">
		<table border="1">
		 
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
	<div class="row">
    	<input id="subValue" type="submit" value="Exportar para Excel">
    </div>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>