package org.openmrs.module.smsreminder.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SMSReminderNotificationTypeSettingsController {

	
	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/smsreminder/sms_notification_type_settings", method = RequestMethod.GET)
	public ModelAndView patientListSender() {
		final ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
}
