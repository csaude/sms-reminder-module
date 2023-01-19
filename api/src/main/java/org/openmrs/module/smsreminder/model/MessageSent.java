package org.openmrs.module.smsreminder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

@Entity
@Table(name = "smsreminder_message_sent")
public class MessageSent extends BaseOpenmrsData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer messageSentId;
	private Integer msgId;
	private String PartnerMsgId;
	private String nid;
	private String fullName;
	private String phoneNumber;
	private String gender;
	private Date alertDate;
	private Date lastVisitDate;
	private Date nextVisitDate;
	private Date dateCreated;
	private String message;
	private String lastStatus;
	private Date lastDateStatus;
	private Patient patient;
	private Integer reminderDays;

	@Override
	public Integer getId() {
		return this.getMessageSentId();
	}

	@Override
	public void setId(final Integer id) {
		this.setMessageSentId(id);
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
		if (!(o instanceof MessageSent)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		final MessageSent sent = (MessageSent) o;

		return this.messageSentId.equals(sent.messageSentId);

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

	public String getPartnerMsgId() {
		return PartnerMsgId;
	}

	public void setPartnerMsgId(String partnerMsgId) {
		PartnerMsgId = partnerMsgId;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public Integer getMessageSentId() {
		return messageSentId;
	}

	public void setMessageSentId(Integer messageSentId) {
		this.messageSentId = messageSentId;
	}

	public Date getLastDateStatus() {
		return lastDateStatus;
	}

	public void setLastDateStatus(Date lastDateStatus) {
		this.lastDateStatus = lastDateStatus;
	}

}
