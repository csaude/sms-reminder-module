package org.openmrs.module.smsreminder.scheduler;

import java.util.Calendar;
import java.util.UUID;

import org.openmrs.GlobalProperty;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.model.MessageSent;
import org.openmrs.module.smsreminder.model.MessageToBeSent;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.model.NotificationType;
import org.openmrs.module.smsreminder.utils.DatasUtil;
import org.openmrs.module.smsreminder.webservice.Consumer;
import org.openmrs.scheduler.tasks.AbstractTask;

public class SendSmsReminderTask extends AbstractTask {

	final AdministrationService administrationService = Context.getAdministrationService();
	final GlobalProperty gpUs = administrationService.getGlobalPropertyObject("smsreminder.location_id");
	final SmsReminderService smsReminderService = SmsReminderUtils.getService();
	final PatientService patientService = Context.getPatientService();
	final LocationService locationService = Context.getLocationService();

	@Override
	public void execute() {

		if (!smsReminderService.getAllNotificationPatient().isEmpty()) {

			for (NotificationPatient notificationPatient : smsReminderService.getAllNotificationPatient()) {

				for (NotificationType notificationType : smsReminderService.getAllNotificationType()) {

					if (notificationPatient.getReminderDays() == notificationType.getNumberOfDays()) {

						String mensage = "Sr ".concat(notificationPatient.getFullName())
								.concat(" Tem um Encontro Marcado na ")
								.concat(locationService.getLocation(Integer.valueOf(gpUs.getPropertyValue())).getName())
								.concat(" no dia ")
								.concat(DatasUtil.formatarDataPt(notificationPatient.getNextVisitDate()));
						createMessageToBeSent(notificationPatient, mensage);

					}
					for (MessageToBeSent messageToBeSent : smsReminderService.getAllMessageToBeSent()) {

						String partnerMsgId = UUID.randomUUID().toString();

						try {
							Consumer.sendMensage(messageToBeSent.getMessage(), messageToBeSent.getPhoneNumber(),
									partnerMsgId);

							MessageSent mensageSent = new MessageSent();
							mensageSent.setNid(messageToBeSent.getNid());
							mensageSent.setFullName(messageToBeSent.getFullName());
							mensageSent.setLastVisitDate(messageToBeSent.getLastVisitDate());
							mensageSent.setNextVisitDate(messageToBeSent.getNextVisitDate());
							mensageSent.setMessage(messageToBeSent.getMessage());
							mensageSent.setPartnerMsgId(partnerMsgId);
							mensageSent.setPhoneNumber(messageToBeSent.getPhoneNumber());
							mensageSent.setPatient(patientService.getPatient(messageToBeSent.getPatientId()));
							mensageSent.setDateCreated(Calendar.getInstance().getTime());
							mensageSent.setReminderDays(messageToBeSent.getReminderDays());
							mensageSent.setGender(messageToBeSent.getGender());

							smsReminderService.saveMensageSent(mensageSent);

						} catch (Throwable e) {
							e.printStackTrace();
						}

					}

				}
			}
		}
	}

	public void createMessageToBeSent(NotificationPatient notificationPatient, String mensage) {

		MessageToBeSent messageToBeSent = new MessageToBeSent();
		messageToBeSent.setNid(notificationPatient.getNid());
		messageToBeSent.setFullName(notificationPatient.getFullName());
		messageToBeSent.setPhoneNumber(notificationPatient.getPhoneNumber());
		messageToBeSent.setGender(notificationPatient.getGender());
		messageToBeSent.setLastVisitDate(notificationPatient.getLastVisitDate());
		messageToBeSent.setNextVisitDate(notificationPatient.getNextVisitDate());
		messageToBeSent.setReminderDays(notificationPatient.getReminderDays());
		messageToBeSent.setPatientId(notificationPatient.getPatientId());
		messageToBeSent.setMessage(mensage);
		smsReminderService.saveMensageToBeSent(messageToBeSent);

	}

}