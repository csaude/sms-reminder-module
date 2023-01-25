package org.openmrs.module.smsreminder.web.controller;

import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotificationTypeController {	
	@RequestMapping(value = "/module/smsreminder/addNotificationTypes", method = RequestMethod.GET)
	public ModelAndView notificationTypes() {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("notificationTypes", smsReminderService.getAllNotificationType());

		return modelAndView;
	}
}
