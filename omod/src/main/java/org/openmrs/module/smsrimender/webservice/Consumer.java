package org.openmrs.module.smsrimender.webservice;


import pt.usendit.api.v2.ArrayOfSms;
import pt.usendit.api.v2.RemoteUsendit;
import pt.usendit.api.v2.RemoteUsenditSoap;
import pt.usendit.api.v2.ScheduleResult;
import pt.usendit.api.v2.Sms;

public class Consumer {

	public static final String PASSWORD_PROPERTY_NAME = "smsrimender.password";

	public static ScheduleResult sendMensage(String mensage, String number)
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
		sms.setSender("");
		sms.setPriority(1);
		sms.setWorkingDays(false);
		sms.setPartnerMsgId("");
		smsbulk.getSms().add(sms);
		ScheduleResult result = remoteUsendit.sendMessages("emaposse", "", "", null, smsbulk);
		System.out.println(result.getScheduleStatus());
		return result;
	}

}
