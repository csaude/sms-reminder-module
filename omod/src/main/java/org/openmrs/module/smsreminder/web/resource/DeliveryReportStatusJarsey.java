/**
 *
 */
package org.openmrs.module.smsreminder.web.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.utils.SmsReminderResource;

@Path("/delivery")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class DeliveryReportStatusJarsey {


	@POST
	public Response createDeliveryReport(DeliveryReportStatus deliveryReportStatus) {
		SmsReminderResource.SaveDeliveryReportStatus(deliveryReportStatus);
		return Response.ok(deliveryReportStatus).build();
	}

}
