<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/smsreminder/css/smsreminder.css"/>
<form method="GET">
	<fieldset>
		<legend><openmrs:message code="smsreminder.config" /></legend>		
	<div class="searchFields">
		<div align="center">
			<label for="typeNotification">
				<openmrs:message code="smsreminder.typeNotification" /><span class="required">*</span>:
			</label>
			<select >
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
			<input type="submit" value='<spring:message code="smsreminder.add"/>'
				name="addType" id="btn-add" />
		</div>	
		
	<c:if test="${not empty notificationTypeList}">
	<div align="center">
		<div class="box">
			<table  id="resultsTable" style="width:100%; font-size:12px;">
				<thead>
					<tr>
						<th><spring:message code="smsreminder.typeNotification"/></th>
						<th><spring:message code="smsreminder.numberOfDays"/></th>
						<th><spring:message code="smsreminder.action"/></th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
			<br/>
		</div>
	</div>
</c:if>
	<c:if test="${empty notificationTypeList}">
		<div id="openmrs_msg">
			<b> <spring:message code="smsreminder.no.load.form" /></b>
		</div>
	</c:if>
	</fieldset>
</form>