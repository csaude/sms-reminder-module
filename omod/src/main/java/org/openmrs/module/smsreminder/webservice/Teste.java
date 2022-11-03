package org.openmrs.module.smsreminder.webservice;

import pt.usendit.api.v2.ArrayOfSms;
import pt.usendit.api.v2.RemoteUsendit;
import pt.usendit.api.v2.RemoteUsenditSoap;
import pt.usendit.api.v2.ScheduleResult;
import pt.usendit.api.v2.Sms;

public class Teste {

	public static ScheduleResult sendMensage(String mensage, String number, String partnerMsgId) throws Throwable {
		RemoteUsenditSoap remoteUsendit = new RemoteUsendit().getRemoteUsenditSoap();
		ArrayOfSms smsbulk = new ArrayOfSms();
		Sms sms = new Sms();
		sms.setBeginTime(null);
		sms.setEndTime(null);
		sms.setExpirationDatetime(null);
		sms.setIsFlash(false);
		sms.setMessageText(mensage);
		sms.setMsisdn(number);
		sms.setSender("Info MZ");
		sms.setPriority(1);
		sms.setWorkingDays(false);
		sms.setPartnerMsgId(partnerMsgId);
		smsbulk.getSms().add(sms);
		ScheduleResult result = remoteUsendit.sendMessages("emaposse", "Mapo@@1991", "Info MZ", null, smsbulk);
		return result;
	}

}
