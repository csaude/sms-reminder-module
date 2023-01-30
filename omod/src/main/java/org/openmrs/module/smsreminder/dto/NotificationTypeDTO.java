package org.openmrs.module.smsreminder.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationTypeDTO {

	private Integer notificationTypeId;
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

	public NotificationTypeDTO(Integer notificationTypeId, String name, String numberOfDays) {
		super();
		this.notificationTypeId=notificationTypeId;
		this.name = name;
		this.numberOfDays = numberOfDays;
	}
	
	public NotificationTypeDTO() {
	}

	public Integer getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

}
