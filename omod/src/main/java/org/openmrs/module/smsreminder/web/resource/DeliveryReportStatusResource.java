package org.openmrs.module.smsreminder.web.resource;

import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.utils.SmsReminderResource;
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

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.DateProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.StringProperty;

@Resource(name = RestConstants.VERSION_1
		+ "/deliveryReportStatus", supportedClass = DeliveryReportStatus.class, supportedOpenmrsVersions = {
				"2.3.* - 9.*" })
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

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#getGETModel(Representation)
	 */
	public Model getGETModel(Representation rep) {
		ModelImpl model = ((ModelImpl) super.getGETModel(rep));
		if (rep instanceof DefaultRepresentation || rep instanceof FullRepresentation) {
			model.property("msgId", new IntegerProperty()).property("deliveryReportDescription", new StringProperty())
					.property("deliveryReportReasonCode", new StringProperty())
					.property("deliveryReportUpdateDatetime", new DateProperty())
					.property("deliveryReportStatus", new IntegerProperty())
					.property("messageSentDatetime", new DateProperty()).property("nrSms", new IntegerProperty())
					.property("partnerMsgId", new StringProperty());
		}
		return model;
	}

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#getCREATEModel(Representation)
	 */
	@Override
	public Model getCREATEModel(Representation rep) {
		return new ModelImpl().property("msgId", new IntegerProperty())
				.property("deliveryReportDescription", new StringProperty())
				.property("deliveryReportReasonCode", new StringProperty())
				.property("deliveryReportUpdateDatetime", new DateProperty())
				.property("deliveryReportStatus", new IntegerProperty())
				.property("messageSentDatetime", new DateProperty()).property("nrSms", new IntegerProperty())
				.property("partnerMsgId", new StringProperty());
	}

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#getUPDATEModel(Representation)
	 */
	@Override
	public Model getUPDATEModel(Representation representation) {
		return new ModelImpl().property("msgId", new IntegerProperty())
				.property("deliveryReportDescription", new StringProperty())
				.property("deliveryReportReasonCode", new StringProperty())
				.property("deliveryReportUpdateDatetime", new DateProperty())
				.property("deliveryReportStatus", new IntegerProperty())
				.property("messageSentDatetime", new DateProperty()).property("nrSms", new IntegerProperty())
				.property("partnerMsgId", new StringProperty());
	}

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getCreatableProperties()
	 */
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();

		description.addProperty("msgId");
		description.addProperty("deliveryReportDescription");
		description.addProperty("deliveryReportReasonCode");
		description.addProperty("deliveryReportUpdateDatetime");
		description.addProperty("deliveryReportStatus");
		description.addProperty("messageSentDatetime");
		description.addProperty("nrSms");
		description.addProperty("partnerMsgId");

		return description;
	}

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getUpdatableProperties()
	 */
	@Override
	public DelegatingResourceDescription getUpdatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();

		description.addProperty("msgId");
		description.addProperty("deliveryReportDescription");
		description.addProperty("deliveryReportReasonCode");
		description.addProperty("deliveryReportUpdateDatetime");
		description.addProperty("deliveryReportStatus");
		description.addProperty("messageSentDatetime");
		description.addProperty("nrSms");
		description.addProperty("partnerMsgId");

		return description;
	}

	@Override
	public DeliveryReportStatus newDelegate() {
		return new DeliveryReportStatus();
	}

	@Override
	public DeliveryReportStatus save(DeliveryReportStatus delegate) {
		SmsReminderResource.updateSent(delegate.getMsgId(), delegate.getDeliveryReportStatus(),
				delegate.getDeliveryReportDescription());
		System.out.println("update status" + delegate.getDeliveryReportStatus());
		return delegate;
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