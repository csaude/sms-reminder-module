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
import org.openmrs.module.smsreminder.model.MensageSent;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.utils.DatasUtil;
import org.openmrs.module.smsreminder.webservice.Consumer;
import org.openmrs.scheduler.tasks.AbstractTask;

public class SendSmsReminderTask extends AbstractTask {

	final AdministrationService administrationService = Context.getAdministrationService();
	final GlobalProperty gpUs = administrationService.getGlobalPropertyObject("smsreminder.us");
	final SmsReminderService smsReminderService = SmsReminderUtils.getService();
	final PatientService patientService = Context.getPatientService();
	final LocationService locationService = Context.getLocationService();

	@Override
	public void execute() {

		if (!smsReminderService.getNotificationPatient().isEmpty()) {
			for (NotificationPatient notificationPatient : smsReminderService.getNotificationPatient()) {

				String mensage = "Sr ".concat(notificationPatient.getFullName()).concat(" Tem um Encontro Marcado na ")
						.concat(locationService.getLocation(Integer.valueOf(gpUs.getPropertyValue())).getName())
						.concat(" no dia ").concat(DatasUtil.formatarDataPt(notificationPatient.getNextVisitDate()));

				try {
					String[] result = notificationPatient.getPhoneNumber().split(",");

					for (String s : result) {

						String partnerMsgId = UUID.randomUUID().toString();

						Consumer.sendMensage(mensage, "258" + s, partnerMsgId);

						MensageSent mensageSent = new MensageSent();

						mensageSent.setNid(notificationPatient.getNid());
						mensageSent.setFullName(notificationPatient.getFullName());
						mensageSent.setLastVisitDate(notificationPatient.getLastVisitDate());
						mensageSent.setNextVisitDate(notificationPatient.getNextVisitDate());
						mensageSent.setMessage(mensage);
						mensageSent.setPartnerMsgId(partnerMsgId);
						mensageSent.setPhoneNumber(notificationPatient.getPhoneNumber());
						mensageSent.setPatient(patientService.getPatient(notificationPatient.getPatientId()));
						mensageSent.setDateCreated(Calendar.getInstance().getTime());
						mensageSent.setReminderDays(notificationPatient.getReminderDays());

						smsReminderService.saveMensageSent(mensageSent);

					}
				} catch (Throwable e) {
					e.printStackTrace();
				}

			}
		}
	}

}