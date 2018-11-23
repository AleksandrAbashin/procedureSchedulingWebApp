package com.firstline.procedure.scheduling.repos;


import com.firstline.procedure.scheduling.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient getById(Long id);
}
