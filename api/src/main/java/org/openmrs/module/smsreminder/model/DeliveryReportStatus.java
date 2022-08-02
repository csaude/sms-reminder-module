package org.openmrs.module.smsreminder.model;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;

public class DeliveryReportStatus extends BaseOpenmrsData {

	private static final long serialVersionUID = 1L;
	private Integer msgId;
	private String deliveryReportDescription;
	private String deliveryReportReasonCode;
	private Date deliveryReportUpdateDatetime;
	private int deliveryReportStatus;
	private Date messageSentDatetime;
	private int nrSms;
	private String partnerMsgId;

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
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

	public int getNrSms() {
		return nrSms;
	}

	public void setNrSms(int nrSms) {
		this.nrSms = nrSms;
	}

	public String getPartnerMsgId() {
		return partnerMsgId;
	}

	public void setPartnerMsgId(String partnerMsgId) {
		this.partnerMsgId = partnerMsgId;
	}

	public int getDeliveryReportStatus() {
		return deliveryReportStatus;
	}

	public void setDeliveryReportStatus(int deliveryReportStatus) {
		this.deliveryReportStatus = deliveryReportStatus;
	}

	@Override
	public Integer getId() {
		return msgId;
	}

	@Override
	public void setId(Integer id) {
		this.msgId = id;

	}

}
