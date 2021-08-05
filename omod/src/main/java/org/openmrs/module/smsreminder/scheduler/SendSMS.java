package org.openmrs.module.smsreminder.scheduler;

import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class SendSMS {

	public String sms(String to, String text) throws UnirestException {
		HttpResponse<String> response;
		response = Unirest.post("https://api.infobip.com/sms/1/text/single")

				.header("authorization", "Basic QXJ0c29mdFRlYzpNYXRzMTAxMCE=")
				.header("content-type", "application/json").header("accept", "application/json")
				.body("{\"from\":\"FGH\",\"to\":\"+" + to + "\",\"text\":\"".concat(text) + "\"}").asString();

		return response.getBody();
	}
}
