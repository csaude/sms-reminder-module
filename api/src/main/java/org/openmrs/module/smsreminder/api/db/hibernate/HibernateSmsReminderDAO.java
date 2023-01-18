/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.smsreminder.api.db.hibernate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.smsreminder.api.db.SmsReminderDAO;
import org.openmrs.module.smsreminder.model.DeliveryReportStatus;
import org.openmrs.module.smsreminder.model.MensageSent;
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.utils.MensageStatus;
import org.openmrs.module.smsreminder.utils.QuerysUtils;
import org.openmrs.module.smsreminder.utils.SentType;

/**
 * It is a default implementation of {@link SmsReminderDAO}.
 */
public class HibernateSmsReminderDAO implements SmsReminderDAO {
	protected final Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;
	private static String NOTIFICATION = "QUERY/NOTIFICATION.sql";
	private static String LTFU = "QUERY/LTFU.sql";

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private org.hibernate.Session getCurrentSession() {
		try {
			return this.sessionFactory.getCurrentSession();
		} catch (final NoSuchMethodError ex) {
			try {
				final Method method = this.sessionFactory.getClass().getMethod("getCurrentSession", null);
				return (org.hibernate.Session) method.invoke(this.sessionFactory, null);
			} catch (final Exception e) {
				throw new RuntimeException("Failed to get the current hibernate session", e);
			}
		}
	}

	@Override
	public MensageSent saveSent(final MensageSent sent) {
		getCurrentSession().saveOrUpdate(sent);
		return sent;
	}

	@Override
	public DeliveryReportStatus saveDeliveryReportStatus(DeliveryReportStatus deliveryReportStatus) {
		try {
			Query q = sessionFactory.getCurrentSession()
					.createQuery("FROM MensageSent s WHERE s.PartnerMsgId = :PartnerMsgId ");

			q.setParameter("PartnerMsgId", deliveryReportStatus.getPartnerMsgId());

			MensageSent sent = (MensageSent) q.uniqueResult();

			if (deliveryReportStatus.getDeliveryReportStatus() == 0) {

				sent.setLastStatus(MensageStatus.DELIVERED.getName());
				sent.setDateLastStatus(deliveryReportStatus.getDeliveryReportUpdateDatetime());
			}
			if (deliveryReportStatus.getDeliveryReportStatus() == 1) {
				sent.setLastStatus(MensageStatus.ON_HOLD.getName());
				sent.setDateLastStatus(deliveryReportStatus.getDeliveryReportUpdateDatetime());

			}
			if (deliveryReportStatus.getDeliveryReportStatus() == 2) {
				sent.setLastStatus(MensageStatus.NOT_DELIVERY.getName());
				sent.setDateLastStatus(deliveryReportStatus.getDeliveryReportUpdateDatetime());

			}
			this.sessionFactory.getCurrentSession().saveOrUpdate(deliveryReportStatus);
			this.sessionFactory.getCurrentSession().saveOrUpdate(sent);

		} catch (Exception e) {

		}
		return deliveryReportStatus;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MensageSent> getAllSent() throws DAOException {
		final Criteria c = this.sessionFactory.getCurrentSession().createCriteria(MensageSent.class);

		return c.list();
	}

	public List<NotificationPatient> getNotificationPatients() throws DAOException {

		final String sql = QuerysUtils.loadQuery(NOTIFICATION);

		final Query query = this.getCurrentSession().createSQLQuery(sql);

		final List<NotificationPatient> notificationPatients = new ArrayList<NotificationPatient>();

		@SuppressWarnings("unchecked")
		final List<Object[]> list = query.list();

		for (final Object[] object : list) {

			final NotificationPatient notificationPatient = new NotificationPatient();

			notificationPatient.setArtStartDate((Date) object[0]);
			notificationPatient.setNid((String) object[1]);
			notificationPatient.setFullName((String) object[2]);
			notificationPatient.setPhoneNumber((String) object[3]);
			notificationPatient.setGender((String) object[4]);
			notificationPatient.setVisitType((Integer) object[5]);
			notificationPatient.setLastVisitDate((Date) object[6]);
			notificationPatient.setNextVisitDate((Date) object[7]);
			notificationPatient.setReminderDays((Integer) object[8]);
			notificationPatient.setSentType(SentType.NOVO_INICIO);
			notificationPatient.setPatientId((Integer) object[9]);

			notificationPatients.add(notificationPatient);

			if (notificationPatient.getReminderDays() == 15 || notificationPatient.getReminderDays() == 7
					|| notificationPatient.getReminderDays() == 3) {

//				notificationPatients.add(notificationPatient);
			}
		}

		return notificationPatients;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationPatient> searchFollowUpPatient() {

		final String sql = QuerysUtils.loadQuery(LTFU);
		final Query query = this.getCurrentSession().createSQLQuery(sql);

		final List<NotificationPatient> notificationPatients = new ArrayList<NotificationPatient>();

		final List<Object[]> list = query.list();

		for (final Object[] object : list) {

			final NotificationPatient notificationPatient = new NotificationPatient();

			notificationPatient.setId((Integer) object[0]);
			notificationPatient.setArtStartDate((Date) object[1]);
			notificationPatient.setNid((String) object[2]);
			notificationPatient.setReminderDays((Integer) object[2]);

			notificationPatients.add(notificationPatient);
		}

		return notificationPatients;
	}

	@Override
	public DeliveryReportStatus searchDeliveryMensage(String uuid) {
		return (DeliveryReportStatus) this.sessionFactory.getCurrentSession().get(DeliveryReportStatus.class, uuid);
	}

}