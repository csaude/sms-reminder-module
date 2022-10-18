package org.openmrs.module.smsreminder.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.openmrs.GlobalProperty;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.utils.DatasUtil;
import org.openmrs.module.smsreminder.utils.SmsReminderResource;
import org.openmrs.module.smsreminder.webservice.Consumer;
import org.openmrs.scheduler.tasks.AbstractTask;

public class SendSmsReminderTask extends AbstractTask {

	final AdministrationService administrationService = Context.getAdministrationService();
	final List<NotificationPatient> notificationPatients = new ArrayList<NotificationPatient>();
	final GlobalProperty gpUs = administrationService.getGlobalPropertyObject("smsreminder.us");
	final SmsReminderService smsReminderService = SmsReminderUtils.getService();
	final PatientService patientService = Context.getPatientService();
	final LocationService locationService = Context.getLocationService();

	@Override
	public void execute() {
		notificationPatients.addAll(SmsReminderResource.getAllNotificationPatient());

		if (!notificationPatients.isEmpty()) {
			for (NotificationPatient notificationPatient : notificationPatients) {

				String mensage = "Sr ".concat(notificationPatient.getFullName()).concat(" Tem um Encontro Marcado na ")
						.concat(locationService.getLocation(Integer.valueOf(gpUs.getPropertyValue())).getName())
						.concat(" no dia ").concat(DatasUtil.formatarDataPt(notificationPatient.getNextVisitDate()));

				try {
					String[] result = notificationPatient.getPhoneNumber().split(",");
					for (String s : result) {
						String partnerMsgId = UUID.randomUUID().toString();

						Consumer.sendMensage(mensage, "258" + s, partnerMsgId);
						notificationPatient.setPartnerMsgId(partnerMsgId);
						notificationPatient.setMensage(mensage);
						SmsReminderResource.saveSent(notificationPatient);

					}
				} catch (Throwable e) {
					e.printStackTrace();
				}

			}
		}
	}

}