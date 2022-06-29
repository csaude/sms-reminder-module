package org.openmrs.module.smsrimender.model;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import org.openmrs.module.smsrimender.utils.SentType;

/**
 * Created by Nelson.Mahumane on 03-09-2015.
 */
public class Sent extends BaseOpenmrsData implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer sentId;

	private  String phoneNumber;
    private Date alertDate;
    private Date dateCreated;
    private String message;
    private String status;
    private Patient patient;
    private Integer reminderDays;
    private SentType sentType;

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

	public SentType getSentType() {
		return this.sentType;
	}

	public void setSentType(final SentType sentType) {
		this.sentType = sentType;
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



}
