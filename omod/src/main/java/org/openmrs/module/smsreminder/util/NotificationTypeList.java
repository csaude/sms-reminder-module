package org.openmrs.module.smsreminder.util;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.openmrs.module.smsreminder.dto.NotificationTypeDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = NotificationTypeList.class)
public class NotificationTypeList {

	List<NotificationTypeDTO> notificationTypes;

	public List<NotificationTypeDTO> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(List<NotificationTypeDTO> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public NotificationTypeList(List<NotificationTypeDTO> notificationTypes) {
		super();
		this.notificationTypes = notificationTypes;
	}

	public NotificationTypeList() {
	}
}
