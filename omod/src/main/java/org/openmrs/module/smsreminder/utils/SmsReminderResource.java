package org.openmrs.module.smsreminder.utils;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.model.Sent;

/**
 * Created by nelson.mahumane on 20-10-2015. Classe que organiza todos recursos
 * necessarios para o envio de sms filtrando as de a cordo com as suas categoria
 */
public class SmsReminderResource {

	public static List<NotificationPatient> getAllNotificationFolowUpPatient() {
		Context.getAdministrationService();

		final SmsReminderService smsReminderService = SmsReminderUtils.getService();

		final List<NotificationPatient> notificationFollowUpPatients = new ArrayList<NotificationPatient>();
		notificationFollowUpPatients.addAll(smsReminderService.searchFollowUpPatient());

		return notificationFollowUpPatients;

	}

	public static List<NotificationPatient> getAllNotificationPatient() {
		Context.getAdministrationService();

		final SmsReminderService smsReminderService = SmsReminderUtils.getService();

		final List<NotificationPatient> notificationPatients = new ArrayList<NotificationPatient>();

		for (NotificationPatient n : smsReminderService.getNotificationPatientByDiasRemanescente()) {

			if (n.getReminderDays() == 20 || n.getReminderDays() == 7 || n.getReminderDays() == 3) {

				notificationPatients.add(n);
			}
		}

		return notificationPatients;

	}

	public static List<Sent> getAllSmsSent() {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		return smsReminderService.getAllSent();
	}

	public static void saveSent(final NotificationPatient notificationPatient, SentType sentType) {
		final Sent sent = new Sent();
		final PatientService patientService = Context.getPatientService();
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		sent.setPhoneNumber(notificationPatient.getPhoneNumber());
		sent.setMessage(notificationPatient.getMensage());
		sent.setAlertDate(notificationPatient.getDateCreated());
		sent.setReminderDays(notificationPatient.getReminderDays());
		sent.setPatient(patientService.getPatient(notificationPatient.getPatientId()));
		sent.setSentType(sentType);
		smsReminderService.saveSent(sent);

	}

	public static void SaveDeliveryReportStatus(final DeliveryReportStatus deliveryReportStatus) {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		smsReminderService.saveDeliveryReportStatus(deliveryReportStatus);
	}

}