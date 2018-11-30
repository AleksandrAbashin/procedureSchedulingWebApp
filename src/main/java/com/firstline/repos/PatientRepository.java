package com.firstline.repos;

import com.firstline.domain.Patient;
import com.firstline.domain.Study;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient getById(Long id);

    @Query(value = "SELECT DISTINCT p FROM Patient p JOIN FETCH p.studies s JOIN FETCH p.patientInfo WHERE s.patient.id = :id")
    List<Study> getListStudyByPatientId(@Param("id") Long id);

    @Query(value = "SELECT p FROM Patient p JOIN FETCH p.studies JOIN FETCH p.patientInfo")
    List<Patient> findAllPatient();


    @Query(value = "SELECT p FROM Patient p WHERE p.patientName = :name")
    Patient findByName(@Param("name") String patientName);

    @EntityGraph("patient.studies")
    Patient findOneById(Long id);

    @EntityGraph(attributePaths = "studies")
    Patient getOneById(Long id);


    @EntityGraph("patient.studies")
    List<Patient> findAll();


}
