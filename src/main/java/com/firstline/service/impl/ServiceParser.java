package com.firstline.service.impl;

import com.firstline.domain.Patient;
import com.firstline.domain.PatientInfo;
import com.firstline.dto.PatientDto;
import com.firstline.repos.PatientRepository;
import com.firstline.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ServiceParser {

    @Value("${upload.path}")
    private String uploadPath;

    private String fileLocation;

    private final PatientService patientService;

    private final PatientRepository patientRepository;

    @Autowired
    public ServiceParser(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public void saveExcelFile(PatientDto patientDto, MultipartFile file) throws IOException {

        PatientInfo patientInfo = new PatientInfo();


        String uuidFile = UUID.randomUUID().toString();
        fileLocation = uuidFile + "." + uuidFile;

        file.transferTo(new File(uploadPath + "/" + fileLocation));

        patientInfo.setFileName(fileLocation);


        Long patientId = patientService.createPatient(patientDto);
        Patient patient = patientRepository.findById(patientId).get();
        patient.setPatientInfo(patientInfo);

        patientRepository.save(patient);

    }

}
