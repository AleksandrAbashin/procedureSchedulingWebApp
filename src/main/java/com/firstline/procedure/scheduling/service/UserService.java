package com.firstline.procedure.scheduling.service;

import com.firstline.procedure.scheduling.dto.PatientDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public UserDetails loadUserByName(String name);
    public PatientDto createStudy(PatientDto patientDto);
    public void deletePatient(Long id);
}
