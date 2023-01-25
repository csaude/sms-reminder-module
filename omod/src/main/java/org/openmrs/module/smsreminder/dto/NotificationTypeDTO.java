package org.openmrs.module.smsreminder.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationTypeDTO {

	private String name;
	private String numberOfDays;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public NotificationTypeDTO(String name, String numberOfDays) {
		super();
		this.name = name;
		this.numberOfDays = numberOfDays;
	}
	
	public NotificationTypeDTO() {
	}

}
