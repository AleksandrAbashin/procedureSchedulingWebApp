package com.firstline.service;

import com.firstline.service.impl.Pdf;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PdfDownloadService {
    public ResponseEntity<InputStreamResource> download(Long id, Pdf pdf) throws IOException;
}
