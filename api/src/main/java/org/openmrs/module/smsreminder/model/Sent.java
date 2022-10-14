package org.openmrs.module.smsreminder.model;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

/**
 * Created by Nelson.Mahumane on 03-09-2015.
 */
public class Sent extends BaseOpenmrsData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer sentId;
	private  Integer msgId;
	private String nid;
	private String fullName;
	private String phoneNumber;
	private String gender;
	private Date alertDate;
	private Date lastVisitDate;
	private Date nextVisitDate;
	private Date dateCreated;
	private String message;
	private String status;
	private String statusDescriptionReason;
	private Patient patient;
	private Integer reminderDays;
	private String uuid;


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getSentId() {
		return this.sentId;
	}

	public void setSentId(final Integer sentId) {
		this.sentId = sentId;
	}

	@Override
	public Integer getId() {
		return this.getSentId();
	}

	@Override
	public void setId(final Integer id) {
		this.setSentId(id);
	}

	public Date getAlertDate() {
		return this.alertDate;
	}

	public void setAlertDate(final Date alertDate) {
		this.alertDate = alertDate;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public Date getDateCreated() {
		return this.dateCreated;
	}

	@Override
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Sent)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		final Sent sent = (Sent) o;

		return this.sentId.equals(sent.sentId);

	}



	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getReminderDays() {
		return reminderDays;
	}

	public void setReminderDays(Integer reminderDays) {
		this.reminderDays = reminderDays;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatusDescriptionReason() {
		return statusDescriptionReason;
	}

	public void setStatusDescriptionReason(String statusDescriptionReason) {
		this.statusDescriptionReason = statusDescriptionReason;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	public Date getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(Date nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}



}
