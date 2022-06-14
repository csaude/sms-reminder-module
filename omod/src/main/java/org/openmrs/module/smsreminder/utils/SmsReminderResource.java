package org.openmrs.module.smsreminder.utils;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.modelo.NotificationFollowUpPatient;
import org.openmrs.module.smsreminder.modelo.NotificationPatient;

/**
 * Created by nelson.mahumane on 20-10-2015. Classe que organiza todos recursos
 * necessarios para o envio de sms filtrando as de a cordo com as suas categoria
 */
public class SmsReminderResource {

	public static List<NotificationFollowUpPatient> getAllNotificationFolowUpPatient() {
		Context.getAdministrationService();

		final SmsReminderService smsReminderService = SmsReminderUtils.getService();

		final List<NotificationFollowUpPatient> notificationFollowUpPatients = new ArrayList<NotificationFollowUpPatient>();
		notificationFollowUpPatients.addAll(smsReminderService.searchFollowUpPatient());

		return notificationFollowUpPatients;

	}
	
	public static List<NotificationPatient> getAllNotificationPatient() {
		Context.getAdministrationService();

		final SmsReminderService smsReminderService = SmsReminderUtils.getService();

		final List<NotificationPatient> notificationPatients = new ArrayList<NotificationPatient>();
		notificationPatients.addAll(smsReminderService.getNotificationPatientByDiasRemanescente());

		return notificationPatients;

	}


	
}