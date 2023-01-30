package org.openmrs.module.smsreminder.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.openmrs.module.smsreminder.dto.NotificationTypeDTO;
import org.openmrs.module.smsreminder.model.NotificationType;
import org.openmrs.module.smsreminder.util.NotificationTypeList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SMSReminderNotificationTypeSettingsController {

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/smsreminder/sms_notification_type_settings", method = RequestMethod.GET)
	public ModelAndView patientListSender() {

		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("notificationTypes", smsReminderService.getAllNotificationType());

		return modelAndView;
	}

	@RequestMapping(value = "/module/smsreminder/addNotificationType", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json", consumes = "application/json", headers = "content-type=application/json")
	@ResponseBody
	String addNotificationTypes(@RequestBody NotificationTypeList notificationTypes) throws Exception {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();

		for (NotificationTypeDTO notificationTypeDTO : notificationTypes.getNotificationTypes()) {

			checkIfNotExist(notificationTypes.getNotificationTypes());

			if (notificationTypeDTO.getNotificationTypeId() == null) {

				NotificationType notificationTypeTobeInsert = new NotificationType();
				notificationTypeTobeInsert.setName(notificationTypeDTO.getName());
				notificationTypeTobeInsert.setNumberOfDays(Integer.parseInt(notificationTypeDTO.getNumberOfDays()));
				smsReminderService.saveNotificationType(notificationTypeTobeInsert);
			} else {

				NotificationType notificationTypeTobeUpdate = smsReminderService
						.findNotificationTypeById(notificationTypeDTO.getNotificationTypeId());
				notificationTypeTobeUpdate.setName(notificationTypeDTO.getName());
				notificationTypeTobeUpdate.setNumberOfDays(Integer.parseInt(notificationTypeDTO.getNumberOfDays()));
				smsReminderService.saveNotificationType(notificationTypeTobeUpdate);
			}
		}

		return "ok";
	}

	public void checkIfNotExist(List<NotificationTypeDTO> notificationTypeDTOs) {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		List<NotificationType> notificationTypesTobeDelete = smsReminderService.getAllNotificationType();

	}
}
