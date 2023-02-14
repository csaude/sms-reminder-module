<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>


<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />
		<openmrs:htmlInclude
	file="${pageContext.request.contextPath}/moduleResources/smsreminder/css/smsreminder.css" />
	
	<script type="text/javascript">	
	function exportTableToExcel(tableID, filename = ''){
	    var downloadLink;
	    var dataType = 'application/vnd.ms-excel';
	    var tableSelect = document.getElementById(tableID);
	    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
	    
	    // Specify file name
	    filename = filename?filename+'.xls':'excel_data.xls';
	    
	    // Create download link element
	    downloadLink = document.createElement("a");
	    
	    document.body.appendChild(downloadLink);
	    
	    if(navigator.msSaveOrOpenBlob){
	        var blob = new Blob(['\ufeff', tableHTML], {
	            type: dataType
	        });
	        navigator.msSaveOrOpenBlob( blob, filename);
	    }else{
	        // Create a link to the file
	        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
	    
	        // Setting the file name
	        downloadLink.download = filename;
	        
	        //triggering the function
	        downloadLink.click();
	    }
	}

	</script>


<h2>
	<openmrs:message code="Lista De SMS Por Enviar Para Pacientes" />
</h2>
<form>

	<div class="row">
		<table id="resultsTable" border="1" class="display" width="100%" cellpadding="2"
			cellspacing="0" style="font-size: 13px;">

			<tr>
				<td>NID</td>
				<td>Nome Comple</td>
				<td>Sexo</td>
				<td>Telefone</td>
				<td>Ultima Visita</td>
				<td>Proxima Visita</td>
				<td>Inicio de TARV</td>
				<td>Dias Remaneicentes</td>
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
					<td>${sms.reminderDays}</td>
				</tr>
			</c:forEach>

		</table>
       <br>
		<div class="row" align="right">
			<button type="submit" onclick="exportTableToExcel('resultsTable', 'Message_Sent')" >Exportar</button>
			
		</div>
	</div>
	<br>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>