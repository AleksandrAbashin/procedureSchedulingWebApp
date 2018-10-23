package com.firstline.procedureScheduling.repos;


import com.firstline.procedureScheduling.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
