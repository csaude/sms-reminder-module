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
import org.openmrs.module.smsreminder.model.NotificationPatient;
import org.openmrs.module.smsreminder.model.Sent;
import org.openmrs.module.smsreminder.utils.SentType;

/**
 * It is a default implementation of {@link SmsReminderDAO}.
 */
public class HibernateSmsReminderDAO implements SmsReminderDAO {
	protected final Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

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
	public Sent saveSent(final Sent sent) {
		getCurrentSession().saveOrUpdate(sent);
		return sent;
	}

	@Override
	public DeliveryReportStatus saveDeliveryReportStatus(DeliveryReportStatus deliveryReportStatus) {
		try {
			Query q = sessionFactory.getCurrentSession()
					.createQuery("FROM Sent s WHERE s.PartnerMsgId = :PartnerMsgId ");

			q.setParameter("PartnerMsgId", deliveryReportStatus.getPartnerMsgId());

			Sent sent = (Sent) q.uniqueResult();

			if (sent != null) {

				if (deliveryReportStatus.getStatus() != null) {

					if (deliveryReportStatus.getStatus() == 0) {
						sent.setStatus("ENTREGUE");
						sent.setStatusDescriptionReason(deliveryReportStatus.getDeliveryReportDescription());
						sent.setMsgId(deliveryReportStatus.getMsgId());

					}
					if (deliveryReportStatus.getStatus() == 1) {
						sent.setStatus("EM ESPERA");
						sent.setStatusDescriptionReason(deliveryReportStatus.getDeliveryReportDescription());
						sent.setMsgId(deliveryReportStatus.getMsgId());

					}
					if (deliveryReportStatus.getStatus() == 2) {
						sent.setStatus("NAO ENTREGUE");
						sent.setStatusDescriptionReason(deliveryReportStatus.getDeliveryReportDescription());
						sent.setMsgId(deliveryReportStatus.getMsgId());

					}
				}
				this.sessionFactory.getCurrentSession().saveOrUpdate(deliveryReportStatus);
				this.sessionFactory.getCurrentSession().saveOrUpdate(sent);

			}

		} catch (Exception e) {
		}
		return deliveryReportStatus;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sent> getAllSent() throws DAOException {
		final Criteria c = this.sessionFactory.getCurrentSession().createCriteria(Sent.class);

		return c.list();
	}

	public List<NotificationPatient> getNotificationPatientByDiasRemanescente() throws DAOException {
		final String sql = "select  " + "date(inicioTARV.data_inicio), " + "pid.identifier AS nid, "
				+ "CONCAT(ifnull(pn.given_name,''),' ',ifnull(pn.middle_name,''),' ',ifnull(pn.family_name,''))  AS nome_completo, "
				+ "pa.value AS telemovel," + "p.gender AS sexo, " + "maxFila.encounter_type as visita, "
				+ "date(maxFila.encounter_datetime) as ultima_visita, " + "date(obsProximo.value_datetime) as proximo_visita, "
				+ "(to_days(curdate()) - to_days(obsProximo.value_datetime)) AS dias_remanescente, maxFila.patient_id "
				+ "from ( " + "select p.patient_id AS patient_id,min(e.encounter_datetime) AS data_inicio "
				+ "from ((patient p " + "join encounter e on((p.patient_id = e.patient_id))) "
				+ "join obs o on((o.encounter_id = e.encounter_id))) " + "where ((e.voided = 0) "
				+ "and (o.voided = 0) " + "and (p.voided = 0) " + "and (e.encounter_type in (18,6,9)) "
				+ "and (o.concept_id = 1255) " + "and (o.value_coded = 1256) "
				+ "and (e.encounter_datetime >= (select global_property.property_value "
				+ "from global_property where (global_property.property = 'smsreminder.reference_date'))) "
				+ "and (e.location_id = (select cast(global_property.property_value as unsigned) "
				+ "from global_property where (global_property.property = 'smsreminder.us')))) "
				+ "group by p.patient_id " + "union "
				+ "select p.patient_id AS patient_id,min(o.value_datetime) AS data_inicio " + "from ((patient p "
				+ "join encounter e on((p.patient_id = e.patient_id))) "
				+ "join obs o on((e.encounter_id = o.encounter_id))) " + "where ((p.voided = 0) "
				+ "and (e.voided = 0) " + "and (o.voided = 0) " + "and (e.encounter_type in (18,6,9)) "
				+ "and (o.concept_id = 1190) " + "and (o.value_datetime is not null) "
				+ "and (o.value_datetime >= (select global_property.property_value "
				+ "from global_property where (global_property.property = 'smsreminder.reference_date'))) "
				+ "and (e.location_id = (select cast(global_property.property_value as unsigned) "
				+ "from global_property where (global_property.property = 'smsreminder.us')))) "
				+ "group by p.patient_id " + "union "
				+ "select pg.patient_id AS patient_id,pg.date_enrolled AS data_inicio from (patient p "
				+ "join patient_program pg on((p.patient_id = pg.patient_id))) " + "where ((pg.voided = 0) "
				+ "and (p.voided = 0) " + "and (pg.program_id = 2) "
				+ "and (pg.date_enrolled >= (select global_property.property_value "
				+ "from global_property where (global_property.property = 'smsreminder.reference_date'))) "
				+ "and (pg.location_id = (select cast(global_property.property_value as unsigned) "
				+ "from global_property where (global_property.property = 'smsreminder.us')))) " + ")inicioTARV "
				+ "inner join " + "( " + "select patient.patient_id AS patient_id, "
				+ "encounter.encounter_datetime AS encounter_datetime from obs "
				+ "join encounter on obs.encounter_id = encounter.encounter_id "
				+ "join patient on encounter.patient_id = patient.patient_id " + "where obs.concept_id = 6309 "
				+ "and encounter.encounter_type = 34 and encounter.voided=0 and patient.voided=0 "
				+ "and obs.value_coded = 6307 " + "group by patient.patient_id " + "UNION "
				+ "select patient.patient_id AS patient_id, "
				+ "max(encounter.encounter_datetime) AS encounter_datetime from obs "
				+ "join encounter on obs.encounter_id = encounter.encounter_id "
				+ "join patient on encounter.patient_id = patient.patient_id " + "where  encounter.encounter_type = 35 "
				+ "and encounter.voided=0 " + "and patient.voided=0 and obs.concept_id = 6309 "
				+ "and	obs.value_coded = 6307 " + "group by patient.patient_id "
				+ ") Contacto on inicioTARV.patient_id=Contacto.patient_id " + "inner join " + "( "
				+ "select p.patient_id AS patient_id, " + "max(e.encounter_datetime) AS encounter_datetime, "
				+ "e.encounter_type AS encounter_type from (patient p "
				+ "join encounter e on((e.patient_id = p.patient_id))) " + "where ((p.voided = 0) "
				+ "and (e.voided = 0) " + "and (e.encounter_type = 18)) " + "group by p.patient_id "
				+ ")maxFila on inicioTARV.patient_id=maxFila.patient_id "
				+ "inner join 	obs obsProximo on obsProximo.person_id=maxFila.patient_id and obsProximo.concept_id=5096 and obsProximo.voided=0 and obsProximo.voided=0 and obsProximo.obs_datetime=maxFila.encounter_datetime "
				+ "inner join person p on p.person_id = maxFila.patient_id  "
				+ "inner join person_attribute pa on pa.person_id = maxFila.patient_id and pa.person_attribute_type_id = 9 and pa.voided=0 "
				+ "inner join patient_identifier pid on pid.patient_id = maxFila.patient_id and pid.voided=0 "
				+ "inner join person_name pn on pn.person_id=maxFila.patient_id and pn.voided=0  "
				+ "group by maxFila.patient_id  ";

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
			;

			notificationPatients.add(notificationPatient);
		}

		return notificationPatients;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationPatient> searchFollowUpPatient() {

		final String sql = "select patient_id, max_frida.encounter_datetime, pa.value, datediff(CURDATE(),o.value_datetime) "
				+ "from(Select p.patient_id,max(encounter_datetime) encounter_datetime from patient p  "
				+ "inner join encounter e on e.patient_id=p.patient_id where p.voided=0 and e.voided=0 and e.encounter_type=18  and e.encounter_datetime<=CURDATE() "
				+ "group by p.patient_id) max_frida  inner join obs o on o.person_id=max_frida.patient_id "
				+ "inner join person_attribute pa on (pa.person_id = max_frida.patient_id) " + "inner join ( "
				+ "select p.patient_id as p from patient p " + "inner join encounter e on e.patient_id = p.patient_id "
				+ "inner join obs o on o.encounter_id = e.encounter_id and o.concept_id=6306 "
				+ "inner join obs telef on telef.encounter_id=e.encounter_id and telef.concept_id=6309 and telef.value_coded=6307 and telef.voided=0 "
				+ "where e.encounter_type in (34,35) and e.voided=0 and p.voided=0 and o.voided=0 "
				+ "and p.patient_id NOT IN(select ultima.patient_id from "
				+ "(SELECT p.patient_id as patient_id, MAX(e.encounter_datetime) as encounter_datetime "
				+ "FROM patient p INNER JOIN encounter e ON e.patient_id=p.patient_id "
				+ "INNER JOIN obs o on o.encounter_id=e.encounter_id and o.concept_id=6306 and o.concept_id is not null  "
				+ "WHERE e.encounter_type in (34,35) AND o.concept_id is not null and o.value_coded is not null "
				+ "AND o.voided=0 and e.voided=0 GROUP BY p.patient_id) ultima "
				+ "inner join obs o on o.person_id=ultima.patient_id and o.obs_datetime=ultima.encounter_datetime and o.concept_id=6306 and o.voided=0 and o.value_coded=1066) "
				+ ") consitiu on consitiu.p=max_frida.patient_id "
				+ "where max_frida.encounter_datetime=o.obs_datetime and o.voided=0 and o.concept_id=5096 and pa.person_attribute_type_id = 9 and pa.value is not null and pa.voided = 0 and "
				+ "patient_id not in (select pg.patient_id from patient p "
				+ "inner join patient_program pg on p.patient_id=pg.patient_id "
				+ "inner join patient_state ps on pg.patient_program_id=ps.patient_program_id "
				+ "where pg.voided=0 and ps.voided=0 and p.voided=0 and "
				+ "pg.program_id=2 and ps.state in (7,8,9,10) and ps.end_date is null and "
				+ "ps.start_date<=CURDATE() union select patient_id from "
				+ "(Select p.patient_id,max(encounter_datetime) encounter_datetime from patient p  "
				+ "inner join encounter e on e.patient_id=p.patient_id "
				+ "inner join obs o on o.encounter_id=e.encounter_id "
				+ "where p.voided=0 and e.voided=0 and e.encounter_type in (6,9) and "
				+ "o.voided=0 and o.concept_id=1255 and o.value_coded<>1260 and e.encounter_datetime<=CURDATE() "
				+ "group by p.patient_id) max_mov " + "inner join obs o on o.person_id=max_mov.patient_id "
				+ "where max_mov.encounter_datetime=o.obs_datetime and o.voided=0 and  "
				+ "o.concept_id=1410 and datediff(CURDATE(), o.value_datetime)<1) "
				+ "and datediff(CURDATE(),o.value_datetime) between 1 and 60 ";

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