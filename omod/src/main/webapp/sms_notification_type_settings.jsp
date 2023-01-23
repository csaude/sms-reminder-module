<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>


<openmrs:htmlInclude
	file="${pageContext.request.contextPath}/moduleResources/smsreminder/smsreminder/smsreminder.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />


<form method="GET">

	<fieldset>

		<legend>Tipos de Notificação:</legend>

		<div>
			<select >
				<option value="Levantamentos">
				
				</option>
				<option value="Consultas">
				
				</option>
				<option value="Faltosos">
				</option>
				
				<option value="Abandonos">
				</option>
			    
			</select>
		</div>


	</fieldset>

</form>