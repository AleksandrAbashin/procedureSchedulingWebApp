package com.firstline.procedure.scheduling.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PdfService {
    public ResponseEntity<InputStreamResource> downloadPdf(Long id) throws IOException;
    public void pdfFromExcel() throws Exception;

}
