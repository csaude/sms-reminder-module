package org.openmrs.module.smsreminder.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.BaseOpenmrsData;

@Entity
@Table(name = "smsreminder_notification_type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationType extends BaseOpenmrsData implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, numberOfDays);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		NotificationType other = (NotificationType) obj;
		return Objects.equals(name, other.name) && numberOfDays == other.numberOfDays;
	}

	public NotificationType(Integer notificationTypeId, String name, int numberOfDays) {
		this.notificationTypeId = notificationTypeId;
		this.name = name;
		this.numberOfDays = numberOfDays;
	}

	public NotificationType() {
	}

	private static final long serialVersionUID = 1L;
	private Integer notificationTypeId;
	private String name;
	private int numberOfDays;

	@Override
	public Integer getId() {
		return getNotificationTypeId();
	}

	@Override
	public void setId(Integer id) {
		setNotificationTypeId(id);
	}

	public Integer getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
