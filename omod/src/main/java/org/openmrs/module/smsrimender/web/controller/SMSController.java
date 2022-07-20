package org.openmrs.module.smsrimender.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.smsrimender.utils.SmsReminderResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("smss")
public class SMSController {

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/smsrimender/smssenderlist", method = RequestMethod.GET)
	public ModelAndView patientListSender() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("smss", SmsReminderResource.getAllNotificationPatient());
		return modelAndView;
	}

}
