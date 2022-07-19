package org.openmrs.module.smsreminder.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.smsreminder.utils.SmsReminderResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("notificationPatients")
public class SMSController {


	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/smsreminder/smssenderlist", method = RequestMethod.GET)
	public ModelAndView patientListSender() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("notificationPatients", SmsReminderResource.getAllNotificationPatient());
		return modelAndView;
	}

	@RequestMapping(value = "/module/smsreminder/smssendedlist", method = RequestMethod.GET)
	public ModelAndView patientListSended() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("notificationPatients", SmsReminderResource.getAllSmsSent());
		return modelAndView;
	}
}
