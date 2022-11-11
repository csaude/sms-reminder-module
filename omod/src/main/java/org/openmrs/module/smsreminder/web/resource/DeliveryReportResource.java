package org.openmrs.module.smsreminder.web.resource;

import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/rest/" + RestConstants.VERSION_1 + "delivery")
public class DeliveryReportResource extends MainResourceController {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> save(DeliveryReportStatus deliveryReportStatus) throws Exception {
		final SmsReminderService smsReminderServiceApi = SmsReminderUtils.getService();
		smsReminderServiceApi.saveDeliveryReportStatus(deliveryReportStatus);
		return ResponseEntity.ok("OK");
	}
}