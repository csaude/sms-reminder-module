package org.openmrs.module.smsreminder.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.model.Sent;


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

			if (n.getReminderDays() == 15 || n.getReminderDays() == 7 || n.getReminderDays() == 3) {
				notificationPatients.add(n);
			}
		}

		return notificationPatients;

	}

	public static List<Sent> getAllSmsSent() {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		return smsReminderService.getAllSent();
	}

	public static void saveSent(final NotificationPatient notificationPatient) {
		final Sent sent = new Sent();

		final PatientService patientService = Context.getPatientService();
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

	    String[] result =  notificationPatient.getPhoneNumber().split(",");
	    for (String s : result) {

		sent.setPhoneNumber(s);
		sent.setMessage(notificationPatient.getMensage());
		sent.setAlertDate(notificationPatient.getDateCreated());
		sent.setPatient(patientService.getPatient(notificationPatient.getPatientId()));
		sent.setFullName(notificationPatient.getFullName());
		sent.setGender(notificationPatient.getGender());
		sent.setLastVisitDate(notificationPatient.getLastVisitDate());
		sent.setNextVisitDate(notificationPatient.getNextVisitDate());
		sent.setReminderDays(notificationPatient.getReminderDays());
		sent.setMsgId(notificationPatient.getMsgId());
		sent.setPartnerMsgId(notificationPatient.getPartnerMsgId());
		smsReminderService.saveSent(sent);
	    }
	}

	public static void SaveDeliveryReportStatus(final DeliveryReportStatus deliveryReportStatus) {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		deliveryReportStatus.setDateCreated(Calendar.getInstance().getTime());
		smsReminderService.saveDeliveryReportStatus(deliveryReportStatus);
	}

}