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
package org.openmrs.module.smsreminder.api.db;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.model.MessageSent;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.model.NotificationType;

/**
 * Database methods for {@link SmsReminderService}.
 */
public interface SmsReminderDAO {

	/*
	 * Add DAO methods here
	 */

	public MessageSent saveMensageSent(MessageSent messageSent);

	public List<MessageSent> getAllMessageSent() throws DAOException;

	public List<NotificationPatient> getAllNotificationPatient() throws DAOException;

	public List<NotificationPatient> findPatientsForLostFollowup();

	public DeliveryReportStatus saveDeliveryReportStatus(DeliveryReportStatus deliveryReportStatus);

	public MessageSent findMessageSentToBeUpdate(DeliveryReportStatus deliveryReportStatus);
	
	public NotificationType saveNotificationType(NotificationType notificationType);
	
	public List<NotificationType> getAllNotificationType() throws APIException;



}