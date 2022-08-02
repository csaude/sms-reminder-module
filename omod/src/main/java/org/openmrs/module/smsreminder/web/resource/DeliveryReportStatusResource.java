package org.openmrs.module.smsreminder.web.resource;

import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1
		+ "/sms", supportedClass = DeliveryReportStatus.class, order = 2, supportedOpenmrsVersions = { "1.10.*",
				"1.11.*", "1.12.*", "2.0.*", "2.1.*, 2.3.3" })
public class DeliveryReportStatusResource extends DelegatingCrudResource<DeliveryReportStatus> {

	@Override
	public DelegatingResourceDescription getRepresentationDescription(final Representation rep) {

		if (rep instanceof RefRepresentation) {

			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("msgId");
			description.addProperty("deliveryReportDescription");
			description.addProperty("deliveryReportReasonCode");
			description.addProperty("deliveryReportUpdateDatetime");
			description.addProperty("deliveryReportStatus");
			description.addProperty("messageSentDatetime");
			description.addProperty("nrSms");
			description.addProperty("partnerMsgId");

			return description;
			
		} else if (rep instanceof DefaultRepresentation) {

			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("msgId");
			description.addProperty("deliveryReportDescription");
			description.addProperty("deliveryReportReasonCode");
			description.addProperty("deliveryReportUpdateDatetime");
			description.addProperty("deliveryReportStatus");
			description.addProperty("messageSentDatetime");
			description.addProperty("nrSms");
			description.addProperty("partnerMsgId");

			return description;
		} else if (rep instanceof FullRepresentation) {

			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("msgId");
			description.addProperty("deliveryReportDescription");
			description.addProperty("deliveryReportReasonCode");
			description.addProperty("deliveryReportUpdateDatetime");
			description.addProperty("deliveryReportStatus");
			description.addProperty("messageSentDatetime");
			description.addProperty("nrSms");
			description.addProperty("partnerMsgId");

			return description;
		} else {
			return null;
		}
	}

	@Override
	public DeliveryReportStatus newDelegate() {
		return new DeliveryReportStatus();
	}

	@Override
	public DeliveryReportStatus save(DeliveryReportStatus delegate) {
		System.out.println("Funcionando............");
		return null;
	}

	@Override
	public DeliveryReportStatus getByUniqueId(String uniqueId) {
		return null;
	}

	@Override
	protected void delete(DeliveryReportStatus delegate, String reason, RequestContext context)
			throws ResponseException {

	}

	@Override
	public void purge(DeliveryReportStatus delegate, RequestContext context) throws ResponseException {

	}

}
