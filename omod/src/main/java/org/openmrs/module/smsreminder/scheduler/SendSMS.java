package org.openmrs.module.smsreminder.scheduler;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SendSMS {

	public static String sms(String to, String text) throws UnirestException {
		HttpResponse<String> response;
		response = Unirest.post("https://api.infobip.com/sms/1/text/single")

				.header("authorization", "Basic QXJ0c29mdFRlYzpNYXRzMTAxMCE=")
				.header("content-type", "application/json").header("accept", "application/json")
				.body("{\"from\":\"FGH\",\"to\":\"+" + to + "\",\"text\":\"".concat(text) + "\"}").asString();

		return response.getBody();
	}

	public static void main(String[] args) throws UnirestException {
		SendSMS.sms("+258840665903", "Ola");

	}

	public String SendMessage(String username, String password, String partnerEventId, String timezone,
			String partnerMsgId, String sender, String msisdn, int mobileOperator, int priority,
			String expirationDatetime, String messageText, String scheduleDatetime, String beginTime, String endTime,
			Boolean workingDays, Boolean isFlash) {
		
		return null;

	}
}
