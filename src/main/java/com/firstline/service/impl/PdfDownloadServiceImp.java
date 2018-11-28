package com.firstline.service.impl;

import com.firstline.repos.PatientRepository;
import com.firstline.service.PdfDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class PdfDownloadServiceImp implements PdfDownloadService {
    @Value("${upload.path}")
    private String uploadPath;

    final
    PatientRepository patientRepository;

    @Autowired
    public PdfDownloadServiceImp(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public ResponseEntity<InputStreamResource> download(Long id, Pdf pdf) throws IOException {

        String fileName = patientRepository.getById(id).getPatientInfo().getFileName();

        File file = new File(uploadPath + "/" + fileName + pdf.getPath());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }
}
