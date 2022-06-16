package org.openmrs.module.smsreminder.webservice;

import java.util.Properties;

import org.openmrs.util.OpenmrsUtil;

import pt.usendit.api.v2.ArrayOfSms;
import pt.usendit.api.v2.RemoteUsendit;
import pt.usendit.api.v2.RemoteUsenditSoap;
import pt.usendit.api.v2.ScheduleResult;
import pt.usendit.api.v2.Sms;

public class Consumer {

	public static final String PASSWORD_PROPERTY_NAME = "smsrimender.password";

	public static ScheduleResult sendMensage(String mensage, String number, String username, String sender)
			throws Throwable {
		RemoteUsenditSoap remoteUsendit = new RemoteUsendit().getRemoteUsenditSoap();
		ArrayOfSms smsbulk = new ArrayOfSms();
		Sms sms = new Sms();
		sms.setBeginTime(null);
		sms.setEndTime(null);
		sms.setExpirationDatetime(null);
		sms.setIsFlash(false);
		sms.setMessageText(mensage);
		sms.setMsisdn(number);
		sms.setSender(sender);
		sms.setPriority(1);
		sms.setWorkingDays(false);
		sms.setPartnerMsgId(sender);
		smsbulk.getSms().add(sms);
		ScheduleResult result = remoteUsendit.sendMessages("emaposse", fetchPassword(), sender, null, smsbulk);
		System.out.println(result.getScheduleStatus());
		return result;
	}

	private static String fetchPassword() {

		Properties props = OpenmrsUtil.getRuntimeProperties(null);
		return props.getProperty(PASSWORD_PROPERTY_NAME, null);

	}

}
