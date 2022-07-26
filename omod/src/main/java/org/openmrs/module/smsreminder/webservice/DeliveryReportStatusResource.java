package org.openmrs.module.smsreminder.webservice;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.springframework.stereotype.Service;

@Path("sms")
@Service(DeliveryReportStatusResource.NAME)
public class DeliveryReportStatusResource {

	public static final String NAME = "mz.org.fgh.sms-api.integ.DeliveryReportStatus";

	@Inject
	private SmsReminderService smsReminderService;

	@POST
	@Path("delivery")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDeliveryReport(@QueryParam("msgId") final int msgId,
			@QueryParam("deliveryReportDescription") final String deliveryReportDescription,
			@QueryParam("deliveryReportReasonCode") final String deliveryReportReasonCode,
			@QueryParam("deliveryReportUpdateDatetime") final Date deliveryReportUpdateDatetime,
			@QueryParam("deliveryReportStatus") final int deliveryReportStatus,
			@QueryParam("messageSentDatetime") final Date messageSentDatetime, @QueryParam("nrSms") final int nrSms,
			@QueryParam("partnerMsgId") final String partnerMsgId) {

		smsReminderService.saveSent(null);

		return null;

	}

}
