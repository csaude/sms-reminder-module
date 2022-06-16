package org.openmrs.module.smsreminder.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.module.smsreminder.utils.SentType;

public class NotificationPatient extends BaseOpenmrsData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer patientId;
	private String phoneNumber;
	private String mensage;
	private BigInteger reminderDays;
	private String nid;
    private Integer visitType;;
	private Date lastVisitDate;
	private Date nextVisitDate;
	private Date artStartDate;
	private SentType sentType;
	private String fullName;
	private String gender;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	@Override
	public Integer getId() {
		return patientId;
	}

	@Override
	public void setId(Integer id) {
		this.patientId = id;

	}

	public BigInteger getReminderDays() {
		return reminderDays;
	}

	public void setReminderDays(BigInteger reminderDays) {
		this.reminderDays = reminderDays;
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

	public Date getArtStartDate() {
		return artStartDate;
	}

	public void setArtStartDate(Date artStartDate) {
		this.artStartDate = artStartDate;
	}

	public SentType getSentType() {
		return sentType;
	}

	public void setSentType(SentType sentType) {
		this.sentType = sentType;
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

	public Integer getVisitType() {
		return visitType;
	}

	public void setVisitType(Integer visitType) {
		this.visitType = visitType;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}


}
