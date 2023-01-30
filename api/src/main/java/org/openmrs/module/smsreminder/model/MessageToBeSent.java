package org.openmrs.module.smsreminder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.openmrs.BaseOpenmrsData;

@Entity
@Table(name = "smsreminder_message_to_be_sent")
public class MessageToBeSent extends BaseOpenmrsData implements Serializable {

	private Integer messageToBeSentId;
	private Integer patientId;
	private String phoneNumber;
	private String nid;
	private String fullName;
	private String gender;
	private String message;
	private Integer reminderDays;
	private Date lastVisitDate;
	private Date nextVisitDate;
	public boolean wasSent;

	public boolean isWasSent() {
		return wasSent;
	}

	public void setWasSent(boolean wasSent) {
		this.wasSent = wasSent;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String mensage) {
		this.message = mensage;
	}

	@Override
	public Integer getId() {
		return patientId;
	}

	@Override
	public void setId(Integer id) {
		this.patientId = id;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public Integer getReminderDays() {
		return reminderDays;
	}

	public void setReminderDays(Integer reminderDays) {
		this.reminderDays = reminderDays;
	}

	private static final long serialVersionUID = 1L;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getMessageToBeSentId() {
		return messageToBeSentId;
	}

	public void setMessageToBeSentId(Integer messageToBeSentId) {
		this.messageToBeSentId = messageToBeSentId;
	}

}
