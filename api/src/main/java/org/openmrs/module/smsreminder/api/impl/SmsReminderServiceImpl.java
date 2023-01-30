/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.smsreminder.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.api.db.SmsReminderDAO;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.model.MessageSent;
import org.openmrs.module.smsreminder.model.MessageToBeSent;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.model.NotificationType;
import org.openmrs.module.smsreminder.utils.MessageStatus;

/**
 * It is a default implementation of {@link SmsReminderService}.
 */
public class SmsReminderServiceImpl extends BaseOpenmrsService implements SmsReminderService {

	protected final Log log = LogFactory.getLog(this.getClass());

	private SmsReminderDAO dao;

	/**
	 * @return the dao
	 */
	public SmsReminderDAO getDao() {
		return this.dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(final SmsReminderDAO dao) {
		this.dao = dao;
	}

	@Override
	public MessageSent saveMensageSent(final MessageSent messageSent) {
		return this.getDao().saveMensageSent(messageSent);
	}

	@Override
	public DeliveryReportStatus saveDeliveryReportStatus(DeliveryReportStatus deliveryReportStatus) {

		MessageSent mensageSent = this.getDao().findMessageSentToBeUpdate(deliveryReportStatus);

		if (mensageSent != null) {

			deliveryReportStatus.setPatientId(mensageSent.getPatient().getPatientId());

			if (deliveryReportStatus.getDeliveryReportStatus() != null) {

				if (deliveryReportStatus.getDeliveryReportStatus() == 0) {

					deliveryReportStatus.setStatusDescription(MessageStatus.DELIVERED.getName());
					mensageSent.setLastStatus(MessageStatus.DELIVERED.getName());
					mensageSent.setLastDateStatus(deliveryReportStatus.getDeliveryReportUpdateDatetime());
				}
				if (deliveryReportStatus.getDeliveryReportStatus() == 1) {
					deliveryReportStatus.setStatusDescription(MessageStatus.ON_HOLD.getName());
					mensageSent.setLastStatus(MessageStatus.ON_HOLD.getName());
					mensageSent.setLastDateStatus(deliveryReportStatus.getDeliveryReportUpdateDatetime());

				}
				if (deliveryReportStatus.getDeliveryReportStatus() == 2) {
					deliveryReportStatus.setStatusDescription(MessageStatus.NOT_DELIVERY.getName());
					mensageSent.setLastStatus(MessageStatus.NOT_DELIVERY.getName());
					mensageSent.setLastDateStatus(deliveryReportStatus.getDeliveryReportUpdateDatetime());
				}
			}
		}
		this.getDao().saveMensageSent(mensageSent);
		return this.getDao().saveDeliveryReportStatus(deliveryReportStatus);
	}

	@Override
	public List<MessageSent> getAllMessageSent() throws APIException {
		return this.getDao().getAllMessageSent();
	}

	@Override
	public List<NotificationPatient> getAllNotificationPatient() {
		List<NotificationPatient> notificationPatients = new ArrayList<>();

		for (NotificationPatient notificationPatient : this.getDao().getAllNotificationPatient()) {

			for (NotificationType notificationType : this.getDao().getAllNotificationType()) {
				if (notificationPatient.getReminderDays().intValue() == notificationType.getNumberOfDays()) {
					notificationPatients.add(notificationPatient);
				}
			}
		}
		return notificationPatients;
	}

	@Override
	public List<NotificationPatient> findPatientsForLostFollowup() {
		return this.getDao().findPatientsForLostFollowup();
	}

	@Override
	public NotificationType saveNotificationType(NotificationType notificationType) {
		return this.getDao().saveNotificationType(notificationType);
	}

	@Override
	public List<NotificationType> getAllNotificationType() throws APIException {
		return this.getDao().getAllNotificationType();
	}

	@Override
	public NotificationType findNotificationTypeById(Integer notificationTypeId) throws APIException {
		return this.getDao().findNotificationTypeById(notificationTypeId);
	}

	@Override
	public void deleteNotificationType(NotificationType notificationType) {
		this.getDao().deleteNotificationType(notificationType);
	}

	@Override
	public MessageToBeSent saveMensageToBeSent(MessageToBeSent messageToBeSent) {
		return this.getDao().saveMensageToBeSent(messageToBeSent);
	}

}