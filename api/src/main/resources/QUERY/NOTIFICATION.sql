       select art_dispensation.* from 
           (
            select  
			         date(inicioTARV.data_inicio),  
			         pid.identifier AS nid, 
					 CONCAT(ifnull(pn.given_name,''),' ',ifnull(pn.middle_name,''),' ',ifnull(pn.family_name,''))  AS nome_completo, 
					  pa.value AS telemovel, p.gender AS sexo,  
					  maxFila.encounter_type as visita, 
					  date(maxFila.encounter_datetime) as ultima_visita,  
					  date(obsProximo.value_datetime) as proximo_visita, 
					  (to_days(curdate()) - to_days(obsProximo.value_datetime)) AS dias_remanescente, 
					  maxFila.patient_id 
				from 
				( 

						select p.patient_id AS patient_id,min(e.encounter_datetime) AS data_inicio 
						from ((patient p  join encounter e on((p.patient_id = e.patient_id))) 
						join obs o on((o.encounter_id = e.encounter_id)))  where ((e.voided = 0) 
						and (o.voided = 0)  and (p.voided = 0)  and (e.encounter_type in (18,6,9)) 
						and (o.concept_id = 1255)  and (o.value_coded = 1256) 
						and (e.encounter_datetime >= (select global_property.property_value 
						from global_property where (global_property.property = 'smsreminder.reference_date'))) 
						and (e.location_id = (select cast(global_property.property_value as unsigned) 
						from global_property where (global_property.property = 'smsreminder.location_id')))) 
						group by p.patient_id  

						union 
						
						select p.patient_id AS patient_id,min(o.value_datetime) AS data_inicio  from ((patient p 
						join encounter e on((p.patient_id = e.patient_id))) 
						join obs o on((e.encounter_id = o.encounter_id)))  where ((p.voided = 0) 
						and (e.voided = 0)  and (o.voided = 0)  and (e.encounter_type in (18,6,9)) 
						and (o.concept_id = 1190)  and (o.value_datetime is not null) 
						and (o.value_datetime >= (select global_property.property_value 
						from global_property where (global_property.property = 'smsreminder.reference_date'))) 
						and (e.location_id = (select cast(global_property.property_value as unsigned) 
						from global_property where (global_property.property = 'smsreminder.location_id')))) 
						group by p.patient_id  

						union

						select pg.patient_id AS patient_id,pg.date_enrolled AS data_inicio from (patient p 
						join patient_program pg on((p.patient_id = pg.patient_id)))  where ((pg.voided = 0) 
						and (p.voided = 0)  and (pg.program_id = 2) 
						and (pg.date_enrolled >= (select global_property.property_value 
						from global_property where (global_property.property = 'smsreminder.reference_date'))) 
						and (pg.location_id = (select cast(global_property.property_value as unsigned) 
						from global_property where (global_property.property = 'smsreminder.location_id'))))  
						)inicioTARV 
						inner join  
						(
						select patient.patient_id AS patient_id, 
						encounter.encounter_datetime AS encounter_datetime from obs 
						join encounter on obs.encounter_id = encounter.encounter_id 
						join patient on encounter.patient_id = patient.patient_id  where obs.concept_id = 6309 
						and encounter.encounter_type = 34 and encounter.voided=0 and patient.voided=0 
						and obs.value_coded = 6307  
						group by patient.patient_id  

						UNION 
						
						select patient.patient_id AS patient_id, 
						max(encounter.encounter_datetime) AS encounter_datetime from obs 
						join encounter on obs.encounter_id = encounter.encounter_id 
						join patient on encounter.patient_id = patient.patient_id  where  encounter.encounter_type = 35 
						and encounter.voided=0  and patient.voided=0 and obs.concept_id = 6309 
						and	obs.value_coded = 6307  
						group by patient.patient_id 
						
						) Contacto on inicioTARV.patient_id=Contacto.patient_id  

						inner join  
						( 
						select p.patient_id AS patient_id,  max(e.encounter_datetime) AS encounter_datetime, 
						e.encounter_type AS encounter_type from (patient p 
						join encounter e on((e.patient_id = p.patient_id)))  where ((p.voided = 0) 
						and (e.voided = 0)  and (e.encounter_type = 18))  
						group by p.patient_id 
						
						)maxFila on inicioTARV.patient_id=maxFila.patient_id 
						
						inner join 	obs obsProximo on obsProximo.person_id=maxFila.patient_id and obsProximo.concept_id=5096 and obsProximo.voided=0 
						and obsProximo.voided=0 and obsProximo.obs_datetime=maxFila.encounter_datetime 
						inner join person p on p.person_id = maxFila.patient_id  
						inner join person_attribute pa on pa.person_id = maxFila.patient_id and pa.person_attribute_type_id = 9 and pa.voided=0 
						inner join patient_identifier pid on pid.patient_id = maxFila.patient_id and pid.voided=0 
						inner join person_name pn on pn.person_id=maxFila.patient_id and pn.voided=0  
					

                       )art_dispensation
                       
                       where art_dispensation.dias_remanescente in(select number_of_days from  smsreminder_notification_type);