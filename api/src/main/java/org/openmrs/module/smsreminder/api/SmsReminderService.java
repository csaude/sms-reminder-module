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
package org.openmrs.module.smsreminder.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.model.NotificationType;
import org.openmrs.module.smsreminder.model.MessageSent;
import org.openmrs.module.smsreminder.model.MessageToBeSent;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean
 * which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(smsreminderService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface SmsReminderService extends OpenmrsService {


	@Transactional
	public MessageSent saveMensageSent(MessageSent messageSent);
	@Transactional
	public DeliveryReportStatus saveDeliveryReportStatus(DeliveryReportStatus deliveryReportStatus);
	@Transactional
	public List<MessageSent> getAllMessageSent() throws APIException;
	@Transactional
	public List<NotificationPatient> getAllNotificationPatient() throws APIException;
	@Transactional
	public List<NotificationPatient> findPatientsForLostFollowup();
	@Transactional
	public NotificationType saveNotificationType(NotificationType notificationType);
	@Transactional
	public List<NotificationType> getAllNotificationType() throws APIException;
	@Transactional
	public NotificationType findNotificationTypeById(Integer notificationTypeId) throws APIException;

	public void deleteNotificationType(NotificationType notificationType);
	
	public MessageToBeSent saveMensageToBeSent(MessageToBeSent messageToBeSent);




}