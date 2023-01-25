<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/smsreminder/css/smsreminder.css"/>
    
    <script type="text/javascript">	
	
        function addRow(){
        	
            var table = document.getElementById("resultsTable");

	    	var tdSelect = document.getElementById("types").value;
            var tdInput = document.getElementById("numberOfDays").value;
            
            var tr = table.insertRow();
            tr.style.backgroundColor = '#1aac9b';

            var td = tr.insertCell();
            td.innerHTML= tdSelect;
            

            td = tr.insertCell();
            td.innerHTML= tdInput;
            
            td = tr.insertCell();
            td.innerHTML= '<input type="button" id="btnEdit" value="editar" onclick="deleteRow(this)" /> <input type="button" id="btnDelete" value="delete" onclick="deleteRow(this)" />';
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

        	var type = document.getElementById("resultsTable").rows[i].cells[0].innerHTML;
        	var days = document.getElementById("resultsTable").rows[i].cells[1].innerHTML;
        	
        	var notificationType = {"name":type,"numberOfDays":days};
        	
        	notificationTypes[i-1]  = notificationType;

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
           }
        table.innerHTML="";
    </script>
    
<form method="POST">
	<div class="searchFields">
		<div align="center">
			<label for="typeNotification">
				<openmrs:message code="smsreminder.typeNotification" /><span class="required">*</span>:
			</label>
			<select id="types">
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
			</select>
			
			<label for="numberOfDays">
				<openmrs:message code="smsreminder.numberOfDays" /><span class="required">*</span>:
			</label>
			<input path="numberOfDays"  size="5" id="numberOfDays" />
		     <input type="button" onClick="addRow()" value="<spring:message code="smsreminder.add"/>"/>
				
		</div>	
		</br>
	
	<div align="center">
	   <body>
			<table  id="resultsTable" style="width:50%; font-size:12px;" border="1" cellpadding="5" >
				<thead>
					<tr>
						<th><spring:message code="smsreminder.typeNotification"/></th>
						<th><spring:message code="smsreminder.numberOfDays"/></th>
						<th><spring:message code="smsreminder.action"/></th>
					</tr>
				</thead>
			</table>
			</br>
		     <input type="button" onClick="save()" value="<spring:message code="smsreminder.save"/>"/>
		</body>
	</div>
	
</form>
<c:if test="${not empty notificationTypes}">

	<div align="center">
	   <body>
			<table  id="finalResult" style="width:50%; font-size:12px;" border="1" cellpadding="5" >
				<thead>
					<tr>
						<th><spring:message code="smsreminder.typeNotification"/></th>
						<th><spring:message code="smsreminder.numberOfDays"/></th>
					</tr>
				</thead>
				
			<c:forEach items="${notificationTypes}" var="notificationTypes">
				<tr>
					<td>${notificationTypes.name}</td>
					<td>${notificationTypes.numberOfDays}</td>
				</tr>
			</c:forEach>				
			</table>
		</body>
	</div>
	</c:if>
	
