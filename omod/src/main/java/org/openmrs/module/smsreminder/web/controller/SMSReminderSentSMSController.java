package org.openmrs.module.smsreminder.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.smsreminder.SmsReminderUtils;
import org.openmrs.module.smsreminder.api.SmsReminderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SMSReminderSentSMSController {

	protected final Log log = LogFactory.getLog(getClass());


	@RequestMapping(value = "/module/smsreminder/smssendedlist", method = RequestMethod.GET)
	public ModelAndView patientListSender() {
		final SmsReminderService smsReminderService = SmsReminderUtils.getService();

		final ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("smss", smsReminderService.getAllSmsSent());
		return modelAndView;
	}


}
