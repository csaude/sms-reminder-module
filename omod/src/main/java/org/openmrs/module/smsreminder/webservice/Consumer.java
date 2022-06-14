package org.openmrs.module.smsreminder.webservice;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Consumer {

	public static void main(String[] args) throws Throwable {

		/*
		 * RemoteUsenditSoap remoteUsendit = new RemoteUsendit().getRemoteUsenditSoap();
		 * ArrayOfSms smsArray = new ArrayOfSms(); Sms sms = new Sms();
		 * GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		 * XMLGregorianCalendar a =
		 * DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		 * sms.setBeginTime(a); sms.setEndTime(a); sms.setExpirationDatetime(null);
		 * sms.setIsFlash(false);
		 * sms.setMessageText("TESTE DE INTEGRACAO SMS RIMINDER");
		 * sms.setMsisdn("258878623077"); sms.setSender("Info MZ"); sms.setPriority(1);
		 * sms.setWorkingDays(false); sms.setPartnerMsgId("Info MZ");
		 * smsArray.getSms().add(sms); ScheduleResult result =
		 * remoteUsendit.sendMessages("emaposse", "Mapo@@1991", "Info MZ", null,
		 * smsArray); System.out.println(result.getScheduleStatus().value());
		 * System.out.println(result.getCreditsSpent());
		 * System.out.println(result.getTotalRecipients());
		 */

	       InputStream fileIn;
	        FileOutputStream fileOut;
	        Scanner s = new Scanner(System.in);

	        System.out.println("https://usendit.co.mz/SMSDeliveries/Report/List?idEventLog=14122386&backUrl=%2fSMSDeliveries%2fSMSDeliveries%2fListPerformedSMSDeliveries%3fuserId%3d3559");
	        String urlStr = s.nextLine();

	        URL url = new URL(urlStr);
	        URLConnection urlConnect = url.openConnection();
	        fileIn = urlConnect.getInputStream();

	        System.out.println("SMS");
	        String fileStr = s.nextLine();
	        fileOut = new FileOutputStream(fileStr);

	        while (fileIn.read() != -1) {   
	            fileOut.write(fileIn.read());
	        }
	        System.out.println("File is downloaded");
	}

}
