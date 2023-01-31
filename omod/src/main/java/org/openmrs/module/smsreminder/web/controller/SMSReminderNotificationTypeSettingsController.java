package org.openmrs.module.smsreminder.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		checkIfNotExist(notificationTypes.getNotificationTypes());

		for (NotificationTypeDTO notificationTypeDTO : notificationTypes.getNotificationTypes()) {


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

		Map<Integer, Integer> map = new HashMap<>();
		for (NotificationTypeDTO notificationTypeDTO : notificationTypeDTOs) {
			if (notificationTypeDTO.getNotificationTypeId() != null) {

				map.put(notificationTypeDTO.getNotificationTypeId(),
						Integer.parseInt(notificationTypeDTO.getNumberOfDays()));
			}
		}
		for (NotificationType notificationType : smsReminderService.getAllNotificationType()) {

			if (!map.containsKey(notificationType.getNotificationTypeId())) {
				smsReminderService.deleteNotificationType(notificationType);
			}
		}
	}
}
