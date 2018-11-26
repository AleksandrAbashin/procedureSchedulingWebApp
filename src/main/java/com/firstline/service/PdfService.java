package com.firstline.service;

import com.firstline.service.impl.Pdf;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PdfService {

    public void pdfFromExcel() throws Exception;
    public ResponseEntity<InputStreamResource> downloadPdf(Long id, Pdf pdf) throws IOException;

}
