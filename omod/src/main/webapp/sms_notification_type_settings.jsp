<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<openmrs:htmlInclude
	file="${pageContext.request.contextPath}/moduleResources/smsreminder/css/smsreminder.css" />
	

<script type="text/javascript">	
	
        function addRow(){
        	
            var table = document.getElementById("resultsTable");
			console.log(table);
	    	
			var tdSelect = document.getElementById("types").value;
            var tdInput = document.getElementById("numberOfDays").value;
            
            var tr = table.insertRow();
            tr.style.backgroundColor = '#1aac9b';

            
            var td = tr.insertCell();
            td.innerHTML= null;
            td.style.display='none';
            
            var td = tr.insertCell();
            td.innerHTML= tdSelect;
            

            td = tr.insertCell();
            td.innerHTML= tdInput;
            
            td = tr.insertCell();
            td.innerHTML= '<input type="button" id="btnEdit" value="editar" onclick="editRow(this)" /> <input type="button" id="btnDelete" value="delete" onclick="deleteRow(this)" />';
		    console.log(table);

            clean();
        }
        
        function clean() {
            var days = document.getElementById("numberOfDays");
            var types = document.getElementById("types");

            if (days.value !="") {
            	days.value = "";
            }
            if (types.value !="") {
            	types.value = "";
            }
        }
        
        function editRow(ele) {
        	
            var table = document.getElementById("resultsTable");
            var days = document.getElementById("numberOfDays");
            var types = document.getElementById("types");

            types.value=ele.parentNode.parentNode.childNodes[1].innerHTML
            days.value=ele.parentNode.parentNode.childNodes[2].innerHTML
        
        }
       
        function deleteRow(ele){
            var table = document.getElementById('resultsTable');
            var rowCount = table.rows.length;
            if(rowCount <= 1){
                alert("There is no row available to delete!");
                return;
            }
            if(ele){
                ele.parentNode.parentNode.remove();
            }else{
                table.deleteRow(rowCount-1);
            }
        }
        
        
        function save(){
        	var table = document.getElementById("resultsTable");
        	const notificationTypes= [];

        	for (var i = 1; row = table.rows[i]; i++) {

            var notificationTypeId = document.getElementById("resultsTable").rows[i].cells[0].innerHTML;
        	var type = document.getElementById("resultsTable").rows[i].cells[1].innerHTML;
        	var days = document.getElementById("resultsTable").rows[i].cells[2].innerHTML;
        	
        	var notificationType = {"notificationTypeId":notificationTypeId,"name":type,"numberOfDays":days};
        	
        	notificationTypes[i-1]  = notificationType;
        	console.log(notificationType);

        	}
			console.log(JSON.stringify({
        		notificationTypes 
	        }));
			
            jQuery.ajax({
                url:"/openmrs/module/smsreminder/addNotificationType.form",
    			type : "POST",
    			data : JSON.stringify({
            		notificationTypes}),
    			contentType : "application/json",
    			dataType : "json",
    			success : function(data) {
                    console.log(data);              
    			}
    		});
        	for (var i = 1; row = table.rows[i]; i++) {
            	table.deleteRow(i);
           }
//         	document.getElementById("resultsTable").getElementsByTagName('tbody')[0].innerHTML = '';

        }  
        </script>

<form method="POST">
	<div class="searchFields">
		<div align="center">
			<label for="typeNotification"> <openmrs:message
					code="smsreminder.typeNotification" /><span class="required">*</span>:
			</label> <select id="types">
				<option value="Levantamentos">
					<openmrs:message code="smsreminder.pickup" />
				</option>
				<option value="Consultas">
					<openmrs:message code="smsreminder.consultation" />
				</option>
				<option value="Faltosos">
					<openmrs:message code="smsreminder.ltfu" />
				</option>
				<option value="Abandonos">
					<openmrs:message code="smsreminder.abandoned" />
				</option>
			</select> <label for="numberOfDays"> <openmrs:message
					code="smsreminder.numberOfDays" /><span class="required">*</span>:
			</label> <input path="numberOfDays" size="5" id="numberOfDays" /> <input
				type="button" onClick="addRow()"
				value="<spring:message code="smsreminder.add"/>" />

		</div>
		</br>

		<div align="center">
			<body>
				<table id="resultsTable" style="width: 50%; font-size: 12px;"
					border="1" cellpadding="5">
					<thead>
						<tr>
						   <th style="display:none;"><spring:message code="ID" /></th>
							<th><spring:message code="smsreminder.typeNotification" /></th>
							<th><spring:message code="smsreminder.numberOfDays" /></th>
							<th><spring:message code="smsreminder.action" /></th>
						</tr>
					</thead>
				<tbody>
				<c:forEach items="${notificationTypes}" var="notificationTypes">
					<tr>
						<td style="display:none;">${notificationTypes.notificationTypeId}</td>
						<td>${notificationTypes.name}</td>
						<td>${notificationTypes.numberOfDays}</td>
						<td>
						<input type="button" id="btnEdit" value="editar" onclick="editRow(this)" /> 
						<input type="button" id="btnDelete" value="delete" onclick="deleteRow(this)" />
						</td>
					</tr>
				</c:forEach>
			    </tbody>
				</table>
				</br>
            	<input type="button" id="btnGravar"  onClick="save()" value="<spring:message code="smsreminder.save"/>" />
			</body>
	</form>
</div>
</br>
</br>
