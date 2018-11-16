package com.firstline.procedure.scheduling.repos;

import com.firstline.procedure.scheduling.domain.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {
}
