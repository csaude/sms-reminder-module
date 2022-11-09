select patient_id, max_frida.encounter_datetime, pa.value, datediff(CURDATE(),o.value_datetime) 
				from(Select p.patient_id,max(encounter_datetime) encounter_datetime from patient p  
				inner join encounter e on e.patient_id=p.patient_id where p.voided=0 and e.voided=0 and e.encounter_type=18  and e.encounter_datetime<=CURDATE() 
				group by p.patient_id) max_frida  inner join obs o on o.person_id=max_frida.patient_id 
				inner join person_attribute pa on (pa.person_id = max_frida.patient_id)  inner join ( 
				select p.patient_id as p from patient p  inner join encounter e on e.patient_id = p.patient_id 
				inner join obs o on o.encounter_id = e.encounter_id and o.concept_id=6306 
				inner join obs telef on telef.encounter_id=e.encounter_id and telef.concept_id=6309 and telef.value_coded=6307 and telef.voided=0 
				where e.encounter_type in (34,35) and e.voided=0 and p.voided=0 and o.voided=0 
				and p.patient_id NOT IN(select ultima.patient_id from 
				(SELECT p.patient_id as patient_id, MAX(e.encounter_datetime) as encounter_datetime 
				FROM patient p INNER JOIN encounter e ON e.patient_id=p.patient_id 
				INNER JOIN obs o on o.encounter_id=e.encounter_id and o.concept_id=6306 and o.concept_id is not null  
				WHERE e.encounter_type in (34,35) AND o.concept_id is not null and o.value_coded is not null 
				AND o.voided=0 and e.voided=0 GROUP BY p.patient_id) ultima 
				inner join obs o on o.person_id=ultima.patient_id and o.obs_datetime=ultima.encounter_datetime and o.concept_id=6306 and o.voided=0 and o.value_coded=1066) 
				) consitiu on consitiu.p=max_frida.patient_id 
				where max_frida.encounter_datetime=o.obs_datetime and o.voided=0 and o.concept_id=5096 and pa.person_attribute_type_id = 9 and pa.value is not null and pa.voided = 0 and 
				patient_id not in (select pg.patient_id from patient p 
				inner join patient_program pg on p.patient_id=pg.patient_id 
				inner join patient_state ps on pg.patient_program_id=ps.patient_program_id 
				where pg.voided=0 and ps.voided=0 and p.voided=0 and 
				pg.program_id=2 and ps.state in (7,8,9,10) and ps.end_date is null and 
				ps.start_date<=CURDATE() union select patient_id from 
				(Select p.patient_id,max(encounter_datetime) encounter_datetime from patient p  
				inner join encounter e on e.patient_id=p.patient_id 
				inner join obs o on o.encounter_id=e.encounter_id 
				where p.voided=0 and e.voided=0 and e.encounter_type in (6,9) and 
				o.voided=0 and o.concept_id=1255 and o.value_coded<>1260 and e.encounter_datetime<=CURDATE() 
				group by p.patient_id) max_mov  inner join obs o on o.person_id=max_mov.patient_id 
				where max_mov.encounter_datetime=o.obs_datetime and o.voided=0 and  
				o.concept_id=1410 and datediff(CURDATE(), o.value_datetime)<1) 
				and datediff(CURDATE(),o.value_datetime) between 1 and 60 