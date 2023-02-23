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
import org.openmrs.module.smsreminder.utils.DatasUtil;
import org.openmrs.module.smsreminder.utils.Validator;
import org.openmrs.module.smsreminder.webservice.Consumer;
import org.openmrs.scheduler.tasks.AbstractTask;

import pt.usendit.api.v2.ScheduleResult;

public class SendSmsReminderTask extends AbstractTask {

	final AdministrationService administrationService = Context.getAdministrationService();
	final GlobalProperty gpUs = administrationService.getGlobalPropertyObject("smsreminder.location_id");
	final GlobalProperty prefix = administrationService.getGlobalPropertyObject("smsreminder.prefix");
	final GlobalProperty partialMessage = administrationService.getGlobalPropertyObject("smsreminder.partial_message");
	final SmsReminderService smsReminderService = SmsReminderUtils.getService();
	final PatientService patientService = Context.getPatientService();
	final LocationService locationService = Context.getLocationService();

	@Override
	public void execute() {

		if (!smsReminderService.getAllNotificationPatient().isEmpty()) {

			for (NotificationPatient notificationPatient : smsReminderService.getAllNotificationPatient()) {
				String mensage = "Sr(a) ".concat(notificationPatient.getFullName())
						.concat(partialMessage.getPropertyValue())
						.concat(locationService.getLocation(Integer.valueOf(gpUs.getPropertyValue())).getName())
						.concat(" no dia ").concat(DatasUtil.formatarDataPt(notificationPatient.getNextVisitDate()));
				createMessageToBeSent(notificationPatient, mensage);

			}
		}

		if (!smsReminderService.getAllMessageToBeSent().isEmpty()) {
			for (MessageToBeSent messageToBeSent : smsReminderService.getAllMessageToBeSent()) {

				String partnerMsgId = UUID.randomUUID().toString();

				try {
					String[] splitNumber = messageToBeSent.getPhoneNumber().split(",");

					for (int i = 0; i < splitNumber.length; i++) {

						if (Validator.cellNumberValidator(splitNumber[i])) {

							ScheduleResult result = Consumer.sendMensage(messageToBeSent.getMessage(),
									prefix.getPropertyValue() + splitNumber[i], partnerMsgId);

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

							if (result.getScheduleStatus().name().equals("SCHEDULE_OK")) {
								smsReminderService.saveMensageSent(mensageSent);
								smsReminderService.deleteMessageToBeSent(messageToBeSent);
							}
						}
					}
				} catch (Throwable e) {
					e.printStackTrace();
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