<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>


<openmrs:htmlInclude
	file="${pageContext.request.contextPath}/moduleResources/smsreminder/smsreminder/smsreminder.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />


<form method="GET">

	<fieldset>

		<legend>Tipos de Notificação:</legend>

		<div  align="center">
			<select >
				<option value="Levantamentos">
				Levantamentos
				</option>
				<option value="Consultas">
				Consultas
				</option>
				
				<option value="Faltosos">
				Faltosos
				</option>
				
				<option value="Abandonos">
				Abandonos
				</option>
			    
			</select>
		</div>
	</fieldset>

</form>