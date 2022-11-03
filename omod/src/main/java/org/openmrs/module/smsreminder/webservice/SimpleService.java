package org.openmrs.module.smsreminder.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.utils.SmsReminderResource;

@WebService
public class SimpleService {

	@WebMethod(operationName = "saveDalivereyReport")
	public String saveDeliveryReport(DeliveryReportStatus deliveryReportStatus) {
		SmsReminderResource.SaveDeliveryReportStatus(deliveryReportStatus);
		return "ok";

	}

}
