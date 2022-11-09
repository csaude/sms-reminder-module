package org.openmrs.module.smsreminder.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.openmrs.BaseOpenmrsData;


@Entity
@Table(name = "smsreminder_delivery_report_status")
public class DeliveryReportStatus extends BaseOpenmrsData {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer msgId;
	private String uuid;
	private String deliveryReportDescription;
	private String deliveryReportReasonCode;
	private Date deliveryReportUpdateDatetime;
	private Integer deliveryReportStatus;
	private Date messageSentDatetime;
	private Integer nrSms;
	private String partnerMsgId;
	private Date dateCreated;

	public DeliveryReportStatus() {

	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDeliveryReportDescription() {
		return deliveryReportDescription;
	}

	public void setDeliveryReportDescription(String deliveryReportDescription) {
		this.deliveryReportDescription = deliveryReportDescription;
	}

	public String getDeliveryReportReasonCode() {
		return deliveryReportReasonCode;
	}

	public void setDeliveryReportReasonCode(String deliveryReportReasonCode) {
		this.deliveryReportReasonCode = deliveryReportReasonCode;
	}

	public Date getDeliveryReportUpdateDatetime() {
		return deliveryReportUpdateDatetime;
	}

	public void setDeliveryReportUpdateDatetime(Date deliveryReportUpdateDatetime) {
		this.deliveryReportUpdateDatetime = deliveryReportUpdateDatetime;
	}

	public Date getMessageSentDatetime() {
		return messageSentDatetime;
	}

	public void setMessageSentDatetime(Date messageSentDatetime) {
		this.messageSentDatetime = messageSentDatetime;
	}

	public String getPartnerMsgId() {
		return partnerMsgId;
	}

	public void setPartnerMsgId(String partnerMsgId) {
		this.partnerMsgId = partnerMsgId;
	}



	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DeliveryReportStatus)) {
			return false;
		}
		DeliveryReportStatus other = (DeliveryReportStatus) obj;
		if (getUuid() == null) {
			return false;
		}
		return getUuid().equals(other.getUuid());
	}

	public int hashCode() {
		return (getUuid() == null ? super.hashCode() : getUuid().hashCode());
	}

	public Integer getNrSms() {
		return nrSms;
	}

	public void setNrSms(Integer nrSms) {
		this.nrSms = nrSms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeliveryReportStatus() {
		return deliveryReportStatus;
	}

	public void setDeliveryReportStatus(Integer deliveryReportStatus) {
		this.deliveryReportStatus = deliveryReportStatus;
	}

}
